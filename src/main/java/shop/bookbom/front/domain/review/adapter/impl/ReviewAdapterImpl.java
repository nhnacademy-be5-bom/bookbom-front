package shop.bookbom.front.domain.review.adapter.impl;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import shop.bookbom.front.common.CommonPage;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.common.exception.RestTemplateException;
import shop.bookbom.front.domain.review.adapter.ReviewAdapter;
import shop.bookbom.front.domain.review.dto.ReviewCheckResponse;
import shop.bookbom.front.domain.review.dto.ReviewForm;
import shop.bookbom.front.domain.review.dto.response.BookReviewResponse;

@Component
@RequiredArgsConstructor
public class ReviewAdapterImpl implements ReviewAdapter {
    private static final ParameterizedTypeReference<CommonResponse<Void>> COMMON_RESPONSE =
            new ParameterizedTypeReference<>() {
            };
    private static final ParameterizedTypeReference<CommonResponse<ReviewCheckResponse>> REVIEW_CHECK_RESPONSE =
            new ParameterizedTypeReference<>() {
            };
    private static final ParameterizedTypeReference<CommonResponse<CommonPage<BookReviewResponse>>>
            BOOK_REVIEW_RESPONSE =
            new ParameterizedTypeReference<>() {
            };
    private final RestTemplate multipartRestTemplate;
    private final RestTemplate restTemplate;
    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    @Override
    public void writeReview(ReviewForm reviewForm) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = getMultiValueMapHttpEntity(reviewForm, httpHeaders);
        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/reviews")
                .toUriString();
        CommonResponse<Void> response = multipartRestTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        requestEntity,
                        COMMON_RESPONSE)
                .getBody();
        if (response == null) {
            throw new RestTemplateException();
        }
        if (!response.getHeader().isSuccessful()) {
            throw new RestTemplateException(response.getHeader().getResultMessage());
        }
    }

    @Override
    public ReviewCheckResponse existsCheck(Long bookId, Long orderId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);
        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/open/reviews/exists-check")
                .queryParam("bookId", bookId)
                .queryParam("orderId", orderId)
                .toUriString();
        CommonResponse<ReviewCheckResponse> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        requestEntity,
                        REVIEW_CHECK_RESPONSE)
                .getBody();
        if (response == null) {
            throw new RestTemplateException();
        }
        if (!response.getHeader().isSuccessful()) {
            throw new RestTemplateException(response.getHeader().getResultMessage());
        }
        return response.getResult();
    }

    @Override
    public Page<BookReviewResponse> getReviews(Long bookId, Pageable pageable) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);
        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/open/reviews")
                .queryParam("bookId", bookId)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .toUriString();
        CommonResponse<CommonPage<BookReviewResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                BOOK_REVIEW_RESPONSE).getBody();
        if (response == null) {
            throw new RestTemplateException();
        }
        if (!response.getHeader().isSuccessful()) {
            throw new RestTemplateException(response.getHeader().getResultMessage());
        }
        return response.getResult();
    }

    private HttpEntity<MultiValueMap<String, Object>> getMultiValueMapHttpEntity(ReviewForm reviewForm,
                                                                                 HttpHeaders httpHeaders) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        if (reviewForm.getImage() != null) {
            ByteArrayResource image;
            try {
                image = new ByteArrayResource(reviewForm.getImage().getBytes()) {
                    @Override
                    public String getFilename() {
                        return reviewForm.getImage().getOriginalFilename();
                    }
                };
                body.add("image", image);
            } catch (IOException e) {
                throw new RestTemplateException("이미지 파일을 읽을 수 없습니다.");
            }
        }
        body.add("bookId", reviewForm.getBookId());
        body.add("orderId", reviewForm.getOrderId());
        body.add("type", reviewForm.getType());
        body.add("rating", reviewForm.getRating());
        body.add("content", reviewForm.getContent());

        return new HttpEntity<>(body, httpHeaders);
    }


}
