package com.gestaoresponsabilidadetecnica.responsavelTecnico.enums;

import lombok.Getter;

@Getter
public enum StatusSolicitacao {

    AGUARDANDO_ANALISE("AGUARDANDO_ANALISE"),
    APROVADO("APROVADO"),
    REPROVADO("REPROVADO"),
    VENCIDO("VENCIDO");

    private String codigo;

    StatusSolicitacao(String codigo) {
        this.codigo = codigo;
    }

}
