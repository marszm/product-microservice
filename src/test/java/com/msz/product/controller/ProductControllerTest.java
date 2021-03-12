package com.msz.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msz.product.model.Category;
import com.msz.product.model.Product;
import com.msz.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetSingleProduct() throws Exception {
        // given
        Product newProduct = new Product();
        Category newCategory = new Category();
        newCategory.setId(222L);
        newCategory.setName("Mariii");
        newCategory.setBrand("najki");
        newProduct.setCategory(newCategory);
        newProduct.setId("1");
        newProduct.setName("Mariusz");
        newProduct.setDiscount(11);
        newProduct.setCurrency("dollar");
        newProduct.setDiscountDescription("year end sale offer");
        productRepository.save(newProduct);
        // when
        MvcResult mvcResult = mockMvc.perform(get("/v1/products/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        // then
        Product product = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Product.class);
        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(newProduct.getId());
        assertThat(product.getName()).isEqualTo(newProduct.getName());
        assertThat(product.getDiscount()).isEqualTo(11.0);
    }

}
