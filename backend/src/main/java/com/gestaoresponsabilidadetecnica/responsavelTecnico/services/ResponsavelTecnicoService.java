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
import com.gestaoresponsabilidadetecnica.responsavelTecnico.enums.StatusSolicitacao;
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
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ResponsavelTecnicoService implements IResponsavelTecnicoService {

    private static final String DIR_ARQUIVOS_RESPONSABILIDADE_TECNICA = "responsavel-tecnico";
    private static final DateTimeFormatter FORMATO_DATA_MES_ANO = DateTimeFormatter.ofPattern("MM-YYYY");

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

        EspecializacaoTecnica especializacaoTecnica = especializacaoTecnicaRepository.findById(
                responsavelTecnicoDTO.getEspecializacao().getId()).orElse(null);

        br.ufla.lemaf.beans.pessoa.Pessoa pessoaEU = pessoaService.getPessoaLogada(request);

        PessoaFisica pessoa = pessoaRepository.findById(pessoaEU.id).orElse(null);

        StatusCadastroResponsavelTecnico status = statusCadastroResponsavelTecnicoRepository.findByCodigo(
                StatusSolicitacao.AGUARDANDO_ANALISE.getCodigo());

        ResponsavelTecnico responsavelTecnico = new ResponsavelTecnico.ResponsavelTecnicoBuilder()
                .setConselhoDeClasse(responsavelTecnicoDTO.getConselhoDeClasse())
                .setEspecializacao(especializacaoTecnica)
                .setFormacao(responsavelTecnicoDTO.getFormacao())
                .setNivelResponsabilidadeTecnica(responsavelTecnicoDTO.getNivelResponsabilidadeTecnica())
                .setOutroVinculoEmpregaticio(responsavelTecnicoDTO.getOutroVinculoEmpregaticio())
                .setPessoa(pessoa)
                .setStatus(status)
                .setPossuiVinculoComGea(responsavelTecnicoDTO.getPossuiVinculoComGea())
                .setRegistro(responsavelTecnicoDTO.getRegistro())
                .setVinculoEmpregaticio(responsavelTecnicoDTO.getVinculoEmpregaticio())
                .build();

        responsavelTecnicoRespository.save(responsavelTecnico);

        return responsavelTecnico;

    }

    @Override
    public ResponsavelTecnico editar(HttpServletRequest request, ResponsavelTecnicoDTO responsavelTecnicoDTO) {

        EspecializacaoTecnica especializacaoTecnica = especializacaoTecnicaRepository.findById(
                responsavelTecnicoDTO.getEspecializacao().getId()).orElse(null);

        String codigoStatus = responsavelTecnicoDTO.getStatus().getCodigo();

        StatusCadastroResponsavelTecnico status = statusCadastroResponsavelTecnicoRepository.findByCodigo(codigoStatus);

        ResponsavelTecnico responsavelTecnico = responsavelTecnicoRespository.findById(responsavelTecnicoDTO.getId()).orElse(null);

        if (responsavelTecnico != null) {

            responsavelTecnico.setConselhoDeClasse(responsavelTecnicoDTO.getConselhoDeClasse());
            responsavelTecnico.setEspecializacao(especializacaoTecnica);
            responsavelTecnico.setFormacao(responsavelTecnicoDTO.getFormacao());
            responsavelTecnico.setNivelResponsabilidadeTecnica(responsavelTecnicoDTO.getNivelResponsabilidadeTecnica());
            responsavelTecnico.setOutroVinculoEmpregaticio(responsavelTecnicoDTO.getOutroVinculoEmpregaticio());
            responsavelTecnico.setPessoa(responsavelTecnicoDTO.getPessoaFisica());
            responsavelTecnico.setStatus(status);
            responsavelTecnico.setJustificativa(responsavelTecnicoDTO.getJustificativa());
            responsavelTecnico.setPossuiVinculoComGea(responsavelTecnicoDTO.getPossuiVinculoComGea());
            responsavelTecnico.setRegistro(responsavelTecnicoDTO.getRegistro());
            responsavelTecnico.setVinculoEmpregaticio(responsavelTecnicoDTO.getVinculoEmpregaticio());

            if (status.is(StatusSolicitacao.APROVADO)) {
                responsavelTecnico.setValidade(DateUtil.calcularValidade());
            } else if (status.is(StatusSolicitacao.REPROVADO)) {
                responsavelTecnico.setValidade(null);
            }

            responsavelTecnicoRespository.save(responsavelTecnico);

        }

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

    @Override
    public ResponsavelTecnico findByPessoaLogada(HttpServletRequest request) {

        br.ufla.lemaf.beans.pessoa.Pessoa pessoaEU = pessoaService.getPessoaLogada(request);

        Pessoa pessoa = pessoaService.transformPessoaEUByPessoa(pessoaEU);

        List<ResponsavelTecnico> responsaveis = findByPessoa(pessoa);

        responsaveis.forEach(responsavelTecnico -> {

            if (!responsavelTecnico.getDocumentos().isEmpty()) {

                responsavelTecnico.getDocumentos().forEach(documentoResponsavelTecnico -> {

                    try {

                        File file = recuperaArquivo(documentoResponsavelTecnico.getHash());

                        if (file != null) {
                            documentoResponsavelTecnico.imagemBase64 = ArquivoUtils.codificaParaBase64(file);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });

            }

        });

        return responsaveis.isEmpty() ? null : responsaveis.get(0);

    }

    private List<ResponsavelTecnico> findByPessoa(Pessoa pessoa) {
        return responsavelTecnicoRespository.findByPessoaOrderById(pessoa);
    }

    @Override
    public ResponsavelTecnico findByID(Integer id) {
        return responsavelTecnicoRespository.findById(id).orElse(null);
    }

    @Override
    public File recuperaArquivo(String hash) {

        DocumentoResponsavelTecnico documentoResponsavelTecnico = documentoResponsavelTecnicoRepository.findByHash(hash);

        return new File(documentoResponsavelTecnico.getCaminho());

    }

    @Override
    public RetornoUploadArquivoDTO salvarAnexo(HttpServletRequest request, MultipartFile multipartFile) throws Exception {

        File file = salvaArquivoDiretorio(multipartFile);

        DocumentoResponsavelTecnico documentoResponsavelTecnico =
                new DocumentoResponsavelTecnico(file, multipartFile.getOriginalFilename(), findByPessoaLogada(request));

        documentoResponsavelTecnicoRepository.save(documentoResponsavelTecnico);

        return new RetornoUploadArquivoDTO(documentoResponsavelTecnico);

    }

    @Override
    public RetornoUploadArquivoDTO removerAnexo(HttpServletRequest request, ResponsavelTecnicoDTO responsavelTecnicoDTO) throws Exception {

        List<ResponsavelTecnico> responsavelTecnicoSalvo =
                responsavelTecnicoRespository.findByPessoaOrderById(responsavelTecnicoDTO.getPessoaFisica());

        responsavelTecnicoSalvo.forEach(responsavelTecnico -> {

            responsavelTecnico.getDocumentos().forEach(documentoResponsavelTecnico -> {

                boolean encontrouDocumentoIgual = responsavelTecnicoDTO.getDocumentos().stream().noneMatch(documentoResponsavelTecnicoDTO ->
                        documentoResponsavelTecnico.getNome().equals(documentoResponsavelTecnicoDTO.getNome()));

                if (!encontrouDocumentoIgual) {

                    try {
                        removeArquivoDiretorio(documentoResponsavelTecnico.getCaminho());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            });

        });

        responsavelTecnicoSalvo.forEach(responsavelTecnico -> {

            responsavelTecnico.getDocumentos().forEach(documentoResponsavelTecnico -> {

                try {
                    documentoResponsavelTecnicoRepository.delete(documentoResponsavelTecnico);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

        });

        return null;

    }

    private File salvaArquivoDiretorio(MultipartFile multipartFile) throws Exception {

//        validaTipoArquivo(multipartFile);

        String pathSalvarArquivo = VariaveisAmbientes.pathSalvarArquivos() +
                File.separator + DIR_ARQUIVOS_RESPONSABILIDADE_TECNICA +
                File.separator + LocalDate.now().format(FORMATO_DATA_MES_ANO) +
                File.separator + UUID.randomUUID() + "." + multipartFile.getContentType().split("/")[1];

        return ArquivoUtils.salvaArquivoDiretorio(multipartFile, pathSalvarArquivo);

    }

    private boolean removeArquivoDiretorio(String pathDeletarArquivo) throws Exception {

        return ArquivoUtils.removeArquivoDiretorio(pathDeletarArquivo);

    }

    private StatusCadastroResponsavelTecnico getStatus(ResponsavelTecnicoDTO responsavelTecnicoDTO) {

        StatusCadastroResponsavelTecnico status;

        if (responsavelTecnicoDTO.getJustificativa() != null) {
            status = statusCadastroResponsavelTecnicoRepository.findByCodigo(StatusSolicitacao.REPROVADO.getCodigo());
        } else if (responsavelTecnicoDTO.getId() == null) {
            status = statusCadastroResponsavelTecnicoRepository.findByCodigo(StatusSolicitacao.AGUARDANDO_ANALISE.getCodigo());
        } else {
            status = statusCadastroResponsavelTecnicoRepository.findByCodigo(StatusSolicitacao.APROVADO.getCodigo());
        }

        return status;

    }

//    private void validaTipoArquivo(MultipartFile multipartFile) throws Exception {
//
//        if(!multipartFile.getOriginalFilename().endsWith(EXTENSAO_ARQUIVO_SINCRONIA)) {
//            throw new Exception("A extensão do arquivo informado deve ser " + EXTENSAO_ARQUIVO_SINCRONIA);
//        }
//
//    }


}