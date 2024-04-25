package shop.bookbom.front.domain.tag.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : shop.bookbom.front.domain.tag.dto
 * fileName       : TagDTO
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
public class TagDTO {
    private Long id;
    private String name;

    @Builder
    public TagDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
