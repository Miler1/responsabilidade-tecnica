package com.gestaoresponsabilidadetecnica.especializacaoTecnica.services;

import com.gestaoresponsabilidadetecnica.especializacaoTecnica.interfaces.IEspecializacaoTecnicaService;
import com.gestaoresponsabilidadetecnica.especializacaoTecnica.models.EspecializacaoTecnica;
import com.gestaoresponsabilidadetecnica.especializacaoTecnica.repositories.EspecializacaoTecnicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecializacaoTecnicaService implements IEspecializacaoTecnicaService {

    @Autowired
    EspecializacaoTecnicaRepository especializacaoTecnicaRepository;

    @Override
    public List<EspecializacaoTecnica> findEspecializacoesTecnicas() {
        return especializacaoTecnicaRepository.findAll();
    }

}