package com.main.Controllers;

import com.main.models.Database;
import com.main.models.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventController {

    public String saveEvent(Event event) {
        String insertEventSQL = "INSERT INTO events (Event_ID, Event_Name, event_type, Club_Name, Event_Description, Event_Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertEventSQL)) {

            preparedStatement.setString(1, event.getEventName());
            preparedStatement.setString(2, event.getEventName());
            preparedStatement.setString(3, "-");
            preparedStatement.setString(4, event.getDescription());
            preparedStatement.setString(5, event.getEventDate());

            // Execute the insert SQL statement
            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                return "Success";
            } else {
                return "Failure: No rows affected.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failure: " + e.getMessage();
        }
    }
}
