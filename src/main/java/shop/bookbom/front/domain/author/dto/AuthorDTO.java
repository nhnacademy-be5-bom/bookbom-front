package shop.bookbom.front.domain.author.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : shop.bookbom.front.domain.author.dto
 * fileName       : AuthorDTO
 * author         : UuLaptop
 * date           : 2024-04-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-17        UuLaptop       최초 생성
 */
@Getter
@NoArgsConstructor
public class AuthorDTO {
    private Long id;
    private String role;
    private String name;

    @Builder
    public AuthorDTO(Long id, String role, String name) {
        this.id = id;
        this.role = role;
        this.name = name;
    }
}
