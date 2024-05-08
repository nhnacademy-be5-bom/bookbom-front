package shop.bookbom.front.domain.signin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInDTO {
    private String email;
    private String password;

    @Builder
    public SignInDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
