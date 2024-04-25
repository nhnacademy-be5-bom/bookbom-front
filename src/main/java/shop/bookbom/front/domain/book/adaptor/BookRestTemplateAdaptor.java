package shop.bookbom.front.domain.book.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * packageName    : shop.bookbom.front.domain.book.adaptor
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
public class BookRestTemplateAdaptor {
//    private static final ParameterizedTypeReference<CommonResponse<CartInfoResponse>> CART_INFO_RESPONSE =
//            new ParameterizedTypeReference<>() {
//            };
//
//    private final RestTemplate restTemplate;
//
//    @Value("${gateway.url}")
//    String gatewayUrl;
//
//
//    public void addToCart(Long id, List<CartAddRequest> addItems) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<List<CartAddRequest>> requestEntity = new HttpEntity<>(addItems, httpHeaders);
//
//        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/carts/{id}")
//                .buildAndExpand(id)
//                .toUriString();
//
//        CommonResponse<CartInfoResponse> response = restTemplate.exchange(
//                        url,
//                        HttpMethod.POST,
//                        requestEntity,
//                        CART_INFO_RESPONSE)
//                .getBody();
//        if (response != null && !response.getHeader().isSuccessful()) {
//            // todo 예외처리
//            throw new RuntimeException();
//        }
//    }
//
//
//    public CartInfoResponse getCart(Long userId) {
//        String url = UriComponentsBuilder.fromHttpUrl(gatewayUrl + "/shop/carts/{id}")
//                .buildAndExpand(userId)
//                .toUriString();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);
//
//
//        CommonResponse<CartInfoResponse> response =
//                restTemplate.exchange(
//                                url,
//                                HttpMethod.GET,
//                                requestEntity,
//                                CART_INFO_RESPONSE)
//                        .getBody();
//
//        if (!response.getHeader().isSuccessful()) {
//            // todo 예외처리
//            throw new RuntimeException();
//        }
//        return response.getResult();
//    }
}
