package test;

import beans.Book;

import java.util.Map;

public class TestGetBook {
    public static void main(String[] args) {
        Book b = new Book();
        b.setIsbn("9787111122334"); // 改成你刚插入的 ISBN

        Map<String, Object> result = b.getBook();

        if (result != null) {
            System.out.println(" 查询成功！");
            System.out.println(result);
        } else {
            System.out.println("❌ 没有找到该图书。");
        }
    }
}
