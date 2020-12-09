import Vue from 'vue';
import VueRouter from 'vue-router';

import { GET_USUARIO } from '@/store/actions.type';
import Index from '@/store/index.js';

Vue.use(VueRouter);

const routes = [
	{
		path: '/',

		beforeEnter: (to, from, next) => {
			BuscaUsuarioLogado(next, true);
		}
	},
	{
		path: '/user',
		name: 'Usuario',
		meta: {
			title: 'Gestão de Responsabilidade Técnica Ambiental'
		},

		component: () => import('../views/User.vue'),

		beforeEnter: (to, from, next) => {
			BuscaUsuarioLogado(next);
		}
	},
	{
		path: '/admin',
		name: 'Administrador',
		meta: {
			title: 'Gestão de Responsabilidade Técnica Ambiental'
		},

		component: () => import('../views/Admin.vue'),

		beforeEnter: (to, from, next) => {
			BuscaUsuarioLogado(next);
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

function BuscaUsuarioLogado(next, login) {

	Index.dispatch(GET_USUARIO)
		.then((usuario) => {

			if (usuario.authenticated) {

				if(login){
					next(usuario.role.url);
				} else {
					next();
				}

			} else {
				window.location.href = process.env.VUE_APP_URL_PORTAL_SEGURANCA;
			}

		})
		.catch(erro => {
			next(false);
		});

}

export default router;
