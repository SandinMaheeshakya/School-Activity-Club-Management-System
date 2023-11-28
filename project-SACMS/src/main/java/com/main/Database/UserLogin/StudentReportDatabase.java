package com.main.Database.UserLogin;

import com.main.Database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentReportDatabase extends Connection {

    public static List<Map<String, String>> getStudentDetails() {

        List<Map<String, String>> allStudentsData = new ArrayList<>();

        try {
            establishConnection();
            Statement statement = connection.createStatement();

            // Execute a SQL query to retrieve all attendance data
            String query = "SELECT * from students";

            ResultSet resultSet = statement.executeQuery(query);

            // Iterate through the result set and populate the list
            while (resultSet.next()) {

                Map<String, String> studentData = new HashMap<>();
                studentData.put("student_id", resultSet.getString("student_id"));
                studentData.put("first_name", resultSet.getString("first_name"));
                studentData.put("last_name", resultSet.getString("last_name"));
                studentData.put("user_name", resultSet.getString("user_name"));
                studentData.put("dob", resultSet.getString("dob"));
                studentData.put("email", resultSet.getString("email"));
                studentData.put("grade", resultSet.getString("grade"));

                allStudentsData.add(studentData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allStudentsData;
    }
}
