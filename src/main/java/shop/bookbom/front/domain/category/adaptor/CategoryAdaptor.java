package shop.bookbom.front.domain.category.adaptor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.bookbom.front.common.CommonListResponse;
import shop.bookbom.front.common.CommonResponse;
import shop.bookbom.front.domain.category.dto.CategoryDTO;
import shop.bookbom.front.domain.category.dto.response.CategoryDepthResponse;

@FeignClient(value = "BOOKBOM-FRONT-CATEGORY", path = "/shop", url = "${bookbom.gateway-url}")
public interface CategoryAdaptor {

    @GetMapping("/category/all")
    CommonResponse<CategoryDepthResponse> getAllCategories();

    @GetMapping("/category/depth1")
    CommonListResponse<CategoryDTO> getDepthOneCategories();

    @GetMapping("/category/{parentId}")
    CommonListResponse<CategoryDTO> getChildCategoriesOf(@PathVariable("parentId") Long parentId);

}
