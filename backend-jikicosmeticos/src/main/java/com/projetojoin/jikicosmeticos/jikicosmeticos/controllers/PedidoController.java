package com.projetojoin.jikicosmeticos.jikicosmeticos.controllers;

import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Pedido;
import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Usuario;
import com.projetojoin.jikicosmeticos.jikicosmeticos.repository.PedidoRepository;
import com.projetojoin.jikicosmeticos.jikicosmeticos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${admin.email}")
    private String adminEmail;

    // Registrar novo pedido (vinculado ao usuário autenticado)
    @PostMapping
    @PreAuthorize("hasRole('CLI') or hasRole('FUNC')")
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido, Authentication authentication) {
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
        if (usuario == null) {
            return ResponseEntity.status(401).build();
        }
        pedido.setIdUser(usuario);
        Pedido savedPedido = pedidoRepository.save(pedido);
        return ResponseEntity.ok(savedPedido);
    }

    // Remover pedido (ADMIN pode remover qualquer, USER só o próprio)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deletePedido(@PathVariable Long idPedido, Authentication authentication) {
        List<Pedido> pedidoOpt = pedidoRepository.findByIdPedido(idPedido);
        if (pedidoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Pedido pedido = pedidoOpt.get(0);
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        // Verifica se é admin ou se o pedido pertence ao usuário autenticado
        if (isAdmin || (usuario != null && pedido.getIdUser() != null &&
                (pedido.getIdUser().getEmail().equals(usuario.getEmail()) ||
                 pedido.getIdUser().getCpf().equals(usuario.getCpf())))) {

            pedidoRepository.delete(pedido);

            // Notifica o admin via Kafka se não for admin removendo
            if (!isAdmin) {
                enviarNotificacaoKafka(usuario, pedido);
            }

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(403).body("Você não tem permissão para remover este pedido.");
        }
    }

    // Envia notificação para o email do administrador via Kafka
    private void enviarNotificacaoKafka(Usuario usuario, Pedido pedido) {
        String mensagem = "O usuário " + usuario.getEmail() + " (CPF: " + usuario.getCpf() + ") cancelou o pedido de ID: " + pedido.getId();
        kafkaTemplate.send("notificacao-admin", mensagem);
    }

    // ADMIN: modificar status de pedidos - Atualizar tb_pedido para remvoer item e modificar status
    @PutMapping("/admin/pedidos/{id}/status")
    @PreAuthorize("authentication.authorities.?[authority.startsWith('UFUNC')].size() > 0")
    public ResponseEntity<?> modificarStatusPedido(@PathVariable Long idPedido, @RequestParam String status) {
        List<Pedido> pedidoOpt = pedidoRepository.findByIdPedido(idPedido);
        if (pedidoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Pedido pedido = pedidoOpt.get(0);
        pedido.setStatus(status);
        pedidoRepository.save(pedido);
        return ResponseEntity.ok("Status do pedido atualizado.");
    }

    // ADMIN: incluir novo produto no estoque (TB_ESTOQUE)  
    @PostMapping("/admin/produtos")
    @PreAuthorize("hasAuthority('FUNC')")
    public ResponseEntity<?> incluirProdutoEstoque(@RequestBody Object produto) {
        return ResponseEntity.ok("Produto incluído no estoque.");
    }

    // USER: criar novo pedido
    @PostMapping("/me/pedidos")
    @PreAuthorize("hasAuthority('CLI')")
    public ResponseEntity<?> criarPedido(@RequestBody Pedido pedido, Authentication authentication) {
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
        if (usuario == null) {
            return ResponseEntity.status(401).body("Usuário não encontrado.");
        }
        pedido.setIdUser(usuario);
        pedidoRepository.save(pedido);
        return ResponseEntity.ok("Pedido criado com sucesso.");
    }

    // USER: cancelar pedido próprio
    @DeleteMapping("/me/pedidos/{id}")
    @PreAuthorize("hasAuthority('CLI')")
    public ResponseEntity<?> cancelarPedido(@PathVariable Long idPedido, Authentication authentication) {
        String email = authentication.getName();
        List<Pedido> pedidoOpt = pedidoRepository.findByIdPedido(idPedido);
        if (pedidoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Pedido pedido = pedidoOpt.get(0);
        if (!pedido.getIdUser().getEmail().equals(email)) {
            return ResponseEntity.status(403).body("Você só pode cancelar seus próprios pedidos.");
        }
        pedidoRepository.delete(pedido);
        return ResponseEntity.ok("Pedido cancelado com sucesso.");
    }

    @SuppressWarnings("unlikely-arg-type")
    @GetMapping("/me/pedidos")
    @PreAuthorize("authentication.authorities.?[authority.startsWith('UCLI')].size() > 0")
    public ResponseEntity<List<Pedido>> listarMeusPedidos(Authentication authentication) {
    String email = authentication.getName();
    Usuario usuarioLogado = usuarioRepository.findByEmail(email).orElse(null);
    if (usuarioLogado == null) {
        return ResponseEntity.status(401).build();
    }
    // Supondo que o código UCLI está em usuarioLogado.getCodigoUcli()
    // E que o campo de autoridade é igual ao código UCLI do usuário
    boolean autorizado = authentication.getAuthorities().stream()
        .anyMatch(a -> a.getAuthority().equals(usuarioLogado.getIdUser()));
    if (!autorizado) {
        return ResponseEntity.status(403).body(null);
    }
    List<Pedido> pedidos = pedidoRepository.findByUsuario(usuarioLogado);
    return ResponseEntity.ok(pedidos);
}

    // ADMIN: Listar todos os pedidos
    @GetMapping("/admin/pedidos")
    @PreAuthorize("authentication.authorities.?[authority.startsWith('UFUNC')].size() > 0")
    public ResponseEntity<List<Pedido>> listarTodosPedidos() {
        List<Pedido> pedidos = new java.util.ArrayList<>();
        Iterable<Pedido> allPedidos = pedidoRepository.findAll();
        allPedidos.forEach(pedidos::add);
        return ResponseEntity.ok(pedidos);
    }
}