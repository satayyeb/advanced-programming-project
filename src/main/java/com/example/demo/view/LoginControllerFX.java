package com.example.demo.view;

import com.example.demo.HelloApplication;
import com.example.demo.controller.LoginController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.demo.view.StageController.errorMaker;

public class LoginControllerFX implements Initializable {
    public Button loginButton;
    public Button registerButton;
    public ImageView background;
    @FXML
    private TextField username, password, nickname;

    @FXML
    private void register() {
        switch (LoginController.createNewUser(username.getText(),
                password.getText(), nickname.getText())) {
            case 0 -> {
                StageController.errorMaker("what a dumb username","user created successfully", Alert.AlertType.INFORMATION);
            }
            case 1 -> errorMaker("Input not valid",
                    "type something dumbAss", Alert.AlertType.ERROR);
            case 2 -> errorMaker("Input not valid",
                    "a user with this username already exists", Alert.AlertType.ERROR);
            case 3 -> errorMaker("Input not valid",
                    "a user with this nickname already exists", Alert.AlertType.ERROR);
            case 4 -> errorMaker("Input not valid",
                    "nice joke. now please insert some real password", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void login() throws IOException {

        switch (LoginController.loginUser(username.getText(),
                password.getText())) {
            case 0 -> StageController.sceneChanger("mainMenu.fxml");
            case 1 -> errorMaker("Input not valid",
                    "type something dumbAss", Alert.AlertType.ERROR);
            case 2 -> errorMaker("Input not valid",
                    "username or password is incorrect", Alert.AlertType.ERROR);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() ->username.setLayoutX(StageController.getScene().getWidth()/2- username.getWidth()/2));
        Platform.runLater(() ->password.setLayoutX(StageController.getScene().getWidth()/2- password.getWidth()/2));
        Platform.runLater(() ->nickname.setLayoutX(StageController.getScene().getWidth()/2- nickname.getWidth()/2));
        Platform.runLater(() ->loginButton.setLayoutX(StageController.getScene().getWidth()/2- loginButton.getWidth()/2));
        Platform.runLater(() ->registerButton.setLayoutX(StageController.getScene().getWidth()/2- registerButton.getWidth()/2));
        Platform.runLater(() ->background.setFitWidth(StageController.getScene().getWidth()));
        Platform.runLater(() ->background.setFitHeight(StageController.getScene().getHeight()));
    }
}
