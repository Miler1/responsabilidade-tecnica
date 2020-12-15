package com.gestaoresponsabilidadetecnica.responsavelTecnico.repositories;

import com.gestaoresponsabilidadetecnica.pessoa.models.Pessoa;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.ResponsavelTecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponsavelTecnicoRespository extends JpaRepository<ResponsavelTecnico, Integer>, JpaSpecificationExecutor<ResponsavelTecnico> {

    List<ResponsavelTecnico> findByPessoaOrderById(Pessoa pessoa);

}