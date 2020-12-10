import ApiService from './api.service';

export default {

    buscaEspecializacoesTecnicas: async () => ApiService.get('/especializacaoTecnica/buscaEspecializacoesTecnicas')
};