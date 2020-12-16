package com.gestaoresponsabilidadetecnica.responsavelTecnico.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gestaoresponsabilidadetecnica.configuracao.utils.GlobalReferences;
import com.gestaoresponsabilidadetecnica.especializacaoTecnica.models.EspecializacaoTecnica;
import com.gestaoresponsabilidadetecnica.pessoa.models.PessoaFisica;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = GlobalReferences.ESQUEMA, name = "responsavel_tecnico")
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
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
    private PessoaFisica pessoa;

    @NotNull(message = "{validacao.notnull}")
    private Date validade;

    @ManyToOne
    @JoinColumn(name = "id_status", referencedColumnName = "id")
    private StatusCadastroResponsavelTecnico status;

    @OneToMany(mappedBy="responsavelTecnico")
    @JsonManagedReference
    private List<DocumentoResponsavelTecnico> documentos;

    public ResponsavelTecnico(ResponsavelTecnicoBuilder responsavelTecnicoBuilder) {

        this.formacao = responsavelTecnicoBuilder.formacao;
        this.conselhoDeClasse = responsavelTecnicoBuilder.conselhoDeClasse;
        this.registro = responsavelTecnicoBuilder.registro;
        this.nivelResponsabilidadeTecnica = responsavelTecnicoBuilder.nivelResponsabilidadeTecnica;
        this.possuiVinculoComGea = responsavelTecnicoBuilder.possuiVinculoComGea;
        this.vinculoEmpregaticio = responsavelTecnicoBuilder.vinculoEmpregaticio;
        this.outroVinculoEmpregaticio = responsavelTecnicoBuilder.outroVinculoEmpregaticio;
        this.especializacao = responsavelTecnicoBuilder.especializacao;
        this.pessoa = responsavelTecnicoBuilder.pessoa;
        this.validade = responsavelTecnicoBuilder.validade;
        this.status = responsavelTecnicoBuilder.status;

    }

    public static class ResponsavelTecnicoBuilder {

        String formacao;
        String conselhoDeClasse;
        String registro;
        String nivelResponsabilidadeTecnica;
        Boolean possuiVinculoComGea;
        String vinculoEmpregaticio;
        String outroVinculoEmpregaticio;
        EspecializacaoTecnica especializacao;
        PessoaFisica pessoa;
        Date validade;
        StatusCadastroResponsavelTecnico status;

        public ResponsavelTecnicoBuilder() {}

        public ResponsavelTecnicoBuilder setFormacao(String formacao) {
            this.formacao = formacao;
            return this;
        }

        public ResponsavelTecnicoBuilder setConselhoDeClasse(String conselhoDeClasse) {
            this.conselhoDeClasse = conselhoDeClasse;
            return this;
        }

        public ResponsavelTecnicoBuilder setRegistro(String registro) {
            this.registro = registro;
            return this;
        }

        public ResponsavelTecnicoBuilder setNivelResponsabilidadeTecnica(String nivelResponsabilidadeTecnica) {
            this.nivelResponsabilidadeTecnica = nivelResponsabilidadeTecnica;
            return this;
        }

        public ResponsavelTecnicoBuilder setPossuiVinculoComGea(Boolean possuiVinculoComGea) {
            this.possuiVinculoComGea = possuiVinculoComGea;
            return this;
        }

        public ResponsavelTecnicoBuilder setVinculoEmpregaticio(String vinculoEmpregaticio) {
            this.vinculoEmpregaticio = vinculoEmpregaticio;
            return this;
        }

        public ResponsavelTecnicoBuilder setOutroVinculoEmpregaticio(String outroVinculoEmpregaticio) {
            this.outroVinculoEmpregaticio = outroVinculoEmpregaticio;
            return this;
        }

        public ResponsavelTecnicoBuilder setEspecializacao(EspecializacaoTecnica especializacao) {
            this.especializacao = especializacao;
            return this;
        }

        public ResponsavelTecnicoBuilder setPessoa(PessoaFisica pessoa) {
            this.pessoa = pessoa;
            return this;
        }

        public ResponsavelTecnicoBuilder setValidade(Date validade) {
            this.validade = validade;
            return this;
        }

        public ResponsavelTecnicoBuilder setStatus(StatusCadastroResponsavelTecnico status) {
            this.status = status;
            return this;
        }

        public ResponsavelTecnico build() {
            return new ResponsavelTecnico(this);
        }
    }

}