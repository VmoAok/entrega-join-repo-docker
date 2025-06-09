package com.projetojoin.jikicosmeticos.jikicosmeticos.controllers;

import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Pedido;
import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Usuario;
import com.projetojoin.jikicosmeticos.jikicosmeticos.repository.PedidoRepository;
import com.projetojoin.jikicosmeticos.jikicosmeticos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

//    @Value("${admin.email}")
//    private String adminEmail;

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

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deletePedido(@PathVariable String idPedido, Authentication authentication) {
        List<Pedido> pedidoOpt = pedidoRepository.findByIdPedido(idPedido);
        if (pedidoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Pedido pedido = pedidoOpt.get(0);
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin || (usuario != null && pedido.getIdUser() != null &&
                (pedido.getIdUser().getEmail().equals(usuario.getEmail()) ||
                 pedido.getIdUser().getCpf().equals(usuario.getCpf())))) {

            pedidoRepository.delete(pedido);

            if (!isAdmin) {
                enviarNotificacaoKafka(usuario, pedido);
            }

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(403).body("Você não tem permissão para remover este pedido.");
        }
    }

    private void enviarNotificacaoKafka(Usuario usuario, Pedido pedido) {
        String mensagem = "O usuário " + usuario.getEmail() + " (CPF: " + usuario.getCpf() + ") cancelou o pedido de ID: " + pedido.getIdUser();
        kafkaTemplate.send("notificacao-admin", mensagem);
    }

    @PutMapping("/admin/pedidos/{id}/status")
    @PreAuthorize("hasAuthority('FUNC')")
    public ResponseEntity<?> modificarStatusPedido(@PathVariable String idPedido, @RequestParam String status) {
        List<Pedido> pedidoOpt = pedidoRepository.findByIdPedido(idPedido);
        if (pedidoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Pedido pedido = pedidoOpt.get(0);
        pedido.setStatus(status);
        pedidoRepository.save(pedido);
        return ResponseEntity.ok("Status do pedido atualizado.");
    }

    @PostMapping("/admin/produtos")
    @PreAuthorize("hasAuthority('FUNC')")
    public ResponseEntity<?> incluirProdutoEstoque(@RequestBody Object produto) {
        return ResponseEntity.ok("Produto incluído no estoque.");
    }

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

    @DeleteMapping("/me/pedidos/{id}")
    @PreAuthorize("hasAuthority('CLI')")
    public ResponseEntity<?> cancelarPedido(@PathVariable String idPedido, Authentication authentication) {
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

@GetMapping("/me/pedidos")
@PreAuthorize("hasAuthority('CLI')")
public ResponseEntity<List<Pedido>> listarMeusPedidos(Authentication authentication) {
    String email = authentication.getName();
    Usuario usuarioLogado = usuarioRepository.findByEmail(email).orElse(null);
    if (usuarioLogado == null) {
        return ResponseEntity.status(401).build();
    }

    List<Pedido> pedidos = pedidoRepository.findByUsuario(usuarioLogado);
    return ResponseEntity.ok(pedidos);
}

    @GetMapping("/admin/pedidos")
    @PreAuthorize("hasAuthority('FUNC')")
    public ResponseEntity<List<Pedido>> listarTodosPedidos() {
        List<Pedido> pedidos = new java.util.ArrayList<>();
        Iterable<Pedido> allPedidos = pedidoRepository.findAll();
        allPedidos.forEach(pedidos::add);
        return ResponseEntity.ok(pedidos);
    }
}