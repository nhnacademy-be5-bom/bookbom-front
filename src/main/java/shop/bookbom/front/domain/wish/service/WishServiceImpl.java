package shop.bookbom.front.domain.wish.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.wish.adapter.WishAdapter;
import shop.bookbom.front.domain.wish.dto.response.WishInfoResponse;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService {
    private final WishAdapter wishAdapter;

    @Override
    public Page<WishInfoResponse> getWishList(Pageable pageable) {
        return wishAdapter.getWishList(pageable);
    }

    @Override
    public void addWish(List<Long> books) {
        wishAdapter.addWish(books);
    }

    @Override
    public void deleteWish(Long wishId) {
        wishAdapter.deleteWish(wishId);
    }
}
