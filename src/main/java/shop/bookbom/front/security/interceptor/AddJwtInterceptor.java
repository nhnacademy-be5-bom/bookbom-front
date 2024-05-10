package shop.bookbom.front.security.interceptor;

import java.io.IOException;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.security.adapter.SecurityAdapter;
import shop.bookbom.front.security.config.JwtConfig;

@Slf4j
@RequiredArgsConstructor
public class AddJwtInterceptor implements ClientHttpRequestInterceptor {

    private final SecurityAdapter securityAdapter;

    private final JwtConfig jwtConfig;

    /**
     * @param request 프론트에서 보내는 요청을 가로채 accessToken을 넣으려고 시도
     * @throws IOException
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws
            IOException {
        HttpHeaders headers = request.getHeaders();

        log.debug("now requesting uri is : " + request.getURI().getPath());
        if (!request.getURI().getPath().equals("auth/token")
                || !request.getURI().getPath().startsWith("/shop/open")) { // token을 발급받는 경우가 아니면 header에 jwt를 넣으려고 시도
            log.debug("try to set jwt header");
            String jwt = getToken("accessToken");

            if (jwt != null && jwtConfig.validateToken(jwt)) { // jwt가 존재하고 만료되지 않았을 경우 header에 넣어줌
                log.debug("accessToken exist! set jwt header");
                headers.add("Authorization", "Bearer " + jwt);

            } else if (!jwtConfig.validateToken(jwt)) { // jwt가 존재하지만 만료되었을 경우 refresh token을 통해 요청을 보냄.
                log.debug("accessToken have to refresh");
                String refreshToken = getToken("refreshToken");
                CommonResponse commonResponse = refreshToken == null ? null : securityAdapter.refresh(refreshToken);

                if (commonResponse != null
                        && commonResponse.getHeader().isSuccessful()
                        && commonResponse.getResult() != null) {
                    log.debug("refresh success");
                    jwt = (String) commonResponse.getResult();
                    headers.add("Authorization", "Bearer " + jwt);
                    ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse()
                            .addCookie(new Cookie("accessToken", jwt));
                } else {
                    log.debug("refresh not success, clear context");
                    SecurityContextHolder.clearContext(); // refresh token도 만료되었을 경우 context를 초기화
                    // TODO 로그인페이지로 REDIRECT?
                }
            }
            // jwt가 없는 경우 요청을 그대로 전송시킴
        }
        return execution.execute(request, body);
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
            log.info("no cookies");
        }
        return null;
    }
}
