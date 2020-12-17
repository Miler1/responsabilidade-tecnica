package com.gestaoresponsabilidadetecnica.especializacaoTecnica.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gestaoresponsabilidadetecnica.especializacaoTecnica.models.EspecializacaoTecnica;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EspecializacaoTecnicaDTO {

    private Integer id;

    private String codigo;

    private String nome;

}
