package com.gestaoresponsabilidadetecnica.pessoa.interfaces;

import br.ufla.lemaf.beans.pessoa.Pessoa;

import javax.servlet.http.HttpServletRequest;

public interface IPessoaService {

    Pessoa getPessoaLogada(HttpServletRequest request);

}