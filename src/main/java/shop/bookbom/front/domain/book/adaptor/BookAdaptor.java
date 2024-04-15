package shop.bookbom.front.domain.book.adaptor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;
import shop.bookbom.front.domain.book.dto.response.BookDetailResponse;

@FeignClient(value = "BOOKBOM-FRONT-BOOK", path = "/shop", url = "${bookbom.gateway-url}")
public interface BookAdaptor {
    @PutMapping("/book/update")
    CommonResponse<Void> save(@RequestBody BookAddRequest bookAddRequest);

    @GetMapping("/book/detail/{id}")
    CommonResponse<BookDetailResponse> get(@PathVariable("id") Long bookId);


}
