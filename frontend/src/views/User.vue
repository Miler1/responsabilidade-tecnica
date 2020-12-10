<template lang="pug">

	v-container.pa-12.align-center.justify-center

		h1.mb-12 Central do responsável técnico ambiental

		Panel(titulo = 'Situação do cadastro:')
			GridListagem.pa-7(
				:tituloAba="tituloAba",
				:tituloListagem="tituloListagem",
				:placeholderPesquisa="placeholderPesquisa",
				:headers="headerListagem",
				:dadosListagem="dadosListagem",
				:updatePagination="updatePagination",
				:parametrosFiltro="parametrosFiltro"
			)

			v-btn#QA-btn-cadastro.float-right(@click='abrirTelaCadastro', large)
				span Cadastrar

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
			placeholderPesquisa: "",
			headerListagem: HEADER,
			dadosListagem: {
				content: [
					{
						solicitacao: {
							nome: "Nome MOCK 2",
							cod: "Um codigo mock 2"
						},
						status: {
							codigo: "REPROVADO",
							nome: "Reprovado"
						},
						validade: "-"
					},
					{
						solicitacao: {
							nome: "Nome MOCK 2",
							cod: "Um codigo mock 2"
						},
						status: {
							codigo: "APROVADO",
							nome: "Aprovado"
						},
						validade: "31/06/2020"
					}
				]
			},
			parametrosFiltro: {}
		};
	},
	methods: {

		updatePagination() {
			this.dadosListagem.nomeItem = 'usuarios';
		},

		abrirTelaCadastro() {
			console.log('abrir');
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
	    padding: 8px 30px;
	    margin: 40px 28px;
	    font-size: 16px;
	    background: @blue-primary;
	    color: white;
	}

</style>