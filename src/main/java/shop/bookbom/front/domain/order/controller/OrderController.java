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
import shop.bookbom.front.domain.order.dto.request.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.request.OpenOrderRequest;
import shop.bookbom.front.domain.order.dto.request.WrapperDto;
import shop.bookbom.front.domain.order.dto.request.WrapperSelectRequest;
import shop.bookbom.front.domain.order.dto.response.BeforeOrderBookResponse;
import shop.bookbom.front.domain.order.dto.response.BeforeOrderResponse;
import shop.bookbom.front.domain.order.dto.response.OrderResponse;
import shop.bookbom.front.domain.order.dto.response.PreOrderResponse;
import shop.bookbom.front.domain.order.dto.response.WrapperSelectBookResponse;
import shop.bookbom.front.domain.order.dto.response.WrapperSelectResponse;
import shop.bookbom.front.domain.order.service.OrderService;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/wrapper")
    public String processSelectWrapperPage(@ModelAttribute BeforeOrderRequestList beforeOrderRequestList,
                                           RedirectAttributes redirectAttributes) {
        PreOrderResponse preOrderResponse = orderService.beforeOrder(beforeOrderRequestList);
        if (!preOrderResponse.isSuccessful()) {
            redirectAttributes.addAttribute("errorMessage", preOrderResponse.getResultMessage());
            return "redirect:/order/stockLow";
        }
        BeforeOrderResponse beforeOrderResponse =
                (BeforeOrderResponse) preOrderResponse.getBeforeOrderResponse();
        // 리스트를 콤마로 구분된 문자열로 변환하여 리다이렉트 매개변수로 전달
        redirectAttributes.addAttribute("wrapperList",
                BeforeOrderResponse.convertWrapperListToString(beforeOrderResponse.getWrapperList()));
        redirectAttributes.addAttribute("beforeOrderBookResponseList",
                BeforeOrderResponse.convertBookListToString(beforeOrderResponse.getBeforeOrderBookResponseList()));
        redirectAttributes.addAttribute("totalOrderCount", beforeOrderResponse.getTotalOrderCount());

        return "redirect:/order/selectWrapper";
    }

    @GetMapping("/order/selectWrapper")
    public String showSelectWrapperPage(@RequestParam("totalOrderCount") int totalOrderCount,
                                        @RequestParam("wrapperList") String wrapperListAsString,
                                        @RequestParam("beforeOrderBookResponseList")
                                        String beforeOrderBookResponseListAsString,
                                        @ModelAttribute PreOrderResponse beforeOrderResponse,
                                        Model model) {

        // 콤마로 구분된 문자열을 다시 리스트로 변환
        List<WrapperDto> wrapperList = BeforeOrderResponse.convertStringToWrapperList(wrapperListAsString);
        List<BeforeOrderBookResponse> beforeOrderBookResponseList =
                BeforeOrderResponse.convertStringToBookList(beforeOrderBookResponseListAsString);

        model.addAttribute("totalOrderCount", totalOrderCount);
        model.addAttribute("wrapperList", wrapperList);
        model.addAttribute("beforeOrderBookResponseList", beforeOrderBookResponseList);
        return "page/order/selectWrapper";
    }

    @GetMapping("/order/stockLow")
    public String stockLow(@RequestParam("errorMessage") String errorMessage,
                           Model model) {
        model.addAttribute("errorMessage", errorMessage);

        return "page/order/exception/stockLow";
    }

    @PostMapping("/order/ordersheet")
    public String postOrdersheet(@ModelAttribute WrapperSelectRequest wrapperSelectRequest,
                                 RedirectAttributes redirectAttributes) {
        WrapperSelectResponse wrapperSelectResponse = orderService.selectWrapper(wrapperSelectRequest);
        redirectAttributes.addAttribute("totalOrderCount", wrapperSelectResponse.getTotalOrderCount());
        redirectAttributes.addAttribute("deliveryCost", wrapperSelectResponse.getDeliveryCost());
        redirectAttributes.addAttribute("wrapCost", wrapperSelectResponse.getWrapCost());
        redirectAttributes.addAttribute("wrapperSelectResponseList",
                WrapperSelectResponse.convertWrapperSelectListToString(
                        wrapperSelectResponse.getWrapperSelectResponseList()));
        redirectAttributes.addAttribute("estimatedDateList",
                WrapperSelectResponse.convertEstimatedDateListToString(wrapperSelectResponse.getEstimatedDateList()));

        return "redirect:/order/ordersheet";
    }

    @GetMapping("/order/ordersheet")
    public String getOrdersheet(@ModelAttribute WrapperSelectResponse wrapperSelectResponse,
                                @RequestParam("totalOrderCount") int totalOrderCount,
                                @RequestParam("deliveryCost") int deliveryCost,
                                @RequestParam("wrapCost") int wrapCost,
                                @RequestParam("wrapperSelectResponseList") String wrapperSelectResponseListToString,
                                @RequestParam("estimatedDateList") String estimatedDateListToStirng,
                                Model model) {

        List<WrapperSelectBookResponse> wrapperSelectResponseList =
                WrapperSelectResponse.convertStringToWrapperSelectList(wrapperSelectResponseListToString);
        List<String> estimatedDateList =
                WrapperSelectResponse.convertStringToEsitmatedDateList(estimatedDateListToStirng);

        model.addAttribute("totalOrderCount", totalOrderCount);
        model.addAttribute("deliveryCost", deliveryCost);
        model.addAttribute("wrapCost", wrapCost);
        model.addAttribute("wrapperSelectResponseList", wrapperSelectResponseList);
        model.addAttribute("estimatedDateList", estimatedDateList);


        return "page/order/ordersheet_non_member";
    }

    @PostMapping("/order")
    public String submitOrder(@ModelAttribute OpenOrderRequest openOrderRequest) {
        OrderResponse orderResponse = orderService.submitOrder(openOrderRequest);


        return "page/order/ordersheet_member";
    }
}



