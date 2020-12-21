package com.gestaoresponsabilidadetecnica.responsavelTecnico.interfaces;

import com.gestaoresponsabilidadetecnica.configuracao.utils.FiltroPesquisa;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos.ResponsavelTecnicoDTO;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos.RetornoUploadArquivoDTO;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.ResponsavelTecnico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public interface IResponsavelTecnicoService {

    ResponsavelTecnico salvar(HttpServletRequest request, ResponsavelTecnicoDTO responsavelTecnicoDTO);

    ResponsavelTecnico editar(HttpServletRequest request, ResponsavelTecnicoDTO responsavelTecnicoDTO);

    Page<ResponsavelTecnico> listar(Pageable pageable, FiltroPesquisa filtro);

    RetornoUploadArquivoDTO salvarAnexo(HttpServletRequest request, MultipartFile file) throws Exception;

    RetornoUploadArquivoDTO removerAnexo(HttpServletRequest request, ResponsavelTecnicoDTO responsavelTecnicoDTO) throws Exception;

    ResponsavelTecnico findByPessoaLogada(HttpServletRequest request);

    ResponsavelTecnico findByID(Integer id);

    File recuperaArquivo(String hash);

}