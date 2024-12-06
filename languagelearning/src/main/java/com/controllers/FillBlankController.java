
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
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

public class FillBlankController implements Initializable {

    @FXML private Label lbl_question;
    @FXML private TextArea txt_answer;

    private boolean clearText = true;

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
    void submitAnswers(ActionEvent event) throws IOException {
        String answer = txt_answer.getText();
        if (facade.answerCurrentQuestion(answer.toLowerCase()))
            App.setRoot("correct_answer");
        else
        App.setRoot("incorrect_answer");
    }
    
    @FXML
    void clearTextField(KeyEvent event) throws IOException {
        if(clearText) {
            txt_answer.setText("");
            clearText = false;
        }
    }
    
}
