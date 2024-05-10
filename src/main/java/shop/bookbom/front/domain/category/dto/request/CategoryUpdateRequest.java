package shop.bookbom.front.domain.category.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryUpdateRequest {
    private Long id;
    private String name;

    @Builder
    public CategoryUpdateRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
