package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        // ========= 必须放行的路径（公开资源） =========
        boolean isPublic =
                uri.endsWith("ImageLogin.jsp") ||           // 登录页 JSP
                        uri.endsWith("CodeServlet") ||              // 验证码 Servlet
                        uri.endsWith("/logout") ||                  // 退出登录（重要）
                        uri.contains("/css/") ||                    // 静态资源
                        uri.contains("/js/") ||
                        uri.contains("/images/") ||
                        uri.endsWith(".png") ||                     // 图片资源 (*.png)
                        uri.endsWith(".jpg") ||                     // 图片资源 (*.jpg)
                        uri.endsWith("favicon.ico") ||              // 浏览器图标
                        (uri.endsWith("/user") && "login".equals(req.getParameter("action"))); // 登录请求

        if (isPublic) {
            chain.doFilter(request, response);
            return;
        }

        // ========= 登录验证逻辑 =========
        HttpSession session = req.getSession(false);
        Object user = (session == null ? null : session.getAttribute("user"));

        if (user == null) {
            // 未登录，跳到登录页
            resp.sendRedirect(req.getContextPath() + "/ImageLogin.jsp");
            return;
        }

        // 已登录，放行
        chain.doFilter(request, response);
    }
}
