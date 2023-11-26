module com.main.projectsacms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.main.projectsacms to javafx.fxml;
    exports com.main.projectsacms;
    exports com.main.projectsacms.Database.UserLogin;
    opens com.main.projectsacms.Database.UserLogin to javafx.fxml;
}