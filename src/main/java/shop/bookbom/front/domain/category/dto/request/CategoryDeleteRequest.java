package shop.bookbom.front.domain.category.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.category.entity.Status;

@Getter
@NoArgsConstructor
public class CategoryDeleteRequest {
    private Long id;
    private static final Status status = Status.DEL;

    @Builder
    public CategoryDeleteRequest(Long id) {
        this.id = id;
    }
}
