package shop.bookbom.front.domain.review.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private String content;
    private int rate;
}
