package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button button_logout;

    @FXML
    private Label label_welcome;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
             DBUtils.changeScene(actionEvent, "login.fxml", "Login LMS", "Reach");
            }
        });
    }

    public void setUserInformation (String username) {
        label_welcome.setText("Welcome " + username + " to Library Management System");
    }
}