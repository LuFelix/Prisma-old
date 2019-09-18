package online.lucianofelix.visao;

import java.sql.Timestamp;

public class Contrato {

	private int seqContrato;
	private String codiContrato;
	private int seqEmp;
	private int codiFunc;
	private Timestamp dataAdmissao;
	private Timestamp dataDemissao;

	public int getSeqContrato() {
		return seqContrato;
	}
	public void setSeqContrato(int seqContrato) {
		this.seqContrato = seqContrato;
	}
	public String getCodiContrato() {
		return codiContrato;
	}
	public void setCodiContrato(String codiContrato) {
		this.codiContrato = codiContrato;
	}
	public int getSeqEmp() {
		return seqEmp;
	}
	public void setSeqEmp(int seqEmp) {
		this.seqEmp = seqEmp;
	}
	public int getCodiFunc() {
		return codiFunc;
	}
	public void setCodiFunc(int codiFunc) {
		this.codiFunc = codiFunc;
	}
	public Timestamp getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Timestamp dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public Timestamp getDataDemissao() {
		return dataDemissao;
	}
	public void setDataDemissao(Timestamp dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

}
