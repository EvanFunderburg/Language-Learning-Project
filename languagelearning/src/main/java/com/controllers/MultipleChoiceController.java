
package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.language.App;
import com.model.LanguageAppFacade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MultipleChoiceController implements Initializable {

    @FXML private Label lbl_question;
    @FXML private Button btn_answerA;
    @FXML private Button btn_answerB;
    @FXML private Button btn_answerC;
    @FXML private Button btn_answerD;

    private LanguageAppFacade facade;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        facade = LanguageAppFacade.getInstance();
       
        
        String[] answers = facade.getAnswerChoices().split("#");
        
        // System.out.println(facade.getAnswerChoices());
        btn_answerA.setText(answers[0]);
        btn_answerB.setText(answers[1]);
        btn_answerC.setText(answers[2]);
        btn_answerD.setText(answers[3]);
        lbl_question.setText(facade.getCurrentQuestionString());
    }   
    @FXML
    void switchToUserHomePage(ActionEvent event) throws IOException {
        

        App.setRoot("lesson_summary");
    }

    @FXML
    void clickedButtonA(ActionEvent event) throws IOException {
        
        if(facade.answerCurrentQuestion("a"))
            App.setRoot("correct_answer");
        else
            App.setRoot("incorrect_answer");
    }

    @FXML
    void clickedButtonB(ActionEvent event) throws IOException {
        
        if(facade.answerCurrentQuestion("b"))
            App.setRoot("correct_answer");
        else
            App.setRoot("incorrect_answer");
    }

    @FXML
    void clickedButtonC(ActionEvent event) throws IOException {
        
        if(facade.answerCurrentQuestion("c"))
            App.setRoot("correct_answer");
        else
            App.setRoot("incorrect_answer");
    }

    @FXML
    void clickedButtonD(ActionEvent event) throws IOException {
        
        if(facade.answerCurrentQuestion("d"))
        App.setRoot("correct_answer");
    else
        App.setRoot("incorrect_answer");
    }
    
}
