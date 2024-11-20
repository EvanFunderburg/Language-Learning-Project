package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;

import com.language.App;
import com.narration.*;
public class LogInController {
    @FXML
    private void switchToEntryPage() throws IOException {
        // Narriator.playSound("1 2 3 4 5 6");
        App.setRoot("entry_page");
    }
}
