package shop.bookbom.front.domain.user.controller;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.bookbom.front.domain.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users/order")
    public String orderList(
            Model model,
            Pageable pageable,
            @RequestParam(value = "date_from", required = false) String dateFrom,
            @RequestParam(value = "date_to", required = false) String dateTo
    ) {
        LocalDate orderDateMin = dateFrom == null ? null : LocalDate.parse(dateFrom);
        LocalDate orderDateMax = dateTo == null ? null : LocalDate.parse(dateTo);
        model.addAttribute("orders", userService.getOrderList(orderDateMin, orderDateMax, pageable));
        return "page/user/order-list";
    }
}
