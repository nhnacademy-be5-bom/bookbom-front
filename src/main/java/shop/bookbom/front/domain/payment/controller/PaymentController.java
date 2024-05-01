package shop.bookbom.front.domain.payment.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.bookbom.front.domain.payment.dto.OrderBookInfoDto;
import shop.bookbom.front.domain.payment.dto.PaymentRequest;
import shop.bookbom.front.domain.payment.dto.PaymentSuccessResponse;
import shop.bookbom.front.domain.payment.service.PaymentService;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

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

    @GetMapping("/toss-fail")
    public String showtosspay_fail() {
        return "page/payment/tossfail";
    }

    @PostMapping("/payment-complete")
    public String paymentComplete(@ModelAttribute PaymentRequest paymentRequest,
                                  RedirectAttributes redirectAttributes) {
        PaymentSuccessResponse paymentResponse = paymentService.getPaymentConfirm(paymentRequest);
        redirectAttributes.addAttribute("orderNumber", paymentResponse.getOrderNumber());
        redirectAttributes.addAttribute("orderInfo", paymentResponse.getOrderInfo());
        redirectAttributes.addAttribute("totalCount", paymentResponse.getTotalCount());

        String orderBookInfoDtoListToString =
                PaymentSuccessResponse.convertOrderBookInfoDtoListToString(paymentResponse.getOrderBookInfoDtoList());
        redirectAttributes.addAttribute("orderBookInfoDtoListToString", orderBookInfoDtoListToString);
        redirectAttributes.addAttribute("totalAmount", paymentResponse.getTotalAmount());
        redirectAttributes.addAttribute("paymentMethodName", paymentResponse.getPaymentMethodName());
        redirectAttributes.addAttribute("deliveryName", paymentResponse.getDeliveryName());
        redirectAttributes.addAttribute("deliveryPhoneNumber", paymentResponse.getDeliveryPhoneNumber());
        redirectAttributes.addAttribute("zipCode", paymentResponse.getZipCode());
        redirectAttributes.addAttribute("deliveryAddress", paymentResponse.getDeliveryAddress());
        redirectAttributes.addAttribute("addressDetail", paymentResponse.getAddressDetail());


        return "redirect:/order-complete";
    }

    @GetMapping("/order-complete")
    public String showOrderComplete(@RequestParam("orderNumber") String orderNumber,
                                    @RequestParam("orderInfo") String orderInfo,
                                    @RequestParam("totalCount") Integer totalCount,
                                    @RequestParam("orderBookInfoDtoListToString") String orderBookInfoDtoListToString,
                                    @RequestParam("totalAmount") Integer totalAmount,
                                    @RequestParam("paymentMethodName") String paymentMethodName,
                                    @RequestParam("deliveryName") String deliveryName,
                                    @RequestParam("deliveryPhoneNumber") String deliveryPhoneNumber,
                                    @RequestParam("zipCode") String zipCode,
                                    @RequestParam("deliveryAddress") String deliveryAddress,
                                    @RequestParam("addressDetail") String addressDetail,
                                    Model model) {
        List<OrderBookInfoDto> orderBookInfoDtoList =
                PaymentSuccessResponse.convertStringToOrderBookInfoDtoList(orderBookInfoDtoListToString);

        model.addAttribute("orderNumber", orderNumber);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("orderBookInfoDtoList", orderBookInfoDtoList);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("paymentMethodName", paymentMethodName);
        model.addAttribute("deliveryName", deliveryName);
        model.addAttribute("deliveryPhoneNumber", deliveryPhoneNumber);
        model.addAttribute("zipCode", zipCode);
        model.addAttribute("deliveryAddress", deliveryAddress);
        model.addAttribute("addressDetail", addressDetail);

        return "page/order/complete";
    }
}
