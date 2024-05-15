package shop.bookbom.front.domain.wish.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.wish.dto.response.WishInfoResponse;

public interface WishService {
    Page<WishInfoResponse> getWishList(Pageable pageable);
}
