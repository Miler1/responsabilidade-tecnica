ALTER TABLE cadastro_unificado.responsavel_tecnico ADD COLUMN justificativa text;
COMMENT ON COLUMN cadastro_unificado.responsavel_tecnico.justificativa IS 'Armazena a justificativa do administrador para reprovar solicitação do resposável técnico.';
