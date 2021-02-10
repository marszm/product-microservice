package com.msz.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "product")
public class Product {

    @Id
    private Long id;
    private String name;
    private Category category;
    private double price;
    private double discount;
    private String discountDescription;

}
