<template lang="pug">

	v-container#container-cadastro.pa-12.align-center.justify-center

		h1.mb-12 Visualizar cadastro de Responsabilidade Técnica Ambiental

		DadosPessoais(:pessoa="pessoa")

		Contatos(:contatos="{...contatos}")

		Endereco(:enderecos="pessoa.enderecos")

		InformacoesTecnicas(:dados="dados")

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
				especializacao: null,
			},
			contatos: {}
		};
	},

	methods: {

		prepararCpf() {

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

		ResponsavelTecnicoService.buscarSolicitacao()
			.then((result) => {
				this.dados = result.data;
			}).catch(error => {
				console.error(error.message);
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

	.message-erro {
		color: #ff5252 !important;
		caret-color: #ff5252!important;
		flex: 1 1 auto;
		font-size: 12px;
		min-height: 14px;
		min-width: 1px;
		position: relative;
	}

}

.theme--light.v-list-item .v-list-item__mask{
	color:white;
	background: #65afef;
}

</style>