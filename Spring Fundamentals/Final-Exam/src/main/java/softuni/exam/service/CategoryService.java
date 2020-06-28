package softuni.exam.service;

import softuni.exam.model.entity.Category;
import softuni.exam.model.entity.CategoryName;

public interface CategoryService {
    void initCategories();

    Category find(CategoryName name);
}
