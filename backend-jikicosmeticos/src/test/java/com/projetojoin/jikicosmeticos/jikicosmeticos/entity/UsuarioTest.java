package com.projetojoin.jikicosmeticos.jikicosmeticos.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;

class UsuarioTest {

    @Test
    void testConstructorAndGetters() {
        Usuario usuario = new Usuario(
            "email@email.com", "Nome", "12345678900", "12345-678", "Cidade",
            "Estado", "Bairro", "Endereco", "senha123", "11999999999"
        );
        assertEquals("email@email.com", usuario.getEmail());
        assertEquals("Nome", usuario.getNome());
        assertEquals("12345678900", usuario.getCpf());
        assertEquals("12345-678", usuario.getCep());
        assertEquals("Cidade", usuario.getCidade());
        assertEquals("Estado", usuario.getEstado());
        assertEquals("Bairro", usuario.getBairro());
        assertEquals("Endereco", usuario.getEndereco());
        assertEquals("senha123", usuario.getPassword());
        assertEquals("11999999999", usuario.getTelefone());
    }

    @Test
    void testSetters() {
        Usuario usuario = new Usuario();
        usuario.setIdUser(10L);
        usuario.setEmail("novo@email.com");
        usuario.setNome("Novo Nome");
        usuario.setCpf("98765432100");
        usuario.setCep("87654-321");
        usuario.setCidade("Nova Cidade");
        usuario.setEstado("Novo Estado");
        usuario.setBairro("Novo Bairro");
        usuario.setEndereco("Novo Endereco");
        usuario.setPassword("novaSenha");
        usuario.setTelefone("11888888888");
        usuario.setHash("hashValue");

        assertEquals(10L, usuario.getIdUser());
        assertEquals("novo@email.com", usuario.getEmail());
        assertEquals("Novo Nome", usuario.getNome());
        assertEquals("98765432100", usuario.getCpf());
        assertEquals("87654-321", usuario.getCep());
        assertEquals("Nova Cidade", usuario.getCidade());
        assertEquals("Novo Estado", usuario.getEstado());
        assertEquals("Novo Bairro", usuario.getBairro());
        assertEquals("Novo Endereco", usuario.getEndereco());
        assertEquals("novaSenha", usuario.getPassword());
        assertEquals("11888888888", usuario.getTelefone());
        assertEquals("hashValue", usuario.getHash());
    }

    @Test
    void testToStringContainsFields() {
        Usuario usuario = new Usuario(
            "email@email.com", "Nome", "12345678900", "12345-678", "Cidade",
            "Estado", "Bairro", "Endereco", "senha123", "11999999999"
        );
        usuario.setIdUser(1L);
        usuario.setHash("hashTest");
        String str = usuario.toString();
        assertTrue(str.contains("idUser=1"));
        assertTrue(str.contains("email='email@email.com'"));
        assertTrue(str.contains("nome='Nome'"));
        assertTrue(str.contains("cpf='12345678900'"));
        assertTrue(str.contains("cep='12345-678'"));
        assertTrue(str.contains("cidade='Cidade'"));
        assertTrue(str.contains("estado='Estado'"));
        assertTrue(str.contains("bairro='Bairro'"));
        assertTrue(str.contains("endereco='Endereco'"));
        assertTrue(str.contains("password='senha123'"));
        assertTrue(str.contains("telefone='11999999999'"));
        assertTrue(str.contains("hash='hashTest'"));
    }

    @Test
    void testUserDetailsMethods() {
        Usuario usuario = new Usuario();
        Collection<?> authorities = usuario.getAuthorities();
        assertNotNull(authorities);
        assertTrue(authorities.isEmpty());
        assertEquals("", usuario.getUsername());
    }
}