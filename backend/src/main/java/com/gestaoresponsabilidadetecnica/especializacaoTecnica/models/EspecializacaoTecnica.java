package com.gestaoresponsabilidadetecnica.especializacaoTecnica.models;

import com.gestaoresponsabilidadetecnica.configuracao.utils.GlobalReferences;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Table(schema = GlobalReferences.ESQUEMA, name = "especializacao_tecnica")
public class EspecializacaoTecnica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String codigo;
}
