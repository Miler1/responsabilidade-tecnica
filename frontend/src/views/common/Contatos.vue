<template lang="pug">

	div.mb-6
		ExpansivePanel(titulo = 'Contatos')
			v-row
				v-col(cols="12", md="6")
					v-col(cols="12")
						v-label E-mail principal:
						span &nbsp;{{this.contatos.email1}}
					v-col(cols="12")
						v-label E-mail secundário:
						span &nbsp;{{this.contatos.email2 ? this.contatos.email2 : "-"}}
					v-col(cols="12")
						v-label Celular:
						span &nbsp; {{this.contatos.cel ? prepararNumTelefone(this.contatos.cel) : "-"}}
				v-col(cols="12", md="6")
					v-col(cols="12")
						v-label Telefone residencial:
						span &nbsp; {{this.contatos.tel1 ? prepararNumTelefone(this.contatos.tel1) : "-"}}
					v-col(cols="12")
						v-label Telefone comercial:
						span &nbsp; {{this.contatos.tel2 ? prepararNumTelefone(this.contatos.tel2) : "-"}}

</template>

<script>
import DataUtils from '@/utils/dataUtils';
import ExpansivePanel from '@/components/ExpansivePanel';

export default {

	name: 'Contatos',

	components: {
		ExpansivePanel
	},

	data: () => ({
		objetoContatos: {}
	}),

	props: {
		contatos: {
			type: [Object]
		}
	},

	methods: {

		prepararNumTelefone(num) {
			return DataUtils.formatarTelefone(num);
		},

	},
};

</script>

<style lang="less" scoped>

.spacer {
	padding-bottom: 1%;
}

</style>