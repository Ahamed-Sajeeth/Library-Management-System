package com.view.controller;


import com.dbController.MemberDbController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.model.Member;
import com.tableModel.MemberTableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MemberManagementController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadTable();
        loadMemberType.getItems().addAll("Student","Teacher");
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
    private JFXButton addMember;

    @FXML
    private JFXTextField memberIdField;

    @FXML
    private JFXButton searchMember;

    @FXML
    private JFXButton updateMember;

    @FXML
    private JFXButton deleteManagement;

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXTextField doaField;

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXTextField phoneField;

    @FXML
    private JFXRadioButton genderField;

    @FXML
    private ToggleGroup Gender;

    @FXML
    private JFXTextField gradeField;

    @FXML
    private JFXTextField addressField;

    @FXML
    private ComboBox<String> loadMemberType;

    @FXML
    private TableView<MemberTableModel> membersTable;

    @FXML
    private TableColumn<MemberTableModel, Integer> MemberIdColumn;

    @FXML
    private TableColumn<MemberTableModel, String> NameColumn;

    @FXML
    private TableColumn<MemberTableModel, String> DOAColumn;

    @FXML
    private TableColumn<MemberTableModel, String> GenderColumn;

    @FXML
    private TableColumn<MemberTableModel, String> EmailColumn;

    @FXML
    private TableColumn<MemberTableModel, String> PhoneColumn;

    @FXML
    private TableColumn<MemberTableModel, String> TypeColumn;

    @FXML
    private TableColumn<MemberTableModel, String> GradeColumn;

    @FXML
    private TableColumn<MemberTableModel, String> AddressColumn;

    private final ObservableList<MemberTableModel> data = FXCollections.observableArrayList();

    @FXML
    void loadMemberType(ActionEvent event) {

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
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    void loadTable(){

        MemberIdColumn.setCellValueFactory(new PropertyValueFactory<MemberTableModel, Integer>("memberId"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<MemberTableModel, String>("name"));
        DOAColumn.setCellValueFactory(new PropertyValueFactory<MemberTableModel, String>("doa"));
        GenderColumn.setCellValueFactory(new PropertyValueFactory<MemberTableModel, String>("gender"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<MemberTableModel, String>("email"));
        PhoneColumn.setCellValueFactory(new PropertyValueFactory<MemberTableModel, String>("phoneNo"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<MemberTableModel, String>("type"));
        GradeColumn.setCellValueFactory(new PropertyValueFactory<MemberTableModel, String>("grade"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<MemberTableModel, String>("address"));

        try{
            membersTable.setItems(data);
            ArrayList<Member> members = null;
            members = MemberDbController.getAllMembers();

            for (Member member : members){
                MemberTableModel mtm = new MemberTableModel();
                mtm.setMemberId(member.getMemberId());
                mtm.setName(member.getName());
                mtm.setDoa(member.getDoa());
                mtm.setGender(member.getGender());
                mtm.setEmail(member.getEmail());
                mtm.setPhoneNo(member.getPhoneNo());
                mtm.setType(member.getType());
                mtm.setGrade(member.getGrade());
                mtm.setAddress(member.getAddress());

                data.add(mtm);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addMember(ActionEvent event) {

        ValidationController valid = new ValidationController();

        if(valid.validateEmpty(memberIdField) && valid.validateEmpty(nameField) && valid.validateEmpty(doaField) &&
                valid.validateEmpty(emailField) && valid.validateEmpty(phoneField) && valid.validateDate(doaField) &&
                valid.validateEmpty(gradeField) && valid.validateEmpty(addressField))

        {
            int memberId = Integer.parseInt(memberIdField.getText());
            String name = nameField.getText();
            String doa = doaField.getText();
            RadioButton selectedRadioButton = (RadioButton) Gender.getSelectedToggle(); //Getting Selected Radio Button
            String gender = selectedRadioButton.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String type = loadMemberType.getValue();
            String grade = gradeField.getText();
            String address = addressField.getText();

            try {
                if(MemberDbController.checkMemberID(memberId)){
                    try {

                        Member member = new Member(memberId,name,doa,gender,email,phone,type,grade,address);
                        int i = MemberDbController.AddMember(member);

                        if (i > 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Member Management");
                            alert.setHeaderText(null);
                            alert.setContentText("Member Added Successfully..!");
                            alert.showAndWait();

                            memberIdField.setText(null);
                            nameField.setText(null);
                            doaField.setText(null);
                            emailField.setText(null);
                            phoneField.setText(null);
                            loadMemberType.setValue(null);
                            gradeField.setText(null);
                            addressField.setText(null);

                            //Table Refresh
                            data.clear();
                            loadTable();
                        }
                        else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Member Management");
                            alert.setHeaderText(null);
                            alert.setContentText("There is an Error in Adding Member..!");
                            alert.showAndWait();
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Member Management");
                    alert.setHeaderText(null);
                    alert.setContentText("Member ID Already Exists..!");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    }

    @FXML
    void deleteMember(ActionEvent event) {

        int memberId = Integer.parseInt(memberIdField.getText());

        try {
            int deleteMember = MemberDbController.DeleteMember(memberId);

            if(deleteMember > 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Member Management");
                alert.setHeaderText(null);
                alert.setContentText("Member Deleted Successfully..!");
                alert.showAndWait();

                memberIdField.setText(null);
                nameField.setText(null);
                doaField.setText(null);
                emailField.setText(null);
                phoneField.setText(null);
                loadMemberType.setValue(null);
                gradeField.setText(null);
                addressField.setText(null);

                //Table Refresh
                data.clear();
                loadTable();

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Member Management");
                alert.setHeaderText(null);
                alert.setContentText("There is an Error in Deleting Member..!");
                alert.showAndWait();

                memberIdField.setText(null);
                nameField.setText(null);
                doaField.setText(null);
                emailField.setText(null);
                phoneField.setText(null);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void searchMember(ActionEvent event) {

        int memberId = Integer.parseInt(memberIdField.getText());

        try {
            Member member = MemberDbController.searchMember(memberId);

            if(member != null){
                memberIdField.setText(String.valueOf(member.getMemberId()));
                nameField.setText(member.getName());
                doaField.setText(member.getDoa());
                genderField.setText(member.getGender());
                emailField.setText(member.getEmail());
                phoneField.setText(String.valueOf(member.getPhoneNo()));
                loadMemberType.setValue(member.getType());
                gradeField.setText(member.getType());
                addressField.setText(member.getAddress());

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Member Management");
                alert.setHeaderText(null);
                alert.setContentText("Member Not Found..!");
                alert.showAndWait();

                memberIdField.setText(null);
                nameField.setText(null);
                doaField.setText(null);
                emailField.setText(null);
                phoneField.setText(null);
                loadMemberType.setValue(null);
                gradeField.setText(null);
                addressField.setText(null);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateMember(ActionEvent event) {
        ValidationController valid = new ValidationController();

        if(valid.validateEmpty(memberIdField) && valid.validateEmpty(nameField) && valid.validateEmpty(doaField) &&
                valid.validateEmpty(emailField) && valid.validateEmpty(phoneField) && valid.validateDate(doaField))

        {
            int memberId = Integer.parseInt(memberIdField.getText());
            String name = nameField.getText();
            String doa = doaField.getText();
            RadioButton selectedRadioButton = (RadioButton) Gender.getSelectedToggle(); //Getting Selected Radio Button
            String gender = selectedRadioButton.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String type = loadMemberType.getValue();
            String grade = gradeField.getText();
            String address = addressField.getText();

            try {
                Member member = new Member(memberId,name,doa,gender,email,phone,type,grade,address);
                int i = MemberDbController.updateMember(member);


                if (i > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Member Management");
                    alert.setHeaderText(null);
                    alert.setContentText("Member Updated Successfully..!");
                    alert.showAndWait();

                    memberIdField.setText(null);
                    nameField.setText(null);
                    doaField.setText(null);
                    emailField.setText(null);
                    phoneField.setText(null);
                    loadMemberType.setValue(null);
                    gradeField.setText(null);
                    addressField.setText(null);

                    //Table Refresh
                    data.clear();
                    loadTable();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Member Management");
                    alert.setHeaderText(null);
                    alert.setContentText("There is an Error in Updating Member..!");
                    alert.showAndWait();
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }
}
