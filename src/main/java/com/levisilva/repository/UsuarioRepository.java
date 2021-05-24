package com.levisilva.repository;

import com.levisilva.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    //query method que vai buscar o usuario pelo seu login
    Optional<Usuario> findByLogin(String login);
}
