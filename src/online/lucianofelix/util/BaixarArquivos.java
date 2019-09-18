package online.lucianofelix.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 
 * @author Luciano de Oliveira Felix Essa classe tem a funcionalidade de receber
 *         uma URL e baixar o arquivo que tem nessa URL atrav�s da cria��o de um
 *         Stream(InputStream) de entrada para ler o fluxo de entrada do arquivo
 *         e de um OutPut para gravar o arquivo num fluxo de sa�da.
 *
 */
public class BaixarArquivos {

	public boolean gravaArquivoDeURL(String stringUrl, String pathLocal,
			String nomeArquivoLocal) throws Exception {

		// transforma a string recebida em um objeto java.net.URL
		URL url = new URL(stringUrl);

		// O arquivo local tem o nome descrito na URL
		// Lembrando que o URL.getPath() ira retornar a estrutura
		// completa de diretorios e voce deve tratar esta String
		// caso nao deseje preservar esta estrutura no seu disco local.
		// nomeArquivoLocal = url.getPath();

		// Cria stream(fluxo) de leitura (este metodo ja faz a conexao)
		InputStream is = url.openStream();

		// Cria stream de escrita.
		FileOutputStream fos = new FileOutputStream(pathLocal
				+ nomeArquivoLocal);

		// Le e grava byte a byte. Voce pode (e deve) usar buffers para
		// melhor performance (BufferedReader).
		int umByte = 0;
		while ((umByte = is.read()) != -1) {
			fos.write(umByte);
		}
		is.close();
		fos.close();

		// apos criar o arquivo fisico, retorna referencia para o mesmo
		new File(pathLocal + nomeArquivoLocal);

		return true;

	}

}
