import Vue from 'vue';
import VueRouter from 'vue-router';

import { GET_USUARIO } from '@/store/actions.type';
import Index from '@/store/index.js';

Vue.use(VueRouter);

const routes = [
	{
		path: '/',

		beforeEnter: async (to, from, next) => {
			ValidarRotaDefault(next);
		}

	},
	{
		path: '/user',
		name: 'Usuario',
		meta: {
			title: 'Gestão de Responsabilidade Técnica Ambiental'
		},

		component: () => import('@/views/usuario/User.vue'),

		beforeEnter: (to, from, next) => {
			ValidarRota(next, 'USUARIO');
		},

		redirect: to => {
			return '/user/home';
		},

		children: [
			{
				path: 'home',
				name: 'Home',
				component: () => import('@/views/usuario/listagem/Listagem.vue')
			},
			{
				path: 'cadastro',
				name: 'Cadastro',
				component: () => import('@/views/usuario/cadastro/Cadastro.vue')
			},
			{
				path: 'edicao/:id',
				name: 'Editar',
				component: () => import('@/views/usuario/cadastro/Cadastro.vue')
			},
			{
				path: 'visualizar/:id',
				name: 'VisualizarCadastroUsuario',
				component: () => import('@/views/common/Visualizacao.vue')
			},
		],
	},
	{
		path: '/admin',
		name: 'Administrador',
		meta: {
			title: 'Gestão de Responsabilidade Técnica Ambiental'
		},

		component: () => import('@/views/administrador/Admin.vue'),

		beforeEnter: (to, from, next) => {
			ValidarRota(next, 'ADMINISTRADOR');
		},

		redirect: to => {
			return '/admin/home';
		},

		children: [
			// CADASTRO
			{
				path: 'home',
				name: 'Home',
				component: () => import('@/views/administrador/listagem/Listagem.vue')
			},
			{
				path: 'analisar/:id',
				name: 'AnalisarRelatorio',
				component: () => import('@/views/common/Visualizacao.vue')
			},
			{
				path: 'visualizar/:id',
				name: 'VisualizarCadastroAdministrador',
				component: () => import('@/views/common/Visualizacao.vue')
			},

		],
	},
	{
		path:'*',

		beforeEnter: (to, from, next) => {
			ValidarRotaDefault(next);
		}

	}
];

const router = new VueRouter({
	mode: 'hash',
	base: process.env.BASE_URL,
	routes
});

// Definir título da página
router.beforeEach((to, from, next) => {

	const maisProximaComTitulo = to.matched.slice().reverse().find(r => r.meta && r.meta.title);

	if (maisProximaComTitulo) {
		document.title = maisProximaComTitulo.meta.title;
	}

	next();

});

async function ValidarRotaDefault(next) {

	let usuario = await BuscarUsuarioLogado();

	if (usuario) {
		next(usuario.role.perfilSelecionado.url);
	} else {
		window.location.href = process.env.VUE_APP_URL_PORTAL_SEGURANCA;
	}

}

async function BuscarUsuarioLogado() {

	let usuarioLogado;

	await Index.dispatch(GET_USUARIO)
		.then((usuario) => {

			if (usuario && usuario.authenticated) {
				usuarioLogado = usuario;
			}

		})
		.catch(error => {
			console.error(error);
		});

	return usuarioLogado;

}

async function ValidarRota(next, perfil) {

	let usuario = await BuscarUsuarioLogado(next);

	if (ValidarPerfil(usuario, perfil)) {
		next();
	} else {
		next(usuario.role.perfilSelecionado.url);
	}

}

function ValidarPerfil(usuario, perfil) {

	if (!usuario) {
		window.location.href = process.env.VUE_APP_URL_PORTAL_SEGURANCA;
	}

	return usuario.role.perfilSelecionado === usuario.role.perfis[perfil];

};

export default router;