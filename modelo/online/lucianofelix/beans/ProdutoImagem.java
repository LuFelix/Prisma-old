package online.lucianofelix.beans;

import java.sql.Date;

import javax.swing.ImageIcon;

public class ProdutoImagem {

	private int seqImagemProduto; // Sequencia de gravação da imagem.
	private byte[] imagem; // A imagem em bytes
	private ImageIcon imgIcon;// Os bytes já convertida para imagem
	private Date dataHoraMarcacao; // Data e hora em que esse preço foi gravado.
	private String codiServico;// Referência para a tabela de serviços.
	private String codiProduto; // Referência para a tabela de produtos.
	private String nomeImagem;// Nome para a imagem.
	private String descricaoImagem;// Descrição para a imagem.

	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	public Date getDataHoraMarcacao() {
		return dataHoraMarcacao;
	}
	public void setDataHoraMarcacao(Date dataHoraMarcacao) {
		this.dataHoraMarcacao = dataHoraMarcacao;
	}
	public String getCodiServico() {
		return codiServico;
	}
	public void setCodiServico(String codiServico) {
		this.codiServico = codiServico;
	}
	public String getCodiProduto() {
		return codiProduto;
	}
	public void setCodiProduto(String codiProduto) {
		this.codiProduto = codiProduto;
	}
	public String getDescricaoImagem() {
		return descricaoImagem;
	}
	public void setDescricaoImagem(String descricaoImagem) {
		this.descricaoImagem = descricaoImagem;
	}
	public String getNomeImagem() {
		return nomeImagem;
	}
	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}
	public ImageIcon getImgIcon() {
		return imgIcon;
	}
	public void setImgIcon(ImageIcon imgIcon) {
		this.imgIcon = imgIcon;
	}
	public int getSeqImagemProduto() {
		return seqImagemProduto;
	}
	public void setSeqImagemProduto(int seqImagemProduto) {
		this.seqImagemProduto = seqImagemProduto;
	}

}
