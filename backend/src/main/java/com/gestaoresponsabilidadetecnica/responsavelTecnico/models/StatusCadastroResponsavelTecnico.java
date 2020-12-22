package com.gestaoresponsabilidadetecnica.responsavelTecnico.models;

import com.gestaoresponsabilidadetecnica.configuracao.utils.GlobalReferences;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.enums.StatusSolicitacao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = GlobalReferences.ESQUEMA, name = "status_cadastro_responsavel_tecnico")
public class StatusCadastroResponsavelTecnico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "{validacao.notnull}")
    private String nome;

    @NotNull(message = "{validacao.notnull}")
    private String codigo;

    public boolean is(StatusSolicitacao status) {
        return status.getCodigo().equals(this.codigo);
    }

}