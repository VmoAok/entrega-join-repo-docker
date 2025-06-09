package com.projetojoin.jikicosmeticos.jikicosmeticos.repository;

import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepositoryTest extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    Optional<Usuario> findByEmail(String email);
}