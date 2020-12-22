<template lang="pug">

	v-container#container-visualizar.pa-12.align-center.justify-center

		h1.mb-4 {{acao === 'analisar' ? 'Análise' : 'Visualização'}} de Responsabilidade Técnica Ambiental

		DadosPessoais(:pessoa="pessoa")

		Contatos(:contatos="{...contatos}")

		Endereco(:enderecos="pessoa.enderecos")

		InformacoesTecnicas(:dados="dados")

		div
			v-row
				v-col(cols="12", md="3")
					v-btn#QA-btn-voltar-visualizacao.float-left(@click='voltar', large, color="#327C32", width="145px", outlined)
						v-icon {{acao === 'analisar' ? "mdi-close" : "mdi-arrow-left"}}
						span {{acao === 'analisar' ? "Cancelar" : "Voltar"}}
				v-col.d-flex.flex-row-reverse(v-if="acao === 'analisar'", cols="12", md="9")
					v-btn#QA-btn-aprovar-analise.ml-3(@click='aprovar', large, color="#327C32",  width="145px", dark)
						v-icon mdi-check-circle-outline
						span Aprovar
					v-btn#QA-btn-reprovar-analise(@click='reprovar', large, color="red", width="145px", outlined)
						v-icon mdi-close-circle-outline
						span Reprovar

</template>

<script>

import PessoaService from '@/services/pessoa.service';
import EspecializacaoTecnicaService from '@/services/especializacaoTecnica.service';
import ResponsavelTecnicoService from '@/services/responsavelTecnico.service';
import snackbar from '@/services/snack.service';

import ExpansivePanel from '@/components/ExpansivePanel';
import GridListagemInclusao from '@/components/GridListagemInclusao';
import TextField from '@/components/TextField';
import DataUtils from '@/utils/dataUtils';
import { HEADER } from '@/utils/dadosHeader/ListagemAnexoInclusao';
import { ERROR_MESSAGES, SUCCESS_MESSAGES } from '@/utils/helpers/messages-utils';
import { mapGetters } from 'vuex';
import Status from '@/enums/statusEnum';

import DadosPessoais from '@/views/common/DadosPessoais';
import Contatos from '@/views/common/Contatos';
import Endereco from '@/views/common/Endereco';
import InformacoesTecnicas from '@/views/common/InformacoesTecnicas';

export default {

	name: 'Cadastro',

	components: {
		ExpansivePanel,
		GridListagemInclusao,
		TextField,
		DadosPessoais,
		Contatos,
		Endereco,
		InformacoesTecnicas
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
			isSelecting: false,
			files: [],
			url: window.location,
			row: null,
			excedeuTamanhoMaximoArquivo: false,
			totalPermitido: 2000000,
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
				justificativa: null,
				status: {},
				pessoaFisica: {},
			},
			pessoa: {},
			pessoaEU:{},
			contatos: {},
			perfil: null,
			acao: null,
		};
	},

	methods: {

		prepararCpf() {

			console.log("pessoa: ", this.pessoa);
			console.log("pessoa: ", this.dados);


			if (this.pessoa.cpf) {
				return DataUtils.formatarCpf(this.pessoa.cpf);
			}

		},

		prepararData(milisegundos) {
			return DataUtils.formatarData(milisegundos);
		},

		prepararCep(cep) {
			return DataUtils.formatarCep(cep);
		},

		prepararNumTelefone(numTelefone) {
			return DataUtils.formatarTelefone(numTelefone);
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

		voltar() {
			this.$router.push({name: (this.perfil).normalize('NFD').replace(/[\u0300-\u036f]/g, "")});
		},

		aprovar() {

			let acao = {};

			acao.confirmar = (result) => {

				if (result.value) {

					this.dados.status.codigo = Status.APROVADO;
					this.dados.pessoaFisica = this.pessoaEU;

					var that = this;

					ResponsavelTecnicoService.editarSolicitacao(that.dados)
						.then(() => {

							that.voltar();
							snackbar.alert(SUCCESS_MESSAGES.administrador.aprovar, snackbar.type.SUCCESS);

						})
						.catch(error => {

							console.error(error);
							that.handleError(error, true);

						});

				}

			};

			this.modalAprovacao(acao);

		},

		reprovar() {

			let acao = {};

			acao.confirmar = (justificativa) => {

				if (justificativa.value) {

					this.dados.justificativa = justificativa.value;
					this.dados.pessoaFisica = this.pessoaEU;
					this.dados.status.codigo = Status.REPROVADO;
					this.dados.validade = null;

					let that = this;

					ResponsavelTecnicoService.editarSolicitacao(this.dados)
						.then( (response) => {

							snackbar.alert(SUCCESS_MESSAGES.administrador.reprovar, snackbar.type.SUCCESS);
							that.voltar();

						})
						.catch(error => {
							console.error(error);
							// that.handleError(error, true);

						});

				}

			};

			this.modalReprovacao(acao);

		},

		modalAprovacao(acao) {

			this.$fire({

				title:
					'<p class="title-modal-confirm">Confirmar aprovação</p>',
				html:
					`<p class="message-modal-confirm">Ao confirmar a aprovação, a análise será finalizada.</p>
					<p class="message-modal-confirm">
						<b>Tem certeza que deseja aprovar o cadastro? Esta opção não poderá ser desfeita e o cadastro permanecerá ativo por 1 ano.</b>
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

		},

		modalReprovacao(acao) {

			this.$fire({

				title: `<div><p class="title-modal-confirm">Confirmar reprovação</p><div>`,
				html:
					`
					<div class="row" id="row-justificativa-analise" style="padding-top: 15px">
						<div class="col col-12" style="display: flex; flex-direction: column; padding-bottom: 0">
							<label id="label-justificativa" aria-hidden="true" class="v-label theme--light" style="text-align: left; padding-bottom:2px">Justificativa</label>
							<div class="v-input v-textarea v-textarea--auto-grow v-textarea--no-resize theme--light v-text-field v-text-field--is-booted v-text-field--enclosed v-text-field--outlined" id="div-input">
								<div class="v-input__control">
									<div class="v-input__slot" >
										<fieldset aria-hidden="true">
											<legend style="width: 0px;">
												<span>​</span>
											</legend>
										</fieldset>
										<div class="v-text-field__slot">
											<textarea id="QA-input-analise-justificativa" rows="4" placeholder="Digite aqui..." required="required" style="margin-top: 10px; width: 100%; resize: none; padding-right: 12px"></textarea>
										</div>
									</div>
									<div class="v-text-field__details">
										<div class="v-messages theme--light">
											<div class="v-messages__wrapper" id="texto-mensagem"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					`,
				showCancelButton: true,
				confirmButtonColor:'#327C32',
				cancelButtonColor: '#FFF',
				showCloseButton: true,
				focusConfirm: false,
				confirmButtonText: '<i class="mdi mdi-check-bold" style="left:0px"></i> Confirmar',
				cancelButtonText: '<i class="mdi mdi-close"></i> Cancelar',
				reverseButtons: true,
				preConfirm: () => {

					const campoJustificativa = document.getElementById("QA-input-analise-justificativa");

					let justificativa = campoJustificativa.value.trim();

					if (justificativa) {
						return justificativa;
					} else {

						const input = document.getElementById("div-input");
						const mensagem = document.getElementById("texto-mensagem");

						const classes = ["v-input--has-state", "v-text-field--placeholder", "error--text"];
						input.classList.add(...classes);
						input.style.color = "#ff5252";

						mensagem.innerHTML = "Obrigatório";
						mensagem.style.fontSize = "12px";
						mensagem.style.color = "#ff5252";
						mensagem.style.caretColor = "#ff5252";

						return false;

					}

				}

			}).then((justificativa) => {
				acao.confirmar(justificativa);
			}).catch((error) => {
				console.log("error", error);
			});

		},

		getUsuarioLogado() {

			return this.usuarioLogado == null ? '' :
				this.usuarioLogado.principal.authorities.filter(function(e) { return e.authority === 'ADMINISTRADOR'; }).length > 0 ? 'Administrador' : 'Usuário';

		}

	},

	computed: {
		...mapGetters(['usuarioLogado'])
	},

	created() {

		let href = (window.location.hash).toString();

		if (href.indexOf('analisar') > -1) {
			this.acao = 'analisar';
		} else {
			this.acao = 'visualizar';
		}

	},

	mounted() {

		if (this.$route.params.id) {

			let id = this.$route.params.id;

			this.perfil = this.getUsuarioLogado();

			if (this.perfil === "Usuário") {

				PessoaService.buscarPessoalogada()
					.then((result) => {

						this.pessoa = result.data;
						this.prepararContatos();

					})
					.catch(error => {
						console.error(error.message);
					});

				ResponsavelTecnicoService.buscarSolicitacao()
					.then((result) => {
						this.dados = result.data;
					}).catch(error => {
						console.error(error.message);
					});


			} else {

				ResponsavelTecnicoService.buscarSolicitacaoById(id)
					.then((response) => {

						this.dados = response.data;
						this.pessoaEU = response.data.pessoa;

						PessoaService.buscarPessoa(this.dados.pessoa.id)
							.then((result) => {

								this.pessoa = result.data;
								this.prepararContatos(this.pessoa);

							})
							.catch(error => {
								console.error(error.message);
							});

					})
					.catch(error => {

						console.error(error);
						snackbar.alert(ERROR_MESSAGES.responsavelTecnico.listagem);

					});

			}

		}

	}

};

</script>

<style lang="less">

#container-visualizar {

	.v-label {
		color: #333;
		font-weight: bold;
	}

	table > thead > tr > th {
		font-size: 14px !important;
	}

	.v-data-footer {
		display: none;
	}

	#QA-btn-voltar-visualizacao, #QA-btn-reprovar-analise {
		background-color: white;
	}

	.v-radio {
		.v-label {
			font-weight: 400;
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
	background: #65afef;
}

</style>