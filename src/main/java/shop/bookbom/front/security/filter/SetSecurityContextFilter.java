package shop.bookbom.front.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.security.adapter.SecurityAdapter;
import shop.bookbom.front.security.config.JwtConfig;
import shop.bookbom.front.security.token.UserEmailJwtAuthenticationToken;

@Slf4j
public class SetSecurityContextFilter extends OncePerRequestFilter {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private SecurityAdapter securityAdapter;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (getToken("accessToken") == null || getToken("refreshToken") == null) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!doesRequestMatches(request)) {
            log.info(request.getRequestURI());
            String accessToken = getToken("accessToken");

            if (!jwtConfig.validateToken(accessToken)) {
                CommonResponse commonResponse = securityAdapter.refresh(getToken("refreshToken"));
                accessToken = commonResponse.getHeader().isSuccessful() ? (String) commonResponse.getResult() : null;
            }
            Authentication a = new UserEmailJwtAuthenticationToken(request.getParameter("email"), accessToken);

            a = authenticationManager.authenticate(a);
            SecurityContextHolder.getContext()
                    .setAuthentication(a);

            log.info(String.valueOf(SecurityContextHolder.getContext().getAuthentication()));
        }
        filterChain.doFilter(request, response);
    }

    private boolean doesRequestMatches(HttpServletRequest request) {
        return (request.getRequestURI().equals("/dosignin")
                || request.getRequestURI().equals("/logout")
                || request.getRequestURI().equals("/sign-up")
                || request.getRequestURI().startsWith("/rest")
                || request.getRequestURI().startsWith("/js")
                || request.getRequestURI().startsWith("/css")
                || request.getRequestURI().startsWith("/images"));
    }

    private String getToken(String token) { // 쿠키에 저장되어있는 jwt를 가져옴
        Cookie[] cookies =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(token)) {
                    return cookie.getValue();
                }
            }
        } else {
            log.error("no cookies");
        }
        return null;
    }
}
