
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

public class TrueFalseController implements Initializable {

    @FXML private Label lbl_title;
    @FXML private Label lbl_type;
    @FXML private Label lbl_question;
    @FXML private Label lbl_feedback;
    private LanguageAppFacade facade;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        facade = LanguageAppFacade.getInstance();
        facade.signIn("ttomacka", "BabyGronk");
        User user = facade.getUser();
        facade.chooseLanguage("german");
        facade.startLesson();
        while(!facade.isLessonFinished() && !facade.getCurrentQuestionType().equals("true_false")) {
            facade.answerCurrentQuestion("null");
        }
        
        lbl_title.setText("Welcome " + user.getFirstName() + " " + user.getLastName());
        lbl_question.setText(facade.getCurrentQuestionString());
    }   
    @FXML
    void switchToUserHomePage(ActionEvent event) throws IOException {
        
        App.setRoot("primary");
    }

    @FXML
    void clickedTrueButton(ActionEvent event) throws IOException {
        

        answerQuestion("true");
        
    }

    @FXML
    void clickedFalseButton(ActionEvent event) throws IOException {
        
        answerQuestion("false");
    }

    private void answerQuestion(String answer) {
        if (facade.answerCurrentQuestion(answer))
            lbl_feedback.setText("Correct! Nice job");
        else
            lbl_feedback.setText("Incorrect :(");

        facade.resetLesson();
    }
    
}
