module com.example.javafx_books {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javafx_books to javafx.fxml;
    opens com.example.javafx_books.model to javafx.base;
    exports com.example.javafx_books;
    exports com.example.javafx_books.controller;
    opens com.example.javafx_books.controller to javafx.fxml;
}