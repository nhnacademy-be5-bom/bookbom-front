package shop.bookbom.front.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.category.adaptor.CategoryAdaptor;
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

    public CategoryDepthResponse getDepthOneCategories() {
        CommonResponse<?> response = categoryAdaptor.getDepthOneCategories();
        // null 없으므로 체크하지 않음
        return (CategoryDepthResponse) response.getResult();
    }

    public CategoryDepthResponse getChildCategoriesById(Long categoryId) {
        CommonResponse<?> response = categoryAdaptor.getChildCategories(categoryId);
        // null 없으므로 체크하지 않음
        return (CategoryDepthResponse) response.getResult();
    }
}
