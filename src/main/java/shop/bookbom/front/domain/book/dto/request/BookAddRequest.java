package shop.bookbom.front.domain.book.dto.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import shop.bookbom.front.domain.author.dto.AuthorSimpleInfo;
import shop.bookbom.front.domain.book.entity.BookStatus;

@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BookAddRequest implements Serializable {
    private String title;
    private List<String> categories = new ArrayList<>();
    private List<String> tags = new ArrayList<>();
    private List<AuthorSimpleInfo> authors = new ArrayList<>();
    private String publisher;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pubDate;

    private String description;
    private String index;
    private String isbn10;
    private String isbn13;
    private Integer cost;
    private Integer discountCost;
    private Boolean packagable;
    private BookStatus status;
    private Integer stock;
}
