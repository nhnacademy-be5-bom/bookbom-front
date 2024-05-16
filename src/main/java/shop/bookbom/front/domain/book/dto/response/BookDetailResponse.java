package shop.bookbom.front.domain.book.dto.response;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import shop.bookbom.front.domain.author.dto.AuthorDTO;
import shop.bookbom.front.domain.category.dto.CategoryDTO;
import shop.bookbom.front.domain.file.dto.FileDTO;
import shop.bookbom.front.domain.pointrate.dto.PointRateSimpleInformation;
import shop.bookbom.front.domain.publisher.entity.dto.PublisherSimpleInformation;
import shop.bookbom.front.domain.review.dto.ReviewStatisticsResponse;
import shop.bookbom.front.domain.tag.dto.TagDTO;

@Getter
@NoArgsConstructor
public class BookDetailResponse {
    // 책 상세페이지 조회에 사용하는 책 단건 조회 응답 DTO
    // Book 의 필드 중 view, status 는 상세 페이지에서 사용하지 않으므로 제외
    private Long id;
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
    private Integer stock;
    private PublisherSimpleInformation publisher;
    private PointRateSimpleInformation pointRate;
    private List<AuthorDTO> authors;
    private List<TagDTO> tags;
    private List<CategoryDTO> categories;
    private List<FileDTO> files;
    private ReviewStatisticsResponse reviewStatistics;

    @Builder
    public BookDetailResponse(Long id,
                              String title,
                              String description,
                              String index,
                              LocalDate pubDate,
                              String isbn10,
                              String isbn13,
                              Integer cost,
                              Integer discountCost,
                              Boolean packagable,
                              Integer stock,
                              PublisherSimpleInformation publisher,
                              PointRateSimpleInformation pointRate,
                              List<AuthorDTO> authors,
                              List<TagDTO> tags,
                              List<CategoryDTO> categories,
                              List<FileDTO> files,
                              ReviewStatisticsResponse reviewStatistics) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.index = index;
        this.pubDate = pubDate;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.cost = cost;
        this.discountCost = discountCost;
        this.packagable = packagable;
        this.stock = stock;
        this.publisher = publisher;
        this.pointRate = pointRate;
        this.authors = authors;
        this.tags = tags;
        this.categories = categories;
        this.files = files;
        this.reviewStatistics = reviewStatistics;
    }
}
