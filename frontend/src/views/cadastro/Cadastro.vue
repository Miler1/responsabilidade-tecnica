<template lang="pug">

	v-container#container-cadastro.pa-12.align-center.justify-center

		h1.mb-12 Cadastro de Responsabilidade Técnica Ambiental

		div.mb-6
			ExpansivePanel(titulo = 'Dados pessoais')
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
			ExpansivePanel(titulo = 'Contatos')
				v-row
					v-col.pb-0(cols="12", md="4")
						v-col(cols="12")
							v-label E-mail principal: &nbsp;
							| {{"miler@xxxxx"}}
					v-col.pb-0(cols="12", md="6")
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
			ExpansivePanel(titulo = 'Endereço')
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
			ExpansivePanel(titulo = 'Informações técnicas')
				v-form.px-2(ref="cadastro")
					v-row
						v-col.pb-0(cols="12", md="6")
							TextField(
								labelOption = "Formação: *",
								id = "QA-input-formacao",
								@changeModel="informacoes.formacao = $event",
								placeholder="Digite aqui",
								:errorMessages="errorMessage"
							)
						v-col.pb-0(cols="12", md="3")
							TextField(
								labelOption = "Conselho de classe: *",
								id = "QA-input-conselho-classe",
								@changeModel="informacoes.conselhoDeClasse = $event",
								placeholder="Digite aqui",
								:errorMessages="errorMessage"
							)
						v-col.pb-0(cols="12", md="3")
							TextField(
								labelOption = "Registro: *",
								id = "QA-input-registro",
								@changeModel="informacoes.registro = $event",
								placeholder="Digite aqui",
								:errorMessages="errorMessage"
							)
					v-row
						v-col.py-0(cols="12", md="8")
							v-label Nível de responsabilidade técnica: *
							div
								v-radio-group#QA-radio-nivel-responsabilidade-tecnica(
									v-model="informacoes.nivelResponsabilidadeTecnica",
									row
								)
									v-radio(label='Consultor pessoa física' value='CONSULTORPF')
									v-radio(label='Empresa consultora' value='EMPRESA_CONSULTORA')
									v-radio(label='Funcionário' value='FUNCIONARIO')
						v-col.py-0(cols="12", md="4")
							v-label Possui vínculo com o GEA: *
							div
								v-radio-group#QA-radio-vinculo-gea(v-model="informacoes.possuiVinculoComGea", row)
									v-radio(label='Sim' value='true')
									v-radio(label='Não' value='false')
					v-row
						v-col.pt-0.pb-0(cols="12", md="12")
							v-label Vínculo empregatício: *
							div.d-flex.flex-row.align-baseline
								v-radio-group#QA-radio-vinculo(v-model="informacoes.vinculoEmpregaticio", row)
									v-radio(label='Efetivo' value='EFETIVO')
									v-radio(label='Contrato' value='CONTRATO')
									v-radio(label='Cargo comissionado' value='CARGO_COMISSIONADO')
									v-radio(label='Outro' value='OUTRO')

								v-text-field#QA-input-outro-vinculo(
									color="#E0E0E0",
									:placeholder="placeholder",
									required,
									v-model="informacoes.outroVinculoEmpregaticio"
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
									v-model="informacoes.especializacao",
									:items="especializacoes",
									:filter="filtroSelect",
									item-text="textoExibicao",
									:error-messages="errorMessage(informacoes.especializacao)",
									no-data-text="Nenhuma área de especialização encontrada",
									@click.native="resetErrorMessage",
									required,
									return-object=true
								)
		div.mb-6
			ExpansivePanel(titulo = 'Anexos')

				div.mt-6
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
					:hideFooter="true",
					:labelNoData="labelNoData"
				)

		div.px-6
			v-btn#QA-btn-cadastro-responsabilidade-tecnica.float-right(@click='salvar', large, color="#2196F3", dark)
					v-icon mdi-plus
					span Cadastrar
			v-btn#QA-btn-cancelar-cadastro.float-left(@click='cancelar', large, outlined)
					v-icon mdi-close
					span Cancelar

</template>

<script>

import PessoaService from '@/services/pessoa.service';
import EspecializacaoTecnicaService from '@/services/especializacaoTecnica.service';
import ResponsavelTecnicoService from '@/services/responsavelTecnico.service';
import snackbar from '@/services/snack.service';
import ExpansivePanel from '@/components/ExpansivePanel';
import GridListagemInclusao from '@/components/GridListagemInclusao';
import TextField from '@/components/TextField';
import { HEADER } from '@/utils/dadosHeader/ListagemAnexoInclusao';
import { ERROR_MESSAGES, SUCCESS_MESSAGES } from '@/utils/helpers/messages-utils';

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
			currentFile: [],
			isSelecting: false,
			row: null,
			pessoa: {},
			especializacoes: [],
			informacoes: {
				formacao: null,
				conselhoDeClasse: null,
				registro: null,
				nivelResponsabilidadeTecnica: null,
				possuiVinculoComGea: null,
				vinculoEmpregaticio: null,
				outroVinculoEmpregaticio: null,
				especializacao: null,
			},
			files: [], 
			row1: null,
			row2: null,
		};
	},

	methods: {

		incluirDados() {

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

		preparaPraSalvar() {

			this.informacoes.possuiVinculoComGea = this.informacoes.possuiVinculoComGea === 'true' ? true : false;
			delete this.informacoes.especializacao.textoExibicao;

		},

		salvarArquivos() {
			
			this.files.forEach(file => {

				console.log('TYPEOFFF', file);

				let formData = new FormData();
				formData.append('file', file);

				ResponsavelTecnicoService.upload(formData)
					.catch(error => {

						console.error(error);

						snackbar.alert(ERROR_MESSAGES.atividadeDispensavel.desativar);

					});

			});

		},

		salvar(item) {
			this.$fire({

				title:
					'<p class="title-modal-confirm">Confirmar cadastro</p>',

				html:
					`<p class="message-modal-confirm">Ao confirmar o cadastro, todas as informações serão enviadas para análise.</p>
					<p class="message-modal-confirm">
						<b>Tem certeza que deseja confirmar o cadastro?</b>
					</p>`,
				showCancelButton: true,
				confirmButtonColor: item.ativo ? '#E6A23C' : '#2196F3',
				cancelButtonColor: '#FFF',
				showCloseButton: true,
				focusConfirm: false,
				confirmButtonText: '<i class="mdi mdi-check"></i> Confirmar',
				cancelButtonText: '<i class="mdi mdi-close"></i> Cancelar',
				reverseButtons: true

			}).then((result) => {

				if(result.value) {

					var that = this;

					this.salvarArquivos();

					// that.preparaPraSalvar();

					// ResponsavelTecnicoService.salvarSolicitacao(that.informacoes)
					// 	.then(() => {

					// 		this.salvarArquivos();

					// 		snackbar.alert(SUCCESS_MESSAGES.cadastro, snackbar.type.SUCCESS);

					// 		this.$router.push({name: 'Usuario'});

					// 	})
					// 	.catch(error => {

					// 		console.error(error);

					// 		snackbar.alert(ERROR_MESSAGES.atividadeDispensavel.desativar);

					// 	});

				}

			}).catch((error) => {
				console.error(error);
			});

		},

		cancelar() {
			this.$router.push({name: 'Usuario'});
		},

	},

	created() {

		PessoaService.buscaPessoalogada()
			.then((result) => {
				this.pessoa = result.data;
			})
			.catch(error => {
				console.log(error.message);
			});

		EspecializacaoTecnicaService.buscaEspecializacoesTecnicas()
			.then((result) => {
				this.especializacoes = result.data;
				this.especializacoes.forEach(e => e.textoExibicao = e.codigo + ' - ' + e.nome);
			}).catch(error => {
				console.log(error.message);
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