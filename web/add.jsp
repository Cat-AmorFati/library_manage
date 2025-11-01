<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="beans.Book, java.math.BigDecimal, java.time.LocalDate" %>
<html>
<head>
    <title>添加图书信息</title>
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
<h2>添加图书信息</h2>
<a href="index.jsp">返回首页</a>

<form method="post">
    <label for="isbn">ISBN：</label>
    <input type="text" id="isbn" name="isbn" placeholder="请输入13位ISBN" required>

    <label for="title">书名：</label>
    <input type="text" id="title" name="title" placeholder="请输入书名" required>

    <label for="author">作者：</label>
    <input type="text" id="author" name="author" placeholder="请输入作者" required>

    <label for="publisher">出版社：</label>
    <input type="text" id="publisher" name="publisher" placeholder="请输入出版社" required>

    <label for="publishDate">出版日期：</label>
    <input type="date" id="publishDate" name="publishDate" required>

    <label for="price">价格（单位：元）：</label>
    <input type="number" id="price" name="price" step="0.01" min="0" required>

    <label for="stock">库存数量：</label>
    <input type="number" id="stock" name="stock" min="0" required>

    <div>
        <button type="submit">添加</button>
        <button type="reset" style="background-color: gray;">重置</button>
    </div>
</form>

<jsp:useBean id="book" class="beans.Book" scope="page" />
<%
    request.setCharacterEncoding("UTF-8");
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String publishDate = request.getParameter("publishDate");
        String price = request.getParameter("price");
        String stock = request.getParameter("stock");

        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setPublishDate(LocalDate.parse(publishDate));
        book.setPrice(new BigDecimal(price));
        book.setStock(Integer.parseInt(stock));

        int result = book.addBook();
        if (result > 0) {
%>
<script>alert("添加成功！"); window.location.href="index.jsp";</script>
<%
} else {
%>
<script>alert("添加失败，请检查输入！");</script>
<%
        }
    }
%>
</body>
</html>
