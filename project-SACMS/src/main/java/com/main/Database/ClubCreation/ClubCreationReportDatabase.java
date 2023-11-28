package com.main.Database.ClubCreation;

import com.main.Database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClubCreationReportDatabase extends Connection {

    public static List<Map<String, String>> getClubDetails() {

        List<Map<String, String>> allClubData = new ArrayList<>();

        try {
            establishConnection();
            Statement statement = connection.createStatement();

            // Execute a SQL query to retrieve all attendance data
            String query = "SELECT cc.clubID, cc.advisor_id, ca.user_name AS advisor_name, cc.clubName, cc.description, cc.clubCategory, cc.email\n" +
                    "FROM club_creation AS cc\n" +
                    "JOIN club_advisors AS ca ON cc.advisor_id = ca.advisor_id;\n";

            ResultSet resultSet = statement.executeQuery(query);

            // Iterate through the result set and populate the list
            while (resultSet.next()) {

                Map<String, String> clubData = new HashMap<>();
                clubData.put("clubID", resultSet.getString("clubID"));
                clubData.put("advisor_id", resultSet.getString("advisor_id"));
                clubData.put("advisor_name", resultSet.getString("advisor_name"));
                clubData.put("clubName", resultSet.getString("clubName"));
                clubData.put("description", resultSet.getString("description"));
                clubData.put("clubCategory", resultSet.getString("clubCategory"));
                clubData.put("email", resultSet.getString("email"));


                allClubData.add(clubData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allClubData;
    }
}
