package shop.bookbom.front.security.interceptor;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.security.adapter.TokenAdapter;
import shop.bookbom.front.security.config.JwtConfig;

@Component
public class AddJwtInterceptor implements ClientHttpRequestInterceptor {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    TokenAdapter tokenAdapter;

    @Autowired
    private JwtConfig jwtConfig;

    /**
     * @param request 프론트에서 보내는 요청을 가로채 accessToken을 넣으려고 시도
     * @throws IOException
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws
            IOException {
        HttpHeaders headers = request.getHeaders();
        if (!request.getURI().getPath().equals("auth/token")) { // token을 발급받는 경우가 아니면 header에 jwt를 넣으려고 시도
            String jwt = getToken("accessToken");

            if (jwt != null && jwtConfig.validateToken(jwt)) { // jwt가 존재하고 만료되지 않았을 경우 header에 넣어줌
                headers.add("Authorization", "Bearer " + jwt);

            } else if (!jwtConfig.validateToken(jwt)) { // jwt가 존재하지만 만료되었을 경우 refresh token을 통해 요청을 보냄.

                CommonResponse commonResponse = tokenAdapter.refreshToken(getToken("refreshToken"));
                if (commonResponse.getHeader().isSuccessful()) {
                    jwt = getToken("accessToken");
                    headers.add("Authorization", "Bearer " + jwt);
                } else {
                    SecurityContextHolder.clearContext(); // refresh token도 만료되었을 경우 context를 초기화하여 로그인 페이지로 redirect시킴
                }
            }
            // jwt가 없는 경우 요청을 그대로 전송시킴
        }
        return execution.execute(request, body);
    }

    private String getToken(String token) { // 쿠키에 저장되어있는 jwt를 가져옴
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(token)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
