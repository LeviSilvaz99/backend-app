package com.levisilva.repository;

import com.levisilva.domain.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Cadastro, Long> {

}
