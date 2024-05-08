package shop.bookbom.front.security.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import shop.bookbom.front.common.CommonResponse;

@FeignClient(value = "BOOKBOM-FRONT-AUTH", url = "${bookbom.gateway-url}")
public interface TokenAdapter {
    @PostMapping("/auth/token/refresh")
    CommonResponse refreshToken(String refreshToken);
}
