package com.projetojoin.jikicosmeticos.jikicosmeticos.repository;

import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Pedido;
import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Usuario;
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
    void testFindByUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail("user@email.com");
        usuario.setNome("Usuário Teste");
        usuario.setCpf("12345678900");
        usuario.setCep("12345-678");
        usuario.setCidade("Cidade");
        usuario.setEstado("Estado");
        usuario.setBairro("Bairro");
        usuario.setEndereco("Endereço");
        usuario.setPassword("senha");
        usuario.setTelefone("11999999999");
        usuarioRepository.save(usuario);

        Pedido pedido = new Pedido();
        pedido.setIdPedido("2");
        pedido.setProduto("Condicionador");
        pedido.setStatusPedido("Processando");


        pedidoRepository.save(pedido);

        List<Pedido> pedidos = pedidoRepository.findByUsuario(usuario);
        assertFalse(pedidos.isEmpty());
        assertEquals("2", pedidos.get(0).getIdPedido());
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