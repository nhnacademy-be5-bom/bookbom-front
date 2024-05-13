package shop.bookbom.front.domain.user.adapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.order.dto.response.OrderInfoResponse;
import shop.bookbom.front.domain.user.dto.OrderDateCondition;
import shop.bookbom.front.domain.user.dto.SignUpDto;
import shop.bookbom.front.domain.user.dto.response.EmailCheckResponse;
import shop.bookbom.front.domain.user.dto.response.UserInfoResponse;

public interface UserAdapter {
    Page<OrderInfoResponse> findOrderList(OrderDateCondition orderDateCondition, Pageable pageable);

    EmailCheckResponse checkEmailCanUse(String email);

    void signUp(SignUpDto of);

    /**
     * 마이페이지 회원 정보를 조회하는 메서드입니다.
     */
    UserInfoResponse getUserInfo();
}
