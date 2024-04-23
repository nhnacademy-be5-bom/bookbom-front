package shop.bookbom.front.domain.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import shop.bookbom.front.domain.order.dto.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.service.OrderService;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping("/wrapper")
    public String showSelectWrpperPage(Model model,
                                       @RequestBody BeforeOrderRequestList beforeOrderRequestList) {


        return "page/order/selectWrapper";
    }
}
