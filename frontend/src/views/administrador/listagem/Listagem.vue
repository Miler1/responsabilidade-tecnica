<template lang='pug'>

	v-container.pa-12.align-center.justify-center

		h1.mb-12 Gestão de Responsabilidade Técnica Ambiental

		Panel(titulo = 'Listagem de responsáveis técnicos cadastrados')
			GridListagem(
				:headers="headerListagem",
				:dadosListagem="dadosListagem",
				:placeholderPesquisa="placeholderPesquisa",
				:updatePagination="updatePagination",
				:iniciarAnalise="iniciarAnalise",
				:parametrosFiltro="parametrosFiltro",
				:perfil="perfil"
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
			placeholderPesquisa: "Pesquisar pelo nome ou status do cadastro",
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

		iniciarAnalise(item) {

			if (!item) {
				return;
			}

			console.log(item);

			this.$router.push({name: 'AnalisarRelatorio', params: { id: item.id}});

		}
	},

};
</script>

<style>

</style>