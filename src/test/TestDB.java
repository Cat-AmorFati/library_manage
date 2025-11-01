package test;

import util.DBUtil;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class TestDB {
    public static void main(String[] args) {
        // 1️测试连接是否正常
        try (Connection conn = DBUtil.getConnection()) {
            System.out.println("数据库连接成功：" + conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2️测试查询多条记录（queryList）
        System.out.println("\n=== 测试 queryList() ===");
        String sql1 = "SELECT * FROM books";
        List<Map<String,Object>> books = DBUtil.queryList(sql1, null);
        for (Map<String,Object> book : books) {
            System.out.printf("书名：%-20s 作者：%-8s 价格：%.2f 库存：%d\n",
                    book.get("title"),
                    book.get("author"),
                    book.get("price"),
                    book.get("stock"));
        }


        // 3️测试查询单条记录（queryMap）
        System.out.println("\n=== 测试 queryMap() ===");
        String sql2 = "SELECT * FROM books WHERE isbn = ?";
        Map<String,Object> single = DBUtil.queryMap(sql2, new String[]{"9787030303691"});
        System.out.println(single);

        // 4️测试更新操作（executeUpdate）
        System.out.println("\n=== 测试 executeUpdate() ===");
        String sql3 = "UPDATE books SET stock = stock + 1 WHERE isbn = ?";
        int rows = DBUtil.executeUpdate(sql3, new String[]{"9787030303691"});
        System.out.println("受影响的行数：" + rows);
    }
}
