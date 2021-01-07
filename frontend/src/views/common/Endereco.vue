<template lang="pug">

	div.mb-6
		ExpansivePanel(titulo = 'Endereço')
			v-row(v-if="enderecos")
				v-col(cols="12", md="6")
					v-col(cols="12")
						v-label Zona de localização:
						span &nbsp; {{enderecos[0].zonaLocalizacao.descricao}}
					v-col(cols="12")
						v-label CEP:
						span &nbsp; {{prepararCep(enderecos[0].cep)}}
					v-col(cols="12")
						v-label Logradouro:
						span &nbsp; &nbsp;{{enderecos[0].logradouro}}
					v-col(cols="12")
						v-label Número:
						span &nbsp; {{enderecos[0].numero ? enderecos[0].numero : "-"}}
				v-col(cols="12", md="6")
					v-col(cols="12")
						v-label Complemento:
						span &nbsp; {{enderecos[0].complemento ? enderecos[0].complemento : "-" }}
					v-col(cols="12")
						v-label Bairro:
						span &nbsp; {{enderecos[0].bairro}}
					v-col(cols="12")
						v-label UF:
						span &nbsp; {{enderecos[0].municipio.estado.sigla}}
					v-col(cols="12")
						v-label Município:
						span &nbsp; {{enderecos[0].municipio.nome}}

</template>

<script>
import DataUtils from '@/utils/dataUtils';
import ExpansivePanel from '@/components/ExpansivePanel';

export default {

	name: 'Endereco',

	components: {
		ExpansivePanel
	},

	props: {
		enderecos: {
			type: [Array]
		}
	},

	methods: {

		prepararCep(cep) {
			return DataUtils.formatarCep(cep);
		},

		prepararData(milisegundos) {
			return DataUtils.formatarData(milisegundos);
		},
	}
};

</script>

<style lang="less">

.spacer {
	padding-bottom: 1%;
}

</style>