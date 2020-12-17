package com.gestaoresponsabilidadetecnica.responsavelTecnico.jobs;

import com.gestaoresponsabilidadetecnica.configuracao.components.VariaveisAmbientes;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.ResponsavelTecnico;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.StatusCadastroResponsavelTecnico;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.services.ResponsavelTecnicoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@EnableScheduling
public class VerificacaoValidade {

    private static final String LOG_PREFIX = "[VALIDADE RESPONSAVEL TECNICO] : ";

    @Autowired
    ResponsavelTecnicoService responsavelTecnicoService;

    @Scheduled(cron = "${job.validade.cron}")
    public void verificarValidadeResponsabilidades() {

        boolean jobAtivado = VariaveisAmbientes.jobVerificacaoEnabled();

        if (jobAtivado) {

            log.info(LOG_PREFIX + "Iniciando Job");

            List<ResponsavelTecnico> responsaveisTecnicosExpirados = responsavelTecnicoService.buscarNovosExpirados();

            if (responsaveisTecnicosExpirados.isEmpty()) {
                log.info(LOG_PREFIX + "Sem novos registros vencidos.");
            } else {

                StatusCadastroResponsavelTecnico statusVencido = responsavelTecnicoService.buscarStatusPorCodigo("VENCIDO");

                responsaveisTecnicosExpirados.forEach(responsavelTecnico -> {

                    log.info(LOG_PREFIX + "Expirando cadastro de ID " + responsavelTecnico.getId());
                    responsavelTecnicoService.mudarStatusResponsavelTecnico(responsavelTecnico, statusVencido);
                    log.info(LOG_PREFIX + "Cadastro de ID " + responsavelTecnico.getId() + " expirado com sucesso.");

                });

            }

            log.info(LOG_PREFIX + "Encerrando Job");

        }

    }

}