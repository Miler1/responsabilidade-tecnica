DELETE FROM portal_seguranca.perfil_usuario pu
WHERE pu.id_perfil = (SELECT id FROM portal_seguranca.perfil p
	WHERE codigo = 'USUARIO' AND id_modulo_pertencente = (SELECT id FROM portal_seguranca.modulo m WHERE sigla = 'GRT'));