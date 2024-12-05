package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.LanguageAppFacade;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChooseLangController {
    @FXML Label lbl_error;
    @FXML
    private void switchToLearn() throws IOException {
        App.setRoot("learn");
    }

    @FXML
    private void switchToProfile() throws IOException {
        App.setRoot("profile");
    }

    @FXML
    private void chooseGerman() throws IOException {
        LanguageAppFacade.getInstance().chooseLanguage("german");
        App.setRoot("profile");
    }

    @FXML
    private void chooseSpanish() throws IOException {
        lbl_error.setText("That language is currently unavailable");
    }

    @FXML
    private void chooseFrench() throws IOException {
        lbl_error.setText("That language is currently unavailable");
    }
}