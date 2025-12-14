<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.Book" %>
<%
    Book b = (Book) request.getAttribute("book");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>编辑图书</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        body {
            font-family: "Microsoft YaHei", sans-serif;
            background: linear-gradient(135deg, #e0f7ff, #f8f9fa);
            color: #333;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background: #fff;
            padding: 40px 50px;
            border-radius: 10px;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
            width: 420px;
            animation: fadeIn .5s ease;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        h2 {
            color: #007bff;
            margin-bottom: 25px;
            text-align: center;
        }

        form p {
            margin: 15px 0;
            font-weight: bold;
        }

        input[type="text"], input[type="number"], input[type="date"] {
            width: 100%;
            padding: 10px;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 14px;
            margin-top: 6px;
            transition: 0.2s;
        }

        input:focus {
            border-color: #007bff;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.3);
            outline: none;
        }

        input[readonly] {
            background: #f7f7f7;
            cursor: not-allowed;
        }

        button {
            width: 100%;
            background: linear-gradient(135deg, #007bff, #0056b3);
            color: white;
            border: none;
            padding: 12px;
            border-radius: 6px;
            font-size: 15px;
            cursor: pointer;
            margin-top: 15px;
            transition: 0.2s;
        }

        button:hover {
            background: linear-gradient(135deg, #0056b3, #004080);
            transform: scale(1.02);
        }

        .footer {
            text-align: center;
            margin-top: 15px;
        }

        .footer a {
            color: #007bff;
            text-decoration: none;
        }

        .footer a:hover {
            text-decoration: underline;
        }

        .error {
            text-align: center;
            color: red;
            font-size: 14px;
        }
    </style>
</head>
<body>

<div class="container">
    <% if (b == null) { %>
    <h2>❌ 未找到该图书</h2>
    <p class="error">可能该图书已被删除或编号错误。</p>
    <div class="footer"><a href="book?action=list">返回列表</a></div>

    <% } else { %>
    <h2>✏️ 编辑图书信息</h2>

    <form action="book" method="post">
        <input type="hidden" name="action" value="update">

        <p>ISBN（只读）：
            <input name="isbn" value="<%= b.getIsbn() %>" readonly>
        </p>

        <p>标题：
            <input name="title" value="<%= b.getTitle() %>" required>
        </p>

        <p>作者：
            <input name="author" value="<%= b.getAuthor() %>" required>
        </p>

        <p>出版社：
            <input name="publisher" value="<%= b.getPublisher() %>" required>
        </p>

        <p>出版日期：
            <input name="publish_date" type="date"
                   value="<%= (b.getPublishDate() != null ? b.getPublishDate().toString() : "") %>">
        </p>

        <p>价格：
            <input name="price" type="number" step="0.01" value="<%= b.getPrice() %>" required>
        </p>

        <p>库存：
            <input name="stock" type="number" min="0" value="<%= b.getStock() %>" required>
        </p>

        <button type="submit">保存修改</button>
    </form>

    <div class="footer">
        <a href="book?action=list">⬅ 取消并返回</a>
    </div>
    <% } %>
</div>

</body>
</html>
