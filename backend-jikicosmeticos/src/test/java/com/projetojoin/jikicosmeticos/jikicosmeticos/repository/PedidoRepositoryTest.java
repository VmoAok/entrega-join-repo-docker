package com.projetojoin.jikicosmeticos.jikicosmeticos.repository;

import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Pedido;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void testSaveAndFindById() {
        Pedido pedido = new Pedido();
        pedido.setIdPedido("1");
        pedido.setProduto("Shampoo");
        pedido.setStatusPedido("Novo");
        pedido.setDataRealizacaoPedido(LocalDate.now());
        pedido.setDataPrevisaoEntrega(LocalDate.now().plusDays(5));

        Pedido saved = pedidoRepository.save(pedido);
        assertTrue(pedidoRepository.findById(saved.getIdPedido()).isPresent());
    }


    @Test
    void testFindByIdPedido() {
        Pedido pedido = new Pedido();
        pedido.setIdPedido("3");
        pedido.setProduto("Máscara");
        pedido.setStatusPedido("Entregue");
        pedidoRepository.save(pedido);

        List<Pedido> pedidos = pedidoRepository.findByIdPedido("3");
        assertFalse(pedidos.isEmpty());
        assertEquals("3", pedidos.get(0).getIdPedido());
    }
}