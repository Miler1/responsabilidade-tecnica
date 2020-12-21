package com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentoResponsavelTecnicoDTO {

    private Integer id;

    private String nome;

}
