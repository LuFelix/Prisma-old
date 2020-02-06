package online.lucianofelix.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Lancamento {

	private int sequencia;// Sequencial de movimentos.
	private String codiConta;// Código chave para a tabela de contas
	private String codiCondPag;// Chave para a tabela de condição de pagamento.
	private String codiPedido; // Referência para a tabela de pedidos.
	private String codiPessoa;// Referência para a tabela de pessoas.
	private Timestamp dtHrLanc; // Data hora do lancamento.
	private Timestamp dtHrVenc; // Data hora do vencimento.
	private Timestamp dtHrReceb;// Data hora do recebimento.
	private BigDecimal valor;// O valor do movimento
	private String obsLancamento; // Uma observação sobre o recebimento.
	private String tipoLancamento; // Movimento de entrada ou de saída;
	private String especieLancamento;// Espécie de lançamento

	public int getSequencia() {
		return sequencia;
	}

	public void setSequencia(int sequencia) {
		this.sequencia = sequencia;
	}

	public String getCodiCondPag() {
		return codiCondPag;
	}

	public void setCodiCondPag(String codiCondPag) {
		this.codiCondPag = codiCondPag;
	}

	public String getCodiPedido() {
		return codiPedido;
	}

	public void setCodiPedido(String codiPedido) {
		this.codiPedido = codiPedido;
	}

	public String getCodiPessoa() {
		return codiPessoa;
	}

	public void setCodiPessoa(String codiPessoa) {
		this.codiPessoa = codiPessoa;
	}

	public String getObsLancamento() {
		return obsLancamento;
	}

	public void setObsLancamento(String obsLancamento) {
		this.obsLancamento = obsLancamento;
	}

	public String getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public String getEspecieLancamento() {
		return especieLancamento;
	}

	public void setEspecieLancamento(String especieLancamento) {
		this.especieLancamento = especieLancamento;
	}

	public String getCodiConta() {
		return codiConta;
	}

	public void setCodiConta(String codiConta) {
		this.codiConta = codiConta;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * @return the dtHrLanc
	 */
	public Timestamp getDtHrLanc() {
		return dtHrLanc;
	}

	/**
	 * @param dtHrLanc
	 *            the dtHrLanc to set
	 */
	public void setDtHrLanc(Timestamp dtHrLanc) {
		this.dtHrLanc = dtHrLanc;
	}

	/**
	 * @return the dtHrVenc
	 */
	public Timestamp getDtHrVenc() {
		return dtHrVenc;
	}

	/**
	 * @param dtHrVenc
	 *            the dtHrVenc to set
	 */
	public void setDtHrVenc(Timestamp dtHrVenc) {
		this.dtHrVenc = dtHrVenc;
	}

	/**
	 * @return the dtHrReceb
	 */
	public Timestamp getDtHrReceb() {
		return dtHrReceb;
	}

	/**
	 * @param dtHrReceb
	 *            the dtHrReceb to set
	 */
	public void setDtHrReceb(Timestamp dtHrReceb) {
		this.dtHrReceb = dtHrReceb;
	}

}
