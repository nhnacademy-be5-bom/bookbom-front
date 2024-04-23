package shop.bookbom.front.domain.cart.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.cart.adapter.CartAdapter;
import shop.bookbom.front.domain.cart.dto.CartItemDto;
import shop.bookbom.front.domain.cart.dto.request.CartAddRequest;
import shop.bookbom.front.domain.cart.service.CartService;

@Service
public class CartServiceImpl implements CartService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final HashOperations<String, Object, Object> redisHash;
    private final CartAdapter cartAdapter;

    @Autowired
    public CartServiceImpl(RedisTemplate<String, Object> redisTemplate, CartAdapter cartAdapter) {
        this.redisTemplate = redisTemplate;
        this.cartAdapter = cartAdapter;
        this.redisHash = redisTemplate.opsForHash();
    }

    @Override
    public void addToCart(String userId, List<CartAddRequest> requests, boolean isLoggedIn) {
        String key = "cart:" + userId;
        requests.forEach(req -> {
            String bookIdKey = String.valueOf(req.getBookId());
            CartItemDto cartItemDto = (CartItemDto) redisHash.get(key, bookIdKey);
            if (cartItemDto != null) {
                cartItemDto.updateQuantity(cartItemDto.getQuantity() + req.getQuantity());
                redisHash.put(key, bookIdKey, cartItemDto);
                return;
            }
            cartItemDto = CartItemDto.of(
                    null,
                    req.getBookId(),
                    req.getThumbnail(),
                    req.getTitle(),
                    req.getPrice(),
                    req.getDiscountPrice(),
                    req.getQuantity());
            redisHash.put(key, bookIdKey, cartItemDto);
            // 만료기간 3일
            if (!isLoggedIn) {
                redisTemplate.expire(key, 3, TimeUnit.DAYS);
            }
        });
    }

    @Override
    public List<CartItemDto> getCart(String userId) {
        String key = "cart:" + userId;
        Map<Object, Object> entries = redisHash.entries(key);
        return entries.values().stream()
                .map(CartItemDto.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteItem(String userId, Long itemId, boolean isLoggedIn) {
        String key = "cart:" + userId;
        redisHash.delete(key, String.valueOf(itemId));
    }

    @Override
    public void updateItem(String userId, Long itemId, int quantity, boolean isLoggedIn) {
        String key = "cart:" + userId;
        String bookIdKey = String.valueOf(itemId);
        CartItemDto cartItemDto = (CartItemDto) redisHash.get(key, bookIdKey);
        if (cartItemDto == null) {
            return;
        }
        cartItemDto.updateQuantity(quantity);
        redisHash.put(key, bookIdKey, cartItemDto);
    }
}
