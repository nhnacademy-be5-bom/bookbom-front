package shop.bookbom.front.domain.book.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.bookauthor.entity.BookAuthor;
import shop.bookbom.front.domain.bookcategory.entity.BookCategory;
import shop.bookbom.front.domain.booktag.entity.BookTag;
import shop.bookbom.front.domain.pointrate.entity.PointRate;
import shop.bookbom.front.domain.publisher.entity.Publisher;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    private Long id;
    private String title;
    private String description;
    private String index;
    private LocalDate pubDate;
    private String isbn10;
    private String isbn13;
    private Integer cost;
    private Integer discountCost;
    private Boolean packagable;
    private Long views;
    private BookStatus status;
    private Integer stock;
    private Publisher publisher;
    private PointRate pointRate;
    private List<BookAuthor> authors = new ArrayList<>();
    private List<BookTag> tags = new ArrayList<>();
    private List<BookCategory> categories = new ArrayList<>();

    @Builder
    public Book(
            String title,
            String description,
            String index,
            LocalDate pubDate,
            String isbn10,
            String isbn13,
            Integer cost,
            Integer discountCost,
            Boolean packagable,
            Long views,
            BookStatus status,
            Integer stock,
            Publisher publisher,
            PointRate pointRate
    ) {
        this.title = title;
        this.description = description;
        this.index = index;
        this.pubDate = pubDate;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.cost = cost;
        this.discountCost = discountCost;
        this.packagable = packagable;
        this.views = views;
        this.status = status;
        this.stock = stock;
        this.publisher = publisher;
        this.pointRate = pointRate;
    }
}
