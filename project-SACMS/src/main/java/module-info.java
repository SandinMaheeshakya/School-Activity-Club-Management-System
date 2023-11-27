module com.main.projectsacms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.main.projectsacms.EventCreation.models to javafx.base;
    opens com.main.projectsacms to javafx.fxml;
    exports com.main.projectsacms.EventCreation;
    opens com.main.projectsacms.EventCreation to javafx.fxml;
}