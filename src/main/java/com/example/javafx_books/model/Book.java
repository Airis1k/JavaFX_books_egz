package com.example.javafx_books.model;

public class Book {
    private int id;
    private String name;
    private String summary;
    private String ISBN;
    private int pageCount;
    private String category;
    private int userId;

    public Book() {
    }

    public Book(int id, String name, String summary, String ISBN, int pageCount, String category) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.ISBN = ISBN;
        this.pageCount = pageCount;
        this.category = category;
    }

    public Book(String name, String summary, String ISBN, int pageCount, String category, int userId) {
        this.name = name;
        this.summary = summary;
        this.ISBN = ISBN;
        this.pageCount = pageCount;
        this.category =  category;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", pageCount=" + pageCount +
                ", category='" + category + '\'' +
                ", userId=" + userId +
                '}';
    }
}
