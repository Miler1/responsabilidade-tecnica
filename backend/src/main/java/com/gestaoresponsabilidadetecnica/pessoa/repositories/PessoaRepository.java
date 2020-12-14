package com.gestaoresponsabilidadetecnica.pessoa.repositories;

import com.gestaoresponsabilidadetecnica.pessoa.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
