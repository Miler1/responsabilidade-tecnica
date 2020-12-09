<template lang='pug'>

	v-container.pa-12.align-center.justify-center

		h1.mb-12 Gestão de responsáveis técnicos

		Panel(titulo = 'Listagem de responsáveis técnicos cadastrados')
			GridListagem(
				:headers="headerListagem",
				:dadosListagem="dadosListagem",
				:placeholderPesquisa="placeholderPesquisa",
				:updatePagination="updatePagination",
				:editarItem="editarItem",
				:ativarDesativarItem="ativarDesativarItem",
				:parametrosFiltro="parametrosFiltro"
			)

</template>

<script>
import Panel from '@/components/Panel.vue';
import GridListagem from '@/components/GridListagem';
import { HEADER } from '@/utils/dadosHeader/ListagemSolicitacoes';

export default {

	name: 'Administrador',

	data: () => {

		return {
			placeholderPesquisa: "teste placeholder",
			headerListagem: HEADER,

			dadosListagem: {
				content: [
					{
						responsavel: {
							nome: "Nome MOCK 1",
							cod: "Um codigo mock 1"
						},
						status: {
							codigo: "STATUS MOCK 1",
							nome: "Status Mock 1"
						},
						justificativa: "Justificativa mock 1",
						validade: "31/03/2022"
					},
					{
						responsavel: {
							nome: "Nome MOCK 2",
							cod: "Um codigo mock 2"
						},
						status: {
							codigo: "VENCIDO",
							nome: "Vencido"
						},
						justificativa: "Justificativa mock 2",
						validade: "31/03/2020"
					},
				]
			},
			updatePagination: () => {},
			editarItem: () => console.log("EDITAR"),
			ativarDesativarItem: () => console.log("ATIVAR/DESATIVAR"),
			parametrosFiltro: {}
		};
	},

	components: {
		Panel,
		GridListagem
	}
	
};
</script>

<style>

</style>