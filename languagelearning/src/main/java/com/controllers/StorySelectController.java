package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.LanguageAppFacade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StorySelectController {
    @FXML private Button btn_red_riding;
    @FXML
    void switchToUserHomePage(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
    @FXML
    void switchToStoryPage(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    void clickedRedRidingStory(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }


}
