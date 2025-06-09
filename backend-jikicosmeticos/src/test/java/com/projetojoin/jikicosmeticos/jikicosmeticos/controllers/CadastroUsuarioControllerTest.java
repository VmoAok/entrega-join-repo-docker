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
    void cadastrarUsuario_DeveRetornarOkEEnviarEmailConfirmacao() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setCpf("12345678900");
        usuario.setNome("Teste");

        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(false);
        when(usuarioRepository.existsByCpf(usuario.getCpf())).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        ResponseEntity<?> response = cadastroUsuarioController.cadastrarUsuario(usuario);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário cadastrado! Um e-mail de confirmação foi enviado.", response.getBody());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void cadastrarUsuario_DeveRetornarConflict_QuandoEmailOuCpfExistir() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setCpf("12345678900");

        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(true);

        ResponseEntity<?> response = cadastroUsuarioController.cadastrarUsuario(usuario);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("E-mail ou CPF já cadastrado!", response.getBody());
        verify(usuarioRepository, never()).save(any(Usuario.class));
        verify(mailSender, never()).send(any(SimpleMailMessage.class));
    }
 
}