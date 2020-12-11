<template lang="pug">

	v-container#container-cadastro.pa-12.align-center.justify-center

		h1.mb-12 Cadastro de Responsabilidade Técnica Ambiental

		div.mb-6
			ExpansivePanel(titulo = 'Dados Pessoais')
				v-row(v-if="pessoa != undefined")
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
							| {{ pessoa.sexo != undefined ? pessoa.sexo.descricao : "-"}}
						v-col(cols="12")
							v-label Nome da mãe: &nbsp;
							| {{pessoa.nomeMae}}
						v-col(cols="12")
							v-label Estado Civil: &nbsp;
							| {{pessoa.estadoCivil != undefined ? pessoa.estadoCivil.descricao : "-"}}
					v-col(cols="12", md="6")
						v-col(cols="12")
							v-label Naturalidade: &nbsp;
							| {{pessoa.naturalidade}}
						v-col(cols="12")
							v-label Número do RG: &nbsp;
							| {{pessoa.rg != undefined ? pessoa.rg.numero : "-"}}
						v-col(cols="12")
							v-label Orgão Expedidor: &nbsp;
							| {{pessoa.rg != undefined ? pessoa.rg.orgaoExpedidor : '-'}}
						v-col(cols="12")
							v-label Título Eleitoral: &nbsp;
							| {{pessoa.tituloEleitoral != undefined ? pessoa.tituloEleitoral : '-'}}
						v-col(cols="12")
							v-label Zona eleitoral: &nbsp;
							| -
						v-col(cols="12")
							v-label Seção eleitoral: &nbsp;
							| -
		div.mb-6
			ExpansivePanel(titulo = 'Contato')
				v-row(v-if="pessoa.contatos != undefined")
					v-col(cols="12", md="4")
						v-col(cols="12")
							v-label E-mail principal: &nbsp;
							| {{pessoa.contatos[0].valor}}
					v-col(cols="12", md="6")
						v-col(cols="12")
							v-label E-mail secundário: &nbsp;
							| {{ pessoa.contatos[1] != undefined ? pessoa.contatos[1].valor : "-"}}
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
			ExpansivePanel(titulo = 'Endereço')
				v-row(v-if="pessoa.enderecos != undefined")
					v-col(cols="12", md="6")
						v-col(cols="12")
							v-label Zona de localização: &nbsp;
							| {{pessoa.enderecos[0].zonaLocalizacao.descricao}}
						v-col(cols="12")
							v-label CEP: &nbsp;
							| {{pessoa.enderecos[0].cep}}
						v-col(cols="12")
							v-label Logradouro: &nbsp;
							| {{pessoa.enderecos[0].logradouro}}
						v-col(cols="12")
							v-label Número: &nbsp;
							| {{pessoa.enderecos[0].numero}}
					v-col(cols="12", md="6")
						v-col(cols="12")
							v-label Complemento: &nbsp;
							| {{pessoa.enderecos[0].complemento}}
						v-col(cols="12")
							v-label Bairro: &nbsp;
							| {{pessoa.enderecos[0].bairro}}
						v-col(cols="12")
							v-label UF: &nbsp;
							| {{pessoa.enderecos[0].municipio.estado.sigla}}
						v-col(cols="12")
							v-label Município: &nbsp;
							| {{pessoa.enderecos[0].municipio.nome}}

		div.mb-6
			ExpansivePanel(titulo = 'Informações técnicas')
				v-form.px-2(ref="cadastro")
					v-row
						//- v-col.pb-0(cols="12", md="6")
						//- 	TextField(
						//- 		v-model="dados.formacao",
						//- 		labelOption = "Formação: *",
						//- 		id = "QA-input-formacao",
						//- 		placeholder="Digite aqui",
						//- 		:errorMessages="errorMessage",
						//- 		@click="resetErrorMessage"
						//- 	)
						//- v-col.pb-0(cols="12", md="3")
						//- 	TextField(
						//- 		v-model="dados.conselho",
						//- 		labelOption = "Conselho de classe: *",
						//- 		id = "QA-input-conselho-classe",
						//- 		placeholder="Digite aqui",
						//- 		:errorMessages="errorMessage",
						//- 		@click="resetErrorMessage"
						//- 	)
						//- v-col.pb-0(cols="12", md="3")
						//- 	TextField(
						//- 		v-model="dados.registro",
						//- 		labelOption = "Registro: *",
						//- 		id = "QA-input-registro",
						//- 		placeholder="Digite aqui",
						//- 		:errorMessages="errorMessage",
						//- 		@click="resetErrorMessage"
						//- 	)
						v-col.pb-0(cols="12", md="6")
							v-label Formação: *
							v-text-field#QA-input-formacao(
								outlined,
								color="#E0E0E0",
								:placeholder="placeholder",
								@click.native="resetErrorMessage",
								v-model="dados.formacao",
								:error-messages="errorMessage(dados.formacao)",
								required,
								dense
							)
						v-col.pb-0(cols="12", md="3")
							v-label Conselho de classe: *
							v-text-field#QA-input-conselho-classe(
								outlined,
								color="#E0E0E0",
								:placeholder="placeholder",
								@click.native="resetErrorMessage",
								v-model="dados.conselho",
								:error-messages="errorMessage(dados.conselho)",
								required,
								dense
							)
						v-col.pb-0(cols="12", md="3")
							v-label Registro: *
							v-text-field#QA-input-registro(
								outlined,
								color="#E0E0E0",
								:placeholder="placeholder",
								@click.native="resetErrorMessage",
								v-model="dados.registro",
								:error-messages="errorMessage(dados.registro)",
								required,
								dense
							)
					v-row
						v-col.py-0(cols="12", md="8")
							v-label Nível de responsabilidade técnica: *
							div
								v-radio-group#QA-radio-nivel-responsabilidade-tecnica(v-model="dados.responsabilidade", :errorMessages="errorMessage(dados.responsabilidade)")
									v-radio(label='Consultor pessoa física' value='CONSULTOR PESSOA FÍSICA' @click="resetErrorMessage")
									v-radio(label='Empresa consultora' value='EMPRESA CONSULTORA' @click="resetErrorMessage")
									v-radio(label='Funcionário' value='FUNCIONÁRIO' @click="resetErrorMessage")
						v-col.py-0(cols="12", md="4")
							v-label Possui vínculo com o GEA: *
							div
								v-radio-group#QA-radio-vinculo-gea(v-model="dados.vinculoGea", :errorMessages="errorMessage(dados.vinculoGea)")
									v-radio(label='Sim' value='Sim' @click="resetErrorMessage")
									v-radio(label='Não' value='Não' @click="resetErrorMessage")
					v-row
						v-col.pt-0.pb-0(cols="12", md="12")
							v-label Vínculo empregatício: *
							div.d-flex.flex-row.align-baseline
								v-radio-group#QA-radio-vinculo(v-model="dados.vinculoEmpregaticio" :errorMessages="errorMessage(dados.vinculoEmpregaticio)")
									v-radio(label='Efetivo' value='EFETIVO' @click="resetErrorMessage" @change="permiteOutroVinculo(false)")
									v-radio(label='Contrato' value='CONTRATO' @click="resetErrorMessage" @change="permiteOutroVinculo(false)")
									v-radio(label='Cargo comissionado' value='CARGO COMISSIONADO' @click="resetErrorMessage" @change="permiteOutroVinculo(false)")
									v-radio(label='Outro' value=' ' @click="resetErrorMessage" @change="permiteOutroVinculo(true)")

								v-text-field#QA-input-outro-vinculo(
									v-model="dados.outroVinculoEmpregaticio",
									:errorMessages="errorMessage(dados.outroVinculoEmpregaticio)",
									color="#E0E0E0",
									:placeholder="placeholder",
									required,
									outlined,
									dense,
									:disabled="!isHabilitado"
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
			ExpansivePanel(titulo = 'Anexos')
				GridListagemInclusao(
					:headers="headerListagem",
					:dadosListagem="files",
				)

				//- v-file-input(
				//- 	v-model="currentFile",
				//-     outlined,
				//-     dense,
				//-     multiple,
				//-     hide-input,
				//-     counter,
				//-     chips,
				//-     prepend-icon="mdi-camera"
				//-     @change="uploadFile()"
				//- )

				v-file-input#QA-input-file(input-file__input
					v-model="file",
					ref='file',
					type='file',
					color="#E0E0E0",
					outlined,
					dense,
					@click="selectFile()"
				)

				//- div.input-file
				//- 	input.input-file__input(ref='file' type='file')
				//- 	    div.input-file__button(@click='selectFile()') click to select a file

				div.mt-6(style="height: 50px;")
					v-btn#QA-btn-adicionar-anexo.float-left(@click="incluirDados", large, v-if="isInclusao")
						v-icon mdi-plus-circle-outline
						span Adicionar anexo

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
import ExpansivePanel from '@/components/ExpansivePanel';
import GridListagemInclusao from '@/components/GridListagemInclusao';
import TextField from '@/components/TextField';
import { HEADER } from '@/utils/dadosHeader/ListagemAnexoInclusao';

export default {
	name: 'Cadastro',

	components: {
		ExpansivePanel,
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
			errorMessageEmpty: true,
			currentFile: [],
			files: [],
			file: null,
			pessoa: {},
			isHabilitado: false,
			especializacoes: [],
			dados: {
				formacao: null,
				conselho: null,
				registro: null,
				responsabilidade: null,
				vinculoGea: null,
				vinculoEmpregaticio: null,
				outroVinculoEmpregaticio: null,
				especializacao: null
			},
		};
	},

	methods: {

		incluirDados() {

		},

		selectFile(){
			let fileInputElement = this.$refs.file;
			console.log(fileInputElement);
			// fileInputElement.click();
			this.file = this.$refs.file;
		},

		uploadFile() {
			console.log(this.currentFile);
			this.files = [...this.currentFile, ...this.files];
		},

		errorMessage(value) {
			return this.errorMessageEmpty || value ? '' : 'Obrigatório';
		},

		checkForm() {

			return this.dados.formacao !== null
				&& this.dados.conselho !== null
				&& this.dados.registro !== null
				&& this.dados.responsabilidade !== null
				&& this.dados.vinculoGea !== null
				&& (this.dados.vinculoEmpregaticio !== null || this.dados.outroVinculoEmpregaticio !== null)
				&& (this.dados.vinculoEmpregaticio !== "" || this.dados.outroVinculoEmpregaticio !== "")
				&& this.dados.vinculoEmpregaticio != " "
				&& this.dados.especializacao !== null;

		},

		clear() {

			this.dados.formacao = null;
			this.dados.conselho = null;
			this.dados.registro = null;
			this.dados.responsabilidade = null;
			this.dados.vinculoGea = null;
			this.dados.vinculoEmpregaticio = null;
			this.dados.outroVinculoEmpregaticio = null;
			this.dados.especializacao = null;
			this.errorMessageEmpty = true;
			// this.resetaDadosCadastro();

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
			this.errorMessageEmpty = true;
		},

		permiteOutroVinculo(isChecked) {

			if (!isChecked) {
				this.dados.outroVinculoEmpregaticio = '';
			}

			this.isHabilitado = isChecked;

		},

		prepararParaSalvar() {
			if (this.dados.vinculoEmpregaticio == " " && this.dados.outroVinculoEmpregaticio != null) {
				this.dados.vinculoEmpregaticio = this.dados.outroVinculoEmpregaticio;
			}
			console.log(this.dados.vinculoEmpregaticio);
			console.log(this.dados);

		},

		salvar() {
			this.prepararParaSalvar();
			if (this.checkForm()) {
				console.log(this.dados);
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
					confirmButtonText: '<i class="fa fa-check-circle"></i> Confirmar',
					cancelButtonText: '<i class="fa fa-close"></i> Cancelar',
					reverseButtons: true

				}).then((result) => {

					if(result.value) {
						//item.ativo = !item.ativo;
						AtividadeService.ativarDesativarAtividadeDispensavel(this.dados)
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

			} else {
				window.scrollTo(0, 0);
				this.errorMessageEmpty = false;
			}

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

	#QA-btn-adicionar-anexo {
		border-radius: 30px;
	}

	.input-file {
		&__input {
			visibility: hidden;
		}

		&__button{
			background-color: red;
			width: 200px;
			padding: 10px;
			border-radius: 4px;
			text-align: center;
			cursor: pointer;

			&:hover{
				background-color: #000;
				color: #fff;
			}
		}
	}

}

</style>