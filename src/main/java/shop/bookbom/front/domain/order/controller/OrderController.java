package shop.bookbom.front.domain.order.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.bookbom.front.common.Adapter.UserCheckAdapter;
import shop.bookbom.front.domain.order.dto.request.BeforeOrderRequest;
import shop.bookbom.front.domain.order.dto.request.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.request.OpenOrderRequest;
import shop.bookbom.front.domain.order.dto.request.OrderRequest;
import shop.bookbom.front.domain.order.dto.request.WrapperSelectRequest;
import shop.bookbom.front.domain.order.dto.response.BeforeOrderResponse;
import shop.bookbom.front.domain.order.dto.response.OpenWrapperSelectResponse;
import shop.bookbom.front.domain.order.dto.response.OrderDetailResponse;
import shop.bookbom.front.domain.order.dto.response.OrderResponse;
import shop.bookbom.front.domain.order.dto.response.WrapperSelectResponse;
import shop.bookbom.front.domain.order.service.OrderService;
import shop.bookbom.front.domain.order.util.OrderUtil;
import shop.bookbom.front.domain.payment.dto.OrderIdResponse;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserCheckAdapter userCheckAdapter;

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

    @GetMapping("/orders/{id}")
    public String orderDetailPage(@PathVariable("id") Long id, Model model) {
        OrderDetailResponse orderDetail = orderService.getOrderDetail(id);
        model.addAttribute("order", orderDetail);
        return "page/order/order-detail";
    }


    //주문서 작성 페이지
    @PostMapping("/order/ordersheet")
    public String postOrdersheet(@ModelAttribute WrapperSelectRequest wrapperSelectRequest,
                                 RedirectAttributes redirectAttributes) {
        String wrapperSelectListToStr =
                OrderUtil.convertWrapperSelectListToString(wrapperSelectRequest.getWrapperSelectBookRequestList());
        redirectAttributes.addAttribute("wrapperSelectListToStr", wrapperSelectListToStr);
        //회원이면
        if (userCheckAdapter.checkUser()) {
            return "redirect:/order/member-ordersheet";
        } else {
            return "redirect:/order/ordersheet";
        }

    }

    //회원 주문 페이지
    @GetMapping("/order/member-ordersheet")
    public String getMemberOrderSheet(@RequestParam("wrapperSelectListToStr") String wrapperSelectListToStr,
                                      Model model) {
        WrapperSelectRequest wrapperSelectRequest = WrapperSelectRequest.builder()
                .wrapperSelectBookRequestList(OrderUtil.convertStringToWrapperSelectList(wrapperSelectListToStr))
                .build();

        WrapperSelectResponse wrapperSelectResponse = orderService.selectWrapperForMember(wrapperSelectRequest);
        model.addAttribute("totalOrderCount", wrapperSelectResponse.getTotalOrderCount());
        model.addAttribute("wrapCost", wrapperSelectResponse.getWrapCost());
        model.addAttribute("wrapperSelectResponseList", wrapperSelectResponse.getWrapperSelectResponseList());
        model.addAttribute("estimatedDateList", wrapperSelectResponse.getEstimatedDateList());
        model.addAttribute("point", wrapperSelectResponse.getPoint());
        model.addAttribute("availableMemberCoupons", wrapperSelectResponse.getAvailableMemberCoupons());
        model.addAttribute("unavailableMemberCoupons", wrapperSelectResponse.getUnavailableMemberCoupons());

        return "page/order/ordersheet_member";
    }


    //주문서 작성 페이지
    @GetMapping("/order/ordersheet")
    public String getOrdersheet(@RequestParam("wrapperSelectListToStr") String wrapperSelectListToStr,
                                Model model) {

        WrapperSelectRequest wrapperSelectRequest = WrapperSelectRequest.builder()
                .wrapperSelectBookRequestList(OrderUtil.convertStringToWrapperSelectList(wrapperSelectListToStr))
                .build();
        OpenWrapperSelectResponse openWrapperSelectResponse = orderService.selectWrapper(wrapperSelectRequest);

        model.addAttribute("totalOrderCount", openWrapperSelectResponse.getTotalOrderCount());
        model.addAttribute("deliveryCost", openWrapperSelectResponse.getDeliveryCost());
        model.addAttribute("wrapCost", openWrapperSelectResponse.getWrapCost());
        model.addAttribute("wrapperSelectResponseList", openWrapperSelectResponse.getWrapperSelectResponseList());
        model.addAttribute("estimatedDateList", openWrapperSelectResponse.getEstimatedDateList());


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

    //결제 수단페이지 - 멤버
    @PostMapping("/order-member")
    public String submitOrder_member(@ModelAttribute OrderRequest orderRequest,
                                     RedirectAttributes redirectAttributes) {
        OrderResponse orderResponse = orderService.submitMemberOrder(orderRequest);
        //결제 금액이 0원 일 경우 결제 단계 건너뛰고 바로 주문완료 페이지 띄움
        if (orderResponse.getAmount() == 0) {
            OrderIdResponse orderIdResponse = orderService.processFreePayment(orderResponse.getOrderId());
            redirectAttributes.addAttribute("orderId", orderIdResponse.getOrderId());
            redirectAttributes.addAttribute("isFree", true);

            return "redirect:/payment-complete";
        }
        redirectAttributes.addAttribute("orderId", orderResponse.getOrderId());
        redirectAttributes.addAttribute("orderName", orderResponse.getOrderName());
        redirectAttributes.addAttribute("amount", orderResponse.getAmount());

        return "redirect:/payment-method";
    }

    @GetMapping("/order/{id}")
    public String orderCancel(@PathVariable("id") String orderId,
                              @RequestParam(name = "reason") String cancelReason,
                              Model model) {
        model.addAttribute("orderId", orderId);
        return "page/order/cancel";
    }
}



