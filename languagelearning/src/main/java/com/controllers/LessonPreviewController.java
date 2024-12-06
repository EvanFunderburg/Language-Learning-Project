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

public class LessonPreviewController implements Initializable {
    private LanguageAppFacade facade;


    @FXML private Label lbl_title;
    @FXML private Label lbl_error;
    @FXML private TextArea txt_lessonPreview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facade = LanguageAppFacade.getInstance();
        User user = facade.getUser();
        
        if (user != null) {
            lbl_title.setText("Welcome " + user.getFirstName() + " " + user.getLastName());
            txt_lessonPreview.setText(facade.startLesson());
        } else {
            lbl_error.setText("User data is not available.");
        }
        facade.resetLesson();
    }
     
    @FXML
    void advanceToLesson(ActionEvent event) throws IOException {
        App.setRoot(facade.getCurrentQuestionType());
    }



    
}