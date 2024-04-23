package shop.bookbom.front.domain.search.adaptor.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import shop.bookbom.front.common.CommonPage;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;
import shop.bookbom.front.domain.search.adaptor.SearchAdapter;

@Component
@RequiredArgsConstructor
public class SearchAdapterImpl implements SearchAdapter {
    private static final ParameterizedTypeReference<CommonResponse<CommonPage<BookSearchResponse>>>
            BOOK_SEARCH_RESPONSE =
            new ParameterizedTypeReference<>() {
            };

    private final RestTemplate restTemplate;

    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    @Override
    public Page<BookSearchResponse> searchBook(String keyword, String searchCond, String sortCond, Pageable pageable) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/search")
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("keyword", keyword)
                .queryParam("searchCond", searchCond)
                .queryParam("sortCond", sortCond)
                .build()
                .toUriString();

        CommonResponse<CommonPage<BookSearchResponse>> response =
                restTemplate.exchange(url, HttpMethod.GET, requestEntity, BOOK_SEARCH_RESPONSE).getBody();
        if (response == null || response.getHeader().isSuccessful()) {
            // todo 예외처리
            throw new RuntimeException();
        }
        return Objects.requireNonNull(response).getResult();
    }
}
