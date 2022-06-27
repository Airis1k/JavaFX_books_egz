package com.example.javafx_books.model;

import java.sql.*;
import java.util.ArrayList;

public class BookDAO {
    // Duomenu bazes prisijungimo 'linkas'
    private static final String URL = "jdbc:mysql://localhost:3306/books";

    // atspausdina visus irasus
    public static ArrayList<Book> printAll() {
        String query = "SELECT * FROM books;";
        ArrayList<Book> list = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("summary"),
                        resultSet.getString("ISBN"),
                        resultSet.getInt("page_count"),
                        resultSet.getString("category")
                ));
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            return list;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static void insert(Book book) {
        String query = "INSERT INTO books (name, summary, ISBN, page_count, category, user_id)\n" +
                "VALUES (?, ?, ?, ?, ?, ?);";

        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getSummary());
            preparedStatement.setString(3, book.getISBN());
            preparedStatement.setInt(4, book.getPageCount());
            preparedStatement.setString(5, book.getCategory());
            preparedStatement.setInt(6, 8);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(Book book) {
        String query = "UPDATE books SET name = ?, summary = ?, ISBN = ?, page_count = ?, category = ? WHERE id = ?;";

        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getSummary());
            preparedStatement.setString(3, book.getISBN());
            preparedStatement.setInt(4, book.getPageCount());
            preparedStatement.setString(5, book.getCategory());
            preparedStatement.setInt(6, book.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
