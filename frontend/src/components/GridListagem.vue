<template lang="pug">

#grid-listagem
	v-row
		v-col(v-if="perfilUsuario !== 'Usuario'", cols='12' md='6')
			v-text-field#QA-input-pesquisar(
				v-model="parametrosFiltro.stringPesquisa"
				:placeholder="placeholderPesquisa",
				prepend-inner-icon="mdi-magnify",
				color="#E0E0E0",
				outlined,
				dense,
				@input='inputPesquisa'
			)
		//- v-col(cols='12' md='12')
		//- 	v-btn#QA-btn-abrir-cadastro.float-right.ml-4(
		//- 			@click="abrirTelaCadastro",
		//- 			large,
		//- 			dark,
		//- 			color="#84A98C",
		//- 			v-if="buttonCadastrar"
		//- 		)
		//- 		//- v-icon.font-cadastrar mdi-plus
		//- 		span.font-cadastrar Cadastrar
		//- v-col(cols='12' md='12')
		//-     v-btn#QA-btn-gerar-relatorio.float-right(
		//- 		    @click="gerarRelatorio",
		//- 		    large,
		//- 		    outlined,
		//- 		    color="#84A98C"
		//- 	   )
		//- 	   v-icon mdi-download
		//- 	   span Gerar relatório

	template
		v-data-table(
				:headers="headers",
				:items='dadosListagem.content',
				hide-default-footer,
				:items-per-page="itensPerPage",
				@update:options="sortBy"
			)

			template(v-slot:item.actions='{ item }')

				v-tooltip(bottom, v-if="item.status.codigo!='REPROVADO' && item.status.codigo != 'APROVADO'")
					template(v-slot:activator="{ on, attrs }")
						v-icon.mr-2(small @click='editarItem(item)', v-on='on', color='#404040')
							| mdi-play-circle-outline
					span Iniciar análise

				v-tooltip(bottom, v-if="item.status.codigo == 'VENCIDO'")
					template(v-slot:activator="{ on, attrs }")
						v-icon.mr-2(small @click='editarItem(item)', v-on='on', color='#404040')
							| mdi-replay
					span Revalidar cadastro

				v-tooltip(bottom, v-if="item.status.codigo=='REPROVADO'")
					template(v-slot:activator="{ on, attrs }")
						v-icon.mr-2(small @click='editarItem(item)', v-on='on', color='#404040')
							| mdi-chat
					span Visualizar justificativa

				v-tooltip(bottom, v-if="item.status.codigo == 'REPROVADO' || item.status.codigo == 'APROVADO'")
					template(v-slot:activator="{ on, attrs }")
						v-icon.mr-2(small @click='editarItem(item)', v-on='on', color='#404040')
							| mdi-eye
					span Visualizar cadastro

			template(v-slot:no-data, v-if="checkNomeItem()")
				span Não existem {{dadosListagem.nomeItem}} a serem exibidas.
			template(v-slot:no-data, v-else)
				span Não existem {{dadosListagem.nomeItem}} a serem exibidos.

			template(v-slot:footer, v-if="dadosListagem.numberOfElements > 0")
				v-row
					v-col(cols='12' md='8')
						v-pagination.float-left(
								v-model="page"
								:length="dadosListagem.totalPages"
								:page="page"
								:total-visible="totalVisible",
								@input="input",
								color="#84A98C"
							)
						span.float-left.exibicao-paginas.mt-4
							| Exibindo {{dadosListagem.numberOfElements}} de {{dadosListagem.totalElements}} registros

					v-col.flex-row.mt-3(cols='12' md='4')
						v-select.float-right.d-inline-flex.mx-4.w-80(
								:items="itensPerPages",
								solo,
								dense,
								@input="changeValue",
								v-model="itensPerPage"
							)
						span.float-right.exibicao-paginas.mt-2.ml-1
							| Resultados por página

</template>

<script>

export default {

	name:'GridListagem',

	props: {

		tituloAba: {
			type: [String]
		},
		tituloListagem: {
			type: [String]
		},
		placeholderPesquisa: {
			type: [String]
		},
		gerarRelatorio: {
			type: [Function]
		},
		headers: {
			type: [Array]
		},
		dadosListagem: {
			type: [Array]
		},
		updatePagination: {
			type: [Function]
		},
		editarItem: {
			type: [Function]
		},
		ativarDesativarItem: {
			type: [Function]
		},
		parametrosFiltro: {
			type: [Object]
		},
		buttonCadastrar: {
			type: [Boolean]
		},
		buttonRelatorio: {
			type: [Boolean]
		},
		abrirTelaCadastro: {
			type: [Function]
		},
		continuarRascunho: {
			type: [Function]
		},
		excluirRascunho: {
			type: [Function]
		}, perfilUsuario: {
			type: [String]
		}

	},

	data: () => ({

		page: 0,
		totalVisible: 7,
		itensPerPage: 10,
		itensPerPages: [10, 15, 20, 30, 40, 50, 100],

	}),

	updated() {

		if (this.dadosListagem) {

			if (this.dadosListagem.pageable) {
				this.page = this.dadosListagem.pageable.pageNumber + 1;
			}

			if (this.dadosListagem.content) {

				this.dadosListagem.content.forEach((item) => {
					item.model = false;
				});

			}

		}

	},

	methods: {

		changeValue(itensPerPage) {

			if (this.parametrosFiltro.itemsPorPagina !== itensPerPage) {

				this.parametrosFiltro.itemsPorPagina = itensPerPage;
				this.parametrosFiltro.pagina = 0;
				this.updatePagination(this.parametrosFiltro);
				window.scrollTo(0, 0);

			}

		},

		input(page) {

			if (this.parametrosFiltro.pagina !== page-1) {

				this.parametrosFiltro.pagina = page-1;
				this.updatePagination(this.parametrosFiltro);
				window.scrollTo(0, 0);

			}

		},

		inputPesquisa(text) {

			this.parametrosFiltro.pagina = 0;
			this.parametrosFiltro.stringPesquisa = text;
			this.updatePagination(this.parametrosFiltro);
			window.scrollTo(0, 0);

		},

		sortBy(value) {

			if (value.sortBy.length > 0) {
				this.parametrosFiltro.tipoOrdenacao = value.sortBy[0] + (value.sortDesc[0] ? ',desc' : ',asc');
			} else {
				this.parametrosFiltro.tipoOrdenacao = null;
			}
			this.updatePagination(this.parametrosFiltro);

		},

		dadosListagemIsNull() {
			return this.dadosListagem == null;
		},

		checkNomeItem() {
			return this.dadosListagem.nomeItem === 'usuarios';
		},

		ativarDesativar(item) {

			item.model = false;

			this.ativarDesativarItem(item);
		}

	},

};

</script>

<style lang="less">

@import "../assets/css/variaveis.less";

thead tr th {
	font-size: 14px !important;
}

tbody tr:nth-of-type(odd) {
	background-color: rgba(0, 0, 0, .05);
}

.titulo-listagem{
	font-size: 18px;
}

.exibicao-paginas{
	color: @text-color;
	font-size: 14px;
}

.v-pagination__item {
	font-size: 13px !important;
}

.v-pagination__item--active{
	cursor: default !important;
}

.w-80{
	width: 80px;

	.v-input__slot {
		font-size: 13px;
	}
}

.v-btn {
	text-transform: none !important;
}

.font-cadastrar{
	font-size:16px;
}

.v-text-field {
	padding-top: 2px !important;
    margin-top: 0 !important;
}

.v-label {
	display: inline-block;
    max-width: 100%;
    margin-bottom: 5px;
    font-weight: bold;
    font-size: 14px !important;
}

</style>
