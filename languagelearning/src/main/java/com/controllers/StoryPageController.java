package com.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.language.App;
import com.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class StoryPageController  implements Initializable {
    @FXML private Button homepage;
    @FXML private Button nextpage;
    @FXML private Text txt_title;
    @FXML private TextArea story_txt;
    @FXML private ImageView image;
    @FXML private Image storypicture;
    private LanguageAppFacade facade;
    private Story cuStory;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        facade = LanguageAppFacade.getInstance();
        cuStory = facade.getStory(facade.getUser().getStorySelect());
        StoryPage page = cuStory.getStoryPage();
        try{
            storypicture = new Image(getClass().getResourceAsStream("stupidfix/"+page.getImage()));
            image.setImage(storypicture);
        }
        catch(Exception e){
        }
        image.setImage(storypicture);
        txt_title.setText(cuStory.getTitle());
        story_txt.setText(page.getsentenceinstr());

        
    }

    @FXML
    void switchToUserHomePage(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
    @FXML
    void switchToNext(ActionEvent event) throws IOException {
        if(cuStory.isStoryComplete()){
            App.setRoot("primary");
        }
;
        cuStory.turnPage();
        StoryPage page = cuStory.getStoryPage();
        try{
            storypicture = new Image(getClass().getResourceAsStream("stupidfix/"+page.getImage()));
            image.setImage(storypicture);
        }
        catch(Exception e){
        }
        story_txt.setText(page.getsentenceinstr());
        if(cuStory.isStoryComplete()){
            nextpage.setText("END STORY");
        }
    }
    
}

/*
 * <ImageView fitHeight="272.0" fitWidth="277.0" pickOnBounds="true" preserveRatio="true" fx:id="image"/>
         <image>
            <Image url= "@images/goldi.png" />
         </image>
      </ImageView>
 */