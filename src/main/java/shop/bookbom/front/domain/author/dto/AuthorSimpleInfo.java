package shop.bookbom.front.domain.author.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : shop.bookbom.front.domain.author.dto
 * fileName       : AuthorSimpleInfo
 * author         : UuLaptop
 * date           : 2024-04-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-17        UuLaptop       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthorSimpleInfo {
    private String role;
    private String name;
}
