
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

public class MatchingController implements Initializable {

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
        while(!facade.isLessonFinished() && !facade.getCurrentQuestionType().equals("matching")) {
            facade.answerCurrentQuestion("null");
        }
        
        
        lbl_title.setText("Welcome " + user.getFirstName() + " " + user.getLastName());
        lbl_question.setText(facade.getCurrentQuestionString());
        String answers = facade.getAnswerChoices();
        String[] temp = answers.split("!");
        System.out.println(answers);
        for(String s : temp)
            System.out.println(s);
        String[] baseSet = temp[0].split("#");
        String[] learningSet = temp[1].split("#");
        System.out.println("Base set");
        for(String s : baseSet)
            System.out.println(s);
        System.out.println("Learning set");
        for(String s : learningSet)
            System.out.println(s);
        lbl_feedback.setText(temp[0] +"\n"+temp[1]);
    }   
    @FXML
    void switchToUserHomePage(ActionEvent event) throws IOException {
        

        App.setRoot("primary");
    }
    
}
