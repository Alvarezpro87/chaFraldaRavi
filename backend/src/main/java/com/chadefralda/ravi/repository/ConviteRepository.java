package com.chadefralda.ravi.repository;


import com.chadefralda.ravi.model.Convite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConviteRepository extends JpaRepository<Convite, Long> {
    // depois podemos criar métodos customizados se precisar
}
