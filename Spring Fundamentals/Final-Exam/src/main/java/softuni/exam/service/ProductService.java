package softuni.exam.service;

import softuni.exam.model.entity.Product;
import softuni.exam.model.service.ProductServiceModel;
import softuni.exam.model.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel);

    List<ProductViewModel> findAllProducts();
    String productPriceSum();
    void delete(String id);
    void deleteAll();
}
