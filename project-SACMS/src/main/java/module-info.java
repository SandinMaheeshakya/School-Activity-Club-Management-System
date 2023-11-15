module com.example.clubcreation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.clubcreation to javafx.fxml;
    exports com.example.clubcreation;
}