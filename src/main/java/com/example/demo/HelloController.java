package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Button button;

    @FXML
    protected void onHelloButtonClick() {
        button.setText("Welcome to JavaFX Application!");
    }
}