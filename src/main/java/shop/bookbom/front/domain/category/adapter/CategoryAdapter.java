package shop.bookbom.front.domain.category.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.bookbom.front.common.CommonListResponse;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.category.dto.CategoryDTO;
import shop.bookbom.front.domain.category.dto.response.CategoryDepthResponse;
import shop.bookbom.front.domain.category.dto.response.CategoryNameAndChildResponse;

@FeignClient(value = "BOOKBOM-FRONT-CATEGORY", path = "/shop", url = "${bookbom.gateway-url}")
public interface CategoryAdapter {

    @GetMapping("/category/all")
    CommonResponse<CategoryDepthResponse> getAllCategories();

    @GetMapping("/category/depth1")
    CommonListResponse<CategoryDTO> getDepthOneCategories();

    @GetMapping("/category/{parentId}")
    CommonListResponse<CategoryDTO> getChildCategoriesOf(@PathVariable("parentId") Long parentId);

    @GetMapping("/category/nameandchild/{parentId}")
    CommonResponse<CategoryNameAndChildResponse> getNameAndChildCategoriesOf(
            @PathVariable("parentId") Long parentId);


}
