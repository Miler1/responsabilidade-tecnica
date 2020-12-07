<template lang="pug">
	Panel(titulo = 'Cadastro de Responsabilidade Técnica e Ambiental')
		GridListagem.pa-7(
			:tituloAba="tituloAba",
			:tituloListagem="tituloListagem",
			:placeholderPesquisa="placeholderPesquisa",
			:headers="headerListagem",
			:dadosListagem="dadosListagem",
			:updatePagination="updatePagination",
			:parametrosFiltro="parametrosFiltro",
			:abrirTelaCadastro="abrirTelaCadastro",
			:buttonCadastrar="buttonCadastrar",
			:buttonRelatorio="buttonRelatorio"
		)

		v-btn#QA-btn-cadastro.float-right(@click='abrirTelaCadastro', large, dark)
			span Cadastro

</template>

<script>

import GridListagem from '@/components/GridListagem';
import Panel from '@/components/Panel';
import { HEADER } from '@/utils/dadosHeader/ListagemUsuarioHeader';

export default {

	name: 'User',

	components: {
		GridListagem,
		Panel
	},

	data: () => {
		return {
			tituloAba: "responsabilidade técnica",
			tituloListagem: "Status solicitação",
			placeholderPesquisa: "busca de usuários",
			headerListagem: HEADER,
			dadosListagem: {},
			parametrosFiltro: {
				pagina: 0,
				itemsPorPagina: 10,
				tipoOrdenacao: 'ativo, asc',
				stringPesquisa: ''
			},
			buttonCadastrar: false,
			buttonRelatorio: false
		};
	},
	methods: {

		updatePagination() {
			this.dadosListagem.nomeItem = 'usuarios';
		},

		abrirTelaCadastro() {
			console.log('abrir');
		},
	},

	mounted() {
		this.updatePagination();
	}
};
</script>

<style lang="less" scoped>

@import "../assets/css/variaveis.less";

	#QA-btn-cadastro {
	    border-radius: 2px;
	    position: relative;
	    padding: 8px 30px;
	    margin: 40px 28px;
	    font-size: 14px;
	    font-weight: 500;
	    text-transform: uppercase;
	    letter-spacing: 0;
	    will-change: box-shadow, transform;
	    transition: box-shadow 0.2s cubic-bezier(0.4, 0, 1, 1), background-color 0.2s cubic-bezier(0.4, 0, 0.2, 1), color 0.2s cubic-bezier(0.4, 0, 0.2, 1);
	    outline: 0;
	    cursor: pointer;
	    text-decoration: none;
	    background: @blue-primary;
	}

</style>