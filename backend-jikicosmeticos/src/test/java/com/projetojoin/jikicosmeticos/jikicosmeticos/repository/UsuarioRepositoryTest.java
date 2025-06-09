package com.projetojoin.jikicosmeticos.jikicosmeticos.repository;

import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve retornar true se existir usuário com email")
    void existsByEmail_ReturnsTrue_WhenEmailExists() {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");
        usuario.setCpf("12345678900");
        usuarioRepository.save(usuario);

        boolean exists = usuarioRepository.existsByEmail("teste@email.com");
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false se não existir usuário com email")
    void existsByEmail_ReturnsFalse_WhenEmailNotExists() {
        boolean exists = usuarioRepository.existsByEmail("naoexiste@email.com");
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Deve retornar true se existir usuário com cpf")
    void existsByCpf_ReturnsTrue_WhenCpfExists() {
        Usuario usuario = new Usuario();
        usuario.setEmail("outro@email.com");
        usuario.setCpf("11122233344");
        usuarioRepository.save(usuario);

        boolean exists = usuarioRepository.existsByCpf("11122233344");
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false se não existir usuário com cpf")
    void existsByCpf_ReturnsFalse_WhenCpfNotExists() {
        boolean exists = usuarioRepository.existsByCpf("00000000000");
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Deve encontrar usuário por email")
    void findByEmail_ReturnsUsuario_WhenEmailExists() {
        Usuario usuario = new Usuario();
        usuario.setEmail("find@email.com");
        usuario.setCpf("55566677788");
        usuarioRepository.save(usuario);

        Optional<Usuario> found = usuarioRepository.findByEmail("find@email.com");
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("find@email.com");
    }

    @Test
    @DisplayName("Deve retornar vazio ao buscar usuário por email inexistente")
    void findByEmail_ReturnsEmpty_WhenEmailNotExists() {
        Optional<Usuario> found = usuarioRepository.findByEmail("naoencontrado@email.com");
        assertThat(found).isNotPresent();
    }
}