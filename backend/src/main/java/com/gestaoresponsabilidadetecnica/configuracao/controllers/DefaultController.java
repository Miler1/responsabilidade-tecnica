package com.gestaoresponsabilidadetecnica.configuracao.controllers;

import com.gestaoresponsabilidadetecnica.configuracao.enums.Acao;
import com.gestaoresponsabilidadetecnica.configuracao.exceptions.PermissionException;
import com.gestaoresponsabilidadetecnica.configuracao.interfaces.IDefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DefaultController {

    @Autowired
    IDefaultService defaultService;

	protected ResponseEntity<InputStreamResource> downloadDocumento(File arquivo, String nomeDocumento) throws FileNotFoundException {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.parseMediaType("application/octet-stream"));
		httpHeaders.setContentDispositionFormData("attachment", nomeDocumento);

		InputStreamResource isr = new InputStreamResource(new FileInputStream(arquivo));

		return new ResponseEntity<>(isr, httpHeaders, HttpStatus.OK);

	}

	protected void verificarPermissao(HttpServletRequest request, Acao... acoes) throws Exception {

		boolean permitido = defaultService.verificarPermissao(request, acoes);

		if(!permitido) {
			throw new PermissionException("Usuário sem permissão para realizar a ação!");
		}

	}

}