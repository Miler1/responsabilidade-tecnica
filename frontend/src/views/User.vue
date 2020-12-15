<template lang="pug">

	v-container.py-12.align-center.justify-center

		h1.mb-12 Central do Responsável Técnico Ambiental

		Panel(titulo = 'Situação do cadastro:')
			GridListagem.pa-7(
				:tituloAba="tituloAba",
				:tituloListagem="tituloListagem",
				:placeholderPesquisa="placeholderPesquisa",
				:headers="headerListagem",
				:dadosListagem="dadosListagem",
				:updatePagination="updatePagination",
				:parametrosFiltro="parametrosFiltro",
				:perfil="perfil"

			)

		v-btn#QA-btn-cadastro.float-right.mt-6(@click='abrirTelaCadastro', large)
			v-icon mdi-plus
			span Cadastrar

</template>

<script>

import GridListagem from '@/components/GridListagem';
import Panel from '@/components/Panel';
import { HEADER } from '@/utils/dadosHeader/ListagemInformacoesTecnicas';

export default {

	name: 'Usuario',

	components: {
		GridListagem,
		Panel
	},

	data: () => {
		return {
			tituloAba: "responsabilidade técnica",
			tituloListagem: "Status solicitação",
			placeholderPesquisa: "",
			headerListagem: HEADER,
			dadosListagem: {},
			parametrosFiltro: {},
			perfil: 'Usuario'
		};
	},
	methods: {

		updatePagination() {
			this.dadosListagem.nomeItem = 'informacões técnicas';
		},

		abrirTelaCadastro() {
			this.$router.push({ name: 'Cadastro' });
		},
	},

	created() {
		this.updatePagination();
	}
};
</script>

<style lang="less" scoped>

@import "../assets/css/variaveis.less";

	#QA-btn-cadastro {
	    //padding: 8px 30px;
		font-size: 16px;
		background: @blue-primary;
		color: white;
	}

</style>