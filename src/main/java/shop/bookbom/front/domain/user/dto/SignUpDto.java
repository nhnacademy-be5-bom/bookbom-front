package shop.bookbom.front.domain.user.dto;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import shop.bookbom.front.domain.user.dto.request.SignUpRequest;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpDto {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private LocalDate birthDate;
    private String phoneNumber;
    private String addressNumber;
    private String address;
    private String addressDetail;

    public static SignUpDto of(SignUpRequest dto, String encodePassword) {
        return new SignUpDto(
                dto.getEmail(),
                encodePassword,
                dto.getName(),
                dto.getNickname(),
                dto.getBirthDate(),
                dto.getPhoneNumber(),
                dto.getAddressNumber(),
                dto.getAddress(),
                dto.getAddressDetail()
        );
    }
}
