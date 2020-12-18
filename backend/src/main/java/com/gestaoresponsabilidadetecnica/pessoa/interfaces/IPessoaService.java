package com.gestaoresponsabilidadetecnica.pessoa.interfaces;

import br.ufla.lemaf.beans.pessoa.Pessoa;
import com.gestaoresponsabilidadetecnica.pessoa.models.PessoaFisica;

import javax.servlet.http.HttpServletRequest;

public interface IPessoaService {

    Pessoa getPessoaLogada(HttpServletRequest request);

    PessoaFisica transformPessoaEUByPessoa(Pessoa pessoaEU);

    Pessoa buscarPessoaById(HttpServletRequest request, Integer idPessoa);

}