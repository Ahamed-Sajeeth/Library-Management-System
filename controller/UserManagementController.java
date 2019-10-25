package com.view.controller;

import com.dbController.UserDbController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.model.User;
import com.tableModel.UserTableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class UserManagementController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTable();
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
    private JFXButton addUser;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXButton searchUser;

    @FXML
    private JFXButton updateUser;

    @FXML
    private JFXButton deleteUser;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private TableView<UserTableModel> usersTable;

    @FXML
    private TableColumn<UserTableModel, String> UsernameColumn;

    @FXML
    private TableColumn<UserTableModel, String> PasswordColumn;

    private final ObservableList<UserTableModel> data = FXCollections.observableArrayList();


    @FXML
    void loadTable(){

        UsernameColumn.setCellValueFactory(new PropertyValueFactory<UserTableModel, String>("username"));
        PasswordColumn.setCellValueFactory(new PropertyValueFactory<UserTableModel, String>("password"));

        try {
            usersTable.setItems(data);
            ArrayList<User> users = null;
            users = UserDbController.getAllUsers();

            for (User user : users) {
                UserTableModel ctm = new UserTableModel();
                ctm.setUsername(user.getUsername());
                ctm.setPassword(user.getPassword());
                data.add(ctm);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    void addUser(ActionEvent event) {
        try {
            if(ValidationController.validateEmpty(usernameField) && ValidationController.validateEmpty(passwordField)) {

                String username = usernameField.getText();
                String password = passwordField.getText();

                User user = new User(username, password);
                int i = UserDbController.addUser(user);

                if(i > 0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("User Management");
                    alert.setHeaderText(null);
                    alert.setContentText("User Registered Successfully..!");
                    alert.showAndWait();

                    usernameField.setText(null);
                    passwordField.setText(null);
                }
                data.clear();               //Refresh Table
                loadTable();                //Refresh Table

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteUser(ActionEvent event) {

        String username = usernameField.getText();

        try {
            int deleteUser = UserDbController.deleteUser(username);

            if(deleteUser > 0){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Management");
                alert.setHeaderText(null);
                alert.setContentText("User Deleted Sucessfully..!");
                alert.showAndWait();

                usernameField.setText(null);
                passwordField.setText(null);

                data.clear();               //Refresh Table
                loadTable();                //Refresh Table

            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Management");
                alert.setHeaderText(null);
                alert.setContentText("There is an Error in Deleting User..!");
                alert.showAndWait();

                usernameField.setText(null);
                passwordField.setText(null);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchUser(ActionEvent event) {

        String username = usernameField.getText();

        try {
            User search = UserDbController.searchUser(username);

            if(search != null){

                usernameField.setText(search.getUsername());
                passwordField.setText(search.getPassword());

            }else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User Management");
                alert.setHeaderText(null);
                alert.setContentText("User Not Found");
                alert.showAndWait();

                usernameField.setText(null);
                passwordField.setText(null);

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateUser(ActionEvent event) {

        String username = usernameField.getText();
        String password = passwordField.getText();

        try {

            User user = new User(username,password);
            int update = UserDbController.updateUser(user);

            if(update > 0){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Management");
                alert.setHeaderText(null);
                alert.setContentText("User Updated Successfully..!");
                alert.showAndWait();

                usernameField.setText(null);
                passwordField.setText(null);

                data.clear();
                loadTable();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
