package com.msz.product.service;

import com.msz.product.repository.ProductRepository;
import com.msz.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public String addProduct(Product product) {
        log.info("adding product");
        productRepository.save(product);
        return "product added";
    }

    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> productCategoryList(String category) {
        return productRepository.findByCategory(category);
    }

    public Optional<Product> productById(Long id) {
        return productRepository.findById(id);
    }

    public String updateProduct(Product product) {
        productRepository.save(product);
        return "product updated";
    }

    public String deleteProductById(Long id) {
        productRepository.deleteById(id);
        return "product deleted";
    }

}
