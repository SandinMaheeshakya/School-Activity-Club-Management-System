package com.main.EventCreation.Controllers;

import com.main.EventCreation.models.DatabaseConnector;
import com.main.EventCreation.models.Event;
import com.main.clubcreation.ClubManagementController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventController {

    public Boolean saveEvent(Event event) {
        String insertEventSQL = "INSERT INTO events (" +
                "EventName,EventDescription,EventType,Club_Name,EventDate,StartTime,EndTime,Duration,Mode,Location,MeetingLink,MeetingID,MeetingPassword" +
                ") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertEventSQL)) {

            preparedStatement.setString(1, event.getEventName());
            preparedStatement.setString(2, event.getDescription());
            preparedStatement.setString(3, event.getEventType());
            preparedStatement.setString(4, ClubManagementController.currentClubName);
            preparedStatement.setString(5, event.getEventDate());
            preparedStatement.setString(6, event.getStartTime());
            preparedStatement.setString(7, event.getEndTime());
            preparedStatement.setString(8, event.getDuration());
            preparedStatement.setString(9, event.getMode());
            preparedStatement.setString(10, event.getPhysicalLocation());
            preparedStatement.setString(11, event.getOnlineMeetingLink());
            preparedStatement.setString(12, event.getOnlineMeetingID());
            preparedStatement.setString(13, event.getOnlineMeetingPassword());

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Boolean updateEvent(Event event) {
        StringBuilder updateSQL = new StringBuilder("UPDATE events SET ");
        ArrayList<Object> parameters = new ArrayList<>();

        if (event.getEventName() != null) {
            updateSQL.append("EventName = ?, ");
            parameters.add(event.getEventName());
        }
        if (event.getDescription() != null) {
            updateSQL.append("EventDescription = ?, ");
            parameters.add(event.getDescription());
        }
        if (event.getEventType() != null) {
            updateSQL.append("EventType = ?, ");
            parameters.add(event.getEventType());
        }
        if (event.getEventDate() != null) {
            updateSQL.append("EventDate = ?, ");
            parameters.add(event.getEventDate());
        }
        if (event.getStartTime() != null) {
            updateSQL.append("StartTime = ?, ");
            parameters.add(event.getStartTime());
        }
        if (event.getDuration() != null) {
            updateSQL.append("Duration = ?, ");
            parameters.add(event.getDuration());
        }
        if (event.getEndTime() != null) {
            updateSQL.append("EndTime = ?, ");
            parameters.add(event.getEndTime());
        }
        if (event.getMode() != null) {
            updateSQL.append("Mode = ?, ");
            parameters.add(event.getMode());
        }
        if (event.getPhysicalLocation() != null) {
            updateSQL.append("Location = ?, ");
            parameters.add(event.getPhysicalLocation());
        }
        if (event.getOnlineMeetingLink() != null) {
            updateSQL.append("MeetingLink = ?, ");
            parameters.add(event.getOnlineMeetingLink());
        }
        if (event.getOnlineMeetingID() != null) {
            updateSQL.append("MeetingID = ?, ");
            parameters.add(event.getOnlineMeetingID());
        }
        if (event.getOnlineMeetingPassword() != null) {
            updateSQL.append("MeetingPassword = ?, ");
            parameters.add(event.getOnlineMeetingPassword());
        }


        if (!parameters.isEmpty()) {
            updateSQL = new StringBuilder(updateSQL.substring(0, updateSQL.length() - 2));
        } else {
            return false;
        }

        updateSQL.append(" WHERE EventID = ?");
        parameters.add(event.getEventID());

        // Execute the update
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            int result = preparedStatement.executeUpdate();

            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Event> getAllEvents() {
        List<Event> eventList = new ArrayList<>();
        String selectEventsSQL = "SELECT * FROM events";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectEventsSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Event event = new Event(
                        String.valueOf(resultSet.getInt("EventID")),
                        resultSet.getString("EventName"),
                        resultSet.getString("EventDescription"),
                        resultSet.getString("EventType"),
                        resultSet.getString("EventDate"),
                        resultSet.getString("StartTime"),
                        resultSet.getString("EndTime"),
                        resultSet.getString("Duration"),
                        resultSet.getString("Mode"),
                        resultSet.getString("Location"),
                        resultSet.getString("MeetingLink"),
                        resultSet.getString("MeetingID"),
                        resultSet.getString("MeetingPassword")
                );
                eventList.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventList;
    }
    public List<Event> searchEvents(String search) {
        List<Event> eventList = new ArrayList<>();
        String selectEventsSQL = "SELECT * FROM events WHERE EventName LIKE ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectEventsSQL)) {

            // Set the search string with wildcards for the LIKE query
            preparedStatement.setString(1, "%" + search + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Event event = new Event(
                        String.valueOf(resultSet.getInt("EventID")),
                        resultSet.getString("EventName"),
                        resultSet.getString("EventDescription"),
                        resultSet.getString("EventType"),
                        resultSet.getString("EventDate"),
                        resultSet.getString("StartTime"),
                        resultSet.getString("EndTime"),
                        resultSet.getString("Duration"),
                        resultSet.getString("Mode"),
                        resultSet.getString("Location"),
                        resultSet.getString("MeetingLink"),
                        resultSet.getString("MeetingID"),
                        resultSet.getString("MeetingPassword")
                );
                eventList.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventList;
    }
    public Boolean deleteEvent(String id) {
        String insertEventSQL = "DELETE FROM events where EventID = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertEventSQL)) {

            preparedStatement.setString(1, id);

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
