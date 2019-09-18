package online.lucianofelix.beans;

public class Servico {

	private int seqServico; // Sequencia de inserção no banco.
	private String codiServico; // Código extra 2 para o produto.
	private String nomeServico; // Nome do serviço.
	private String descServico; // Descrição do ítem.
	private String codiCategoria;// Chave para a tabela de categoria.
	private float tempoResposta;// Tempo máximo para o ínicio do serviço.
	private float tempoExecucao;// Tempo de execução do serviço.
	private float duracao; // Quant. de horas necessárias para o serviço.
	private float precoHora; // O preço da hora trabalhada.
	private float precoAdicional; // Alguma taxa extra

	public String getCodiCategoria() {
		return codiCategoria;
	}

	public void setCodiCategoria(String codiCategoria) {
		this.codiCategoria = codiCategoria;
	}

	public float getTempoResposta() {
		return tempoResposta;
	}

	public void setTempoResposta(float tempoResposta) {
		this.tempoResposta = tempoResposta;
	}

	public float getTempoExecucao() {
		return tempoExecucao;
	}

	public void setTempoExecucao(float tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}

	public float getDuracao() {
		return duracao;
	}

	public void setDuracao(float duracao) {
		this.duracao = duracao;
	}

	public int getSeqServico() {
		return seqServico;
	}

	public void setSeqServico(int seqServico) {
		this.seqServico = seqServico;
	}

	public String getCodiServico() {
		return codiServico;
	}

	public void setCodiServico(String codiServico) {
		this.codiServico = codiServico;
	}

	public String getNomeServico() {
		return nomeServico;
	}

	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}

	public String getDescServico() {
		return descServico;
	}

	public void setDescServico(String descServico) {
		this.descServico = descServico;
	}

	public float getPrecoHora() {
		return precoHora;
	}

	public void setPrecoHora(float precoHora) {
		this.precoHora = precoHora;
	}

	public float getPrecoAdicional() {
		return precoAdicional;
	}

	public void setPrecoAdicional(float precoAdicional) {
		this.precoAdicional = precoAdicional;
	}
}
