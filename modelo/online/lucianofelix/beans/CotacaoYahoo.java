package online.lucianofelix.beans;

import java.sql.Timestamp;

public class CotacaoYahoo implements Comparable<CotacaoYahoo> {

	public void setIdYahoo(String idYahoo) {
		this.idYahoo = idYahoo;
	}

	public void setDataHoraCotacao(Timestamp dataHoraCotacao) {
		this.dataHoraCotacao = dataHoraCotacao;
	}

	public void setPreAbe(float preAbe) {
		this.preAbe = preAbe;
	}

	public void setPreFec(float preFec) {
		this.preFec = preFec;
	}

	public void setPreMax(float preMax) {
		this.preMax = preMax;
	}

	public void setPreMin(float preMin) {
		this.preMin = preMin;
	}

	public void setVolumeNeg(float volumeNeg) {
		this.volumeNeg = volumeNeg;
	}

	public void setVariacao(float variacao) {
		this.variacao = variacao;
	}

	public void setSeqCotacao(int seqCotacao) {
		this.seqCotacao = seqCotacao;
	}

	private int seqCotacao;
	private String idYahoo;
	private Timestamp dataHoraCotacao;
	private float preAbe;
	private float preFec;
	private float preMax;
	private float preMin;
	private float volumeNeg;
	private float variacao;

	// TODO Nova cotação com: idAtivo, dataCotacao, preAbe, preFec, preMax,
	// preMin, volumeNeg, QuatotTit, totNe.
	// "ABCB4.SA",8.77,"9/14/2015","1:20pm",-0.09,8.80,8.81,8.70,155200

	public CotacaoYahoo() {
		super();
		setSeqCotacao(0);

	}

	public int getSeqCotacao() {
		return seqCotacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataHoraCotacao == null) ? 0 : dataHoraCotacao.hashCode());
		result = prime * result + ((idYahoo == null) ? 0 : idYahoo.hashCode());
		result = prime * result + Float.floatToIntBits(preAbe);
		result = prime * result + Float.floatToIntBits(preFec);
		result = prime * result + Float.floatToIntBits(preMax);
		result = prime * result + Float.floatToIntBits(preMin);
		result = prime * result + seqCotacao;
		result = prime * result + Float.floatToIntBits(variacao);
		result = prime * result + Float.floatToIntBits(volumeNeg);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CotacaoYahoo other = (CotacaoYahoo) obj;
		if (dataHoraCotacao == null) {
			if (other.dataHoraCotacao != null)
				return false;
		} else if (!dataHoraCotacao.equals(other.dataHoraCotacao))
			return false;
		if (idYahoo == null) {
			if (other.idYahoo != null)
				return false;
		} else if (!idYahoo.equals(other.idYahoo))
			return false;
		if (Float.floatToIntBits(preAbe) != Float.floatToIntBits(other.preAbe))
			return false;
		if (Float.floatToIntBits(preFec) != Float.floatToIntBits(other.preFec))
			return false;
		if (Float.floatToIntBits(preMax) != Float.floatToIntBits(other.preMax))
			return false;
		if (Float.floatToIntBits(preMin) != Float.floatToIntBits(other.preMin))
			return false;
		if (seqCotacao != other.seqCotacao)
			return false;
		if (Float.floatToIntBits(variacao) != Float.floatToIntBits(other.variacao))
			return false;
		if (Float.floatToIntBits(volumeNeg) != Float.floatToIntBits(other.volumeNeg))
			return false;
		return true;
	}

	public String getIdYahoo() {
		return idYahoo;
	}

	public Timestamp getDataHoraCotacao() {
		return dataHoraCotacao;
	}

	public float getPreAbe() {
		return preAbe;
	}

	public float getPreFec() {
		return preFec;
	}

	public float getPreMax() {
		return preMax;
	}

	public float getPreMin() {
		return preMin;
	}

	public float getVolumeNeg() {
		return volumeNeg;
	}

	public float getVariacao() {
		return variacao;
	}

	@Override
	public int compareTo(CotacaoYahoo CotYahoo) {

		if (this.preFec < CotYahoo.getPreFec()) {
			return -1;
		}

		return 0;
	}

}
