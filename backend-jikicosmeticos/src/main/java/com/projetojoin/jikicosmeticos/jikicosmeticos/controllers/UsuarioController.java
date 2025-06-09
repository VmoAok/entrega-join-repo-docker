package com.projetojoin.jikicosmeticos.jikicosmeticos.controllers;

import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Usuario;
import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Pedido;
import com.projetojoin.jikicosmeticos.jikicosmeticos.repository.UsuarioRepository;
import com.projetojoin.jikicosmeticos.jikicosmeticos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    // Endpoint para login (valida email, senha e data de modificação)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(loginRequest.getEmail());
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Usuário não encontrado.");
        }
        Usuario usuario = usuarioOpt.get();
        // Verifica senha e data de modificação (exemplo: dataModificacao é LocalDateTime)
        if (!usuario.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(401).body("Senha incorreta.");
        }
        if (loginRequest.getDataModificacao() != null && !loginRequest.getDataModificacao().equals(usuario.getDataModificacao())) {
            return ResponseEntity.status(401).body("Data de modificação inválida.");
        }
        // Aqui você geraria e retornaria o token JWT
        return ResponseEntity.ok("Login realizado com sucesso.");
    }
}