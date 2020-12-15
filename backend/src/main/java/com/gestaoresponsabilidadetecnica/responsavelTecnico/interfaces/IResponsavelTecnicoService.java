package com.gestaoresponsabilidadetecnica.responsavelTecnico.interfaces;

import com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos.ResponsavelTecnicoDTO;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos.RetornoUploadArquivoDTO;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.ResponsavelTecnico;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface IResponsavelTecnicoService {

    ResponsavelTecnico salvar(HttpServletRequest request, ResponsavelTecnicoDTO responsavelTecnicoDTO);

    RetornoUploadArquivoDTO salvarAnexo(HttpServletRequest request, MultipartFile file) throws Exception;

}
