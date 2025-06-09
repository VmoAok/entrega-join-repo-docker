package com.projetojoin.jikicosmeticos.jikicosmeticos.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {

    @Test
    void testSetAndGetIdPedido() {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(1L);
        assertEquals(1L, pedido.getIdPedido());
    }

    @Test
    void testSetAndGetUsuario() {
        Pedido pedido = new Pedido();
        Usuario usuario = new Usuario();
        pedido.setUsuario(usuario);
        assertEquals(usuario, pedido.getUsuario());
    }

    @Test
    void testSetAndGetIdCosmetico() {
        Pedido pedido = new Pedido();
        pedido.setIdCosmetico(10L);
        assertEquals(10L, pedido.getIdCosmetico());
    }

    @Test
    void testSetAndGetProduto() {
        Pedido pedido = new Pedido();
        pedido.setProduto("Creme Hidratante");
        assertEquals("Creme Hidratante", pedido.getProduto());
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
        pedido.setCategoriaProduto("Nutrição");
        assertEquals("Nutrição", pedido.getCategoriaProduto());
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
        pedido.setStatusPedido("Processando");
        assertEquals("Processando", pedido.getStatusPedido());
    }

    @Test
    void testSetAndGetDataRealizacaoPedido() {
        Pedido pedido = new Pedido();
        LocalDate dataRealizacao = LocalDate.of(2025, 6, 9);
        pedido.setDataRealizacaoPedido(dataRealizacao);
        assertEquals(dataRealizacao, pedido.getDataRealizacaoPedido());
    }

    @Test
    void testSetAndGetDataPrevisaoEntrega() {
        Pedido pedido = new Pedido();
        LocalDate dataEntrega = LocalDate.of(2025, 6, 15);
        pedido.setDataPrevisaoEntrega(dataEntrega);
        assertEquals(dataEntrega, pedido.getDataPrevisaoEntrega());
    }

    @Test
    void testToStringContainsFields() {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(1L);
        pedido.setIdCosmetico(2L);
        pedido.setProduto("Condicionador");
        pedido.setTipoProduto("Cabelo");
        pedido.setCategoriaProduto("Nutrição");
        pedido.setQuantidade(2);
        pedido.setStatusPedido("Processando");
        pedido.setDataRealizacaoPedido(LocalDate.of(2025, 6, 9));
        pedido.setDataPrevisaoEntrega(LocalDate.of(2025, 6, 15));

        String str = pedido.toString();

        assertTrue(str.contains("idPedido=1"));
        assertTrue(str.contains("idCosmetico=2"));
        assertTrue(str.contains("produto=Condicionador"));
        assertTrue(str.contains("tipoProduto=Cabelo"));
        assertTrue(str.contains("categoriaProduto=Nutrição"));
        assertTrue(str.contains("quantidade=2"));
        assertTrue(str.contains("statusPedido=Processando"));
        assertTrue(str.contains("dataRealizacaoPedido=2025-06-09"));
        assertTrue(str.contains("dataPrevisaoEntrega=2025-06-15"));
    }
}
