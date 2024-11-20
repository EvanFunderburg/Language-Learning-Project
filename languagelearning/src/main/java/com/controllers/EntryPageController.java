package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;

import com.language.App;
import com.narration.*;

public class EntryPageController {

    @FXML
    private void switchToLogIn() throws IOException {
        // Narriator.playSound("Log In");
        App.setRoot("log_in");
    }

    @FXML
    private void switchToSignUp() throws IOException {
        // Narriator.playSound("Sign Up");
        App.setRoot("sign_up");
    }
}

