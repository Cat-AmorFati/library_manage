package model;

import dao.BookDAO;
import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

public class BookService {

    private final BookDAO dao = new BookDAO();

    // ================= 基础功能 =================

    // 查询全部图书
    public List<Book> list() {
        return dao.getAllBooks();
    }

    // 根据 ISBN 查询
    public Book get(String isbn) {
        return dao.getBookByIsbn(isbn);
    }

    // 添加
    public boolean add(Book b) {
        return dao.addBook(b);
    }

    // 更新
    public boolean update(Book b) {
        return dao.updateBook(b);
    }

    // 删除
    public boolean delete(String isbn) {
        return dao.deleteBook(isbn);
    }

    // ================= 搜索功能 =================
    public List<Book> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            // 关键字为空，返回全部
            return dao.getAllBooks();
        }
        return dao.searchBooks(keyword.trim());
    }

    // ================= 批量导入（Excel） =================

    /**
     * Excel 列顺序：
     * 0 ISBN | 1 书名 | 2 作者 | 3 出版社 | 4 出版日期 | 5 价格 | 6 库存
     */
    public int importBooks(InputStream is) {
        int successCount = 0;

        try {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            // 从第 1 行开始，跳过表头
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                // ISBN（强制按字符串读，避免科学计数法）
                Cell isbnCell = row.getCell(0);
                isbnCell.setCellType(CellType.STRING);
                String isbn = isbnCell.getStringCellValue().trim();
                if (isbn.isEmpty()) continue;

                String title = row.getCell(1).getStringCellValue();
                String author = row.getCell(2).getStringCellValue();
                String publisher = row.getCell(3).getStringCellValue();

                // 出版日期
                Date publishDate = null;
                Cell dateCell = row.getCell(4);
                if (dateCell != null && DateUtil.isCellDateFormatted(dateCell)) {
                    publishDate = new Date(dateCell.getDateCellValue().getTime());
                }

                double price = row.getCell(5).getNumericCellValue();
                int stock = (int) row.getCell(6).getNumericCellValue();

                Book book = new Book(isbn, title, author, publisher,
                        publishDate, price, stock);

                // 是否已存在
                boolean ok;
                Book exist = dao.getBookByIsbn(isbn);
                if (exist != null) {
                    exist.setStock(exist.getStock() + stock);
                    ok = dao.updateBook(exist);
                } else {
                    ok = dao.addBook(book);
                }

                // 只有真正写入成功才统计
                if (ok) {
                    successCount++;
                } else {
                    System.err.println("导入失败 ISBN=" + isbn);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return successCount;
    }
}
