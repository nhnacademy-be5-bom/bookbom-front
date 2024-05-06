package shop.bookbom.front.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.bookbom.front.domain.order.util.OrderUtil;
import shop.bookbom.front.domain.payment.dto.PaymentRequest;
import shop.bookbom.front.domain.payment.dto.PaymentSuccessResponse;
import shop.bookbom.front.domain.payment.service.PaymentService;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    //결제 수단 페이지
    @GetMapping("/payment-method")
    public String selectPaymentMethod(@RequestParam("orderId") String orderId,
                                      @RequestParam("orderName") String orderName,
                                      @RequestParam("amount") String amount,
                                      Model model) {
        model.addAttribute("orderId", orderId);
        model.addAttribute("orderName", orderName);
        model.addAttribute("amount", amount);

        return "page/payment/checkout";
    }

    //토스 결제 성공했을 때
    @GetMapping("/toss-success")
    public String showtosspay_success(@RequestParam("paymentKey") String paymentKey,
                                      @RequestParam("orderId") String orderId,
                                      @RequestParam("amount") Integer amount,
                                      Model model) {
        model.addAttribute("orderId", orderId);
        model.addAttribute("paymentKey", paymentKey);
        model.addAttribute("amount", amount);

        return "page/payment/tosssuccess";
    }

    //토스 결제 실패 했을 때
    @GetMapping("/toss-fail")
    public String showtosspay_fail() {
        return "page/payment/tossfail";
    }

    //주문 완료 페이지
    @PostMapping("/payment-complete")
    public String paymentComplete(@ModelAttribute PaymentRequest paymentRequest,
                                  RedirectAttributes redirectAttributes) {
        String paymentRequestToStr = OrderUtil.convertPaymentRequestToString(paymentRequest);
        redirectAttributes.addAttribute("paymentRequestToStr", paymentRequestToStr);

        return "redirect:/payment-complete";
    }

    //주문 완료 페이지
    @GetMapping("/payment-complete")
    public String showOrderComplete(@RequestParam("paymentRequestToStr") String paymentRequestToStr
            , Model model) {

        PaymentRequest paymentRequest = OrderUtil.convertStringToPaymentRequest(paymentRequestToStr);
        PaymentSuccessResponse paymentResponse = paymentService.getPaymentConfirm(paymentRequest);

        model.addAttribute("orderNumber", paymentResponse.getOrderNumber());
        model.addAttribute("orderInfo", paymentResponse.getOrderInfo());
        model.addAttribute("totalCount", paymentResponse.getTotalCount());
        model.addAttribute("orderBookInfoDtoList", paymentResponse.getOrderBookInfoDtoList());
        model.addAttribute("totalAmount", paymentResponse.getTotalAmount());
        model.addAttribute("paymentMethodName", paymentResponse.getPaymentMethodName());
        model.addAttribute("deliveryName", paymentResponse.getDeliveryName());
        model.addAttribute("deliveryPhoneNumber", paymentResponse.getDeliveryPhoneNumber());
        model.addAttribute("zipCode", paymentResponse.getZipCode());
        model.addAttribute("deliveryAddress", paymentResponse.getDeliveryAddress());
        model.addAttribute("addressDetail", paymentResponse.getAddressDetail());

        return "page/order/complete";
    }
}
