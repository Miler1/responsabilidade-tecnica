<template lang='pug'>

#app
	v-app
		v-main.default
			MenuSuperior

			v-container
				router-view

				//- Elemento para exibir mensagens de alertas globais
				v-snackbar(v-for='(snackbar, index) in snackbars.filter(s => s.showing)'
							:key='snackbar.text + Math.random()'
							v-model='snackbar.showing'
							:timeout='snackbar.timeout'
							:color='snackbar.color',
							top,
							:style='`padding-top: ${(index * 60) + 8}px;`')

					v-icon.mr-4 {{snackbar.icon}}

					span {{snackbar.text}}

					template(v-slot:action=	'{ attrs }')
						v-btn(text @click='snackbar.showing = false' v-bind="attrs")
							| Fechar

</template>

<script>

import { mapGetters } from 'vuex';
import MenuSuperior from '@/components/MenuSuperior.vue';
import Snackbar from '@/components/Snackbar';

export default {

	name: "App",

	components: {
		MenuSuperior,
		Snackbar
	},

	computed: {
		...mapGetters(['snackbars'])
	},

};

</script>

<style lang="less">

@import "assets/css/variaveis.less";

html {
	overflow-y: auto !important;

	body {
		margin: 0;

		h1, h2 {
			color: @color-primary;
		}

		#app {
			font-family: Roboto, sans-serif;
			-webkit-font-smoothing: antialiased;
			-moz-osx-font-smoothing: grayscale;
		}

		.swal2-shown {
			font-family: Roboto, sans-serif;
			-webkit-font-smoothing: antialiased;
			-moz-osx-font-smoothing: grayscale;

			.swal2-header {
				align-items: baseline;
				border-bottom: 1px solid #ddd;
			}

			.title-modal-confirm {
				font-size: 18px;
				text-align: left;
			}

			.message-modal-confirm {
				font-size: 16px;
				margin-top: 20px;
				text-align: left;
			}

			.swal2-cancel {
				margin-right: 130px;
				color: #777777;
				border: 1px solid #E0E0E0 !important;
				outline: none;
			}

			.swal2-close {
				margin-top: 4px;
			}
		}

		.v-snack__wrapper {
			max-width: 80%;
		}
	}
}

.default{
	background-color: #E0E0E0;
}

.container {
	width: 85% !important;
}

</style>