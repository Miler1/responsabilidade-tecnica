package com.gestaoresponsabilidadetecnica.pessoa.models;

import com.gestaoresponsabilidadetecnica.configuracao.utils.GlobalReferences;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = GlobalReferences.ESQUEMA, name = "pessoa_fisica")
@PrimaryKeyJoinColumn(name = "id_pessoa", referencedColumnName = "id")
public class PessoaFisica extends Pessoa {

    @NotNull(message = "{validacao.notnull}")
    private String nome;

    @NotNull(message = "{validacao.notnull}")
    private String cpf;

    @NotNull(message = "{validacao.notnull}")
    private String numeroRg;

    @NotNull(message = "{validacao.notnull}")
    private String orgaoExpedidorRg;

    @NotNull(message = "{validacao.notnull}")
    private Integer sexo;

    @NotNull(message = "{validacao.notnull}")
    private String naturalidade;

    @NotNull(message = "{validacao.notnull}")
    private Date dataNascimento;

    @NotNull(message = "{validacao.notnull}")
    private String numeroTituloEleitoral;

    @NotNull(message = "{validacao.notnull}")
    private String zonaTituloEleitoral;

    @NotNull(message = "{validacao.notnull}")
    private String secaoTituloEleitoral;

    @NotNull(message = "{validacao.notnull}")
    private String nomeMae;

}