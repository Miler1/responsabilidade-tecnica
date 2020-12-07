import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';

import { GET_USUARIO } from '@/store/actions.type';
import Index from '@/store/index.js';

Vue.use(VueRouter);

const routes = [
	{
		path: '/',
		name: 'Home',
		component: Home,

		meta: {
			title: 'Gestão de Responsabilidade Técnica'
		},

		beforeEnter: (to, from, next) => {
			BuscaUsuarioLogado(next);
		}
	},
	{
		path: '/user',
		name: 'Usuário',
		// route level code-splitting
		// this generates a separate chunk (about.[hash].js) for this route
		// which is lazy-loaded when the route is visited.
		component: () => import(/* webpackChunkName: "about" */ '../views/User.vue')
	},
	{
		path: '/admin',
		name: 'Administrador',
		component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
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

function BuscaUsuarioLogado(next) {

	Index.dispatch(GET_USUARIO)
		.then((usuario) => {
			console.log(usuario);
			if (usuario.authenticated) {
				next();
			} else {
				window.location.href = process.env.VUE_APP_URL_PORTAL_SEGURANCA;
			}
		})
		.catch(erro => {
			next(false);
		});

}

export default router;
