package com.gestaoresponsabilidadetecnica.pessoa.controllers;

import br.ufla.lemaf.beans.pessoa.Pessoa;
import com.gestaoresponsabilidadetecnica.configuracao.components.VariaveisAmbientes;
import com.gestaoresponsabilidadetecnica.configuracao.controllers.DefaultController;
import com.gestaoresponsabilidadetecnica.configuracao.enums.Acao;
import com.gestaoresponsabilidadetecnica.pessoa.interfaces.IPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/pessoa/")
public class PessoaController extends DefaultController {

    private static final String HEADER_CORS = "Access-Control-Allow-Origin";

    @Autowired
    IPessoaService pessoaService;

    @GetMapping(value = "buscaPessoaLogada")
    public ResponseEntity<Pessoa> buscarPessoaLogada(HttpServletRequest request) throws Exception {

        verificarPermissao(request, Acao.BUSCAR_INFORMACAO_PESSOA);

        Pessoa pessoa = pessoaService.getPessoaLogada(request);

        return ResponseEntity.ok()
                .header(HEADER_CORS, VariaveisAmbientes.baseUrlFrontend())
                .body(pessoa);
    }

}