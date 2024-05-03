package shop.bookbom.front.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.bookbom.front.domain.member.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/my-page")
    public String myPage(Model model) {
        model.addAttribute("member", memberService.myPage());
        return "page/user/my-page";
    }
}
