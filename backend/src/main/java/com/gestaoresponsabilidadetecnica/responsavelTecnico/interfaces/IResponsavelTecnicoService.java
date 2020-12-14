package com.gestaoresponsabilidadetecnica.responsavelTecnico.interfaces;

import com.gestaoresponsabilidadetecnica.responsavelTecnico.dtos.ResponsavelTecnicoDTO;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.ResponsavelTecnico;

import javax.servlet.http.HttpServletRequest;

public interface IResponsavelTecnicoService {

    ResponsavelTecnico salvar(HttpServletRequest request, ResponsavelTecnicoDTO responsavelTecnicoDTO);

}
