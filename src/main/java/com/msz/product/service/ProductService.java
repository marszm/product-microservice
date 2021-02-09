package com.msz.product.service;

import com.msz.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public List<Product> listAllProducts;

    public String addProduct(Product product) {

        return "success";
    }

    public List<Product> listAllProducts() {
        return listAllProducts;
    }

}
