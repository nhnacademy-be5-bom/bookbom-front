package shop.bookbom.front.domain.book.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthorResponse {
    private Long id;
    private String name;
    private String role;
}
