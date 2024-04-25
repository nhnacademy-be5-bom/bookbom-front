package shop.bookbom.front.domain.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.bookbom.front.domain.order.dto.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.BeforeOrderResponse;
import shop.bookbom.front.domain.order.service.OrderService;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/wrapper")
    public String processSelectWrapperPage(@ModelAttribute BeforeOrderRequestList beforeOrderRequestList,
                                           RedirectAttributes redirectAttributes) {
        BeforeOrderResponse beforeOrderResponse = orderService.beforeOrder(beforeOrderRequestList);
        redirectAttributes.addFlashAttribute("totalOrderCount", beforeOrderResponse.getTotalOrderCount());
//        for (int i = 0; i < beforeOrderResponse.getWrapperList().size(); i++) {
//            redirectAttributes.addFlashAttribute("wrapper[" + i + "]", beforeOrderResponse.getWrapperList().get(i));
//        }
//        for (int i = 0; i < beforeOrderResponse.getBeforeOrderBookResponseList().size(); i++) {
//            redirectAttributes.addFlashAttribute("beforeOrderBookResponse[" + i + "]",
//                    beforeOrderResponse.getBeforeOrderBookResponseList().get(i));
//        }
        redirectAttributes.addFlashAttribute("wrapperList", beforeOrderResponse.getWrapperList());
        redirectAttributes.addFlashAttribute("beforeOrderBookResponseList",
                beforeOrderResponse.getBeforeOrderBookResponseList());
        return "redirect:/order/selectWrapper";
    }

    @GetMapping("/order/selectWrapper")
    public String showSelectWrapperPage(Model model) {

        model.addAttribute("totalOrderCount", model.getAttribute("totalOrderCount"));
        model.addAttribute("wrapperList", model.getAttribute("wrapperList"));
        model.addAttribute("beforeOrderBookResponseList", model.getAttribute("beforeOrderBookResponseList"));
        return "page/order/selectWrapper";
    }
}
