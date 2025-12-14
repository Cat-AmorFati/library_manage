package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data                   // 自动生成 getter/setter/toString/equals/hashCode
@NoArgsConstructor       // 无参构造
@AllArgsConstructor      // 全参构造
public class Book {
    private String isbn;          // 书号
    private String title;         // 标题
    private String author;        // 作者
    private String publisher;     // 出版社
    private Date publishDate;     // 出版日期
    private double price;         // 价格
    private int stock;            // 库存
}
