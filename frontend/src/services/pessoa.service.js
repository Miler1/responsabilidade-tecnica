import ApiService from './api.service';

export default {

    buscarPessoalogada: async () => ApiService.get('/pessoa/buscarPessoaLogada'),

    buscarPessoa: async (idPessoa) => ApiService.get('/pessoa/buscarPessoa/' + idPessoa)

};