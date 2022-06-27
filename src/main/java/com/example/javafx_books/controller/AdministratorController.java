package com.example.javafx_books.controller;

import com.example.javafx_books.Main;
import com.example.javafx_books.model.Book;
import com.example.javafx_books.model.BookDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class AdministratorController {
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn summaryColumn;
    @FXML
    private TableColumn isbnColumn;
    @FXML
    private TableColumn pageCountColumn;
    @FXML
    private TableColumn categoryColumn;

    ObservableList<Book> list = FXCollections.observableArrayList();

    @FXML
    protected void onSearchButtonClick() {
        List<Book> bookList = BookDAO.printAll();

        for (Book book : bookList) {
            // Is DB saraso sudedame elementus i ObservableList (kad juos galetume matyti GUI)
            list.add(new Book(book.getId(), book.getName(), book.getSummary(), book.getISBN(), book.getPageCount(), book.getCategory()));

            // Priskiriame lenteles stulpeliams reiksmes is DB
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            summaryColumn.setCellValueFactory(new PropertyValueFactory<>("summary"));
            isbnColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            pageCountColumn.setCellValueFactory(new PropertyValueFactory<>("page_count"));
            categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        }

        tableView.setItems(list);
    }

    @FXML
    protected void onEditButtonClick(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("crud-view.fxml"));
        Stage crudWindow = new Stage();
        crudWindow.setTitle("Admin");
        crudWindow.setScene(new Scene(root, 480, 480));
        crudWindow.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
