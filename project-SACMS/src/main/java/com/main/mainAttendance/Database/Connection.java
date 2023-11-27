package com.main.mainAttendance.Database;
import java.sql.*;

public class Connection {

    //Class Attributes
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sacms";
    private static final String DB_Username = "root";
    private static final String DB_Password = "";


    protected static java.sql.Connection connection = null;

    public static java.sql.Connection getConnection() {
        return connection;
    }

    public static void establishConnection() {

        try {
             connection = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public static void closeConnection(){

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
