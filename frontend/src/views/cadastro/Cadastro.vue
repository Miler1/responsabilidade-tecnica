<template lang="pug">

	v-container#container-cadastro.pa-12.align-center.justify-center

		h1.mb-12 Cadastro de Responsabilidade Técnica Ambiental

		div.mb-6
			expansivePanel(titulo = 'Dados Pessoais')
				v-row
					v-col(cols="12", md="6")
						v-col(cols="12")
							v-label Nome Completo: &nbsp;
							| {{"Pessoa 1"}}
						v-col(cols="12")
							v-label CPF: &nbsp;
							| 111.111.111-11
						v-col(cols="12")
							v-label Data de Nascimento: &nbsp;
							| 01/01/0001
						v-col(cols="12")
							v-label Sexo: &nbsp;
							| Masculino:
						v-col(cols="12")
							v-label Nome da mãe: &nbsp;
							| Nome da mãe
						v-col(cols="12")
							v-label Estado Civil: &nbsp;
							| Solteiro
					v-col(cols="12", md="6")
						v-col(cols="12")
							v-label Naturalidade: &nbsp;
							| Belo Horizonte
						v-col(cols="12")
							v-label Número do RG: &nbsp;
							| 11-111.111
						v-col(cols="12")
							v-label Orgão Expedidor: &nbsp;
							| -
						v-col(cols="12")
							v-label Título Eleitoral: &nbsp;
							| 111111111111111
						v-col(cols="12")
							v-label Zona Eleitoral: &nbsp;
							| -
						v-col(cols="12")
							v-label Seção Eleitoral: &nbsp;
							| -
		div.mb-6
			expansivePanel(titulo = 'Contato')
				v-row
					v-col(cols="12", md="4")
						v-col(cols="12")
							v-label E-mail principal: &nbsp;
							| {{"miler@xxxxx"}}
					v-col(cols="12", md="6")
						v-col(cols="12")
							v-label E-mail secundário: &nbsp;
							| xxxxxxxxxxx@xxxx
				v-row
					v-col(cols="12", md="4")
						v-col(cols="12")
							v-label Celular: &nbsp;
							| 35-99812-7151
					v-col(cols="12", md="4")
						v-col(cols="12")
							v-label Telefone Residencial: &nbsp;
							| 35-99812-7151
					v-col(cols="12", md="4")
						v-col(cols="12")
							v-label Telefone Comercial: &nbsp;
							| 35-99812-7151
		div.mb-6
			expansivePanel(titulo = 'Endereço')
				v-row
					v-col(cols="12", md="6")
						v-col(cols="12")
							v-label Zona de localização: &nbsp;
							| Zona Urbana
						v-col(cols="12")
							v-label CEP: &nbsp;
							| 33333-333
						v-col(cols="12")
							v-label Logradouro: &nbsp;
							| xxxxxxxxxxx
						v-col(cols="12")
							v-label Número: &nbsp;
							| 192
					v-col(cols="12", md="6")
						v-col(cols="12")
							v-label Complemento: &nbsp;
							| dfefest
						v-col(cols="12")
							v-label Bairro: &nbsp;
							| kgrogkrop
						v-col(cols="12")
							v-label UF: &nbsp;
							| fjetjpotg
						v-col(cols="12")
							v-label Município: &nbsp;
							| feetetetey

		div.mb-6
			expansivePanel(titulo = 'Informações Técnicas')
				v-form.px-2(ref="cadastro")
					v-row
						v-col.pb-0(cols="12", md="6")
							v-label Formação: *
							v-select#QA-select-input-formacao(
								outlined,
								dense
							)
						v-col.pb-0(cols="12", md="3")
							TextField(
								labelOption = "Conselho de classe: *",
								id = "QA-input-conselho-classe",
								placeholder="Digite aqui",
								:errorMessages="errorMessage"
							)
						v-col.pb-0(cols="12", md="3")
							TextField(
								labelOption = "Registro: *",
								id = "QA-input-registro",
								placeholder="Digite aqui",
								:errorMessages="errorMessage"
							)
					v-row
						v-col.py-0(cols="12", md="8")
							v-label Nível de responsabilidade técnica: *
							div
								v-radio-group#QA-radio-nivel-responsabilidade-tecnica(v-model='row' row='')
									v-radio(label='Consultor pessoa física' value='radio-1')
									v-radio(label='Empresa consultora' value='radio-2')
									v-radio(label='Funcionário' value='radio-3')
						v-col.py-0(cols="12", md="4")
							v-label Possui vínculo com o GEA: *
							div
								v-radio-group#QA-radio-vinculo-gea(v-model='row' row='')
									v-radio(label='Sim' value='radio-1')
									v-radio(label='Não' value='radio-2')
					v-row
						v-col.pt-0.pb-0(cols="12", md="12")
							v-label Qual o vínculo: *
							div.d-flex.flex-row.align-baseline
								v-radio-group#QA-radio-vinculo(v-model='row' row='')
									v-radio(label='Efeito' value='radio-1')
									v-radio(label='Contrato' value='radio-2')
									v-radio(label='Cargo Comissionado' value='radio-3')
									v-radio(label='Outro' value='radio-4')

								v-text-field#QA-input-outro-vinculo(
									color="#E0E0E0",
									:placeholder="placeholder",
									required,
									outlined,
									dense
								)
					v-row
						v-col(cols="12", md="12")
							v-label Qual a área de especialização: *
							div.d-flex.flex-row.align-baseline
								v-select#QA-select-area-especializacao(
									outlined,
									dense
								)
		div.mb-6
			expansivePanel(titulo = 'Anexos')
				v-file-input(
					v-model="currentFile",
				    outlined,
				    dense,
				    multiple,
				    counter,
				    chips,
				    @change="uploadFile()"
				)
				div.mt-6(style="height: 50px;")
					v-btn#QA-btn-adicionar-anexo.float-right(@click="incluirDados", large, v-if="isInclusao")
						v-icon mdi-plus-circle-outline
						span Adicionar anexo

				GridListagemInclusao(
					:headers="headerListagem",
					:dadosListagem="files",
				)

		div.px-5
			v-btn#QA-btn-cadastro-responsabilidade-tecnica.float-right(@click='salvar', large)
					span Cadastrar
			v-btn#QA-btn-voltar-cadastro.float-left(@click='voltar', large, outlined)
					span Voltar

</template>

<script>
import expansivePanel from '@/components/expansivePanel';
import GridListagemInclusao from '@/components/GridListagemInclusao';
import TextField from '@/components/TextField';
import { HEADER } from '@/utils/dadosHeader/ListagemAnexoInclusao';

export default {
	name: 'Cadastro',

	components: {
		expansivePanel,
		GridListagemInclusao,
		TextField
	},

	data: () => {

		return {
			placeholder: "Digite aqui...",
			labelNoData: 'Não há nenhum anexo a ser exibido',
			headerListagem: HEADER,
			isInclusao: true,
			currentFile: [],
			files: [],
			row: null,
			dadosListagem: [
				{
					nome: 'Arquivo 1'
				},
				{
					nome: 'Arquivo 2'
				}
			]
		};
	},

	methods: {
		incluirDados() {

		},
		uploadFile() {
			console.log(this.currentFile);
			this.files = [...this.currentFile];
		},
		errorMessage() {

		},
		salvar() {

		},
		voltar() {
			this.$router.push('/user');
		}
	}
};
</script>

<style lang="less" scoped>

#container-cadastro {

	.v-label {
		color: #333;
		font-weight: bold;
		font-size: 16px;
	}

	span {
		font-weight: 400;
		font-size: 16px;
	}


	.col-dados-pessoais > .v-label{
		padding: 10px 0;
	}

	.v-btn {
		color: white;
        background-color: #2196F3;
	}

	.v-data-footer {
		display: none;
	}

	#QA-btn-voltar-cadastro {
		color: #2196F3;
        background-color: #E0E0E0;
        width: 121px;
	}

}

</style>