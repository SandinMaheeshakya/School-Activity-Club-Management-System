package com.main.Database.UserLogin;

import com.main.Database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvisorReportDatabase extends Connection {

    public static List<Map<String, String>> getAdvisorDetails() {

        List<Map<String, String>> allAdvisorData = new ArrayList<>();

        try {
            establishConnection();
            Statement statement = connection.createStatement();

            // Execute a SQL query to retrieve all attendance data
            String query = "SELECT * from club_advisors";

            ResultSet resultSet = statement.executeQuery(query);

            // Iterate through the result set and populate the list
            while (resultSet.next()) {

                Map<String, String> advisorData = new HashMap<>();
                advisorData.put("advisor_id", resultSet.getString("advisor_id"));
                advisorData.put("first_name", resultSet.getString("first_name"));
                advisorData.put("last_name", resultSet.getString("last_name"));
                advisorData.put("user_name", resultSet.getString("user_name"));
                advisorData.put("dob", resultSet.getString("dob"));
                advisorData.put("email", resultSet.getString("email"));
                advisorData.put("department", resultSet.getString("department"));

                allAdvisorData.add(advisorData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allAdvisorData;
    }
}
