<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, model.Book" %>
<%@ page import="model.UserInfo" %>
<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    if (books == null) books = new ArrayList<>();

    UserInfo user = (UserInfo) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>图书浏览</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        body {
            font-family: "Microsoft YaHei", sans-serif;
            background: linear-gradient(135deg, #e0f7ff, #f8f9fa);
            color: #333;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 85%;
            margin: 50px auto;
            background: #fff;
            border-radius: 10px;
            padding: 40px;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.08);
        }

        h2 {
            color: #007bff;
            border-bottom: 2px solid #007bff;
            padding-bottom: 8px;
        }

        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 25px;
        }

        .btn {
            display: inline-block;
            background: linear-gradient(135deg, #007bff, #0056b3);
            color: white;
            padding: 8px 16px;
            border-radius: 5px;
            text-decoration: none;
            transition: 0.2s;
        }

        .btn:hover {
            background: linear-gradient(135deg, #0056b3, #003d80);
            transform: translateY(-1px);
        }

        .book-list {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .book-item {
            background: #f9f9f9;
            border: 1px solid #e1e1e1;
            border-radius: 8px;
            padding: 15px 20px;
            margin-bottom: 10px;
            transition: 0.2s;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .book-item:hover {
            background: #eaf4ff;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
        }

        .book-info {
            font-size: 15px;
        }

        .price {
            font-weight: bold;
            color: #28a745;
        }

        .empty {
            text-align: center;
            color: #999;
            margin-top: 30px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="top-bar">
        <h2>📖 图书浏览</h2>

        <div>
            <span style="margin-right: 15px; color:#555;">
                👤 当前用户：<strong><%= user.getUsername() %></strong>
            </span>
            <a class="btn" href="logout">退出</a>
        </div>

    </div>

    <% if (books.isEmpty()) { %>
    <p class="empty">暂无图书，请稍后再试。</p>
    <% } else { %>
    <ul class="book-list">
        <% for (Book b : books) { %>
        <li class="book-item">
            <div class="book-info">
                <strong><%= b.getTitle() %>
                </strong><br>
                <small>ISBN: <%= b.getIsbn() %> | 作者: <%= b.getAuthor() %> | 出版社: <%= b.getPublisher() %>
                </small>
            </div>
            <span class="price">￥<%= b.getPrice() %></span>
        </li>
        <% } %>
    </ul>
    <% } %>
</div>

</body>
</html>
