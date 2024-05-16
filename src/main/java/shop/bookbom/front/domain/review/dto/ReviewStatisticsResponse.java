package shop.bookbom.front.domain.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : shop.bookbom.front.review.dto
 * fileName       : ReviewStatisticsResponse
 * author         : UuLaptop
 * date           : 2024-04-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-23        UuLaptop       최초 생성
 */
@Getter
@NoArgsConstructor
public class ReviewStatisticsResponse {
    private Integer totalReviewCount;
    private Double averageReviewRate;

    @Builder
    public ReviewStatisticsResponse(Integer totalReviewCount, Double averageReviewRate) {
        this.totalReviewCount = totalReviewCount;
        this.averageReviewRate = averageReviewRate;
    }
}
