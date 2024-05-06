package shop.bookbom.front.domain.order.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.bookbom.front.domain.order.dto.request.BeforeOrderRequest;
import shop.bookbom.front.domain.order.dto.request.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.request.OpenOrderRequest;
import shop.bookbom.front.domain.order.dto.request.WrapperSelectRequest;
import shop.bookbom.front.domain.order.dto.response.BeforeOrderResponse;
import shop.bookbom.front.domain.order.dto.response.OrderResponse;
import shop.bookbom.front.domain.order.dto.response.WrapperSelectResponse;
import shop.bookbom.front.domain.order.service.OrderService;
import shop.bookbom.front.domain.order.util.OrderUtil;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    //포장지 선택페이지
    @PostMapping("/order/wrapper")
    public String processSelectWrapperPage(@ModelAttribute BeforeOrderRequestList beforeOrderRequestList,
                                           RedirectAttributes redirectAttributes) {

        String beforeOrderRequestsStr =
                OrderUtil.convertBeforeOrderListToString(beforeOrderRequestList.getBeforeOrderRequests());
        redirectAttributes.addAttribute("beforeOrderRequestsStr", beforeOrderRequestsStr);
        return "redirect:/order/wrapper";
    }

    //prg 패턴으로 보여줌
    @GetMapping("/order/wrapper")
    public String showSelectWrapperPage(@RequestParam("beforeOrderRequestsStr") String beforeOrderRequestsStr,
                                        Model model, RedirectAttributes redirectAttributes) {
        List<BeforeOrderRequest> beforeOrderRequests = OrderUtil.convertStringToBeforeOrderList(beforeOrderRequestsStr);
        BeforeOrderRequestList beforeOrderRequestList =
                BeforeOrderRequestList.builder().beforeOrderRequests(beforeOrderRequests).build();
        BeforeOrderResponse beforeOrderResponse = orderService.beforeOrder(beforeOrderRequestList);

        model.addAttribute("totalOrderCount", beforeOrderResponse.getTotalOrderCount());
        model.addAttribute("wrapperList", beforeOrderResponse.getWrapperList());
        model.addAttribute("beforeOrderBookResponseList", beforeOrderResponse.getBeforeOrderBookResponseList());
        return "page/order/selectWrapper";
    }

    //재고 부족 페이지
//    @GetMapping("/order/stockLow")
//    public String stockLow(@RequestParam("errorMessage") String errorMessage,
//                           Model model) {
//        model.addAttribute("errorMessage", errorMessage);
//
//        return "page/order/exception/stockLow";
//    }

    //주문서 작성 페이지
    @PostMapping("/order/ordersheet")
    public String postOrdersheet(@ModelAttribute WrapperSelectRequest wrapperSelectRequest,
                                 RedirectAttributes redirectAttributes) {
        String wrapperSelectListToStr =
                OrderUtil.convertWrapperSelectListToString(wrapperSelectRequest.getWrapperSelectBookRequestList());
        redirectAttributes.addAttribute("wrapperSelectListToStr", wrapperSelectListToStr);
        return "redirect:/order/ordersheet";
    }

    //주문서 작성 페이지
    @GetMapping("/order/ordersheet")
    public String getOrdersheet(@RequestParam("wrapperSelectListToStr") String wrapperSelectListToStr,
                                Model model) {
        WrapperSelectRequest wrapperSelectRequest = WrapperSelectRequest.builder()
                .wrapperSelectBookRequestList(OrderUtil.convertStringToWrapperSelectList(wrapperSelectListToStr))
                .build();
        WrapperSelectResponse wrapperSelectResponse = orderService.selectWrapper(wrapperSelectRequest);

        model.addAttribute("totalOrderCount", wrapperSelectResponse.getTotalOrderCount());
        model.addAttribute("deliveryCost", wrapperSelectResponse.getDeliveryCost());
        model.addAttribute("wrapCost", wrapperSelectResponse.getWrapCost());
        model.addAttribute("wrapperSelectResponseList", wrapperSelectResponse.getWrapperSelectResponseList());
        model.addAttribute("estimatedDateList", wrapperSelectResponse.getEstimatedDateList());
        
        return "page/order/ordersheet_non_member";
    }

    //결제 수단페이지
    @PostMapping("/order")
    public String submitOrder(@ModelAttribute OpenOrderRequest openOrderRequest,
                              RedirectAttributes redirectAttributes) {
        OrderResponse orderResponse = orderService.submitOrder(openOrderRequest);

        redirectAttributes.addAttribute("orderId", orderResponse.getOrderId());
        redirectAttributes.addAttribute("orderName", orderResponse.getOrderName());
        redirectAttributes.addAttribute("amount", orderResponse.getAmount());

        return "redirect:/payment-method";
    }


}




