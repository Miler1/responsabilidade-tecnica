package com.gestaoresponsabilidadetecnica.responsavelTecnico.repositories;

import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.ResponsavelTecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsavelTecnicoRespository extends JpaRepository<ResponsavelTecnico, Integer> {
}
