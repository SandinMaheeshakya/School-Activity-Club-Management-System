package com.example.clubcreation;

import javafx.scene.image.Image;

public class CreateClub {
    String clubName;
    String description;
    String clubCategory;
    String clubAdvisor;
    String email;
    int contact;

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

    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public CreateClub(String clubName, String description, String clubCategory, String clubAdvisor, String email, int contact, Image image){
        this.clubName = clubName;
        this.clubCategory = clubAdvisor;
        this.description = description;
        this.clubAdvisor = clubAdvisor;
        this.email = email;
        this.contact = contact;
        this.image = image;
    }

}
