package com.gestaoresponsabilidadetecnica.configuracao.utils;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArquivoUtils {

	public static File salvaArquivoDiretorio(MultipartFile multipartFile, String diretorioSalvarComNomeArquivo) throws Exception {

		Path pathArquivoRetorno = Paths.get(diretorioSalvarComNomeArquivo);

		File arquivoRetorno = pathArquivoRetorno.toFile();

		if(!arquivoRetorno.exists()) {
			Files.createDirectories(pathArquivoRetorno);
		}

		FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), arquivoRetorno);

		return arquivoRetorno;

	}

	public static File salvaArquivoDiretorio(File file, String diretorioSalvarComNomeArquivo) throws Exception {

		Path pathArquivoRetorno = Paths.get(diretorioSalvarComNomeArquivo);

		File arquivoRetorno = pathArquivoRetorno.toFile();

		if(!arquivoRetorno.exists()) {
			Files.createDirectories(pathArquivoRetorno.getParent());
		}

		FileUtils.copyInputStreamToFile(new FileInputStream(file), arquivoRetorno);

		return arquivoRetorno;

	}

	public static String codificaParaBase64(File file) throws IOException {

		byte[] bytes = loadFile(file);
		byte[] encoded = Base64.encodeBase64(bytes);
		String encodedString = new String(encoded);

		return encodedString;
	}

	private static byte[] loadFile(File file) throws IOException {

		InputStream is = new FileInputStream(file);

		long length = file.length();

		byte[] bytes = new byte[(int)length];

		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length-offset)) >= 0) {
			offset += numRead;
		}

		if (offset < bytes.length) {
			throw new IOException("Erro ao ler arquivo - loadFile -> " + file.getName());
		}

		is.close();

		return bytes;
	}

}
