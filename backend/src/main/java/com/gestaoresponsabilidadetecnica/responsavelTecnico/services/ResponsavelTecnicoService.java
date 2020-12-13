package com.gestaoresponsabilidadetecnica.responsavelTecnico.services;

import com.gestaoresponsabilidadetecnica.configuracao.utils.DateUtil;
import com.gestaoresponsabilidadetecnica.especializacaoTecnica.models.EspecializacaoTecnica;
import com.gestaoresponsabilidadetecnica.especializacaoTecnica.repositories.EspecializacaoTecnicaRepository;
import com.gestaoresponsabilidadetecnica.pessoa.interfaces.IPessoaService;
import com.gestaoresponsabilidadetecnica.pessoa.models.Pessoa;
import com.gestaoresponsabilidadetecnica.pessoa.repositories.PessoaRepository;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos.ResponsavelTecnicoDTO;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.interfaces.IResponsavelTecnicoService;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.ResponsavelTecnico;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.StatusCadastroResponsavelTecnico;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.repositories.ResponsavelTecnicoRespository;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.repositories.StatusCadastroResponsavelTecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class ResponsavelTecnicoService implements IResponsavelTecnicoService {

    @Autowired
    EspecializacaoTecnicaRepository especializacaoTecnicaRepository;

    @Autowired
    StatusCadastroResponsavelTecnicoRepository statusCadastroResponsavelTecnicoRepository;

    @Autowired
    ResponsavelTecnicoRespository responsavelTecnicoRespository;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    IPessoaService pessoaService;

    @Override
    public ResponsavelTecnico salvar(HttpServletRequest request, ResponsavelTecnicoDTO responsavelTecnicoDTO) {

        EspecializacaoTecnica especializacaoTecnica = especializacaoTecnicaRepository.findById(responsavelTecnicoDTO.getEspecializacao().getId()).orElse(null);

        br.ufla.lemaf.beans.pessoa.Pessoa pessoaEU = pessoaService.getPessoaLogada(request);

        Pessoa pessoa = pessoaRepository.findById(pessoaEU.id).orElse(null);

        StatusCadastroResponsavelTecnico status = statusCadastroResponsavelTecnicoRepository.findByCodigo("AGUARDANDO_ANALISE");

        Date validade = DateUtil.acrescentarUmAno(new Date());

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
                .setValidade(validade)
                .setVinculoEmpregaticio(responsavelTecnicoDTO.getVinculoEmpregaticio())
                .build();

        responsavelTecnicoRespository.save(responsavelTecnico);

        return responsavelTecnico;

    }

}
