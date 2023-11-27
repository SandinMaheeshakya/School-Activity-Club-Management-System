module com.main.projectsacms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.main.projectsacms to javafx.fxml;
    opens com.main.models to javafx.base;

    exports com.main;
}