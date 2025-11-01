package test;

import beans.Book;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestUpdateBook {
    public static void main(String[] args) {
        // 假设这本书已经存在于数据库（之前 addBook 插入的那条）
        Book b = new Book(
                "9787111122334",           // isbn 不变
                "Java核心技术（修订版）",     // 修改标题
                "李华",                      // 作者不变
                "电子工业出版社",              // 出版社不变
                LocalDate.parse("2021-05-01"), // 改出版日期
                new BigDecimal("98.00"),       // 改价格
                25                              // 改库存
        );

        int result = b.updateBook();
        if (result > 0) {
            System.out.println("修改成功！");
        } else {
            System.out.println("修改失败！");
        }
    }
}
