package shop.bookbom.front.domain.review.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookReviewResponse {
    private Long id;
    private String nickname;
    private LocalDateTime createdAt;
    private List<String> images;
    private String content;
    private int rate;
}
