package com.main.EventCreation.Controllers;

import com.main.EventCreation.models.DatabaseConnector;
import com.main.EventCreation.models.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

    public class EventDataInserter {
        private static final String INSERT_SQL = "INSERT INTO events \n" +
                "(EventName, EventDescription, EventType,Club_Name, EventDate, StartTime, EndTime, Duration, Mode, Location, MeetingLink, MeetingID, MeetingPassword) \n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        public static void insertEvent(Event event) {
            try (Connection conn = DatabaseConnector.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {

                pstmt.setString(1, event.getEventName());
                pstmt.setString(2, event.getDescription());
                pstmt.setString(3, event.getEventType()); // Assuming eventType is a String
                pstmt.setString(4, event.getEventDate()); // Assuming eventDate is a String in "YYYY-MM-DD" format
                pstmt.setString(5, event.getStartTime()); // Assuming startTime is a String
                pstmt.setString(6, event.getEndTime()); // Assuming endTime is a String
                pstmt.setString(7, event.getDuration()); // Assuming duration is an int representing minutes
                pstmt.setString(8, event.getMode()); // Assuming mode is a String
                pstmt.setString(9, event.getPhysicalLocation()); // Assuming location is a String
                pstmt.setString(10, event.getOnlineMeetingID()); // Assuming meetingLink is a String
                pstmt.setString(11, event.getOnlineMeetingLink()); // Assuming meetingID is a String
                pstmt.setString(12, event.getOnlineMeetingPassword()); // Assuming meetingPassword is a String
                // Continue setting placeholders for all fields

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Insert successful!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


