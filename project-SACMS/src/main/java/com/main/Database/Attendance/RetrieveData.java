package com.main.Database.Attendance;
import com.main.Database.Connection;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RetrieveData extends Connection {

    private  static int studentsCount = 0;

    public static int getStudentsCount() {
        return studentsCount;
    }

    // Retrieving Event Data
    public static ArrayList<Map<String, String>> getEventsData() throws SQLException {
        studentsCount = 0;

        ArrayList<Map<String, String>> events = new ArrayList<>();

        try {
            //Establishing the connection
            establishConnection();

            // Creating a statement
            Statement statement = connection.createStatement();

            //SQL query
            String query = "SELECT Event_Name, Event_Description, Event_ID, Event_Date, event_type, Club_Name FROM events";

            //Results
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String eventName = resultSet.getString("Event_Name");
                String eventsDescription = resultSet.getString("Event_Description");
                String eventID = resultSet.getString("Event_ID");
                String eventDate = resultSet.getString("Event_Date");
                String clubName = resultSet.getString("Club_Name");
                String eventType = resultSet.getString("event_type");

                Map<String, String> eventDetails = new HashMap<>();
                eventDetails.put("eventID", eventID);
                eventDetails.put("eventName", eventName);
                eventDetails.put("eventsDescription", eventsDescription);
                eventDetails.put("eventDate", eventDate);
                eventDetails.put("clubName", clubName);
                eventDetails.put("eventType", eventType);

                events.add(eventDetails);

            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            // Closing the connection
            if (connection != null) {
                closeConnection();
            }
        }

        return events;
    }


    // Retrieving Student Data

    //getting the IDs of Students who Registered in the Events
    public static ArrayList<ArrayList<Map<String, String>>> getStudentsData(String eventId) {
        ArrayList<ArrayList<Map<String, String>>> students = new ArrayList<>();
        int count = 0;
        int groupCount = 0;

        try {
            establishConnection();

            String query = "SELECT students.* FROM students " +
                    "INNER JOIN student_events ON students.student_id = student_events.student_id " +
                    "WHERE student_events.event_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, eventId);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the retrieved students
            while (resultSet.next()) {
                Map<String, String> studentDetails = new HashMap<>();

                String studentId = resultSet.getString("student_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String grade = resultSet.getString("grade");

                // Get other student details as needed
                studentDetails.put("student_id", studentId);
                studentDetails.put("studentName", firstName + " " + lastName);
                studentDetails.put("grade", grade);

                studentsCount++;

                if (count == 0) {
                    students.add(new ArrayList<>());
                }

                students.get(groupCount).add(studentDetails);
                count++;

                if (count >= 6) {
                    count = 0;
                    groupCount++;
                }
            }
            closeConnection();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println(studentsCount);
        return students;
    }

    public static String retrieveAttendanceDataFromDatabase(String eventID,String studentID) {
        establishConnection();

        String attendanceStatus = null;
        try {
            String query = "SELECT Student_ID, Attendance FROM event_attendance WHERE Event_ID = ? AND Student_ID = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, eventID);
                statement.setString(2, studentID);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    HashMap<String, String> attendanceData = new HashMap<>();
                    attendanceStatus = resultSet.getString("Attendance");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            closeConnection();
        }

        return attendanceStatus;
    }



    public static InputStream retrieveReportData(String reportName, String columnName){
        InputStream inputStream = null;
        String query = "SELECT"+columnName+" FROM jasper_reports WHERE report_name '"+reportName+"'";

        try {
            establishConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                inputStream = resultSet.getBinaryStream(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return inputStream;

    }

}



