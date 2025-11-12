package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.UserInfo;

public class AuthFilter implements Filter {
    @Override public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        UserInfo user = (session==null)? null : (UserInfo) session.getAttribute("user");
        if (user == null) {
            ((HttpServletResponse)response).sendRedirect(req.getContextPath()+"/ImageLogin.jsp");
            return;
        }
        chain.doFilter(request, response);
    }
}

