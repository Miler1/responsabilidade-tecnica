const DataUtil = {

    formatarData:(date) => {
        return new Date(date).toLocaleDateString();
    },

    formatarCpf:  (cpf) => {
        return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g,"\$1.\$2.\$3\-\$4");
    },

    formatarCep: (cep) => {
        return cep.replace(/^([\d]{2})\.?([\d]{3})\-?([\d]{3})/, "$1.$2-$3");
    },

    formatarTitulo: function(titulo) {
        return titulo.replace(/(\d{4})(\d{4})(\d{4})/g,"\$1 \$2 \$3");
    },

    formatarTelefone: (num) => {

        num = num.replace(/\D/g,"");
        num = num.replace(/^(\d{2})(\d)/g,"($1) $2");
        num = num.replace(/(\d)(\d{4})$/,"$1-$2");

        return num;

    }

};

export default DataUtil;