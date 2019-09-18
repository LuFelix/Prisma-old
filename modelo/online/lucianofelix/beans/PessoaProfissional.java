package online.lucianofelix.beans;

public class PessoaProfissional {

	private int seqPessProf;
	private String codiPess;
	private String codiProf;
	private String nomeProf;
	private String docFunc;
	private int pis;
	private boolean optante;

	public String getCodiPess() {
		return codiPess;
	}
	public void setCodiPess(String codiPess) {
		this.codiPess = codiPess;
	}
	public String getCodiProf() {
		return codiProf;
	}
	public void setCodiProf(String codiProf) {
		this.codiProf = codiProf;
	}
	public String getNomeProf() {
		return nomeProf;
	}
	public void setNomeProf(String nomeProf) {
		this.nomeProf = nomeProf;
	}
	public String getDocFunc() {
		return docFunc;
	}
	public void setDocFunc(String docFunc) {
		this.docFunc = docFunc;
	}
	public int getPis() {
		return pis;
	}
	public void setPis(int pis) {
		this.pis = pis;
	}
	public boolean isOptante() {
		return optante;
	}
	public void setOptante(boolean optante) {
		this.optante = optante;
	}
	public int getSeqPessProf() {
		return seqPessProf;
	}
	public void setSeqPessProf(int seqPessProf) {
		this.seqPessProf = seqPessProf;
	}

}
