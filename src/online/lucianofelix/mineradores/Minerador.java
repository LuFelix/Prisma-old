package online.lucianofelix.mineradores;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import online.lucianofelix.beans.Ativo;
import online.lucianofelix.beans.CotacaoAtivo;
import online.lucianofelix.beans.Fuse;
import online.lucianofelix.beans.Operacao;
import online.lucianofelix.beans.OrdemBovespa;
import online.lucianofelix.controle.ControlaFuse;
import online.lucianofelix.dao.DAOAtivo;
import online.lucianofelix.dao.DAOCotacaoAtivo;
import online.lucianofelix.dao.DAOFuse;
import online.lucianofelix.dao.DAOOperacao;
import online.lucianofelix.dao.DAOOrdemBovespa;
import online.lucianofelix.indicadoresAT.MediaMovelSimples;
import online.lucianofelix.visao.FrameInicial;

public class Minerador implements Runnable {

	private List<Float> listMM;
	private List<Ativo> listMinrQtdNeg;
	private ArrayList<Float> valores;
	private MediaMovelSimples calculaMedia;
	private DAOAtivo daoAtv;
	private String codiO;
	private Ativo atv;
	private DAOCotacaoAtivo daoCot;
	private List<CotacaoAtivo> listCot;
	private Operacao ope;
	private OrdemBovespa ordBov;
	private DAOOperacao daoOpe;
	private DAOOrdemBovespa daoOrdBov;
	private DAOFuse daoFuse;
	private ControlaFuse contFuse;
	private String codiOrdem;
	int indexAtv, k, dias;
	private List<Ativo> listAtv;
	private int tamListAtv;
	String idNeg;

	private Fuse fuse;

	// @Override
	// public void run() {
	// // RUN
	//
	// // atv = daoAtv.procurarIdneg(idNeg);
	// while (indexAtv < tamListAtv) {
	// try {
	// // recomendaVenda(dias);
	// FrameInicial.pesquisaOperacao();
	// // indexAtv++;
	// Thread.sleep(200);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	@Override
	public void run() {
		try {
			recomendaVenda(idNeg, dias);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Minerador() {

		calculaMedia = new MediaMovelSimples();
		daoAtv = new DAOAtivo();
		daoCot = new DAOCotacaoAtivo();
		daoOpe = new DAOOperacao();
		daoFuse = new DAOFuse();
		ordBov = new OrdemBovespa();
		daoOrdBov = new DAOOrdemBovespa();
		contFuse = new ControlaFuse();
		// listAtv = daoAtv.procurarTodosLisCot();
		// for (Ativo ativo : listAtv) {
		// System.out.println(ativo.getListCot().get(0).getTotNeg());
		// }

		// tamListAtv = listAtv.size();

		// indexAtv = 0;
		// System.out.println("Comparando com MM de: " + dias + " dias.");
		// System.out.println("Lista contem: " + tamListAtv + " Ativos.");

	}

	public void recomendaVenda(String idNeg, int dias)
			throws InterruptedException {
		JTextArea areaAtu = new JTextArea();
		atv = new Ativo();
		atv.setIdNeg(idNeg);
		atv.setListCot(daoCot.conCotAtvOrdDtAscend(idNeg));
		if (atv.getListCot().get(0).getQuaToTit() > 200) {
			listMM = new ArrayList<Float>();
			valores = new ArrayList<Float>();

			for (int i = 0; i < atv.getListCot().size(); i++) {
				valores.add(atv.getListCot().get(i).getPreFec());
			}

			// System.out.println(atv.getNomeRes());
			// System.out.println(atv.getListCot().size() + " valores de
			// movimento para análise\n");

			listMM = calculaMedia.calcula(dias, valores);
			k = listMM.size() - 1;

			// System.out.println("Tamanho da lista de valores de Fechamento: "
			// + atv.getListCot().size());
			// System.out.println("Tamanho da lista de valores da Média: " +
			// listMM.size());
			// System.out.println("Serão utilizados " + k + " valores.");

			areaAtu.append("Tamanho da lista de valores de Fechamento: "
					+ atv.getListCot().size() + "\n");
			Thread.sleep(150);
			areaAtu.append("Tamanho da lista de valores da Média: "
					+ listMM.size() + "\n");
			Thread.sleep(150);
			areaAtu.append("Serão utilizados " + k + " valores.\n");
			for (int i = 0; i < k; i++) {
				if (listMM.get(i) < valores.get(i)) {
					FrameInicial.getScrVisualiza().setViewportView(areaAtu);
					// System.out.println("Venda recomendada por MM de " + dias
					// + " dias na data: "
					// + atv.getListCot().get(i).getDataCotacao() + " ::: Valor
					// da Média: " + listMM.get(i)
					// + " | VENDA NO PRE FEC: " + valores.get(i) + "\n");
					areaAtu.append(("Venda recomendada por MM de " + dias
							+ " dias  na data: "
							+ atv.getListCot().get(i).getDataCotacao()
							+ " ::: Valor da Média: " + listMM.get(i)
							+ "  | VENDA NO PRE FEC: " + valores.get(i)
							+ "\n"));
					// System.out.println("vendeu!!!\n");
					areaAtu.append("vendeu!!!\n");

					Thread.sleep(15);
					areaAtu.setCaretPosition(areaAtu.getDocument().getLength());
					fuse = new Fuse();
					fuse.setCodiAtivo(atv.getIdNeg());
					fuse.setCodiFuse(contFuse.criaCodigo());
					fuse.setTipoFuse("Vendida");
					fuse.setDataInicio(
							atv.getListCot().get(i).getDataCotacao());
					fuse.setQuant(300);
					fuse.setExecEntrada(true);
					fuse.setPrecStartCompra(valores.get(i));
					fuse.setExecSaida(true);
					ope = new Operacao();
					ope.setCodiAtivo(atv.getIdNeg());
					ope.setCodiOrdem(fuse.getCodiFuse());
					ope.setDataHoraExec(
							atv.getListCot().get(i).getDataCotacao());
					ope.setTipoOp("Venda");
					ope.setQtdPapeis(300);
					ope.setValorPapel(valores.get(i));
					ope.setTotal(ope.getQtdPapeis() * valores.get(i));
					daoFuse.cadastrar(fuse);
					daoOpe.cadastrar(ope);

					int j = i;
					int contDias = 0;
					Thread.sleep(150);
					while (listMM.get(j) < valores.get(j) & (j < k)) {
						contDias++;
						// System.out.println("Permaneceu vendido: " + contDias
						// + " dia(s)\n");
						areaAtu.append("Permaneceu vendido: " + contDias
								+ " dia(s)\n");
						Thread.sleep(150);
						// System.out.println("J: " + j + " I: " + i);
						i = j;
						j++;
					}
					// System.out.println("Saiu da operação vendida...Foram " +
					// contDias + " dias de operação\n");
					// System.out.println("Valor de RECOMPRA no preço de Fec.: "
					// + valores.get(j) + " ::: Valor da média: "
					// + listMM.get(j) + " ::: Na data =>> " +
					// atv.getListCot().get(j).getDataCotacao() + "\n");
					areaAtu.append("Saiu da operação vendida...Foram "
							+ contDias + " de operação\n");
					areaAtu.append("Valor de RECOMPRA no preço de Fec.: "
							+ valores.get(j) + " ::: Valor da média: "
							+ listMM.get(j) + " ::: Na data =>> "
							+ atv.getListCot().get(j).getDataCotacao() + "\n");
					Thread.sleep(150);
					areaAtu.setCaretPosition(areaAtu.getDocument().getLength());
					ope = new Operacao();
					ope.setCodiAtivo(atv.getIdNeg());
					ope.setCodiOrdem(fuse.getCodiFuse());
					ope.setDataHoraExec(
							atv.getListCot().get(j).getDataCotacao());
					ope.setTipoOp("Recompra");
					ope.setQtdPapeis(300);
					ope.setValorPapel(valores.get(j));
					ope.setTotal(ope.getQtdPapeis() * valores.get(j));
					fuse.setExecSaida(true);
					fuse.setPrecStartVenda(ope.getValorPapel());
					fuse.setDataFim(atv.getListCot().get(j).getDataCotacao());
					daoOpe.cadastrar(ope);
					fuse.setLucroPrejuizo(fuse.getPrecStartCompra()
							- fuse.getPrecStartVenda());
					fuse.setTotal(fuse.getLucroPrejuizo() * fuse.getQuant());
					daoFuse.alterar(fuse);
				}
			}
			// FrameInicial.atualizaTela();

		}
	}

	public void recomendaCompra(Ativo atv) throws InterruptedException {
		listMM = new ArrayList<Float>();
		valores = new ArrayList<Float>();
		calculaMedia = new MediaMovelSimples();
		daoAtv = new DAOAtivo();
		daoCot = new DAOCotacaoAtivo();
		// listCot = daoCot.conCotAtvOrdDtAscend(idNeg);
		for (int i = 0; i < listCot.size(); i++) {
			valores.add(listCot.get(i).getPreFec());
		}
		System.out.println(atv.getNomeRes());
		System.out.println(
				atv.getListCot().size() + " valores de movimento para análise");
		int periodo = Integer.parseInt(
				JOptionPane.showInputDialog("Deseja a média de quantos dias?"));
		System.out.println("Comparando com MM de: " + periodo + " dias.");
		listMM = calculaMedia.calcula(periodo, valores);
		int k = listMM.size() - 1;
		System.out.println(
				"Tamanho da lista de valores da Média: " + listMM.size());
		System.out.println(
				"Tamanho da lista de valores de Fechamento: " + listCot.size());
		System.out.println("Serão utilizados " + k + " valores.");
		for (int i = 0; i < k; i++) {
			if (listMM.get(i) > valores.get(i)) {
				System.out.println("Compra recomendada por MM " + periodo
						+ " na data: " + listCot.get(i).getDataCotacao()
						+ " Valor da Média: " + listMM.get(i)
						+ "  | COMPRA NO PRE FEC: " + valores.get(i));
				int j = i;
				int contDias = 0;
				Thread.sleep(300);
				System.out.println("comprou!!!");
				while (listMM.get(j) > valores.get(j) & (j < k)) {
					contDias++;
					System.out.println(
							"Permaneceu comprado: " + contDias + " dia(s)");
					i = j;
					j++;
					Thread.sleep(300);
				}
				System.out.println("Saiu da operação comprada...Foram "
						+ contDias + " de operação!!");
				System.out.println("Valor da média: " + listMM.get(j)
						+ " ::: Valor da venda no Preço de Fec.: "
						+ valores.get(j) + "::: Na data =>> "
						+ listCot.get(j).getDataCotacao() + "\n");
			}
			Thread.sleep(300);
		}
	}

	public List<Ativo> minerarQtdNegMercVista(List<Ativo> listAtvLoc) {
		listMinrQtdNeg = new ArrayList<Ativo>();
		for (int i = 0; i < listAtvLoc.size(); i++) {
			if (listAtvLoc.get(i).getListCot().get(0).getTotNeg() > 200) {
				listMinrQtdNeg.add(listAtvLoc.get(i));
			}
		}
		return listMinrQtdNeg;
	}

}