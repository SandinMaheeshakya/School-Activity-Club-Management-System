package com.example.clubcreation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnClubCreate;

    @FXML
    private Pane pnlCreateClub;

    @FXML
    private Pane pnlWelcomePage;

    @FXML
    private Button btnHome;

    @FXML
    private TextField txtAdvisor;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtClubName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtEmail;

    @FXML
    private ImageView imgView;

    @FXML
    private Button btnChooseImage;

    @FXML
    private Button btnClubProfile;

    @FXML
    private Pane pnlClubProfiles;

    @FXML
    private HBox cardLayout;



    @FXML
    void clearProfile(ActionEvent event) {
        if (event.getSource() == btnClear){
            txtClubName.clear();
            txtDescription.clear();
            txtCategory.clear();
            txtAdvisor.clear();
            txtEmail.clear();
            txtContact.clear();
            imgView.setImage(null);
        }
    }

    @FXML
    void createProfile(ActionEvent event) {
        if(event.getSource() == btnCreate){
            String clubName = txtClubName.getText();
            String description = txtDescription.getText();
            String clubCategory = txtCategory.getText();
            String clubAdvisor = txtAdvisor.getText();
            String email = txtEmail.getText();
            String contact = txtContact.getText();
            String image = String.valueOf(imgView.getImage());

            boolean isValid = true;
            String errorMessage = "";

            if (clubName.isEmpty()){
                isValid = false;
                txtClubName.setStyle("-fx-background-color: red");
                errorMessage += "Please enter proper name for the club\n";
            } else {
                if (!clubName.matches("[a-zA-Z ]+")){
                    isValid = false;
                    txtClubName.setStyle("-fx-background-color: red");
                    errorMessage += "Club name should be only letters\n";
                }
            }
            if (clubAdvisor.isEmpty()){
                isValid = false;
                txtAdvisor.setStyle("-fx-background-color: red");
                errorMessage += "Please enter advisor name\n";
            }
            if (clubCategory.isEmpty()){
                isValid = false;
                txtCategory.setStyle("-fx-background-color: red");
                errorMessage += "Please enter type of the club\n";
            }
            int clubContact = 0;
            if(contact.isEmpty()){
                isValid = false;
                txtContact.setStyle("-fx-border-color: red");
                errorMessage += "Please enter a valid contact\n";
            } else {
                try {
                    clubContact = Integer.parseInt(contact);
                    if (clubContact < 0) {
                        isValid = false;
                        txtContact.setStyle("-fx-border-color: red");
                        errorMessage += "Please enter a valid contact\n";
                    }
                } catch (NumberFormatException e) {
                    isValid = false;
                    txtContact.setStyle("-fx-border-color: red");
                    errorMessage += "Please enter a valid contact\n";
                }
            }

            CreateClub club = new CreateClub(clubName, description, clubCategory, clubAdvisor, email, Integer.parseInt(contact), image);

            // Create an instance of DatabaseHandler and save the club data to the database
            DatabaseConnection databaseHandler = new DatabaseConnection("jdbc:mysql://localhost:3306/sacms", "root", "");
            try {
                databaseHandler.connect();
                databaseHandler.insertClub(club);
                databaseHandler.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        txtClubName.clear();
        txtDescription.clear();
        txtCategory.clear();
        txtAdvisor.clear();
        txtEmail.clear();
        txtContact.clear();
        imgView.setImage(null);


    }

    @FXML
    void goToCreateClub(ActionEvent event) {
        if (event.getSource() == btnClubCreate) {
            pnlCreateClub.setVisible(true);
            pnlWelcomePage.setVisible(false);
            pnlClubProfiles.setVisible(false);
        }
    }

    @FXML
    void goToManageClub(ActionEvent event) {
        if(event.getSource() == btnClubProfile){
            pnlClubProfiles.setVisible(true);
            pnlWelcomePage.setVisible(false);
            pnlCreateClub.setVisible(false);
        }
    }

    @FXML
    void goToHomePage(ActionEvent event) {
        if (event.getSource() == btnHome) {
            pnlWelcomePage.setVisible(true);
            pnlCreateClub.setVisible(false);
            pnlClubProfiles.setVisible(false);
        }

    }

    @FXML
    void choosePhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(btnChooseImage.getScene().getWindow());

        if (selectedFile != null) {
            try {
                // Load the selected image file into an Image object
                Image image = new Image(selectedFile.toURI().toString());
                // Set the Image object to the ImageView
                imgView.setImage(image);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

