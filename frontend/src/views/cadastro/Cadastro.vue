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
						v-col(cols="12", md="6")
							TextField(
								labelOption = "Formação: *",
								id = "QA-input-formacao",
								@changeModel="dados.formacao = $event",
								placeholder="Digite aqui",
								:errorMessages="errorMessage"
							)
						v-col(cols="12", md="3")
							TextField(
								labelOption = "Conselho de classe: *",
								id = "QA-input-conselho-classe",
								@changeModel="dados.conselhoDeClasse = $event",
								placeholder="Digite aqui",
								:errorMessages="errorMessage"
							)
						v-col(cols="12", md="3")
							TextField(
								labelOption = "Registro: *",
								id = "QA-input-registro",
								@changeModel="dados.registro = $event",
								placeholder="Digite aqui",
								:errorMessages="errorMessage"
							)
					v-row
						v-col.py-0(cols="12", md="8")
							v-label Nível de responsabilidade técnica: *
							div
								v-radio-group#QA-radio-nivel-responsabilidade-tecnica(
									v-model="dados.nivelResponsabilidadeTecnica",
									row
								)
									v-radio(label='Consultor pessoa física' value='CONSULTORPF')
									v-radio(label='Empresa consultora' value='EMPRESA_CONSULTORA')
									v-radio(label='Funcionário' value='FUNCIONARIO')
						v-col.py-0(cols="12", md="4")
							v-label Possui vínculo com o GEA: *
							div
								v-radio-group#QA-radio-vinculo-gea(v-model="dados.possuiVinculoComGea", row)
									v-radio(label='Sim' value='true')
									v-radio(label='Não' value='false')
					v-row
						v-col.pt-0.pb-0(cols="12", md="12")
							v-label Vínculo empregatício: *
							div.d-flex.flex-row.align-baseline
								v-radio-group#QA-radio-vinculo(v-model="dados.vinculoEmpregaticio", row)
									v-radio(label='Efetivo' value='EFETIVO')
									v-radio(label='Contrato' value='CONTRATO')
									v-radio(label='Cargo comissionado' value='CARGO_COMISSIONADO')
									v-radio(label='Outro' value='OUTRO')

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

				div.px-3.pt-7
					v-btn#QA-btn-adicionar-anexo.float-right(
						color="#2196F3",
						class="text-none",
						depressed,
						outlined,
						:loading="isSelecting",
						@click="onButtonClick"
					)
						v-icon mdi-plus-circle-outline
						span Adicionar anexo
					input(
						ref="uploader",
						class="d-none",
						type="file",
						multiple,
						@change="uploadFile"
					)

					GridListagemInclusao.mt-12.mb-4(
						:headers="headerListagem",
						:dadosListagem="files",
						:hideFooter="false",
						:labelNoData="labelNoData"
					)

		div.d-flex.flex-row.justify-space-between
			v-btn#QA-btn-voltar-cadastro(@click='voltar', large, outlined)
				v-icon mdi-arrow-left
				span Voltar
			v-btn#QA-btn-cadastro-responsabilidade-tecnica(@click='salvar', large, color="#2196F3", dark)
				v-icon mdi-plus
				span Cadastrar

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
			labelNoData: "Nenhum anexo adicionado",
			headerListagem: HEADER,
			isInclusao: true,
			errorMessageEmpty: true,
			currentFile: [],
			isSelecting: false,
			files: [],
			file: null,
			url: window.location,
			pessoa: {},
			isHabilitado: false,
			especializacoes: [],
			dados: {
				formacao: null,
				conselhoDeClasse: null,
				registro: null,
				nivelResponsabilidadeTecnica: null,
				possuiVinculoComGea: null,
				vinculoEmpregaticio: null,
				outroVinculoEmpregaticio: null,
				especializacao: null
			},
		};
	},

	methods: {

		removerAnexo(item) {
			let pos = this.files.map(function(e) { return e.name; }).indexOf(item);
			let deletedFile = this.files.splice(pos, 1);
		},

		downloadAnexo() {
			console.log(window.location);
			this.$http({
				method: 'get',
				url: this.url,
				responseType: 'arraybuffer'
			})
				.then(response => {
					this.forceFileDownload(response);
				})
				.catch(() => console.log('error occured'));
		},

		forceFileDownload(response){
			console.log(response);
			const url = window.URL.createObjectURL(new Blob([response.data]));
			const link = document.createElement('a');
			link.href = url;
			link.setAttribute('download', 'file.png'); //or any other extension
			document.body.appendChild(link);
			link.click();
		},

		onButtonClick() {
			this.isSelecting = true;
			window.addEventListener('focus', () => {
				this.isSelecting = false;
			}, { once: true });

			this.$refs.uploader.click();
		},

		uploadFile(e) {
			this.files = this.files.concat([...e.target.files]);
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

		errorMessage(value) {
			return this.errorMessageEmpty || value ? '' : 'Obrigatório';
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

		handleError(erro) {
			console.log(erro);
		},

		salvar() {
			this.prepararParaSalvar();
			if (this.checkForm()) {
				console.log(this.dados);
				this.$fire({

					title:
						'<p class="title-modal-confirm">Confirmar cadastro</p>',

					html:
						`<p class="message-modal-confirm">Ao confirmar o cadastro, todas as informações serão salvas e enviadas para análise.</p>
						<p class="message-modal-confirm">
							<b>Tem certeza que deseja confirmar o cadastro? Esta opção não poderá ser desfeita e todas as informações serão salvas e enviadas para análise.</b>
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

		cancelar() {
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

<style lang="less">

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

	.v-data-footer {
		display: none;
	}

	#QA-btn-cancelar-cadastro {
		color: #2196F3;
		background-color: white;
		width: 145px;
	}

	.v-radio {
		.v-label {
			font-weight: 400;
		}
	}

}

.theme--light.v-list-item .v-list-item__mask{
	color:white;
	background: #65afef;
}

</style>