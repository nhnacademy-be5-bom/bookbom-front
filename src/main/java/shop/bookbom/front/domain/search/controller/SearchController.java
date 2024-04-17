package shop.bookbom.front.domain.search.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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

        responses.getContent().forEach(
                response -> log.info("bookId: {}", response.getId())
        );
        model.addAttribute("books", responses.getContent());
        model.addAttribute("currentPage", responses.getNumber());
        model.addAttribute("totalPages", responses.getTotalPages());
        model.addAttribute("totalItems", responses.getTotalElements());
        model.addAttribute("size", responses.getSize());
        model.addAttribute("keyword", keyword);
        model.addAttribute("sorted", sorted);
        model.addAttribute("search", search);

        return "page/search/search";
    }


    private static String getSortCondition(String sorted) {
        if (StringUtils.hasText(sorted)) {
            return SearchSort.valueOf(sorted.toUpperCase()).name();
        }
        return SearchSort.NONE.name();
    }

    private static String getSearchCondition(String search) {
        if (StringUtils.hasText(search)) {
            return SearchSort.valueOf(search.toUpperCase()).name();
        }
        return SearchCondition.NONE.name();
    }
}
