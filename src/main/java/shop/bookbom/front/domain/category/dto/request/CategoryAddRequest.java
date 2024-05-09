package shop.bookbom.front.domain.category.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryAddRequest {
    private Long id;
    private Long parentId;
    private String name;

    @Builder
    public CategoryAddRequest(Long id, Long parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }
}
