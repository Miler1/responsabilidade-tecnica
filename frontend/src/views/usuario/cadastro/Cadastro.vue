<template lang="pug">

	v-container#container-cadastro.pa-12.align-center.justify-center

		h1.mb-4 {{isInclusao ? 'Cadastro' : 'Edição'}} de Responsabilidade Técnica Ambiental

		DadosPessoais(:pessoa="pessoa")

		Contatos(:contatos="{...contatos}")

		Endereco(:enderecos="pessoa.enderecos")

		div.mb-6
			ExpansivePanel(titulo = 'Informações técnicas')
				v-form.pa-2(ref="cadastro")
					v-row
						v-col(cols="12", md="6")
							TextField(
								ref="textFieldFormacao",
								v-model="dados.formacao",
								labelOption="Formação:",
								id="QA-input-formacao",
								@changeModel="dados.formacao = $event",
								placeholder="Digite aqui",
								:errorMessages="errorMessage",
								@click.native="resetErrorMessage"
							)
						v-col(cols="12", md="3")
							TextField(
								ref="textFieldConcelhoDeClasse",
								v-model="dados.conselhoDeClasse",
								labelOption="Conselho de classe:",
								id="QA-input-conselho-classe",
								@changeModel="dados.conselhoDeClasse = $event",
								placeholder="Digite aqui",
								:errorMessages="errorMessage",
								@click.native="resetErrorMessage"
							)
						v-col(cols="12", md="3")
							TextField(
								ref="textFieldRegistro",
								v-model="dados.registro",
								labelOption="Registro:",
								id="QA-input-registro",
								@changeModel="dados.registro = $event",
								placeholder="Digite aqui",
								:errorMessages="errorMessage",
								@click.native="resetErrorMessage"
							)
					v-row
						v-col.py-0(cols="12", md="8")
							v-label Nível de responsabilidade técnica:
							v-radio-group#QA-radio-nivel-responsabilidade-tecnica.d-flex.align-center(
								v-model="dados.nivelResponsabilidadeTecnica",
								:errorMessages="errorMessage(dados.nivelResponsabilidadeTecnica)",
								row
							)
								v-radio(label='Consultor pessoa física', value='CONSULTORPF', color="#327C32")
								v-radio(label='Empresa consultora', value='EMPRESA_CONSULTORA', color="#327C32")
								v-radio(label='Funcionário', value='FUNCIONARIO', color="#327C32")
						v-col.py-0(cols="12", md="4")
							v-label Possui vínculo com o GEA:
							v-radio-group#QA-radio-vinculo-gea.d-flex.align-center(v-model="dados.possuiVinculoComGea", :errorMessages="errorMessage(dados.possuiVinculoComGea)", row)
								v-radio(label='Sim' value='true', color="#327C32")
								v-radio(label='Não' value='false', color="#327C32")
					v-row(v-if="dados.possuiVinculoComGea != null && dados.possuiVinculoComGea === 'true'")
						v-col.pb-0(cols="12")
							v-label Vínculo empregatício:
							div.d-flex.flex-row
								v-radio-group#QA-radio-vinculo.d-flex.align-center(v-model="dados.vinculoEmpregaticio", @change="permiteOutroVinculo()", :errorMessages="errorMessage(dados.vinculoEmpregaticio)", row)
									v-radio(label='Efetivo' value='EFETIVO', color="#327C32")
									v-radio(label='Contrato' value='CONTRATO', color="#327C32")
									v-radio(label='Cargo comissionado' value='CARGO_COMISSIONADO', color="#327C32")
									v-radio(label='Outro' value='OUTRO', color="#327C32" )

								v-text-field.mt-5#QA-input-outro-vinculo(
									v-if="dados.vinculoEmpregaticio === 'OUTRO'"
									v-model="dados.outroVinculoEmpregaticio",
									:errorMessages="errorMessage(dados.outroVinculoEmpregaticio)",
									color="#E0E0E0",
									:placeholder="placeholder",
									required,
									outlined,
									dense
								)
					v-row
						v-col(cols="12")
							v-label Área de especialização:
							v-autocomplete#QA-select-area-especializacao(
								outlined,
								dense,
								:placeholder="placeholderSelect",
								color="#E0E0E0",
								item-color="green darken-3",
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

				v-row.pa-3
					v-col(cols="12")
						v-btn#QA-btn-adicionar-anexo.float-right(
							color="#327C32",
							class="text-none",
							outlined,
							:loading="isSelecting",
							@click="onButtonClick"
						)
							v-icon mdi-plus
							span Adicionar anexo
						input(
							ref="uploader",
							accept=filesAccept,
							class="d-none",
							type="file",
							multiple,
							required,
							:error-messages="errorMessage(files)",
							@change="uploadFile"
						)

						div.message-erro(
							v-if="files.length == 0 && !errorMessageEmpty")
								| Obrigatório
						div.message-erro(
							v-if="excedeuTamanhoMaximoArquivo")
								| Erro! Tamanho do arquivo inválido. O arquivo deve conter menos de 10MB

					v-col(cols="12")
						GridListagemInclusao(
							:headers="headerListagem",
							:dadosListagem="files",
							:labelNoData="labelNoData",
							:removerAnexo="removerAnexo",
							:downloadAnexo="downloadAnexo"
						)

		div.d-flex.flex-row.justify-space-between
			v-btn#QA-btn-cancelar-cadastro(@click='cancelar', large, outlined, color="#327C32", width="145px")
				v-icon mdi-close
				span Cancelar
			v-btn#QA-btn-cadastro-responsabilidade-tecnica(v-if="isInclusao", @click='salvar', large, color="#327C32", dark)
				v-icon mdi-check
				span Finalizar e enviar
			v-btn#QA-btn-editar-responsabilidade-tecnica(v-if="!isInclusao", @click='salvar', large, color="#327C32", dark)
				v-icon mdi-floppy
				span Salvar e enviar

</template>

<script>

import DataUtils from '@/utils/dataUtils';

import PessoaService from '@/services/pessoa.service';
import EspecializacaoTecnicaService from '@/services/especializacaoTecnica.service';
import ResponsavelTecnicoService from '@/services/responsavelTecnico.service';
import snackbar from '@/services/snack.service';

import ExpansivePanel from '@/components/ExpansivePanel';
import GridListagemInclusao from '@/components/GridListagemInclusao';
import TextField from '@/components/TextField';
import DadosPessoais from '@/views/common/DadosPessoais';
import Contatos from '@/views/common/Contatos';
import Endereco from '@/views/common/Endereco';
import Status from '@/enums/statusEnum';

import { HEADER } from '@/utils/dadosHeader/ListagemAnexoInclusao';
import { ERROR_MESSAGES, SUCCESS_MESSAGES } from '@/utils/helpers/messages-utils';

export default {
	name: 'Cadastro',

	components: {
		ExpansivePanel,
		GridListagemInclusao,
		TextField,
		DadosPessoais,
		Contatos,
		Endereco
	},

	data: () => {

		return {
			placeholder: "Digite aqui...",
			placeholderSelect: "Selecione",
			labelNoData: 'Não existem anexos adicionados. Realize a inclusão através do botão "Adicionar anexo".',
			filesAccept: "image/jpg, image/jpeg, image/bmp, image/tiff, image/png, application/pdf",
			headerListagem: HEADER,
			isInclusao: true,
			errorMessageEmpty: true,
			isSelecting: false,
			files: [],
			row: null,
			mimetype: null,
			excedeuTamanhoMaximoArquivo: false,
			totalPermitido: 10000000,
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
				especializacao: null,
				pessoaFisica: null,
				documentos: null
			},
			contatos: {},
			pessoa: {}
		};
	},

	methods: {

		downloadAnexo(item) {

			if (item.hash != null) {
				ResponsavelTecnicoService.download(item.hash)
					.then((result) => {
						let blob = DataUtils.b64ToBlob(result.data, result.headers['content-type']);
						const link = document.createElement('a');
						link.href = URL.createObjectURL(blob);
						link.download = item.name;
						link.target = '_blank';
						link.click();
						URL.revokeObjectURL(link.href);
					});
			} else {

				const link = document.createElement('a');
				link.href = URL.createObjectURL(item);
				link.download = item.name;
				link.target = '_blank';
				link.click();
				URL.revokeObjectURL(link.href);

			}

		},

		prepararNumTelefone(numTelefone) {
			return DataUtils.formatarTelefone(numTelefone);
		},

		removerAnexo(item) {
			let pos = this.files.map(function(e) { return e.name; }).indexOf(item.name);
			let deletedFile = this.files.splice(pos, 1);
		},

		onButtonClick() {
			this.isSelecting = true;

			window.addEventListener('focus', () => {
				this.isSelecting = false;
			}, { once: true });

			this.$refs.uploader.click();

		},

		checaTamanhoArquivo(item) {

			if (item.size > this.totalPermitido) {
				return true;
			}

		},

		uploadFile(e) {

			var invalido = false;
			var tamanhoinvalido = false;

			e.target.files.forEach(file => {

				if (file.type == '' || !this.filesAccept.includes(file.type)) {
					invalido = true;
				}

				if (this.checaTamanhoArquivo(file)) {
					tamanhoinvalido = true;
				}

			});

			if (invalido) {

				snackbar.alert(ERROR_MESSAGES.tipoInvalido);
				return;

			}

			if (tamanhoinvalido) {

				this.excedeuTamanhoMaximoArquivo = true;
				return;

			} else {
				this.excedeuTamanhoMaximoArquivo = false;
			}

			this.files = this.files.concat([...e.target.files]);

		},

		checkForm() {

			if (this.dados.possuiVinculoComGea === 'false') {

				return this.dados.formacao !== null
					&& this.dados.formacao !== ''
					&& this.dados.conselhoDeClasse !== null
					&& this.dados.conselhoDeClasse !== ''
					&& this.dados.registro !== null
					&& this.dados.registro !== ''
					&& this.dados.nivelResponsabilidadeTecnica !== null
					&& this.dados.possuiVinculoComGea !== null
					&& this.dados.especializacao !== null
					&& this.files.length > 0;
			}

			return this.dados.formacao !== null
				&& this.dados.formacao !== ''
				&& this.dados.conselhoDeClasse !== null
				&& this.dados.conselhoDeClasse !== ''
				&& this.dados.registro !== null
				&& this.dados.registro !== ''
				&& this.dados.nivelResponsabilidadeTecnica !== null
				&& this.dados.possuiVinculoComGea !== null
				&& this.dados.vinculoEmpregaticio !== null
				&& (this.dados.vinculoEmpregaticio !== 'OUTRO'
					|| this.dados.outroVinculoEmpregaticio !== null
					&& this.dados.outroVinculoEmpregaticio !== "")
				&& this.dados.especializacao !== null
				&& this.files.length > 0;

		},

		checkFormReverse() {

			return (this.dados.formacao == null || this.dados.formacao == '')
				&& (this.dados.conselhoDeClasse == null || this.dados.conselhoDeClasse == '')
				&& (this.dados.registro == null || this.dados.registro == '')
				&& this.dados.nivelResponsabilidadeTecnica == null
				&& this.dados.possuiVinculoComGea == null
				&& this.dados.especializacao == null
				&& this.files.length == 0;

		},

		clear() {

			this.dados.formacao = null;
			this.dados.conselhoDeClasse = null;
			this.dados.registro = null;
			this.dados.nivelResponsabilidadeTecnica = null;
			this.dados.possuiVinculoComGea = null;
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

		handleError(error) {
			console.error(error);
		},

		prepararParaSalvar() {

			this.dados.justificativa = null;

			if (!this.isInclusao) {
				this.dados.status.codigo = Status.AGUARDANDO_ANALISE;
			}

			this.dados.possuiVinculoComGea = this.dados.possuiVinculoComGea === 'true' ? true : false;
			delete this.dados.especializacao.textoExibicao;

		},

		salvarArquivos() {

			this.files.forEach(file => {

				let formData = new FormData();
				formData.append('file', file);

				ResponsavelTecnicoService.upload(formData)
					.catch(error => {
						console.error(error);
					});

			});

		},

		editarArquivos(item) {

			ResponsavelTecnicoService.apagarArquivos(item)
				.catch(error => {
					console.log(error);
				});

			this.files.forEach(file => {

				let formData = new FormData();

				formData.append('file', file);

				ResponsavelTecnicoService.upload(formData)
					.catch(error => {
						console.error(error);
					});

			});

		},

		salvar() {

			let acao = {};

			acao.confirmar = (result) => {

				if (result.value) {

					var that = this;

					that.prepararParaSalvar();

					if (this.isInclusao) {

						ResponsavelTecnicoService.salvarSolicitacao(that.dados)
							.then(() => {

								that.salvarArquivos();
								snackbar.alert(SUCCESS_MESSAGES.responsavelTecnico.cadastro, snackbar.type.SUCCESS);
								that.$router.push({name: 'Usuario'});

							})
							.catch(error => {
								console.error(error);
							});

					} else {

						ResponsavelTecnicoService.editarSolicitacao(that.dados)
							.then(() => {

								that.editarArquivos(that.dados);
								snackbar.alert(SUCCESS_MESSAGES.responsavelTecnico.edicao, snackbar.type.SUCCESS);
								that.$router.push({name: 'Usuario'});

							})
							.catch(error => {
								console.error(error);
							});

					}

				}

			};

			this.modalSalvar(acao);

		},

		modalSalvar(acao) {

			if (this.checkForm()) {

				this.$fire({

					title:
						`<p class="title-modal-confirm">Confirmar ` + (this.isInclusao ? 'cadastro' : 'edição') + `</p>`,
					html:
						`<p class="message-modal-confirm">Ao confirmar ` + (this.isInclusao ? ' o cadastro' : ' a edição') + `, todas as informações serão salvas e enviadas para análise.</p>
						<p class="message-modal-confirm">
							<b>Tem certeza que deseja confirmar ` + (this.isInclusao ? 'o cadastro' : 'a edição') + `? Esta opção não poderá ser desfeita e todas as informações serão salvas e enviadas para análise.</b>
						</p>`,
					showCancelButton: true,
					confirmButtonColor: '#327C32',
					cancelButtonColor: '#FFF',
					showCloseButton: true,
					focusConfirm: false,
					confirmButtonText: '<i class="mdi mdi-check-bold"></i> Confirmar',
					cancelButtonText: '<i class="mdi mdi-close"></i> Cancelar',
					reverseButtons: true

				}).then((result) => {
					acao.confirmar(result);
				}).catch((error) => {
					console.error(error);
				});

			} else {
				window.scrollTo(0, 0);
				this.errorMessageEmpty = false;
			}

		},

		cancelar() {

			if (!this.checkFormReverse()) {

				this.$fire({

					title:
						'<p class="title-modal-confirm">Confirmar cancelamento ` + (this.isInclusao ? 'do cadastro' : 'da edição')`</p>',
					html:
						`<p class="message-modal-confirm">Ao confirmar o cancelamento ` + (this.isInclusao ? 'do cadastro' : 'da edição') +
						`, todas as informações ` + (this.isInclusao ? ' inseridas' : ' editadas') + ` serão perdidas.</p>
						<p class="message-modal-confirm">
							<b>Tem certeza que deseja cancelar` + (this.isInclusao ? ' o cadastro' : ' a edição') +
						`? Esta opção não poderá ser desfeita e todas as informações ` + (this.isInclusao ? ' inseridas' : ' editadas') + ` serão perdidas.</b>
						</p>`,
					showCancelButton: true,
					confirmButtonColor: '#327C32',
					cancelButtonColor: '#FFF',
					showCloseButton: true,
					focusConfirm: false,
					confirmButtonText: '<i class="mdi mdi-check-bold"></i> Confirmar',
					cancelButtonText: '<i class="mdi mdi-close"></i> Cancelar',
					reverseButtons: true

				}).then((result) => {

					if (result.value) {
						this.$router.push({name: 'Usuario'});
					}

				}).catch((error) => {
					console.error(error);
				});

			} else {
				this.$router.push({name: 'Usuario'});
			}

		},

		prepararContatos() {

			this.pessoa.contatos.forEach( (contato) => {

				if (contato.tipo.descricao === 'Email') {

					if (contato.principal) {
						this.contatos.email1 = contato.valor;
					} else {
						this.contatos.email2 = contato.valor;
					}

				} else if (contato.tipo.descricao === 'Telefone celular') {
					this.contatos.cel = contato.valor;
				} else if (contato.tipo.descricao === 'Telefone residencial') {
					this.contatos.tel1 = contato.valor;
				} else  {
					this.contatos.tel2 = contato.valor;
				}

			});

		},

		prepararDadosParaEdicao(informacaoTecnica) {

			this.dados = informacaoTecnica;

			this.$refs.textFieldFormacao.setModel(this.dados.formacao);
			this.$refs.textFieldConcelhoDeClasse.setModel(this.dados.conselhoDeClasse);
			this.$refs.textFieldRegistro.setModel(this.dados.registro);

			this.dados.possuiVinculoComGea = this.dados.possuiVinculoComGea ? 'true' : 'false';

			if (this.dados.especializacao != null) {
				this.dados.especializacao.textoExibicao = this.dados.especializacao.codigo + ' - ' + this.dados.especializacao.nome;
			}

			this.dados.pessoaFisica = JSON.parse(JSON.stringify(informacaoTecnica.pessoa));
			delete this.dados.pessoa;

			if (this.dados.documentos != null) {

				this.dados.documentos.forEach(documento => {

					let mimetype = DataUtils.formatarArquivo(documento);
					let blob = DataUtils.b64ToBlob(documento.imagemBase64, mimetype);
					let file = new File([blob], documento.nome, {type: mimetype});

					file.hash = documento.hash;

					this.files.push(file);

				});

			};

		},

	},

	mounted() {

		if (this.$route.params.id) {
			this.isInclusao = false;
		} else {
			this.isInclusao = true;
		}

	},

	created() {

		PessoaService.buscarPessoalogada()
			.then((result) => {

				this.pessoa = result.data;
				this.prepararContatos();

				if (this.$route.params.id) {

					ResponsavelTecnicoService.buscarSolicitacao(this.pessoa.id)
						.then( (result) => {
							this.prepararDadosParaEdicao(result.data);
						})
						.catch( error => {
							console.error(error);
						});

				}

			})
			.catch(error => {
				console.error(error.message);
			});

		EspecializacaoTecnicaService.buscaEspecializacoesTecnicas()
			.then((result) => {

				this.especializacoes = result.data;
				this.especializacoes.forEach(e => e.textoExibicao = e.codigo + ' - ' + e.nome);

			}).catch(error => {
				console.error(error.message);
			});

	}

};
</script>

<style lang="less">

@import "../../../assets/css/variaveis.less";

#container-cadastro {

	.v-label {
		color: #333;
		font-weight: bold;
	}

	.v-input--selection-controls__ripple{
		color: @color-secondary;
	}

	.v-data-footer {
		display: none;
	}

	#QA-btn-cancelar-cadastro {
		background-color: white;
	}

	.v-radio {

		.v-label {
			font-weight: 400;
			margin: 0 !important;
			font-size: 14px;
		}
	}

	.v-input--selection-controls.v-input {

		.v-messages {
			padding-left: 12px;
		}
	}

	.message-erro {
		flex: 1 1 auto;
		font-size: 12px;
		min-height: 14px;
		min-width: 1px;
		position: relative;
		color: #ff5252 !important;
		caret-color: #ff5252 !important;
	}

	table > thead > tr > th {
		font-size: 14px !important;
	}

	table > tbody > tr > td {
		font-size: 14px !important;

		button {
			margin-right: 8px !important;
		}
	}

}

.theme--light.v-list-item .v-list-item__mask{
	color:white;
	background: @color-secondary;
}

</style>