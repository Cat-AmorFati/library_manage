package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishDate;
    private BigDecimal price;
    private int stock;
}
