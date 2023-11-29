package com.main.registrationProcess;

import javafx.scene.control.TextField;
import org.junit.Test;
import static org.junit.Assert.*;

public class StartPageControllerTest {



    //Validation Test Cases
    @Test
    public void testAdvisorFirstNameValidation_EmptyInput() {
        startPageController controller = new startPageController();
        controller.getAdvisorFirstName().setText("hello");

        TextField textField = new TextField();

        controller.advisorFirstNameValidation();
        assertEquals("hello",controller.getAdvisorFirstName().getText());
    }

    @Test
    public void testAdvisorFirstNameValidation_ValidInput() {
        startPageController controller = new startPageController();
        controller.getAdvisorFirstName().setText("John");

        controller.advisorFirstNameValidation();

        assertEquals("", controller.getAdvisorFirstName().getStyle());
    }

    @Test
    public void testAdvisorEmailValidation_EmptyInput() {
        startPageController controller = new startPageController();
        controller.getAdvisorEmail().setText("");

        controller.advisorEmailValidation();

        assertTrue(controller.getAdvisorEmail().getStyle().contains("-fx-border-color: red"));
    }

    @Test
    public void testAdvisorEmailValidation_InvalidInput() {
        startPageController controller = new startPageController();
        controller.getAdvisorEmail().setText("invalid_email");

        controller.advisorEmailValidation();

        assertTrue(controller.getAdvisorEmail().getStyle().contains("-fx-border-color: red"));
    }


    @Test
    public void testAdvisorTeachingIDValidation_EmptyInput() {
        startPageController controller = new startPageController();
        controller.getAdvisorTeachingID().setText("");

        controller.advisorTeachingIDValidation();

        assertTrue(controller.getAdvisorTeachingID().getStyle().contains("-fx-border-color: red"));
    }

    @Test
    public void testAdvisorTeachingIDValidation_InvalidInput() {
        startPageController controller = new startPageController();
        controller.getAdvisorTeachingID().setText("InvalidID");

        controller.advisorTeachingIDValidation();

        assertTrue(controller.getAdvisorTeachingID().getStyle().contains("-fx-border-color: red"));
    }




}
