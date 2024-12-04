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

public class CorrectAnswerController {
    @FXML
    void nextQuestionButton(ActionEvent event) throws IOException {
        LanguageAppFacade facade = LanguageAppFacade.getInstance();
        System.out.println(facade.getLessonScore());
        if(facade.isLessonFinished()){
            System.out.println("Lesson is finished");
            App.setRoot("lesson_summary");
            return;
        }
        String type = facade.getCurrentQuestionType();
        App.setRoot(type);

    }

    @FXML
    void exitButton(ActionEvent event) throws IOException {
        App.setRoot("lesson_summary");
    }
}
