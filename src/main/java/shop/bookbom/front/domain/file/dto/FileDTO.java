package shop.bookbom.front.domain.file.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : shop.bookbom.front.domain.file.dto
 * fileName       : FileDTO
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
public class FileDTO {
    private String url;
    private String extension;

    @Builder
    public FileDTO(String url, String extension) {
        this.url = url;
        this.extension = extension;
    }
}
