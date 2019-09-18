/**
 * 
 */
package online.lucianofelix.beans;

import java.sql.Date;

/**
 * @author Luciano Felix
 * @category Classe que detém o modelo de uma tabela de preços com os valores de
 *           desconto ou acréscimo, loja e o tipo de tabela a que se destina.
 *
 */
public class TabelaPreco {

	private int seqTabelaPrecos;// Sequencia de criação das tabelas.
	private String codiTabela; // Código composto administração da tabela.
	private Date dataCriacao; // O momento de criação da tabela.
	private Date dataInicio; // A data de início dessa tabela de preços.
	private Date dataFim; // A data em que a tabela deve terminar.
	private String tipoTabela; // O tipo de tabela.
	private String descTabela; // A descrição da tabela.
	private String codiFornecedor; // Fornecedor dos preços dessa tabela.
	private String codiLoja; // Referência da loja para essa tabela.
	private String classeTabela; // Classificação. Pode ser normal, promocional.
	private String nomeTabela; // Nome da tabela
	private String codiProduto;// Código do produto ao qual a lista pertence

	/**
	 * @return the seqTabelaPrecos
	 */
	public int getSeqTabelaPrecos() {
		return seqTabelaPrecos;
	}

	/**
	 * @param seqTabelaPrecos
	 *            the seqTabelaPrecos to set
	 */
	public void setSeqTabelaPrecos(int seqTabelaPrecos) {
		this.seqTabelaPrecos = seqTabelaPrecos;
	}

	/**
	 * @return the dataCriacao
	 */
	public Date getDataCriacao() {
		return dataCriacao;
	}

	/**
	 * @return the codiTabela
	 */
	public String getCodiTabela() {
		return codiTabela;
	}

	/**
	 * @param codiTabela
	 *            the codiTabela to set
	 */
	public void setCodiTabela(String codiTabela) {
		this.codiTabela = codiTabela;
	}

	/**
	 * @param dataCriacao
	 *            the dataCriacao to set
	 */
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	/**
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param date
	 *            the dataInicio to set
	 */
	public void setDataInicio(Date date) {
		this.dataInicio = date;
	}

	/**
	 * @return the dataFim
	 */
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * @param date
	 *            the dataFim to set
	 */
	public void setDataFim(Date date) {
		this.dataFim = date;
	}

	/**
	 * @return the tipoTabela
	 */
	public String getTipoTabela() {
		return tipoTabela;
	}

	/**
	 * @param tipoTabela
	 *            the tipoTabela to set
	 */
	public void setTipoTabela(String tipoTabela) {
		this.tipoTabela = tipoTabela;
	}

	/**
	 * @return the descTabela
	 */
	public String getDescTabela() {
		return descTabela;
	}

	/**
	 * @param descTabela
	 *            the descTabela to set
	 */
	public void setDescTabela(String descTabela) {
		this.descTabela = descTabela;
	}

	/**
	 * @return the codiFornecedor
	 */
	public String getCodiFornecedor() {
		return codiFornecedor;
	}

	/**
	 * @param codiFornecedor
	 *            the codiFornecedor to set
	 */
	public void setCodiFornecedor(String codiFornecedor) {
		this.codiFornecedor = codiFornecedor;
	}

	/**
	 * @return the codiLoja
	 */
	public String getCodiLoja() {
		return codiLoja;
	}

	/**
	 * @return the nomeTabela
	 */
	public String getNomeTabela() {
		return nomeTabela;
	}

	/**
	 * @param nomeTabela
	 *            the nomeTabela to set
	 */
	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}

	/**
	 * @param codiLoja
	 *            the codiLoja to set
	 */
	public void setCodiLoja(String codiLoja) {
		this.codiLoja = codiLoja;
	}

	/**
	 * @return the classeTabela
	 */
	public String getClasseTabela() {
		return classeTabela;
	}

	/**
	 * @param classeTabela
	 *            the classeTabela to set
	 */
	public void setClasseTabela(String classeTabela) {
		this.classeTabela = classeTabela;
	}

	/**
	 * @return the codiProduto
	 */
	public String getCodiProduto() {
		return codiProduto;
	}

	/**
	 * @param codiProduto
	 *            the codiProduto to set
	 */
	public void setCodiProduto(String codiProduto) {
		this.codiProduto = codiProduto;
	}

}
