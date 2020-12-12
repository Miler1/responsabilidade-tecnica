package com.gestaoresponsabilidadetecnica.responsavelTecnico.models;

import br.ufla.lemaf.beans.pessoa.Pessoa;
import com.gestaoresponsabilidadetecnica.especializacaoTecnica.models.EspecializacaoTecnica;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class ResponsavelTecnico implements Serializable {

    @Id
    @SuppressWarnings("unused")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "{validacao.notnull}")
    private String formacao;

    @NotNull(message = "{validacao.notnull}")
    private String conselhoDeClasse;

    @NotNull(message = "{validacao.notnull}")
    private String registro;

    @NotNull(message = "{validacao.notnull}")
    private String nivelResponsabilidadeTecnica;

    @NotNull(message = "{validacao.notnull}")
    private Boolean possuiVinculoComGea;

    @NotNull(message = "{validacao.notnull}")
    private String vinculoEmpregaticio;

    @NotNull(message = "{validacao.notnull}")
    private String outroVinculoEmpregaticio;

    @ManyToOne
    @JoinColumn(name = "id_especializacao", referencedColumnName = "id")
    private EspecializacaoTecnica especializacao;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    private Pessoa pessoa;

    @NotNull(message = "{validacao.notnull}")
    private Date validade;

    @ManyToOne
    @JoinColumn(name = "id_status", referencedColumnName = "id")
    private StatusCadastroResponsavelTecnico status;

    public static class ResponsavelTecnicoBuilder {
        public ResponsavelTecnico build() {
            return new ResponsavelTecnico(this);
        }
    }
}
