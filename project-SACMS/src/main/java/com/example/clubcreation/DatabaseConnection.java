package com.example.clubcreation;
import javafx.scene.image.Image;

import java.sql.*;
import java.util.Base64;

public class DatabaseConnection {
    private Connection connection;
    private String url;
    private String userName;
    private String password;

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

    public void insertClub(CreateClub club) throws SQLException {
        String insertQuery = "INSERT INTO club_creation (clubName, description, clubCategory, advisorName, email, contact, image) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, club.getClubName());
            statement.setString(2, club.getDescription());
            statement.setString(3, club.getClubCategory());
            statement.setString(4, club.getClubAdvisor());
            statement.setString(5, club.getEmail());
            statement.setInt(6, club.getContact());

            statement.setString(7, club.getImage());

            statement.executeUpdate();

        }
    }
}

