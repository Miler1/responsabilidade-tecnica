package com.gestaoresponsabilidadetecnica.configuracao.enums;

import lombok.Getter;

@Getter
public enum Acao {

    LISTAR_SOLICITACOES("LISTAR_SOLICITACOES"),
    SOLICITAR_CADASTRO("SOLICITAR_CADASTRO"),
    BUSCAR_INFORMACAO_PESSOA("BUSCAR_INFORMACAO_PESSOA"),
    GERENCIAR_ARQUIVOS("GERENCIAR_ARQUIVOS"),
    BAIXAR_ARQUIVOS("BAIXAR_ARQUIVOS"),
    EDITAR_CADASTRO("EDITAR_CADASTRO");

    private String codigo;

    Acao(String codigo) {

        this.codigo = codigo;
    }

}
