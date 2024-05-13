package shop.bookbom.front.domain.order.service;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.order.adapter.OrderAdapter;
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

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderAdapter orderAdapter;

    @Override
    public BeforeOrderResponse beforeOrder(BeforeOrderRequestList beforeOrderRequestList) {
        return orderAdapter.beforeOrder(beforeOrderRequestList);
    }

    @Override
    public OrderDetailResponse getOrderDetail(Long id) {
        return orderAdapter.getOrderDetail(id);
    }

    @Override
    public OpenWrapperSelectResponse selectWrapper(WrapperSelectRequest wrapperSelectRequest) {
        return orderAdapter.wrapperSelect(wrapperSelectRequest);
    }

    @Override
    public WrapperSelectResponse selectWrapperForMember(WrapperSelectRequest wrapperSelectRequest, Long userId) {
        return orderAdapter.wrapperSelectForMember(wrapperSelectRequest, userId);
    }

    @Override
    public OrderResponse submitOrder(OpenOrderRequest openOrderRequest) {
        return orderAdapter.submitOrder(openOrderRequest);
    }

    @Override
    public OrderResponse submitMemberOrder(OrderRequest orderRequest, Long userId) {
        return orderAdapter.submitMemberOrder(orderRequest, userId);
    }

    @Override
    public Page<OrderManagementResponse> orderManagement(Pageable pageable, String sort, String status,
                                                         LocalDate dateFrom,
                                                         LocalDate dateTo) {
        return orderAdapter.getOrderManagement(pageable, dateFrom, dateTo, sort, status);
    }

    @Override
    public CommonResponse<Void> updateOrderStatus(OrderStatusUpdateRequest request) {
        return orderAdapter.updateOrderStatus(request);
    }

    @Override
    public OrderIdResponse processFreePayment(String orderNumber) {
        return orderAdapter.processFreePayment(orderNumber);
    }
}
