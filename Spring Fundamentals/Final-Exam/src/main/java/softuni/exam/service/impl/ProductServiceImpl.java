package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.model.entity.Product;
import softuni.exam.model.service.ProductServiceModel;
import softuni.exam.model.view.ProductViewModel;
import softuni.exam.repository.ProductRepository;
import softuni.exam.service.CategoryService;
import softuni.exam.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        product.setCategory(this.categoryService.find(productServiceModel.getCategory().getName()));
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public List<ProductViewModel> findAllProducts() {

        return this.productRepository.findAll().stream()
                .map(product -> {
                    ProductViewModel productViewModel = this.modelMapper.map(product, ProductViewModel.class);

                    return productViewModel;
                })
                .collect(Collectors.toList());

    }


    @Override
    public void delete(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.productRepository.deleteAll();
    }


}
