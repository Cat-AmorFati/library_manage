package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Book;
import model.BookService;
import model.UserInfo;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/book")
public class BookController extends HttpServlet {
    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");

        // 1️默认显示所有图书
        if (action == null || "list".equals(action)) {
            List<Book> list = bookService.list();
            req.setAttribute("books", list);

            // 根据用户角色跳转不同界面
            UserInfo user = (UserInfo) req.getSession().getAttribute("user");
            if (user != null && "admin".equals(user.getRole())) {
                req.getRequestDispatcher("list.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("userlist.jsp").forward(req, resp);
            }
            return;
        }

        // 2️编辑表单页：根据 ISBN 查询并回显数据
        if ("editForm".equals(action)) {
            String isbn = req.getParameter("isbn");
            Book b = bookService.get(isbn);
            req.setAttribute("book", b);
            req.getRequestDispatcher("edit.jsp").forward(req, resp);
            return;
        }

        // 3️删除图书
        if ("delete".equals(action)) {
            String isbn = req.getParameter("isbn");
            boolean ok = bookService.delete(isbn);
            req.getRequestDispatcher(ok ? "success.jsp" : "failure.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");

        // 公共部分：获取表单字段
        String isbn = req.getParameter("isbn");
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String publisher = req.getParameter("publisher");
        String dateStr = req.getParameter("publish_date");
        String priceStr = req.getParameter("price");
        String stockStr = req.getParameter("stock");

        Date publishDate = null;
        if (dateStr != null && !dateStr.isEmpty()) {
            try {
                publishDate = Date.valueOf(dateStr); // yyyy-MM-dd
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        double price = 0.0;
        int stock = 0;
        try {
            if (priceStr != null) price = Double.parseDouble(priceStr);
            if (stockStr != null) stock = Integer.parseInt(stockStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // 4️添加图书
        if ("add".equals(action)) {
            Book b = new Book(isbn, title, author, publisher, publishDate, price, stock);
            boolean ok = bookService.add(b);
            req.getRequestDispatcher(ok ? "success.jsp" : "failure.html").forward(req, resp);
            return;
        }

        // 5️更新图书
        if ("update".equals(action)) {
            Book b = new Book(isbn, title, author, publisher, publishDate, price, stock);
            boolean ok = bookService.update(b);
            req.getRequestDispatcher(ok ? "success.jsp" : "failure.html").forward(req, resp);
        }
    }
}
