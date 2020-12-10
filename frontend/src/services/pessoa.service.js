import ApiService from './api.service';

export default {

    buscaPessoalogada: async () => ApiService.get('/pessoa/buscaPessoaLogada')

};