package shop.bookbom.front.security.provider;

import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.bookbom.front.domain.signin.adaptor.SignInAdaptor;
import shop.bookbom.front.domain.signin.dto.SignInDTO;
import shop.bookbom.front.security.config.JwtConfig;
import shop.bookbom.front.security.dto.AccessNRefreshTokenDto;
import shop.bookbom.front.security.entity.UserIdRole;
import shop.bookbom.front.security.token.UserEmailPasswordAuthenticationToken;
import shop.bookbom.front.security.token.UserIdPasswordAuthenticationToken;

/**
 * user의 email과 password를 proxy를 통해 확인.
 * adapter를 통해 받은 값이 true이면 authentication을 return
 */
@Slf4j
public class UserEmailPasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private SignInAdaptor signInAdaptor;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String rawPassword = String.valueOf(authentication.getCredentials());
//        String password = passwordEncoder.encode(
//                String.valueOf(authentication.getCredentials()) + "bom" + authentication.getName());
//        log.info(password);
//                passwordEncoder.encode(rawPassword));
        // gateway로 요청을 보내 jwt 토큰을 받아온다.
        SignInDTO build = SignInDTO.builder().email(email).password(rawPassword).build();
        AccessNRefreshTokenDto accessNRefreshTokenDto
                = signInAdaptor.signIn(build);

        return getUserIdRoleToken(accessNRefreshTokenDto.getAccessToken());
    }

    public Authentication getUserIdRoleToken(String accessToken) {

        UserIdRole userIdRole = jwtConfig.getUserIdRole(accessToken);
        log.debug("now doing UserIdRole. now request is : " + userIdRole.getUserId());

        return new UserIdPasswordAuthenticationToken(userIdRole.getUserId(), null,
                Collections.singleton(new SimpleGrantedAuthority(
                        userIdRole.getRole())));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserEmailPasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }


}
