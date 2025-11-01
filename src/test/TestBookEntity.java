package test;

import entity.BookEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestBookEntity {
    public static void main(String[] args) {
        BookEntity book = new BookEntity(
                "9787030303691",
                "环境信息系统工程",
                "王桥",
                "科学出版社",
                LocalDate.parse("2011-08-01"),
                new BigDecimal("113.00"),
                20
        );

        System.out.println(book);
        System.out.println("出版年份: " + book.getPublishDate().getYear());
        System.out.println("库存总价: " + book.getPrice().multiply(new BigDecimal(book.getStock())));
    }
}