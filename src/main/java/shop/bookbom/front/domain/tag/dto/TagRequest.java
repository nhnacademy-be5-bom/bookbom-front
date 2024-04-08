package shop.bookbom.front.domain.tag.dto;

import lombok.Getter;
import shop.bookbom.front.domain.category.entity.Status;

//태그 등록을 요청받았을 때 넘어온 정보를 받아오는 dto
@Getter
public class TagRequest {
    String name;
    Status status;
}
