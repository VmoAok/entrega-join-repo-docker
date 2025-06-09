package com.projetojoin.jikicosmeticos.jikicosmeticos.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CadastroDTOTest {

    @Test
    void testConstructorAndGetters() {
        CadastroDTO dto = new CadastroDTO(
            "email@email.com", "Nome", "12345678900", "12345-678", "Cidade",
            "Estado", "Bairro", "Endereco", "senha123", "11999999999"
        );
        assertEquals("email@email.com", dto.getEmail());
        assertEquals("Nome", dto.getNome());
        assertEquals("12345678900", dto.getCpf());
        assertEquals("12345-678", dto.getCep());
        assertEquals("Cidade", dto.getCidade());
        assertEquals("Estado", dto.getEstado());
        assertEquals("Bairro", dto.getBairro());
        assertEquals("Endereco", dto.getEndereco());
        assertEquals("senha123", dto.getPassword());
        assertEquals("11999999999", dto.getTelefone());
    }

    @Test
    void testSetters() {
        CadastroDTO dto = new CadastroDTO();
        dto.setEmail("novo@email.com");
        dto.setNome("Novo Nome");
        dto.setCpf("98765432100");
        dto.setCep("87654-321");
        dto.setCidade("Nova Cidade");
        dto.setEstado("Novo Estado");
        dto.setBairro("Novo Bairro");
        dto.setEndereco("Novo Endereco");
        dto.setPassword("novaSenha");
        dto.setTelefone("11888888888");

        assertEquals("novo@email.com", dto.getEmail());
        assertEquals("Novo Nome", dto.getNome());
        assertEquals("98765432100", dto.getCpf());
        assertEquals("87654-321", dto.getCep());
        assertEquals("Nova Cidade", dto.getCidade());
        assertEquals("Novo Estado", dto.getEstado());
        assertEquals("Novo Bairro", dto.getBairro());
        assertEquals("Novo Endereco", dto.getEndereco());
        assertEquals("novaSenha", dto.getPassword());
        assertEquals("11888888888", dto.getTelefone());
    }

    @Test
    void testToString() {
        CadastroDTO dto = new CadastroDTO(
            "email@email.com", "Nome", "12345678900", "12345-678", "Cidade",
            "Estado", "Bairro", "Endereco", "senha123", "11999999999"
        );
        String str = dto.toString();
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
    }
}