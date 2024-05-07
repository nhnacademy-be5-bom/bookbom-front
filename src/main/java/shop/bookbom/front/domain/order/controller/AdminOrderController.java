package shop.bookbom.front.domain.order.controller;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.bookbom.front.domain.order.dto.response.OrderManagementResponse;
import shop.bookbom.front.domain.order.service.OrderService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/orders")
public class AdminOrderController {
    private final OrderService orderService;

    @GetMapping
    public String orderList(
            @PageableDefault Pageable pageable,
            @RequestParam(value = "date_from", required = false) String dateFrom,
            @RequestParam(value = "date_to", required = false) String dateTo,
            @RequestParam(value = "sorted", required = false) String sort,
            @RequestParam(value = "status", required = false) String status,
            Model model
    ) {
        LocalDate orderDateMin = dateFrom == null ? null : LocalDate.parse(dateFrom);
        LocalDate orderDateMax = dateTo == null ? null : LocalDate.parse(dateTo);
        Page<OrderManagementResponse> orders =
                orderService.orderManagement(pageable, sort, status, orderDateMin, orderDateMax);
        model.addAttribute("orderPage", orders);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        model.addAttribute("sorted", sort);
        model.addAttribute("status", status);
        return "page/admin/order-list";
    }
}
