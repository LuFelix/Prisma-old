package online.lucianofelix.beans;

import java.util.ArrayList;
import java.util.List;

public class Produto {
	// TODO variaveis de contole
	private String nome_prod; // Nome do produto.
	private String desc_prod; // Descrição do ítem.
	private String aliq_prod; // Aliquota de ICMS do produto.
	private String codi_prod_1; // O principal código do produto.
	private String codi_prod_2; // Código extra 2 para o produto.
	private int seq_produto; // Sequencia de inserção no banco.
	private int quantMovimento;// Campo para movimenta��o do produto
	private List<ProdutoCotacao> listCotacaoProduto;
	private float prec_custo; // real, -- Pre�o de custo produto.
	private float prec_prod_1; // real, -- Pre�o atual de venda do produto.
	private float prec_prod_2; // real, -- Pre�o antecessor de venda do produto.
	private int estoqueAtual;
	// TODO Dados do produto para a nota
	// <prod>
	// <cProd>861</cProd> código do produto no sistema
	// <cEAN></cEAN> código de barras
	// <xProd>ARROZ AMIGAO PARB.LF T-AP 6/5KG (1)</xProd> desc prod nota fiscal
	// <NCM>10063011</NCM> ncm do cadastro do produto
	// <CFOP>5102</CFOP> cfop de venda do produto conforme o caso, pessoa
	// física
	// ou jurídica mesma Uf ou fora
	// <uCom>FD</uCom> unidade do produto
	// <qCom>1.00</qCom> quantidade comercializada
	// <vUnCom>29.00</vUnCom> valor unitário comercializado
	// <vProd>29.00</vProd> valor da venda
	// <cEANTrib></cEANTrib>
	// <uTrib>FD</uTrib>
	// <qTrib>1.00</qTrib> quantidade
	// <vUnTrib>29.00</vUnTrib> valor unitário
	// <indTot>1</indTot>
	// </prod>
	private int cProd; // guardar o codigo sequencial.
	private int cEAN;// c�digo ean pode ser vazio.
	private String xProd;
	private int NCM;
	private int CFOP;
	private String uCom;
	private String qCom;
	private float vUnCom;
	private float vProd;
	private int cEANTrib;
	private String uTrib;
	private float qTrib;
	private float vUnTrib;
	private int indTot;

	public Produto() {
		listCotacaoProduto = new ArrayList<ProdutoCotacao>();
	}

	public int getcProd() {
		return cProd;
	}

	public void setcProd(int cProd) {
		this.cProd = cProd;
	}

	public int getcEAN() {
		return cEAN;
	}

	public void setcEAN(int cEAN) {
		this.cEAN = cEAN;
	}

	public String getxProd() {
		return xProd;
	}

	public void setxProd(String xProd) {
		this.xProd = xProd;
	}

	public int getNCM() {
		return NCM;
	}

	public void setNCM(int nCM) {
		NCM = nCM;
	}

	public int getCFOP() {
		return CFOP;
	}

	public void setCFOP(int cFOP) {
		CFOP = cFOP;
	}

	public String getuCom() {
		return uCom;
	}

	public void setuCom(String uCom) {
		this.uCom = uCom;
	}

	public String getqCom() {
		return qCom;
	}

	public void setqCom(String qCom) {
		this.qCom = qCom;
	}

	public float getvUnCom() {
		return vUnCom;
	}

	public void setvUnCom(float vUnCom) {
		this.vUnCom = vUnCom;
	}

	public float getvProd() {
		return vProd;
	}

	public void setvProd(float vProd) {
		this.vProd = vProd;
	}

	public int getcEANTrib() {
		return cEANTrib;
	}

	public void setcEANTrib(int cEANTrib) {
		this.cEANTrib = cEANTrib;
	}

	public String getuTrib() {
		return uTrib;
	}

	public void setuTrib(String uTrib) {
		this.uTrib = uTrib;
	}

	public float getqTrib() {
		return qTrib;
	}

	public void setqTrib(float qTrib) {
		this.qTrib = qTrib;
	}

	public float getvUnTrib() {
		return vUnTrib;
	}

	public void setvUnTrib(float vUnTrib) {
		this.vUnTrib = vUnTrib;
	}

	public int getIndTot() {
		return indTot;
	}

	public void setIndTot(int indTot) {
		this.indTot = indTot;
	}

	public String getNome_prod() {
		return nome_prod;
	}

	public String getDesc_prod() {
		return desc_prod;
	}

	public String getAliq_prod() {
		return aliq_prod;
	}

	public String getCodi_prod_1() {
		return codi_prod_1;
	}

	public String getCodi_prod_2() {
		return codi_prod_2;
	}

	public int getSeq_produto() {
		return seq_produto;
	}

	public float getPrec_custo() {
		return prec_custo;
	}

	public float getPrec_prod_1() {
		return prec_prod_1;
	}

	public float getPrec_prod_2() {
		return prec_prod_2;
	}

	public void setNome_prod(String nome_prod) {
		this.nome_prod = nome_prod;
	}

	public void setDesc_prod(String desc_prod) {
		this.desc_prod = desc_prod;
	}

	public void setAliq_prod(String aliq_prod) {
		this.aliq_prod = aliq_prod;
	}

	public void setCodi_prod_1(String codi_prod_1) {
		this.codi_prod_1 = codi_prod_1;
	}

	public void setCodi_prod_2(String codi_prod_2) {
		this.codi_prod_2 = codi_prod_2;
	}

	public void setSeq_produto(int seq_produto) {
		this.seq_produto = seq_produto;
	}

	public void setPrec_custo(float prec_custo) {
		this.prec_custo = prec_custo;
	}

	public void setPrec_prod_1(float prec_prod_1) {
		this.prec_prod_1 = prec_prod_1;
	}

	public void setPrec_prod_2(float prec_prod_2) {
		this.prec_prod_2 = prec_prod_2;
	}

	/**
	 * @return the quantMovimento
	 */
	public int getQuantMovimento() {
		return quantMovimento;
	}

	/**
	 * @param quantMovimento
	 *            the quantMovimento to set
	 */
	public void setQuantMovimento(int quantMovimento) {
		this.quantMovimento = quantMovimento;
	}

	/**
	 * @return the listCotacaoProduto
	 */
	public List<ProdutoCotacao> getListCotacaoProduto() {
		return listCotacaoProduto;
	}

	/**
	 * @param listCotacaoProduto
	 *            the listCotacaoProduto to set
	 */
	public void setListCotacaoProduto(List<ProdutoCotacao> listCotacaoProduto) {
		this.listCotacaoProduto = listCotacaoProduto;
	}

	public int getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(int estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}

	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result + ((codi_prod_1 == null) ? 0 :
	// codi_prod_1.hashCode());
	// result = prime * result + ((desc_prod == null) ? 0 :
	// desc_prod.hashCode());
	// result = prime * result + ((nome_prod == null) ? 0 :
	// nome_prod.hashCode());
	// return result;
	// }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Produto))
			return false;
		Produto other = (Produto) obj;
		if (codi_prod_1 == null) {
			if (other.codi_prod_1 != null)
				return false;
		} else if (!codi_prod_1.equals(other.codi_prod_1))
			return false;
		if (desc_prod == null) {
			if (other.desc_prod != null)
				return false;
		} else if (!desc_prod.equals(other.desc_prod))
			return false;
		if (nome_prod == null) {
			if (other.nome_prod != null)
				return false;
		} else if (!nome_prod.equals(other.nome_prod))
			return false;
		return true;
	}

}