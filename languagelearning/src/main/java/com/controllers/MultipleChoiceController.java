
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

public class MultipleChoiceController implements Initializable {

    @FXML private Label lbl_title;
    @FXML private Label lbl_type;
    @FXML private Label lbl_question;
    @FXML private Button btn_answerA;
    @FXML private Button btn_answerB;
    @FXML private Button btn_answerC;
    @FXML private Button btn_answerD;
    @FXML private Label lbl_feedback;

    private LanguageAppFacade facade;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        facade = LanguageAppFacade.getInstance();
        facade.signIn("ttomacka", "BabyGronk");
        User user = facade.getUser();
        facade.chooseLanguage("german");
        facade.startLesson();
        while(!facade.isLessonFinished() && !facade.getCurrentQuestionType().equals("multiple_choice")) {
            facade.answerCurrentQuestion("null");
        }
        
        String[] answers = facade.getAnswerChoices().split("#");
        
        // System.out.println(facade.getAnswerChoices());
        btn_answerA.setText(answers[0]);
        btn_answerB.setText(answers[1]);
        btn_answerC.setText(answers[2]);
        btn_answerD.setText(answers[3]);
        lbl_title.setText("Welcome " + user.getFirstName() + " " + user.getLastName());
        lbl_question.setText(facade.getCurrentQuestionString());
    }   
    @FXML
    void switchToUserHomePage(ActionEvent event) throws IOException {
        

        App.setRoot("primary");
    }

    @FXML
    void clickedButtonA(ActionEvent event) throws IOException {
        
        lbl_feedback.setText("Clicked Button A");
        answerQuestion("a");
    }

    @FXML
    void clickedButtonB(ActionEvent event) throws IOException {
        
        lbl_feedback.setText("Clicked Button B");
        answerQuestion("b");
    }

    @FXML
    void clickedButtonC(ActionEvent event) throws IOException {
        
        lbl_feedback.setText("Clicked Button C");
        answerQuestion("c");
    }

    @FXML
    void clickedButtonD(ActionEvent event) throws IOException {
        
        lbl_feedback.setText("Clicked Button D");
        answerQuestion("d");
    }

    private void answerQuestion(String ans) {
        if (facade.answerCurrentQuestion(ans)) {
            lbl_feedback.setText("Correct! Nice job");
        }
        else
            lbl_feedback.setText("Incorrect :(");
    }

    
}
