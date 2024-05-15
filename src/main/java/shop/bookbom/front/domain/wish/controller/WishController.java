package shop.bookbom.front.domain.wish.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.bookbom.front.domain.wish.dto.response.WishInfoResponse;
import shop.bookbom.front.domain.wish.service.WishService;

@Controller
@RequiredArgsConstructor
public class WishController {
    private final WishService wishService;

    @GetMapping("/wish")
    public String getWishPage(Model model, @PageableDefault Pageable pageable){
        Page<WishInfoResponse> wishList = wishService.getWishList(pageable);
        model.addAttribute("wishList", wishList);
        return "page/wish/wish";
    }
}
