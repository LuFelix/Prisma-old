package online.lucianofelix.util;

import static online.lucianofelix.util.IO.copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Classe utilit&aacute;ria para compacta&ccedil;&atilde;o e
 * descompacta&ccedil;&atilde;o de arquivos ZIP
 * 
 * @author Ricardo Artur Staroski
 */
public final class ManipulaArquivoZip {

	/**
	 * Compacta determindado arquivo ou diret&oacute;rio para o arquivo ZIP
	 * especificado
	 * 
	 * @param input
	 *            O arquivo ou diret&oacute;rio de entrada
	 * @param output
	 *            O arquivo ZIP de sa&iacute;da
	 *
	 * @return O checksum da compacta&ccedil;&atilde;o do arquivo
	 */
	public static long compress(final File input, final File output) throws IOException {
		if (!input.exists()) {
			throw new IOException(input.getName() + " não existe!");
		}
		if (output.exists()) {
			if (output.isDirectory()) {
				throw new IllegalArgumentException("\"" + output.getAbsolutePath() + "\" não é um arquivo!");
			}
		} else {
			final File parent = output.getParentFile();
			if (parent != null) {
				parent.mkdirs();
			}
			output.createNewFile();
		}
		Checksum checksum = createChecksum();
		final ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(output));
		zip.setLevel(Deflater.BEST_COMPRESSION);
		compressInternal(null, input, zip, checksum);
		zip.finish();
		zip.flush();
		zip.close();
		return checksum.getValue();
	}

	/**
	 * Extrai um arquivo ZIP para o diretório especificado
	 * 
	 * @param input
	 *            O arquivo ZIP de entrada
	 * @param output
	 *            O diretório de saída
	 * @return O checksum da descompactação do arquivo
	 */
	public static long extract(final File input, final File output) throws IOException {
		if (input.exists()) {
			if (input.isDirectory()) {
				throw new IllegalArgumentException("\"" + input.getAbsolutePath() + "\" não é um arquivo!");
			}
		} else {
			throw new IllegalArgumentException("\"" + input.getAbsolutePath() + "\" não existe!");
		}
		if (output.exists()) {
			if (output.isFile()) {
				throw new IllegalArgumentException("\"" + output.getAbsolutePath() + "\" não é um diretório!");
			}
		}
		Checksum checksum = createChecksum();
		final ZipInputStream zip = new ZipInputStream(new FileInputStream(input));
		extractInternal(zip, output, checksum);
		zip.close();
		return checksum.getValue();
	}

	// Adiciona determinado arquivo ao ZIP
	private static void compressInternal(final String caminho, final File arquivo, final ZipOutputStream zip,
			Checksum checksum) throws IOException {
		final boolean dir = arquivo.isDirectory();
		String nome = arquivo.getName();
		nome = (caminho != null ? caminho + "/" + nome : nome);
		final ZipEntry item = new ZipEntry(nome + (dir ? "/" : ""));
		item.setTime(arquivo.lastModified());
		zip.putNextEntry(item);
		if (dir) {
			zip.closeEntry();
			final File[] arquivos = arquivo.listFiles();
			for (int i = 0; i < arquivos.length; i++) {
				// recursivamente adiciona outro arquivo ao ZIP
				compressInternal(nome, arquivos[i], zip, checksum);
			}
		} else {
			item.setSize(arquivo.length());
			final FileInputStream entrada = new FileInputStream(arquivo);
			copy(entrada, zip, checksum);
			entrada.close();
			zip.closeEntry();
		}
	}

	private static Checksum createChecksum() {
		return new CRC32();
	}

	// Retira determinado elemento do arquivo ZIP
	private static void extractInternal(final ZipInputStream zip, final File pasta, Checksum checksum)
			throws IOException {
		ZipEntry elemento = null;
		while ((elemento = zip.getNextEntry()) != null) {
			String nome = elemento.getName();
			nome = nome.replace('/', File.separatorChar);
			nome = nome.replace('\\', File.separatorChar);
			File arquivo = new File(pasta, nome);
			if (elemento.isDirectory()) {
				arquivo.mkdirs();
			} else {
				boolean existe = arquivo.exists();
				if (!existe) {
					final File parent = arquivo.getParentFile();
					if (parent != null) {
						parent.mkdirs();
					}
					arquivo.createNewFile();
				}
				boolean oculto = false;
				boolean somenteLeitura = false;
				if (existe) {
					oculto = arquivo.isHidden();
					if (oculto) {
						Files.setAttribute(arquivo.toPath(), "dos:hidden", false);
					}
					somenteLeitura = !arquivo.canWrite();
					if (somenteLeitura) {
						arquivo.setWritable(true);
					}
				}

				OutputStream saida = new FileOutputStream(arquivo);
				copy(zip, saida, checksum);
				saida.close();

				if (existe) {
					if (somenteLeitura) {
						arquivo.setWritable(false);
					}
					if (oculto) {
						Files.setAttribute(arquivo.toPath(), "dos:hidden", true);
					}
				}
			}
			arquivo.setLastModified(elemento.getTime());
		}
	}

	// Construtor privado - Náo há razão em instanciar esta classe
	private ManipulaArquivoZip() {
	}
}
