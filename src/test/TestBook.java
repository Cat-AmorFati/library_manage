package test;

import beans.Book;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestBook {
    public static void main(String[] args) {
        // 测试添加图书
        Book b = new Book(
                "9787111122334",
                "Java核心技术",
                "李华",
                "电子工业出版社",
                LocalDate.parse("2020-05-01"),
                new BigDecimal("88.00"),
                20
        );

        System.out.println("添加图书结果：" + b.addBook());
    }
}