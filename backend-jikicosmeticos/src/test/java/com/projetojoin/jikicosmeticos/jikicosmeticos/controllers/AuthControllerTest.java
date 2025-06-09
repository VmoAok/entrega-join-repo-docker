package com.projetojoin.jikicosmeticos.jikicosmeticos.controllers;

import com.projetojoin.jikicosmeticos.jikicosmeticos.config.TokenConfig;
import com.projetojoin.jikicosmeticos.jikicosmeticos.dto.LoginRequestDTO;
import com.projetojoin.jikicosmeticos.jikicosmeticos.dto.LoginResponseDTO;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class AuthControllerTest {

        @InjectMocks
        private AuthController authController;

        @Mock
        private UsuarioRepository usuarioRepository;

        @Mock
        private TokenConfig tokenConfig;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testRegister_NewUser_ReturnsOk() {
            Usuario newUser = new Usuario();
            newUser.setEmail("test@example.com");

            when(usuarioRepository.findByEmail(newUser.getEmail())).thenReturn(Optional.empty());
            when(usuarioRepository.save(newUser)).thenReturn(newUser);

            ResponseEntity<?> response = authController.register(newUser);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("Usuário registrado com sucesso!", response.getBody());
            verify(usuarioRepository, times(1)).save(newUser);
        }

        @Test
        public void testRegister_ExistingUser_ReturnsConflict() {
            Usuario existingUser = new Usuario();
            existingUser.setEmail("test@example.com");

            when(usuarioRepository.findByEmail(existingUser.getEmail())).thenReturn(Optional.of(existingUser));

            ResponseEntity<?> response = authController.register(existingUser);

            assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
            assertEquals("Usuário já existe!", response.getBody());
            verify(usuarioRepository, never()).save(any(Usuario.class));
        }

        @Test
        public void testLogin_SuccessfulLogin_ReturnsOkWithToken() {
            LoginRequestDTO loginRequest = new LoginRequestDTO();
            loginRequest.setEmail("test@example.com");
            loginRequest.setPassword("password");

            Usuario usuario = new Usuario();
            usuario.setEmail("test@example.com");
            usuario.setPassword("password");

            when(usuarioRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(usuario));
            when(tokenConfig.generateToken(usuario)).thenReturn("mockedToken");

            ResponseEntity<LoginResponseDTO> response = authController.login(loginRequest);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("mockedToken", response.getBody().getToken());
        }

        @Test
        public void testLogin_InvalidCredentials_ReturnsUnauthorized() {
            LoginRequestDTO loginRequest = new LoginRequestDTO();
            loginRequest.setEmail("test@example.com");
            loginRequest.setPassword("wrongPassword");

            Usuario usuario = new Usuario();
            usuario.setEmail("test@example.com");
            usuario.setPassword("password");

            when(usuarioRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(usuario));

            ResponseEntity<LoginResponseDTO> response = authController.login(loginRequest);

            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

        @Test
        public void testLogin_UserNotFound_ReturnsUnauthorized() {
            LoginRequestDTO loginRequest = new LoginRequestDTO();
            loginRequest.setEmail("test@example.com");
            loginRequest.setPassword("password");

            when(usuarioRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.empty());

            ResponseEntity<LoginResponseDTO> response = authController.login(loginRequest);

            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }
}
