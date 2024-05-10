package shop.bookbom.front.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import shop.bookbom.front.common.exception.BaseException;
import shop.bookbom.front.common.exception.ErrorCode;
import shop.bookbom.front.security.entity.UserIdRole;

@RequiredArgsConstructor
public class JwtConfig {
    @Value("${JWT_SECRET_KEY}")
    private String secretKey;

    public UserIdRole getUserIdRole(String accessToken) {
        if (!validateToken(accessToken)) {
            throw new BaseException(ErrorCode.JWT_NOT_VALIDATE);
        }
        Claims claims = getClaims(accessToken);
        return UserIdRole.builder()
                .userId(claims.get("userId", Long.class))
                .role(claims.get("role", String.class))
                .build();
    }

    // 토큰의 유효성 확인
    public boolean validateToken(String jwtToken) {
        try {
            Claims claims = getClaims(jwtToken);
            return claims.getExpiration().before(new Date()) == false;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaims(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }
}
