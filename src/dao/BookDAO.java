package dao;

import model.Book;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // 获取全部图书
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY publish_date DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book b = new Book(
                        rs.getString("isbn"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getDate("publish_date"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 根据 ISBN 获取单本书
    public Book getBookByIsbn(String isbn) {
        String sql = "SELECT * FROM books WHERE isbn=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Book(
                        rs.getString("isbn"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getDate("publish_date"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 添加图书
    public boolean addBook(Book b) {
        String sql = "INSERT INTO books (isbn, title, author, publisher, publish_date, price, stock) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getIsbn());
            ps.setString(2, b.getTitle());
            ps.setString(3, b.getAuthor());
            ps.setString(4, b.getPublisher());
            ps.setDate(5, b.getPublishDate());
            ps.setDouble(6, b.getPrice());
            ps.setInt(7, b.getStock());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 更新图书
    public boolean updateBook(Book b) {
        String sql = "UPDATE books SET title=?, author=?, publisher=?, publish_date=?, price=?, stock=? WHERE isbn=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setString(3, b.getPublisher());
            ps.setDate(4, b.getPublishDate());
            ps.setDouble(5, b.getPrice());
            ps.setInt(6, b.getStock());
            ps.setString(7, b.getIsbn());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 删除图书
    public boolean deleteBook(String isbn) {
        String sql = "DELETE FROM books WHERE isbn=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, isbn);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
