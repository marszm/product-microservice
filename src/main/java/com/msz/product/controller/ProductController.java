package com.msz.product.controller;

import com.msz.product.model.Product;
import com.msz.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1")
@AllArgsConstructor
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @PostMapping("/addProduct")
    ResponseEntity<Product> addProduct(@RequestBody Product product) {
        String status = productService.addProduct(product);
        logger.info("product added status", status);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/productList")
    List<Product> productList() {
        return productService.listAllProducts();
    }

    @GetMapping(/"product/{id}")
    Optional<Product> productById(@PathVariable Long id) {
        return productService.productById(id);
    }

    @PutMapping("/productUpdate")
    String updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/product/{id}")
    String deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }

}
