package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Book;
import model.BookService;
import model.UserInfo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

@MultipartConfig
@WebServlet("/book")
public class BookController extends HttpServlet {

    private final BookService bookService = new BookService();

    // ======================= GET 请求 =======================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String action = req.getParameter("action");

        // 搜索
        if ("search".equals(action)) {
            String keyword = req.getParameter("keyword");

            List<Book> list = bookService.search(keyword);
            req.setAttribute("books", list);

            UserInfo user = (UserInfo) req.getSession().getAttribute("user");
            if (user != null && "admin".equals(user.getRole())) {
                req.getRequestDispatcher("list.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("userlist.jsp").forward(req, resp);
            }
            return;
        }

        // 1 默认：图书列表
        if (action == null || "list".equals(action)) {
            List<Book> list = bookService.list();
            req.setAttribute("books", list);

            UserInfo user = (UserInfo) req.getSession().getAttribute("user");
            if (user != null && "admin".equals(user.getRole())) {
                req.getRequestDispatcher("list.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("userlist.jsp").forward(req, resp);
            }
            return;
        }

        // 2 编辑页面
        if ("editForm".equals(action)) {
            String isbn = req.getParameter("isbn");
            Book b = bookService.get(isbn);
            req.setAttribute("book", b);
            req.getRequestDispatcher("edit.jsp").forward(req, resp);
            return;
        }

        // 3 删除图书
        if ("delete".equals(action)) {
            String isbn = req.getParameter("isbn");
            boolean ok = bookService.delete(isbn);
            req.getRequestDispatcher(ok ? "success.jsp" : "failure.jsp")
                    .forward(req, resp);
            return;
        }

        // 4 跳转到批量导入页面
        if ("import".equals(action)) {
            req.getRequestDispatcher("import.jsp").forward(req, resp);
        }
    }

    // ======================= POST 请求 =======================
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String action = req.getParameter("action");

        // 5 批量导入处理（必须最先判断）
        if ("importDo".equals(action)) {
            importDo(req, resp);
            return;
        }

        // ===== 公共表单字段 =====
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
                publishDate = Date.valueOf(dateStr);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        double price = 0;
        int stock = 0;
        try {
            if (priceStr != null) price = Double.parseDouble(priceStr);
            if (stockStr != null) stock = Integer.parseInt(stockStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // 6 添加图书
        if ("add".equals(action)) {
            Book b = new Book(isbn, title, author, publisher,
                    publishDate, price, stock);
            boolean ok = bookService.add(b);
            req.getRequestDispatcher(ok ? "success.jsp" : "failure.jsp")
                    .forward(req, resp);
            return;
        }

        // 7 更新图书
        if ("update".equals(action)) {
            Book b = new Book(isbn, title, author, publisher,
                    publishDate, price, stock);
            boolean ok = bookService.update(b);
            req.getRequestDispatcher(ok ? "success.jsp" : "failure.jsp")
                    .forward(req, resp);
        }
    }

    // ======================= 批量导入方法 =======================
    private void importDo(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        Part part = req.getPart("excelFile");
        InputStream is = part.getInputStream();

        int count = bookService.importBooks(is);

        req.setAttribute("msg", "成功导入 " + count + " 条图书记录");
        req.getRequestDispatcher("success.jsp").forward(req, resp);
    }
}
