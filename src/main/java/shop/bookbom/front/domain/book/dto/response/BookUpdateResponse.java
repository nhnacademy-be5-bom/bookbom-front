package shop.bookbom.front.domain.book.dto.response;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import shop.bookbom.front.domain.author.dto.AuthorDTO;

/**
 * packageName    : shop.bookbom.front.domain.book.dto.response
 * fileName       : BookUpdateResponse
 * author         : UuLaptop
 * date           : 2024-05-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-03        UuLaptop       최초 생성
 */

@Getter
public class BookUpdateResponse {
    private Long id;
    private String thumbnail;
    private String title;
    private String description;
    private String index;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pubDate;
    private String isbn10;
    private String isbn13;
    private Integer cost;
    private Integer discountCost;
    private Boolean packagable;
    private String status;
    private Integer stock;

    private Long publisherId;
    private String publisherName;
    private List<AuthorDTO> author;
    private List<String> tags;
    private List<String> categories;
}
