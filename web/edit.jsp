<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="beans.Book, java.util.*" %>
<html>
<head>
    <title>修改图书信息</title>
    <style>
        body {
            font-family: "Microsoft YaHei", sans-serif;
            background-color: #f9f9f9;
            text-align: center;
        }
        form {
            background: white;
            padding: 20px;
            margin: 40px auto;
            width: 400px;
            box-shadow: 0 0 6px rgba(0, 0, 0, 0.1);
            border-radius: 6px;
        }
        label {
            display: block;
            text-align: left;
            margin-top: 10px;
            margin-bottom: 4px;
            font-weight: bold;
        }
        input {
            width: 95%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            padding: 8px 16px;
            margin: 10px 5px;
            border: none;
            border-radius: 4px;
            background-color: #007bff;
            color: white;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>
<h2>修改图书信息</h2>
<a href="index.jsp">返回首页</a>

<jsp:useBean id="book" class="beans.Book" scope="page" />
<jsp:setProperty name="book" property="isbn" param="isbn" />

<%
    Map<String, Object> data = book.getBook();
    if (data != null) {
%>

<form action="edit_do.jsp" method="post">
    <label for="isbn">ISBN：</label>
    <input type="text" id="isbn" name="isbn" value="<%= data.get("isbn") %>" readonly>

    <label for="title">书名：</label>
    <input type="text" id="title" name="title" value="<%= data.get("title") %>" required>

    <label for="author">作者：</label>
    <input type="text" id="author" name="author" value="<%= data.get("author") %>" required>

    <label for="publisher">出版社：</label>
    <input type="text" id="publisher" name="publisher" value="<%= data.get("publisher") %>" required>

    <label for="publishDate">出版日期：</label>
    <input type="date" id="publishDate" name="publishDate" value="<%= data.get("publish_date") %>" required>

    <label for="price">价格（单位：元）：</label>
    <input type="number" id="price" name="price" step="0.01" min="0" value="<%= data.get("price") %>" required>

    <label for="stock">库存数量：</label>
    <input type="number" id="stock" name="stock" min="0" value="<%= data.get("stock") %>" required>

    <div>
        <button type="submit">保存修改</button>
        <button type="reset" style="background-color: gray;">重置</button>
    </div>
</form>

<%
} else {
%>
<p>未找到该图书信息，请返回 <a href="index.jsp">首页</a>。</p>
<%
    }
%>
</body>
</html>

