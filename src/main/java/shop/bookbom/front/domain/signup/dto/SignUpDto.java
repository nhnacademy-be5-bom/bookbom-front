package shop.bookbom.front.domain.signup.dto;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class SignUpDto {

    @NotBlank(message = "아이디를 입력하세요")
    @Email(message = "유효한 이메일 주소를 입력하세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다")
    private String password;

    @Size(min = 2, message = "이름은 최소 2자 이상이어야 합니다")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String phoneNumber;

    @Size(min = 1, message = "닉네임은 최소 1자 이상이어야 합니다")
    private String nickname;

    @Size(min = 5, message = "우편번호는 최소 5자 이상이어야 합니다")
    private String addressNumber;

    private String address;

    private String addressDetail;

}
