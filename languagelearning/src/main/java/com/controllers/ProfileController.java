package com.controllers;

import java.io.IOException;

import com.language.App;

import javafx.fxml.FXML;

public class ProfileController {
    @FXML
    private void switchToLearn() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("learn");
    }

    @FXML
    private void logout() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("entry_page");
    }

    @FXML
    private void switchToPrint() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("print");
    }
}