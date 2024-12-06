package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.language.App;
import com.model.LanguageAppFacade;
import com.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StoryPageController  implements Initializable {
    @FXML private Button homepage;
    @FXML private Label lbl_title;
    @FXML private Label story_txt;
    @FXML private ImageView image;
    @FXML private Image storypicture;
    private LanguageAppFacade facade;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        facade = LanguageAppFacade.getInstance();
        String imagestr = facade.getStory(facade.getUser().getStorySelect()).getimage;
       storypicture = new Image(getClass().getResourceAsStream("@images/"+imagestr));
        
        String[] answers = facade.getAnswerChoices().split("#");
        
        // System.out.println(facade.getAnswerChoices());
        lbl_title.setText("ballfart");
        story_txt.setText("balls in my face llllllllllllllllllllllllllllllllllllllllllllllllllllll morea balss");
        imageView.setImage();
        
    }
    public void displayImage(){
        myImageV
    } 
    @FXML
    void switchToUserHomePage(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
    
}
