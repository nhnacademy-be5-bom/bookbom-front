package shop.bookbom.front.domain.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shop.bookbom.front.domain.order.dto.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.BeforeOrderResponse;
import shop.bookbom.front.domain.order.service.OrderService;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping("/order/wrapper")
    public String showSelectWrpperPage(Model model,
                                       @ModelAttribute BeforeOrderRequestList beforeOrderRequestList
    ) {
        BeforeOrderResponse beforeOrderResponse = orderService.beforeOrder(beforeOrderRequestList);
        model.addAttribute("totalOrderCount", beforeOrderResponse.getTotalOrderCount());
        model.addAttribute("beforeOrderBookResponseList", beforeOrderResponse.getBeforeOrderBookResponseList());
        model.addAttribute("wrapperList", beforeOrderResponse.getWrapperList());

        return "page/order/selectWrapper";
    }
}
