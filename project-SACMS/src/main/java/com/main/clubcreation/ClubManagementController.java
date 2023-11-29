package com.main.clubcreation;

import com.main.Database.ClubCreation.ClubCreationReportDatabase;
import com.main.Database.ClubCreation.DatabaseConnection;
import com.main.EventCreation.Menu;
import com.main.registrationProcess.SACMS;
import com.main.registrationProcess.startPageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ClubManagementController implements Initializable { //controller class for club management

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
    private TableColumn<CreateClub, Image> colProfilePicture;

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
    private ImageView imgUpdateImage;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnAddEvent;

    @FXML
    private Button btnEditClubs;

    @FXML
    private Pane pnlAddEvent;

    @FXML
    private Pane pnlUpdate;

    @FXML
    private ImageView btnClose;

    @FXML
    void clearProfile(ActionEvent event) { //clear button for club creation
        if (event.getSource() == btnClear) {
            clearFields();
        }
    }

    @FXML
    void goToCreateClub(ActionEvent event) { //go to create club panel action
        if (event.getSource() == btnClubCreate) {
            pnlCreateClub.setVisible(true);
            pnlWelcomePage.setVisible(false);
            pnlClubProfiles.setVisible(false);
        }
    }

    @FXML
    void goToManageClub(ActionEvent event) { //go to manage club action
        if (event.getSource() == btnClubProfile) {
            pnlClubProfiles.setVisible(true);
            pnlWelcomePage.setVisible(false);
            pnlCreateClub.setVisible(false);
        }
    }

    @FXML
    private void handleClose(MouseEvent event) { //close button
        if (event.getSource() == btnClose)
            System.exit(0);
    }
    @FXML
    void createProfile(ActionEvent event) throws IOException { //action for create club
        if (event.getSource() == btnCreate) {
            String clubID = txtClubID.getText();
            String clubName = txtClubName.getText();
            String description = txtDescription.getText();
            String clubCategory = txtCategory.getText();
            String clubAdvisor = txtAdvisor.getText();
            String email = txtEmail.getText();
            String contact = txtContact.getText();
            Image image = imgView.getImage();
            String imagePath = "";
            if (image != null) { //error handling part for insert data
                try {
                    File imageFile = new File(image.getUrl());
                    imagePath = imageFile.toURI().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

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
            } else {
                if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                    isValid = false;
                    txtEmail.setStyle("-fx-border-color: #a33a3a");
                    errorMessage += "Please enter valid email.\n";
                }
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
                System.out.println("Image Path: " + imagePath);
                CreateClub club = new CreateClub(clubID, clubName, description, clubCategory, clubAdvisor, email, Integer.parseInt(contact), imagePath);

                DatabaseConnection databaseHandler = new DatabaseConnection("jdbc:mysql://localhost:3306/sacms", "root", "");
                try {
                    databaseHandler.connect();
                    databaseHandler.insertClub(club);
                    databaseHandler.disconnect();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                showMessage("Success", "Club data added successfully.");
                clearFields();
                settingTheTable();
            }
            else {
                showAlert("Invalid Input", errorMessage);
            }
        }
    }

    private void showAlert(String title, String message) { //show alert dialog
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showMessage(String title, String message) { //show information dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void choosePhoto(ActionEvent event) { //action for choose photo
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(btnChooseImage.getScene().getWindow());

        if (selectedFile != null) {
            try {
                Image image = new Image(selectedFile.toURI().toString(), true);
                imgView.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Error loading image. Please select a valid image file.");
            }
        }
    }

    public void settingTheTable() { //setup the table with club details
        List<CreateClub> clubList = DatabaseConnection.clubDetails("jdbc:mysql://localhost:3306/sacms", "root", "");
        ObservableList<CreateClub> observableClubList = FXCollections.observableArrayList();
        observableClubList.addAll(clubList);

        for (CreateClub club : observableClubList) {
            byte[] imageData = club.getImageData(); // Assuming there is a method getImageData() in CreateClub class
            if (imageData != null && imageData.length > 0) {
                try {
                    Image image = new Image("your_image_path.jpg");
                    club.setImage(image);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        tblManage.getItems().setAll(clubList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //Initialize method for the controller
        colClubID.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        colClubName.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colClubType.setCellValueFactory(new PropertyValueFactory<>("clubCategory"));
        colAdvisorName.setCellValueFactory(new PropertyValueFactory<>("clubAdvisor"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));


        colProfilePicture.setCellValueFactory(new PropertyValueFactory<>("image"));

        colProfilePicture.setCellFactory(column -> new TableCell<>() {
            private final ImageView imageView = new ImageView();

            {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(imageView);
            }

            @Override
            protected void updateItem(Image image, boolean empty) {
                super.updateItem(image, empty);

                if (empty || image == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(image);
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    setGraphic(imageView);
                }
            }
        });

        ObservableList<CreateClub> allClubs = FXCollections.observableArrayList();
        allClubs.addAll(DatabaseConnection.clubDetails("jdbc:mysql://localhost:3306/sacms", "root", ""));
        tblManage.getItems().setAll(allClubs);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            handleSearch(newValue);
        });
    }

        private void handleSearch(String query) { //handle search function
            ObservableList<CreateClub> filteredClubs = FXCollections.observableArrayList();

            for (CreateClub club : tblManage.getItems()) {
                if (club.getClubName().toLowerCase().contains(query.toLowerCase())) {
                    filteredClubs.add(club);
                }
            }
            tblManage.getItems().setAll(filteredClubs);
        }

    @FXML
    void updateClubs(ActionEvent event) { //action for update clubs
        CreateClub selectedClub = tblManage.getSelectionModel().getSelectedItem();

        if (selectedClub == null) {
            showAlert("No Club Selected", "Please select a club to update.");
            return;
        }

        String updatedClubID = txtUpdateClubID.getText();
        String updatedClubName = txtUpdateClubName.getText();
        String updatedDescription = txtUpdateDescription.getText();
        String updatedType = txtUpdateType.getText();
        String updatedAdvisor = txtUpdateAdvisor.getText();
        String updatedEmail = txtUpdateEmail.getText();
        String updatedContactText = txtUpdateContact.getText();
        String updatedImage = imgView.getImage() != null ? imgView.getImage().toString() : "";

        txtUpdateClubID.setText(selectedClub.getClubID());
        txtUpdateClubID.setDisable(true);
        txtUpdateClubName.setText(selectedClub.getClubName());
        txtUpdateDescription.setText(selectedClub.getDescription());
        txtUpdateType.setText(selectedClub.getClubCategory());
        txtUpdateAdvisor.setText(selectedClub.getClubAdvisor());
        txtUpdateEmail.setText(selectedClub.getEmail());
        txtUpdateContact.setText(String.valueOf(selectedClub.getContact()));

        boolean isValid = true;
        String errorMessage = "";

        if (updatedClubName.isEmpty()) {  //error handling part for update
            isValid = false;
            txtClubName.setStyle("-fx-border-color: #a33a3a");
            errorMessage += "Please enter proper name for the club.\n";
        } else {
            if (!updatedClubName.matches("[a-zA-Z ]+")) {
                isValid = false;
                txtClubName.setStyle("-fx-border-color: #a33a3a");
                errorMessage += "Club name should be only letters.\n";
            }
        }
        if (updatedClubID.isEmpty()) {
            isValid = false;
            txtClubID.setStyle("-fx-border-color: #a33a3a");
            errorMessage += "Please enter Club ID.\n";
        }
        if (updatedAdvisor.isEmpty()) {
            isValid = false;
            txtAdvisor.setStyle("-fx-border-color: #a33a3a");
            errorMessage += "Please enter advisor name.\n";
        }
        if (updatedType.isEmpty()) {
            isValid = false;
            txtCategory.setStyle("-fx-border-color: #a33a3a");
            errorMessage += "Please enter type of the club.\n";
        }
        if (updatedEmail.isEmpty()) {
            isValid = false;
            txtEmail.setStyle("-fx-border-color: #a33a3a");
            errorMessage += "Please enter your email.\n";
        } else {
            if (!updatedEmail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                isValid = false;
                txtEmail.setStyle("-fx-border-color: #a33a3a");
                errorMessage += "Please enter valid email.\n";
            }
        }
        if (updatedDescription.isEmpty()) {
            isValid = false;
            txtDescription.setStyle("-fx-border-color: #a33a3a");
            errorMessage += "Please enter a description.\n";
        }
        int clubContact = 0;
        if (updatedContactText.isEmpty()) {
            isValid = false;
            txtContact.setStyle("-fx-border-color: #a33a3a");
            errorMessage += "Please enter a valid contact.\n";
        } else {
            try {
                clubContact = Integer.parseInt(updatedContactText);
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
        if (isValid) {CreateClub club = new CreateClub(updatedClubID, updatedClubName, updatedDescription, updatedType, updatedAdvisor, updatedEmail, Integer.parseInt(updatedContactText), updatedImage);

            DatabaseConnection databaseHandler = new DatabaseConnection("jdbc:mysql://localhost:3306/sacms", "root", "");
            try {
                databaseHandler.connect();
                databaseHandler.updateClub(club);
                databaseHandler.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            btnUpdate.setOnAction(e -> saveUpdatedClub(selectedClub));
            showMessage("Success", "Club data updated successfully.");
            clearFields();
        }
        else {
            showAlert("Invalid Input", errorMessage);
        }

        settingTheTable();
        tblManage.refresh();
        updateClubs(new ActionEvent());
        clearUpdateFields();

    }

    @FXML
    void goToUpdate(ActionEvent event) { //goto update panel section
        if (event.getSource() == btnEditClubs) {
            pnlAddEvent.setVisible(false);
            pnlUpdate.setVisible(true);
        }
    }

    private void saveUpdatedClub(CreateClub selectedClub) { //save updated club details
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
    public static String currentClubId;
    public static String currentClubName;
    @FXML
    void selectRow(MouseEvent event) { //select row action
        if (event.getClickCount() == 1) {
            CreateClub selectedClub = tblManage.getSelectionModel().getSelectedItem();
            if (selectedClub != null) {
                pnlAddEvent.setVisible(true);
                pnlClubProfiles.setVisible(true);

                currentClubId = selectedClub.getClubID();
                currentClubName = selectedClub.getClubName();
            }
            updateTextFields(selectedClub);

            if (pnlUpdate.isVisible()){
                pnlAddEvent.setVisible(false);
            }
        }
    }

    private void updateTextFields(CreateClub club) { //update text filed with club details
        txtUpdateClubID.setText(club.getClubID());
        txtUpdateClubName.setText(club.getClubName());
        txtUpdateDescription.setText(club.getDescription());
        txtUpdateType.setText(club.getClubCategory());
        txtUpdateAdvisor.setText(club.getClubAdvisor());
        txtUpdateEmail.setText(club.getEmail());
        txtUpdateContact.setText(String.valueOf(club.getContact()));
    }


    @FXML
    void deleteClubs(ActionEvent event) { //delete club action
        CreateClub selectedClub = tblManage.getSelectionModel().getSelectedItem();

        if (selectedClub == null) {
            showAlert("No Club Selected", "Please select a club to delete.");
            return;
        }

        DatabaseConnection databaseHandler = new DatabaseConnection("jdbc:mysql://localhost:3306/sacms", "root", "");
        try {
            databaseHandler.connect();
            databaseHandler.deleteClub(selectedClub);
            databaseHandler.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showMessage("Success", "Club data deleted successfully.");
        clearUpdateFields();
        settingTheTable();
    }

    private void resetStyles() { //reset style for text field
        txtClubID.setStyle("");
        txtClubName.setStyle("");
        txtDescription.setStyle("");
        txtCategory.setStyle("");
        txtAdvisor.setStyle("");
        txtEmail.setStyle("");
        txtContact.setStyle("");
    }

    private void clearFields() { //clear all input details
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

    private void clearUpdateFields() { //clear all updated input details
        txtUpdateClubID.clear();
        txtUpdateClubName.clear();
        txtUpdateDescription.clear();
        txtUpdateType.clear();
        txtUpdateAdvisor.clear();
        txtUpdateEmail.clear();
        txtUpdateContact.clear();
        imgUpdateImage.setImage(null);

        resetUpdateStyles();
    }

    private void resetUpdateStyles(){ //reset style for updated text field
        txtUpdateClubID.setStyle("");
        txtUpdateClubName.setStyle("");
        txtUpdateDescription.setStyle("");
        txtUpdateType.setStyle("");
        txtUpdateAdvisor.setStyle("");
        txtUpdateEmail.setStyle("");
        txtUpdateContact.setStyle("");
    }


    //Add EventButton
    public  void onAddEventButtonClick(){ //add event button action
        Menu menu = new Menu();
        Stage stage = new Stage();
        menu.start(stage);
    }

    public void onBackButtonClickInMainPage() throws IOException { //back button action in main page
        SACMS sacms = new SACMS();
        Stage stage = new Stage();
        startPageController.backStatus = true;
        sacms.start(stage);
    }

    public void onBackButtonClickInDisplayClubs(){ //back button action in display clubs
        pnlWelcomePage.setVisible(true);
        pnlClubProfiles.setVisible(false);
    }

    public void onBackButtonInCreateClubClick(){ //back button action in create club
        pnlCreateClub.setVisible(false);
        pnlWelcomePage.setVisible(true);
    }

    public void onGenerateReportButtonClick(){ //generate report button action
        // Generate and display the report
        List<Map<String,String>> allClubData = ClubCreationReportDatabase.getClubDetails();
        ClubCreationReport generatedClubDetailsReport = new ClubCreationReport();
        generatedClubDetailsReport.generateAttendanceReport(allClubData);
    }
}
