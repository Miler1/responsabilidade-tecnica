package com.gestaoresponsabilidadetecnica.configuracao.controllers;

import com.gestaoresponsabilidadetecnica.configuracao.enums.Acao;
import com.gestaoresponsabilidadetecnica.configuracao.exceptions.PermissionException;
import com.gestaoresponsabilidadetecnica.configuracao.interfaces.IDefaultService;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.enums.ExtensaoArquivo;
import org.apache.commons.io.FileUtils;
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
import java.io.IOException;

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

	protected ResponseEntity<byte[]> downloadFile(File arquivo, String nomeDocumento) throws IOException {

		// represent PDF as byteArray for further serialization
		byte[] byteArray = FileUtils.readFileToByteArray(arquivo);

		// serialize PDF to Base64
		byte[] encodedBytes = java.util.Base64.getEncoder().encode(byteArray);

		//Setting Headers
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.parseMediaType(ExtensionResolver(nomeDocumento)));
		httpHeaders.setContentDispositionFormData("attachment", nomeDocumento);
		httpHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		httpHeaders.setContentLength(encodedBytes.length);

		return new ResponseEntity<>(encodedBytes, httpHeaders, HttpStatus.OK);

	}

	protected void verificarPermissao(HttpServletRequest request, Acao... acoes) throws Exception {

		boolean permitido = defaultService.verificarPermissao(request, acoes);

		if(!permitido) {
			throw new PermissionException("Usuário sem permissão para realizar a ação!");
		}

	}

	protected String ExtensionResolver(String fileName) {

		String[] extensao = fileName.split("\\.");

		switch (extensao[1]) {

			case "pdf" -> {
				return ExtensaoArquivo.PDF.getCodigo();
			}

			case "jpg" -> {
				return ExtensaoArquivo.JPG.getCodigo();
			}

			case "jpeg" -> {
				return ExtensaoArquivo.JPEG.getCodigo();
			}

			case "tif" -> {
				return ExtensaoArquivo.TIF.getCodigo();
			}

			case "bmp" -> {
				return ExtensaoArquivo.BMP.getCodigo();
			}

			case "png" -> {
				return ExtensaoArquivo.PNG.getCodigo();
			}

			default -> {
				return "application/octet-stream";
			}

		}

	}

}