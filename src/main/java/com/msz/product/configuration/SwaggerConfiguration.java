package com.msz.product.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@EnableCaching
public class SwaggerConfiguration {

    @Bean
    Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.msz.product.controller"))
                .build();
    }

    private ApiInfo geApiInfo() {
        return new ApiInfo("Product Microservice",
                "Product Microservice for online shopping portal",
                "1.0",
                "https://github.com/marszm",
                new Contact("marszm", "https://www.linkedin.com/in/mariusz-szmer-31463b12a/", "abc@gmail.com"),
                "Terms of Use License", "https://github.com/marszm/product-microservice/blob/master/LICENSE",
                Collections.emptyList()
        );
    }

}
