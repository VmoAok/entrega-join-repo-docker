package com.projetojoin.jikicosmeticos.jikicosmeticos.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginRequestDTOTest {

    @Test
    void testSetAndGetEmail() {
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setEmail("teste@email.com");
        assertEquals("teste@email.com", dto.getEmail());
    }

    @Test
    void testSetAndGetPassword() {
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setPassword("senha123");
        assertEquals("senha123", dto.getPassword());
    }

    @Test
    void testSetAndGetEmailAndPassword() {
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setEmail("usuario@email.com");
        dto.setPassword("senha456");
        assertEquals("usuario@email.com", dto.getEmail());
        assertEquals("senha456", dto.getPassword());
    }
}