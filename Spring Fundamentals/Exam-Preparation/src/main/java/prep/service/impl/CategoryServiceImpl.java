package prep.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import prep.model.entity.Category;
import prep.model.entity.CategoryName;
import prep.model.service.CategoryServiceModel;
import prep.repository.CategoryRepository;
import prep.service.CategoryService;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initCategories() {
        if(this.categoryRepository.count()==0){
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                    this.categoryRepository.save(new Category(categoryName,
                            String.format("Description for %s",
                                    categoryName.name())));
                    });
        }
    }

    @Override
    public Category find(CategoryName categoryName) {
        return
                this.categoryRepository
                        .findByCategoryName(categoryName).orElse(null);
    }
}
