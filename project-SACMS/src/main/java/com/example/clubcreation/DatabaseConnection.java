package com.example.clubcreation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseConnection {
    private Connection connection;
    private static String url;
    private static String userName;
    private static String password;

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
        String insertQuery = "INSERT INTO club_creation (clubID, advisor_id, clubName, description, clubCategory, email, contact, image) VALUES (?,?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, club.getClubID());
            statement.setString(2, club.getClubName());
            statement.setString(3, club.getDescription());
            statement.setString(4, club.getClubCategory());
            statement.setString(5, club.getClubAdvisor());
            statement.setString(6, club.getEmail());
            statement.setInt(7, club.getContact());
            statement.setString(8, club.getImage());

            statement.executeUpdate();
        }
        } else {
            System.out.println("Invalid advisor_id. Please enter a valid advisor_id.");
        }
    }

    private boolean advisorIdExists(String advisorId) throws SQLException {
        String query = "SELECT advisor_id FROM club_advisors WHERE advisor_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, advisorId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }
    public static List<CreateClub>clubDeatils(){
        List<CreateClub> clubList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            String insertQuery = "SELECT * FROM club_creation";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                CreateClub createClub = new CreateClub(
                        resultSet.getString("clubID"),
                        resultSet.getString("advisor_id"),
                        resultSet.getString("clubName"),
                        resultSet.getString("description"),
                        resultSet.getString("clubCategory"),
                        resultSet.getNString("email"),
                        resultSet.getInt("contact"),
                        resultSet.getString("image")
                );
                clubList.add(createClub);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubList;
    }
}

