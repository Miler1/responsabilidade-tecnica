import ApiService from './api.service';

export default {

	getUsuario: async () => ApiService.get('/autenticacao/usuario-logado'),

	logout: async () => ApiService.post('/logout'),

	getRole: authorities => {

		let defaultRoles = ['ADMINISTRADOR', 'USUARIO'];
		let roles = {
			ADMINISTRADOR: {role: 'Administrador', url: '/admin', codigo: 'ADMIN'},
			USUARIO: {role: 'Usuário', url: '/user', codigo: "USER"}
		};

		let role = null;

		if(authorities){

			authorities.forEach(elem => {

				if(defaultRoles.indexOf(elem.authority) >= 0){
					role = roles[elem.authority];
				}

			});

		}

		return {perfis:roles, perfilSelecionado:role};

	}

};