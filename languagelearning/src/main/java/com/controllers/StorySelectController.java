package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.LanguageAppFacade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StorySelectController {
    @FXML
    void switchToUserHomePage() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    void switchToStoryPage() throws IOException {
        App.setRoot("primary");
    }
}
