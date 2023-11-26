package com.main.projectsacms.Database.UserLogin;

import com.main.projectsacms.CreateClub;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection extends com.main.projectsacms.Database.Connection {

    public static void insertStudent(String studentId, String firstname, String lastname, String username, String dob, String email, String grade, String password) throws SQLException {
        establishConnection();
         try  {
             String query = "INSERT INTO students (student_id, first_name, last_name, user_name, dob, email, grade, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             preparedStatement.setString(1, studentId);
             preparedStatement.setString(2, firstname);
             preparedStatement.setString(3, lastname);
             preparedStatement.setString(4, username);
             preparedStatement.setString(5, dob);
             preparedStatement.setString(6, email);
             preparedStatement.setString(7, grade);
             preparedStatement.setString(8, password);
             preparedStatement.executeUpdate();
         } catch (SQLException e) {
             e.printStackTrace();
         }
         closeConnection();
     }

     public static void InsertAdvisor(String advisorId, String firstName, String lastName, String userName, String dob, String email, String department, String password) throws SQLException{
        establishConnection();

         try {
             String query2 = "INSERT INTO club_advisors (advisor_id, first_name, last_name, user_name, dob, email, department, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
             PreparedStatement preparedStatements = connection.prepareStatement(query2);
             preparedStatements.setString(1, advisorId);
             preparedStatements.setString(2, firstName);
             preparedStatements.setString(3, lastName);
             preparedStatements.setString(4, userName);
             preparedStatements.setString(5, dob);
             preparedStatements.setString(6, email);
             preparedStatements.setString(7, department);
             preparedStatements.setString(8, password);
             preparedStatements.executeUpdate();
         } catch (SQLException e) {
             e.printStackTrace();
         }
         closeConnection();
    }

    public static boolean validateAdvisorLogin(String username, String password) throws SQLException {
        String query = "SELECT * FROM club_advisors WHERE user_name = ? AND password = ?";

        establishConnection();

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validateStudentLogin(String username, String password) {
        String query = "SELECT * FROM students WHERE user_name = ? AND password = ?";
        establishConnection();

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public static List<CreateClub> getAllClubs() {
        List<CreateClub> clubs = new ArrayList<>();

        String query = "SELECT * FROM clubs"; // Assuming your table is named 'create_club'

        establishConnection();
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                CreateClub club = new CreateClub();
                club.setClubID(resultSet.getString("club_ID"));
                club.setClubName(resultSet.getString("club_name"));
                club.setDescription(resultSet.getString("club_details"));
                club.setClubCategory(resultSet.getString("club_category"));
                club.setClubAdvisor(resultSet.getString("club_advisor"));
                club.setEmail(resultSet.getString("club_email"));
                club.setContact(Integer.parseInt(resultSet.getString("club_contact")));

                clubs.add(club);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clubs;
    }
    public static void joinClub(String studentId, String clubId) {
        establishConnection();
        try  {
            // Assuming there's a table named 'student_club' to store the association
            String query = "INSERT INTO student_club (student_id, club_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, clubId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

