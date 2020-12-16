<template lang="pug">

	#informacoes-tecnicas

		div.mb-6
			ExpansivePanel(titulo = 'Informações técnicas')
				v-row
					v-col(cols="12", md="6")
						v-label Formação:
						span &nbsp;{{this.dados.formacao}}
					v-col(cols="12", md="3")
						v-label Conselho de classe:
						span &nbsp;{{this.dados.conselhoDeClasse}}
					v-col(cols="12", md="3")
						v-label Registro:
						span &nbsp;{{this.dados.registro}}

				v-row
					v-col(cols="12", md="6")
						v-label Nível de responsabilidade técnica:
						span &nbsp;{{this.niveisRT[this.dados.nivelResponsabilidadeTecnica]}}
					v-col(cols="12", md="6")
						v-label Possui vínculo com o GEA:
						span &nbsp;{{this.dados.possuiVinculoComGea ? 'Sim' : 'Não'}}

				v-row
					v-col(cols="12", md="6", v-if="this.dados.possuiVinculoComGea")
						v-label Vínculo empregatício:
						span &nbsp;{{this.vinculo[this.dados.vinculoEmpregaticio]}}
					v-col(cols="12", md="6", v-if="this.dados.possuiVinculoComGea && this.dados.vinculoEmpregaticio === 'OUTRO'")
						v-label Nome do vínculo:
						span &nbsp;{{this.dados.outroVinculoEmpregaticio}}

				v-row
					v-col(cols="12", md="12")
						v-label Área de especialização:
						span &nbsp;{{this.dados.especializacao ? this.dados.especializacao.codigo : ''}} 
							|- {{this.dados.especializacao ? this.dados.especializacao.nome : ''}}

		div.mb-6
			ExpansivePanel(titulo = 'Anexos')
				v-row
					v-col(cols="12", md="12")
						GridListagemInclusao.mb-4(
							:headers="headerListagem",
							:dadosListagem="this.dados.documentos",
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

					let blob = new Blob([result.data], { type: 'application/pdf' });
					const link = document.createElement('a');
					link.href = URL.createObjectURL(blob);
					link.target = '_blank';
					link.click();
					URL.revokeObjectURL(link.href);

				})
				.catch(error => {
					console.error(error.message);
				});


		},

	},
};

</script>

<style lang="less" scoped>

.spacer {
	padding-bottom: 1%;
}

</style>