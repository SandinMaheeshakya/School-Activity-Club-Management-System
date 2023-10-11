module com.example.clubcreation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.clubcreation to javafx.fxml;
    exports com.example.clubcreation;
}