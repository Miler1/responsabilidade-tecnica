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
				:visualizarItem="visualizarCadastro"
			)

		v-btn#QA-btn-cadastro.float-right.mt-6(@click='abrirTelaCadastro', large)
			v-icon mdi-plus
			span Cadastrar

</template>

<script>

import GridListagem from '@/components/GridListagem';
import Panel from '@/components/Panel';
import ResponsavelTecnicoService from '@/services/responsavelTecnico.service';
import PessoaService from '@/services/pessoa.service';
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
			dadosListagem: {content:[]},
			parametrosFiltro: {},
			perfil: 'Usuario'
		};
	},
	methods: {

		visualizarCadastro() {
			alert("TESTE!");
		},

		updatePagination() {

			PessoaService.buscarPessoalogada()
				.then((response) => {

					let pessoa = response.data;

					ResponsavelTecnicoService.buscarSolicitacao(pessoa.id)
						.then( (result) => {

							this.dadosListagem.content = result.data;
							this.dadosListagem.nomeItem = "informações técnicas";

						})
						.catch( error => {
							console.error(error);
						});
				})
				.catch(error => {
					console.error(error.message);
				});

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

@import "../../assets/css/variaveis.less";

	#QA-btn-cadastro {
	    //padding: 8px 30px;
		font-size: 16px;
		background: @blue-primary;
		color: white;
	}

</style>