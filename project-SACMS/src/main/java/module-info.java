module com.main.mainAttendance {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.main.mainAttendance to javafx.fxml;
    exports com.main.mainAttendance;

    exports com.main.mainAttendance.AttendanceTracking;
    opens com.main.mainAttendance.AttendanceTracking to javafx.fxml;
}