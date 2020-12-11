package com.gestaoresponsabilidadetecnica.configuracao.interfaces;

import com.gestaoresponsabilidadetecnica.configuracao.enums.Acao;

import javax.servlet.http.HttpServletRequest;

public interface IDefaultService {

    Boolean verificarPermissao(HttpServletRequest request, Acao... acoes);
}
