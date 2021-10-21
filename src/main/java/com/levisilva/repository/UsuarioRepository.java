package com.levisilva.repository;

import com.levisilva.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Login, Integer> {
    //query method que vai buscar o usuario pelo seu login
    Optional<Login> findByLogin(String login);
}
