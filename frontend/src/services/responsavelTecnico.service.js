import ApiService from './api.service';

export default {

    salvarSolicitacao: async (informacoes) => ApiService.post('/responsavelTecnico/salvarSolicitacao', informacoes),

    editarSolicitacao: async (informacoes) => ApiService.post('/responsavelTecnico/editarSolicitacao', informacoes),

    apagarArquivos: async (informacoes) => ApiService.post('/responsavelTecnico/removerArquivos', informacoes),

	listar: async (parametrosFiltro) => {

		if (parametrosFiltro) {

			return ApiService.post(`responsavelTecnico/listar/?
						page=${parametrosFiltro.pagina > 0 ? parametrosFiltro.pagina : 0}
						&size=${parametrosFiltro.itemsPorPagina > 0 ? parametrosFiltro.itemsPorPagina : 10}
						&sort=${parametrosFiltro.tipoOrdenacao ? parametrosFiltro.tipoOrdenacao : 'id,asc'}`,

			parametrosFiltro
			);

		} else {
			return ApiService.post('responsavelTecnico/listar/?page=0&size=10&sort=id,asc', {});
		}

	},

	upload: async (file) => ApiService.post('/responsavelTecnico/uploadFile', file),

	download: async (hash) => ApiService.get('/responsavelTecnico/downloadFile/' + hash),

	buscarSolicitacao: async() => ApiService.get('/responsavelTecnico/buscarSolicitacao/'),

    buscarSolicitacaoById: async(id) => ApiService.get('/responsavelTecnico/buscarSolicitacaoById/' + id)

};