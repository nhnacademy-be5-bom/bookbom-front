package shop.bookbom.front.domain.bookcategory.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.book.entity.Book;
import shop.bookbom.front.domain.category.entity.Category;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookCategory {
    private Long id;
    private Book book;
    private Category category;

    @Builder
    public BookCategory(Book book, Category category) {
        this.book = book;
        this.category = category;
    }
}
