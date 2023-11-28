package com.main.Database.ClubCreation;

import com.main.Database.ClubCreation.BaseDatabaseConnection;
import com.main.clubcreation.CreateClub;
import javafx.scene.image.Image;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection extends BaseDatabaseConnection {

    public DatabaseConnection(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }


    public void connect() throws SQLException {
        connection = DriverManager.getConnection(url, userName, password);
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void insertClub(CreateClub club ) throws SQLException{
        if (advisorIdExists(club.getClubAdvisor())) {
            String insertQuery = "INSERT INTO club_creation (clubID, clubName, description, clubCategory, advisor_id, email, contact, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, club.getClubID());
                statement.setString(2, club.getClubName());
                statement.setString(3, club.getDescription());
                statement.setString(4, club.getClubCategory());
                statement.setString(5, club.getClubAdvisor());
                statement.setString(6, club.getEmail());
                statement.setInt(7, club.getContact());
                File imageFile = new File(String.valueOf(club.getImage()));
                if (imageFile.exists()) {
                    statement.setString(8, String.valueOf(club.getImage()));
                } else {
                    System.out.println("Image file not found: " + club.getImage());
                    statement.setString(8, " ");
                }

                statement.executeUpdate();
            }
        } else {
            System.out.println("Invalid advisor_id. Please enter a valid advisor_id.");
        }
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sacms";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url, user, password);
    }

    public void closeResources(Connection connection, PreparedStatement preparedStatement, AutoCloseable resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateClub(CreateClub club) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            String updateQuery = "UPDATE club_creation SET clubName=?, description=?, clubCategory=?, advisor_id=?, email=?, contact=?, image=? WHERE clubID=?";
            preparedStatement = connection.prepareStatement(updateQuery);

            preparedStatement.setString(1, club.getClubName());
            preparedStatement.setString(2, club.getDescription());
            preparedStatement.setString(3, club.getClubCategory());
            preparedStatement.setString(4, club.getClubAdvisor());
            preparedStatement.setString(5, club.getEmail());
            preparedStatement.setInt(6, club.getContact());
            preparedStatement.setString(7, String.valueOf(club.getImage()));
            preparedStatement.setString(8, club.getClubID());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteClub(CreateClub club) throws SQLException {
        String deleteQuery = "DELETE FROM club_creation WHERE clubID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, club.getClubID());
            preparedStatement.executeUpdate();
        }
    }

    private boolean advisorIdExists(String advisorId) throws SQLException {
        String insertQuery = "SELECT advisor_id FROM club_advisors WHERE advisor_id = ?";
        String selectQuery = "SELECT * FROM club_creation";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, advisorId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    public static List<CreateClub> clubDetails(String url, String userName, String password) {
        List<CreateClub> clubList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            String selectQuery = "SELECT * FROM club_creation";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    CreateClub createClub = new CreateClub(
                            resultSet.getString("clubID"),
                            resultSet.getString("clubName"),
                            resultSet.getString("description"),
                            resultSet.getString("clubCategory"),
                            resultSet.getString("advisor_id"),
                            resultSet.getNString("email"),
                            resultSet.getInt("contact"),
                            null
                    );
                    Blob imageBlob = resultSet.getBlob("image");
                    if (imageBlob != null) {
                        try (InputStream inputStream = imageBlob.getBinaryStream()) {
                            Image image = new Image(inputStream);
                            createClub.setImage(image);
                        }
                    }
                    clubList.add(createClub);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return clubList;
    }


}
