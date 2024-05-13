package shop.bookbom.front.domain.user.service;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.order.dto.response.OrderInfoResponse;
import shop.bookbom.front.domain.user.dto.request.SignUpRequest;
import shop.bookbom.front.domain.user.dto.response.EmailCheckResponse;
import shop.bookbom.front.domain.user.dto.response.UserInfoResponse;
import shop.bookbom.front.domain.user.dto.response.UserRankResponse;

public interface UserService {
    /**
     * 주문 목록을 조회하는 메서드입니다.
     */
    Page<OrderInfoResponse> getOrderList(LocalDate orderDateMin, LocalDate orderDateMax, Pageable pageable);

    EmailCheckResponse checkEmailCanUse(String email);

    void signUp(SignUpRequest signUpRequest);

    /**
     * 마이페이지 회원 정보를 조회하는 메서드입니다.
     */
    UserInfoResponse getUserInfo();

    /**
     * 회원 등급을 조회하는 메서드입니다.
     */
    UserRankResponse getUserRank();
}
