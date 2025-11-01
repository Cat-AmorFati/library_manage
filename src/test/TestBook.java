package test;

import beans.Book;
import java.util.*;

public class TestBook {
    public static void main(String[] args) {
        // 1️测试添加图书
        Book b = new Book("9787111122334", "Java核心技术", "李华", "电子工业出版社", "2020-05-01", 88.0, 20);
        System.out.println("添加图书结果：" + b.addBook());

        // 2️测试查询单本图书
        b.setIsbn("9787111122334");
        Map<String,Object> book = b.getBook();
        System.out.println("查询结果：" + book);

        // 3️测试修改图书信息
        b.setPrice(90.0);
        b.setStock(25);
        System.out.println("修改图书结果：" + b.updateBook());

        // 4️测试查询所有图书
        List<Map<String,Object>> list = b.getAllBooks();
        for (Map<String,Object> row : list) {
            System.out.println(row);
        }

        // 5️测试删除图书
        System.out.println("删除结果：" + b.delBook());
    }
}

