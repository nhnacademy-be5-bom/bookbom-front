package shop.bookbom.front.domain.book.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import shop.bookbom.front.domain.book.entity.BookStatus;
import shop.bookbom.front.domain.publisher.entity.Publisher;

@Getter
public class BookAddRequest {
    // 관리자 책 등록에 사용하는 책 등록 요청 DTO
    // Book 의 필드 중 id, view 는 등록 페이지에서 사용하지 않으므로 제외
    // front 에서 사용하는 버전에는 작가 필드를 스트링으로 받음

    @JsonIgnore
    private MultipartFile thumbnail;
    private String title;

    private String category_depth1;
    private String category_depth2;
    private String category_depth3;

    private List<String> tags;
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

    public BookAddRequest(MultipartFile thumbnail,
                          String title,
                          String category_depth1,
                          String category_depth2,
                          String category_depth3,
                          List<String> tags,
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
        this.category_depth1 = category_depth1;
        this.category_depth2 = category_depth2;
        this.category_depth3 = category_depth3;
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
