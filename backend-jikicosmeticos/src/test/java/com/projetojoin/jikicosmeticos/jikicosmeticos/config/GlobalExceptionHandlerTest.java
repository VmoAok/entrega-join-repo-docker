package com.projetojoin.jikicosmeticos.jikicosmeticos.config;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    @Test
    void testHandlerException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        Exception ex = new Exception("Erro de teste");

        ResponseEntity<String> response = handler.handlerException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Ocorreu um erro interno. Por favor, tente novamente.", response.getBody());
    }
}