<template lang="pug">

#header
	v-app-bar(app, flat, tile, outlined=true)
		v-row
			v-col.d-flex.flex-column.align-center(cols="12", md="8")
				v-img.cursor-pointer.align-self-start(contain :src="require('@/assets/img/Logo_GRT_Horizontal.png')" height="40px", width="180px", @click="clickHome")
			v-col.d-flex.flex-column.align-center(cols="12", md="4")
				div.align-self-end
					div#div-toolbar.d-flex.flex-row.justify-center.align-center
						v-icon.mr-2 mdi-account
						v-tooltip(bottom)
							template(v-slot:activator="{ on, attrs }")
								v-toolbar-title#toolbar-title.text-sm-body-2.text-center(v-bind="attrs", v-on="on")
									| {{usuarioLogado && usuarioLogado.name}}
							span {{usuarioLogado && usuarioLogado.name}}
						v-tooltip(bottom)
							template(v-slot:activator="{ on, attrs }")
								v-btn(icon, @click='logout', v-on="on")
									v-icon mdi-logout
							span Sair do sistema
					v-toolbar-title.text-caption.text-center
						| {{getPerfilUsuario()}}
	//-hr.thin

</template>

<script>

import { mapGetters } from 'vuex';
import { USUARIO_LOGOUT } from '@/store/actions.type.js';
import { ERROR_MESSAGES } from '@/utils/helpers/messages-utils';
import snackbar from '@/services/snack.service';

export default {

	name: 'MenuSuperior',

	methods: {
		logout () {
			this.$store.dispatch(USUARIO_LOGOUT)
				.then (() => {
					window.location.href = process.env.VUE_APP_URL_PORTAL_SEGURANCA;
				}).catch((error) => {
					console.error(error);
					snackbar.alert(ERROR_MESSAGES.logout);
				});
		},

		getPerfilUsuario () {

			return this.usuarioLogado == null ? '' : 
				this.usuarioLogado.principal.authorities.filter(function(e) { return e.authority === 'ADMINISTRADOR'; }).length > 0 ? 'Administrador' : 'Usuário';

		},

		clickHome () {
			this.$router.push({path: '/'});
		}
	},

	computed: {
		...mapGetters(['usuarioLogado'])
	},
};

</script>

<style lang="less" scoped>

#div-toolbar {
	height: 16px;

	#toolbar-title {
		cursor: default;
	}
}

.text-caption {
	color: #BDBDBD;
}

.thin {
	height: 1px !important;
}

.cursor-pointer {
	cursor: pointer;
}

</style>