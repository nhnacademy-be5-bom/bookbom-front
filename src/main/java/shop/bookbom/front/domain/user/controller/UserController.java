package shop.bookbom.front.domain.user.controller;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.bookbom.front.domain.order.dto.response.OrderInfoResponse;
import shop.bookbom.front.domain.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users/order")
    public String orderList(
            Model model,
            @PageableDefault Pageable pageable,
            @RequestParam(value = "date_from", required = false) String dateFrom,
            @RequestParam(value = "date_to", required = false) String dateTo
    ) {
        LocalDate orderDateMin = dateFrom == null ? null : LocalDate.parse(dateFrom);
        LocalDate orderDateMax = dateTo == null ? null : LocalDate.parse(dateTo);
        Page<OrderInfoResponse> orderPage = userService.getOrderList(orderDateMin, orderDateMax, pageable);
        model.addAttribute("orderPage", orderPage);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        return "page/user/order-list";
    }
}
