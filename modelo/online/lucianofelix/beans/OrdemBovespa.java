package online.lucianofelix.beans;

import java.sql.Timestamp;

public class OrdemBovespa {

	private int seqOrdBovespa;
	private int qtdPapeis;
	private int validade;
	private float valorOrdem;
	private String codiOrdem;
	private String codiFuse;
	private String tipoOrdem;
	private String codiAtivo;
	private boolean executou;
	private Timestamp dataHoraExec;

	public int getValidade() {
		return validade;
	}

	public void setValidade(int i) {
		this.validade = i;
	}

	public int getSeqOrdBovespa() {
		return seqOrdBovespa;
	}

	public void setSeqOrdBovespa(int seqOrdBovespa) {
		this.seqOrdBovespa = seqOrdBovespa;
	}

	public float getValorOrdem() {
		return valorOrdem;
	}

	public void setValorOrdem(float valorOrdem) {
		this.valorOrdem = valorOrdem;
	}

	public int getQtdPapeis() {
		return qtdPapeis;
	}

	public void setQtdPapeis(int qtdPapeis) {
		this.qtdPapeis = qtdPapeis;
	}

	public String getCodiOrdem() {
		return codiOrdem;
	}

	public void setCodiOrdem(String codiOrdem) {
		this.codiOrdem = codiOrdem;
	}

	public String getCodiFuse() {
		return codiFuse;
	}

	public void setCodiFuse(String codiFuse) {
		this.codiFuse = codiFuse;
	}

	public String getTipoOrdem() {
		return tipoOrdem;
	}

	public void setTipoOrdem(String tipoOrdem) {
		this.tipoOrdem = tipoOrdem;
	}

	public String getCodiAtivo() {
		return codiAtivo;
	}

	public void setCodiAtivo(String codiAtivo) {
		this.codiAtivo = codiAtivo;
	}

	public boolean isExecutou() {
		return executou;
	}

	public void setExecutou(boolean executou) {
		this.executou = executou;
	}

	public Timestamp getDataHoraExec() {
		return dataHoraExec;
	}

	public void setDataHoraExec(Timestamp dataHoraExec) {
		this.dataHoraExec = dataHoraExec;
	}

}
