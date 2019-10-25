package com.model;


public class Book {

    private int bookId;
    private String name;
    private String isbn;
    private String author;
    private String category;
    private String description;
    private int noOfCopies;
    private int availableOfCopies;

    public Book(int bookId, String name, String isbn, String author, String category, String description, int noOfCopies, int availableOfCopies) {
        this.bookId = bookId;
        this.name = name;
        this.isbn = isbn;
        this.author = author;
        this.category = category;
        this.description = description;
        this.noOfCopies = noOfCopies;
        this.availableOfCopies = availableOfCopies;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    public int getAvailableOfCopies() {
        return availableOfCopies;
    }

    public void setAvailableOfCopies(int availableOfCopies) {
        this.availableOfCopies = availableOfCopies;
    }


}
