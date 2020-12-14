package com.gestaoresponsabilidadetecnica.responsavelTecnico.controllers;

import com.gestaoresponsabilidadetecnica.configuracao.components.VariaveisAmbientes;
import com.gestaoresponsabilidadetecnica.configuracao.controllers.DefaultController;
import com.gestaoresponsabilidadetecnica.configuracao.enums.Acao;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos.ResponsavelTecnicoDTO;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos.RetornoUploadArquivoDTO;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.interfaces.IResponsavelTecnicoService;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.ResponsavelTecnico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/responsavelTecnico/")
public class ResponsavelTecnicoController extends DefaultController {

    private static final String HEADER_CORS = "Access-Control-Allow-Origin";

    @Autowired
    IResponsavelTecnicoService responsavelTecnicoService;

    @PostMapping(value = "salvarSolicitacao")
    public ResponseEntity<ResponsavelTecnico> salvarSolicitacao(
            HttpServletRequest request, @Valid @RequestBody ResponsavelTecnicoDTO responsavelTecnicoDTO) throws Exception {

        verificarPermissao(request, Acao.SOLICITAR_CADASTRO);

        ResponsavelTecnico responsavelTecnico = responsavelTecnicoService.salvar(request, responsavelTecnicoDTO);

        return ResponseEntity.ok()
                .header(HEADER_CORS, VariaveisAmbientes.baseUrlFrontend())
                .body(responsavelTecnico);
    }

//    SALVAR_ARQUIVOS

    @PostMapping(value = "uploadFile")
    public ResponseStatus uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception {

        verificarPermissao(request, Acao.SALVAR_ARQUIVOS);

        RetornoUploadArquivoDTO retornoUploadArquivoDTO = responsavelTecnicoService.salvarAnexo(request, file);

        return null;
    }

}
