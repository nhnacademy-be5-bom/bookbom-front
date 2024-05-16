package shop.bookbom.front.domain.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : shop.bookbom.front.review.dto
 * fileName       : ReviewResponse
 * author         : UuLaptop
 * date           : 2024-04-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-24        UuLaptop       최초 생성
 */
@Getter
@NoArgsConstructor
public class ReviewResponse {
    private Long id;
    private int rate;
    private String content;
}
