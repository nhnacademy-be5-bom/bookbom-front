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
import shop.bookbom.front.domain.order.dto.BeforeOrderBookResponse;
import shop.bookbom.front.domain.order.dto.BeforeOrderRequestList;
import shop.bookbom.front.domain.order.dto.BeforeOrderResponse;
import shop.bookbom.front.domain.order.dto.PreOrderResponse;
import shop.bookbom.front.domain.order.dto.WrapperDto;
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
}



