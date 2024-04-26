package shop.bookbom.front.domain.book.dto.response;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.file.dto.FileDTO;
import shop.bookbom.front.domain.pointrate.dto.response.PointRateDTO;
import shop.bookbom.front.domain.publisher.entity.Publisher;
import shop.bookbom.front.domain.review.dto.response.ReviewDTO;
import shop.bookbom.front.domain.tag.dto.TagDTO;
import shop.bookbom.front.review.dto.ReviewStatisticsResponse;

@Getter
@NoArgsConstructor
public class BookMediumResponse {
    private Long id;
    private String title;
    private LocalDate pubDate;
    private Integer cost;
    private Integer discountCost;
    private Publisher publisher;
    private PointRateDTO pointRate;
    private List<AuthorResponse> authors;
    private List<TagDTO> tags;
    private List<FileDTO> files;
    private List<ReviewDTO> reviews;
    private ReviewStatisticsResponse reviewStatistics;
}
