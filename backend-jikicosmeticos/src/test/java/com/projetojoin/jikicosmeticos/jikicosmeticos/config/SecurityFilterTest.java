package com.projetojoin.jikicosmeticos.jikicosmeticos.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SecurityFilterTest {

    private final SecurityConfig securityConfig = new SecurityConfig();

    @Test
    void corsConfigurationSource_ShouldReturnNonNull() {
        CorsConfigurationSource source = securityConfig.corsConfigurationSource();
        assertNotNull(source, "CorsConfigurationSource should not be null");
    }
}