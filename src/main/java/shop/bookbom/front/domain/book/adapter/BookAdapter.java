package shop.bookbom.front.domain.book.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import shop.bookbom.front.common.CommonPage;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;
import shop.bookbom.front.domain.book.dto.request.BookUpdateRequest;
import shop.bookbom.front.domain.book.dto.response.BookDetailResponse;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;

@FeignClient(value = "BOOKBOM-FRONT-BOOK", path = "/shop", url = "${bookbom.gateway-url}")
public interface BookAdapter {
    @PutMapping("/book/update/new")
    CommonResponse<Void> save(@RequestBody BookAddRequest bookAddRequest);

    @PutMapping("/book/update/{id}")
    public CommonResponse<Void> updateBook(@RequestBody BookUpdateRequest bookUpdateRequest,
                                           @PathVariable("id") Long bookId);

    @DeleteMapping("/book/delete/{id}")
    public CommonResponse<Void> deleteBook(@PathVariable("id") Long bookId);

    @GetMapping("/book/detail/{id}")
    CommonResponse<BookDetailResponse> get(@PathVariable("id") Long bookId);

//    @GetMapping("/books/best")
//    CommonResponse<Page<BookMediumResponse>> getBest(Pageable pageable);
//
//    @GetMapping("/books/all")
//    CommonResponse<Page<BookMediumResponse>> getAll(Pageable pageable);

    @GetMapping("/books/category/{categoryId}")
    CommonResponse<CommonPage<BookSearchResponse>> getByCategoryId(
            @PathVariable("categoryId") Long categoryId,
            Pageable pageable,
            @RequestParam String sortCondition);
}
