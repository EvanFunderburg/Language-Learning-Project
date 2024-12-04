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

public class LessonPreviewController  {
    @FXML private Label lbl_title;
     
    @FXML
    void advanceToLesson(ActionEvent event) throws IOException {
        LanguageAppFacade facade = LanguageAppFacade.getInstance();
        facade.resetLesson();
        System.out.println(facade.startLesson());
        App.setRoot(facade.getCurrentQuestionType());
    }



    
}