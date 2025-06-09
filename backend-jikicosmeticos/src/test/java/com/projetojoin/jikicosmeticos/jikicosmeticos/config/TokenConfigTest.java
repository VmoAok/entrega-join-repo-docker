package com.projetojoin.jikicosmeticos.jikicosmeticos.config;

import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

public class TokenConfigTest {

    private TokenConfig tokenConfig;

    private String secret = "1234567890abcdefjoin";

    @BeforeEach
    void setUp() {
        tokenConfig = new TokenConfig();
        ReflectionTestUtils.setField(tokenConfig, "secret", secret);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateToken_ShouldGenerateValidToken() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setIdUser(123L);

        // Act
        String token = tokenConfig.generateToken(usuario);

        // Assert
        assertNotNull(token, "Token should not be null");
        assertFalse(token.isEmpty(), "Token should not be empty");
    }

    @Test
    void validateToken_ShouldReturnSubjectForValidToken() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setIdUser(123L);
        String token = tokenConfig.generateToken(usuario);

        // Act
        String subject = tokenConfig.validateToken(token);

        // Assert
        assertEquals("123", subject, "Subject should match user ID");
    }

    @Test
    void validateToken_ShouldReturnEmptyStringForInvalidToken() {
        // Arrange
        String invalidToken = "invalid.token";

        // Act
        String subject = tokenConfig.validateToken(invalidToken);

        // Assert
        assertEquals("", subject, "Subject should be empty for invalid token");
    }
}