package com.gestaoresponsabilidadetecnica.pessoa.services;

import br.ufla.lemaf.beans.pessoa.Pessoa;
import com.gestaoresponsabilidadetecnica.entradaUnica.services.EntradaUnicaWS;
import com.gestaoresponsabilidadetecnica.pessoa.interfaces.IPessoaService;
import com.gestaoresponsabilidadetecnica.pessoa.repositories.PessoaRepository;
import org.apache.commons.collections4.iterators.PeekingIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class PessoaService implements IPessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Override
    public Pessoa getPessoaLogada(HttpServletRequest request) {

        Object login = request.getSession().getAttribute("login");

        return EntradaUnicaWS.ws.buscarPessoaFisicaPeloCpf(login.toString());

    }

    @Override
    public com.gestaoresponsabilidadetecnica.pessoa.models.Pessoa transformPessoaEUByPessoa(Pessoa pessoaEU) {
       return pessoaRepository.findById(pessoaEU.id).orElse(null);
    }

}
