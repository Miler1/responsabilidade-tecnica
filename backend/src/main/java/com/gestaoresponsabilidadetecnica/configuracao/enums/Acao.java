package com.gestaoresponsabilidadetecnica.configuracao.enums;

import lombok.Getter;

@Getter
public enum Acao {

    LISTAR_SOLICITACOES("LISTAR_SOLICITACOES"),
    SOLICITAR_CADASTRO("SOLICITAR_CADASTRO"),
    BUSCAR_INFORMACAO_PESSOA("BUSCAR_INFORMACAO_PESSOA");

    private String codigo;

    Acao(String codigo) {

        this.codigo = codigo;
    }

}
