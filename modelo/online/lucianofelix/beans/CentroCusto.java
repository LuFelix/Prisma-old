package online.lucianofelix.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CentroCusto {

	private int seqcentroCusto;
	private String codiCentroCusto;
	private String nomeCentroCusto;
	private String descCentroCusto;
	private List<Conta> listContas;

	public CentroCusto() {
		super();
		listContas = new ArrayList<Conta>();
	}
	@Override
	public String toString() {
		return getNomeCentroCusto();
	}
	public int getSeqcentroCusto() {
		return seqcentroCusto;
	}

	public void setSeqcentroCusto(int seqcentroCusto) {
		this.seqcentroCusto = seqcentroCusto;
	}

	public String getCodiCentroCusto() {
		return codiCentroCusto;
	}

	public void setCodiCentroCusto(String codiCentroCusto) {
		this.codiCentroCusto = codiCentroCusto;
	}

	public String getNomeCentroCusto() {
		return nomeCentroCusto;
	}

	public void setNomeCentroCusto(String nomeCentroCusto) {
		this.nomeCentroCusto = nomeCentroCusto;
	}

	public String getDescCentroCusto() {
		return descCentroCusto;
	}

	public void setDescCentroCusto(String descCentroCusto) {
		this.descCentroCusto = descCentroCusto;
	}

	public List<Conta> getListContas() {
		return Collections.unmodifiableList(listContas);
	}

	public void setListContas(List<Conta> listContas) {
		this.listContas = listContas;
	}

}
