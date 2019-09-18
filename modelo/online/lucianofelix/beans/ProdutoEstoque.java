package online.lucianofelix.beans;

import java.sql.Date;

public class ProdutoEstoque {

	private int seqMovimento;
	private int quantidade;
	private String codiEstoque;
	private String codiProduto;
	private String codiPedido;
	private String tipoMovimento;
	private Date dataHoraMovimento;

	public String getCodiEstoque() {
		return codiEstoque;
	}

	public void setCodiEstoque(String codiEstoque) {
		this.codiEstoque = codiEstoque;
	}

	public Date getDataHoraMovimento() {
		return dataHoraMovimento;
	}

	public void setDataHoraMovimento(Date dataHoraMovimento) {
		this.dataHoraMovimento = dataHoraMovimento;
	}

	public String getCodiProduto() {
		return codiProduto;
	}

	public void setCodiProduto(String codiProduto) {
		this.codiProduto = codiProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getCodiPedido() {
		return codiPedido;
	}

	public void setCodiPedido(String codiPedido) {
		this.codiPedido = codiPedido;
	}

	public String getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public int getSeqMovimento() {
		return seqMovimento;
	}

	public void setSeqMovimento(int seqMovimento) {
		this.seqMovimento = seqMovimento;
	}

}
