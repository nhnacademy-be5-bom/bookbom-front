package shop.bookbom.front.domain.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.order.service.OrderService;
import shop.bookbom.front.domain.payment.dto.response.PaymentCancelResponse;

@RestController
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderService orderService;

    @GetMapping("/cancel")
    public CommonResponse<PaymentCancelResponse> orderCancel(@RequestParam(name = "id") String orderId,
                                                             @RequestParam(name = "reason") String cancelReason,
                                                             Model model) {
        PaymentCancelResponse paymentCancelResponse = orderService.cancelOrder(Long.valueOf(orderId), cancelReason);
        return CommonResponse.successWithData(paymentCancelResponse);
    }
}
