package com.gestaoresponsabilidadetecnica.pessoa.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gestaoresponsabilidadetecnica.configuracao.utils.GlobalReferences;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = GlobalReferences.ESQUEMA, name = "pessoa_fisica")
@PrimaryKeyJoinColumn(name = "id_pessoa", referencedColumnName = "id")
public class PessoaFisica extends Pessoa {

    @NotNull(message = "{validacao.notnull}")
    private String nome;

}
