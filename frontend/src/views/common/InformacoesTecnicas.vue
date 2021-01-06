<template lang="pug">

#informacoes-tecnicas
	div.mb-6
		ExpansivePanel(titulo = 'Informações técnicas')
			v-row
				v-col(cols="12", md="6")
					v-col(cols="12")
						v-label Formação:
						span &nbsp;{{this.dados.formacao}}
					v-col(cols="12")
						v-label Registro:
						span &nbsp;{{this.dados.registro}}
					v-col(cols="12")
						v-label Possui vínculo com o GEA:
						span &nbsp;{{this.dados.possuiVinculoComGea ? 'Sim' : 'Não'}}

				v-col(cols="12", md="6")
					v-col(cols="12")
						v-label Conselho de classe:
						span &nbsp;{{this.dados.conselhoDeClasse}}
					v-col(cols="12")
						v-label Nível de responsabilidade técnica:
						span &nbsp;{{this.niveisRT[this.dados.nivelResponsabilidadeTecnica]}}
					v-col(v-if="this.dados.possuiVinculoComGea", cols="12")
						v-label Vinculo empregatício:
						span(v-if="this.dados.vinculoEmpregaticio === 'OUTRO'") &nbsp; Outro - {{this.dados.outroVinculoEmpregaticio}}
						span(v-else) &nbsp;{{this.vinculo[this.dados.vinculoEmpregaticio]}}
					v-col(v-else, cols="12")
						v-label Área de especialização:
						span &nbsp;{{this.dados.especializacao ? this.dados.especializacao.codigo : ''}}
							|- {{this.dados.especializacao ? this.dados.especializacao.nome : ''}}

				v-col.py-0(v-if="this.dados.possuiVinculoComGea", cols="12")
					v-col.pt-0.pb-6(cols="12")
						v-label Área de especialização:
						span &nbsp;{{this.dados.especializacao ? this.dados.especializacao.codigo : ''}}
							|- {{this.dados.especializacao ? this.dados.especializacao.nome : ''}}

	div.mb-6
		ExpansivePanel(titulo = 'Anexos')
			v-row.pa-3
				v-col(cols="12")
					GridListagemInclusao(
						:headers="headerListagem",
						:dadosListagem="dados.documentos",
						:labelNoData="labelNoData",
						:downloadAnexo="downloadAnexo",
						:exibirIconeRemover='false',
						:visualizacao='true'
					)

</template>

<script>

import DataUtils from '@/utils/dataUtils';
import ExpansivePanel from '@/components/ExpansivePanel';
import GridListagemInclusao from '@/components/GridListagemInclusao';
import ResponsavelTecnicoService from '@/services/responsavelTecnico.service';
import { NIVEIS_RT, VINCULO } from '@/enums/responsabilidadeTecnicaEnum';
import { HEADER } from '@/utils/dadosHeader/ListagemAnexoInclusao';

export default {

	name: 'InformacoesTecnicas',

	components: {
		ExpansivePanel,
		GridListagemInclusao,
	},

	data: () => {
		return{
			niveisRT: NIVEIS_RT,
			vinculo: VINCULO,
			headerListagem: HEADER,
			labelNoData: "Não há nenhum anexo adicionado",
		};
	},

	props: {
		dados: {
			type: [Object]
		}
	},

	methods: {

		downloadAnexo(item) {

			ResponsavelTecnicoService.download(item.hash)
				.then((result) => {
					let blob = DataUtils.b64ToBlob(result.data, result.headers['content-type']);
					const link = document.createElement('a');
					link.href = URL.createObjectURL(blob);
					link.download = item.nome;
					link.target = '_blank';
					link.click();
					URL.revokeObjectURL(link.href);
				});

		},

	},

};

</script>

<style lang="less">

	.v-col {

		span {
			font-weight: 400 !important;
			font-size: 14px !important;
		}
	}

</style>