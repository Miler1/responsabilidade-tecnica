package com.gestaoresponsabilidadetecnica.entradaUnica.controllers;

import com.gestaoresponsabilidadetecnica.configuracao.components.VariaveisAmbientes;
import com.gestaoresponsabilidadetecnica.configuracao.utils.GlobalReferences;
import com.gestaoresponsabilidadetecnica.entradaUnica.dtos.AutenticacaoDTO;
import com.gestaoresponsabilidadetecnica.entradaUnica.interfaces.IAutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

	@Autowired
	IAutenticacaoService autenticacaoService;

	@GetMapping(value="/entradaUnica/{sessionKey}")
	public RedirectView entradaUnica(HttpServletRequest request, @PathVariable("sessionKey") String sessionKey) {

		autenticacaoService.entradaUnica(request, sessionKey);

		return new RedirectView(VariaveisAmbientes.baseUrlFrontend() + GlobalReferences.ROOT_PATH);

	}

	@GetMapping(value="/usuario-logado")
	public ResponseEntity<Principal> usuarioLogado(HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		return ResponseEntity.ok()
				.header("Access-Control-Allow-Origin", VariaveisAmbientes.baseUrlFrontend())
				.body(principal);

	}

}
