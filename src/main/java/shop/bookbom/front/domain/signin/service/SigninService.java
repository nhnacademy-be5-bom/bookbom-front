package shop.bookbom.front.domain.signin.service;


import java.util.Collection;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("SignInService")
public class SigninService {

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    public boolean isUser() {
        return hasRole("USER");
    }

    public boolean isAdmin() {
        return hasRole("ADMIN");
    }

    public boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean hasRoleMember = authorities.stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_" + role));

        return hasRoleMember;
    }

}
