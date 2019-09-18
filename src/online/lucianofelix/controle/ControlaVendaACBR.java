package online.lucianofelix.controle;

import java.io.File;
import java.io.FileNotFoundException;

import online.lucianofelix.beans.Produto;
import online.lucianofelix.util.ManipulaArquivoTxt;

public class ControlaVendaACBR
		implements
			ControlaSaidaACBR,
			ControlaEntradaACBR {

	String nomePastaEnt;
	String nomeArquivoEnt;
	String nomePastaSai;
	String nomeArquivoSai;
	String resposta;

	File arquivoSai;
	File arquivoEnt;

	ManipulaArquivoTxt manTxt;

	public ControlaVendaACBR() {
		nomePastaEnt = "C:\\ACBRENT";
		nomeArquivoEnt = "ent.txt";
		nomePastaSai = "C:\\ACBRSAI";
		nomeArquivoSai = "sai.txt";

	}

	public String leArquivoSai() {
		manTxt = new ManipulaArquivoTxt();
		String conteudoArquivoSai = null;
		try {
			Thread.sleep(2000);
			arquivoSai = new File(nomePastaSai, nomeArquivoSai);
			conteudoArquivoSai = manTxt.extraiConteudo(arquivoSai);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Arquivo ainda n�o existe");
			leArquivoSai();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erro de Interrup��o");
			leArquivoSai();
		}

		return conteudoArquivoSai;

	}

	@Override
	public String ecfLeituraX() {
		manTxt = new ManipulaArquivoTxt();
		arquivoEnt = manTxt.montaArquivotxt("ECF.LeituraX");
		try {
			manTxt.gravaArquivo(arquivoEnt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * ECF.AbreCupom [ ( cCPF_CNPJ, cNOME, cENDERECO) ] Par�metros: cCPF_CNPJ -
	 * Par�metro opcional. Se necess�rio, informe o CPF/CNPJ do Consumidor cNOME
	 * -Par�metro opcional. Se necess�rio, informe o NOME do Consumidor
	 * cENDERECO -Par�metro opcional. Se necess�rio, informe o ENDERE�O do
	 * Consumidor Exemplos: ECF.AbreCupom
	 * ECF.AbreCupom("1234567890","ACBr""Tatui") Nota: Nem todos os modelos de
	 * ECF fazem uso do par�metro cCPF_CNPJ, nesse caso o Cupom ser� aberto,
	 * por�m o n�mero de identifica��o do cliente n�o ser� impresso. Dica: Para
	 * identificar o cliente prefira usar o Rodap� do Cupom, que permite at� 8
	 * linhas de texto livre Exemplo de Resposta: OK: Created with the Personal
	 * Edition
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ecfAbreCupom() throws Exception {
		// TODO M�todo que abre um cupom
		resposta = ecfTestaPodeAbrirCupom();
		if (resposta.contains("OK:")) {
			System.out.println("Tentando abrir cupom...");
			manTxt = new ManipulaArquivoTxt();
			arquivoEnt = manTxt.montaArquivotxt("ECF.AbreCupom", nomePastaEnt,
					nomeArquivoEnt);
			manTxt.gravaArquivo(arquivoEnt);
			resposta = ecfAbreCupomResposta();
			System.out.println("Resposta do comando abre cupom com SUCESSO==> "
					+ resposta);
			return resposta;

		}
		System.out.println(
				"Resposta do comando abre cupom com ERRO==> " + resposta);
		return resposta;

	}

	@Override
	public String ecfAtivar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ecfDesativar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean ecfAtivo() throws Exception {
		// TODO Comando ECF Ativo (Retorna o estado do ECF)
		manTxt = new ManipulaArquivoTxt();
		arquivoEnt = manTxt.montaArquivotxt("ECF.Ativo", nomePastaEnt,
				nomeArquivoEnt);
		manTxt.gravaArquivo(arquivoEnt);

		return false;
	}

	@Override
	public int ecfColunas() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String ecfComandoEnviado() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ecfRespostaComando() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ecfModeloStr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ecfModelo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ecfPorta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ecfTimeOut() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * 
	 * ECF.VendeItem(cCodigo, cDescricao, cAliquotaICMS, nQtd, nValorUnitario
	 * [,nDescontoPorc, cUnidade] ) Par�metros: cCodigo - Texto com o c�digo do
	 * produto, geralmente � aceito at� 13 caracteres, alguns ECFs apenas
	 * aceitam num�ricos no c�digo. cDescricao - Texto com a descri��o do
	 * Produto vendido. Procure n�o usar acentos, pois alguns ECFs n�o aceitam
	 * caracteres acentuados. Para imprimir Descri��es "grandes" habilite a
	 * op��o "Descri��o Grande" no ACBrMonitor. cAliquotaICMS - Texto com a
	 * representa��o da Al�quota do ICMS. - As al�quotas podem ser informadas em
	 * Valor (mesmo sendo texto). Exemplos: "18", "2.46". - Se no ECF existem
	 * al�quotas iguais para ICMS e ISS, use o sufixo "T" para informar que a
	 * al�quota � do ICMS ou "S" para al�quotas do ISS. Exemplo: "18T" =
	 * al�quota de 18% do ICMS; "2.5S" al�quota de 2,5% do ISS - As al�quotas
	 * podem ser informadas em �ndice, de acordo com a Tabela de al�quotas do
	 * ECF, nesse caso use a letra "T", seguida da posi��o da Al�quota: Exemplo:
	 * "T01", "T10" - Existem al�quotas internas do ECF para tratar produtos
	 * Isentos, nesse caso use: "FF" para Substitui��o Tribut�ria, "NN" = N�o
	 * incid�ncia ou "II" = Isento nQtd - Quantidade de Produtos a Vender.
	 * Permite valores com at� 3 casas decimais. O ACBr verifica quantas casas
	 * decimais existem no valor informado e utiliza o comando apropriado para o
	 * ECF, otimizando a impress�o para Inteiros o 2 casas decimais, sempre que
	 * poss�vel nValorUnitario � Pre�o Unit�rio do produto vendido. Permite
	 * valores com at� 3 casas decimais. O ACBr verifica quantas casas decimais
	 * existem no valor informado e utiliza o comando apropriado para o ECF,
	 * otimizando a impress�o para 2 casas decimais, sempre que poss�vel.
	 * nDescontoPorc - Par�metro opcional, Se necess�rio, informe a Porcentagem
	 * de Desconto a aplicar no item Vendido. Dependendo do ECF o valor e
	 * porcentagem do Desconto ser� impresso no Cupom. cUnidade - Par�metro
	 * opcional, Se necess�rio, informe o Texto com a unidade de medida do Item.
	 * Exemplo: "UN", "LT", "MT", "KG", etc Exemplos:
	 * ECF.VendeItem("789012223233","PRODUTO TESTE, TRIBUTADO","18",1, 100.34,
	 * 10,"LT") - cCodigo = "789012223233" - cDescricao =
	 * "PRODUTO TESTE, TRIBUTADO" - cAliquotaICMS = 18 - nQtd, = 1 -
	 * nValorUnitario = 100.34 (Cem Reais e Trinta e Quatro Centavos) -
	 * nDescontoPorc = 10 (Dez Por cento) - cUnidade = "LT"
	 * ECF.VendeItem("789012456789","PRODUTO TESTE, ISENTO","II", 1.5, 2.5 ) N�o
	 * especificou os par�metros opcionais (Desconto e Unidade)... Notas: - O
	 * ACBr tentar� otimizar a impress�o ocupando o menor numero de linhas
	 * poss�veis, de acordo com o tamanho dos par�metros cCodigo e cDescricao.
	 * Espa�os a direita de cDescricao s�o ignorados. - Para evitar "diferen�as"
	 * entre o seu programa e o impresso no ECF, procure informar os campos nQtd
	 * e nValorUnitario j� arredondados para o n�mero m�ximo de casas decimais
	 * que voc� deseja utilizar. Exemplo de Resposta: OK:
	 * 
	 * @throws Exception
	 */
	public String ecfVendeItem(Produto prod) throws Exception {
		// TODO Comando ECF Vende Item
		manTxt = new ManipulaArquivoTxt();
		arquivoEnt = manTxt.montaArquivotxt("ECF.VendeItem(\""
				+ prod.getCodi_prod_1() + "\"," + "\"" + prod.getDesc_prod()
				+ "\"," + "\"" + prod.getAliq_prod() + "\"," + "\""
				// + prod.getEstoque() + "\"," + "\""
				+ prod.getPrec_prod_1() + "\")", nomePastaEnt, nomeArquivoEnt);
		manTxt.gravaArquivo(arquivoEnt);
		resposta = ecfVendeItemResposta();
		System.out
				.println("Resposta do comando ECF Vende Item ==> " + resposta);
		return resposta;
	}

	@Override
	public String ecfCancelaItemVendido(int numItem) throws Exception {
		// TODO Comando ECF Cancela o Item vendido
		manTxt = new ManipulaArquivoTxt();
		arquivoEnt = manTxt.montaArquivotxt(
				"ECF.CancelaItemVendido" + "(" + numItem + ")", nomePastaEnt,
				nomeArquivoEnt);
		manTxt.gravaArquivo(arquivoEnt);
		resposta = ecfCancelaItemVendidoResposta();
		System.out.println(
				"Resposta do comando ecf Cancela item vendido ==>" + resposta);

		return resposta;
	}

	@Override
	public String ecfTestaPodeAbrirCupom() throws Exception {
		// TODO Comando Testa Pode Abrir Cupom FISCAL
		manTxt = new ManipulaArquivoTxt();
		arquivoEnt = manTxt.montaArquivotxt("ECF.TestaPodeAbrirCupom",
				nomePastaEnt, nomeArquivoEnt);
		manTxt.gravaArquivo(arquivoEnt);
		resposta = ecfTestaPodeAbrirCupomResposta();
		System.out.println(
				"Resposta do comando ecf testa abrir cupom ==>" + resposta);
		return resposta;
	}

	@Override
	public String ecfSubtotalizaCupom(String acrescimoDesconto)
			throws Exception {
		// TODO Comando Subtotaliza o cupom corrente
		manTxt = new ManipulaArquivoTxt();
		arquivoEnt = manTxt.montaArquivotxt(
				"ECF.SubtotalizaCupom('" + acrescimoDesconto + "')",
				nomePastaEnt, nomeArquivoEnt);
		manTxt.gravaArquivo(arquivoEnt);
		resposta = ecfSubtotalizaCupomResposta();
		System.out.println(
				"Resposta do Comando Subtotaliza Cupom ==> " + resposta);
		return resposta;

	}

	@Override
	public String ecfFecharCupom(String mensagemRodape) throws Exception {
		// TODO Comando Ecf Fechar Cupom
		manTxt = new ManipulaArquivoTxt();
		arquivoEnt = manTxt.montaArquivotxt(
				"ECF.FechaCupom(" + mensagemRodape + ")", nomePastaEnt,
				nomeArquivoEnt);
		manTxt.gravaArquivo(arquivoEnt);
		resposta = ecfFecharCupomResposta();
		System.out.println(
				"Resposta do Comando Ecf Fechar Cupom ==> " + resposta);
		return resposta;
	}

	@Override
	public String ecfCancelarCupom() throws Exception {
		// TODO Comando ECF Cancela Cupom
		manTxt = new ManipulaArquivoTxt();
		arquivoEnt = manTxt.montaArquivotxt("ECF.CancelaCupom", nomePastaEnt,
				nomeArquivoEnt);
		manTxt.gravaArquivo(arquivoEnt);
		resposta = ecfCancelarCupomResposta();
		System.out.println(
				"Resposta do comando ecf cancelar cupom==>" + resposta);
		return resposta;
	}

	@Override
	public String ecfReducaoZ(String dataHora) throws Exception {
		// TODO Comando ECF Reducao Z
		dataHora = "";
		manTxt = new ManipulaArquivoTxt();
		arquivoEnt = manTxt.montaArquivotxt("ECF.ReducaoZ", nomePastaEnt,
				nomeArquivoEnt);
		manTxt.gravaArquivo(arquivoEnt);
		return ecfReducaoZResposta("");
	}

	@Override
	public String ecfAtivarResposta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ecfDesativarResposta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean ecfAtivoResposta() {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public int ecfColunasResposta() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String ecfComandoEnviadoResposta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ecfRespostaComandoResposta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ecfModeloStrResposta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ecfModeloResposta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ecfPortaResposta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ecfTimeOutResposta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ecfAbreCupomResposta() {
		// TODO Auto-generated method stub
		do {
			leArquivoSai();
		} while (!arquivoSai.exists());
		resposta = leArquivoSai();
		arquivoSai.delete();

		return resposta;
	}

	@Override
	public String ecfLeituraXResposta() {
		// TODO Comando ECF Leitura X
		return null;
	}

	@Override
	public String ecfVendeItemResposta() {
		// TODO Resposta do Comando ECF Vende Item
		do {
			leArquivoSai();
		} while (!arquivoSai.exists());

		resposta = leArquivoSai();
		arquivoSai.delete();

		return resposta;

	}

	@Override
	public String ecfCancelaItemVendidoResposta() {
		// TODO Resposta do Comando Cancela Item Vendido
		do {
			leArquivoSai();
		} while (!arquivoSai.exists());
		resposta = leArquivoSai();
		arquivoSai.delete();

		return resposta;
	}

	@Override
	public String ecfTestaPodeAbrirCupomResposta() {
		// TODO Respostra do Comando ECF Testa Pode Abrir Cupom
		do {
			leArquivoSai();
		} while (!arquivoSai.exists());

		resposta = leArquivoSai();
		arquivoSai.delete();
		return resposta;

	}

	@Override
	public String ecfSubtotalizaCupomResposta() {
		// TODO Resposta do Comando Subtotaliza Cupom
		do {
			leArquivoSai();
		} while (!arquivoSai.exists());

		resposta = leArquivoSai();
		arquivoSai.delete();

		return resposta;

	}

	@Override
	public String ecfEfetuaPagamento(String codFormaPagamento, float valor,
			String observacao, boolean imprimeVinculado) throws Exception {
		// TODO Efetua o Pagamento total ou parcial informando os parametros
		// necessarios
		manTxt = new ManipulaArquivoTxt();
		arquivoEnt = manTxt.montaArquivotxt(
				"ECF.EfetuaPagamento(" + codFormaPagamento + "," + valor + ","
						+ observacao + "," + imprimeVinculado + ")",
				nomePastaEnt, nomeArquivoEnt);
		manTxt.gravaArquivo(arquivoEnt);
		resposta = ecfEfetuaPagamentoResposta();
		System.out.println(
				"Resposta do comando ecf EfeTua Pagamento ==>" + resposta);
		return resposta;

	}

	@Override
	public String ecfFecharCupomResposta() {
		// TODO Auto-generated method stub
		do {
			leArquivoSai();
		} while (!arquivoSai.exists());

		resposta = leArquivoSai();
		arquivoSai.delete();

		return resposta;

	}

	@Override
	public String ecfCancelarCupomResposta() {
		do {
			leArquivoSai();
		} while (!arquivoSai.exists());

		resposta = leArquivoSai();
		arquivoSai.delete();
		return resposta;
	}

	@Override
	public String ecfReducaoZResposta(String dataHora) {
		// TODO Auto-generated method stub
		do {
			leArquivoSai();
		} while (!arquivoSai.exists());

		resposta = leArquivoSai();
		arquivoSai.delete();
		return resposta;

	}

	@Override
	/**
	 * Retorna Formas de Pagamento Cadastradas Nota: Esse comando quando
	 * executado a primeira vez, se comunica com o ECF a fim de carregar a
	 * tabela de Formas de Pagamento. Ap�s a carga, elas s�o transferidas para a
	 * mem�ria do ACBrECF Exemplo de Resposta:
	 * 
	 * OK: 01 Dinheiro |02 VCARTAO
	 * 
	 */
	public String ecfFormasPagamento() throws Exception {
		// TODO Carregar as formas de pagamento previamente para a mrmoria do
		// acbrEcf
		manTxt = new ManipulaArquivoTxt();
		arquivoEnt = manTxt.montaArquivotxt("ECF.FormasPagamento", nomePastaEnt,
				nomeArquivoEnt);
		manTxt.gravaArquivo(arquivoEnt);
		resposta = ecfFormasPagamentoResposta();
		System.out.println(
				"Resposta do comando ecf Formas de Pagamento==>" + resposta);
		return resposta;

	}

	@Override
	public String ecfFormasPagamentoResposta() {
		// TODO Resposta do Comando Ecf Formas de Pagamento
		do {
			leArquivoSai();
		} while (!arquivoSai.exists());

		resposta = leArquivoSai();
		arquivoSai.delete();
		return resposta;
	}

	@Override
	public String ecfEfetuaPagamentoResposta() {
		// TODO Resposta do comando ecf Formas de Pagamento
		do {
			leArquivoSai();
		} while (!arquivoSai.exists());

		resposta = leArquivoSai();
		arquivoSai.delete();
		return resposta;
	}

}
