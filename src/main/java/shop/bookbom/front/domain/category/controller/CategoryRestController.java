package shop.bookbom.front.domain.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.bookbom.front.common.CommonListResponse;
import shop.bookbom.front.domain.category.dto.CategoryDTO;
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
@RestController
@RequiredArgsConstructor
public class CategoryRestController {
    public final CategoryService categoryService;

    @RequestMapping("rest/categories/{parentId}")
    public CommonListResponse<CategoryDTO> getCategoriesByParentId(@PathVariable("parentId") Long id) {
        return categoryService.getChildCategoriesById(id);
    }

}
