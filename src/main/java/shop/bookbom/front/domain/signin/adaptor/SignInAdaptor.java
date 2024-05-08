package shop.bookbom.front.domain.signin.adaptor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.signin.dto.SignInDTO;
import shop.bookbom.front.security.dto.AccessNRefreshTokenDto;

//@FeignClient(value = "BOOKBOM-FRONT-SIGNIN", url = "${bookbom.gateway-feign-url}")
public interface SignInAdaptor {
//    @PostMapping("/auth/token")
    AccessNRefreshTokenDto signIn(SignInDTO signInDTO);
}
