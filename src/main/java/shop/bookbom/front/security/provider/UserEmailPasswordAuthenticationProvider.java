package shop.bookbom.front.security.provider;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import shop.bookbom.front.security.config.JwtConfig;
import shop.bookbom.front.security.entity.UserIdRole;
import shop.bookbom.front.security.token.UserEmailJwtAuthenticationToken;
import shop.bookbom.front.security.token.UserIdPasswordAuthenticationToken;

/**
 * user의 email과 password를 proxy를 통해 확인.
 * adapter를 통해 받은 값이 true이면 authentication을 return
 */
public class UserEmailPasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtConfig jwtConfig;


    @Value("${bookbom.front-url}")
    String frontUrl;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String jwt = String.valueOf(authentication.getCredentials());
        return getUserIdRoleToken(jwt);
    }

    public Authentication getUserIdRoleToken(String accessToken) {

        UserIdRole userIdRole = jwtConfig.getUserIdRole(accessToken);

        return new UserIdPasswordAuthenticationToken(userIdRole.getUserId(), null,
                Collections.singleton(new SimpleGrantedAuthority(
                        userIdRole.getRole())));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserEmailJwtAuthenticationToken.class.isAssignableFrom(authentication);
    }


}
