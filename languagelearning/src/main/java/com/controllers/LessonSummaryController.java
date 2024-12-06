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

public class LessonSummaryController implements Initializable{
    @FXML private Label lbl_score;
    @FXML private Label lbl_feedback;
    @FXML private Label lbl_error;
    @FXML private TextArea txt_difficultPhrases;
    private LanguageAppFacade facade;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        facade = LanguageAppFacade.getInstance();
        int score = facade.getLessonScore();
        lbl_score.setText("Score: "+ score);
        // if score is 8 or greater the user advances stages
        // if not they retry the stage and the text on the button is made to match that
        if(facade.isStruggleLesson() && score >= 0)
        {
            lbl_feedback.setText("good job practicing your struggle words!");

        }
        else if(facade.isStruggleLesson())
        {
            lbl_feedback.setText("Keep working on your words. You got this!");

        }
        else if (score >= 6) {
            lbl_feedback.setText("Well Done. You've earned passage to the next stage!");
            facade.advanceStage(); 
            // advancing to the next stage is giving errors
        }
        else {
            lbl_feedback.setText("Well shucks. You're gonna have to redo this stage :(");
        }

        txt_difficultPhrases.setText(facade.getUser().showStruggleList());
    }

    @FXML
    void exitToProfile(ActionEvent event) throws IOException {
        App.setRoot("profile");
    }

    @FXML
    void exitToLearn(ActionEvent event) throws IOException {
        App.setRoot("learn");
    }
    
}

// <Button fx:id="btn_advanceStage" text="Advance to Next Stage" onAction="#buttonToNextLesson"/>