package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. 清除 Session（彻底退出）
        req.getSession().invalidate();

        // 2. 禁止浏览器缓存（防止退出后能按“后退”看到旧页面）
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", 0);

        // 3. 跳转回登录页面
        resp.sendRedirect("ImageLogin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 让 POST 退出与 GET 相同逻辑
        doGet(req, resp);
    }
}
