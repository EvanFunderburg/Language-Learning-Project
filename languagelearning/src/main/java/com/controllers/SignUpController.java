
package com.controllers;

import java.io.IOException;
import java.util.Locale.LanguageRange;

import com.language.App;
import com.model.LanguageAppFacade;
import com.narration.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SignUpController {

    @FXML
    private Label lbl_error;

    @FXML
    private Button signUpButton;

    @FXML
    private TextArea txt_password;

    @FXML
    private TextArea txt_username;

    @FXML
    private TextArea txt_firstname;

    @FXML
    private TextArea txt_lastname;

    @FXML
    void switchToUserHomePage(ActionEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();
        String firstname = txt_firstname.getText();
        String lastname = txt_lastname.getText();

        LanguageAppFacade facade = LanguageAppFacade.getInstance();
        if(!facade.signUp(firstname, lastname, username, password)){
            lbl_error.setText("Invalid login credentials.");
            return;
        }

        App.setRoot("choose_lang");
    }
}
