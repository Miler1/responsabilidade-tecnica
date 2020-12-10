package com.gestaoresponsabilidadetecnica.especializacaoTecnica.interfaces;

import com.gestaoresponsabilidadetecnica.especializacaoTecnica.models.EspecializacaoTecnica;

import java.util.List;

public interface IEspecializacaoTecnicaService {
    List<EspecializacaoTecnica> findEspecializacoesTecnicas();
}
