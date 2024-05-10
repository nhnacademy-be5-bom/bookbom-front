package shop.bookbom.front.security.filter;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import shop.bookbom.front.common.exception.ErrorCode;
import shop.bookbom.front.domain.signin.dto.SignInDTO;
import shop.bookbom.front.security.adapter.SecurityAdapter;
import shop.bookbom.front.security.dto.AccessNRefreshTokenDto;
import shop.bookbom.front.security.exception.RedirectException;
import shop.bookbom.front.security.token.UserEmailJwtAuthenticationToken;

/**
 * jwt 토큰을 받아와 AUTHENTICATION을 등록해주는 필터
 * -> 토큰 요청은 UserEmailPasswordAuthenticationProvider에서
 * SecurityAdapter가 진행
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityAdapter securityAdapter;

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // UserEmailPasswordAuthenticationProvider를 통해 UserIdRoleAuthentication Token을 얻는다
        AccessNRefreshTokenDto accessNRefreshTokenDto =
                securityAdapter.getTokens(SignInDTO.builder().email(request.getParameter("email"))
                        .password(request.getParameter("password")).build());

        setCookie("accessToken", accessNRefreshTokenDto.getAccessToken(), response);
        setCookie("refreshToken", accessNRefreshTokenDto.getRefreshToken(), response);

        Authentication a = new UserEmailJwtAuthenticationToken(request.getParameter("email"),
                accessNRefreshTokenDto.getAccessToken());
        a = authenticationManager.authenticate(a);

        SecurityContextHolder.getContext()
                .setAuthentication(a);

        try {
            response.sendRedirect("/");
        } catch (Exception e) {
            throw new RedirectException(ErrorCode.REDIRECT_FAILED);
        }
        return a;
    }

    public void setCookie(String name, String token, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(true);
        response.addCookie(cookie);
    }
}
