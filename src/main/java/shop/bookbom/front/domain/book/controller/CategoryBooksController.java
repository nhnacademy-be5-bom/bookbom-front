package shop.bookbom.front.domain.book.controller;

import static shop.bookbom.front.domain.search.controller.SearchController.getSortCondition;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;
import shop.bookbom.front.domain.book.service.BookService;
import shop.bookbom.front.domain.category.dto.response.CategoryNameAndChildResponse;
import shop.bookbom.front.domain.category.service.CategoryService;

@Controller
@RequiredArgsConstructor
public class CategoryBooksController {
    private final BookService bookService;
    private final CategoryService categoryService;

    @GetMapping("/category/{id}")
    public String index(@PathVariable(value = "id") Long categoryId,
                        @PageableDefault(size = 5) Pageable pageable,
                        @RequestParam(required = false) String sorted,
                        Model model) {
        String sortCondition = getSortCondition(sorted);

        Page<BookSearchResponse> bookResponse =
                bookService.getBooksByCategoryId(categoryId, pageable, sortCondition).getResult();

        CategoryNameAndChildResponse categoryResponse =
                categoryService.getCategoryNameAndChildCategoriesByCategoryId(categoryId);

        model.addAttribute("category", categoryResponse);

        model.addAttribute("books", bookResponse.getContent());
        model.addAttribute("currentPage", bookResponse.getNumber());
        model.addAttribute("pageSize", bookResponse.getPageable().getPageSize());
        model.addAttribute("totalPages", bookResponse.getTotalPages());
        model.addAttribute("totalItems", bookResponse.getTotalElements());
        model.addAttribute("size", bookResponse.getSize());
        model.addAttribute("sorted", sorted);

        return "page/category/categorybooks";
    }

}
