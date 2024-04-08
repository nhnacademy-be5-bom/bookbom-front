package shop.bookbom.front.domain.booktag.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.book.entity.Book;
import shop.bookbom.front.domain.tag.entity.Tag;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookTag {
    private Long id;
    private Book book;
    private Tag tag;

    @Builder
    public BookTag(Book book, Tag tag) {
        this.book = book;
        this.tag = tag;
    }
}
