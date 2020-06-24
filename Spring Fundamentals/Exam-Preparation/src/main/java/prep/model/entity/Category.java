package prep.model.entity;

import javax.persistence.*;

@Entity
@Table(name="categories")
public class Category extends BaseEntity {
    private CategoryName categoryName;
    private String description;

    public Category() {
    }
    public Category(CategoryName categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;

    }


    @Enumerated()
    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName name) {
        this.categoryName = name;
    }

    @Column(name="description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
