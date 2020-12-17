package com.gestaoresponsabilidadetecnica.responsavelTecnico.repositories;

import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.DocumentoResponsavelTecnico;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.ResponsavelTecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoResponsavelTecnicoRepository extends JpaRepository<DocumentoResponsavelTecnico, Integer> {

    DocumentoResponsavelTecnico findByHash(String hash);

    List<DocumentoResponsavelTecnico> findByResponsavelTecnico(ResponsavelTecnico responsavelTecnico);

}
