module com.main.projectsacms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.main.EventCreation.models to javafx.base;
    opens com.main.projectsacms to javafx.fxml;
    exports com.main.EventCreation;
    opens com.main.EventCreation to javafx.fxml;
}