<template lang="pug">

	div.mb-6
		ExpansivePanel(titulo = 'Dados pessoais')
			v-row(v-if="pessoa != undefined")
				v-col(cols="12", md="6")
					v-col(cols="12")
						v-label Nome completo:
						span &nbsp;{{pessoa.nome}}
					v-col(cols="12")
						v-label CPF:
						span &nbsp; {{prepararCpf()}}
					v-col(cols="12")
						v-label Data de nascimento:
						span &nbsp; {{prepararData(pessoa.dataNascimento)}}
					v-col(cols="12")
						v-label Sexo:
						span &nbsp;{{ pessoa.sexo ? pessoa.sexo.descricao : "-"}}
					v-col(cols="12")
						v-label Nome da mãe:
						span &nbsp;{{pessoa.nomeMae}}
					v-col(cols="12")
						v-label Estado civil:
						span &nbsp;{{pessoa.estadoCivil ? pessoa.estadoCivil.descricao : "-"}}
				v-col(cols="12", md="6")
					v-col(cols="12")
						v-label Naturalidade:
						span &nbsp;{{pessoa.naturalidade ? pessoa.naturalidade : '-'}}
					v-col(cols="12")
						v-label Número do RG:
						span &nbsp;{{pessoa.rg && pessoa.rg.numero  ? pessoa.rg.numero : "-"}}
					v-col(cols="12")
						v-label Órgão expedidor:
						span &nbsp;{{pessoa.rg && pessoa.rg.orgaoExpedidor ? pessoa.rg.orgaoExpedidor : '-'}}
					v-col(cols="12")
						v-label Título eleitoral:
						span &nbsp;{{pessoa.tituloEleitoral ? pessoa.tituloEleitoral.numero : '-'}}
					v-col(cols="12")
						v-label Zona eleitoral:
						span &nbsp;{{pessoa.tituloEleitoral ? pessoa.tituloEleitoral.zona : '-'}}
					v-col(cols="12")
						v-label Seção eleitoral:
						span &nbsp;{{pessoa.tituloEleitoral ? pessoa.tituloEleitoral.secao : '-'}}

</template>

<script>
import DataUtils from '@/utils/dataUtils';
import ExpansivePanel from '@/components/ExpansivePanel';

export default {

	name: 'DadosPessoais',

	components: {
		ExpansivePanel
	},

	props: {
		pessoa: {
			type: [Object]
		}
	},

	methods: {

		prepararCpf() {

			if (this.pessoa.cpf) {
				return DataUtils.formatarCpf(this.pessoa.cpf);
			}

		},

		prepararData(milisegundos) {
			return DataUtils.formatarData(milisegundos);
		},
	}
};

</script>

<style lang="less" scoped>

.spacer {
	padding-bottom: 1%;
}

</style>