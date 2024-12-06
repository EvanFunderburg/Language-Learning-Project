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

public class PrintController implements Initializable {
    private LanguageAppFacade facade;
    @FXML private TextArea txt_difficultPhrases;
 

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        facade = LanguageAppFacade.getInstance();
        User user = facade.getUser();
        System.out.println(user.showStruggleList());
        txt_difficultPhrases.setText(user.showStruggleList());
    }
    

    @FXML
    private void goBack() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("profile");
    }

    @FXML
    private void switchToPrint() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("print");
    }

    
}