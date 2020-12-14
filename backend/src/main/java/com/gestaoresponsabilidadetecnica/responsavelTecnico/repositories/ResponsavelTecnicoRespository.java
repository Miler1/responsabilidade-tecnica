package com.gestaoresponsabilidadetecnica.responsavelTecnico.repositories;

import com.gestaoresponsabilidadetecnica.pessoa.models.Pessoa;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.ResponsavelTecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponsavelTecnicoRespository extends JpaRepository<ResponsavelTecnico, Integer> {

    List<ResponsavelTecnico> findByPessoaOrderById(Pessoa pessoa);

}
