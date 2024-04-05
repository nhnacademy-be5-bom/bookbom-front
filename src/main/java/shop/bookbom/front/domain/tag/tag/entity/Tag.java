package shop.bookbom.front.domain.tag.tag.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.category.entity.Status;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {
    private Long id;
    private String name;
    private Status status;

    @Builder
    public Tag(String name, Status status) {
        this.name = name;
        this.status = status;
    }
}
