package com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gestaoresponsabilidadetecnica.especializacaoTecnica.models.EspecializacaoTecnica;
import com.gestaoresponsabilidadetecnica.pessoa.models.PessoaFisica;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.StatusCadastroResponsavelTecnico;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsavelTecnicoDTO {

    private Integer id;

    private String conselhoDeClasse;

    private EspecializacaoTecnica especializacao;

    private String formacao;

    private String nivelResponsabilidadeTecnica;

    private String outroVinculoEmpregaticio;

    private Boolean possuiVinculoComGea;

    private String registro;

    private String vinculoEmpregaticio;

    private String justificativa;

    private StatusCadastroResponsavelTecnico status;

    private PessoaFisica pessoaFisica;

}