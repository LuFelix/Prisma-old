package online.lucianofelix.beans;

import java.sql.Timestamp;

public class OrdemServico {

	private String seqOrdServ;
	private String codiOrdServ;
	private String descOrdServ;
	private String justOrdServ;
	private String ObsOrdServ;
	private String codiStatOrdServ;
	private String nomeStatus;;
	private String codiPessoa;
	private String nomePesOrdServ;
	private String codiServico;
	private String nomeServOrdServ;
	private Timestamp horaAbertura;
	private Timestamp horaVencimento;
	private Timestamp horaInicio;

	public String getSeqOrdServ() {
		return seqOrdServ;
	}

	public void setSeqOrdServ(String seqOrdServ) {
		this.seqOrdServ = seqOrdServ;
	}

	public String getCodiOrdServ() {
		return codiOrdServ;
	}

	public void setCodiOrdServ(String codiOrdServ) {
		this.codiOrdServ = codiOrdServ;
	}

	public String getDescOrdServ() {
		return descOrdServ;
	}

	public void setDescOrdServ(String descOrdServ) {
		this.descOrdServ = descOrdServ;
	}

	public String getJustOrdServ() {
		return justOrdServ;
	}

	public void setJustOrdServ(String justOrdServ) {
		this.justOrdServ = justOrdServ;
	}

	public String getObsOrdServ() {
		return ObsOrdServ;
	}

	public void setObsOrdServ(String obsOrdServ) {
		ObsOrdServ = obsOrdServ;
	}

	public String getCodiStatOrdServ() {
		return codiStatOrdServ;
	}

	public void setCodiStatOrdServ(String codiStatOrdServ) {
		this.codiStatOrdServ = codiStatOrdServ;
	}

	public String getNomeStatus() {
		return nomeStatus;
	}

	public void setNomeStatus(String nomeStatus) {
		this.nomeStatus = nomeStatus;
	}

	public String getCodiPessoa() {
		return codiPessoa;
	}

	public void setCodiPessoa(String codiPessoa) {
		this.codiPessoa = codiPessoa;
	}

	public String getNomePesOrdServ() {
		return nomePesOrdServ;
	}

	public void setNomePesOrdServ(String nomePesOrdServ) {
		this.nomePesOrdServ = nomePesOrdServ;
	}

	public String getCodiServico() {
		return codiServico;
	}

	public void setCodiServico(String codiServico) {
		this.codiServico = codiServico;
	}

	public String getNomeServOrdServ() {
		return nomeServOrdServ;
	}

	public void setNomeServOrdServ(String nomeServOrdServ) {
		this.nomeServOrdServ = nomeServOrdServ;
	}

	public Timestamp getHoraAbertura() {
		return horaAbertura;
	}

	public void setHoraAbertura(Timestamp horaAbertura) {
		this.horaAbertura = horaAbertura;
	}

	public Timestamp getHoraVencimento() {
		return horaVencimento;
	}

	public void setHoraVencimento(Timestamp horaVencimento) {
		this.horaVencimento = horaVencimento;
	}

	public Timestamp getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Timestamp horaInicio) {
		this.horaInicio = horaInicio;
	}

}
