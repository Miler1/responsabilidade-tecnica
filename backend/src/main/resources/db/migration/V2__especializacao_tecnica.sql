CREATE TABLE cadastro_unificado.especializacao_tecnica (
    id INTEGER NOT NULL,
    codigo CHARACTER VARYING(9),
    nome CHARACTER VARYING(200),
    CONSTRAINT pk_especializacao_tecnica PRIMARY KEY (id),
    CONSTRAINT un_pessoa_fisica_cpf UNIQUE (codigo)
);

COMMENT ON TABLE cadastro_unificado.especializacao_tecnica IS 'Entidade responsável por armazenar os dados referentes às especializações técnicas dos responsáveis técnicos.';
COMMENT ON COLUMN cadastro_unificado.especializacao_tecnica.id IS 'Identificador único da entidade.';
COMMENT ON COLUMN cadastro_unificado.especializacao_tecnica.codigo IS 'Codigo único referente a especialização técnica.';
COMMENT ON COLUMN cadastro_unificado.especializacao_tecnica.nome IS 'Nome da especialização técnica.';

ALTER TABLE cadastro_unificado.especializacao_tecnica OWNER TO postgres;
GRANT ALL ON TABLE cadastro_unificado.especializacao_tecnica TO postgres;
GRANT ALL ON TABLE cadastro_unificado.especializacao_tecnica TO responsabilidade_tecnica_ap;
GRANT SELECT, INSERT, UPDATE ON TABLE cadastro_unificado.especializacao_tecnica TO cadastro_unificado;

insert into cadastro_unificado.especializacao_tecnica(id, codigo, nome)
VALUES (1, '111-01-00', 'Engenheiro Ambiental'),
(2, '111-02-00', 'Engenheiro Civil'),
(3, '111-03-00', 'Engenheiro de Fortificação e Construção'),
(4, '111-04-01', 'Engenheiro de Operação - Construção Civil'),
(5, '111-04-02', 'Engenheiro de Operação - Construção de Estradas'),
(6, '111-04-03', 'Engenheiro de Operação - Edificações'),
(7, '111-04-04', 'Engenheiro de Operação - Estradas'),
(8, '111-05-01', 'Engenheiro Industrial - Civil'),
(9, '111-06-00', 'Engenheiro Militar'),
(10, '111-08-00', 'Engenheiro Sanitarista'),
(11, '111-09-00', 'Engenheiro Sanitarista e Ambiental'),
(12, '111-10-00', 'Engenheiro de Infra-Estrutura Aeronáutica'),
(13, '111-11-01', 'Engenheiro de Produção - Civil'),
(14, '111-12-00', 'Engenheiro Hídrico'),
(15, '111-13-00', 'Urbanista'),
(16, '111-14-00', 'Engenheiro de Transportes'),
(17, '112-01-00', 'Tecnólogo em Construção Civil'),
(18, '112-01-01', 'Tecnólogo em Construção Civil - Edificações'),
(19, '112-01-02', 'Tecnólogo em Construção Civil - Estrada e Topografia'),
(20, '112-01-03', 'Tecnólogo em Construção Civil - Movimento de Terra e Pavimentação'),
(21, '112-01-04', 'Tecnólogo em Construção Civil - Obras de Solos'),
(22, '112-01-05', 'Tecnólogo em Construção Civil - Obras Hidráulicas'),
(23, '112-01-06', 'Tecnólogo em Construção Civil - Terraplenagem'),
(24, '112-02-00', 'Tecnólogo em Edificações'),
(25, '112-03-00', 'Tecnólogo em Estradas'),
(26, '112-04-00', 'Tecnólogo em Operação e Administração de Sistemas de Navegação Fluvial'),
(27, '112-05-00', 'Tecnólogo em Saneamento'),
(28, '112-06-00', 'Tecnólogo em Saneamento Ambiental'),
(29, '112-07-00', 'Tecnólogo em Saneamento Básico'),
(30, '112-08-00', 'Tecnólogo em Controle de Obras'),
(31, '112-09-01', 'Tecnólogo em Transporte Terrestre - Urbano'),
(32, '112-10-00', 'Tecnólogo em Processos Ambientais'),
(33, '112-11-00', 'Tecnólogo em Gestão Ambiental'),
(34, '121-01-00', 'Engenheiro de Computação'),
(35, '121-02-00', 'Engenheiro de Comunicações'),
(36, '121-03-00', 'Engenheiro de Controle e Automação'),
(37, '121-04-01', 'Engenheiro de Operação - Eletrônica'),
(38, '121-04-02', 'Engenheiro de Operação - Eletrotécnica'),
(39, '121-04-03', 'Engenheiro de Operação - Telecomunicações'),
(40, '121-05-01', 'Engenheiro de Produção - Eletricista'),
(41, '121-06-00', 'Engenheiro de Telecomunicações'),
(42, '121-07-00', 'Engenheiro de Transmissão'),
(43, '121-08-00', 'Engenheiro Eletricista'),
(44, '121-08-01', 'Engenheiro Eletricista - Eletrônica'),
(45, '121-08-02', 'Engenheiro Eletricista - Eletrotécnica'),
(46, '121-09-00', 'Engenheiro em Eletrônica'),
(47, '121-10-00', 'Engenheiro em Eletrotécnica'),
(48, '121-11-01', 'Engenheiro Industrial - Elétrica'),
(49, '121-11-02', 'Engenheiro Industrial - Eletrônica'),
(50, '121-11-03', 'Engenheiro Industrial - Eletrotécnica'),
(51, '121-11-04', 'Engenheiro Industrial - Telecomunicações'),
(52, '121-12-00', 'Engenheiro Biomédico'),
(53, '121-13-00', 'Engenheiro de Energia'),
(54, '121-14-00', 'Engenheiro de Software'),
(55, '122-01-00', 'Tecnólogo em Automação Industrial'),
(56, '122-02-00', 'Tecnólogo em Distribuição de Energia Elétrica'),
(57, '122-03-00', 'Tecnólogo em Eletricidade'),
(58, '122-04-00', 'Tecnólogo em Eletrônica'),
(59, '122-05-00', 'Tecnólogo em Eletrônica Industrial'),
(60, '122-07-00', 'Tecnólogo em Instrumentação e Controle'),
(61, '122-08-00', 'Tecnólogo em Máquinas Elétricas'),
(62, '122-09-00', 'Tecnólogo em Sistemas Elétricos'),
(63, '122-10-00', 'Tecnólogo em Técnicas Digitais'),
(64, '122-11-00', 'Tecnólogo em Telecomunicações'),
(65, '122-11-01', 'Tecnólogo em Telecomunicações - Telefonia e Redes Externas'),
(66, '122-12-00', 'Tecnólogo em Sistemas de Telefonia'),
(67, '122-13-00', 'Tecnólogo em Transmissão e Distribuição Elétrica'),
(68, '122-14-00', 'Tecnólogo em Redes de Computadores'),
(69, '122-15-00', 'Tecnólogo em Sistemas de Comunicação sem Fio'),
(70, '122-16-00', 'Tecnólogo em Eletrotécnica Industrial'),
(71, '131-01-00', 'Engenheiro Aeronáutico'),
(72, '131-02-00', 'Engenheiro Mecânico e de Armamento'),
(73, '131-05-01', 'Engenheiro de Operação - Aeronáutica'),
(74, '131-05-02', 'Engenheiro de Operação - Fabricação Mecânica'),
(75, '131-05-03', 'Engenheiro de Operação - Indústria da Madeira'),
(76, '131-05-04', 'Engenheiro de Operação - Máquinas e Motores'),
(77, '131-05-05', 'Engenheiro de Operação - Mecânica'),
(78, '131-05-06', 'Engenheiro de Operação - Mecânica Automobilística'),
(79, '131-05-07', 'Engenheiro de Operação - Mecânica de Manutenção'),
(80, '131-05-08', 'Engenheiro de Operação - Mecânica de Máquinas e Ferramentas'),
(81, '131-05-09', 'Engenheiro de Operação - Metalurgista'),
(82, '131-05-10', 'Engenheiro de Operação - Processo de Fabricação Mecânica'),
(83, '131-05-11', 'Engenheiro de Operação - Produção'),
(84, '131-05-12', 'Engenheiro de Operação - Refrigeração e Ar Condicionado'),
(85, '131-05-13', 'Engenheiro de Operação - Siderurgia'),
(86, '131-06-00', 'Engenheiro de Produção'),
(87, '131-06-01', 'Engenheiro de Produção - Mecânica'),
(88, '131-06-02', 'Engenheiro de Produção - Metalurgista'),
(89, '131-06-03', 'Engenheiro de Produção - Agroindústria'),
(90, '131-07-01', 'Engenheiro Industrial - Madeira'),
(91, '131-07-02', 'Engenheiro Industrial - Mecânica'),
(92, '131-07-03', 'Engenheiro Industrial - Metalurgia'),
(93, '131-08-00', 'Engenheiro Mecânico'),
(94, '131-08-01', 'Engenheiro Mecânico - Automação e Sistemas'),
(95, '131-09-00', 'Engenheiro Metalurgista'),
(96, '131-10-00', 'Engenheiro Naval Engenheira Naval Eng. Naval'),
(97, '131-11-00', 'Engenheiro Mecânico Eletricista'),
(98, '131-12-00', 'Engenheiro Acústico'),
(99, '131-13-00', 'Engenheiro Automotivo'),
(100, '131-14-00', 'Engenheiro Aeroespacial'),
(101, '132-01-00', 'Tecnólogo em Aeronaves'),
(102, '132-02-00', 'Tecnólogo em Construção Naval'),
(103, '132-03-00', 'Tecnólogo em Eletromecânica'),
(104, '132-04-00', 'Tecnólogo em Indústria da Madeira'),
(105, '132-05-00', 'Tecnólogo em Manutenção de Máquinas e Equipamentos'),
(106, '132-06-00', 'Tecnólogo em Máquinas'),
(107, '132-07-00', 'Tecnólogo em Máquinas e Equipamentos'),
(108, '132-08-00', 'Tecnólogo em Mecânica'),
(109, '132-08-01', 'Tecnólogo em Mecânica - Automobilismo'),
(110, '132-08-02', 'Tecnólogo em Mecânica - Desenhista Projetista'),
(111, '132-08-03', 'Tecnólogo em Mecânica - Oficinas'),
(112, '132-08-04', 'Tecnólogo em Mecânica - Produção Industrial de Móveis'),
(113, '132-08-05', 'Tecnólogo em Mecânica - Soldagem'),
(114, '132-08-06', 'Tecnólogo em Mecânica - Processos Industriais'),
(115, '132-09-00', 'Tecnólogo em Mecânica, Oficina e Manutenção'),
(116, '132-10-00', 'Tecnólogo em Metalurgia'),
(117, '132-11-00', 'Tecnólogo em Processo de Produção e Usinagem'),
(118, '132-12-00', 'Tecnólogo em Produção de Calçados'),
(119, '132-13-00', 'Tecnólogo em Produção de Couro'),
(120, '132-14-00', 'Tecnólogo em Siderúrgica'),
(121, '132-15-00', 'Tecnólogo em Soldagem'),
(122, '132-16-00', 'Tecnólogo Naval'),
(123, '132-17-00', 'Tecnólogo em Qualidade Total'),
(124, '132-18-00', 'Tecnólogo em Mecatrônica Industrial'),
(125, '132-19-00', 'Tecnólogo em Gestão da Produção Industrial'),
(126, '132-20-00', 'Tecnólogo em Fabricação Mecânica'),
(127, '141-01-00', 'Engenheiro de Alimentos'),
(128, '141-02-00', 'Engenheiro de Materiais'),
(129, '141-03-01', 'Engenheiro de Operação - Petroquímica'),
(130, '141-03-02', 'Engenheiro de Operação - Química'),
(131, '141-03-03', 'Engenheiro de Operação - Têxtil'),
(132, '141-04-01', 'Engenheiro de Produção - Materiais'),
(133, '141-04-02', 'Engenheiro de Produção - Química'),
(134, '141-04-03', 'Engenheiro de Produção - Têxtil'),
(135, '141-05-01', 'Engenheiro Industrial - Química'),
(136, '141-06-00', 'Engenheiro Químico'),
(137, '141-07-00', 'Engenheiro Têxtil'),
(138, '141-08-00', 'Engenheiro de Petróleo'),
(139, '141-09-00', 'Engenheiro de Plástico'),
(140, '141-10-00', 'Engenheiro Bioquímico'),
(141, '141-11-00', 'Engenheiro Nuclear'),
(142, '141-12-00', 'Engenheiro de Bioprocessos e Biotecnologia'),
(143, '142-01-00', 'Tecnólogo em Alimentos'),
(144, '142-02-00', 'Tecnólogo em Cerâmica'),
(145, '142-03-00', 'Tecnólogo em Indústria Têxtil'),
(146, '142-04-00', 'Tecnólogo em Materiais'),
(147, '142-05-00', 'Tecnólogo em Processos Petroquímicos'),
(148, '142-06-00', 'Tecnólogo em Química'),
(149, '142-07-00', 'Tecnólogo Têxtil'),
(150, '142-08-00', 'Tecnólogo em Petróleo e Gás'),
(151, '142-09-00', 'Tecnólogo em Polímeros'),
(152, '142-10-00', 'Tecnólogo em Produção de Vestuário'),
(153, '151-01-00', 'Engenheiro de Minas'),
(154, '151-02-00', 'Engenheiro Geólogo'),
(155, '151-03-00', 'Geólogo'),
(156, '151-04-00', 'Engenheiro de Exploração e Produção de Petróleo'),
(157, '152-01-00', 'Tecnólogo de Minas'),
(158, '152-02-00', 'Tecnólogo em Manutenção Petroquímica'),
(159, '152-03-00', 'Tecnólogo em Rochas Ornamentais'),
(160, '161-01-00', 'Agrimensor Agrimensora Agrim.'),
(161, '161-02-00', 'Engenheiro Agrimensor'),
(162, '161-03-00', 'Engenheiro Cartógrafo'),
(163, '161-05-00', 'Engenheiro de Geodésia'),
(164, '161-06-00', 'Engenheiro em Topografia Rural'),
(165, '161-07-00', 'Engenheiro Geógrafo'),
(166, '161-08-00', 'Engenheiro Topógrafo'),
(167, '161-09-00', 'Geógrafo'),
(168, '161-10-00', 'Engenheiro Agrimensor e Cartógrafo'),
(169, '162-01-00', 'Tecnólogo em Topografia'),
(170, '162-02-00', 'Tecnólogo em Geoprocessamento'),
(171, '162-03-00', 'Tecnólogo em Agrimensura'),
(172, '311-01-00', 'Engenheiro Agrícola'),
(173, '311-02-00', 'Engenheiro Agrônomo'),
(174, '311-03-00', 'Engenheiro de Pesca'),
(175, '311-04-00', 'Engenheiro Florestal'),
(176, '311-05-00', 'Meteorologista'),
(177, '311-07-00', 'Engenheiro de Aqüicultura'),
(178, '312-01-00', 'Tecnólogo em Açúcar e Álcool'),
(179, '312-02-00', 'Tecnólogo em Administração Rural'),
(180, '312-03-00', 'Tecnólogo em Agricultura'),
(181, '312-04-00', 'Tecnólogo em Agronomia'),
(182, '312-05-00', 'Tecnólogo em Agropecuária'),
(183, '312-06-00', 'Tecnólogo em Aqüicultura'),
(184, '312-07-00', 'Tecnólogo em Bovinocultura'),
(185, '312-08-00', 'Tecnólogo em Ciências Agrárias'),
(186, '312-09-00', 'Tecnólogo em Cooperativismo'),
(187, '312-10-00', 'Tecnólogo em Curtumes e Tanantes'),
(188, '312-11-00', 'Tecnólogo em Fitotecnia'),
(189, '312-12-00', 'Tecnólogo em Fruticultura'),
(190, '312-12-01', 'Tecnólogo em Fruticultura de Clima Temperado'),
(191, '312-13-00', 'Tecnólogo em Heveicultura'),
(192, '312-14-00', 'Tecnólogo em Laticínios Tecnóloga em Laticínios Tecg. Latic.'),
(193, '312-15-00', 'Tecnólogo em Mecanização Agrícola'),
(194, '312-16-00', 'Tecnólogo em Meteorologia'),
(195, '312-17-00', 'Tecnólogo em Pecuária'),
(196, '312-18-00', 'Tecnólogo Industrial de Açúcar de Cana'),
(197, '312-19-00', 'Tecnólogo em Recursos Hídricos e Irrigação'),
(198, '312-20-00', 'Tecnólogo em Horticultura'),
(199, '312-21-00', 'Tecnólogo em Irrigação e Drenagem'),
(200, '312-22-00', 'Tecnólogo em Agroindústria'),
(201, '312-23-00', 'Tecnólogo em Agroecologia'),
(202, '312-24-00', 'Tecnólogo em Viticultura e Enologia'),
(203, '312-25-00', 'Tecnólogo em Cafeicultura'),
(204, '312-26-00', 'Tecnológo em Silvicultura'),
(205, '312-27-00', 'Tecnólogo em Paisagismo e Jardinagem'),
(206, '312-28-00', 'Tecnólogo em Produção de Grãos'),
(207, '312-29-00', 'Tecnólogo em Agronegócios'),
(208, '421-02-00', 'Engenheiro de Saúde e Segurança'),
(209, '422-01-00', 'Tecnólogo de Segurança do Trabalho'),
(210, '423-01-00', 'Técnico de Segurança do Trabalho'),
(211, '424-01-00', 'Engenheiro de Segurança do Trabalho');