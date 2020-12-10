package com.gestaoresponsabilidadetecnica.pessoa.services;

import br.ufla.lemaf.beans.pessoa.Pessoa;
import com.gestaoresponsabilidadetecnica.entradaUnica.services.EntradaUnicaWS;
import com.gestaoresponsabilidadetecnica.pessoa.interfaces.IPessoaService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class PessoaService implements IPessoaService {

    @Override
    public Pessoa getPessoaLogada(HttpServletRequest request) {

        Object login = request.getSession().getAttribute("login");

        return EntradaUnicaWS.ws.buscarPessoaFisicaPeloCpf(login.toString());

    }

}
