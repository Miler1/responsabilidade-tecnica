package com.gestaoresponsabilidadetecnica.responsavelTecnico.models;

import com.gestaoresponsabilidadetecnica.configuracao.utils.GlobalReferences;
import com.gestaoresponsabilidadetecnica.especializacaoTecnica.models.EspecializacaoTecnica;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = GlobalReferences.ESQUEMA, name = "documento_responsavel_tecnico")
public class DocumentoResponsavelTecnico {

    @Id
    @SuppressWarnings("unused")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "{validacao.notnull}")
    private String caminho;

    @NotNull(message = "{validacao.notnull}")
    private String hash;

    @NotNull(message = "{validacao.notnull}")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_responsavel_tecnico", referencedColumnName = "id")
    private ResponsavelTecnico responsavelTecnico;

    public DocumentoResponsavelTecnico() {}

    public DocumentoResponsavelTecnico(File file, ResponsavelTecnico responsavelTecnico) {

        this.caminho = file.getAbsolutePath();
        this.nome = file.getName();
        this.responsavelTecnico = responsavelTecnico;
        this.hash = UUID.randomUUID().toString();

    }
}
