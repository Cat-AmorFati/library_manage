package test;

import beans.Book;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestAddBook {
    public static void main(String[] args) {
        Book book = new Book(
                "9787302242246",
                "环境保护与可持续发展",
                "曲向荣",
                "清华大学出版社",
                LocalDate.parse("2023-06-01"),
                new BigDecimal("32.00"),
                30
        );

        int result = book.addBook();
        if (result > 0) {
            System.out.println("图书添加成功！");
        } else {
            System.out.println("图书添加失败。");
        }
    }
}