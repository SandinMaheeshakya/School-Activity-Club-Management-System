package com.main.clubcreation;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;

public class CreateClub {
    private String clubID;
    private String clubName;
    private String description;
    private String clubCategory;
    private String clubAdvisor;
    private String email;
    private int contact;
    private ObjectProperty<Image> image = new SimpleObjectProperty<>();
    private byte[] imageData;

    public CreateClub(String clubID, String clubName, String description, String clubCategory, String clubAdvisor, String email, int contact, String imagePath){
        this.clubID = clubID;
        this.clubName = clubName;
        this.clubCategory = clubCategory;
        this.description = description;
        this.clubAdvisor = clubAdvisor;
        this.email = email;
        this.contact = contact;

        if (imagePath != null && !imagePath.isEmpty()) {
            this.image = new SimpleObjectProperty<>(new Image(imagePath));
        } else {
            this.image = new SimpleObjectProperty<>(null);
        }

    }

    public String getClubID() {
        return clubID;
    }

    public void setClubID(String clubID) {
        this.clubID = clubID;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClubCategory() {
        return clubCategory;
    }

    public void setClubCategory(String clubCategory) {
        this.clubCategory = clubCategory;
    }

    public String getClubAdvisor() {
        return clubAdvisor;
    }

    public void setClubAdvisor(String clubAdvisor) {
        this.clubAdvisor = clubAdvisor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }
    public ObjectProperty<Image> imageProperty() {
        return image;
    }

    public Image getImage() {return image.get();}

    public void setImage(Image image) {
        this.image.set(image);
    }


    public byte[] getImageData() {
        return imageData;
    }

}
