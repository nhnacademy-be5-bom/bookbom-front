package shop.bookbom.front.domain.user.controller;

import java.time.LocalDate;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import shop.bookbom.front.annotation.Login;
import shop.bookbom.front.common.dto.UserDto;
import shop.bookbom.front.domain.member.service.MemberService;
import shop.bookbom.front.domain.order.dto.response.OrderInfoResponse;
import shop.bookbom.front.domain.user.dto.request.SetPasswordRequest;
import shop.bookbom.front.domain.user.dto.request.SignUpRequest;
import shop.bookbom.front.domain.user.dto.request.WithDrawDTO;
import shop.bookbom.front.domain.user.dto.response.UserInfoResponse;
import shop.bookbom.front.domain.user.service.UserService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final MemberService memberService;
    private final UserService userService;


    @GetMapping("/users/my-page")
    public String myPage(@Login UserDto userDto, Model model) {
        if (userDto.getRole().equals("ROLE_USER")) {
            UserInfoResponse infos = userService.getUserInfo();
            Long orderId = infos.getLastOrders().get(0).getId();
            return "redirect:/orders/" + orderId;
        }
        model.addAttribute("user", memberService.getMemberInfo());
        return "page/user/my-page";
    }


    @GetMapping("/users/orders")
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
    public String signUpPage(@ModelAttribute("signUpRequest") SignUpRequest request) {
        return "page/user/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute @Valid SignUpRequest signUpRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "page/user/sign-up";
        }
        LocalDate today = LocalDate.now();
        if (signUpRequest.getBirthDate() != null && signUpRequest.getBirthDate().isAfter(today)) {
            bindingResult.rejectValue("birthDate", "error.birthDate", "생년월일은 현재 날짜보다 이후일 수 없습니다.");
            return "page/user/sign-up";
        }
        if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "비밀번호가 일치하지 않습니다.");
            return "page/user/sign-up";
        }
        boolean existsError = false;
        if (!signUpRequest.isEmailCanUse()) {
            bindingResult.reject("error.email", "이메일 중복체크를 진행해주세요.");
            existsError = true;
        }
        if (!signUpRequest.isNicknameCanUse()) {
            bindingResult.reject("error.nickname", "닉네임 중복체크를 진행해주세요.");
            existsError = true;
        }
        if (existsError) {
            return "page/user/sign-up";
        }
        userService.signUp(signUpRequest);
        return "redirect:/sign-up/success";
    }

    @GetMapping("/sign-up/success")
    public String signUpSuccess() {
        return "page/user/sign-up-success";
    }

    @GetMapping("/users/update-private")
    public String setPassword(@ModelAttribute("setPasswordRequest") SetPasswordRequest setPasswordRequest) {
        return "page/user/set-password";
    }

    @PostMapping("/users/update-private")
    public String doSetPassword(@ModelAttribute("setPasswordRequest") @Valid SetPasswordRequest setPasswordRequest,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "page/user/set-password";
        }
        if (!setPasswordRequest.getPassword().equals(setPasswordRequest.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "입력한 비밀번호가 같지 않습니다.");
            return "page/user/set-password";
        }
        try {
            userService.setPassword(setPasswordRequest);
        } catch (Exception e) {
            bindingResult.rejectValue("prePassword", "error.prePassword", "현재 비밀번호가 일치하지 않습니다");
            return "page/user/set-password";
        }
        return "redirect:/users/my-page";
    }

    @GetMapping("/users/my-rank")
    public String myRank(Model model) {
        model.addAttribute("userRank", userService.getUserRank());
        return "page/user/my-rank";
    }
    @GetMapping("/users/withdraw")
    public String getDeletePage() {
        return "page/withdraw/delete-user";
    }

    @PostMapping("/users/withdraw")
    public String deleteUser(@ModelAttribute WithDrawDTO withDrawDTO) {
        userService.deleteUser(withDrawDTO);
        return "page/main";
    }
}
