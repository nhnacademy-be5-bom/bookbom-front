package shop.bookbom.front.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/my-page")
    public String myPage() {
        return "page/user/my-page";
    }
}
