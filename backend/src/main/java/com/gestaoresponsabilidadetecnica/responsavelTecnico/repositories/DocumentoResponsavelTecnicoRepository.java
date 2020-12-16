package com.gestaoresponsabilidadetecnica.responsavelTecnico.repositories;

import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.DocumentoResponsavelTecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoResponsavelTecnicoRepository extends JpaRepository<DocumentoResponsavelTecnico, Integer> {

    DocumentoResponsavelTecnico findByHash(String hash);

}
