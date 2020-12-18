package com.gestaoresponsabilidadetecnica.responsavelTecnico.controllers;

import com.gestaoresponsabilidadetecnica.configuracao.components.VariaveisAmbientes;
import com.gestaoresponsabilidadetecnica.configuracao.controllers.DefaultController;
import com.gestaoresponsabilidadetecnica.configuracao.enums.Acao;
import com.gestaoresponsabilidadetecnica.configuracao.utils.FiltroPesquisa;
import com.gestaoresponsabilidadetecnica.pessoa.interfaces.IPessoaService;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos.ResponsavelTecnicoDTO;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos.RetornoUploadArquivoDTO;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.interfaces.IResponsavelTecnicoService;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.ResponsavelTecnico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;

@RestController
@RequestMapping("/responsavelTecnico/")
public class ResponsavelTecnicoController extends DefaultController {

    private static final String HEADER_CORS = "Access-Control-Allow-Origin";

    @Autowired
    IResponsavelTecnicoService responsavelTecnicoService;

    @Autowired
    IPessoaService pessoaService;

    @PostMapping(value = "salvarSolicitacao")
    public ResponseEntity<ResponsavelTecnico> salvarSolicitacao(
            HttpServletRequest request, @Valid @RequestBody ResponsavelTecnicoDTO responsavelTecnicoDTO) throws Exception {

        verificarPermissao(request, Acao.SOLICITAR_CADASTRO);

        ResponsavelTecnico responsavelTecnico = responsavelTecnicoService.salvar(request, responsavelTecnicoDTO);

        return ResponseEntity.ok()
                .header(HEADER_CORS, VariaveisAmbientes.baseUrlFrontend())
                .body(responsavelTecnico);

    }

    @PostMapping(value = "editarSolicitacao")
    public ResponseEntity<ResponsavelTecnico> editarSolicitacao(
            HttpServletRequest request, @Valid @RequestBody ResponsavelTecnicoDTO responsavelTecnicoDTO) throws Exception {

        verificarPermissao(request, Acao.EDITAR_CADASTRO);

        ResponsavelTecnico responsavelTecnico = responsavelTecnicoService.editar(request, responsavelTecnicoDTO);

        return ResponseEntity.ok()
                .header(HEADER_CORS, VariaveisAmbientes.baseUrlFrontend())
                .body(responsavelTecnico);

    }

    @PostMapping(value = "listar")
    public ResponseEntity<Page<ResponsavelTecnico>> listar(HttpServletRequest request,
                                                           @PageableDefault(size = 20) Pageable pageable,
                                                           @RequestBody FiltroPesquisa filtroPesquisa) throws Exception {

        verificarPermissao(request, Acao.LISTAR_SOLICITACOES);

        Page<ResponsavelTecnico> atividades = responsavelTecnicoService.listar(pageable, filtroPesquisa);

        return ResponseEntity.ok()
                .header(HEADER_CORS, VariaveisAmbientes.baseUrlFrontend())
                .body(atividades);

    }

    @PostMapping(value = "uploadFile")
    public ResponseEntity<RetornoUploadArquivoDTO> uploadFile(HttpServletRequest request,
                                                              @RequestParam("file") MultipartFile file) throws Exception {

        verificarPermissao(request, Acao.SALVAR_ARQUIVOS);

        RetornoUploadArquivoDTO retornoUploadArquivoDTO = responsavelTecnicoService.salvarAnexo(request, file);

        return ResponseEntity.ok()
                .header(HEADER_CORS, VariaveisAmbientes.baseUrlFrontend())
                .body(retornoUploadArquivoDTO);

    }

    @GetMapping(value = "downloadFile/{hash}")
    public ResponseEntity<InputStreamResource> download(HttpServletRequest request,
                                                        @NotNull @PathVariable("hash") String hash) throws Exception {

        verificarPermissao(request, Acao.BAIXAR_ARQUIVOS);

        File file = responsavelTecnicoService.recuperaArquivo(hash);

        return downloadDocumento(file, file.getName());

    }

    @GetMapping(value = "buscarSolicitacao")
    public ResponseEntity<ResponsavelTecnico> buscarSolicitacao(HttpServletRequest request) throws Exception{

        verificarPermissao(request, Acao.LISTAR_SOLICITACOES);

        ResponsavelTecnico solicitacao = responsavelTecnicoService.findByPessoaLogada(request);

        return ResponseEntity.ok()
                .header(HEADER_CORS, VariaveisAmbientes.baseUrlFrontend())
                .body(solicitacao);

    }

    @GetMapping(value = "buscarSolicitacaoById/{id}")
    public ResponseEntity<ResponsavelTecnico> buscarSolicitacaoById(HttpServletRequest request,
                                                                    @PathVariable("id") Integer id) throws Exception{

        verificarPermissao(request, Acao.LISTAR_SOLICITACOES);

        ResponsavelTecnico solicitacao = responsavelTecnicoService.findByID(id);

        return ResponseEntity.ok()
                .header(HEADER_CORS, VariaveisAmbientes.baseUrlFrontend())
                .body(solicitacao);

    }

}