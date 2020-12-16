package com.gestaoresponsabilidadetecnica.responsavelTecnico.services;

import com.gestaoresponsabilidadetecnica.configuracao.components.VariaveisAmbientes;
import com.gestaoresponsabilidadetecnica.configuracao.utils.ArquivoUtils;
import com.gestaoresponsabilidadetecnica.configuracao.utils.DateUtil;
import com.gestaoresponsabilidadetecnica.configuracao.utils.FiltroPesquisa;
import com.gestaoresponsabilidadetecnica.especializacaoTecnica.models.EspecializacaoTecnica;
import com.gestaoresponsabilidadetecnica.especializacaoTecnica.repositories.EspecializacaoTecnicaRepository;
import com.gestaoresponsabilidadetecnica.pessoa.interfaces.IPessoaService;
import com.gestaoresponsabilidadetecnica.pessoa.models.Pessoa;
import com.gestaoresponsabilidadetecnica.pessoa.models.PessoaFisica;
import com.gestaoresponsabilidadetecnica.pessoa.repositories.PessoaRepository;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos.ResponsavelTecnicoDTO;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos.RetornoUploadArquivoDTO;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.interfaces.IResponsavelTecnicoService;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.DocumentoResponsavelTecnico;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.ResponsavelTecnico;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.StatusCadastroResponsavelTecnico;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.repositories.DocumentoResponsavelTecnicoRepository;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.repositories.ResponsavelTecnicoRespository;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.repositories.StatusCadastroResponsavelTecnicoRepository;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.specifications.ResponsavelTecnicoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ResponsavelTecnicoService implements IResponsavelTecnicoService {

    private static final String DIR_ARQUIVOS_RESPONSABILIDADE_TECNICA = "responsavel-tecnico";

    @Autowired
    EspecializacaoTecnicaRepository especializacaoTecnicaRepository;

    @Autowired
    StatusCadastroResponsavelTecnicoRepository statusCadastroResponsavelTecnicoRepository;

    @Autowired
    ResponsavelTecnicoRespository responsavelTecnicoRespository;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    DocumentoResponsavelTecnicoRepository documentoResponsavelTecnicoRepository;

    @Autowired
    IPessoaService pessoaService;

    @Override
    public ResponsavelTecnico salvar(HttpServletRequest request, ResponsavelTecnicoDTO responsavelTecnicoDTO) {

        EspecializacaoTecnica especializacaoTecnica = especializacaoTecnicaRepository.findById(responsavelTecnicoDTO.getEspecializacao().getId()).orElse(null);

        br.ufla.lemaf.beans.pessoa.Pessoa pessoaEU = pessoaService.getPessoaLogada(request);

        PessoaFisica pessoa = pessoaRepository.findById(pessoaEU.id).orElse(null);

        StatusCadastroResponsavelTecnico status = statusCadastroResponsavelTecnicoRepository.findByCodigo("AGUARDANDO_ANALISE");

        ResponsavelTecnico responsavelTecnico = new ResponsavelTecnico.ResponsavelTecnicoBuilder()
                .setConselhoDeClasse(responsavelTecnicoDTO.getConselhoDeClasse())
                .setEspecializacao(especializacaoTecnica)
                .setFormacao(responsavelTecnicoDTO.getFormacao())
                .setNivelResponsabilidadeTecnica(responsavelTecnicoDTO.getNivelResponsabilidadeTecnica())
                .setOutroVinculoEmpregaticio(responsavelTecnicoDTO.getOutroVinculoEmpregaticio())
                .setPessoa(pessoa)
                .setPossuiVinculoComGea(responsavelTecnicoDTO.getPossuiVinculoComGea())
                .setRegistro(responsavelTecnicoDTO.getRegistro())
                .setStatus(status)
                .setVinculoEmpregaticio(responsavelTecnicoDTO.getVinculoEmpregaticio())
                .build();

        responsavelTecnicoRespository.save(responsavelTecnico);

        return responsavelTecnico;

    }

    @Override
    public Page<ResponsavelTecnico> listar(Pageable pageable, FiltroPesquisa filtro) {

        Specification<ResponsavelTecnico> specification = prepararFiltro(filtro);

        return responsavelTecnicoRespository.findAll(specification, pageable);

    }

    private Specification<ResponsavelTecnico> prepararFiltro(FiltroPesquisa filtro) {

        Specification<ResponsavelTecnico> specification = Specification.where(ResponsavelTecnicoSpecification.padrao());

        if (filtro.getStringPesquisa() != null) {

            specification = specification.and(ResponsavelTecnicoSpecification.nome(filtro.getStringPesquisa())
                    .or(ResponsavelTecnicoSpecification.status(filtro.getStringPesquisa())));

        }

        return specification;

    }

    public List<ResponsavelTecnico> buscarNovosExpirados() {

        StatusCadastroResponsavelTecnico statusAtivo = statusCadastroResponsavelTecnicoRepository.findByCodigo("APROVADO");

        return responsavelTecnicoRespository.findAll(
                Specification
                        .where(ResponsavelTecnicoSpecification.vencidoPorData(new Date()))
                        .and(ResponsavelTecnicoSpecification.status(statusAtivo))
        );

    }

    public StatusCadastroResponsavelTecnico buscarStatusPorCodigo(String codigo) {
        return statusCadastroResponsavelTecnicoRepository.findByCodigo(codigo);
    }

    public void mudarStatusResponsavelTecnico(ResponsavelTecnico responsavelTecnico, StatusCadastroResponsavelTecnico status) {

        responsavelTecnico.setStatus(status);
        responsavelTecnicoRespository.save(responsavelTecnico);

    }

    private ResponsavelTecnico findByPessoaLogada(HttpServletRequest request) {

        br.ufla.lemaf.beans.pessoa.Pessoa pessoaEU = pessoaService.getPessoaLogada(request);

        Pessoa pessoa = pessoaService.transformPessoaEUByPessoa(pessoaEU);

        List<ResponsavelTecnico> responsaveis = findByPessoa(request, pessoa);

        return responsaveis.get(responsaveis.size() - 1);

    }

    @Override

    public List<ResponsavelTecnico> findByPessoa(HttpServletRequest request, Pessoa pessoa) {
        return responsavelTecnicoRespository.findByPessoaOrderById(pessoa);
    }

    @Override
    public RetornoUploadArquivoDTO salvarAnexo(HttpServletRequest request, MultipartFile multipartFile) throws Exception {

        File file = salvaArquivoDiretorio(multipartFile);

        DocumentoResponsavelTecnico documentoResponsavelTecnico = new DocumentoResponsavelTecnico(file, findByPessoaLogada(request));

        documentoResponsavelTecnicoRepository.save(documentoResponsavelTecnico);

        return new RetornoUploadArquivoDTO(documentoResponsavelTecnico);

    }
    
    private File salvaArquivoDiretorio(MultipartFile multipartFile) throws Exception {

//        validaTipoArquivo(multipartFile);

        final DateTimeFormatter FORMATO_DATA_MES_ANO = DateTimeFormatter.ofPattern("MM-YYYY");

        String pathSalvarArquivo = VariaveisAmbientes.pathSalvarArquivos() +
                File.separator + DIR_ARQUIVOS_RESPONSABILIDADE_TECNICA +
                File.separator + LocalDate.now().format(FORMATO_DATA_MES_ANO) +
                File.separator + UUID.randomUUID() + "." + multipartFile.getContentType().split("/")[1];

        return ArquivoUtils.salvaArquivoDiretorio(multipartFile, pathSalvarArquivo);

    }

//    private void validaTipoArquivo(MultipartFile multipartFile) throws Exception {
//
//        if(!multipartFile.getOriginalFilename().endsWith(EXTENSAO_ARQUIVO_SINCRONIA)) {
//            throw new Exception("A extensão do arquivo informado deve ser " + EXTENSAO_ARQUIVO_SINCRONIA);
//        }
//
//    }

}