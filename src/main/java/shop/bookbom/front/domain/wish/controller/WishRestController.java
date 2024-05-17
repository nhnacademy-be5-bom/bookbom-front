package shop.bookbom.front.domain.wish.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.wish.service.WishService;

@RestController
@RequiredArgsConstructor
public class WishRestController {
    private final WishService wishService;

    @PostMapping("/wish")
    public CommonResponse<Void> addWish(@RequestBody List<Long> books){
        wishService.addWish(books);
        return CommonResponse.success();
    }

    @DeleteMapping("/wish/{wishId}")
    public CommonResponse<Void> deleteWish(@PathVariable Long wishId){
        wishService.deleteWish(wishId);
        return CommonResponse.success();
    }
}
