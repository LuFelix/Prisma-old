package online.lucianofelix.beans;

public class Indicadores {

	private int seqInd;
	private int periodo;
	private String codiFuse;
	private String nomeIndi;

	public int getSeqInd() {
		return seqInd;
	}

	public void setSeqInd(int seqInd) {
		this.seqInd = seqInd;
	}

	public String getNomeIndi() {
		return nomeIndi;
	}

	public void setNomeIndi(String nomeIndi) {
		this.nomeIndi = nomeIndi;
	}

	/**
	 * @return the periodo
	 */
	public int getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo
	 *            the periodo to set
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return the codiFuse
	 */
	public String getCodiFuse() {
		return codiFuse;
	}

	/**
	 * @param codiFuse
	 *            the codiFuse to set
	 */
	public void setCodiFuse(String codiFuse) {
		this.codiFuse = codiFuse;
	}

}
