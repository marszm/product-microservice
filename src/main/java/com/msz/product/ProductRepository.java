package com.msz.product;

import com.msz.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, Long> {
    @Query("{'Category.name':?0}")
    List<Product> findByCategory(String category);
}
