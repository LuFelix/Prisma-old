package online.lucianofelix.beans;

import java.util.List;

public class Conta {
	private int seqConta;
	private String tipoConta;
	private String codiConta;
	private String agencia;
	private String conta;
	private String banco;
	private String nomeConta;
	private String tiular;
	private String numCartao;
	private String senha;
	private String descConta;
	private String obsConta;
	private String centroCusto;
	private List<Lancamento> listEntradas;
	private float saldoAtual;

	public float getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(float saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public int getSeqConta() {
		return seqConta;
	}

	public void setSeqConta(int seqConta) {
		this.seqConta = seqConta;
	}

	public String getCodiConta() {
		return codiConta;
	}

	public void setCodiConta(String codiConta) {
		this.codiConta = codiConta;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getNomeConta() {
		return nomeConta;
	}

	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}

	public String getTiular() {
		return tiular;
	}

	public void setTiular(String tiular) {
		this.tiular = tiular;
	}

	public String getNumCartao() {
		return numCartao;
	}

	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDescConta() {
		return descConta;
	}

	public void setDescConta(String descConta) {
		this.descConta = descConta;
	}

	public String getObsConta() {
		return obsConta;
	}

	public void setObsConta(String obsConta) {
		this.obsConta = obsConta;
	}

	public List<Lancamento> getListEntradas() {
		return listEntradas;
	}

	public void setListEntradas(List<Lancamento> listEntradas) {
		this.listEntradas = listEntradas;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(String centroCusto) {
		this.centroCusto = centroCusto;
	}

}
