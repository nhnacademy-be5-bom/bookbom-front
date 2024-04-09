package shop.bookbom.front.domain.category.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.category.dto.CategoryDTO;

@Getter
@NoArgsConstructor
public class CategoryDepthResponse {
    private List<CategoryDTO> categories;

    @Builder
    public CategoryDepthResponse(List<CategoryDTO> categories) {
        this.categories = categories;
    }
}