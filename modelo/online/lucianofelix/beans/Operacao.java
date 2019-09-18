package online.lucianofelix.beans;

import java.sql.Timestamp;

public class Operacao {
	private int seqOperacao;
	private float valorPapel;
	private float corretagem;
	private float total;
	private int qtdPapeis;
	private String codiFuse;
	private String codiOrdem;
	private String codiAtivo;
	private String codiOperacao;
	private String tipoOp;
	private Timestamp dataHoraExec;

	public Operacao() {
	}

	public int getSeqOperacao() {
		return seqOperacao;
	}

	public void setSeqOperacao(int seqOperacao) {
		this.seqOperacao = seqOperacao;
	}

	public String getCodiOperacao() {
		return codiOperacao;
	}

	public void setCodiOperacao(String codiOperacao) {
		this.codiOperacao = codiOperacao;
	}

	public String getCodiAtivo() {
		return codiAtivo;
	}

	public void setCodiAtivo(String codiAtivo) {
		this.codiAtivo = codiAtivo;
	}

	public Timestamp getDataHoraExec() {
		return dataHoraExec;
	}

	public void setDataHoraExec(Timestamp dataHoraExec) {
		this.dataHoraExec = dataHoraExec;
	}

	public String getTipoOp() {
		return tipoOp;
	}

	public void setTipoOp(String tipoOp) {
		this.tipoOp = tipoOp;
	}

	public float getValorPapel() {
		return valorPapel;
	}

	public void setValorPapel(float valorPapel) {
		this.valorPapel = valorPapel;
	}

	public String getCodiOrdem() {
		return codiOrdem;
	}

	public void setCodiOrdem(String codiOrdem) {
		this.codiOrdem = codiOrdem;
	}

	public int getQtdPapeis() {
		return qtdPapeis;
	}

	public void setQtdPapeis(int qtdPapeis) {
		this.qtdPapeis = qtdPapeis;
	}

	public float getCorretagem() {
		return corretagem;
	}

	public void setCorretagem(float corretagem) {
		this.corretagem = corretagem;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getCodiFuse() {
		return codiFuse;
	}

	public void setCodiFuse(String codiFuse) {
		this.codiFuse = codiFuse;
	}

}
