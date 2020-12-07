<template lang="pug">
	v-layout.align-center.justify-center
		v-card#card-usuario.elevation-12
			GridListagem.pa-7(
				:tituloAba="tituloAba",
				:tituloListagem="tituloListagem",
				:placeholderPesquisa="placeholderPesquisa",
				:headers="headerListagem",
				:dadosListagem="dadosListagem",
				:updatePagination="updatePagination",
				:parametrosFiltro="parametrosFiltro",
				:abrirTelaCadastro="abrirTelaCadastro",
				:buttonCadastrar="buttonCadastrar"
			)

			v-btn#QA-btn-cadastro.float-right(@click='abrirTelaCadastro', large, dark)
				span Cadastro

</template>

<script>

import GridListagem from '@/components/GridListagem';
import { HEADER } from '@/utils/dadosHeader/ListagemUsuarioHeader';

export default {

	name: 'User',

	components: {
		GridListagem
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
			this.dadosListagem.content = [{ ativo: false, validade: new Date() }];
			console.log(this.dadosListagem);
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

	#card-usuario{
		margin: 50px;
		background-color: rgba(255, 255, 255, 0.91);
		border: 1px solid @border-components;
		border-radius: 10px;
		height: auto;
		width: 50%;
	}

	#QA-btn-cadastro {
		background-color: @green-primary;
		color: @btn-text-color;
		font-size: 16px;
		text-transform: none !important;
		    /* float: right; */
	    position: absolute;
	    /* display: flex; */
	    right: 26px;
	    margin-top: 30px;
	    padding-right: 30px;
	    padding-left: 30px;
	}

</style>