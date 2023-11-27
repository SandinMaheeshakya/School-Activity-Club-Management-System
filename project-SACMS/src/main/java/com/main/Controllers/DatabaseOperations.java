package com.main.Controllers;

import com.main.models.Event;

import java.sql.*;

public class DatabaseOperations {

    private static final String INSERT_SQL = "INSERT INTO events (EventName, EventDescription, EventType, EventDate, StartTime, EndTime, Duration, Mode, Location, MeetingLink, MeetingID, MeetingPassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void insertEvent(Event event) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdatabase", "username", "password");
            preparedStatement = connection.prepareStatement(INSERT_SQL);

            preparedStatement.setString(1, event.getEventName());
            preparedStatement.setString(2, event.getDescription());
            // Set all the prepared statement parameters here...

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Event inserted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly here
        } finally {
            // Close resources in the finally block to ensure they're closed even if an exception occurs
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle this exception too
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle this exception too
                }
            }
        }
    }
}
