package com.example.javafx_books.model;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    // Duomenu bazes prisijungimo 'linkas'
    private static final String URL = "jdbc:mysql://localhost:3306/books";

    // insertina irasa i DB nurodant values
    public static void insert(User user) {
        String query = "INSERT INTO users (username, password, email, admin) VALUES (?, ?, ?, ?);";

        try {
            // Vykdome prisijungima prie DB ir atliekame uzklausas
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Siekiant isvengti SQL injekciju, kiekviena laukeli aprasome atskirai - ignoruojami specialus simboliai
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setBoolean(4, user.isAdmin());

            // Naujo iraso sukurimui, esamo iraso redagavimui ir trynimui, naudosime executeUpdate metoda
            // Esamo iraso paieskai naudosime executeQuery metoda
            preparedStatement.executeUpdate();

            // Ivykdzius uzklausa, gera praktika yra uzdaryti prisijungimus
            preparedStatement.close();
            connection.close();

            System.out.println("Record has been inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: record has not been inserted.");
        }
    }

    // Selectina slaptazodi, kai nurodomas vartotojo vardas
    public static String getBCryptPassword(String username) {
        String query = "SELECT password FROM users WHERE username LIKE ?;";

        String bCryptPassword = "";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bCryptPassword = resultSet.getString("password");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bCryptPassword;
    }

    // Grazina admino statusa (0 arba 1) perduodant username
    public static int getAdminStatus(String username) {
        String query = "SELECT admin FROM users WHERE username LIKE ?;";

        int adminStatus = 0;
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                adminStatus = resultSet.getInt("admin");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adminStatus;
    }

    public static int returnId(String username) {
        String query = "SELECT id FROM users WHERE username LIKE ?";
        int username2 = 0;

        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                username2 = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return username2;
    }
}
