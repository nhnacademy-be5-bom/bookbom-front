package shop.bookbom.front.domain.wish.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.wish.dto.response.WishInfoResponse;

public interface WishService {
    Page<WishInfoResponse> getWishList(Pageable pageable);

    void addWish(List<Long> books);

    void deleteWish(Long wishId);
}
