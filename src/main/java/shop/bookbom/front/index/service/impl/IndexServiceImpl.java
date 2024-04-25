package shop.bookbom.front.index.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.book.dto.response.BookMediumResponse;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;
import shop.bookbom.front.domain.review.dto.response.ReviewDTO;
import shop.bookbom.front.index.adapter.IndexAdapter;
import shop.bookbom.front.index.service.IndexService;

@Service
@RequiredArgsConstructor
public class IndexServiceImpl implements IndexService {
    private final IndexAdapter indexAdapter;

    @Override
    public Page<BookSearchResponse> mainLatestBooks(Pageable pageable) {
        Page<BookMediumResponse> responses = indexAdapter.mainLatestBooks(pageable);
        return responses.map(this::getBookSearchResponse);
    }

    @Override
    public Page<BookSearchResponse> mainBestBooks(Pageable pageable) {
        Page<BookMediumResponse> responses = indexAdapter.mainBestBooks(pageable);
        return responses.map(this::getBookSearchResponse);
    }

    /**
     * BookMediumResponse를 BookSearchResponse로 변환합니다.
     *
     * @param book 변환할 BookMediumResponse
     * @return 변환된 BookSearchResponse
     */
    private BookSearchResponse getBookSearchResponse(BookMediumResponse book) {
        double averageReviewRate = book.getReviews().stream()
                .mapToDouble(ReviewDTO::getRate)
                .average().orElse(0);
        averageReviewRate = Math.round(averageReviewRate * 10.0) / 10.0;
        int totalReviewCount = book.getReviews().size();
        return BookSearchResponse.builder()
                .id(book.getId())
                .thumbnail(Objects.requireNonNull(
                        book.getFiles().stream().filter(f -> f.getExtension().equals("img")).findFirst()
                                .orElse(null)).getUrl())
                .title(book.getTitle())
                .author(book.getAuthors())
                .publisherName(book.getPublisher().getName())
                .pubDate(book.getPubDate())
                .price(book.getCost())
                .discountPrice(book.getDiscountCost())
                .reviewRating(averageReviewRate)
                .reviewCount(totalReviewCount)
                .build();
    }
}
