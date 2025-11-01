<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="util.DBUtil, java.sql.*" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>数据库测试</title>
</head>
<body style="font-family: Microsoft YaHei; background: #f9f9f9; padding: 20px;">
<h2>数据库连接测试结果</h2>
<%
    try (Connection conn = DBUtil.getConnection()) {
        out.println("<p style='color:green;'>数据库连接成功: " + conn + "</p>");
        ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) FROM books");
        if (rs.next()) {
            out.println("<p style='color:blue;'>books 表记录数: " + rs.getInt(1) + "</p>");
        }
    } catch (Exception e) {
        out.println("<p style='color:red;'>连接失败: " + e.getMessage() + "</p>");
    }
%>
</body>
</html>
