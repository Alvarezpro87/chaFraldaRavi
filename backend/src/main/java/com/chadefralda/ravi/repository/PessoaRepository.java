package com.chadefralda.ravi.repository;


import com.chadefralda.ravi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> { }
