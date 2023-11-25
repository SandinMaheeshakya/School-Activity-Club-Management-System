package com.main.projectsacms;

import java.sql.*;
import java.sql.Connection;

public class DatabaseConnection {

     private static final String DB_URL = "jdbc:mysql://localhost:3306/sacms";
     private static final String DB_USER = "root";
     private static final String DB_PASS = "";

     private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Connected to the database successfully");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }
    public static Connection getConnection() {
        return connection;
    }


    public static void insertStudent(String studentId, String firstname, String lastname, String username, String dob, String email, String grade, String password) throws SQLException {
         try (Connection connection = getConnection()) {
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
         connection.close();
     }

     public static void InsertAdvisor(String advisorId, String firstName, String lastName, String userName, String dob, String email, String department, String password) throws SQLException{
         try (Connection connection = getConnection()){
             String query2 = "INSERT INTO club_advisors (advisor_id, first_name, last_name, user_name, dob, email, department, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
             PreparedStatement preparedStatement = connection.prepareStatement(query2);
             preparedStatement.setString(1, advisorId);
             preparedStatement.setString(2, firstName);
             preparedStatement.setString(3, lastName);
             preparedStatement.setString(4, userName);
             preparedStatement.setString(5, dob);
             preparedStatement.setString(6, email);
             preparedStatement.setString(7, department);
             preparedStatement.setString(8, password);
             preparedStatement.executeUpdate();
         } catch (SQLException e) {
             e.printStackTrace();
         }
         connection.close();
     }

    public static boolean validateAdvisorLogin(String username, String password) {
        String query = "SELECT * FROM club_advisors WHERE user_name = ? AND password = ?";

        try (Connection connection = getConnection();
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

        try (Connection connection = getConnection();
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

     /*public static void main(String[] args) throws SQLException {
         DatabaseConnection.insertStudent("baba","ff", "dd", "dd", "dd", "ddedd", "ee", "dddd");
         DatabaseConnection.InsertAdvisor("baba","ff", "dd", "dd", "dd", "ddedd", "ee", "dddd");
     }*/
}
