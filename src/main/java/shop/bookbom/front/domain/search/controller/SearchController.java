package shop.bookbom.front.domain.search.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;
import shop.bookbom.front.domain.search.dto.SearchCondition;
import shop.bookbom.front.domain.search.dto.SearchSort;
import shop.bookbom.front.domain.search.service.SearchService;

@Controller
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/search")
    public String searchBook(
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam String keyword,
            @RequestParam(required = false) String sorted,
            @RequestParam(required = false) String search,
            Model model
    ) {
        String sortCondition = getSortCondition(sorted);
        String searchCondition = getSearchCondition(search);
        Page<BookSearchResponse> responses =
                searchService.searchBook(keyword, searchCondition, sortCondition, pageable);
        model.addAttribute("books", responses.getContent());
        model.addAttribute("currentPage", responses.getNumber());
        model.addAttribute("pageSize", responses.getPageable().getPageSize());
        model.addAttribute("totalPages", responses.getTotalPages());
        model.addAttribute("totalItems", responses.getTotalElements());
        model.addAttribute("size", responses.getSize());
        model.addAttribute("keyword", keyword);
        model.addAttribute("sorted", sorted);
        model.addAttribute("search", search);

        return "page/search/search";
    }


    public static String getSortCondition(String sorted) {
        if (StringUtils.hasText(sorted)) {
            return SearchSort.valueOf(sorted.toUpperCase()).name();
        }
        return SearchSort.NONE.name();
    }

    private static String getSearchCondition(String search) {
        if (StringUtils.hasText(search)) {
            return SearchCondition.valueOf(search.toUpperCase()).name();
        }
        return SearchCondition.NONE.name();
    }
}
