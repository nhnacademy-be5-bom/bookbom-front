package shop.bookbom.front.security.token;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UserEmailJwtAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public UserEmailJwtAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public UserEmailJwtAuthenticationToken(Object principal, Object credentials,
                                           Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
