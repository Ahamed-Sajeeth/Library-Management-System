package com.view.controller;


import com.dbController.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginUIController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane root2;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXButton Login;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    void Login(ActionEvent event) {

        LoginController login = new LoginController();

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText("Username & Password Required..!");
            alert.showAndWait();
        } else {

            try {
                if (login.Login(username, password)) {

                    AnchorPane user = FXMLLoader.load(getClass().getResource(("/com/view/fxml/Dashboard.fxml")));
                    root.getChildren().setAll(user);

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Username or Password..!");
                    alert.showAndWait();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
