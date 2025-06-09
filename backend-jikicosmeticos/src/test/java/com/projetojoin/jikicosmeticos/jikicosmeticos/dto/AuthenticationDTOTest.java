package com.projetojoin.jikicosmeticos.jikicosmeticos.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// ...existing code...

class AuthenticationDTOTest {

    @Test
    void testConstructorAndGetters() {
        AuthenticationDTO dto = new AuthenticationDTO("user@email.com", "senha123");
        assertEquals("user@email.com", dto.getemail());
        assertEquals("senha123", dto.getPassword());
    }

    @Test
    void testSetEmail() {
        AuthenticationDTO dto = new AuthenticationDTO("a@a.com", "senha");
        dto.setemail("novo@email.com");
        assertEquals("novo@email.com", dto.getemail());
    }

    @Test
    void testSetPassword() {
        AuthenticationDTO dto = new AuthenticationDTO("a@a.com", "senha");
        dto.setPassword("novaSenha");
        assertEquals("novaSenha", dto.getPassword());
    }
}