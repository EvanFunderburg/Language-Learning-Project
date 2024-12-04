package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.LanguageAppFacade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class IncorrectAnswerController {
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
