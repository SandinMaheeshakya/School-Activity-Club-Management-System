module com.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;
    requires jasperreports;


    opens com.main.EventCreation.models to javafx.base;

    opens com.main.EventCreation to javafx.fxml;
    exports com.main.EventCreation;

    opens com.main.clubcreation to javafx.fxml;
    exports com.main.clubcreation;

    opens com.main.registrationProcess to javafx.fxml;
    exports com.main.registrationProcess;

    exports com.main.Database.UserLogin;
    opens com.main.Database.UserLogin to javafx.fxml;

    opens com.main.mainAttendance to javafx.fxml;
    exports com.main.mainAttendance;

    exports com.main.mainAttendance.AttendanceTracking;
    opens com.main.mainAttendance.AttendanceTracking to javafx.fxml;
    exports com.main.Database.ClubCreation;
    opens com.main.Database.ClubCreation to javafx.fxml;

}