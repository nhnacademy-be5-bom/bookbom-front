package shop.bookbom.front.domain.wish.service;

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
}
