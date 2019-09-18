package online.lucianofelix.yahoo;

import java.util.List;
import java.util.Set;

import online.lucianofelix.beans.AtivoYahoo;
import online.lucianofelix.beans.CotacaoYahoo;
import online.lucianofelix.dao.DAOAtvYahoo;
import online.lucianofelix.dao.DAOCotacaoYahoo;

public class AtuIntrDay {

	private Set<CotacaoYahoo> hashCotYahoo;
	private List<AtivoYahoo> listAtvYahoo;

	private DAOCotacaoYahoo daoCotYahoo;
	private DAOAtvYahoo daoAtvYahoo;
	private int index;
	private int tamLista;
	private AtualizaYahoo atuYahoo;

	private CotacaoYahoo cotYahoo;
	private AtivoYahoo atvYahoo;

	public AtuIntrDay() {

		daoAtvYahoo = new DAOAtvYahoo();
		daoCotYahoo = new DAOCotacaoYahoo();
		atuYahoo = new AtualizaYahoo();
		listAtvYahoo = daoAtvYahoo.listarOrdIdNeg();
		hashCotYahoo = daoCotYahoo.consUltCotHashSet();
		tamLista = listAtvYahoo.size() - 1;
		index = 0;

	}

	public void atualizar() throws Exception {
		for (int i = 0; i < tamLista; i++) {
			atvYahoo = listAtvYahoo.get(i);
			// atuYahoo.atualizaPasta(atvYahoo.getIdYahoo());
			atuYahoo.atualizaCSV(atvYahoo.getIdYahoo());
			cotYahoo = atuYahoo
					.interpretaCotacaoYahoo(listAtvYahoo.get(i).getIdYahoo());
			System.out.println(
					"tentando atualizar para ===>> " + atvYahoo.getIdYahoo());
			System.out.println(" CotYahoo: => " + cotYahoo.getIdYahoo());
			System.out
					.println(" Timestamp => " + cotYahoo.getDataHoraCotacao());
			System.out.println(" Pre Abert => " + cotYahoo.getPreAbe());
			System.out.println(" Pre Ult => " + cotYahoo.getPreFec());
			System.out.println(" Pre Max => " + cotYahoo.getPreMax());
			System.out.println(" Pre Min => " + cotYahoo.getPreMin());
			System.out.println(" Volume => " + cotYahoo.getVolumeNeg());
			System.out.println(" Variação => " + cotYahoo.getVariacao());
			System.out.println("Data Hora => " + cotYahoo.getDataHoraCotacao());
			System.out.println();
			System.out.println("=================+++=========================");

		}
	}

}
