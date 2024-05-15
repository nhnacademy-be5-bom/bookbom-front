package shop.bookbom.front.domain.review.adapter.impl;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.common.exception.RestTemplateException;
import shop.bookbom.front.domain.review.adapter.ReviewAdapter;
import shop.bookbom.front.domain.review.dto.ReviewForm;

@Component
@RequiredArgsConstructor
public class ReviewAdapterImpl implements ReviewAdapter {
    private static final ParameterizedTypeReference<CommonResponse<Void>> COMMON_RESPONSE =
            new ParameterizedTypeReference<>() {
            };
    private final RestTemplate restTemplate;
    @Value("${bookbom.gateway-url}")
    String gatewayUrl;

    @Override
    public void writeReview(ReviewForm reviewForm) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = getMultiValueMapHttpEntity(reviewForm, httpHeaders);
        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/reviews")
                .toUriString();
        CommonResponse<Void> response = restTemplate.exchange(
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

    private HttpEntity<MultiValueMap<String, Object>> getMultiValueMapHttpEntity(ReviewForm reviewForm,
                                                                                 HttpHeaders httpHeaders) {
        ByteArrayResource image;
        try {
            image = new ByteArrayResource(reviewForm.getImage().getBytes()) {
                @Override
                public String getFilename() {
                    return reviewForm.getImage().getOriginalFilename();
                }
            };
        } catch (IOException e) {
            throw new RestTemplateException("이미지 파일을 읽을 수 없습니다.");
        }
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("bookId", reviewForm.getBookId());
        body.add("orderId", reviewForm.getOrderId());
        body.add("type", reviewForm.getType());
        body.add("rating", reviewForm.getRating());
        body.add("content", reviewForm.getContent());
        body.add("image", image);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, httpHeaders);
        return requestEntity;
    }
}
