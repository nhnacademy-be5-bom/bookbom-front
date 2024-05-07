package shop.bookbom.front.domain.signup.controller;


import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.bookbom.front.domain.signup.dto.SignUpDto;
import shop.bookbom.front.domain.signup.service.SignUpService;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;

    @PostMapping("/signup")
    public String doRegister(@Valid SignUpDto signUpDto) {
        Long userId = signUpService.register(signUpDto);
        if (userId == null) {
            return "redirect:/signupfail";
        }
        return "redirect:/signupsuccess";
    }

    @GetMapping("/signupsuccess")
    public String registerSuccess() {
        return "page/signup/signupsuccess";
    }

    @GetMapping("/signupfail")
    public String registerFail() {
        return "page/signup/signupfail";
    }
}
