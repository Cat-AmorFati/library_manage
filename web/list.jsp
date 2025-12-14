<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>图书管理后台</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        body {
            font-family: "Microsoft YaHei", sans-serif;
            background: linear-gradient(135deg, #e0f7ff, #f8f9fa);
            color: #333;
            margin: 0;
            padding: 0;
        }

        /* 顶部导航栏 */
        .navbar {
            background: #007bff;
            color: white;
            padding: 12px 40px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .navbar a {
            color: white;
            text-decoration: none;
            margin: 0 10px;
            font-weight: bold;
        }

        .navbar a:hover {
            text-decoration: underline;
        }

        .container {
            width: 90%;
            margin: 30px auto;
            background: #fff;
            border-radius: 10px;
            padding: 25px 30px;
            box-shadow: 0 6px 18px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #007bff;
            margin-bottom: 15px;
        }

        .toolbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }

        .toolbar input[type="text"] {
            width: 250px;
            padding: 6px 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .btn {
            display: inline-block;
            background: linear-gradient(135deg, #007bff, #0056b3);
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 5px;
            text-decoration: none;
            transition: 0.2s;
            font-size: 14px;
        }

        .btn:hover {
            background: linear-gradient(135deg, #0056b3, #003d80);
            transform: translateY(-1px);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #e8f3ff;
            transition: 0.2s ease-in-out;
        }

        .footer {
            text-align: right;
            margin-top: 10px;
            color: #666;
        }

        .checkbox {
            width: 18px;
            height: 18px;
        }
    </style>
</head>
<body>

<!-- 顶部菜单栏 -->
<div class="navbar">
    <div class="nav-left">
        <a href="book?action=list">📚 图书管理系统</a>
        <a href="add.html">➕ 添加图书</a>
        <a href="book?action=import">📂 批量导入</a>
    </div>
    <div class="nav-right">
        <a href="logout">🚪 退出</a>
    </div>
</div>

<div class="container">
    <div class="toolbar">
        <form action="book" method="get" style="margin:0;">
            <input type="hidden" name="action" value="search">
            <input type="text" name="keyword" placeholder="输入书名或作者搜索">
            <button class="btn" type="submit">🔍 搜索</button>
        </form>

        <div>
            <button class="btn" onclick="batchDelete()">🗑️ 批量删除</button>
        </div>
    </div>

    <form id="bookForm" action="book?action=batchDelete" method="post">
        <table>
            <thead>
            <tr>
                <th><input type="checkbox" id="selectAll" class="checkbox"></th>
                <th>ISBN</th>
                <th>标题</th>
                <th>作者</th>
                <th>出版社</th>
                <th>出版日期</th>
                <th>价格</th>
                <th>库存</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="b" items="${books}">
                <tr>
                    <td><input type="checkbox" name="selectedIsbn" value="${b.isbn}" class="checkbox"></td>
                    <td>${b.isbn}</td>
                    <td>${b.title}</td>
                    <td>${b.author}</td>
                    <td>${b.publisher}</td>
                    <td>${b.publishDate}</td>
                    <td>￥${b.price}</td>
                    <td>${b.stock}</td>
                    <td>
                        <a class="btn" href="book?action=editForm&isbn=${b.isbn}">✏️ 编辑</a>
                        <a class="btn" href="book?action=delete&isbn=${b.isbn}" onclick="return confirm('确认删除？');">🗑️
                            删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>

    <p class="footer">总数：<c:out value="${fn:length(books)}"/></p>
</div>

<script>
    // 全选复选框
    document.getElementById('selectAll').addEventListener('change', function () {
        const checkboxes = document.querySelectorAll('input[name="selectedIsbn"]');
        checkboxes.forEach(cb => cb.checked = this.checked);
    });

    // 批量删除确认
    function batchDelete() {
        const selected = document.querySelectorAll('input[name="selectedIsbn"]:checked');
        if (selected.length === 0) {
            alert("请先选择要删除的书籍！");
            return;
        }
        if (confirm("确定要删除选中的 " + selected.length + " 本书吗？")) {
            document.getElementById("bookForm").submit();
        }
    }
</script>

</body>
</html>
