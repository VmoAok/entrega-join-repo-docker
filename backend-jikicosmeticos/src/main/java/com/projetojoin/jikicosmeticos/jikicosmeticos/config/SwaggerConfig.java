package com.projetojoin.jikicosmeticos.jikicosmeticos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Jiki API")
                        .description("API para gerenciamento de um ecommerce de cosméticos")
                        .version("v1")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org"))
                );
    }
}