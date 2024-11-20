package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;

import com.language.App;
import com.narration.*;

public class EntryPageController {

    @FXML
    private void switchToLogIn() throws IOException {
        Narriator.playSound("Log In");
        //App.setRoot("secondary");
    }

    @FXML
    private void switchToSignUp() throws IOException {
        Narriator.playSound("Sign Up");
        //App.setRoot("secondary");
    }
}

