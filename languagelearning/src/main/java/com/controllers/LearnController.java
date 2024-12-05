package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Locale.LanguageRange;
import java.util.ResourceBundle;

import com.language.App;
import com.model.LanguageAppFacade;
import com.model.User;
import com.narration.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class LearnController  {

     
    @FXML
    void switchToLearnPage(ActionEvent event) throws IOException {
        
        App.setRoot("lesson_preview");
    }

    @FXML
    void switchToStoryPage(ActionEvent event) throws IOException {
        
        App.setRoot("story_select");
    }

    @FXML
    void switchToStrugglePage(ActionEvent event) throws IOException {
        
       App.setRoot("struggle_lesson_preview");
    }

    @FXML
    void switchToProfile(ActionEvent event) throws IOException {
        
        App.setRoot("profile");
    }


    
}