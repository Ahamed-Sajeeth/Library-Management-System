package com.view.controller;

import com.db.DBConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadCounts();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
    private JFXTextField bookCountField;

    @FXML
    private JFXTextField memberCountField;

    @FXML
    private JFXTextField lendCountField;

    @FXML
    private JFXTextField userCountField;

    @FXML
    void btnBookMgmt(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(("/com/view/fxml/BookManagement.fxml")));
            root.getChildren().setAll(pane);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    void btnDashboard(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(("/com/view/fxml/Dashboard.fxml")));
            root.getChildren().setAll(pane);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    void btnLendBook(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(("/com/view/fxml/LendBook.fxml")));
            root.getChildren().setAll(pane);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    void btnMemberMgmt(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(("/com/view/fxml/MemberManagement.fxml")));
            root.getChildren().setAll(pane);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    void btnSignOut(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(("/com/view/fxml/Login.fxml")));
            root.getChildren().setAll(pane);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    void btnUserMgmt(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(("/com/view/fxml/UserManagement.fxml")));
            root.getChildren().setAll(pane);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    void loadCounts() throws SQLException, ClassNotFoundException {

        String bookCount;
        String memberCount;
        String lendCount;
        String userCount;

        String sql1 = "SELECT COUNT(*) AS TOTAL FROM books";
        String sql2 = "SELECT COUNT(*) AS TOTAL FROM members";
        String sql3 = "SELECT COUNT(*) AS TOTAL FROM lend";
        String sql4 = "SELECT COUNT(*) AS TOTAL FROM users";

        Connection conn = DBConnection.getDBConnection().getConnection();
        Statement stm = conn.createStatement();

        ResultSet rst1 = stm.executeQuery(sql1);
        while (rst1.next()) {
            bookCount = rst1.getString("TOTAL");
            bookCountField.setText(bookCount);
        }

        ResultSet rst2 = stm.executeQuery(sql2);
        while (rst2.next()) {
            memberCount = rst2.getString("TOTAL");
            memberCountField.setText(memberCount);
        }

        ResultSet rst3 = stm.executeQuery(sql3);
        while (rst3.next()) {
            lendCount = rst3.getString("TOTAL");
            lendCountField.setText(lendCount);
        }

        ResultSet rst4 = stm.executeQuery(sql4);
        while (rst4.next()) {
            userCount = rst4.getString("TOTAL");
            userCountField.setText(userCount);
        }

    }

}
