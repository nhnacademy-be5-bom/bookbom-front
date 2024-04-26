package shop.bookbom.front.domain.publisher.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : shop.bookbom.front.domain.publisher.entity.dto
 * fileName       : PublisherSimpleInformation
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
public class PublisherSimpleInformation {
    private String name;

    @Builder
    public PublisherSimpleInformation(String name) {
        this.name = name;
    }
}
