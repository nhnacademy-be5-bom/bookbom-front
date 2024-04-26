package shop.bookbom.front.domain.book.dto.response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import shop.bookbom.front.domain.file.dto.FileDTO;
import shop.bookbom.front.domain.pointrate.dto.PointRateSimpleInformation;
import shop.bookbom.front.domain.publisher.entity.dto.PublisherSimpleInformation;
import shop.bookbom.front.domain.tag.dto.TagDTO;
import shop.bookbom.front.review.dto.ReviewResponse;
import shop.bookbom.front.review.dto.ReviewStatisticsResponse;

/**
 * packageName    : shop.bookbom.front.domain.book.dto.response
 * fileName       : BookCategoryResponse
 * author         : UuLaptop
 * date           : 2024-04-24
 * description    : BookMediumResponse
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-24        UuLaptop       최초 생성
 */
@Getter
@NoArgsConstructor
public class BookCategoryResponse {
    private Long id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pubDate;
    private Integer cost;
    private Integer discountCost;
    private PublisherSimpleInformation publisher;
    private PointRateSimpleInformation pointRate;
    private List<AuthorResponse> authors = new ArrayList<>();
    private List<TagDTO> tags = new ArrayList<>();
    private List<FileDTO> files = new ArrayList<>();
    private List<ReviewResponse> reviews = new ArrayList<>();
    // 리뷰 평점, 리뷰 총갯수
    private ReviewStatisticsResponse reviewStatistics;
}
