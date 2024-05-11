package shop.bookbom.front.domain.user.service.impl;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.order.dto.response.OrderInfoResponse;
import shop.bookbom.front.domain.user.adapter.UserAdapter;
import shop.bookbom.front.domain.user.dto.OrderDateCondition;
import shop.bookbom.front.domain.user.dto.SignUpDto;
import shop.bookbom.front.domain.user.dto.request.SignUpRequest;
import shop.bookbom.front.domain.user.dto.response.SignupCheckResponse;
import shop.bookbom.front.domain.user.dto.response.UserInfoResponse;
import shop.bookbom.front.domain.user.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserAdapter userAdapter;

    @Override
    public Page<OrderInfoResponse> getOrderList(LocalDate orderDateMin, LocalDate orderDateMax, Pageable pageable) {
        OrderDateCondition orderDateCondition = new OrderDateCondition(orderDateMin, orderDateMax);
        return userAdapter.findOrderList(orderDateCondition, pageable);
    }

    @Override
    public SignupCheckResponse checkEmailCanUse(String email) {
        return userAdapter.checkEmailCanUse(email);
    }

    @Override
    public void signUp(SignUpRequest request) {
        String password = request.getPassword();
        // todo 비밀번호 암호화
        String encodePassword = password;
        userAdapter.signUp(SignUpDto.of(request, encodePassword));
    }

    @Override
    public UserInfoResponse getUserInfo() {
        return userAdapter.getUserInfo();
    }

    @Override
    public SignupCheckResponse checkNicknameCanUse(String nickname) {
        return userAdapter.checkNicknameCanUse(nickname);
    }
}
