package shop.bookbom.front.domain.wish.adapter;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.bookbom.front.domain.wish.dto.response.WishInfoResponse;

public interface WishAdapter {
    Page<WishInfoResponse> getWishList(Pageable pageable);

    void addWish(List<Long> books);

    void deleteWish(Long wishId);
}
