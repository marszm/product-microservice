package com.msz.product.service;

import com.msz.product.ProductRepository;
import com.msz.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService() {
    }

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String addProduct(Product product) {

        productRepository.save(product);
        return "product saved into db";
    }

    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

}
