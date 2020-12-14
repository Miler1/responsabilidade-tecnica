package com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos;

import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.DocumentoResponsavelTecnico;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class RetornoUploadArquivoDTO implements Serializable {

	private String hash;

	public RetornoUploadArquivoDTO(DocumentoResponsavelTecnico documentoResponsavelTecnico) {

		this.hash = documentoResponsavelTecnico.getHash();

	}

}
