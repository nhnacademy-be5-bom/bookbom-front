package shop.bookbom.front.domain.book.adaptor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;

@FeignClient(value = "BOOKBOM-GATEWAY", path = "/shop")
public interface BookAdaptor {
    @PutMapping("/book/update")
    CommonResponse<Void> save(@RequestBody BookAddRequest bookAddRequest);

}
