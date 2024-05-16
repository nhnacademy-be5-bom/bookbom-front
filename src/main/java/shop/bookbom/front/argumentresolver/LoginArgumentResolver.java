package shop.bookbom.front.argumentresolver;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import shop.bookbom.front.annotation.Login;
import shop.bookbom.front.common.JwtSecretKeyProperties;
import shop.bookbom.front.common.dto.UserDto;

@Component
@RequiredArgsConstructor
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtSecretKeyProperties jwtSecretKeyProperties;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean isUserDtoType = UserDto.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginAnnotation && isUserDtoType;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        String secretKey = jwtSecretKeyProperties.getSecretKey();
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        Cookie loginCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("accessToken"))
                .findFirst()
                .orElse(null);
        if (loginCookie == null) {
            return null;
        }
        String accessToken = loginCookie.getValue();
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
        if (claims == null || claims.isEmpty()) {
            return null;
        }
        return new UserDto(claims.get("userId", Long.class), claims.get("role", String.class));
    }
}
