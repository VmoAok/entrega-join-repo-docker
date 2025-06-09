package com.projetojoin.jikicosmeticos.jikicosmeticos.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SwaggerConfigTest {

    private final SwaggerConfig swaggerConfig = new SwaggerConfig();

    @Test
    void customOpenAPI_ShouldReturnConfiguredOpenAPI() {
        // Act
        OpenAPI openAPI = swaggerConfig.customOpenAPI();

        // Assert
        assertNotNull(openAPI, "OpenAPI should not be null");
        assertNotNull(openAPI.getInfo(), "OpenAPI Info should not be null");
        assertEquals("Jiki API", openAPI.getInfo().getTitle(), "API Title should match");
        assertEquals("API para gerenciamento de um ecommerce de cosméticos", openAPI.getInfo().getDescription(), "API Description should match");
        assertEquals("v1", openAPI.getInfo().getVersion(), "API Version should match");
        assertNotNull(openAPI.getInfo().getLicense(), "API License should not be null");
        assertEquals("Apache 2.0", openAPI.getInfo().getLicense().getName(), "License Name should match");
        assertEquals("https://springdoc.org", openAPI.getInfo().getLicense().getUrl(), "License URL should match");
    }
}