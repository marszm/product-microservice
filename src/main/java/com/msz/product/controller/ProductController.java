package com.msz.product.controller;

import com.msz.product.model.Product;
import com.msz.product.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("v1")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    @ApiOperation("Used to add product into db")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product) {
        String status = productService.addProduct(product);
        log.info(status);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/products")
    @ApiOperation("Used to list all products from")
    public List<Product> productList() {
        return productService.listAllProducts();
    }

    @GetMapping("/products/{id}")
    public Optional<Product> productById(@PathVariable String id) {
        return productService.productById(id);
    }

    @PutMapping("/products")
    public String updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProductById(@PathVariable String id) {
        return productService.deleteProductById(id);
    }

}
