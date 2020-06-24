package prep.service;

import prep.model.entity.Category;
import prep.model.entity.CategoryName;
import prep.model.service.CategoryServiceModel;

public interface CategoryService {
    void initCategories();

    Category find(CategoryName categoryName);
}
