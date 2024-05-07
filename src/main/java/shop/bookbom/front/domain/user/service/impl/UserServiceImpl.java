package shop.bookbom.front.domain.user.service.impl;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.member.dto.request.OrderDateCondition;
import shop.bookbom.front.domain.order.dto.response.OrderInfoResponse;
import shop.bookbom.front.domain.user.adapter.UserAdapter;
import shop.bookbom.front.domain.user.dto.response.EmailCheckResponse;
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
    public EmailCheckResponse checkEmailCanUse(String email) {
        return userAdapter.checkEmailCanUse(email);
    }
}
