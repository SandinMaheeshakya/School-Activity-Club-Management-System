package com.main.Database.Attendance;

import com.main.Database.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceReportDatabase extends Connection {

    public static List<Map<String, String>> getAttendanceData() {
        List<Map<String, String>> allAttendanceData = new ArrayList<>();

        try {
            establishConnection();
            Statement statement = connection.createStatement();

            // Execute a SQL query to retrieve all attendance data
            String query = "SELECT ea.event_id, s.student_id, s.first_name, s.last_name, ea.attendance\n" +
                    "FROM students s\n" +
                    "INNER JOIN event_attendance ea ON s.student_id = ea.student_id";
            ResultSet resultSet = statement.executeQuery(query);

            // Iterate through the result set and populate the list
            while (resultSet.next()) {

                Map<String, String> attendanceData = new HashMap<>();
                attendanceData.put("eventId", resultSet.getString("event_id"));
                attendanceData.put("student_id", resultSet.getString("student_id"));
                attendanceData.put("first_name", resultSet.getString("first_name"));
                attendanceData.put("last_name", resultSet.getString("last_name"));
                attendanceData.put("attendance", resultSet.getString("attendance"));

                allAttendanceData.add(attendanceData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allAttendanceData;
    }
}
