package shop.bookbom.front.domain.category.service;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import shop.bookbom.front.common.CommonListResponse;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.category.adapter.CategoryAdapter;
import shop.bookbom.front.domain.category.dto.CategoryDTO;
import shop.bookbom.front.domain.category.dto.response.CategoryDepthResponse;
import shop.bookbom.front.domain.category.dto.response.CategoryNameAndChildResponse;

@Service("CategoryService")
@RequiredArgsConstructor
@CacheConfig(cacheNames = "category")
public class CategoryService {
    private final CategoryAdapter categoryAdaptor;

    public CategoryDepthResponse getAllCategories() {
        CommonResponse<?> response = categoryAdaptor.getAllCategories();
        // null 없으므로 체크하지 않음
        return (CategoryDepthResponse) response.getResult();
    }

    @Cacheable(key = "'depth1'", unless = "#result.isEmpty()")
    public List<CategoryDTO> getDepthOneCategories() {
        CommonListResponse<CategoryDTO> response = categoryAdaptor.getDepthOneCategories();
        // null 없으므로 체크하지 않음
        return response.getResult();
    }

    @Cacheable(key = "'childCategory' + #categoryId", unless = "#result == null")
    public CommonListResponse<CategoryDTO> getChildCategoriesById(Long categoryId) {
        return categoryAdaptor.getChildCategoriesOf(categoryId);
    }

    public CategoryNameAndChildResponse getCategoryNameAndChildCategoriesByCategoryId(Long categoryId) {
        CommonResponse<CategoryNameAndChildResponse> response =
                categoryAdaptor.getNameAndChildCategoriesOf(categoryId);
        // null 없으므로 체크하지 않음
        return Objects.requireNonNull(response).getResult();
    }

}
