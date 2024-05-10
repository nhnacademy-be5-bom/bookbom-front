package shop.bookbom.front.security.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserIdRole {
    private Long userId;
    private String role;

    @Builder
    public UserIdRole(Long userId, String role) {
        this.userId = userId;
        this.role = role;
    }
}
