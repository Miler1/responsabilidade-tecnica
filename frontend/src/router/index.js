import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';

Vue.use(VueRouter);

const routes = [
	{
		path: '/',
		name: 'Home',
		component: Home,

		meta: {
			title: 'Gestão de Responsabilidade Técnica'
		},
	},
	{
		path: '/about',
		name: 'About',
		// route level code-splitting
		// this generates a separate chunk (about.[hash].js) for this route
		// which is lazy-loaded when the route is visited.
		component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
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

export default router;
