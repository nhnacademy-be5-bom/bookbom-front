package shop.bookbom.front.domain.order.adapter;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.order.dto.request.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.request.OpenOrderRequest;
import shop.bookbom.front.domain.order.dto.request.OrderRequest;
import shop.bookbom.front.domain.order.dto.request.OrderStatusUpdateRequest;
import shop.bookbom.front.domain.order.dto.request.WrapperSelectRequest;
import shop.bookbom.front.domain.order.dto.response.BeforeOrderResponse;
import shop.bookbom.front.domain.order.dto.response.OpenWrapperSelectResponse;
import shop.bookbom.front.domain.order.dto.response.OrderDetailResponse;
import shop.bookbom.front.domain.order.dto.response.OrderManagementResponse;
import shop.bookbom.front.domain.order.dto.response.OrderResponse;
import shop.bookbom.front.domain.order.dto.response.WrapperSelectResponse;
import shop.bookbom.front.domain.payment.dto.OrderIdResponse;

public interface OrderAdapter {
    BeforeOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequestList);

    /**
     * 주문 상세 정보를 불러오는 메서드입니다.
     *
     * @param id 주문 id
     * @return 주문 상세 정보
     */
    OrderDetailResponse getOrderDetail(Long id);

    OpenWrapperSelectResponse wrapperSelect(WrapperSelectRequest wrapperSelectRequest);

    WrapperSelectResponse wrapperSelectForMember(WrapperSelectRequest wrapperSelectRequest, Long userId);

    OrderResponse submitOrder(OpenOrderRequest openOrderRequest);

    OrderResponse submitMemberOrder(OrderRequest orderRequest, Long userId);

    Page<OrderManagementResponse> getOrderManagement(Pageable pageable, LocalDate dateFrom, LocalDate dateTo,
                                                     String sort, String status);

    CommonResponse<Void> updateOrderStatus(OrderStatusUpdateRequest request);

    OrderIdResponse processFreePayment(String orderNumber);
}
