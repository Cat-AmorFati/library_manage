package beans;

import lombok.*;
import util.DBUtil;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String publish_date;
    private double price;
    private int stock;

    // 1️获取所有图书
    public List<Map<String, Object>> getAllBooks() {
        String sql = "SELECT * FROM books";
        return DBUtil.queryList(sql, null);
    }

    // 2️添加图书
    public int addBook() {
        String sql = "INSERT INTO books(isbn, title, author, publisher, publish_date, price, stock) VALUES(?,?,?,?,?,?,?)";
        String[] params = {isbn, title, author, publisher, publish_date, String.valueOf(price), String.valueOf(stock)};
        return DBUtil.executeUpdate(sql, params);
    }

    // 3️查询单本图书
    public Map<String, Object> getBook() {
        String sql = "SELECT * FROM books WHERE isbn = ?";
        String[] params = {isbn};
        return DBUtil.queryMap(sql, params);
    }

    // 4️修改图书信息
    public int updateBook() {
        String sql = "UPDATE books SET title=?, author=?, publisher=?, publish_date=?, price=?, stock=? WHERE isbn=?";
        String[] params = {title, author, publisher, publish_date, String.valueOf(price), String.valueOf(stock), isbn};
        return DBUtil.executeUpdate(sql, params);
    }

    // 5️删除图书
    public int delBook() {
        String sql = "DELETE FROM books WHERE isbn = ?";
        String[] params = {isbn};
        return DBUtil.executeUpdate(sql, params);
    }
}
