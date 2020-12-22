import Extensao from "./extensions";

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

	},

	formatarArquivo: (documento) => {

		let extensao = documento.nome.split('.');

		let mimetype = '';

		if (extensao[1] == 'pdf') {
			mimetype = Extensao.PDF;
		} else if (extensao[1] == 'jpg') {
			mimetype = Extensao.JPG;
		} else if (extensao[1] == 'jpeg') {
			mimetype = Extensao.JPEG;
		} else if (extensao[1] == 'tif') {
			mimetype = Extensao.TIF;
		} else if (extensao[1] == 'bmp') {
			mimetype = Extensao.BMP;
		} else if (extensao[1] == 'png') {
			mimetype = Extensao.PNG;
		}

		return mimetype;

	},

	b64ToBlob(b64Data, contentType) {

		contentType = contentType || '';

		var sliceSize = 512;

		b64Data = b64Data.replace(/^[^,]+,/, '');
		b64Data = b64Data.replace(/\s/g, '');

		var byteCharacters = window.atob(b64Data);
		var byteArrays = [];

		for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {

			var slice = byteCharacters.slice(offset, offset + sliceSize);
			var byteNumbers = new Array(slice.length);

			for (var i = 0; i < slice.length; i++) {
				byteNumbers[i] = slice.charCodeAt(i);
			}

			var byteArray = new Uint8Array(byteNumbers);

			byteArrays.push(byteArray);

		}

		var blob = new Blob(byteArrays, {type: contentType});
		return blob;

	},

};

export default DataUtil;