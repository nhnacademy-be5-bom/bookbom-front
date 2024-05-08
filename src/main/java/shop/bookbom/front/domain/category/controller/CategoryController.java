package shop.bookbom.front.domain.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import shop.bookbom.front.domain.category.service.CategoryService;

/**
 * packageName    : shop.bookbom.front.domain.category.controller
 * fileName       : CategoryController
 * author         : UuLaptop
 * date           : 2024-04-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-26        UuLaptop       최초 생성
 */
@Controller
@RequiredArgsConstructor
public class CategoryController {
    public final CategoryService categoryService;

}
