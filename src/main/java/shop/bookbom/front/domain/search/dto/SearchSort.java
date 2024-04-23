package shop.bookbom.front.domain.search.dto;

import lombok.Getter;

@Getter
public enum SearchSort {
    POPULAR("인기도순"),
    LATEST("최신순"),
    LOWEST_PRICE("낮은가격순"),
    HIGHEST_PRICE("높은가격순"),
    OLDEST("상품명순"),
    NONE("없음"),
    ;

    private final String value;

    SearchSort(String value) {
        this.value = value;
    }
}
