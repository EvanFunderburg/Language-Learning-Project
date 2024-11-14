package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;

import com.language.App;
import com.narration.*;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        Narriator.playSound("1 2 3 4 5 6");
        //App.setRoot("secondary");
    }
}
