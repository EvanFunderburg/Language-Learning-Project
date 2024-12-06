package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.LanguageAppFacade;
import com.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StorySelectController {
    private LanguageAppFacade facade;
    @FXML private Button btn_red_riding;
    @FXML private Button btn_goldi;
    @FXML private Button btn_3pigs;
    @FXML private Button btn_gnmoon;
    @FXML private Button homepage;
    @FXML
    void switchToUserHomePage(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
    @FXML
    void clickedRedRidingStory(ActionEvent event) throws IOException {
        facade =LanguageAppFacade.getInstance();
        facade.chooseStory(1);
        App.setRoot("storypage");
        
    }
    @FXML
    void clickedgoldiStory(ActionEvent event) throws IOException {
        facade =LanguageAppFacade.getInstance();
        facade.chooseStory(2);
        App.setRoot("storypage");
    }
    @FXML
    void clickedmoonStory(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
    @FXML
    void clickedpigsStory(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
}
