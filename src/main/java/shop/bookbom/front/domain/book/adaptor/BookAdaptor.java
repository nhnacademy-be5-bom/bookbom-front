package shop.bookbom.front.domain.book.adaptor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;

@FeignClient(value = "bookbom-shop-dev1", path = "/shop")
public interface BookAdaptor {
    @PutMapping("/book/update")
    CommonResponse<Void> putBook(@RequestParam BookAddRequest bookAddRequest);

}
