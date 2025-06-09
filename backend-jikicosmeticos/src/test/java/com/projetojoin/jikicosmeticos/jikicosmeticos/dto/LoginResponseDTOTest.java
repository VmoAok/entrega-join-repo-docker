package com.projetojoin.jikicosmeticos.jikicosmeticos.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginResponseDTOTest {

    @Test
    void testConstructorAndGetToken() {
        LoginResponseDTO dto = new LoginResponseDTO("abc123token");
        assertEquals("abc123token", dto.getToken());
    }

    @Test
    void testSetToken() {
        LoginResponseDTO dto = new LoginResponseDTO("initialToken");
        dto.setToken("newTokenValue");
        assertEquals("newTokenValue", dto.getToken());
    }
}