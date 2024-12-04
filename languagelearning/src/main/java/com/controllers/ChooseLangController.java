package com.controllers;

import java.io.IOException;

import com.language.App;

import javafx.fxml.FXML;

public class ChooseLangController {
    @FXML
    private void switchToLearn() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("learn");
    }

    @FXML
    private void switchToProfile() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("profile");
    }
}