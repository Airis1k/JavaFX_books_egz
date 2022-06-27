package com.example.javafx_books.controller;

import com.example.javafx_books.Main;
import com.example.javafx_books.model.User;
import com.example.javafx_books.model.UserDAO;
import com.example.javafx_books.utils.BCryptPassword;
import com.example.javafx_books.utils.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPassField;
    @FXML
    private TextField emailField;
    @FXML
    private CheckBox isAdmin;
    @FXML
    private Label errorLabel;

    @FXML
    protected void onRegisterButtonClick(ActionEvent event) throws Exception {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPass = confirmPassField.getText();
        String email = emailField.getText();
        boolean isAdminCheck = isAdmin.isSelected();

        if (!Validation.isValidUsername(username)) {
            errorLabel.setText("Username is incorrect! Just capital and lower cases, numbers, length between 5 and 13!");
        } else if (!Validation.isValidPassword(password)) {
            errorLabel.setText("Password is incorrect! Just capital and lower cases, special symbols and length between 5 and 16!");
        } else if (!confirmPass.equals(password)) {
            errorLabel.setText("Passwords are not equal!");
        } else if (!Validation.isValidEmail(email)) {
            errorLabel.setText("Email is incorrect! Just capital and lower cases, numbers and special symbols!");
        } else {
            // 1-a paimam ir uzBCryptinam passworda ir tada perduodame per parametrus naujam sukurtam useriui
            String bCryptPassword = BCryptPassword.hashPassword(password);

            // Pridedame nauja sukurta useri i DB.
            User user = new User(username, bCryptPassword, email, isAdminCheck);
            UserDAO.insert(user);

            // Sukurus nauja useri, griztame i login-view langa
            // Vaizdo uzkrovimas
            Parent root = FXMLLoader.load(Main.class.getResource("login-view.fxml"));
            Stage loginWindow = new Stage();
            loginWindow.setTitle("Login");
            loginWindow.setScene(new Scene(root, 600, 420));
            // Lango parodymas
            loginWindow.show();
            // Kodas reikalingas paslepti pries tai buvusi langa
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }
}
