package com.projetojoin.jikicosmeticos.jikicosmeticos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Usuario;
import com.projetojoin.jikicosmeticos.jikicosmeticos.repository.UsuarioRepository;
import com.projetojoin.jikicosmeticos.jikicosmeticos.dto.LoginRequestDTO;
import com.projetojoin.jikicosmeticos.jikicosmeticos.dto.LoginResponseDTO;
import com.projetojoin.jikicosmeticos.jikicosmeticos.config.TokenConfig;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenConfig tokenConfig;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
    if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
        return ResponseEntity.status(409).body("Usuário já existe!");
    }
    usuarioRepository.save(usuario);
    return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
                .orElse(null);
        if (usuario == null || !usuario.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        String token = tokenConfig.generateToken(usuario);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}