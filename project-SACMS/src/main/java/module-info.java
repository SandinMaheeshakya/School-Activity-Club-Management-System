module com.main.projectsacms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.main.projectsacms to javafx.fxml;
    exports com.main.projectsacms;
}