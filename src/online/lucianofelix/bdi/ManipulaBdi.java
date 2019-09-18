package online.lucianofelix.bdi;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import online.lucianofelix.beans.Ativo;
import online.lucianofelix.beans.CotacaoAtivo;
import online.lucianofelix.util.FormatarNumeros;
import online.lucianofelix.util.ManipulaArquivoTxt;
import online.lucianofelix.util.ManipulaData;

/**
 * 
 * @author Luciano de Oliveira Felix Silva This class reads the lines of BDIN
 *         "Boletin Diário de Informações" of Bovespa and chooses the correct
 *         values to fields of the papers.
 *
 */
public class ManipulaBdi {
	private JTable tblPapeis;
	private online.lucianofelix.util.ManipulaArquivoTxt manTxt;
	private int tipReg;
	private String dtGerArq;
	private String linha;
	private Ativo ativo;
	private ManipulaData manData;
	private ArrayList<Ativo> arrayAtivo;
	private ArrayList<CotacaoAtivo> arrayCotacao;
	private ManipulaBdi manBdi;
	private Vector<String> vetorLinhas;
	private Timestamp dataTimestamp;

	/**
	 * Recebe o arquivo BDI e retorna uma String com a data
	 * 
	 * @param bdi
	 * @return data
	 */

	public String datBdi(File bdi) {

		manTxt = new ManipulaArquivoTxt();
		if (manTxt.verificaArquivo(bdi) == true) {
			vetorLinhas = manTxt.arquivoParaVetorLinhas(bdi);

			for (int i = 0; i < vetorLinhas.size(); i++) {

				linha = vetorLinhas.elementAt(i);
				// the next line catch the type of register in BDI. Each type
				// corresponds to each case in the next switch case statement.
				tipReg = Integer.parseInt(linha.substring(0, 2));

				switch (tipReg) {

				case 0:
					dtGerArq = linha.substring(22, 30);// Make file date
				default:
					break;
				}
			}

			String aux = dtGerArq.substring(0, 6);
			dtGerArq = dtGerArq.substring(6, 8) + "/" + aux;
			aux = dtGerArq.substring(7, 9);
			dtGerArq = dtGerArq.substring(0, 7);
			dtGerArq = dtGerArq.substring(0, 2) + "/" + aux + "/" + dtGerArq.substring(3, 7);
			// retorna String (dd/mm/aaaa) (dd/mm/yyyy)
			return dtGerArq;

		}
		return null;
	}

	// TODO +++++Recebe o arquivo e retorna retorna a hora
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public String horaBdi(File bdi) {

		manTxt = new ManipulaArquivoTxt();

		if (manTxt.verificaArquivo(bdi) == true) {
			vetorLinhas = manTxt.arquivoParaVetorLinhas(bdi);
			String hrGerArq = null;
			for (int i = 0; i < vetorLinhas.size(); i++) {

				linha = vetorLinhas.elementAt(i);
				// the next line catch the type of register in BDI. Each type
				// corresponds to each case in the next switch case statement.
				tipReg = Integer.parseInt(linha.substring(0, 2));

				switch (tipReg) {

				case 0:
					hrGerArq = linha.substring(38, 43);// Make file hour
				default:
					break;
				}

			}
			String aux = hrGerArq.substring(2, 5);
			hrGerArq = hrGerArq.substring(0, 2) + ":" + aux;
			return hrGerArq;
		}
		return null;
	}

	public ArrayList<Ativo> bdiparaArrayAtivos(File bdi) {
		System.out.println("ManipulaBdi.bdiParaArrayAtivo");
		manTxt = new ManipulaArquivoTxt();
		manBdi = new ManipulaBdi();
		manData = new ManipulaData();
		arrayAtivo = new ArrayList<Ativo>();

		if (manTxt.verificaArquivo(bdi) == true) {
			vetorLinhas = manTxt.arquivoParaVetorLinhas(bdi);
			FormatarNumeros formataNumero = new FormatarNumeros();
			// String horaString = manBdi.horaBdi(bdi);
			String dataString = manBdi.datBdi(bdi);
			System.out.println("Data do bdi: " + dataString);

			try {
				dataTimestamp = manData.converteDataTimestamp(dataString);
				// dataDate = manData.converteDataDate(dataString);
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (int i = 0; i < vetorLinhas.size(); i++) {
				linha = vetorLinhas.elementAt(i);
				// the next line catch the type of register in BDI. Each type
				// corresponds to each case in the next switch case statement.
				tipReg = Integer.parseInt(linha.substring(0, 2));

				switch (tipReg) {
				case 2:
					String cdBdi = linha.substring(3, 4);// this field have
															// utility to
															// classification of
															// papers in
															// emission of BDI
															// (associated table
															// NE001 )
					String desBdi = linha.substring(5, 34);// description of bdi
															// code
					String nomRes = linha.substring(34, 46);// short name of
															// enterprise
					String especi = linha.substring(47, 56);// paper
															// specification to
															// new market(in
															// positions 9, 10,
															// is indicated: N1,
															// N2, or NM)
					String indCar = linha.substring(57);// Characteristics
														// indicator of
														// paper(see the
														// associated table
														// PA020)
					String idNeg = linha.substring(57, 69); // negotiation code
					int tpMerc = Integer.parseInt(linha.substring(70, 72));// id
																			// of
																			// the
																			// market
																			// that
																			// the
																			// paper
																			// is
																			// associated
					String noMerc = linha.substring(73, 87);// type market
															// description
					String praZot = linha.substring(88, 90);// time in days of
															// market a term
					float preAbe = Float.parseFloat(linha.substring(91, 101));// open
																				// price
																				// of
																				// paper
					float preMax = Float.parseFloat(linha.substring(102, 112));// max
																				// price
																				// of
																				// paper
					float preMin = Float.parseFloat(linha.substring(113, 123));// minimal
																				// price
																				// of
																				// paper
					String preMed = linha.substring(124, 134);// middle of price
																// of paper
					float preUlt = Integer.parseInt(linha.substring(135, 145));// last
																				// price
																				// of
																				// the
																				// paper
					String sinOsc = linha.substring(146);// percent evolution of
															// yesterday index
					String oscila = linha.substring(147, 151);
					String preOfeC = linha.substring(152, 162);// better offer
																// buy price
					String preOfeV = linha.substring(163, 173);// better offer
																// sell price
					long totNeg = Long.parseLong(linha.substring(174, 178));// total
																			// number
																			// of
																			// negotiations
																			// in
																			// this
																			// paper
					long quaToTit = Long.parseLong(linha.substring(179, 193));// total
																				// number
																				// of
																				// paper
																				// negotiated
					float volTot = Float.parseFloat(linha.substring(194, 210));// total
																				// volume
																				// of
																				// paper
																				// negotiated

					ativo = new Ativo();
					ativo.setNomeRes(nomRes);
					ativo.setIdNeg(idNeg);
					ativo.setTpMerc(tpMerc);
					preAbe = formataNumero.mostrar2decimaisFloat(preAbe);
					preUlt = formataNumero.mostrar2decimaisFloat(preUlt);
					preMax = formataNumero.mostrar2decimaisFloat(preMax);
					preMin = formataNumero.mostrar2decimaisFloat(preMin);
					// volTot = formataNumero.mostrar2decimaisFloat(volTot);
					// totNeg = formataNumero.mostrar2decimaisFloat(totNeg);
					ativo.setCotacao(new CotacaoAtivo(idNeg, dataTimestamp, preAbe, preUlt, preMax, preMin, volTot,
							quaToTit, totNeg));
					arrayAtivo.add(ativo);

				}
			}
			return arrayAtivo;
		}
		return null;
	}

	public JTable bdiTabelaAtivos(File bdi) {
		// Criação da tabela
		tblPapeis = new JTable();

		// definição do modelo da tabela

		ManipulaBdi manBdi = new ManipulaBdi();
		Ativo atv = new Ativo();
		arrayAtivo = manBdi.bdiparaArrayAtivos(bdi);
		Object colunas[] = { "NomeRes", "ValAbe", "ValMax" };
		DefaultTableModel modeloTabela = new DefaultTableModel();
		modeloTabela = (DefaultTableModel) tblPapeis.getModel();
		modeloTabela.setColumnIdentifiers(colunas);

		for (int i = 0; i < arrayAtivo.size(); i++) {
			System.out.println(arrayAtivo.size());
			atv = arrayAtivo.get(i);
			Object linha[] = { atv.getNomeRes(), atv.getCotacao().getPreAbe(), atv.getCotacao().getPreMax() };
			modeloTabela.addRow(linha);
		}
		tblPapeis.setModel(modeloTabela);
		return tblPapeis;

	}

}
