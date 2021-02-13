package com.msz.product.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "product")
@ApiModel("Conatin all the atributes required under product entity")
public class Product {

    @Id
    @ApiModelProperty("This is unique id of the product")
    private Long id;
    private String name;
    private Category category;
    private double price;
    private String  currency;
    private double discount;
    private String discountDescription;
    private List<String> imageURLs;

}
