package online.lucianofelix.beans;

import java.sql.Timestamp;
import java.util.List;

/**
 * 
 * @author Luciano Felix
 * 
 *         substantivo
 *         ---------------------------------------------------------------------
 *         --- o fusível fuse, fuze - o estopim fuse, match - o rastilho fuse,
 *         priming - a mecha wick, fuse, tinder, match, candle-wick - o
 *         detonador detonator, fuse, exploder, detonating fuse
 *         ---------------------------------------------------------------------
 *         verbo
 *         ---------------------------------------------------------------------
 *         ---fundir - merge, fuse, blow, melt, cast, smelt unir unite, join,
 *         attach, put together, link, fuse --- espoletar - fuse --- misturar
 *         mix, blend, mingle, mix up, combine, fuse derreter melt, thaw,
 *         liquefy, fuse, found, flux amalgamar amalgamate, merge, fuse,
 *         coalesce, mix, mash --- queimar burn, burn out, fire, scorch, bake,
 *         fuse liquefazer liquefy, melt, liquidize, liquate, condense, fuse
 *         colocar mecha fuse
 *         ---------------------------------------------------------------------
 *         Esta classe contém a idéia de uma partida. Uma partida em um esporte,
 *         uma partida em um tipo qualquer de jogo como por exemplo um jogo de
 *         xadrez ou de baralho, uma partida de poker. Este nome foi escolhido
 *         por que buscava um sinônimo da palavra match, que em português bem
 *         como no espanhol significam partida em um sentido bem adequado ao uso
 *         que se propõe essa classe, porém o fonema da palavra match não me
 *         agradava aos ouvidos. Buscando um sinonimo em ingles achei a palavra
 *         fuse que além de soar bem aos ouvidos continha um contexto bem mais
 *         preciso que a anterior. Bom então por quê não em português, já que
 *         inclusive contem um homnimo em espanhol? Porque não queria trazer o
 *         aplicativo para o contexto de jogos de azar, mantendo assim um
 *         contexto específico tão quanto universal.
 *
 */
public class Fuse {

	private int seqFuse;
	private int quant;
	private float total;
	private float lucroPrejuizo;
	private float precStartCompra;
	private float precOrdCompra;
	private float precStartVenda;
	private float precOrdVenda;
	private String codiFuse;
	private String tipoFuse;
	private String codiAtivo;
	private String obsFuse;
	private Timestamp dataInicio;
	private Timestamp dataFim;
	private Timestamp dataExecStop;
	private Boolean execEntrada;
	private Boolean execSaida;
	private List<OrdemBovespa> listOrdGeradas;
	private List<Operacao> listOperVinc;
	private List<ItensIndicadores> listIndiUtilizados;

	public Boolean getExecEntrada() {
		return execEntrada;
	}

	public void setExecEntrada(Boolean execEntrada) {
		this.execEntrada = execEntrada;
	}

	public Boolean getExecSaida() {
		return execSaida;
	}

	public void setExecSaida(Boolean execSaida) {
		this.execSaida = execSaida;
	}

	public float getPrecStartCompra() {
		return precStartCompra;
	}

	public void setPrecStartCompra(float precStartCompra) {
		this.precStartCompra = precStartCompra;
	}

	public float getPrecOrdCompra() {
		return precOrdCompra;
	}

	public void setPrecOrdCompra(float precOrdCompra) {
		this.precOrdCompra = precOrdCompra;
	}

	public float getPrecStartVenda() {
		return precStartVenda;
	}

	public void setPrecStartVenda(float precStartVenda) {
		this.precStartVenda = precStartVenda;
	}

	public float getPrecOrdVenda() {
		return precOrdVenda;
	}

	public void setPrecOrdVenda(float precOrdVenda) {
		this.precOrdVenda = precOrdVenda;
	}

	public void setCodiFuse(String codiFuse) {
		this.codiFuse = codiFuse;
	}

	public String getCodiFuse() {
		return codiFuse;
	}

	public void setCodiReco(String codiFuse) {
		this.codiFuse = codiFuse;
	}

	public String getCodiAtivo() {
		return codiAtivo;
	}

	public void setCodiAtivo(String codiAtivo) {
		this.codiAtivo = codiAtivo;
	}

	public Timestamp getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Timestamp dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Timestamp getDataFim() {
		return dataFim;
	}

	public void setDataFim(Timestamp dataFim) {
		this.dataFim = dataFim;
	}

	public List<Operacao> getListOperVinc() {
		return listOperVinc;
	}

	public void setListOperVinc(List<Operacao> listOperVinc) {
		this.listOperVinc = listOperVinc;
	}

	public List<ItensIndicadores> getListIndiUtilizados() {
		return listIndiUtilizados;
	}

	public void setListIndiUtilizados(List<ItensIndicadores> listIndiUtilizados) {
		this.listIndiUtilizados = listIndiUtilizados;
	}

	public float getLucroPrejuizo() {
		return lucroPrejuizo;
	}

	public void setLucroPrejuizo(float lucroPrejuizo) {
		this.lucroPrejuizo = lucroPrejuizo;
	}

	public String getObsFuse() {
		return obsFuse;
	}

	public void setObsFuse(String obsFuse) {
		this.obsFuse = obsFuse;
	}

	public int getSeqFuse() {
		return seqFuse;
	}

	public void setSeqFuse(int seqFuse) {
		this.seqFuse = seqFuse;
	}

	public List<OrdemBovespa> getListOrdGeradas() {
		return listOrdGeradas;
	}

	public void setListOrdGeradas(List<OrdemBovespa> listOrdGeradas) {
		this.listOrdGeradas = listOrdGeradas;
	}

	public Timestamp getDataExecStop() {
		return dataExecStop;
	}

	public void setDataExecStop(Timestamp dataExecStop) {
		this.dataExecStop = dataExecStop;
	}

	/**
	 * @return the quant
	 */
	public int getQuant() {
		return quant;
	}

	/**
	 * @param quant
	 *            the quant to set
	 */
	public void setQuant(int quant) {
		this.quant = quant;
	}

	/**
	 * @return the total
	 */
	public float getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(float total) {
		this.total = total;
	}

	public String getTipoFuse() {
		return tipoFuse;
	}

	public void setTipoFuse(String tipoFuse) {
		this.tipoFuse = tipoFuse;
	}

}
