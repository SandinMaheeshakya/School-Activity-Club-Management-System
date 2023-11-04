package com.main.projectsacms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://your_database_host:3306/sacms";
    private static final String DB_USER = "Shamal";
    private static final String DB_PASS = "Shamal";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void insertStudent(Student student) throws SQLException {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO students (studentId, firstName, lastName, userName, dob, email, grade) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getStudentId());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setString(4, student.getUserName());
            preparedStatement.setInt(5, student.getDob());
            preparedStatement.setString(6, student.getEmail());
            preparedStatement.setString(7, student.getGrade());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();

    }
}