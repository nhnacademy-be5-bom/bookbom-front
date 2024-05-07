package shop.bookbom.front.domain.book.adapter;

import java.io.IOException;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import shop.bookbom.front.common.CommonPage;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;
import shop.bookbom.front.domain.book.dto.request.BookUpdateRequest;
import shop.bookbom.front.domain.book.dto.response.BookDetailResponse;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;
import shop.bookbom.front.domain.book.dto.response.BookUpdateResponse;

/**
 * packageName    : shop.bookbom.front.domain.book.adapter
 * fileName       : BookRestTemplateAdaptor
 * author         : UuLaptop
 * date           : 2024-04-15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-15        UuLaptop       최초 생성
 */
@Component
@RequiredArgsConstructor
public class BookAdapter {
    private final RestTemplate restTemplate;
    private static final ParameterizedTypeReference<CommonResponse<BookDetailResponse>>
            BOOK_DETAIL_RESPONSE = new ParameterizedTypeReference<>() {
    };
    private static final ParameterizedTypeReference<CommonResponse<BookUpdateResponse>>
            BOOK_UPDATE_RESPONSE = new ParameterizedTypeReference<>() {
    };
    private static final ParameterizedTypeReference<CommonResponse<CommonPage<BookSearchResponse>>>
            CATEGORY_BOOKS_RESPONSE = new ParameterizedTypeReference<>() {
    };

    @Value("${bookbom.gateway-url}")
    private String gatewayUrl;

    public BookDetailResponse getBookDetail(Long bookId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/book/detail/" + bookId)
                .toUriString();

        CommonResponse<BookDetailResponse> response =
                restTemplate.exchange(url, HttpMethod.GET, requestEntity, BOOK_DETAIL_RESPONSE).getBody();
        if (response == null || !response.getHeader().getIsSuccessful()) {
            // todo 예외처리
            throw new RuntimeException();
        }
        return response.getResult();
    }

    public BookUpdateResponse getBookUpdate(Long bookId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/book/update/" + bookId)
                .toUriString();

        CommonResponse<BookUpdateResponse> response =
                restTemplate.exchange(url, HttpMethod.GET, requestEntity, BOOK_UPDATE_RESPONSE).getBody();
        if (response == null || !response.getHeader().getIsSuccessful()) {
            // todo 예외처리
            throw new RuntimeException();
        }
        return response.getResult();
    }

    public Page<BookSearchResponse> getByCategoryId(Long categoryId,
                                                    Pageable pageable,
                                                    String sortCondition) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop//books/category/" + categoryId)
                .queryParam("pageable", pageable)
                .queryParam("sortCondition", sortCondition)
                .toUriString();

        CommonResponse<CommonPage<BookSearchResponse>> response =
                restTemplate.exchange(url, HttpMethod.GET, requestEntity, CATEGORY_BOOKS_RESPONSE).getBody();
        if (response == null || !response.getHeader().getIsSuccessful()) {
            // todo 예외처리
            throw new RuntimeException();
        }
        return response.getResult();
    }

    public CommonResponse<Void> save(MultipartFile file, BookAddRequest bookAddRequest)
            throws IOException {
        RestTemplate multiPartRestTemplate = getMultiPartRestTemplate();

        ByteArrayResource fileResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() throws IllegalStateException {
                return file.getOriginalFilename();
            }
        };

        HttpHeaders httpHeadersForFile = new HttpHeaders();
        httpHeadersForFile.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<ByteArrayResource> fileEntity = new HttpEntity<>(fileResource, httpHeadersForFile);

        HttpHeaders httpHeadersForDto = new HttpHeaders();
        httpHeadersForDto.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<BookAddRequest> dtoEntity = new HttpEntity<>(bookAddRequest, httpHeadersForDto);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/book/update/new")
                .toUriString();

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("thumbnail", fileEntity);
        map.add("bookAddRequest", dtoEntity);

        HttpEntity<MultiValueMap<String, Object>> mapHttpEntity =
                new HttpEntity<>(map, httpHeadersForFile);

        CommonResponse<Void> response =
                multiPartRestTemplate.exchange(url, HttpMethod.PUT, mapHttpEntity, CommonResponse.class).getBody();

        if (response == null || !(response.getHeader().getIsSuccessful())) {
            // todo 예외처리
            throw new RuntimeException();
        }
        return response;
    }

    public CommonResponse<Void> update(MultipartFile file,
                                       BookUpdateRequest bookUpdateRequest,
                                       Long bookId) throws IOException {

        RestTemplate multiPartRestTemplate = getMultiPartRestTemplate();

        ByteArrayResource fileResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() throws IllegalStateException {
                return file.getOriginalFilename();
            }
        };

        HttpHeaders httpHeadersForFile = new HttpHeaders();
        httpHeadersForFile.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<ByteArrayResource> fileEntity = new HttpEntity<>(fileResource, httpHeadersForFile);

        HttpHeaders httpHeadersForDto = new HttpHeaders();
        httpHeadersForDto.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<BookUpdateRequest> dtoEntity = new HttpEntity<>(bookUpdateRequest, httpHeadersForDto);

        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/book/update/" + bookId)
                .toUriString();

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("thumbnail", fileEntity);
        map.add("bookUpdateRequest", dtoEntity);

        HttpEntity<MultiValueMap<String, Object>> mapHttpEntity =
                new HttpEntity<>(map, httpHeadersForFile);

        CommonResponse<Void> response =
                multiPartRestTemplate.exchange(url, HttpMethod.PUT, mapHttpEntity, CommonResponse.class).getBody();

        if (response == null) {
            // todo 예외처리
            throw new RuntimeException();
        }
        return response;
    }

    private static RestTemplate getMultiPartRestTemplate() {
        HttpMessageConverter<Object> jackson = new MappingJackson2HttpMessageConverter();
        HttpMessageConverter<Resource> resource = new ResourceHttpMessageConverter();
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        formHttpMessageConverter.addPartConverter(jackson);
        formHttpMessageConverter.addPartConverter(resource);

        return new RestTemplate(Arrays.asList(jackson, resource, formHttpMessageConverter));
    }
}
