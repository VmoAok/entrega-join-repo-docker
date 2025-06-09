package com.projetojoin.jikicosmeticos.jikicosmeticos.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void testSetAndGetIdPedido() {
        Pedido pedido = new Pedido();
        pedido.setIdPedido("123");
        assertEquals("123", pedido.getIdPedido());
    }

    @Test
    void testSetAndGetIdCosmetico() {
        Pedido pedido = new Pedido();
        pedido.setIdCosmetico("cosm1");
        assertEquals("cosm1", pedido.getIdCosmetico());
    }

    @Test
    void testSetAndGetProduto() {
        Pedido pedido = new Pedido();
        pedido.setProduto("Shampoo");
        assertEquals("Shampoo", pedido.getProduto());
    }

    @Test
    void testSetAndGetTipoProduto() {
        Pedido pedido = new Pedido();
        pedido.setTipoProduto("Cabelo");
        assertEquals("Cabelo", pedido.getTipoProduto());
    }

    @Test
    void testSetAndGetCategoriaProduto() {
        Pedido pedido = new Pedido();
        pedido.setCategoriaProduto("Hidratação");
        assertEquals("Hidratação", pedido.getCategoriaProduto());
    }

    @Test
    void testSetAndGetQuantidade() {
        Pedido pedido = new Pedido();
        pedido.setQuantidade(5);
        assertEquals(5, pedido.getQuantidade());
    }

    @Test
    void testSetAndGetStatusPedido() {
        Pedido pedido = new Pedido();
        pedido.setStatusPedido("Enviado");
        assertEquals("Enviado", pedido.getStatusPedido());
    }

    @Test
    void testSetAndGetDataRealizacaoPedido() {
        Pedido pedido = new Pedido();
        LocalDate data = LocalDate.of(2024, 6, 9);
        pedido.setDataRealizacaoPedido(data);
        assertEquals(data, pedido.getDataRealizacaoPedido());
    }

    @Test
    void testSetAndGetDataPrevisaoEntrega() {
        Pedido pedido = new Pedido();
        LocalDate data = LocalDate.of(2024, 6, 15);
        pedido.setDataPrevisaoEntrega(data);
        assertEquals(data, pedido.getDataPrevisaoEntrega());
    }

    @Test
    void testToStringContainsFields() {
        Pedido pedido = new Pedido();
        pedido.setIdPedido("1");
        pedido.setIdCosmetico("cosm2");
        pedido.setProduto("Condicionador");
        pedido.setTipoProduto("Cabelo");
        pedido.setCategoriaProduto("Nutrição");
        pedido.setQuantidade(2);
        pedido.setStatusPedido("Processando");
        pedido.setDataRealizacaoPedido(LocalDate.of(2024, 6, 9));
        pedido.setDataPrevisaoEntrega(LocalDate.of(2024, 6, 15));
        String str = pedido.toString();
        assertTrue(str.contains("idPedido='1'"));
        assertTrue(str.contains("idCosmetico='cosm2'"));
        assertTrue(str.contains("produto='Condicionador'"));
        assertTrue(str.contains("tipoProduto='Cabelo'"));
        assertTrue(str.contains("categoriaProduto='Nutrição'"));
        assertTrue(str.contains("quantidade=2"));
        assertTrue(str.contains("statusPedido='Processando'"));
        assertTrue(str.contains("dataRealizacaoPedido=2024-06-09"));
        assertTrue(str.contains("dataPrevisaoEntrega=2024-06-15"));
    }
}
