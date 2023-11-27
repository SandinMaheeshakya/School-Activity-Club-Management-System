module com.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.main.EventCreation.models to javafx.base;

    opens com.main.EventCreation to javafx.fxml;
    exports com.main.EventCreation;

    opens com.main.clubcreation to javafx.fxml;
    exports com.main.clubcreation;

    opens com.main.registrationProcess to javafx.fxml;
    exports com.main.registrationProcess;

    exports com.main.Database.UserLogin;
    opens com.main.Database.UserLogin to javafx.fxml;


}