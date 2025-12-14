<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>图书管理登录</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        body {
            font-family: "Microsoft YaHei", sans-serif;
            background: linear-gradient(135deg, #007bff, #6c63ff);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-box {
            background: #fff;
            padding: 40px 45px;
            border-radius: 10px;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
            width: 360px;
            text-align: center;
            animation: fadeIn 0.6s ease;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-15px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        h2 {
            color: #007bff;
            margin-bottom: 25px;
        }

        form p {
            text-align: left;
            margin: 10px 0;
        }

        input[type="text"], input[type="password"], input[name="captcha"] {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 14px;
        }

        input:focus {
            outline: none;
            border-color: #007bff;
            box-shadow: 0 0 6px rgba(0, 123, 255, 0.3);
        }

        button {
            width: 100%;
            background: linear-gradient(135deg, #007bff, #0056b3);
            color: white;
            padding: 10px;
            font-size: 15px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            margin-top: 10px;
            transition: 0.2s;
        }

        button:hover {
            background: linear-gradient(135deg, #0056b3, #004080);
            transform: translateY(-1px);
        }

        .captcha-box {
            display: flex;
            align-items: center;
            gap: 10px;
            margin-top: 6px;
        }

        .error {
            color: red;
            margin-top: 15px;
            font-size: 13px;
        }
    </style>
</head>
<body>

<div class="login-box">
    <h2>📚 图书管理系统登录</h2>

    <form action="user" method="post">
        <input type="hidden" name="action" value="login">

        <p>用户名：
            <input type="text" name="username" required>
        </p>

        <p>密　码：
            <input type="password" name="password" required>
        </p>

        <p>验证码：</p>
        <div class="captcha-box">
            <input type="text" name="captcha" placeholder="请输入验证码" required>

            <!-- 关键：验证码图片 -->
            <img src="CodeServlet"
                 onclick="this.src='CodeServlet?'+Math.random();"
                 title="点击刷新"
                 style="cursor:pointer; height:38px;">
        </div>

        <button type="submit">登录</button>
    </form>

    <p class="error">
        <%= request.getAttribute("error") == null ? "" : request.getAttribute("error") %>
    </p>
</div>

</body>
</html>
