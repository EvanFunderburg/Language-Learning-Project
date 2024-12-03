package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import com.language.App;
import com.model.*;
import com.narration.*;

public class PrimaryController implements Initializable{
    @FXML private Label lbl_title;

    private LanguageAppFacade facade;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = LanguageAppFacade.getInstance();
        facade.signIn("ttomacka", "BabyGronk");
        User user = facade.getUser();
        lbl_title.setText("Welcome " + user.getFirstName() + " " + user.getLastName());
    }   
    @FXML
    private void switchToSecondary() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("entry_page");
    }
    @FXML
    private void switchToMultipleChoice() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("multiple_choice");
    }
    @FXML
    private void switchToTrueFalse() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("true_false");
    }
    @FXML
    private void switchToFillInBlank() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("fill_blank");
    }@FXML
    private void switchToMatching() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("matching");
    }
}
