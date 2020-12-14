package com.gestaoresponsabilidadetecnica.responsavelTecnico.repositories;

import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.StatusCadastroResponsavelTecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusCadastroResponsavelTecnicoRepository extends JpaRepository<StatusCadastroResponsavelTecnico, Integer> {

    StatusCadastroResponsavelTecnico findByCodigo(String codigo);

}
