package shop.bookbom.front.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.user.dto.response.SignupCheckResponse;
import shop.bookbom.front.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping("/check-email")
    public CommonResponse<SignupCheckResponse> checkEmailCanUse(@RequestParam("email") String email) {
        return CommonResponse.successWithData(userService.checkEmailCanUse(email));
    }

    @GetMapping("/check-nickname")
    public CommonResponse<SignupCheckResponse> checkNicknameCanUse(@RequestParam("nickname") String nickname) {
        return CommonResponse.successWithData(userService.checkNicknameCanUse(nickname));
    }
}
