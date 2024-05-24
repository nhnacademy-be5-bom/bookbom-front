package shop.bookbom.front.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonPage<T> extends PageImpl<T> implements Serializable {
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CommonPage(
            @JsonProperty("content") List<T> content,
            @JsonProperty("number") int number,
            @JsonProperty("size") int size,
            @JsonProperty("totalElements") Long totalElements,
            @JsonProperty("pageable") JsonNode pageable,
            @JsonProperty("last") boolean last,
            @JsonProperty("totalPages") int totalPages,
            @JsonProperty("sort") JsonNode sort
    ) {
        super(content, PageRequest.of(number, size), totalElements);
    }

    public CommonPage(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public CommonPage(List<T> content) {
        super(content);
    }

    public CommonPage() {
        super(new ArrayList<>());
    }
}
