package online.lucianofelix.beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
	// TODO variáveis de controle
	private int seqPedi;
	private int quantItens;
	private float totalPedi;
	private String codiPedi;
	private String obsPedi1;
	private String obsPedi2;
	private String codiPessoaCliente;
	private String codiPessoaVendedor;
	private String nomeVendedor;
	private String nomeCondPagamento;
	private String numNota;
	private String codiCondPag;
	private Timestamp dataHoraPedi;
	private List<Produto> itensProduto;
	private List<Lancamento> lancPedido;
	private String tipoPedido;
	private String codiTabPreco;

	// TODO Dados do cliente na nota
	// <emit>
	// <CNPJ>13013983000142</CNPJ>
	// <xNome>J B DO NASCIMENTO DISTRIBUIDORA</xNome>
	// <xFant>J B DO NASCIMENTO DISTRIBUIDORA</xFant>
	// <enderEmit>
	// <xLgr>AV ANTONIO LISBOA AMORIM 1 A</xLgr>
	// <nro>1</nro>
	// <xBairro>ANTARES</xBairro>
	// <cMun>2704302</cMun>
	// <xMun>MACEIO</xMun>
	// <UF>AL</UF>
	// <CEP>57060972</CEP>
	// <cPais>1058</cPais>
	// <xPais>BRASIL</xPais>
	// <fone>08233340306</fone>
	// </enderEmit>
	// <IE>242416160</IE>
	// <IM>000000000000000</IM>
	// <CNAE>4639701</CNAE>
	// <CRT>3</CRT>
	// </emit>
	private String CNPJ;
	private String xNome;
	private String fant;
	private String xLgr;
	private String nro;
	private String xBairro;
	private int cMun;
	private String xMun;
	private String UF;
	private int CEP;
	private int cPais;
	private String xPais;
	private int fone;
	private int IE;
	private int IM;
	private int CNAE;
	private int CRT;

	public String getTipoPedido() {
		return tipoPedido;
	}

	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public String getxNome() {
		return xNome;
	}

	public void setxNome(String xNome) {
		this.xNome = xNome;
	}

	public String getFant() {
		return fant;
	}

	public void setFant(String fant) {
		this.fant = fant;
	}

	public String getxLgr() {
		return xLgr;
	}

	public void setxLgr(String xLgr) {
		this.xLgr = xLgr;
	}

	public String getNro() {
		return nro;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}

	public String getxBairro() {
		return xBairro;
	}

	public void setxBairro(String xBairro) {
		this.xBairro = xBairro;
	}

	public int getcMun() {
		return cMun;
	}

	public void setcMun(int cMun) {
		this.cMun = cMun;
	}

	public String getxMun() {
		return xMun;
	}

	public void setxMun(String xMun) {
		this.xMun = xMun;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public int getCEP() {
		return CEP;
	}

	public void setCEP(int cEP) {
		CEP = cEP;
	}

	public int getcPais() {
		return cPais;
	}

	public void setcPais(int cPais) {
		this.cPais = cPais;
	}

	public String getxPais() {
		return xPais;
	}

	public void setxPais(String xPais) {
		this.xPais = xPais;
	}

	public int getFone() {
		return fone;
	}

	public void setFone(int fone) {
		this.fone = fone;
	}

	public int getIE() {
		return IE;
	}

	public void setIE(int iE) {
		IE = iE;
	}

	public int getIM() {
		return IM;
	}

	public void setIM(int iM) {
		IM = iM;
	}

	public int getCNAE() {
		return CNAE;
	}

	public void setCNAE(int cNAE) {
		CNAE = cNAE;
	}

	public int getCRT() {
		return CRT;
	}

	public void setCRT(int cRT) {
		CRT = cRT;
	}

	public int getSeqPedi() {
		return seqPedi;
	}

	public void setSeqPedi(int seqPedi) {
		this.seqPedi = seqPedi;
	}

	public int getQuantItens() {
		return quantItens;
	}

	public void setQuantItens(int quantItens) {
		this.quantItens = quantItens;
	}

	public float getTotalPedi() {
		return totalPedi;
	}

	public void setTotalPedi(float totalPedi) {
		this.totalPedi = totalPedi;
	}

	public String getCodiPedi() {
		return codiPedi;
	}

	public void setCodiPedi(String codiPedi) {
		this.codiPedi = codiPedi;
	}

	public String getObsPedi1() {
		return obsPedi1;
	}

	public void setObsPedi1(String obsPedi1) {
		this.obsPedi1 = obsPedi1;
	}

	public String getObsPedi2() {
		return obsPedi2;
	}

	public void setObsPedi2(String obsPedi2) {
		this.obsPedi2 = obsPedi2;
	}

	public Timestamp getDataHoraPedi() {
		return dataHoraPedi;
	}

	public void setDataHoraPedi(Timestamp dataHoraPedi) {
		this.dataHoraPedi = dataHoraPedi;
	}

	public Produto[] getItensProdutoArray(List<Produto> arListItProd) {
		Produto[] itProdArray = new Produto[arListItProd.size()];
		itProdArray = arListItProd.toArray(itProdArray);
		return itProdArray;
	}

	public String getNumNota() {
		return numNota;
	}

	public void setNumNota(String numNota) {
		this.numNota = numNota;
	}

	public String getCodiCondPag() {
		return codiCondPag;
	}

	public void setCodiCondPag(String codiCondPag) {
		this.codiCondPag = codiCondPag;
	}

	/**
	 * @return the itensProduto
	 */
	public List<Produto> getItensProduto() {
		return itensProduto;
	}

	public Pedido() {
		super();
		itensProduto = new ArrayList<Produto>();
		lancPedido = new ArrayList<Lancamento>();
	}

	/**
	 * @param itensProduto
	 *            the itensProduto to set
	 */
	public void setItensProduto(List<Produto> itensProduto) {
		this.itensProduto = itensProduto;
	}

	public String getCodiPessoaCliente() {
		return codiPessoaCliente;
	}

	public void setCodiPessoaCliente(String codiPessoaCliente) {
		this.codiPessoaCliente = codiPessoaCliente;
	}

	public String getCodiPessoaVendedor() {
		return codiPessoaVendedor;
	}

	public void setCodiPessoaVendedor(String codiPessoaVendedor) {
		this.codiPessoaVendedor = codiPessoaVendedor;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public String getNomeCondPagamento() {
		return nomeCondPagamento;
	}

	public void setNomeCondPagamento(String nomeCondPagamento) {
		this.nomeCondPagamento = nomeCondPagamento;
	}

	public String getCodiTabPreco() {
		return codiTabPreco;
	}

	public void setCodiTabPreco(String codiTabPreco) {
		this.codiTabPreco = codiTabPreco;
	}

	public List<Lancamento> getLancPedido() {
		return lancPedido;
	}

	public void setLancPedido(List<Lancamento> lancPedido) {
		this.lancPedido = lancPedido;
	}
}
