package com.controllers;

import java.io.IOException;

import com.language.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StoryPageController {
    @FXML private Button homepage;
    @FXML
    void switchToUserHomePage(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
    
}
