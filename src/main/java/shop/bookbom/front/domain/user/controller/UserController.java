package shop.bookbom.front.domain.user.controller;

import java.time.LocalDate;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.bookbom.front.domain.order.dto.response.OrderInfoResponse;
import shop.bookbom.front.domain.user.dto.request.SignUpRequest;
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

    @GetMapping("/sign-up")
    public String signUpPage(@ModelAttribute("signUpRequest") SignUpRequest member) {
        return "page/user/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute @Valid SignUpRequest signUpRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "page/user/sign-up";
        }
        if (signUpRequest.getBirthDate() != null && signUpRequest.getBirthDate().isAfter(LocalDate.now())) {
            bindingResult.rejectValue("birthDate", "error.birthDate", "생년월일은 현재 날짜보다 이후일 수 없습니다.");
            return "page/user/sign-up";
        }
        if (!signUpRequest.isEmailCanUse()) {
            bindingResult.reject("error.email", "이메일 중복체크를 진행해주세요.");
            return "page/user/sign-up";
        }
        return "redirect:/";
    }
}
