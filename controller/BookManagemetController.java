package com.view.controller;


import com.dbController.BookDbController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.model.Book;
import com.tableModel.BookTableModel;
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

public class BookManagemetController implements Initializable {

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
    private JFXButton addBook;

    @FXML
    private JFXTextField bookIdField;

    @FXML
    private JFXButton searchBook;

    @FXML
    private JFXButton updateBook;

    @FXML
    private JFXButton deleteBook;

    @FXML
    private JFXTextField bookNameField;

    @FXML
    private JFXTextField isbnField;

    @FXML
    private JFXTextField authorField;

    @FXML
    private JFXTextField categoryField;

    @FXML
    private JFXTextField descriptionField;

    @FXML
    private JFXTextField copiesField;

    @FXML
    private JFXTextField availableField;


    @FXML
    private TableView<BookTableModel> booksTable;

    @FXML
    private TableColumn<BookTableModel, Integer> BookIdColumn;

    @FXML
    private TableColumn<BookTableModel, String> BookNameColumn;

    @FXML
    private TableColumn<BookTableModel, String> IsbnColumn;

    @FXML
    private TableColumn<BookTableModel, String> AuthorColumn;

    @FXML
    private TableColumn<BookTableModel, String> CategoryColumn;

    @FXML
    private TableColumn<BookTableModel, String> DescriptionColumn;

    @FXML
    private TableColumn<BookTableModel, Integer> CopiesColumn;

    @FXML
    private TableColumn<BookTableModel, Integer> AvailableColumn;

    private final ObservableList<BookTableModel> data = FXCollections.observableArrayList();


    @FXML
    void loadTable(){

        BookIdColumn.setCellValueFactory(new PropertyValueFactory<BookTableModel, Integer>("bookId"));
        BookNameColumn.setCellValueFactory(new PropertyValueFactory<BookTableModel, String>("name"));
        IsbnColumn.setCellValueFactory(new PropertyValueFactory<BookTableModel, String>("isbn"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<BookTableModel, String>("author"));
        CategoryColumn.setCellValueFactory(new PropertyValueFactory<BookTableModel, String>("category"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<BookTableModel, String>("description"));
        CopiesColumn.setCellValueFactory(new PropertyValueFactory<BookTableModel, Integer>("noOfCopies"));
        AvailableColumn.setCellValueFactory(new PropertyValueFactory<BookTableModel, Integer>("AvailableOfCopies"));

        try {
            booksTable.setItems(data);
            ArrayList<Book> books = null;
            books = BookDbController.getAllBooks();

            for (Book book : books) {
                BookTableModel ctm = new BookTableModel();
                ctm.setBookId(book.getBookId());
                ctm.setName(book.getName());
                ctm.setIsbn(book.getIsbn());
                ctm.setAuthor(book.getAuthor());
                ctm.setCategory(book.getCategory());
                ctm.setDescription(book.getDescription());
                ctm.setNoOfCopies(book.getNoOfCopies());
                ctm.setAvailableOfCopies(book.getAvailableOfCopies());

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
    void addBook(ActionEvent event) {

        ValidationController valid = new ValidationController();

        if(valid.validateEmpty(bookIdField) && valid.validateEmpty(bookNameField) && valid.validateEmpty(authorField) &&
                valid.validateEmpty(categoryField) && valid.validateEmpty(copiesField) && valid.numbersOnly(availableField)&&
                valid.validateEmpty(isbnField)&& valid.numbersOnly(isbnField))

        {
            int bookId = Integer.parseInt(bookIdField.getText());
            String bookname = bookNameField.getText();
            String isbn = isbnField.getText();
            String author = authorField.getText();
            String category = categoryField.getText();
            String description = descriptionField.getText();
            int copies = Integer.parseInt(copiesField.getText());
            int availableofcopies = Integer.parseInt(availableField.getText());

            try {
                Book book = new Book(bookId,bookname,isbn,author,category,description,copies,availableofcopies);
                int i = BookDbController.AddBook(book);

                if (i > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Book Management");
                    alert.setHeaderText(null);
                    alert.setContentText("Book Added Successfully..!");
                    alert.showAndWait();

                    bookIdField.setText(null);
                    bookNameField.setText(null);
                    isbnField.setText(null);
                    authorField.setText(null);
                    categoryField.setText(null);
                    descriptionField.setText(null);
                    copiesField.setText(null);
                    availableField.setText(null);

                    //Table Refresh
                    data.clear();
                    loadTable();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Book Management");
                    alert.setHeaderText(null);
                    alert.setContentText("There is an Error in Adding Book..!");
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
    void deleteBook(ActionEvent event) {

        int bookId = Integer.parseInt(bookIdField.getText());

        try {
           int deleteBook = BookDbController.DeleteBook(bookId);

           if(deleteBook > 0){
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Book Management");
               alert.setHeaderText(null);
               alert.setContentText("Book Deleted Successfully..!");
               alert.showAndWait();

               bookIdField.setText(null);
               bookNameField.setText(null);
               isbnField.setText(null);
               authorField.setText(null);
               categoryField.setText(null);
               descriptionField.setText(null);
               copiesField.setText(null);
               availableField.setText(null);

               //Table Refresh
               data.clear();
               loadTable();

           }else{
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Book Management");
               alert.setHeaderText(null);
               alert.setContentText("There is an Error in Deleting Book..!");
               alert.showAndWait();

               bookIdField.setText(null);
               bookNameField.setText(null);
               isbnField.setText(null);
               authorField.setText(null);
               categoryField.setText(null);
               descriptionField.setText(null);
               copiesField.setText(null);
               availableField.setText(null);
           }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void searchBook(ActionEvent event) {

        int bookId = Integer.parseInt(bookIdField.getText());

        try {
            Book book = BookDbController.searchBook(bookId);

            if(book != null){
                bookIdField.setText(String.valueOf(book.getBookId()));
                bookNameField.setText(book.getName());
                isbnField.setText(book.getIsbn());
                authorField.setText(book.getAuthor());
                categoryField.setText(book.getCategory());
                descriptionField.setText(book.getDescription());
                copiesField.setText(String.valueOf(book.getNoOfCopies()));
                availableField.setText(String.valueOf(book.getAvailableOfCopies()));

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Book Management");
                alert.setHeaderText(null);
                alert.setContentText("Book Not Found..!");
                alert.showAndWait();

                bookIdField.setText(null);
                bookNameField.setText(null);
                isbnField.setText(null);
                authorField.setText(null);
                categoryField.setText(null);
                descriptionField.setText(null);
                copiesField.setText(null);
                availableField.setText(null);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateBook(ActionEvent event) {
        ValidationController valid = new ValidationController();

        if(valid.validateEmpty(bookIdField) && valid.validateEmpty(bookNameField) && valid.validateEmpty(authorField) &&
                valid.validateEmpty(categoryField) && valid.validateEmpty(copiesField) && valid.validateEmpty(availableField))

        {
            int bookId = Integer.parseInt(bookIdField.getText());
            String bookname = bookNameField.getText();
            String isbn = isbnField.getText();
            String author = authorField.getText();
            String category = categoryField.getText();
            String description = descriptionField.getText();
            int copies = Integer.parseInt(copiesField.getText());
            int available = Integer.parseInt(availableField.getText());

            try {
                Book book = new Book(bookId,bookname,isbn,author,category,description,copies,available);
                int i = BookDbController.updateBook(book);

                if (i > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Book Management");
                    alert.setHeaderText(null);
                    alert.setContentText("Book Updated Successfully..!");
                    alert.showAndWait();

                    bookIdField.setText(null);
                    bookNameField.setText(null);
                    isbnField.setText(null);
                    authorField.setText(null);
                    categoryField.setText(null);
                    descriptionField.setText(null);
                    copiesField.setText(null);
                    availableField.setText(null);

                    //Table Refresh
                    data.clear();
                    loadTable();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Book Management");
                    alert.setHeaderText(null);
                    alert.setContentText("There is an Error in Updating Book..!");
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
