package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.language.App;
import com.model.LanguageAppFacade;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class PrimaryController implements Initializable{
    @FXML private Label lbl_title;

    private LanguageAppFacade facade;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = LanguageAppFacade.getInstance();
        facade.signIn("ttomacka", "BabyGronk");
        facade.chooseLanguage("german");
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
        //App.setRoot("multiple_choice");
    }
    @FXML
    private void switchToTrueFalse() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        //App.setRoot("true_false");
    }
    @FXML
    private void switchToFillInBlank() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        //App.setRoot("fill_blank");
    }@FXML
    private void switchToMatching() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        //App.setRoot("matching");
    }@FXML
    private void switchToLearn() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("learn");
    }@FXML
    private void switchToCorrect() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("correct_answer");
    }@FXML
    private void switchToIncorrect() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("incorrect_answer");
    }@FXML
    private void switchToStorySelect() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("story_select");
    }@FXML
    private void switchToChooseLang() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("choose_lang");
    }@FXML
    private void switchToPhrasesPreview() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("phrases_preview");
    }@FXML
    private void switchToProfile() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("profile");
    }@FXML
    private void switchToPrintPage() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("print");
    }
}
