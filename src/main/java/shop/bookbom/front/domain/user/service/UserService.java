package shop.bookbom.front.domain.user.service;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.order.dto.response.OrderInfoResponse;
import shop.bookbom.front.domain.user.dto.request.SetPasswordRequest;
import shop.bookbom.front.domain.user.dto.request.SignUpRequest;
import shop.bookbom.front.domain.user.dto.response.SignupCheckResponse;
import shop.bookbom.front.domain.user.dto.response.UserInfoResponse;

public interface UserService {
    /**
     * 주문 목록을 조회하는 메서드입니다.
     */
    Page<OrderInfoResponse> getOrderList(LocalDate orderDateMin, LocalDate orderDateMax, Pageable pageable);

    SignupCheckResponse checkEmailCanUse(String email);

    void signUp(SignUpRequest signUpRequest);

    /**
     * 마이페이지 회원 정보를 조회하는 메서드입니다.
     */
    UserInfoResponse getUserInfo();

    /**
     * 닉네임 중복 체크하는 메서드입니다.
     *
     * @param nickname 닉네임
     * @return 닉네임 사용 가능 여부
     */
    SignupCheckResponse checkNicknameCanUse(String nickname);

    void setPassword(SetPasswordRequest setPasswordRequest);
}
