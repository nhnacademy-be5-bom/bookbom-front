package shop.bookbom.front.domain.user.service;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.order.dto.response.OrderInfoResponse;

public interface UserService {
    /**
     * 주문 목록을 조회하는 메서드입니다.
     */
    Page<OrderInfoResponse> getOrderList(LocalDate orderDateMin, LocalDate orderDateMax, Pageable pageable);
}