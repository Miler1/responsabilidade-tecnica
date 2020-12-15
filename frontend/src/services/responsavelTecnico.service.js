import ApiService from './api.service';

export default {

    salvarSolicitacao: async (informacoes) => ApiService.post('/responsavelTecnico/salvarSolicitacao', informacoes),

	listar: async (parametrosFiltro) => {

		if (parametrosFiltro) {

			return ApiService.post(`responsavelTecnico/listar/?
						page=${parametrosFiltro.pagina > 0 ? parametrosFiltro.pagina : 0}
						&size=${parametrosFiltro.itemsPorPagina > 0 ? parametrosFiltro.itemsPorPagina : 10}
						&sort=${parametrosFiltro.tipoOrdenacao ? parametrosFiltro.tipoOrdenacao : 'pessoa.nome,asc'}`,

			parametrosFiltro
			);

		} else {
			return ApiService.post('responsavelTecnico/listar/?page=0&size=10&sort=pessoa.nome,asc', {});
		}

	},

    upload: async (file) => ApiService.post('/responsavelTecnico/uploadFile', file)

};