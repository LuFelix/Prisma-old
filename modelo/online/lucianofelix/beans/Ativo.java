package online.lucianofelix.beans;

import java.util.List;

public class Ativo {
	private CotacaoAtivo cotacao;
	private String seqAtivo;
	private String nomeRes;
	private List<CotacaoAtivo> listCot;
	private String idNeg;
	private int tpMerc;

	public String getSeqAtivo() {
		return seqAtivo;
	}

	public void setSeqAtivo(String codigoAtivo) {
		this.seqAtivo = codigoAtivo;
	}

	public String getNomeRes() {
		return nomeRes;
	}

	public void setNomeRes(String nomeRes) {
		this.nomeRes = nomeRes;
	}

	public String getIdNeg() {
		return idNeg;
	}

	public void setIdNeg(String idNeg) {
		this.idNeg = idNeg;
	}

	public int getTpMerc() {
		return tpMerc;
	}

	public void setTpMerc(int tpMerc) {
		this.tpMerc = tpMerc;
	}

	public List<CotacaoAtivo> getListCot() {
		return listCot;
	}

	public void setListCot(List<CotacaoAtivo> listCot) {

		this.listCot = listCot;
	}

	public CotacaoAtivo getCotacao() {
		// TODO Auto-generated method stub
		return cotacao;
	}

	public void setCotacao(CotacaoAtivo cotacao) {
		this.cotacao = cotacao;
	}

}
