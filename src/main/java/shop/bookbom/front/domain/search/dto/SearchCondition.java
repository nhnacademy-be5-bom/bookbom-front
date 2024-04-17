package shop.bookbom.front.domain.search.dto;

import lombok.Getter;

@Getter
public enum SearchCondition {
    BOOK_TITLE("도서명"),
    AUTHOR("작가명"),
    PUBLISHER("출판사"),
    NONE("없음"),
    ;

    private final String value;

    SearchCondition(String value) {
        this.value = value;
    }
}
