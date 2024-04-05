package shop.bookbom.front.domain.author.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Author {
    private Long id;
    private String name;

    @Builder
    public Author(String name) {
        this.name = name;
    }
}
