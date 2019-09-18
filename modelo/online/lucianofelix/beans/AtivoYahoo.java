package online.lucianofelix.beans;

import java.util.List;

public class AtivoYahoo {
	private CotacaoYahoo cotacaoYahoo;
	private String seqAtivo;
	private List<CotacaoYahoo> listCot;
	private List<CotacaoAtivo> listCotComp;
	private String idYahoo;
	private int tpMerc;

	public CotacaoYahoo getCotacaoYahoo() {
		return cotacaoYahoo;
	}

	public void setCotacaoYahoo(CotacaoYahoo cotacaoYahoo) {
		this.cotacaoYahoo = cotacaoYahoo;
	}

	public String getSeqAtivo() {
		return seqAtivo;
	}

	public void setSeqAtivo(String seqAtivo) {
		this.seqAtivo = seqAtivo;
	}

	public List<CotacaoYahoo> getListCot() {
		return listCot;
	}

	public void setListCot(List<CotacaoYahoo> listCot) {
		this.listCot = listCot;
	}

	public String getIdYahoo() {
		return idYahoo;
	}

	public void setIdYahoo(String idYahoo) {
		this.idYahoo = idYahoo;
	}

	public List<CotacaoAtivo> getListCotComp() {
		return listCotComp;
	}

	public void setListCotComp(List<CotacaoAtivo> listCotComp) {
		this.listCotComp = listCotComp;
	}

	public int getTpMerc() {
		return tpMerc;
	}

	public void setTpMerc(int tpMerc) {
		this.tpMerc = tpMerc = 10;
	}
}
