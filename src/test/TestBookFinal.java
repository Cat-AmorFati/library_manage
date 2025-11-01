package test;

import beans.Book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TestBookFinal {
    public static void main(String[] args) {
        // 1️新增一本书
        Book b1 = new Book(
                "9787564123456",
                "Java从入门到精通",
                "王刚",
                "清华大学出版社",
                LocalDate.parse("2022-03-15"),
                new BigDecimal("66.00"),
                18
        );
        System.out.println("添加图书结果：" + b1.addBook());

        // 2️查询所有图书
        System.out.println("所有图书：");
        List<Map<String, Object>> list = b1.getAllBooks();
        for (Map<String, Object> row : list) {
            System.out.println(row);
        }

        // 3️删除刚插入的这本书
        b1.setIsbn("9787564123456");
        System.out.println("删除结果：" + b1.delBook());
    }
}
