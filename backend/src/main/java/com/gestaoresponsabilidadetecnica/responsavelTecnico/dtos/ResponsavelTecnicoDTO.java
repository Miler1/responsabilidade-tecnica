package com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gestaoresponsabilidadetecnica.especializacaoTecnica.dtos.EspecializacaoTecnicaDTO;
import lombok.Getter;

import java.io.File;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsavelTecnicoDTO {

    private Integer id;

    private String conselhoDeClasse;

    private EspecializacaoTecnicaDTO especializacao;

    private String formacao;

    private String nivelResponsabilidadeTecnica;

    private String outroVinculoEmpregaticio;

    private Boolean possuiVinculoComGea;

    private String registro;

    private String vinculoEmpregaticio;

}
