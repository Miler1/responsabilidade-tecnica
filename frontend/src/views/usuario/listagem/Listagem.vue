<template lang="pug">

	v-container.py-12.align-center.justify-center

		h1.mb-4 Central do Responsável Técnico Ambiental

		div.d-flex.flex-row-reverse
			v-btn#QA-btn-cadastro.mb-6(@click='abrirTelaCadastro', large, v-if="dadosListagem.content.length === 0")
				v-icon mdi-plus
				span Cadastrar

		Panel(titulo = 'Situação do cadastro:')
			GridListagem.pa-4(
				:tituloAba="tituloAba",
				:tituloListagem="tituloListagem",
				:placeholderPesquisa="placeholderPesquisa",
				:headers="headerListagem",
				:dadosListagem="dadosListagem",
				:updatePagination="updatePagination",
				:parametrosFiltro="parametrosFiltro",
				:visualizarItem="visualizarCadastro",
				:editarItem="editarCadastro"
				:perfil="perfil"
				:visualizarJustificativa="visualizarJustificativa",
				:textNoDataList="textNoDataList"
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

	data: () => {
		return {
			tituloAba: "responsabilidade técnica",
			tituloListagem: "Status solicitação",
			placeholderPesquisa: "",
			headerListagem: [],
			dadosListagem: {content:[]},
			parametrosFiltro: {stringPesquisa: ''},
			perfil: 'Usuario',
			textNoDataList: 'Você não possui cadastro como responsável técnico ambiental. Realize o cadastro através do botão "Cadastrar".'
		};
	},

	methods: {

		visualizarCadastro(item) {

			if (!item) {
				return;
			}

			this.$router.push({ name: 'VisualizarCadastroUsuario', params: { id: item.id }});

		},

		visualizarJustificativa(item) {

			this.$fire({

				title:
					'<p class="title-modal-confirm">Justificativa da reprovação</p>',
				html:
					`<p class="message-modal-confirm" style="text-align: justify; text-justify: inter-word; padding-bottom: 16px">
						${item.justificativa}
					</p>`,

				confirmButtonColor: '#327C32',
				showCloseButton: true,
				focusConfirm: false,
				confirmButtonText: '<i class="mdi mdi-close"></i> Fechar',
				reverseButtons: true,

			});

		},

		updatePagination() {

			PessoaService.buscarPessoalogada()
				.then((response) => {

					let pessoa = response.data;

					ResponsavelTecnicoService.buscarSolicitacao(pessoa.id)
						.then( (result) => {

							if (result.data === "") {
								this.dadosListagem.content = this.headerListagem = [];
							} else {

								this.dadosListagem.content = [result.data];
								this.headerListagem = HEADER;

							}

						}).catch( error => {
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

		editarCadastro(item) {
			this.$router.push({ name: 'Editar', params: { id : item.id } });
		}
	},

	created() {
		this.updatePagination();
	},

	beforeRouteLeave(to, from, next) {

		if (to.name === 'Cadastro' && this.dadosListagem.content.length > 0) {
			this.$router.push({ name: 'Usuario' });
		} else {
			next();
		}

	}

};
</script>

<style lang="less">

@import "../../../assets/css/variaveis.less";

	#QA-btn-cadastro {
	    //padding: 8px 30px;
		text-transform: none;
		font-size: 16px;
		background: @color-primary;
		color: white;
	}

</style>