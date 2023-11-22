package com.main.projectsacms.Database.Attendance;
import com.main.projectsacms.Database.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class InputData extends Connection {

    //Input Attendance
    public static void insertAttendanceDataToDatabase(ArrayList<HashMap<String, String>> attendanceData) throws SQLException {
        establishConnection();

        try {
            String sql = "INSERT INTO event_attendance (Event_ID, Student_ID, Attendance) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                for (HashMap<String, String> attendance : attendanceData) {
                    statement.setString(1, attendance.get("eventID"));
                    statement.setString(2, attendance.get("studentID"));
                    statement.setString(3, attendance.get("attendance"));
                    statement.addBatch();
                }
                statement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

    }


}
