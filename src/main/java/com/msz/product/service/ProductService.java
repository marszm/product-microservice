package com.msz.product.service;

import com.msz.product.ProductRepository;
import com.msz.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

//    public ProductService() {
//    }

//    public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    public String addProduct(Product product) {

        productRepository.save(product);
        return "product saved into db";
    }

    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

}
