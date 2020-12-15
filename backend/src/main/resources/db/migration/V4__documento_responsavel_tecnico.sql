CREATE SEQUENCE cadastro_unificado.documento_responsavel_tecnico_id_seq
    NO MINVALUE
    NO MAXVALUE
    START WITH 1
    INCREMENT BY 1
    CACHE 1;

CREATE TABLE cadastro_unificado.documento_responsavel_tecnico (
    id INTEGER NOT NULL,
    caminho CHARACTER VARYING(300) NOT NULL,
    hash CHARACTER VARYING(100) NOT NULL,
    nome CHARACTER VARYING(200) NOT NULL,
    id_responsavel_tecnico INTEGER NOT NULL,
    CONSTRAINT pk_documento_responsavel_tecnico PRIMARY KEY (id),
    CONSTRAINT fk_drt_responsavel_tecnico FOREIGN KEY (id_responsavel_tecnico) REFERENCES cadastro_unificado.responsavel_tecnico (id)
);

ALTER TABLE cadastro_unificado.documento_responsavel_tecnico ALTER COLUMN id SET DEFAULT nextval('cadastro_unificado.documento_responsavel_tecnico_id_seq'::regclass);

COMMENT ON TABLE cadastro_unificado.documento_responsavel_tecnico IS 'Entidade responsável por armazenar os documentos referentes ao responsável técnico.';
COMMENT ON COLUMN cadastro_unificado.documento_responsavel_tecnico.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN cadastro_unificado.documento_responsavel_tecnico.caminho IS 'caminho onde se encontra o documento.';
COMMENT ON COLUMN cadastro_unificado.documento_responsavel_tecnico.hash IS 'Hash de validação do documento.';
COMMENT ON COLUMN cadastro_unificado.documento_responsavel_tecnico.nome IS 'nome do documento no diretório.';
COMMENT ON COLUMN cadastro_unificado.documento_responsavel_tecnico.id_responsavel_tecnico IS 'identificador que referencia o responsavel tecnico no documento.';

ALTER TABLE cadastro_unificado.documento_responsavel_tecnico OWNER TO postgres;
GRANT ALL PRIVILEGES ON TABLE cadastro_unificado.documento_responsavel_tecnico TO responsabilidade_tecnica_ap;
GRANT SELECT, INSERT, UPDATE ON TABLE cadastro_unificado.documento_responsavel_tecnico TO cadastro_unificado;
GRANT SELECT, USAGE ON SEQUENCE cadastro_unificado.documento_responsavel_tecnico_id_seq TO responsabilidade_tecnica_ap;
