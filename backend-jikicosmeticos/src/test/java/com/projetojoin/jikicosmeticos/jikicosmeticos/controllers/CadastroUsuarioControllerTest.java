package com.projetojoin.jikicosmeticos.jikicosmeticos.controllers;

import com.projetojoin.jikicosmeticos.jikicosmeticos.dto.CadastroDTO;
import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Usuario;
import com.projetojoin.jikicosmeticos.jikicosmeticos.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CadastroUsuarioControllerTest {

    @InjectMocks
    private CadastroUsuarioController cadastroUsuarioController;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastrarUsuario_ShouldReturnOk_WhenUserIsRegistered() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setCpf("12345678900");

        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(false);
        when(usuarioRepository.existsByCpf(usuario.getCpf())).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        // Act
        ResponseEntity<?> response = cadastroUsuarioController.cadastrarUsuario(usuario);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário cadastrado!", response.getBody());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void cadastrarUsuario_ShouldReturnConflict_WhenEmailOrCpfExists() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setCpf("12345678900");

        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(true);

        // Act
        ResponseEntity<?> response = cadastroUsuarioController.cadastrarUsuario(usuario);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("E-mail ou CPF já cadastrado!", response.getBody());
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void atualizarMe_ShouldReturnOk_WhenUserIsUpdated() {
        // Arrange
        CadastroDTO dto = new CadastroDTO();
        dto.setEmail("newemail@example.com");
        dto.setTelefone("999999999");

        Usuario existingUser = new Usuario();
        existingUser.setEmail("oldemail@example.com");
        existingUser.setTelefone("888888888");

        when(authentication.getName()).thenReturn("oldemail@example.com");
        when(usuarioRepository.findByEmail("oldemail@example.com")).thenReturn(Optional.of(existingUser));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(existingUser);

        // Act
        ResponseEntity<?> response = cadastroUsuarioController.atualizarMe(dto, authentication);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Dados atualizados com sucesso!", response.getBody());
        assertEquals("newemail@example.com", existingUser.getEmail());
        assertEquals("999999999", existingUser.getTelefone());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void atualizarMe_ShouldReturnNotFound_WhenUserDoesNotExist() {
        // Arrange
        CadastroDTO dto = new CadastroDTO();
        dto.setEmail("newemail@example.com");
        dto.setTelefone("999999999");

        when(authentication.getName()).thenReturn("nonexistent@example.com");
        when(usuarioRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = cadastroUsuarioController.atualizarMe(dto, authentication);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void solicitarExclusao_ShouldReturnOk_WhenUserExistsAndEmailIsSent() {
        // Arrange
        Usuario existingUser = new Usuario();
        existingUser.setEmail("test@example.com");
        existingUser.setCpf("12345678900");

        when(authentication.getName()).thenReturn("test@example.com");
        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(Optional.of(existingUser));

        // Act
        ResponseEntity<?> response = cadastroUsuarioController.solicitarExclusao(authentication);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Solicitação de exclusão enviada ao administrador.", response.getBody());
        verify(mailSender, times(0)).send(any(SimpleMailMessage.class));
    }

    @Test
    void solicitarExclusao_ShouldReturnNotFound_WhenUserDoesNotExist() {
        // Arrange
        when(authentication.getName()).thenReturn("nonexistent@example.com");
        when(usuarioRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = cadastroUsuarioController.solicitarExclusao(authentication);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(mailSender, never()).send(any(SimpleMailMessage.class));
    }
}