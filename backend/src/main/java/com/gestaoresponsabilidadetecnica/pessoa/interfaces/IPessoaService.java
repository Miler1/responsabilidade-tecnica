package com.gestaoresponsabilidadetecnica.pessoa.interfaces;

import br.ufla.lemaf.beans.pessoa.Pessoa;
import com.gestaoresponsabilidadetecnica.pessoa.models.PessoaFisica;

import javax.servlet.http.HttpServletRequest;

public interface IPessoaService {

    Pessoa getPessoaLogada(HttpServletRequest request);

    com.gestaoresponsabilidadetecnica.pessoa.models.Pessoa transformPessoaEUByPessoa(Pessoa pessoaEU);
}
