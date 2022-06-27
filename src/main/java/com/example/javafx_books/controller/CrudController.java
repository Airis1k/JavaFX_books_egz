package com.example.javafx_books.controller;

import com.example.javafx_books.Main;
import com.example.javafx_books.model.Book;
import com.example.javafx_books.model.BookDAO;
import com.example.javafx_books.model.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CrudController {
    @FXML
    private TextField nameField;
    @FXML
    private TextArea summaryField;
    @FXML
    private TextField isbnField;
    @FXML
    private TextField pageField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField idField;

    @FXML
    protected void onCreateButtonClick(ActionEvent event) throws IOException {
        String name = nameField.getText();
        String summary = summaryField.getText();
        String isbn = isbnField.getText();
        int page = Integer.parseInt(pageField.getText());
        String category = categoryField.getText();
        int userId = UserDAO.returnId(name);

        Book book = new Book(name, summary, isbn, page, category, userId);
        BookDAO.insert(book);

        Parent root = FXMLLoader.load(Main.class.getResource("administrator-view.fxml"));
        Stage adminWindow = new Stage();
        adminWindow.setTitle("Admin");
        adminWindow.setScene(new Scene(root, 920, 480));
        adminWindow.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    protected void onSearchButtonClick() {
    }

    @FXML
    protected void onUpdateButtonClick(ActionEvent event) throws IOException {
        String name = nameField.getText();
        String summary = summaryField.getText();
        String isbn = isbnField.getText();
        int page = Integer.parseInt(pageField.getText());
        String category = categoryField.getText();
        int id = Integer.parseInt(idField.getText());

        Book book = new Book(name, summary, isbn, page, category, id);
        BookDAO.update(book);

        Parent root = FXMLLoader.load(Main.class.getResource("administrator-view.fxml"));
        Stage adminWindow = new Stage();
        adminWindow.setTitle("Admin");
        adminWindow.setScene(new Scene(root, 920, 480));
        adminWindow.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    protected void onDeleteButtonClick() {
    }
}
