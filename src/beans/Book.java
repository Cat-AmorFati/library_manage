package beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.DBUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private String isbn;          // 主键
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishDate; // LocalDate 对应数据库 DATE
    private BigDecimal price;      // BigDecimal 对应数据库 DECIMAL
    private int stock;

    // 1. 添加图书
    public int addBook() {
        String sql = "INSERT INTO books (isbn, title, author, publisher, publish_date, price, stock) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String[] params = {
                isbn,
                title,
                author,
                publisher,
                publishDate.toString(),   // 转换成 yyyy-MM-dd
                price.toPlainString(),    // BigDecimal 转字符串
                String.valueOf(stock)
        };
        return DBUtil.executeUpdate(sql, params);
    }

    // 2. 查询单本图书（通过 ISBN）
    public Map<String, Object> getBook() {
        String sql = "SELECT * FROM books WHERE isbn = ?";
        String[] params = {isbn};
        return DBUtil.queryMap(sql, params);
    }

    // 3. 修改图书信息
    public int updateBook() {
        String sql = "UPDATE books SET title=?, author=?, publisher=?, publish_date=?, price=?, stock=? WHERE isbn=?";
        String[] params = {
                title,
                author,
                publisher,
                publishDate.toString(),     // LocalDate 转字符串
                price.toPlainString(),      // BigDecimal 转字符串
                String.valueOf(stock),
                isbn
        };
        return DBUtil.executeUpdate(sql, params);
    }

    // 4. 获取所有图书
    public List<Map<String, Object>> getAllBooks() {
        String sql = "SELECT * FROM books";
        return DBUtil.queryList(sql, null);
    }

    // 5. 删除图书
    public int delBook() {
        String sql = "DELETE FROM books WHERE isbn = ?";
        String[] params = {isbn};
        return DBUtil.executeUpdate(sql, params);
    }
}
