<template lang="pug">

	v-container#container-cadastro.pa-12.align-center.justify-center

		h1.mb-4 Cadastro de Responsabilidade Técnica Ambiental

		DadosPessoais(:pessoa="pessoa")

		Contatos(:contatos="{...contatos}")

		Endereco(:enderecos="pessoa.enderecos")

		div.mb-6
			ExpansivePanel(titulo = 'Informações técnicas')
				v-form.px-2(ref="cadastro")
					v-row
						v-col(cols="12", md="6")
							TextField(
								v-model="dados.formacao",
								labelOption = "Formação: *",
								id = "QA-input-formacao",
								@changeModel="dados.formacao = $event",
								placeholder="Digite aqui",
								:errorMessages="errorMessage",
								@click.native="resetErrorMessage"
							)
						v-col(cols="12", md="3")
							TextField(
								v-model="dados.conselhoDeClasse",
								labelOption = "Conselho de classe: *",
								id = "QA-input-conselho-classe",
								@changeModel="dados.conselhoDeClasse = $event",
								placeholder="Digite aqui",
								:errorMessages="errorMessage",
								@click.native="resetErrorMessage"
							)
						v-col(cols="12", md="3")
							TextField(
								v-model="dados.registro",
								labelOption = "Registro: *",
								id = "QA-input-registro",
								@changeModel="dados.registro = $event",
								placeholder="Digite aqui",
								:errorMessages="errorMessage",
								@click.native="resetErrorMessage"
							)
					v-row
						v-col.py-0(cols="12", md="8")
							v-label Nível de responsabilidade técnica: *
							v-radio-group#QA-radio-nivel-responsabilidade-tecnica.d-flex.align-center(
								v-model="dados.nivelResponsabilidadeTecnica",
								:errorMessages="errorMessage(dados.nivelResponsabilidadeTecnica)",
								row
							)
								v-radio(label='Consultor pessoa física', value='CONSULTORPF', color="#327C32")
								v-radio(label='Empresa consultora', value='EMPRESA_CONSULTORA', color="#327C32")
								v-radio(label='Funcionário', value='FUNCIONARIO', color="#327C32")
						v-col.py-0(cols="12", md="4")
							v-label Possui vínculo com o GEA: *
							v-radio-group#QA-radio-vinculo-gea.d-flex.align-center(v-model="dados.possuiVinculoComGea", :errorMessages="errorMessage(dados.possuiVinculoComGea)", row)
								v-radio(label='Sim' value='true', color="#327C32")
								v-radio(label='Não' value='false', color="#327C32")
					v-row(v-if="dados.possuiVinculoComGea != null && dados.possuiVinculoComGea === 'true'")
						v-col.pt-0.pb-0(cols="12")
							v-label Vínculo empregatício: *
							div.d-flex.flex-row
								v-radio-group#QA-radio-vinculo.d-flex.align-center(v-model="dados.vinculoEmpregaticio", @change="permiteOutroVinculo()", :errorMessages="errorMessage(dados.vinculoEmpregaticio)", row)
									v-radio(label='Efetivo' value='EFETIVO', color="#327C32")
									v-radio(label='Contrato' value='CONTRATO', color="#327C32")
									v-radio(label='Cargo comissionado' value='CARGO_COMISSIONADO', color="#327C32")
									v-radio(label='Outro' value='OUTRO', color="#327C32" )

								v-text-field.mt-5#QA-input-outro-vinculo(
									v-if="dados.vinculoEmpregaticio === 'OUTRO'"
									v-model="dados.outroVinculoEmpregaticio",
									:errorMessages="errorMessageOutroVinculo(dados.outroVinculoEmpregaticio)",
									color="#E0E0E0",
									:placeholder="placeholder",
									required,
									outlined,
									dense
								)
					v-row
						v-col(cols="12")
							v-label Área de especialização: *
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

				div.px-3.pt-7
					v-btn#QA-btn-adicionar-anexo.float-right(
						color="#327C32",
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
							| Tamanho de arquivo inválido. O arquivo deve conter menos de 10MB

					GridListagemInclusao.mt-12.mb-4(
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
			v-btn#QA-btn-cadastro-responsabilidade-tecnica(@click='salvar', large, color="#327C32", width="145px", dark)
				v-icon mdi-plus
				span Cadastrar

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
			labelNoData: "Não há nenhum anexo adicionado",
			filesAccept: "image/jpg, image/jpeg, image/bmp, image/tiff, image/png, application/pdf",
			headerListagem: HEADER,
			isInclusao: true,
			errorMessageEmpty: true,
			isSelecting: false,
			files: [],
			url: window.location,
			row: null,
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
				especializacao: null
			},
			contatos: {},
			pessoa: {},
		};
	},

	methods: {

		downloadAnexo(item) {

			const link = document.createElement('a');
			link.href = URL.createObjectURL(item);
			link.target = '_blank';
			link.click();
			URL.revokeObjectURL(link.href);

		},

		prepararNumTelefone(numTelefone) {
			return DataUtils.formatarTelefone(numTelefone);
		},

		removerAnexo(item) {

			let pos = this.files.map(function(e) { return e.name; }).indexOf(item);
			let deletedFile = this.files.splice(pos, 1);

		},

		onButtonClick() {

			this.isSelecting = true;

			window.addEventListener('focus', () => {
				this.isSelecting = false;
			}, { once: true });

			this.$refs.uploader.click();

		},

		checaTamanhoArquivo() {

			if (this.files.some(file => file.size > this.totalPermitido)) {
				this.files.splice(0,this.files.length);
				this.excedeuTamanhoMaximoArquivo = true;
			} else {
				this.excedeuTamanhoMaximoArquivo = false;
			}

			return this.excedeuTamanhoMaximoArquivo;

		},

		uploadFile(e) {

			var invalido = false;

			e.target.files.forEach(file => {

				if (file.type == '' || !this.filesAccept.includes(file.type)) {
					invalido = true;
				}

			});

			if (invalido) {

				snackbar.alert(ERROR_MESSAGES.tipoInvalido);
				return;

			}

			this.files = this.files.concat([...e.target.files]);
			this.checaTamanhoArquivo();
		},

		checkForm() {

			if (this.dados.possuiVinculoComGea === 'false') {

				return this.dados.formacao !== null
					&& this.dados.conselhoDeClasse !== null
					&& this.dados.registro !== null
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

			if (Array.isArray(value)){

				if (value.length == 0) {
					return 'Obrigatório';
				}
				if (value.some(file => file.size > 1e7)) {
					return 'Erro! Tamanho de arquivo inválido. O arquivo deve conter menos de 2MB.';
				}
			}

			return this.errorMessageEmpty || value ? '' : 'Obrigatório';

		},

		errorMessageOutroVinculo(value) {
			if (this.isHabilitado) {
				return this.errorMessageEmpty || value ? '' : 'Obrigatório';
			}
		},

		permiteOutroVinculo(isChecked) {

			if (!isChecked) {
				this.dados.outroVinculoEmpregaticio = '';
			}

			this.isHabilitado = isChecked;

		},

		handleError(erro) {
			console.log(erro);
		},

		prepararParaSalvar() {

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

						// snackbar.alert(ERROR_MESSAGES.atividadeDispensavel.desativar);

					});

			});

		},

		salvar() {

			if (this.checkForm()) {

				this.$fire({

					title:
						'<p class="title-modal-confirm">Confirmar cadastro</p>',
					html:
						`<p class="message-modal-confirm">Ao confirmar o cadastro, todas as informações serão salvas e enviadas para análise.</p>
						<p class="message-modal-confirm">
							<b>Tem certeza que deseja confirmar o cadastro? Esta opção não poderá ser desfeita e todas as informações serão salvas e enviadas para análise.</b>
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

						var that = this;

						that.prepararParaSalvar();

						ResponsavelTecnicoService.salvarSolicitacao(that.dados)
							.then(() => {

								that.salvarArquivos();

								snackbar.alert(SUCCESS_MESSAGES.cadastro, snackbar.type.SUCCESS);

								that.$router.push({name: 'Usuario'});

							})
							.catch(error => {

								console.error(error);
								// snackbar.alert(ERROR_MESSAGES.atividadeDispensavel.desativar);

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
			this.$router.push({name: 'Usuario'});
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

		}

	},

	created() {

		PessoaService.buscarPessoalogada()
			.then((result) => {

				this.pessoa = result.data;
				this.prepararContatos();

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
		font-size: 16px;
	}

	.v-input--selection-controls__ripple{
		color: @color-secondary;
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
		background-color: white;
	}

	.v-radio {

		.v-label {
			font-weight: 400;
			margin: 0 !important;
		}

	}

	.v-input--selection-controls.v-input {

		.v-messages {
			padding-left: 12px;
		}
	}

}

.theme--light.v-list-item .v-list-item__mask{
	color:white;
	background: @color-secondary;
}

</style>