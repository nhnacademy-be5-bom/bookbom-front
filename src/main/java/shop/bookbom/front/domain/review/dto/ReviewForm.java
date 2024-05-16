package shop.bookbom.front.domain.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class ReviewForm {
    private Long bookId;
    private Long orderId;
    private String type;
    private int rating;
    private String content;
    private MultipartFile image;
}
