package shop.bookbom.front.domain.book.adapter;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.bookbom.front.common.CommonPage;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;
import shop.bookbom.front.domain.book.dto.request.BookUpdateRequest;
import shop.bookbom.front.domain.book.dto.response.BookDetailResponse;
import shop.bookbom.front.domain.book.dto.response.BookSearchResponse;
import shop.bookbom.front.domain.book.dto.response.BookUpdateResponse;

@FeignClient(value = "BOOKBOM-FRONT-BOOK", path = "/shop", url = "${bookbom.gateway-url}")
public interface BookAdapter {

    @PutMapping("/book/update/new")
    @Headers("Content-Type: multipart/form-data")
    CommonResponse<Void> save(@ModelAttribute BookAddRequest bookAddRequest);

    @PutMapping("/book/update/{id}")
    CommonResponse<Void> update(@ModelAttribute BookUpdateRequest bookUpdateRequest,
                                @PathVariable("id") Long bookId);

    @DeleteMapping("/book/delete/{id}")
    CommonResponse<Void> delete(@PathVariable("id") Long bookId);

    @GetMapping("/book/detail/{id}")
    CommonResponse<BookDetailResponse> get(@PathVariable("id") Long bookId);

    @GetMapping("/book/update/{id}")
    public CommonResponse<BookUpdateResponse> getBookUpdate(@PathVariable("id") Long bookId);

    @GetMapping("/books/category/{categoryId}")
    CommonResponse<CommonPage<BookSearchResponse>> getByCategoryId(
            @PathVariable("categoryId") Long categoryId,
            Pageable pageable,
            @RequestParam String sortCondition);
}
