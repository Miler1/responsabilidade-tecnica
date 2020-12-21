<template lang='pug'>

	v-container.pa-12.align-center.justify-center

		h1.mb-4 Gestão de Responsabilidade Técnica Ambiental

		Panel(titulo = 'Listagem de responsáveis técnicos cadastrados')
			GridListagem(
				:headers="headerListagem",
				:dadosListagem="dadosListagem",
				:placeholderPesquisa="placeholderPesquisa",
				:updatePagination="updatePagination",
				:editarItem="editarItem",
				:parametrosFiltro="parametrosFiltro",
				:perfil="perfil",
				:visualizarItem="visualizarCadastro"
				:visualizarJustificativa="visualizarJustificativa"

			)

</template>

<script>

import ResponsavelTecnicoService from '@/services/responsavelTecnico.service';
import Panel from '@/components/Panel.vue';
import GridListagem from '@/components/GridListagem';
import { HEADER } from '@/utils/dadosHeader/ListagemSolicitacoes';
import { ERROR_MESSAGES } from '@/utils/helpers/messages-utils';

export default {

	name: 'AdministradorListagem',

	components: {
		Panel,
		GridListagem
	},

	data: () => {

		return {
			placeholderPesquisa: "Pesquisar pelo nome do responsável ou status",
			headerListagem: HEADER,

			dadosListagem: {},
			parametrosFiltro: {
				pagina: 0,
				itemsPorPagina: 10,
				tipoOrdenacao: 'id,asc',
				stringPesquisa: ''
			},
			perfil: 'Administrador'
		};
	},

	methods: {

		visualizarJustificativa(item) {

			this.$fire({

				title:
					'<p class="title-modal-confirm">Justificativa</p>',
				html:
					`<p class="message-modal-confirm" style="text-align: justify; text-justify: inter-word; padding-bottom: 16px">
						<b>` + item.justificativa + `</b>
					</p>`,

				confirmButtonColor: '#327C32',
				showCloseButton: true,
				focusConfirm: false,
				confirmButtonText: '<i class="mdi mdi-close"></i> Fechar',
				reverseButtons: true,

			});

		},

		updatePagination(parametrosFiltro) {

			ResponsavelTecnicoService.listar(parametrosFiltro)
				.then((response) => {

					this.dadosListagem = response.data;
					this.dadosListagem.nomeItem = "responsáveis técnicos";

				})
				.catch(erro => {

					console.error(erro);

					snackbar.alert(ERROR_MESSAGES.responsavelTecnico.listagem);

				});

		},

		editarItem(item) {

			if (!item) {
				return;
			}

			this.$router.push({name: 'AnalisarRelatorio', params: { id: item.id}});

		},

		visualizarCadastro(item) {

			if (!item) {
				return;
			}

			this.$router.push({ name: 'VisualizarCadastroAdministrador', params: { id: item.id }});

		},

	},

};
</script>

<style>

</style>