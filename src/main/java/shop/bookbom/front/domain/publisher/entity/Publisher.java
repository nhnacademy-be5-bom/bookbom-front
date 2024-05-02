package shop.bookbom.front.domain.publisher.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Publisher {
    private String name;

    @Builder
    public Publisher(String name) {
        this.name = name;
    }
}
