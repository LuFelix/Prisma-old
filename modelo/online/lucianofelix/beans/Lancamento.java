package online.lucianofelix.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Lancamento {

	private int sequencia;// Sequencial de movimentos.
	private String codiCtaReceber;// numero do título
	private List<Lancamento> listbaixas; // lsta de baixas do titulo
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
	private String tipoDocvinculado;// Tipo de documento que gerou a
									// fatura(pedido, contrato, etc)
	/**
	 * @return the sequencia
	 */
	public int getSequencia() {
		return sequencia;
	}
	/**
	 * @param sequencia
	 *            the sequencia to set
	 */
	public void setSequencia(int sequencia) {
		this.sequencia = sequencia;
	}
	/**
	 * @return the codiCtaReceber
	 */
	public String getCodiCtaReceber() {
		return codiCtaReceber;
	}
	/**
	 * @param codiCtaReceber
	 *            the codiCtaReceber to set
	 */
	public void setCodiCtaReceber(String codiCtaReceber) {
		this.codiCtaReceber = codiCtaReceber;
	}
	/**
	 * @return the listbaixas
	 */
	public List<Lancamento> getListbaixas() {
		return listbaixas;
	}
	/**
	 * @param listbaixas
	 *            the listbaixas to set
	 */
	public void setListbaixas(List<Lancamento> listbaixas) {
		this.listbaixas = listbaixas;
	}
	/**
	 * @return the codiConta
	 */
	public String getCodiConta() {
		return codiConta;
	}
	/**
	 * @param codiConta
	 *            the codiConta to set
	 */
	public void setCodiConta(String codiConta) {
		this.codiConta = codiConta;
	}
	/**
	 * @return the codiCondPag
	 */
	public String getCodiCondPag() {
		return codiCondPag;
	}
	/**
	 * @param codiCondPag
	 *            the codiCondPag to set
	 */
	public void setCodiCondPag(String codiCondPag) {
		this.codiCondPag = codiCondPag;
	}
	/**
	 * @return the codiPedido
	 */
	public String getCodiPedido() {
		return codiPedido;
	}
	/**
	 * @param codiPedido
	 *            the codiPedido to set
	 */
	public void setCodiPedido(String codiPedido) {
		this.codiPedido = codiPedido;
	}
	/**
	 * @return the codiPessoa
	 */
	public String getCodiPessoa() {
		return codiPessoa;
	}
	/**
	 * @param codiPessoa
	 *            the codiPessoa to set
	 */
	public void setCodiPessoa(String codiPessoa) {
		this.codiPessoa = codiPessoa;
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
	 * @return the obsLancamento
	 */
	public String getObsLancamento() {
		return obsLancamento;
	}
	/**
	 * @param obsLancamento
	 *            the obsLancamento to set
	 */
	public void setObsLancamento(String obsLancamento) {
		this.obsLancamento = obsLancamento;
	}
	/**
	 * @return the tipoLancamento
	 */
	public String getTipoLancamento() {
		return tipoLancamento;
	}
	/**
	 * @param tipoLancamento
	 *            the tipoLancamento to set
	 */
	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
	/**
	 * @return the especieLancamento
	 */
	public String getEspecieLancamento() {
		return especieLancamento;
	}
	/**
	 * @param especieLancamento
	 *            the especieLancamento to set
	 */
	public void setEspecieLancamento(String especieLancamento) {
		this.especieLancamento = especieLancamento;
	}
	/**
	 * @return the tipoDocvinculado
	 */
	public String getTipoDocvinculado() {
		return tipoDocvinculado;
	}
	/**
	 * @param tipoDocvinculado
	 *            the tipoDocvinculado to set
	 */
	public void setTipoDocvinculado(String tipoDocvinculado) {
		this.tipoDocvinculado = tipoDocvinculado;
	}

}
