CREATE ROLE responsabilidade_tecnica_ap LOGIN
  ENCRYPTED PASSWORD 'responsabilidade_tecnica_ap'
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;

GRANT USAGE ON SCHEMA public TO responsabilidade_tecnica_ap;
GRANT USAGE ON SCHEMA cadastro_unificado TO responsabilidade_tecnica_ap;

ALTER DEFAULT PRIVILEGES FOR USER responsabilidade_tecnica_ap IN SCHEMA cadastro_unificado
    GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO responsabilidade_tecnica_ap;

ALTER DEFAULT PRIVILEGES FOR USER responsabilidade_tecnica_ap IN SCHEMA cadastro_unificado
    GRANT SELECT, USAGE ON SEQUENCES TO responsabilidade_tecnica_ap;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA cadastro_unificado TO responsabilidade_tecnica_ap;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA cadastro_unificado TO responsabilidade_tecnica_ap;