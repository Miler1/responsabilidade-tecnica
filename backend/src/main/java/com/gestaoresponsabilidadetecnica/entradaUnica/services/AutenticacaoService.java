package com.gestaoresponsabilidadetecnica.entradaUnica.services;

import br.ufla.lemaf.beans.pessoa.Usuario;
import com.gestaoresponsabilidadetecnica.entradaUnica.dtos.AutenticacaoDTO;
import com.gestaoresponsabilidadetecnica.entradaUnica.interfaces.IAutenticacaoService;
import com.gestaoresponsabilidadetecnica.seguranca.components.Autenticacao;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AutenticacaoService implements IAutenticacaoService {

	@Override
	public Authentication entradaUnica(HttpServletRequest request, String sessionKey) {

		var usuarioEntradaUnica = EntradaUnicaWS.ws.searchBySessionKey(sessionKey);

		if(usuarioEntradaUnica == null) {
			//throw new ConfiguradorNotFoundException("A chave de sessão do Entrada Única não é valida.");
			throw new RuntimeException("A chave de sessão do Entrada Única não é valida.");
		}

		usuarioEntradaUnica.sessionKeyEntradaUnica = sessionKey;

		request.getSession().setAttribute("sessionKeyEntradaUnica", usuarioEntradaUnica.sessionKeyEntradaUnica);
		request.getSession().setAttribute("login", usuarioEntradaUnica.login);

		return new Autenticacao(usuarioEntradaUnica);

	}

	@Override
	public Authentication login(HttpServletRequest request, AutenticacaoDTO autenticacao) {

		Usuario usuarioEntradaUnica;

		try {
			usuarioEntradaUnica = EntradaUnicaWS.ws.login(autenticacao.getLogin(), autenticacao.getPassword());
		} catch (Exception e){
			//throw new ConfiguradorNotFoundException(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

		if(usuarioEntradaUnica == null) {
			//throw new ConfiguradorNotFoundException("Usuário não encontrado.");
			throw new RuntimeException("Usuário não encontrado.");
		}

		//usuarioEntradaUnica.id = usuario.getId();

		//Como existe só um perfil pro módulo, vamos setar ele aqui.
		usuarioEntradaUnica.perfilSelecionado = usuarioEntradaUnica.perfis.get(0);

		request.getSession().setAttribute("sessionKeyEntradaUnica", usuarioEntradaUnica.sessionKeyEntradaUnica);
		request.getSession().setAttribute("login", usuarioEntradaUnica.login);

		return new Autenticacao(usuarioEntradaUnica);

	}

	@Override
	public Authentication authenticate(Authentication authentication) {
		return null;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return false;
	}
}
