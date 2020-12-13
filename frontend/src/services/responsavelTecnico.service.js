import ApiService from './api.service';

export default {

    salvarSolicitacao: async (informacoes) => ApiService.post('/responsavelTecnico/salvarSolicitacao', informacoes),
    upload: async (file) => ApiService.post('/responsavelTecnico/uploadFile', file)

};