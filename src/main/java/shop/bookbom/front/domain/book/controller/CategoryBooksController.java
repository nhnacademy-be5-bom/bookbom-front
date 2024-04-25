package shop.bookbom.front.domain.book.controller;

import static shop.bookbom.front.domain.search.controller.SearchController.getSortCondition;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;
import shop.bookbom.front.domain.book.service.BookService;

@Controller
@RequiredArgsConstructor
public class CategoryBooksController {
    private final BookService bookService;

    @GetMapping("/category")
    public String index(Model model,
                        @PageableDefault(size = 5) Pageable pageable,
                        @RequestParam(value = "id") Long bookId,
                        @RequestParam(required = false) String sorted) {
        String sortCondition = getSortCondition(sorted);

        Page<BookSearchResponse> response =
                bookService.getBooksByCategoryId(bookId, sortCondition, pageable).getResult();

        model.addAttribute("books", response.getContent());
        model.addAttribute("currentPage", response.getNumber());
        model.addAttribute("pageSize", response.getPageable().getPageSize());
        model.addAttribute("totalPages", response.getTotalPages());
        model.addAttribute("totalItems", response.getTotalElements());
        model.addAttribute("size", response.getSize());
        model.addAttribute("sorted", sorted);

        return "page/category/categorybooks";
    }

}
