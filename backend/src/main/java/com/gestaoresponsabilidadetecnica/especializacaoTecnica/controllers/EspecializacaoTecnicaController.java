package com.gestaoresponsabilidadetecnica.especializacaoTecnica.controllers;

import br.ufla.lemaf.beans.pessoa.Pessoa;
import com.gestaoresponsabilidadetecnica.configuracao.components.VariaveisAmbientes;
import com.gestaoresponsabilidadetecnica.configuracao.controllers.DefaultController;
import com.gestaoresponsabilidadetecnica.configuracao.enums.Acao;
import com.gestaoresponsabilidadetecnica.especializacaoTecnica.interfaces.IEspecializacaoTecnicaService;
import com.gestaoresponsabilidadetecnica.especializacaoTecnica.models.EspecializacaoTecnica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/especializacaoTecnica")
public class EspecializacaoTecnicaController extends DefaultController {

    private static final String HEADER_CORS = "Access-Control-Allow-Origin";

    @Autowired
    IEspecializacaoTecnicaService atividadeCnaeService;

    @GetMapping(value = "buscaEspecializacoesTecnicas")
    public ResponseEntity<List<EspecializacaoTecnica>> buscarEspecializacoesTecnicas(HttpServletRequest request) throws Exception {

        verificarPermissao(request, Acao.BUSCAR_INFORMACAO_PESSOA);

        List<EspecializacaoTecnica> especializacoesTecnicas = atividadeCnaeService.findEspecializacoesTecnicas();

        return ResponseEntity.ok()
                .header(HEADER_CORS, VariaveisAmbientes.baseUrlFrontend())
                .body(especializacoesTecnicas);
    }
}
