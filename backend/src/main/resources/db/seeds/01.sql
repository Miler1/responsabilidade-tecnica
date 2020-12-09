INSERT INTO portal_seguranca.perfil_usuario
SELECT users.id AS user_id, profiles.id AS prof_id
FROM
	(SELECT id, login FROM portal_seguranca.usuario u) AS users,
	(SELECT id FROM portal_seguranca.perfil p
	 WHERE codigo = 'USUARIO'
	 AND id_modulo_pertencente = (SELECT id FROM portal_seguranca.modulo m WHERE sigla = 'GRT')) AS profiles
WHERE users.id NOT IN (SELECT id_usuario FROM portal_seguranca.perfil_usuario WHERE id_perfil = (SELECT id
	 FROM portal_seguranca.perfil p
	 WHERE codigo = 'USUARIO' AND id_modulo_pertencente = (SELECT id FROM portal_seguranca.modulo m WHERE sigla = 'GRT')))
AND char_length(users.login) = 11;