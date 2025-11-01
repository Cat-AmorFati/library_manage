<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="beans.Book, java.util.*" %>
<html>
<head>
    <title>图书管理系统</title>
    <style>
        body {
            font-family: "Microsoft YaHei", sans-serif;
            text-align: center;
            background-color: #f9f9f9;
        }
        h2 {
            margin-top: 40px;
            color: #333;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        table {
            border-collapse: collapse;
            margin: 30px auto;
            width: 85%;
            background: white;
            box-shadow: 0 0 5px rgba(0,0,0,0.1);
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px 12px;
        }
        th {
            background-color: #f2f2f2;
        }
        td {
            text-align: center;
        }
        .add-link {
            margin-top: 20px;
            display: inline-block;
            font-weight: bold;
        }
    </style>
</head>

<body>
<h2>图书管理系统</h2>
<a class="add-link" href="add.jsp">添加图书信息</a>

<table>
    <tr>
        <th>ISBN</th>
        <th>书名</th>
        <th>作者</th>
        <th>出版社</th>
        <th>出版日期</th>
        <th>价格</th>
        <th>管理</th>
    </tr>

    <jsp:useBean id="book" class="beans.Book" scope="page" />
    <%
        List<Map<String, Object>> list = book.getAllBooks();
        if (list != null && !list.isEmpty()) {
            for (Map<String, Object> row : list) {
    %>
    <tr>
        <td><%= row.get("isbn") %></td>
        <td><%= row.get("title") %></td>
        <td><%= row.get("author") %></td>
        <td><%= row.get("publisher") %></td>
        <td><%= row.get("publish_date") %></td>
        <td><%= row.get("price") %></td>
        <td>
            <a href="edit.jsp?isbn=<%= row.get("isbn") %>">修改</a>
            &nbsp;
            <a href="del.jsp?isbn=<%= row.get("isbn") %>">删除</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="7">暂无图书数据</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>

