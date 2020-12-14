CREATE TABLE cadastro_unificado.status_cadastro_responsavel_tecnico (
    id INTEGER NOT NULL,
    codigo CHARACTER VARYING(50),
    nome CHARACTER VARYING(200),
    CONSTRAINT pk_status_cadastro_responsavel_tecnico PRIMARY KEY (id),
    CONSTRAINT un_status_cadastro_responsavel_tecnico_codigo UNIQUE (codigo)
);

COMMENT ON TABLE cadastro_unificado.status_cadastro_responsavel_tecnico IS 'Entidade responsável por armazenar os dados referentes aos status do cadastro de responsáveis técnicos.';
COMMENT ON COLUMN cadastro_unificado.status_cadastro_responsavel_tecnico.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN cadastro_unificado.status_cadastro_responsavel_tecnico.codigo IS 'Codigo único referente ao status do cadastro de responsáveis técnicos.';
COMMENT ON COLUMN cadastro_unificado.status_cadastro_responsavel_tecnico.nome IS 'Nome do status do cadastro de responsáveis técnicos.';

ALTER TABLE cadastro_unificado.status_cadastro_responsavel_tecnico OWNER TO postgres;
GRANT ALL PRIVILEGES ON TABLE cadastro_unificado.status_cadastro_responsavel_tecnico TO responsabilidade_tecnica_ap;
GRANT SELECT, INSERT, UPDATE ON TABLE cadastro_unificado.status_cadastro_responsavel_tecnico TO cadastro_unificado;

insert into cadastro_unificado.status_cadastro_responsavel_tecnico(id, codigo, nome)
VALUES (1, 'AGUARDANDO_ANALISE', 'Aguardando análise'),
(2, 'APROVADO', 'Aprovado'),
(3, 'REPROVADO', 'Reprovado'),
(4, 'VENCIDO', 'Vencido');

CREATE SEQUENCE cadastro_unificado.responsavel_tecnico_id_seq
    NO MINVALUE
	NO MAXVALUE
	START WITH 1
	INCREMENT BY 1
	CACHE 1;

CREATE TABLE cadastro_unificado.responsavel_tecnico (
    id INTEGER NOT NULL,
    formacao CHARACTER VARYING(500) NOT NULL,
    conselho_de_classe CHARACTER VARYING(500) NOT NULL,
    registro CHARACTER VARYING(500) NOT NULL,
    nivel_responsabilidade_tecnica CHARACTER VARYING(500) NOT NULL,
    possui_vinculo_com_gea BOOLEAN NOT NULL,
    vinculo_empregaticio CHARACTER VARYING(500),
    outro_vinculo_empregaticio CHARACTER VARYING(500),
    id_especializacao INTEGER NOT NULL,
    id_pessoa INTEGER NOT NULL,
    validade DATE,
    id_status INTEGER,
    CONSTRAINT pk_responsavel_tecnico PRIMARY KEY (id),
	CONSTRAINT fk_rt_especializacao_tecnica FOREIGN KEY (id_especializacao) REFERENCES cadastro_unificado.especializacao_tecnica (id),
	CONSTRAINT fk_rt_pessoa FOREIGN KEY (id_pessoa) REFERENCES cadastro_unificado.pessoa (id),
	CONSTRAINT fk_rt_status FOREIGN KEY (id_status) REFERENCES cadastro_unificado.status_cadastro_responsavel_tecnico (id)
);

ALTER TABLE cadastro_unificado.responsavel_tecnico ALTER COLUMN id SET DEFAULT nextval('cadastro_unificado.responsavel_tecnico_id_seq'::regclass);

COMMENT ON TABLE cadastro_unificado.responsavel_tecnico IS 'Entidade responsável por armazenar os dados referentes ao responsável técnico.';
COMMENT ON COLUMN cadastro_unificado.responsavel_tecnico.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN cadastro_unificado.responsavel_tecnico.formacao IS 'Formação do responsável técnico.';
COMMENT ON COLUMN cadastro_unificado.responsavel_tecnico.conselho_de_classe IS 'Conselho de Classe do responsável técnico.';
COMMENT ON COLUMN cadastro_unificado.responsavel_tecnico.registro IS 'registro do responsável técnico.';
COMMENT ON COLUMN cadastro_unificado.responsavel_tecnico.nivel_responsabilidade_tecnica IS 'nível de responsabilidade técnica que o responsável técnico possuí.';
COMMENT ON COLUMN cadastro_unificado.responsavel_tecnico.possui_vinculo_com_gea IS 'flag que dis se o responsável técnico possuí vinculo com o GEA.';
COMMENT ON COLUMN cadastro_unificado.responsavel_tecnico.vinculo_empregaticio IS 'Vínculo empregaticio do responsável técnico.';
COMMENT ON COLUMN cadastro_unificado.responsavel_tecnico.outro_vinculo_empregaticio IS 'Nome do outro tipo de vinculo empregaticio que o responsável técnico possui.';
COMMENT ON COLUMN cadastro_unificado.responsavel_tecnico.id_especializacao IS 'Identificador da entidade cadastro_unificado.especializacao_tecnica que realizará o relacionamento entre as entidadades cadastro_unificado.especializacao_tecnica e cadastro_unificado.responsavel_tecnico.';
COMMENT ON COLUMN cadastro_unificado.responsavel_tecnico.id_pessoa IS 'Identificador da entidade cadastro_unificado.pessoa que realizará o relacionamento entre as entidades cadastro_unificado.pessoa e cadastro_unificado.responsavel_tecnico.';
COMMENT ON COLUMN cadastro_unificado.responsavel_tecnico.validade IS 'Validade do cadastro do responsável técnico.';
COMMENT ON COLUMN cadastro_unificado.responsavel_tecnico.id_status IS 'Identificador da entidade cadastro_unificado.status_cadastro_responsavel_tecnico que realizará o relacionamento entre as entidades cadastro_unificado.status_cadastro_responsavel_tecnico e cadastro_unificado.responsavel_tecnico.';


GRANT ALL PRIVILEGES ON TABLE cadastro_unificado.responsavel_tecnico TO responsabilidade_tecnica_ap;
GRANT SELECT, INSERT, UPDATE ON TABLE cadastro_unificado.responsavel_tecnico TO cadastro_unificado;
GRANT SELECT, USAGE ON SEQUENCE cadastro_unificado.responsavel_tecnico_id_seq TO responsabilidade_tecnica_ap;
ALTER TABLE cadastro_unificado.responsavel_tecnico OWNER TO postgres;