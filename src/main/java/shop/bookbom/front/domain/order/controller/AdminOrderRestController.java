package shop.bookbom.front.domain.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.order.dto.request.OrderStatusUpdateRequest;
import shop.bookbom.front.domain.order.service.OrderService;

@RestController
@RequiredArgsConstructor
public class AdminOrderRestController {
    private final OrderService orderService;

    @PostMapping("/admin/orders/update-status")
    public CommonResponse<Void> updateOrderStatus(@RequestBody OrderStatusUpdateRequest request) {
        return orderService.updateOrderStatus(request);
    }
}
