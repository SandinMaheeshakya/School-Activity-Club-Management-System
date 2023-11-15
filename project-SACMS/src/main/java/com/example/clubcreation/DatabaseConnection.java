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

            String base64Image = imageToBase64(club.getImage());
            statement.setString(7, base64Image);

            statement.executeUpdate();

        }
    }

    private String imageToBase64(Image image) {
        // Convert Image to byte array
        // Assuming you have a method to convert JavaFX Image to byte array
        byte[] imageBytes = imageToByteArray(image);

        // Encode byte array to Base64 string
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    private byte[] imageToByteArray(Image image) {
        // Implement this method to convert JavaFX Image to byte array
        // Example: Convert the Image to BufferedImage, then extract the byte array
        // Refer to JavaFX documentation or other sources for appropriate conversion
        // This example assumes BufferedImage conversion for simplicity
        // BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        // ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        // return byteArrayOutputStream.toByteArray();
        return new byte[0]; // Placeholder, replace with actual implementation
    }
}

