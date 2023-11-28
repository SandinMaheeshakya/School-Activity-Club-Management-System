package com.main.Database.EventCreation;

import com.main.Database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventCreationDatabase extends Connection {

    public static List<Map<String, String>> getEventDetails() {

        List<Map<String, String>> allClubData = new ArrayList<>();

        try {
            establishConnection();
            Statement statement = connection.createStatement();

            // Execute a SQL query to retrieve all attendance data
            String query = "SELECT EventName,EventDescription,EventType,Club_Name,Mode,Location,MeetingID from events";

            ResultSet resultSet = statement.executeQuery(query);

            // Iterate through the result set and populate the list
            while (resultSet.next()) {

                Map<String, String> eventData = new HashMap<>();
                eventData.put("EventName", resultSet.getString("EventName"));
                eventData.put("EventDescription", resultSet.getString("EventDescription"));
                eventData.put("EventType", resultSet.getString("EventType"));
                eventData.put("Club_Name", resultSet.getString("Club_Name"));
                eventData.put("Mode", resultSet.getString("Mode"));
                eventData.put("Location", resultSet.getString("Location"));
                eventData.put("MeetingID", resultSet.getString("MeetingID"));


                allClubData.add(eventData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allClubData;
    }
}
