package online.lucianofelix.util;

import java.util.List;
import java.util.Set;

import online.lucianofelix.beans.AtivoYahoo;
import online.lucianofelix.beans.CotacaoYahoo;
import online.lucianofelix.dao.DAOAtvYahoo;
import online.lucianofelix.dao.DAOCotacaoYahoo;
import online.lucianofelix.yahoo.AtualizaYahoo;

public class AtualizaCotacaoAutHashSet implements Runnable {

	private Set<CotacaoYahoo> hashCotYahoo;
	private List<AtivoYahoo> listAtvYahoo;

	private DAOCotacaoYahoo daoCotYahoo;
	private DAOAtvYahoo daoAtvYahoo;
	private int index;
	private int tamLista;
	private AtualizaYahoo atuYahoo;

	private CotacaoYahoo cotYahoo;
	private AtivoYahoo atvYahoo;

	public AtualizaCotacaoAutHashSet() {
		index = 0;
		daoAtvYahoo = new DAOAtvYahoo();
		daoCotYahoo = new DAOCotacaoYahoo();
		atuYahoo = new AtualizaYahoo();
		listAtvYahoo = daoAtvYahoo.listarOrdIdNeg();
		hashCotYahoo = daoCotYahoo.consUltCotHashSet();
		System.out.println("Tamanho lista de Ativos: " + listAtvYahoo.size());
		tamLista = listAtvYahoo.size() - 1;
		// System.out.println("Hash está vazio? " + hashCotYahoo.isEmpty());
		// System.out.println("Tamanho do hash: " + hashCotYahoo.size());
		// FrameInicial.setTabela(contAtv.pesqAtivoTabelaUltimas());
		// FrameInicial.getScrVisualiza().setViewportView(FrameInicial.getTabela());
		// FrameInicial.getScrLista().setViewportView(null);
	}

	@Override
	public void run() {

		while (true) {
			// System.out.println("Tamanho do HashSet de Últimas: " +
			// hashCotYahoo.size());
			cotYahoo = new CotacaoYahoo();
			atvYahoo = listAtvYahoo.get(index);
			try {
				atuYahoo.atualizaPasta(atvYahoo.getIdYahoo());
				cotYahoo = atuYahoo
						.interpretaCotacaoYahoo(atvYahoo.getIdYahoo());
				System.out.println(" CotYahoo: => " + cotYahoo.getIdYahoo());
				System.out.println(
						" Timestamp => " + cotYahoo.getDataHoraCotacao());
				System.out.println(" Pre Abert => " + cotYahoo.getPreAbe());
				System.out.println(" Pre Ult => " + cotYahoo.getPreFec());
				System.out.println(" Pre Max => " + cotYahoo.getPreMax());
				System.out.println(" Pre Min => " + cotYahoo.getPreMin());
				System.out.println(" Volume => " + cotYahoo.getVolumeNeg());
				System.out.println(" Variação => " + cotYahoo.getVariacao());
				System.out.println(
						"Data Hora => " + cotYahoo.getDataHoraCotacao());
				System.out.println();

				System.out.println("HashSet contem essa cotação baixada? "
						+ hashCotYahoo.contains(cotYahoo));

				if (!hashCotYahoo.contains(cotYahoo)) {
					System.out.println("Preparando para inserir...");
					daoCotYahoo.inserir(cotYahoo);
					// FrameInicial.setTabela(contAtv.pesqAtivoTabelaUltimas());
					// FrameInicial.getScrVisualiza()
					// .setViewportView(FrameInicial.getTabela());
					// FrameInicial.getScrVisualiza().getVerticalScrollBar()
					// .setValue(1000);
					System.out.println(
							"Cotacao: " + cotYahoo.getDataHoraCotacao());
					// System.out.println("Eixo Y " + contAtv.getEixoY());
					// FrameInicial.getScrLista().setViewportView(null);

					// FrameInicial.atualizaTela();
					System.out.println("Inserido...");
					System.out.println();
					hashCotYahoo = daoCotYahoo.consUltCotHashSet();
					System.out.println("Tamanho do Hash de Últimas: "
							+ hashCotYahoo.size());
					System.out.println("Index quando não contem " + index);
					index++;
				} else {
					// FrameInicial.setTabela(contAtv.pesqAtivoTabelaUltimas());
					// FrameInicial.getScrVisualiza()
					// .setViewportView(FrameInicial.getTabela());
					// FrameInicial.getScrVisualiza().getVerticalScrollBar()
					// .setValue(1800);
					// System.out.println("Eixo Y " + contAtv.getEixoY());
					// FrameInicial.getScrLista().setViewportView(null);
					System.out.println("Cotação mais atual: "
							+ cotYahoo.getDataHoraCotacao());
					System.out.println("Index quando contem " + index);
					hashCotYahoo = daoCotYahoo.consUltCotHashSet();
					index++;
				}
				Thread.sleep(5);
				if (index == tamLista) {

					index = 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
