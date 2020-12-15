package com.gestaoresponsabilidadetecnica.responsavelTecnico.interfaces;

import com.gestaoresponsabilidadetecnica.configuracao.utils.FiltroPesquisa;
import com.gestaoresponsabilidadetecnica.pessoa.models.Pessoa;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos.ResponsavelTecnicoDTO;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos.RetornoUploadArquivoDTO;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.ResponsavelTecnico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IResponsavelTecnicoService {

    ResponsavelTecnico salvar(HttpServletRequest request, ResponsavelTecnicoDTO responsavelTecnicoDTO);

    Page<ResponsavelTecnico> listar(Pageable pageable, FiltroPesquisa filtro);

    RetornoUploadArquivoDTO salvarAnexo(HttpServletRequest request, MultipartFile file) throws Exception;

    List<ResponsavelTecnico> findByPessoa(HttpServletRequest request, Pessoa pessoa);

    List<ResponsavelTecnico> buscarSolicitacao(HttpServletRequest request, Integer idPessoa);

}