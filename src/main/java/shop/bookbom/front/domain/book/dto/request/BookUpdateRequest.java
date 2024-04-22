package shop.bookbom.front.domain.book.dto.request;

import java.io.File;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import shop.bookbom.front.domain.book.entity.BookStatus;
import shop.bookbom.front.domain.publisher.entity.Publisher;

@Getter
public class BookUpdateRequest {
    // 관리자 책 수정에 사용하는 책 수정 요청 DTO
    // Book 의 필드 중 id, view 는 등록 페이지에서 사용하지 않으므로 제외

    private File thumbnail;
    private String title;
    private String categories;
    private String tags;
    private String authors;
    private Publisher publisher;
    
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

    @Builder

    public BookUpdateRequest(File thumbnail,
                             String title,
                             String categories,
                             String tags,
                             String authors,
                             String description,
                             String index,
                             LocalDate pubDate,
                             String isbn10,
                             String isbn13,
                             Integer cost,
                             Integer discountCost,
                             Boolean packagable,
                             BookStatus status,
                             Integer stock,
                             Publisher publisher) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.categories = categories;
        this.tags = tags;
        this.authors = authors;
        this.description = description;
        this.index = index;
        this.pubDate = pubDate;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.cost = cost;
        this.discountCost = discountCost;
        this.packagable = packagable;
        this.status = status;
        this.stock = stock;
        this.publisher = publisher;
    }
}
