<template lang="pug">

	v-container.py-12.align-center.justify-center

		h1.mb-12 Central do Responsável Técnico Ambiental

		div.d-flex.flex-row-reverse
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
				:visualizarJustificativa="visualizarJustificativa",
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

			this.$router.push({ name: 'VisualizarCadastroUsuario', params: { id: item.id }});

		},

		visualizarJustificativa(item) {

			this.$fire({

				title:
					'<p class="title-modal-confirm">Justificativa</p>',
				html:
					`<p class="message-modal-confirm">Justificativa:</p>
					<p class="message-modal-confirm" style="text-align: justify; text-justify: inter-word; padding-bottom: 16px">
						<b>` + item.justificativa + `</b>
					</p>`,

				confirmButtonColor: '#327C32',
				showCloseButton: true,
				focusConfirm: false,
				confirmButtonText: '<i class="mdi mdi-close"></i> Fechar',
				reverseButtons: true,

			});

		},

		updatePagination() {

			ResponsavelTecnicoService.buscarSolicitacao()
				.then( (result) => {

					this.dadosListagem.content = result.data === "" ? [] : [result.data];
					this.dadosListagem.noData = 'Você ainda não possui cadastro como responsável técnico ambiental. Realize seu cadastro através do botão "Cadastrar".';

				})
				.catch( error => {
					console.error(error);
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

@import "../../../assets/css/variaveis.less";

	#QA-btn-cadastro {
	    //padding: 8px 30px;
		font-size: 16px;
		background: @blue-primary;
		color: white;
	}

</style>