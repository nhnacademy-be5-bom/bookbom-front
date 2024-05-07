package shop.bookbom.front.domain.order.service;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.order.dto.request.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.request.OpenOrderRequest;
import shop.bookbom.front.domain.order.dto.request.OrderStatusUpdateRequest;
import shop.bookbom.front.domain.order.dto.request.WrapperSelectRequest;
import shop.bookbom.front.domain.order.dto.response.OrderDetailResponse;
import shop.bookbom.front.domain.order.dto.response.OrderManagementResponse;
import shop.bookbom.front.domain.order.dto.response.OrderResponse;
import shop.bookbom.front.domain.order.dto.response.PreOrderResponse;
import shop.bookbom.front.domain.order.dto.response.WrapperSelectResponse;


public interface OrderService {
    /**
     * 주문 상세 정보를 불러오는 메서드입니다.
     *
     * @param id 주문 id
     * @return 주문 상세 정보
     */
    OrderDetailResponse getOrderDetail(Long id);

    PreOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequestList);

    WrapperSelectResponse selectWrapper(WrapperSelectRequest wrapperSelectRequest);

    OrderResponse submitOrder(OpenOrderRequest openOrderRequest);

    Page<OrderManagementResponse> orderManagement(Pageable pageable, String sort, String status, LocalDate dateFrom,
                                                  LocalDate dateTo);

    CommonResponse<Void> updateOrderStatus(OrderStatusUpdateRequest request);
}
