package com.gestaoresponsabilidadetecnica.entradaUnica.controllers;

import com.gestaoresponsabilidadetecnica.configuracao.components.VariaveisAmbientes;
import com.gestaoresponsabilidadetecnica.entradaUnica.services.EntradaUnicaWS;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entradaUnica")
public class EntradaUnicaController {

    private static final String HEADER_CORS = "Access-Control-Allow-Origin";

    @GetMapping(value = "setores")
    public ResponseEntity<String> findSetores() {

        return ResponseEntity.ok()
                .header(HEADER_CORS, VariaveisAmbientes.baseUrlFrontend())
                .body(EntradaUnicaWS.findSetores());

    }

}
