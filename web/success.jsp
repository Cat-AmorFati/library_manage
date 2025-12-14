<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>操作成功</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(135deg, #4facfe, #00f2fe);
            font-family: "Microsoft YaHei", sans-serif;
            color: #333;
            margin: 0;
        }

        .box {
            background: #fff;
            padding: 40px 60px;
            border-radius: 10px;
            box-shadow: 0 8px 25px rgba(0, 0, 0, .15);
            text-align: center;
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
            margin-bottom: 15px;
        }

        a.btn {
            display: inline-block;
            margin-top: 15px;
            background: #007bff;
            color: #fff;
            padding: 10px 20px;
            border-radius: 6px;
            text-decoration: none;
            transition: .2s;
        }

        a.btn:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
<div class="box">
    <h2>✅ 操作成功</h2>
    <p><%= request.getAttribute("message") == null ? "" : request.getAttribute("message") %>
    </p>
    <a href="book?action=list" class="btn">返回列表</a>
</div>
</body>
</html>
