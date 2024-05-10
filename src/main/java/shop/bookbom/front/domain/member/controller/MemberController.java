package shop.bookbom.front.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shop.bookbom.front.domain.member.dto.request.WithDrawDTO;
import shop.bookbom.front.domain.member.service.MemberService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/withdraw")
    public String getDeletePage(Model model) {
        // 공통화 작업 완료 시 memberId를 받아서 넣어주기
        model.addAttribute("memberId",1);
        return "page/withdraw/delete-member";
    }

    @PostMapping("/member/withdraw")
    public String deleteMember(@ModelAttribute WithDrawDTO withDrawDTO) {
        // 공통화 작업 완료 시 memberId를 받아서 넣어주기
        memberService.deleteMember(1L, withDrawDTO);
        return "page/main";
    }
}
