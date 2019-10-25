package com.view.controller;


import com.db.DBConnection;
import com.dbController.BookDbController;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LendBookController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadBooks();
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
    private JFXButton lendBook;

    @FXML
    private JFXButton Reset;

    @FXML
    private JFXButton bookReturn;

    @FXML
    private ComboBox<String> loadBooks;

    @FXML
    private TextField memberIdField;

    @FXML
    private TextField borrowDateField;

    @FXML
    private TextField returnDateField;

    @FXML
    private TextField lenderNameField;

    @FXML
    void bookReturn(ActionEvent event) {
        try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/view/fxml/ReturnBook.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Return Book Management");
                stage.setScene(new Scene(root));
                stage.show();
        }catch(IOException e){
                System.out.println(e);
        }
    }

    @FXML
    void Reset(ActionEvent event) {

        memberIdField.setText(null);
        borrowDateField.setText(null);
        returnDateField.setText(null);
        lenderNameField.setText(null);
        loadBooks.setValue(null);
    }

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
    void lendBook(ActionEvent event) {

        ValidationController valid = new ValidationController();

        if(valid.validateEmpty(memberIdField) && valid.validateEmpty(borrowDateField)
                && valid.validateEmpty(returnDateField) && valid.validateEmpty(lenderNameField) )
        {
            int memberId = Integer.parseInt(memberIdField.getText());
            String bookName = loadBooks.getValue();
            String borrowDate = borrowDateField.getText();
            String returnDate = returnDateField.getText();
            String lender = lenderNameField.getText();

            try {
                String sql = "INSERT INTO lend(memberId , book , borrowDate , returnDate , lender) " +
                        "VALUES ('"+memberId+"','"+bookName+"','"+borrowDate+"','"+returnDate+"','"+lender+"')";

                //String sql = "INSERT INTO lend VALUES (memberId= '"+memberId+"' ,book= '"+bookName+"' ,borrowDate= '"+borrowDate+"' ,returnDate= '"+returnDate+"' ,lender= '"+lender+"')";

                Connection conn = null;
                conn = DBConnection.getDBConnection().getConnection();
                Statement stm = conn.createStatement();
                int i = stm.executeUpdate(sql);

                if(i>0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Lend a Book");
                    alert.setHeaderText(null);
                    alert.setContentText("Book Lended for Member ID "+memberId+" Successfully..!");
                    alert.showAndWait();

                    memberIdField.setText(null);
                    borrowDateField.setText(null);
                    returnDateField.setText(null);
                    lenderNameField.setText(null);
                    loadBooks.setValue(null);

                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lend a Book");
                    alert.setHeaderText(null);
                    alert.setContentText("There is an Error..!");
                    alert.showAndWait();
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    private void loadBooks() {
        ArrayList arrayList = null;
        try {
            arrayList = BookDbController.getBooks();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        ObservableList observableArray = FXCollections.observableArrayList();
        observableArray.addAll(arrayList);

        if (observableArray != null){
            loadBooks.setItems(observableArray);
        }

    }


}
