package shop.bookbom.front.domain.cart.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.cart.adapter.CartAdapter;
import shop.bookbom.front.domain.cart.dto.CartAddRequest;
import shop.bookbom.front.domain.cart.dto.CartInfoResponse;
import shop.bookbom.front.domain.cart.dto.response.CartResponse;
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
        requests.forEach(request -> {
            String key = "cart:" + userId;
            String bookIdKey = String.valueOf(request.getBookId());
            String quantity = String.valueOf(redisHash.get(key, bookIdKey));
            if (quantity == null) {
                redisHash.put(key, bookIdKey, String.valueOf(request.getQuantity()));
            } else {
                redisHash.put(key, bookIdKey, String.valueOf(Integer.parseInt(quantity) + request.getQuantity()));
            }
        });

        if (isLoggedIn) {
            cartAdapter.addToCart(Long.valueOf(userId), requests);
            redisTemplate.expire(userId, 30, TimeUnit.DAYS);
        } else {
            redisTemplate.expire(userId, 7, TimeUnit.DAYS);
        }
    }
}
