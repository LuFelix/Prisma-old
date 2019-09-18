package online.lucianofelix.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;

/**
 * Classe utilit&aacute;ria para opera&ccedil;&otilde;es de I/O
 * 
 * @author Ricardo Artur Staroski
 */
public final class IO {

	/**
	 * Copia o arquivo de origem para o arquivo de destino.
	 * 
	 * @param from
	 *            O arquivo de origem.
	 * @param to
	 *            O arquivo de destino.
	 * @throws IOException
	 */
	public static void copy(File from, File to) throws IOException {
		InputStream in = new FileInputStream(from);
		OutputStream out = new FileOutputStream(to);
		copy(in, out);
		in.close();
		out.close();
		to.setLastModified(from.lastModified());
	}

	/**
	 * Copia o arquivo de origem para o arquivo de destino.
	 * 
	 * @param from
	 *            O arquivo de origem.
	 * @param to
	 *            O arquivo de destino.
	 * @param checksum
	 *            O checksum da escrita.
	 * @throws IOException
	 */
	public static void copy(File from, File to, Checksum checksum)
			throws IOException {
		InputStream in = new FileInputStream(from);
		OutputStream out = new FileOutputStream(to);
		copy(in, out, checksum);
		in.close();
		out.close();
		to.setLastModified(from.lastModified());
	}

	/**
	 * Copia o conte&uacute;do do stream de entrada para o stream de
	 * sa&iacute;da.
	 * 
	 * @param from
	 *            O stream de entrada.
	 * @param to
	 *            O stream de sa&iacute;da.
	 * @throws IOException
	 */
	public static void copy(InputStream from, OutputStream to)
			throws IOException {
		final int count = BLOCK_SIZE;
		byte[] bytes = new byte[count];
		for (int read = -1; (read = from.read(bytes, 0, count)) != -1; to
				.write(bytes, 0, read))
			;
		to.flush();
	}

	/**
	 * Copia o conte&uacute;do do stream de entrada para o stream de
	 * sa&iacute;da.
	 * 
	 * @param from
	 *            O stream de entrada.
	 * @param to
	 *            O stream de sa&iacute;da.
	 * @param checksum
	 *            O checksum da escrita.
	 * @throws IOException
	 */
	public static void copy(InputStream from, OutputStream to, Checksum checksum)
			throws IOException {
		final int count = BLOCK_SIZE;
		byte[] bytes = new byte[count];
		for (int read = -1; (read = from.read(bytes, 0, count)) != -1; to
				.write(bytes, 0, read)) {
			checksum.update(bytes, 0, read);
		}
		to.flush();
	}

	/**
	 * Copia o arquivo de origem para o arquivo de destino.
	 * 
	 * @param from
	 *            O caminho do arquivo de origem.
	 * @param to
	 *            O caminho do arquivo de destino.
	 * @throws IOException
	 */
	public static void copy(String from, String to) throws IOException {
		copy(new FileInputStream(from), new FileOutputStream(to));
	}

	/**
	 * Copia o arquivo de origem para o arquivo de destino.
	 * 
	 * @param from
	 *            O caminho do arquivo de origem.
	 * @param to
	 *            O caminho do arquivo de destino.
	 * @param checksum
	 *            O checksum da escrita.
	 * @throws IOException
	 */
	public static void copy(String from, String to, Checksum checksum)
			throws IOException {
		copy(new FileInputStream(from), new FileOutputStream(to), checksum);
	}

	/**
	 * Analisa o arquivo informado, se o mesmo n&atilde;o existir, um novo
	 * &eacute; criado
	 * 
	 * @param file
	 *            O arquivo a ser verificado
	 * @return O pr&oacute;prio par&acirc;metro
	 * @throws IOException
	 */
	public static File createIfNotExists(File file) throws IOException {
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (parent != null) {
				parent.mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}

	/**
	 * Apaga o arquivo informado
	 * 
	 * @param file
	 *            O arquivo a ser apagado
	 * @throws IOException
	 */
	public static void delete(File file) throws IOException {
		Files.deleteIfExists(file.toPath());
	}

	/**
	 * Apaga o arquivo informado
	 * 
	 * @param file
	 *            O arquivo a ser apagado
	 * @throws IOException
	 */
	public static void delete(String file) throws IOException {
		delete(new File(file));
	}

	/**
	 * Obt&eacute;m todas as linhas do arquivo informado
	 * 
	 * @param file
	 *            O arquivo do qual se deseja ler as linhas
	 * @return Uma lista de contendo as linhas do arquivo
	 * @throws IOException
	 */
	public static List<String> readLines(File file) throws IOException {
		List<String> lines = new ArrayList<String>();
		BufferedReader input = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = input.readLine()) != null) {
			lines.add(line);
		}
		input.close();
		return lines;
	}

	/**
	 * Grava as linhas no arquivo informado
	 * 
	 * @param lines
	 *            As linhas a serem gravadas
	 * @param file
	 *            O arquivo no qual se deseja gravar as linhas
	 * @throws IOException
	 */
	public static void writeLines(File file, List<String> lines)
			throws IOException {
		BufferedWriter output = new BufferedWriter(new FileWriter(file));
		for (int i = 0, n = lines.size(); i < n; i++) {
			if (i > 0) {
				output.newLine();
			}
			output.write(lines.get(i));
		}
		output.flush();
		output.close();
	}

	/**
	 * Tamanho padr&atilde;o, 8KB, utilizado para blocos de mem&oacute;ria.
	 */
	public static int BLOCK_SIZE = 8192;

	// não faz sentido instanciar esta classe
	private IO() {
	}
}