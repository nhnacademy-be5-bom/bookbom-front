package shop.bookbom.front.domain.bookauthor.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.author.entity.Author;
import shop.bookbom.front.domain.book.entity.Book;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookAuthor {

    private Long id;

    private String role;

    private Book book;

    private Author author;

    @Builder
    public BookAuthor(String role, Book book, Author author) {
        this.role = role;
        this.book = book;
        this.author = author;
    }
}
