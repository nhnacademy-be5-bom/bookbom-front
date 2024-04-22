package shop.bookbom.front.domain.category.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.bookbom.front.common.CommonListResponse;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.category.adaptor.CategoryAdaptor;
import shop.bookbom.front.domain.category.dto.CategoryDTO;
import shop.bookbom.front.domain.category.dto.response.CategoryDepthResponse;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryAdaptor categoryAdaptor;

    public CategoryDepthResponse getAllCategories() {
        CommonResponse<?> response = categoryAdaptor.getAllCategories();
        // null 없으므로 체크하지 않음
        return (CategoryDepthResponse) response.getResult();
    }

    public List<CategoryDTO> getDepthOneCategories() {
        CommonListResponse<CategoryDTO> response = categoryAdaptor.getDepthOneCategories();
        // null 없으므로 체크하지 않음
        return response.getResult();
    }

    public List<CategoryDTO> getChildCategoriesById(Long categoryId) {
        CommonListResponse<?> response = categoryAdaptor.getChildCategoriesOf(categoryId);
        // null 없으므로 체크하지 않음
        return (List<CategoryDTO>) response.getResult();
    }
}
