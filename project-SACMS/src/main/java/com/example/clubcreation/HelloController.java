package com.example.clubcreation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.scene.input.MouseEvent;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
    private TextField txtClubID;

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
    private TableView<CreateClub> tblManage;

    @FXML
    private TableColumn<CreateClub, String> colAdvisorName;

    @FXML
    private TableColumn<CreateClub, String> colClubName;

    @FXML
    private TableColumn<CreateClub, String> colClubType;

    @FXML
    private TableColumn<CreateClub, Integer> colContact;

    @FXML
    private TableColumn<CreateClub, String> colDescription;

    @FXML
    private TableColumn<CreateClub, String> colEmail;

    @FXML
    private TableColumn<CreateClub, String> colProfilePicture;

    @FXML
    private TableColumn<CreateClub, String> colClubID;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtUpdateAdvisor;

    @FXML
    private TextField txtUpdateClubID;

    @FXML
    private TextField txtUpdateClubName;

    @FXML
    private TextField txtUpdateContact;

    @FXML
    private TextField txtUpdateDescription;

    @FXML
    private TextField txtUpdateEmail;

    @FXML
    private TextField txtUpdateType;

    @FXML
    void clearProfile(ActionEvent event) {
        if (event.getSource() == btnClear) {
            clearFields();
        }
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
        if (event.getSource() == btnClubProfile) {
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
    void createProfile(ActionEvent event) throws IOException {
        if (event.getSource() == btnCreate) {
            String clubID = txtClubID.getText();
            String clubName = txtClubName.getText();
            String description = txtDescription.getText();
            String clubCategory = txtCategory.getText();
            String clubAdvisor = txtAdvisor.getText();
            String email = txtEmail.getText();
            String contact = txtContact.getText();
            String image = imgView.getImage() != null ? imgView.getImage().toString() : "";

            boolean isValid = true;
            String errorMessage = "";

            if (clubName.isEmpty()) {
                isValid = false;
                txtClubName.setStyle("-fx-border-color: #a33a3a");
                errorMessage += "Please enter proper name for the club.\n";
            } else {
                if (!clubName.matches("[a-zA-Z ]+")) {
                    isValid = false;
                    txtClubName.setStyle("-fx-border-color: #a33a3a");
                    errorMessage += "Club name should be only letters.\n";
                }
            }
            if (clubID.isEmpty()) {
                isValid = false;
                txtClubID.setStyle("-fx-border-color: #a33a3a");
                errorMessage += "Please enter Club ID.\n";
            }
            if (clubAdvisor.isEmpty()) {
                isValid = false;
                txtAdvisor.setStyle("-fx-border-color: #a33a3a");
                errorMessage += "Please enter advisor name.\n";
            }
            if (clubCategory.isEmpty()) {
                isValid = false;
                txtCategory.setStyle("-fx-border-color: #a33a3a");
                errorMessage += "Please enter type of the club.\n";
            }
            if (email.isEmpty()) {
                isValid = false;
                txtEmail.setStyle("-fx-border-color: #a33a3a");
                errorMessage += "Please enter your email.\n";
            }
            if (description.isEmpty()) {
                isValid = false;
                txtDescription.setStyle("-fx-border-color: #a33a3a");
                errorMessage += "Please enter a description.\n";
            }
            int clubContact = 0;
            if (contact.isEmpty()) {
                isValid = false;
                txtContact.setStyle("-fx-border-color: #a33a3a");
                errorMessage += "Please enter a valid contact.\n";
            } else {
                try {
                    clubContact = Integer.parseInt(contact);
                    if (clubContact < 0) {
                        isValid = false;
                        txtContact.setStyle("-fx-border-color: #a33a3a");
                        errorMessage += "Please enter a valid contact.\n";
                    }
                } catch (NumberFormatException e) {
                    isValid = false;
                    txtContact.setStyle("-fx-border-color: #a33a3a");
                    errorMessage += "Please enter a valid contact.\n";
                }
            }
            if (isValid) {
                CreateClub club = new CreateClub(clubID, clubName, description, clubCategory, clubAdvisor, email, Integer.parseInt(contact), image);

                DatabaseConnection databaseHandler = new DatabaseConnection("jdbc:mysql://localhost:3306/sacms", "root", "");
                try {
                    databaseHandler.connect();
                    databaseHandler.insertClub(club);
                    databaseHandler.disconnect();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                clearFields();
                settingTheTable();
            }
            else {
                showAlert("Invalid Input", errorMessage);
            }
        }
    }

    private void clearFields() {
        txtClubID.clear();
        txtClubName.clear();
        txtDescription.clear();
        txtCategory.clear();
        txtAdvisor.clear();
        txtEmail.clear();
        txtContact.clear();
        imgView.setImage(null);

        resetStyles();
    }

    private void resetStyles() {
        txtClubID.setStyle("");
        txtClubName.setStyle("");
        txtDescription.setStyle("");
        txtCategory.setStyle("");
        txtAdvisor.setStyle("");
        txtEmail.setStyle("");
        txtContact.setStyle("");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void choosePhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(btnChooseImage.getScene().getWindow());

        if (selectedFile != null) {
            try {
                Image image = new Image(selectedFile.toURI().toString());
                imgView.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void settingTheTable() {
        List<CreateClub> clubList = DatabaseConnection.clubDetails("jdbc:mysql://localhost:3306/sacms", "root", "");
        tblManage.getItems().setAll(clubList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colClubID.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        colClubName.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colClubType.setCellValueFactory(new PropertyValueFactory<>("clubCategory"));
        colAdvisorName.setCellValueFactory(new PropertyValueFactory<>("clubAdvisor"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colProfilePicture.setCellValueFactory(new PropertyValueFactory<>("image"));

        colProfilePicture.setCellFactory(column -> new TableCell<CreateClub, String>() {
            private final ImageView imageView = new ImageView();

            {
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
            }

            @Override
            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);

                if (empty || imagePath == null || imagePath.isEmpty()) {
                    setGraphic(null);
                } else {
                    try {
                        Image image = new Image(new File(imagePath).toURI().toString());
                        imageView.setImage(image);
                        setGraphic(imageView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        settingTheTable();
    }

    @FXML
    void updateClubs(ActionEvent event) {
        CreateClub selectedClub = tblManage.getSelectionModel().getSelectedItem();

        if (selectedClub == null) {
            showAlert("No Club Selected", "Please select a club to update.");
            return;
        }

        txtUpdateClubID.setText(selectedClub.getClubID());
        txtUpdateClubName.setText(selectedClub.getClubName());
        txtUpdateDescription.setText(selectedClub.getDescription());
        txtUpdateType.setText(selectedClub.getClubCategory());
        txtUpdateAdvisor.setText(selectedClub.getClubAdvisor());
        txtUpdateEmail.setText(selectedClub.getEmail());
        txtUpdateContact.setText(String.valueOf(selectedClub.getContact()));

        btnUpdate.setOnAction(e -> saveUpdatedClub(selectedClub));
    }

    private void saveUpdatedClub(CreateClub selectedClub) {
        // Retrieve updated values from the text fields
        String updatedClubID = txtUpdateClubID.getText();
        String updatedClubName = txtUpdateClubName.getText();
        String updatedDescription = txtUpdateDescription.getText();
        String updatedType = txtUpdateType.getText();
        String updatedAdvisor = txtUpdateAdvisor.getText();
        String updatedEmail = txtUpdateEmail.getText();
        String updatedContactText = txtUpdateContact.getText();

        int updatedContact;
        try {
            updatedContact = Integer.parseInt(updatedContactText);
            if (updatedContact < 0) {
                showAlert("Invalid Input", "Please enter a valid contact.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid contact.");
            return;
        }

        selectedClub.setClubID(updatedClubID);
        selectedClub.setClubName(updatedClubName);
        selectedClub.setDescription(updatedDescription);
        selectedClub.setClubCategory(updatedType);
        selectedClub.setClubAdvisor(updatedAdvisor);
        selectedClub.setEmail(updatedEmail);
        selectedClub.setContact(updatedContact);

        DatabaseConnection databaseHandler = new DatabaseConnection("jdbc:mysql://localhost:3306/sacms", "root", "");
        try {
            databaseHandler.connect();
            databaseHandler.updateClub(selectedClub);
            databaseHandler.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        clearUpdateFields();
        settingTheTable();
        btnUpdate.setOnAction(this::updateClubs);
    }

    private void clearUpdateFields() {
        txtUpdateClubID.clear();
        txtUpdateClubName.clear();
        txtUpdateDescription.clear();
        txtUpdateType.clear();
        txtUpdateAdvisor.clear();
        txtUpdateEmail.clear();
        txtUpdateContact.clear();
    }

    @FXML
    void selectRow(MouseEvent event) {
        if (event.getClickCount() == 1) {
            updateClubs(new ActionEvent());
        }
    }

    @FXML
    void deleteClubs(ActionEvent event) {

    }
}
