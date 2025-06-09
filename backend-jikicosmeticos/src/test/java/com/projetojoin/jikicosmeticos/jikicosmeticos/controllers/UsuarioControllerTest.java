package com.projetojoin.jikicosmeticos.jikicosmeticos.controllers;

import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Usuario;
import com.projetojoin.jikicosmeticos.jikicosmeticos.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ShouldReturnOk_WhenCredentialsAreValid() {
        // Arrange
        Usuario loginRequest = new Usuario();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");


        Usuario existingUser = new Usuario();
        existingUser.setEmail("test@example.com");
        existingUser.setPassword("password");

        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(Optional.of(existingUser));

        // Act
        ResponseEntity<?> response = usuarioController.login(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}