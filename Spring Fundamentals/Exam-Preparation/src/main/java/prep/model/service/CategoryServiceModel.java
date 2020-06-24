package prep.model.service;

import prep.model.entity.CategoryName;

public class CategoryServiceModel {
    private CategoryName categoryName;
    private String description;

    public CategoryServiceModel() {
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
