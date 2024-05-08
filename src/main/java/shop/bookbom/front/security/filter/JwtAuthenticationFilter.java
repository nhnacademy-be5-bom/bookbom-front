package shop.bookbom.front.security.filter;


import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import shop.bookbom.front.common.exception.ErrorCode;
import shop.bookbom.front.security.exception.RedirectException;
import shop.bookbom.front.security.token.UserEmailPasswordAuthenticationToken;

/**
 * jwt 토큰을 검증하는 필터
 * -> 토큰
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        log.info("now doing jwtAuthenticationFilter");
        Authentication a = new UserEmailPasswordAuthenticationToken(request.getParameter("email"),
                request.getParameter("password"));

        // UserEmailPasswordAuthenticationProvider를 통해 UserIdRoleAuthentication Token을 얻는다
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

    protected void doFilterInternal(HttpServletRequest request) throws IOException {
    }
}
