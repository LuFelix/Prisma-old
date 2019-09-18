package online.lucianofelix.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import online.lucianofelix.beans.Produto;
import online.lucianofelix.dao.DAOProdutoPrepSTM;
import online.lucianofelix.dao.DAOProdutosCotacao;

public class ManipulaArquivoTxt {

	Vector<String> vetorBdi;
	ManipulaArquivoTxt manTxt;
	File arquivo;

	public ManipulaArquivoTxt() {

	}

	/**
	 * Verificar se o arquivo existe
	 * 
	 * @param File
	 *            -- arquivo
	 * @return boolean
	 */

	public boolean verificaArquivo(File arquivo) {
		try {
			if (arquivo.exists()) {
				return true;
			}
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "O arquivo para leitura nãoo foi selecionado!\n");

		}
		return false;
	}

	/**
	 * Recebe um arquivo e retorna uma area de texto com o
	 * 
	 * @param JTextArea
	 *            areaTexto
	 * @param File
	 *            arquivo
	 * @return JTextArea areaTexto
	 * @throws FileNotFoundException
	 * 
	 */

	public JTextArea extraiConteudo(JTextArea areaTexto, File arquivo) throws FileNotFoundException {

		manTxt = new ManipulaArquivoTxt();
		// deve fazer um teste para saber se o arquivo está aberto
		if (manTxt.verificaArquivo(arquivo)) {
			try {
				BufferedReader leArquivo = new BufferedReader(new FileReader(arquivo));
				String lin = "";
				while ((lin = leArquivo.readLine()) != null) {
					areaTexto.append(lin + "\n");
				}
				leArquivo.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return areaTexto;
	};

	/**
	 * Lê um File e Retorna o Conteudo em uma String
	 * 
	 * @param File
	 *            -- arquivo
	 * @return String -- conteudo
	 * 
	 * @throws FileNotFoundException
	 */

	public String extraiConteudo(File arquivo) throws FileNotFoundException {

		manTxt = new ManipulaArquivoTxt();
		String conteudo = "";

		// deve fazer um teste para saber se o arquivo estï¿½ aberto
		if (manTxt.verificaArquivo(arquivo)) {

			try {
				BufferedReader leArquivo = new BufferedReader(new FileReader(arquivo));
				String lin = "";
				while ((lin = leArquivo.readLine()) != null) {
					conteudo = conteudo + lin;
				}
				leArquivo.close();

			} catch (Exception e) {
				conteudo = e.getMessage();

			}

		}
		return conteudo;
	};

	/**
	 * Buscar quantidade de ocorrencias de Uma String em um File
	 * 
	 * @param String
	 *            busca -- String procurada
	 * @param File
	 *            arquivo --Arquivo para procurar a String
	 * 
	 * @return int quantidadeEncontrada
	 */

	public int buscarQuantString(String busca, File arquivo) {

		manTxt = new ManipulaArquivoTxt();
		int quantidadeEncontrada = 0;
		if (manTxt.verificaArquivo(arquivo)) {
			Vector<String> vetorLinhas = null;
			vetorLinhas = manTxt.arquivoParaVetorLinhas(arquivo);
			int tamVetorLinhas = vetorLinhas.size();
			String linha = "";
			for (int i = 0; i < tamVetorLinhas; i++) {
				linha = vetorLinhas.elementAt(i);
				if (linha.contains(busca)) {
					quantidadeEncontrada++;
				}
			}

		}
		return quantidadeEncontrada;
	}

	/**
	 * Converte um arquivo em um Vector de linhas
	 * 
	 * @param arquivo
	 * @return Vector -- vetorLinhas
	 */

	public Vector<String> arquivoParaVetorLinhas(File arquivo) {

		manTxt = new ManipulaArquivoTxt();
		Vector<String> vetorLinhas = new Vector<String>();
		try {
			if (manTxt.verificaArquivo(arquivo) == true) {
				BufferedReader leArquivo = new BufferedReader(new FileReader(arquivo));
				String line = "";
				while ((line = leArquivo.readLine()) != null) {
					vetorLinhas.addElement(line);
				}
				leArquivo.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return vetorLinhas;
	}

	// TODO +++++ Recebe um arquivo e grava no

	public boolean gravaArquivo(File arquivo) throws Exception {

		manTxt = new ManipulaArquivoTxt();

		if (manTxt.verificaArquivo(arquivo)) {

			FileInputStream entradaArquivo = new FileInputStream(arquivo.getAbsolutePath());
			// Agora temos o stream de bytes do arquivo em entradaArquivo e
			// passamos para buffer
			// esse stream para que seja manipulado
			BufferedInputStream buferArquivo = new BufferedInputStream(entradaArquivo);
			// Criaï¿½ï¿½o de um objeto que grava stream de saï¿½da de arquivo
			// com a
			// passagem do
			// endereï¿½o e o tipo de arquivo concatenados
			FileOutputStream gravaArquivo = new FileOutputStream(arquivo.getAbsolutePath() + ".txt");
			// a variï¿½vel "i" recebe o conteudo do bufer e escreve o
			// conteï¿½do
			// byte a byte
			// retornando -1 o quando o conteudo do stream acabar
			int i;
			while ((i = buferArquivo.read()) >= 0) {
				gravaArquivo.write(i);
			}
			gravaArquivo.close();
			buferArquivo.close();
			return true;

		} else {
			JOptionPane.showMessageDialog(null, "Favor selecionar o arquivo!");

		}

		return false;
	}

	// TODO +++++Cria um novo arquivo vazio e com um nome e um local
	// pre-selecionados ++++++++++++++++++++++++++++++++++++++++++++

	boolean criarArquivoFisico(File arquivo) {
		try {
			arquivo.createNewFile();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	// TODO +++++Cria um novo arquivo vazio e com um nome e pede o local para
	// salvar +++++++++++++++++++++++++++++++++++++++++++++
	File criaArquivoFisico(File arquivo) {
		// SelecionaArquivo ï¿½ uma clase que manipula o JFileChooser para
		// retornar as Strings com
		// o local o nome do arquivo a ser salvo
		SelecionaArquivo local = new SelecionaArquivo();
		// Esse mï¿½todo cria o diï¿½logo de escolha de endereï¿½o que contem a
		// pasta
		// e o nome do arquivo a ser salvo
		local.escolheLocalSalvar(local);
		arquivo = new File(local.getPasta(), local.getNomArquivo());
		try {
			arquivo.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arquivo;

	}

	// TODO +++++Recebe uma area de texto e monta em um arquivo Escolhendo o
	// Local Para Salvar+++++++++++++++++++++++++++++++++

	public File montaArquivotxt(JTextArea texto) {
		arquivo = criaArquivoFisico(arquivo);// nome e local no arquivo criado
		try {
			FileWriter escreveArquivo = new FileWriter(arquivo);
			escreveArquivo.write(texto.getText());// escreve o conteudo da area
													// de texto
			escreveArquivo.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
		return arquivo;
	}

	// TODO Função que recebe uma String e Monta em um arquivo
	// Escolhendo onde quer salvar

	public File montaArquivotxt(String texto) {
		arquivo = criaArquivoFisico(arquivo);// nome e local no arquivo criado
		try {
			FileWriter escreveArquivo = new FileWriter(arquivo);
			escreveArquivo.write(texto);// escreve o conteudo da String
			escreveArquivo.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
		return arquivo;
	}

	// TODO Recebe uma area de texto e monta em um arquivo com parametros
	// do local de salvar

	public File montaArquivotxt(JTextArea texto, String nomePasta, String nomeArquivo) throws IOException {
		arquivo = new File(nomePasta, nomeArquivo);
		arquivo.createNewFile();
		FileWriter escreveArquivo = new FileWriter(arquivo);
		escreveArquivo.write(texto.getText());// escreve o conteudo da tela
												// principal
		escreveArquivo.close();
		return arquivo;
	}

	// TODO Recebe uma String e Monta em um arquivo Recebendo parametro do
	// local para salvar

	public File montaArquivotxt(String texto, String nomePasta, String nomeArquivo) throws IOException {

		arquivo = new File(nomePasta, nomeArquivo);
		arquivo.createNewFile();
		FileWriter escreveArquivo = new FileWriter(arquivo);
		escreveArquivo.write(texto);// escreve o conteudo da String
		escreveArquivo.close();

		return arquivo;
	}

	// TODO Recebe uma tabela e cria um arquivo sem extensão com
	// seu conteudo separado
	// por ";". O caractere separador pode ser alterado

	public boolean salvarTabela(JTable tblTabela) {

		if (tblTabela != null) {
			int i = 0;
			// Criaï¿½ï¿½o do arquivo que vai receber a tabela
			File arquivo = null;
			arquivo = criaArquivoFisico(arquivo);
			try {
				FileWriter escreveTabela = new FileWriter(arquivo);
				while (i < tblTabela.getRowCount()) {
					int j = 0;
					while (j < tblTabela.getColumnCount()) {
						escreveTabela.write(tblTabela.getValueAt(i, j).toString() + "; ");
						j++;
					}
					escreveTabela.write("\n");
					i++;
				}
				escreveTabela.close();
				return true;

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public void createCsvFile() {

		try {

			// Criação de um buffer para a escrita em uma stream
			BufferedWriter StrW = new BufferedWriter(new FileWriter("C:\\tabela.csv"));

			// Escrita dos dados da tabela
			StrW.write("Nome;Telefone;Idade\n");
			StrW.write("Juliana;6783-8490;23\n");
			StrW.write("Tatiana;6743-7480;45\n");
			StrW.write("Janice;6909-9380;21");
			StrW.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readCsvFile() {
		Produto pro = new Produto();
		DAOProdutoPrepSTM daoPro = new DAOProdutoPrepSTM();
		DAOProdutosCotacao daoProCot = new DAOProdutosCotacao();
		String data = String.valueOf(new Timestamp(System.currentTimeMillis())).substring(0, 10);
		try {
			BufferedReader StrReader = new BufferedReader(new FileReader("c:\\tabela.csv"));
			String Str;
			String[] TableLine;
			String cell, cell2, cell3;

			while ((Str = StrReader.readLine()) != null) {
				TableLine = Str.split(";");
				for (int i = 0; i < TableLine.length - 2; i++) {
					cell = TableLine[i];
					cell2 = TableLine[i + 1];
					cell3 = TableLine[i + 2];
					pro.setNome_prod(cell);
					pro.setCodi_prod_1(cell2);
					pro.setAliq_prod("II");
					try {
						daoPro.cadastrar(pro);
						daoProCot.novoPrecoProduto("Venda", Date.valueOf(data), pro.getCodi_prod_1(),
								Float.parseFloat(cell3));
						System.out.println("Cadastrado Produto: " + pro.getNome_prod() + " Código: "
								+ pro.getCodi_prod_1() + " Preço: " + pro.getPrec_custo());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Não cadastrado Produto: " + pro.getNome_prod() + " Código: "
								+ pro.getCodi_prod_1() + " Preço: " + pro.getPrec_custo());
						// e.printStackTrace();
					}

					// System.out.println("Produto: " + pro.getNome_prod() + "
					// Código: " + pro.getCodi_prod_1()
					// + " Preço: " + pro.getPrec_custo());
				}
				// for (String cell : TableLine) {
				// System.out.print(cell + " : ");
				// }
			}
			StrReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
