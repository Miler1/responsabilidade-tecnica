const ER_BASE = 'Erro! Não foi possível ';

const ERROR_MESSAGES = {

	logout: 'Erro ao realizar logout.',

	responsavelTecnico: {
		listagem: 'Erro ao listar os responsáveis técnicos!'
	}



};

const SUCCESS_MESSAGES = {

	cadastro: 'Sucesso! As informações foram cadastradas e enviadas para análise.',

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