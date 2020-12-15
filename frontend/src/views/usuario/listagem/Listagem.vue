<template lang="pug">

	v-container.py-12.align-center.justify-center

		h1.mb-12 Central do Responsável Técnico Ambiental

		v-btn#QA-btn-cadastro.mb-6(@click='abrirTelaCadastro', large, v-if="dadosListagem.content.length === 0")
			v-icon mdi-plus
			span Cadastrar

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
				:visualizarItem="visualizarCadastro",
				:editarItem="editarCadastro"
			)

</template>

<script>

import GridListagem from '@/components/GridListagem';
import Panel from '@/components/Panel';
import ResponsavelTecnicoService from '@/services/responsavelTecnico.service';
import PessoaService from '@/services/pessoa.service';
import { HEADER } from '@/utils/dadosHeader/ListagemInformacoesTecnicas';

export default {

	name: 'UsuarioListagem',

	components: {
		GridListagem,
		Panel
	},

	props: {
		pessoa: {
			type: [Object]
		}
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

		visualizarCadastro(item) {

			if (!item) {
				return;
			}

			if(item.id){

			}

		},

		updatePagination() {

			PessoaService.buscarPessoalogada()
				.then((response) => {

					let pessoa = response.data;
					console.log(pessoa);
					ResponsavelTecnicoService.buscarSolicitacao(pessoa.id)
						.then( (result) => {

							this.dadosListagem.content = result.data;
							this.dadosListagem.noData = 'Você ainda não possui cadastro como responsável técnico ambiental. Realize seu cadastro através do botão "Cadastrar".';
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

		editarCadastro(pessoa) {
			this.$router.push({ name: 'Editar', params: { idPessoa: pessoa.id } });
		}
	},

	created() {
		// this.updatePagination();
		console.log(this.pessoa);
	}
};
</script>

<style lang="less" scoped>

@import "../../../assets/css/variaveis.less";

	#QA-btn-cadastro {
	    //padding: 8px 30px;
		font-size: 16px;
		background: @blue-primary;
		color: white;
	}

</style>