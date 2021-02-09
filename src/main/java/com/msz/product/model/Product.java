package com.msz.product.model;

import lombok.Data;

@Data
public class Product {

    private Long id;
    private String name;
    private Category category;
    private double price;
    private double discount;
    private String discountDescription;

}
