module com.example.clubcreation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.main.clubcreation to javafx.fxml;
    exports com.main.clubcreation;
}