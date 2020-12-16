package com.gestaoresponsabilidadetecnica.pessoa.repositories;

import com.gestaoresponsabilidadetecnica.pessoa.models.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaFisica, Integer> {
}
