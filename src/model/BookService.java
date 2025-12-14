package model;

import dao.BookDAO;

import java.util.List;

public class BookService {
    private final BookDAO dao = new BookDAO();

    public List<Book> list() {
        return dao.getAllBooks();
    }

    public Book get(String isbn) {
        return dao.getBookByIsbn(isbn);
    }

    public boolean add(Book b) {
        return dao.addBook(b);
    }

    public boolean update(Book b) {
        return dao.updateBook(b);
    }

    public boolean delete(String isbn) {
        return dao.deleteBook(isbn);
    }
}
