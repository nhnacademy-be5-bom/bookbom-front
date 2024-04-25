package shop.bookbom.front.index.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.book.dto.response.BookMediumResponse;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;
import shop.bookbom.front.index.adapter.IndexAdapter;
import shop.bookbom.front.index.service.IndexService;

@Service
@RequiredArgsConstructor
public class IndexServiceImpl implements IndexService {
    private final IndexAdapter indexAdapter;

    @Override
    public List<BookSearchResponse> mainLatestBooks(int size) {
        List<BookMediumResponse> responses = indexAdapter.mainLatestBooks(size).getContent();
        return responses.stream()
                .map(this::getBookSearchResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookSearchResponse> mainBestBooks(int size) {
        List<BookMediumResponse> responses = indexAdapter.mainBestBooks(size).getContent();
        return responses.stream()
                .map(this::getBookSearchResponse)
                .collect(Collectors.toList());
    }

    /**
     * BookMediumResponse를 BookSearchResponse로 변환합니다.
     * @param book 변환할 BookMediumResponse
     * @return 변환된 BookSearchResponse
     */
    private BookSearchResponse getBookSearchResponse(BookMediumResponse book) {
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
                .reviewRating(book.getReviewStatistics().getAverageReviewRate())
                .reviewCount(book.getReviewStatistics().getTotalReviewCount())
                .build();
    }
}
