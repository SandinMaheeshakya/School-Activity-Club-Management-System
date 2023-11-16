package com.example.clubcreation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class CardController {
    @FXML
    private HBox box;

    @FXML
    private Label name;

    @FXML
    private ImageView profileImage;

    private String[] colors = {"B9E5FF", "BDB2FE", "FB9AA8", "FF5856"};

    public void setData(Club club){
        Image image = new Image(getClass().getResourceAsStream(club.getProfileImage()));
        profileImage.setImage(image);

        name.setText(club.getName());

        box.setStyle("-fx-background-color: "+ Color.web(colors[(int)(Math.random()*colors.length)]));

    }
}
