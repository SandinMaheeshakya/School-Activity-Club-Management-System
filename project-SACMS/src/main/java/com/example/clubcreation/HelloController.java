package com.example.clubcreation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;

public class HelloController {

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
    void clearProfile(ActionEvent event) {

    }

    @FXML
    void createProfile(ActionEvent event) {
        if(event.getSource() == btnCreate){
            String clubName = txtClubName.getText();
            String description = txtDescription.getText();
            String clubCategory = txtCategory.getText();
            String clubAdvisor = txtAdvisor.getText();
            String email = txtEmail.getText();
            int contact = Integer.parseInt(txtContact.getText());
            String image = String.valueOf(imgView.getImage());

            CreateClub club = new CreateClub(clubName, description, clubCategory, clubAdvisor, email, contact, image);

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
            pnlCreateClub.setOpacity(1);
            pnlWelcomePage.setOpacity(0);

        }

    }

    @FXML
    void goToManageClub(ActionEvent event) {

    }

    @FXML
    void goToHomePage(ActionEvent event) {
        if (event.getSource() == btnHome) {
            pnlWelcomePage.setOpacity(1);
            pnlCreateClub.setOpacity(0);
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


}

