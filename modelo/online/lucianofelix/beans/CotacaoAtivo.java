package online.lucianofelix.beans;

import java.sql.Timestamp;

public class CotacaoAtivo {

	private int seqCotacao;
	private String idAtivo;
	private Timestamp dataCotacao;
	private float preAbe;
	private float preFec;
	private float preMax;
	private float preMin;
	private float volumeNeg;
	private long quaToTit;
	private long totNeg;

	// TODO Nova cotação com: idAtivo, dataCotacao, preAbe, preFec, preMax,
	// preMin, volumeNeg, QuatotTit, totNe.
	public CotacaoAtivo(String idAtivo, Timestamp dataCotacao, float preAbe, float preFec, float preMax, float preMin,
			float volumeNeg, long quaTotTit, long totNeg) {
		super();
		this.idAtivo = idAtivo;
		this.dataCotacao = dataCotacao;
		this.preAbe = preAbe;
		this.preFec = preFec;
		this.preMax = preMax;
		this.preMin = preMin;
		this.volumeNeg = volumeNeg;
		this.quaToTit = quaTotTit;
		this.totNeg = totNeg;
	}

	public Timestamp getDataCotacao() {
		return dataCotacao;
	}

	public float getVolumeNeg() {
		return volumeNeg;
	}

	public long getQuaToTit() {
		return quaToTit;
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

	public long getTotNeg() {
		return totNeg;
	}

	public int getSeqCotacao() {
		return seqCotacao;
	}

	public String getIdAtivo() {
		return idAtivo;
	}

	public void setSeqCotacao(int seqCotacao) {
		this.seqCotacao = seqCotacao;
	}

}
