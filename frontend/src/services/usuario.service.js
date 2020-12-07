import ApiService from './api.service';

export default {

	getUsuario: async () => ApiService.get('/autenticacao/usuario-logado'),

	logout: async () => ApiService.post('/logout'),

	getRole: authorities => {

		let defaultRoles = ['ADMINISTRADOR', 'USUARIO'];
		let roles = new Map();

		roles.set('ADMINISTRADOR', {role: 'Administrador', url: '/admin'});
		roles.set('USUARIO', {role: 'Usuário', url: '/user'});

		let role = null;

		if(authorities){

			authorities.forEach(elem => {

				if(defaultRoles.indexOf(elem.authority) >= 0){
					role = roles.get(elem.authority);
				}

			});

		}

		return role;

	}

};