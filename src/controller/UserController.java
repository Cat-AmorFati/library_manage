package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserInfo;
import model.UserService;

import java.io.IOException;

@WebServlet("/user")
public class UserController extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String action = req.getParameter("action");

        // 只允许登录操作
        if (!"login".equals(action)) {
            resp.sendRedirect("ImageLogin.jsp");
            return;
        }

        // ---------------- 获取表单参数 ----------------
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String inputCaptcha = req.getParameter("captcha");

        // ---------------- 获取 session 中验证码 ----------------
        String sessionCaptcha = (String) req.getSession().getAttribute("checkcode");

        // ========== 验证码校验 ==========
        if (inputCaptcha == null || sessionCaptcha == null ||
                !sessionCaptcha.equalsIgnoreCase(inputCaptcha.trim())) {

            req.setAttribute("error", "验证码错误！");
            req.getRequestDispatcher("ImageLogin.jsp").forward(req, resp);
            return;
        }

        // 验证码一次性使用：立即移除
        req.getSession().removeAttribute("checkcode");

        // ========== 用户名密码校验 ==========
        UserInfo user = userService.login(username, password);

        if (user == null) {
            req.setAttribute("error", "用户名或密码不正确");
            req.getRequestDispatcher("ImageLogin.jsp").forward(req, resp);
            return;
        }

        // ========== 登录成功 ==========
        req.getSession(true).setAttribute("user", user);
        resp.sendRedirect("book?action=list");
    }
}
