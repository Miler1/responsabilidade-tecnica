package com.gestaoresponsabilidadetecnica.configuracao.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@EnableAutoConfiguration
public final class VariaveisAmbientes {

	private static Environment environment;

	@Autowired
	private VariaveisAmbientes(final Environment env) {
		setEnvironment(env);
	}

	public static void setEnvironment(Environment environment) {
		com.gestaoresponsabilidadetecnica.configuracao.components.VariaveisAmbientes.environment = environment;
	}

	public static String baseUrl() {
		return environment.getProperty("server.servlet.context-baseUrl") + environment.getProperty("server.servlet.context-path");
	}

	public static String baseUrlFrontend() {
		return environment.getProperty("server.dev.frontend.baseUrl");
	}

	/** ENTRADA UNICA*/
	public static String entradaUnicaClientId() {
		return environment.getProperty("entrada.unica.client.id");
	}

	public static String entradaUnicaClientSecret() {
		return environment.getProperty("entrada.unica.client.secret");
	}

	public static String entradaUnicaPortalSeguracao() {
		return environment.getProperty("entrada.unica.portal.seguranca");
	}

	public static String entradaUnicaCadastroUnificado() {
		return environment.getProperty("entrada.unica.cadastro.unificado");
	}

}
