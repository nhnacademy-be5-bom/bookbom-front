package shop.bookbom.front.domain.category.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : shop.bookbom.front.domain.category.dto.response
 * fileName       : CategoryNameAndChildResponse
 * author         : UuLaptop
 * date           : 2024-04-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-25        UuLaptop       최초 생성
 */
@Getter
@NoArgsConstructor
public class CategoryNameAndChildResponse {
    private Long id;
    private String name;
    List<CategoryNameAndChildResponse> children;
}
