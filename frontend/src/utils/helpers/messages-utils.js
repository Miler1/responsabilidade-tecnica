const ER_BASE = 'Erro! Não foi possível ';

const ERROR_MESSAGES = {

	logout: 'Erro ao realizar logout do sistema.',

	responsavelTecnico: {
		listagem: 'Erro ao listar os responsáveis técnicos ambientais!'
	},

	tipoInvalido: 'Erro! Formato de arquivo inválido. O arquivo deve ser de um dos seguintes formatos: PDF, PNG, JPEG, JPG, TIFF ou BMP.'

};

const SUCCESS_MESSAGES = {

	administrador: {
		aprovar: 'Sucesso! O cadastro foi aprovado. ',
		reprovar: 'Sucesso! O cadastro foi reprovado. ',
	},

	responsavelTecnico: {
		cadastro: 'Sucesso! As informações foram cadastradas e enviadas para análise.',
		edicao: 'Sucesso! As informações foram editadas e enviadas para análise.',
	}
	// cnae: {
	// 	ativar: 'Sucesso! O CNAE foi ativado.',
	// 	desativar: 'Sucesso! O CNAE foi desativado.',
	// },


};

const WARNING_MESSAGES = {

	// taxaLicenciamento: "Aviso! Para cadastrar, é necessário adicionar ao menos uma taxa de licenciamento. ",

};

export {
	ERROR_MESSAGES,
	SUCCESS_MESSAGES,
	WARNING_MESSAGES
};