package shop.bookbom.front.domain.category.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    private Long id;
    private String name;
    private Status status;
    private Category parent;
    private List<Category> child = new ArrayList<>();

    @Builder
    public Category(String name, Status status, Category parent) {
        this.name = name;
        this.status = status;
        this.parent = parent;
    }

    public void assignParent(Category parent) {
        this.parent = parent;
    }

    public void addChildCategory(Category child) {
        this.child.add(child);
        child.assignParent(this);
    }
}
