package shop.bookbom.front.domain.user.adapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.user.dto.request.WithDrawDTO;
import shop.bookbom.front.domain.order.dto.response.OrderInfoResponse;
import shop.bookbom.front.domain.user.dto.OrderDateCondition;
import shop.bookbom.front.domain.user.dto.SignUpDto;
import shop.bookbom.front.domain.user.dto.request.SetPasswordRequest;
import shop.bookbom.front.domain.user.dto.response.SignupCheckResponse;
import shop.bookbom.front.domain.user.dto.response.UserInfoResponse;
import shop.bookbom.front.domain.user.dto.response.UserRankResponse;

public interface UserAdapter {
    Page<OrderInfoResponse> findOrderList(OrderDateCondition orderDateCondition, Pageable pageable);

    SignupCheckResponse checkEmailCanUse(String email);

    void signUp(SignUpDto of);

    /**
     * 마이페이지 회원 정보를 조회하는 메서드입니다.
     */
    UserInfoResponse getUserInfo();

    /**
     * 회원 등급을 조회하는 메서드입니다.
     */
    UserRankResponse getUserRank();
  
    void deleteUser(WithDrawDTO withDrawDTO);
    /**
     * 닉네임 중복 체크하는 메서드입니다.
     *
     * @param nickname 닉네임
     * @return 닉네임 사용 가능 여부
     */
    SignupCheckResponse checkNicknameCanUse(String nickname);

    void setPassword(SetPasswordRequest setPasswordRequest);
}
