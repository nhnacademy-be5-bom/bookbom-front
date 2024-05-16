package shop.bookbom.front.domain.cart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
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
import shop.bookbom.front.domain.cart.dto.response.CartInfoResponse;
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
        List<Long> ids = isLoggedIn ? cartAdapter.addToCart(Long.valueOf(userId), requests) : new ArrayList<>();
        List<CartItemDto> cartItems = new ArrayList<>();
        requests.forEach(req -> {
            String bookIdKey = String.valueOf(req.getBookId());
            CartItemDto item = (CartItemDto) redisHash.get(key, bookIdKey);
            Long itemId = isLoggedIn ? ids.get(requests.indexOf(req)) : null;
            item = createCartItem(item, req, itemId);
            cartItems.add(item);
        });
        saveCartToRedis(userId, cartItems, isLoggedIn);
    }

    @Override
    public List<CartItemDto> getCart(String userId, boolean isLoggedIn) {
        String key = "cart:" + userId;
        Map<Object, Object> entries = redisHash.entries(key);
        if (entries.isEmpty() && isLoggedIn) {
            CartInfoResponse cart = cartAdapter.getCart(Long.valueOf(userId));
            saveCartToRedis(userId, cart.getCartItems(), true);
            return cart.getCartItems();
        }
        return entries.values().stream()
                .map(CartItemDto.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteItem(String userId, Long bookId, boolean isLoggedIn) {
        String key = "cart:" + userId;
        redisHash.delete(key, String.valueOf(bookId));
        if (isLoggedIn) {
            cartAdapter.deleteCart(bookId);
            redisTemplate.expire(key, 10, TimeUnit.DAYS);
        } else {
            redisTemplate.expire(key, 3, TimeUnit.DAYS);
        }
    }

    @Override
    public void updateItem(String userId, Long bookId, int quantity, boolean isLoggedIn) {
        String key = "cart:" + userId;
        String bookIdKey = String.valueOf(bookId);
        CartItemDto cartItemDto = (CartItemDto) redisHash.get(key, bookIdKey);
        if (cartItemDto == null) {
            return;
        }
        cartItemDto.updateQuantity(quantity);
        redisHash.put(key, bookIdKey, cartItemDto);
        if (isLoggedIn) {
            cartAdapter.updateCart(bookId, quantity);
            redisTemplate.expire(key, 10, TimeUnit.DAYS);
        } else {
            redisTemplate.expire(key, 3, TimeUnit.DAYS);
        }
    }

    /**
     * redis에 상품을 저장하는 메서드입니다.
     *
     * @param userId     사용자 ID(비회원인 경우 UUID)
     * @param cartItems  장바구니 상품 리스트
     * @param isLoggedIn 로그인 여부
     */
    private void saveCartToRedis(String userId, List<CartItemDto> cartItems, boolean isLoggedIn) {
        String key = "cart:" + userId;
        if (!cartItems.isEmpty()) {
            Map<String, CartItemDto> cartItemsMap = new HashMap<>();
            for (CartItemDto item : cartItems) {
                cartItemsMap.put(String.valueOf(item.getBookId()), item);
            }
            redisHash.putAll(key, cartItemsMap);
            if (isLoggedIn) {
                redisTemplate.expire(key, 10, TimeUnit.DAYS);
            } else {
                redisTemplate.expire(key, 3, TimeUnit.DAYS);
            }
        }
    }

    /**
     * 장바구니 저장 요청을 CartItemDto로 만들어주는 메서드입니다.
     *
     * @param item    장바구니 상품
     * @param request 장바구니 추가 요청
     * @param itemId  장바구니 상품 ID(회원의 경우 DB에서 받아온 ID, 비회원은 null)
     * @return CartItemDto
     */
    private CartItemDto createCartItem(CartItemDto item, CartAddRequest request, Long itemId) {
        return CartItemDto.of(
                itemId,
                request.getBookId(),
                request.getThumbnail(),
                request.getTitle(),
                request.getPrice(),
                request.getDiscountPrice(),
                item == null ? request.getQuantity() : item.getQuantity() + request.getQuantity()
        );
    }
}
