package com.view.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ReturnBookController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane sidebar;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnBookMgmt;

    @FXML
    private JFXButton btnMemberMgmt;

    @FXML
    private JFXButton btnUserMgmt;

    @FXML
    private JFXButton btnLendBook;

    @FXML
    private JFXButton btnSignOut;

    @FXML
    private JFXButton returnBook;

    @FXML
    private JFXButton Reset;

    @FXML
    private TextField memberIdField;

    @FXML
    private TextField borrowDateField;

    @FXML
    private TextField returnDateField;

    @FXML
    private TextField lenderNameField;

    @FXML
    private TextField memberIdField1;

    @FXML
    void Reset(ActionEvent event) {

    }

    @FXML
    void btnBookMgmt(ActionEvent event) {

    }

    @FXML
    void btnDashboard(ActionEvent event) {

    }

    @FXML
    void btnLendBook(ActionEvent event) {

    }

    @FXML
    void btnMemberMgmt(ActionEvent event) {

    }

    @FXML
    void btnSignOut(ActionEvent event) {

    }

    @FXML
    void btnUserMgmt(ActionEvent event) {

    }

    @FXML
    void returnBook(ActionEvent event) {

    }

}
