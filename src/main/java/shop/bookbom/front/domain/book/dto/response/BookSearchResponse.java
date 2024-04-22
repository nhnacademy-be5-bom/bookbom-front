package shop.bookbom.front.domain.book.dto.response;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookSearchResponse {
    private Long id;
    private String thumbnail;
    private String title;
    private List<AuthorResponse> author;
    private Long publisherId;
    private String publisherName;
    private LocalDate pubDate;
    private int price;
    private int discountPrice;
    private double reviewRating;
    private long reviewCount;
}
