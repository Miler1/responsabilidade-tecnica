package com.gestaoresponsabilidadetecnica.configuracao.interfaces;

import com.gestaoresponsabilidadetecnica.configuracao.enums.Acao;

import javax.servlet.http.HttpServletRequest;

public interface IDefaultService {

    Boolean verificaPermissao(HttpServletRequest request, Acao... acoes);
}
