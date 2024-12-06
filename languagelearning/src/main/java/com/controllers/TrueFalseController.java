
package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.language.App;
import com.model.LanguageAppFacade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class TrueFalseController implements Initializable {

    @FXML private Label lbl_question;
    private LanguageAppFacade facade;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        facade = LanguageAppFacade.getInstance();
        lbl_question.setText(facade.getCurrentQuestionString());
    }   
    @FXML
    void switchToUserHomePage(ActionEvent event) throws IOException {
        
        App.setRoot("lesson_summary");
    }

    @FXML
    void clickedTrueButton(ActionEvent event) throws IOException {
        

        answerQuestion("true");
        
    }

    @FXML
    void clickedFalseButton(ActionEvent event) throws IOException {
        
        answerQuestion("false");
    }

    private void answerQuestion(String answer) throws IOException{
        if (facade.answerCurrentQuestion(answer))
            App.setRoot("correct_answer");
        else
        App.setRoot("incorrect_answer");

    }
    
}
