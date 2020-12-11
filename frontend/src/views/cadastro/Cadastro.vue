<template lang="pug">

	v-container#container-cadastro.pa-12.align-center.justify-center

		h1.mb-12 Cadastro de Responsabilidade Técnica Ambiental

		div.mb-6
			expansivePanel(titulo = 'Dados pessoais')
				v-row
					v-col(cols="12", md="6")
						v-col(cols="12")
							v-label Nome completo: &nbsp;
							| {{pessoa.nome}}
						v-col(cols="12")
							v-label CPF: &nbsp;
							| {{pessoa.cpf}}
						v-col(cols="12")
							v-label Data de nascimento: &nbsp;
							| {{pessoa.dataNascimento}}
						v-col(cols="12")
							v-label Sexo: &nbsp;
							| {{'Masculino'}}
						v-col(cols="12")
							v-label Nome da mãe: &nbsp;
							| {{pessoa.nomeMae}}
						v-col(cols="12")
							v-label Estado civil: &nbsp;
							| {{'Solteiro'}}
					v-col(cols="12", md="6")
						v-col(cols="12")
							v-label Naturalidade: &nbsp;
							| Belo Horizonte
						v-col(cols="12")
							v-label Número do RG: &nbsp;
							| 11-111.111
						v-col(cols="12")
							v-label Órgão expedidor: &nbsp;
							| -
						v-col(cols="12")
							v-label Título eleitoral: &nbsp;
							| 111111111111111
						v-col(cols="12")
							v-label Zona eleitoral: &nbsp;
							| -
						v-col(cols="12")
							v-label Seção eleitoral: &nbsp;
							| -
		div.mb-6
			expansivePanel(titulo = 'Contatos')
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
							v-label Telefone residencial: &nbsp;
							| 35-99812-7151
					v-col(cols="12", md="4")
						v-col(cols="12")
							v-label Telefone comercial: &nbsp;
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
			expansivePanel(titulo = 'Informações técnicas')
				v-form.px-2(ref="cadastro")
					v-row
						v-col.pb-0(cols="12", md="6")
							v-label Formação: *
							v-select#QA-select-input-formacao(
								outlined,
								no-data-text="Nenhuma formação encontrada",
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
								v-radio-group#QA-radio-vinculo-gea(v-model='row1' row1='')
									v-radio(label='Sim' value='radio-1')
									v-radio(label='Não' value='radio-2')
					v-row
						v-col.pt-0.pb-0(cols="12", md="12")
							v-label Vínculo empregatício: *
							div.d-flex.flex-row.align-baseline
								v-radio-group#QA-radio-vinculo(v-model='row2' row2='')
									v-radio(label='Efetivo' value='radio-1')
									v-radio(label='Contrato' value='radio-2')
									v-radio(label='Cargo comissionado' value='radio-3')
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
							v-label Área de especialização: *
							div.d-flex.flex-row.align-baseline
								v-autocomplete#QA-select-area-especializacao(
									outlined,
									dense,
									:placeholder="placeholderSelect"
									item-color="grey darken-3",
									v-model="dados.especializacao",
									:items="especializacoes",
									:filter="filtroSelect",
									item-text="textoExibicao",
									:error-messages="errorMessage(dados.especializacao)",
									no-data-text="Nenhuma área de especialização encontrada",
									@click.native="resetErrorMessage",
									required,
									return-object=true
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
					v-icon mdi-plus
					span Cadastrar
			v-btn#QA-btn-voltar-cadastro.float-left(@click='voltar', large, outlined)
					v-icon mdi-arrow-left
					span Voltar

</template>

<script>

import PessoaService from '@/services/pessoa.service';
import EspecializacaoTecnicaService from '@/services/especializacaoTecnica.service';
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
			placeholderSelect: "Selecione",
			headerListagem: HEADER,
			isInclusao: true,
			currentFile: [],
			files: [],
			row: null,
			pessoa: {},
			especializacoes: [],
			dados: {
				especializacao: null
			},
			row1: null,
			row2: null,
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

		filtroSelect(item, query, itemText) {

			query = this.normalizer(query);
			itemText = itemText ? this.normalizer(itemText) : itemText;

			return itemText.toLocaleLowerCase().indexOf(query.toLocaleLowerCase()) > -1;

		},

		normalizer(string) {
			return string.toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, "");
		},

		resetErrorMessage() {

		},

		salvar(item) {
			this.$fire({

				title:
					'<p class="title-modal-confirm">Cadastro de responsabilidade técnica </p>',

				html:
					`<p class="message-modal-confirm">Ao salvar o cadastro, ele ficará disponível no sistema.</p>
					<p class="message-modal-confirm">
						<b>Tem certeza que deseja salvar as informações do cadastro?</b>
					</p>`,
				showCancelButton: true,
				confirmButtonColor: item.ativo ? '#E6A23C' : '#67C23A',
				cancelButtonColor: '#FFF',
				showCloseButton: true,
				focusConfirm: false,
				confirmButtonText: '<i class="mdi mdi-check-circle"></i> Confirmar',
				cancelButtonText: '<i class="mdi mdi-close"></i> Cancelar',
				reverseButtons: true

			}).then((result) => {

				if(result.value) {
					item.ativo = !item.ativo;
					AtividadeService.ativarDesativarAtividadeDispensavel(item.id)
						.then(() => {

							if (item.ativo) {
								snackbar.alert(SUCCESS_MESSAGES.atividadeDispensavel.ativar, snackbar.type.SUCCESS);
							} else {
								snackbar.alert(SUCCESS_MESSAGES.atividadeDispensavel.desativar, snackbar.type.SUCCESS);
							}

							// this.updatePagination();
							// this.resetaDadosFiltragem();

						})
						.catch(error => {

							console.error(error);

							if (item.ativo) {
								snackbar.alert(ERROR_MESSAGES.atividadeDispensavel.ativar);
							} else {
								snackbar.alert(ERROR_MESSAGES.atividadeDispensavel.desativar);
							}

							item.ativo = !item.ativo;

						});

				}

			}).catch((error) => {
				console.error(error);
			});

		},

		voltar() {
			this.$router.push('/user');
		},

	},

	created() {

		PessoaService.buscaPessoalogada()
			.then((result) => {
				this.pessoa = result.data;
			})
			.catch(erro => {
				this.handleError(erro);
			});
		
		EspecializacaoTecnicaService.buscaEspecializacoesTecnicas()
			.then((result) => {
				this.especializacoes = result.data;
				this.especializacoes.forEach(e => e.textoExibicao = e.codigo + ' - ' + e.nome);
			}).catch(erro => {
				this.handleError(erro);
			});
	}
};
</script>

<style lang="less" scoped>

@import "../assets/css/variaveis.less";

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
		color: @blue-primary;
        background-color: white;
        width: 145px;
	}

}

</style>