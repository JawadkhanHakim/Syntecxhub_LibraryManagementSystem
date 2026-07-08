package com.library;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {

    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author, isbn, quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsbn());
            ps.setInt(4, book.getQuantity());
            ps.executeUpdate();
            System.out.println("✅ Book added successfully!");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("❌ A book with this ISBN already exists.");
        } catch (SQLException e) {
            System.out.println("❌ Error adding book: " + e.getMessage());
        }
    }

    public void removeBook(String isbn) {
        String sql = "DELETE FROM books WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, isbn);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Book removed successfully!");
            } else {
                System.out.println("⚠️ No book found with that ISBN.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error removing book: " + e.getMessage());
        }
    }

    public List<Book> searchByTitle(String title) {
        List<Book> results = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + title + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                results.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("quantity")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error searching book: " + e.getMessage());
        }

        return results;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("quantity")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error fetching books: " + e.getMessage());
        }

        return books;
    }
}