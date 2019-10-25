package com.dbController;


import com.db.DBConnection;
import com.model.Book;

import java.sql.*;
import java.util.ArrayList;

public class BookDbController {

    public static int AddBook(Book book)throws ClassNotFoundException,SQLException {
        String SQL="INSERT INTO books VALUES(?,?,?,?,?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(SQL);
        stm.setObject(1, book.getBookId());
        stm.setObject(2, book.getName());
        stm.setObject(3,book.getIsbn());
        stm.setObject(4, book.getAuthor());
        stm.setObject(5, book.getCategory());
        stm.setObject(6, book.getDescription());
        stm.setObject(7, book.getNoOfCopies());
        stm.setObject(8, book.getAvailableOfCopies());

        return  stm.executeUpdate();
    }

    public static int DeleteBook(Integer bookId) throws ClassNotFoundException, SQLException {

        String sql = "DELETE FROM books WHERE bookId ='"+bookId+"'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        return  stm.executeUpdate();
    }

    public static Book searchBook(Integer bookId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM books WHERE bookId = ? ";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, bookId);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            Book book = new Book(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4), rst.getString(5), rst.getString(6), rst.getInt(7),rst.getInt(8));
            return book;
        }
        return null;
    }

    public static int updateBook(Book book) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE books SET bookId= ? ,name= ?, isbn= ? ,author= ? ,category= ? ,description= ? ,noOfCopies= ? ,availableOfCopies= ? WHERE bookId= '" +book.getBookId()+ "'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, book.getBookId());
        stm.setObject(2, book.getName());
        stm.setObject(3,book.getIsbn());
        stm.setObject(4, book.getAuthor());
        stm.setObject(5, book.getCategory());
        stm.setObject(6, book.getDescription());
        stm.setObject(7, book.getNoOfCopies());
        stm.setObject(8, book.getAvailableOfCopies());

        return  stm.executeUpdate();
    }

    public static ArrayList<Book> getAllBooks() throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm = conn.createStatement();
        ResultSet rst = stm.executeQuery("Select * From books");
        ArrayList <Book> BookList = new ArrayList<>();
        while(rst.next()){
            Book book;
            book = new Book(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4), rst.getString(5), rst.getString(6), rst.getInt(7), rst.getInt(8));
            BookList.add(book);
        }
        return BookList;
    }

    public static ArrayList<String> getBooks() throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery("Select name from books");

        ArrayList<String>bookList=new ArrayList<>();
        while(rst.next()){
            bookList.add(rst.getString("name"));
        }
        return bookList;
    }

}
