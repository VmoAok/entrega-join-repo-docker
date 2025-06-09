package com.projetojoin.jikicosmeticos.jikicosmeticos.controllers;

import com.projetojoin.jikicosmeticos.jikicosmeticos.dto.CadastroDTO;
import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Usuario;
import com.projetojoin.jikicosmeticos.jikicosmeticos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;

@RestController
@RequestMapping("/cadastro")
public class CadastroUsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${admin.email}")
    private String adminEmail;

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario2) {
        if (usuarioRepository.existsByEmail(usuario2.getEmail()) || usuarioRepository.existsByCpf(usuario2.getCpf())) {
            return ResponseEntity.status(409).body("E-mail ou CPF já cadastrado!");
        }
        Usuario usuario = new Usuario();
        usuario.setEmail(usuario2.getEmail());
        usuario.setNome(usuario2.getNome());
        usuario.setCpf(usuario2.getCpf());
        usuario.setCep(usuario2.getCep());
        usuario.setCidade(usuario2.getCidade());
        usuario.setEstado(usuario2.getEstado());
        usuario.setBairro(usuario2.getBairro());
        usuario.setEndereco(usuario2.getEndereco());
        usuario.setPassword(usuario2.getPassword());
        usuario.setTelefone(usuario2.getTelefone());
        usuario.setHash(gerarHashCpf(usuario2.getCpf()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuário cadastrado!");
    }

    @PutMapping("/me")
    public ResponseEntity<?> atualizarMe(@RequestBody CadastroDTO dto, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) return ResponseEntity.notFound().build();
        Usuario usuario = usuarioOpt.get();

        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Dados atualizados com sucesso!");
    }

    @DeleteMapping("/me")
    public ResponseEntity<?> solicitarExclusao(Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) return ResponseEntity.notFound().build();
        Usuario usuario = usuarioOpt.get();
        enviarSolicitacaoExclusaoAdmin(usuario);
        return ResponseEntity.ok("Solicitação de exclusão enviada ao administrador.");
    }

    private String gerarHashCpf(String cpf) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(cpf.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar hash do CPF", e);
        }
    }

    private void enviarSolicitacaoExclusaoAdmin(Usuario usuario) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(adminEmail);
        message.setSubject("Solicitação de exclusão de usuário (LGPD)");
        message.setText("Usuário solicitou exclusão de seus dados:\nEmail: " + usuario.getEmail() + "\nCPF: " + usuario.getCpf());
        mailSender.send(message);
    }

   
}