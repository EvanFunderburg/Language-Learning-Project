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
import javafx.scene.text.Text;

public class ProfileController implements Initializable {

    private LanguageAppFacade facade;
    @FXML private Text lbl_name;
    @FXML private Text txt_userdata;
 

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        facade = LanguageAppFacade.getInstance();
        User user = facade.getUser();
        lbl_name.setText(user.getFirstName()+ " " + user.getLastName());
        String lang = "N/A";
        String stage = "N/A";
        if(user.getCurrentLanguageTrack() != null) {
            lang = user.getCurrentLanguageTrack().getLearningLanguage().label;
            stage = ""+user.getCurrentLanguageTrack().getCurrentStageLevel();
        }
        txt_userdata.setText(user.getUsername()+ "             Questions Correct: "+user.getQuestionsCorrect()+
        "                        Questions Wrong: "+user.getQuestionsWrong()+
        "                        Accuracy:                    "+user.calculateAccuracy()+
        "                        Current Language Track: "+lang+
        "                        Current Stage: "+stage);
    }

    @FXML
    private void switchToLearn() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("learn");
    }

    @FXML
    private void logout() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        facade.logout();
        App.setRoot("entry_page");
    }

    @FXML
    private void switchToPrint() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("print");
    }

    
}


/*
 *  <Label fx:id="lbl_username" layoutX="24.0" layoutY="24.0" text="Username: "/>
      <Label fx:id="lbl_quesCorr" layoutX="24.0" layoutY="24.0" text="Questions Correct: "/>
      <Label fx:id="lbl_quesIncorr" layoutX="24.0" layoutY="24.0" text="Questions Incorrect: "/>
      <Label fx:id="lbl_accuracy" layoutX="24.0" layoutY="24.0" text="Accuracy: "/>
      <Label fx:id="lbl_currStage" layoutX="24.0" layoutY="24.0" text="Current Stage: "/>

    @FXML private Label lbl_username;
    @FXML private Label lbl_quesCorr;
    @FXML private Label lbl_quesIncorr;
    @FXML private Label lbl_accuracy;
    @FXML private Label lbl_currStage;
 */