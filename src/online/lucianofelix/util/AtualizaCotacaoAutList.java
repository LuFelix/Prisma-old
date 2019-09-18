package online.lucianofelix.util;

import java.util.List;

import online.lucianofelix.beans.AtivoYahoo;
import online.lucianofelix.beans.CotacaoYahoo;
import online.lucianofelix.dao.DAOAtvYahoo;
import online.lucianofelix.dao.DAOCotacaoYahoo;
import online.lucianofelix.yahoo.AtualizaYahoo;

public class AtualizaCotacaoAutList implements Runnable {

	private List<AtivoYahoo> listAtvYahoo;
	private List<CotacaoYahoo> listUltCotacoes;

	private DAOAtvYahoo daoAtvYahoo;
	private DAOCotacaoYahoo daoCotYahoo;

	private AtualizaYahoo atuYahoo;

	private AtivoYahoo atvYahoo;
	private CotacaoYahoo cotYahoo;

	private int index;
	private int tamLista;

	public AtualizaCotacaoAutList() {

		index = 0;
		daoAtvYahoo = new DAOAtvYahoo();
		daoCotYahoo = new DAOCotacaoYahoo();
		atuYahoo = new AtualizaYahoo();
		listAtvYahoo = daoAtvYahoo.listarOrdIdNeg();
		listUltCotacoes = daoCotYahoo.consultaUltimasCotacoes();
		System.out.println("Tamanho lista de Ativos: " + listAtvYahoo.size());
		tamLista = listAtvYahoo.size() - 1;
		System.out.println(
				"Lista de últimas está vazio? " + listUltCotacoes.isEmpty());
		System.out.println(
				"Tamanho da lista de últimas: " + listUltCotacoes.size());

	}

	@Override
	public void run() {

		System.out.println(
				"Tamanho da lista de últimos: " + listUltCotacoes.size());

		while (true) {
			cotYahoo = new CotacaoYahoo();
			atvYahoo = listAtvYahoo.get(index);
			try {
				atuYahoo.atualizaPasta(atvYahoo.getIdYahoo());
				cotYahoo = atuYahoo
						.interpretaCotacaoYahoo(atvYahoo.getIdYahoo());
				System.out.println("CotYahoo: => " + cotYahoo.getIdYahoo());
				System.out.println(
						" Timestamp => " + cotYahoo.getDataHoraCotacao());
				System.out.println(" Pre_abert => " + cotYahoo.getPreAbe());
				System.out.println(" Pre Fech  => " + cotYahoo.getPreFec());
				System.out.println(" Pre maxi  => " + cotYahoo.getPreMax());
				System.out.println(" Pre Mini  => " + cotYahoo.getPreMin());
				System.out.println(" Volume    => " + cotYahoo.getVolumeNeg());
				System.out.println(" Variação  => " + cotYahoo.getVariacao());
				System.out.println();

				System.out.println("A lista de últimas contem cotYahoo? "
						+ listUltCotacoes.contains(cotYahoo));

				if (!listUltCotacoes.contains(cotYahoo)) {
					daoCotYahoo.inserir(cotYahoo);
					System.out.println("Inserido...");
					System.out.println();
					listUltCotacoes = daoCotYahoo.consultaUltimasCotacoes();
					System.out.println("Tamanho da Lista de últimas: "
							+ listUltCotacoes.size());
				} else {
					System.out.println("Cotação mais atual: "
							+ cotYahoo.getDataHoraCotacao());
				}
				Thread.sleep(5);
				if (index < tamLista) {
					index++;
				} else {
					index = 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
