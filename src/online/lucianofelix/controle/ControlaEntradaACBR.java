package online.lucianofelix.controle;

import java.io.IOException;

import online.lucianofelix.beans.Produto;

public interface ControlaEntradaACBR {
	
	String ecfAtivar();
	
	String ecfDesativar();
	
	boolean ecfAtivo() throws Exception;
	
	int		ecfColunas();
	
	String  ecfComandoEnviado();
	
	String  ecfRespostaComando();
	
	String  ecfModeloStr();
	
	String  ecfModelo();
	
	String  ecfPorta();
	
	String  ecfTimeOut();
		
	String  ecfAbreCupom() throws Exception;
	
	String ecfLeituraX();
	
	String ecfVendeItem(Produto prod) throws Exception;
	
	String ecfCancelaItemVendido(int numItem) throws Exception;
	
	String ecfTestaPodeAbrirCupom() throws Exception;
	
	String ecfSubtotalizaCupom(String acrescimoDesconto) throws Exception;
	
	String ecfEfetuaPagamento(String codFormaPagamento, float valor, String observacao, boolean imprimeVinculado) throws Exception;
	
	String ecfFecharCupom(String mensagemRodape) throws Exception, IOException;
	
	String ecfCancelarCupom() throws Exception;
	
	String ecfReducaoZ(String dataHora) throws Exception;
	
	String ecfFormasPagamento() throws Exception;
	
	
}
