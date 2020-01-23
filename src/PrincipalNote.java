import online.lucianofelix.visao.FrameInicial;

/**
 * 
 * Construir o método pesquisa pós venda de acordo com o roteiro de apresentação
 * do pós venda. Construir classe LembretesSistema que lista uma tabela inferior
 * no frame inicial com uma lista selecionável com as opçções iguais às do
 * gmail. Ao clicar na mensagem o painel de detalhes carrega a mensagem (a
 * tabela central está livre nesse momento). Na mensagem tem um botão realizar
 * Pós Venda se o tipo de mensagem for lembrete de pedido. realizarPosVenda
 * carrega a tabela com os últimos atendimentos com esse cliente os botões novo
 * consultam que tela é e executam o método correspondente a um novo elemento,
 * de acordo com seu tipo de tela. Criar a classe evento para colocar os dados
 * de um evento. Anfitriã - anfitria(pode ser nulo), tipoEventoServiço(cuidados
 * com a pele, sessão de beleza, tarde especial, etc) . Nessa classe se adiciona
 * os tipos de serviço executados. a subtela de mensagens do sistema deverá
 * carregar uma agenda para auxiliar a1 marcação desse agendamento. Construir o
 * sistema de gerenciamento das notas.
 * 
 * @author Luciano Felix
 *
 */
public class PrincipalNote {

	public static void main(String[] args) {
		new FrameInicial();

		// new FrameECF();
		// new ManipulaImagens();
		// JFrame frm1 = new JFrame("Cotações");
		// JPanel pnlCotacoes = new JPanel(new BorderLayout());
		// exibeCotacoes();
		// new FrameLogin();
	}

	// static void exibeCotacoes() {
	// try {
	// DAOAtvYahoo daoAtv = new DAOAtvYahoo();
	// ArrayList<AtivoYahoo> listAtv = new ArrayList<AtivoYahoo>(
	// daoAtv.listarOrdIdNeg());
	// AtualizaYahoo atu = new AtualizaYahoo();
	//
	// for (int i = 0; i < listAtv.size(); i++) {
	// System.out.println("+++++++++++++++++===++++++++++++++++++++");
	// atu.atualizaCSV(listAtv.get(i).getIdYahoo());
	//
	// System.out.println("+++++++++++++++++===++++++++++++++++++++");
	// }
	//
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
}
// DAOPessoaProfissional daopro = new DAOPessoaProfissional();
// DAOPessoaPG daop = new DAOPessoaPG();
// PessoaProfissional pp = new PessoaProfissional();
// Pessoa p = daop.listaPessoas().get(1);
//
// pp.setCodiPess(p.getCodiPessoa());
// pp.setCodiProf("6776555");
// pp.setNomeProf("Marceneiro");
// pp.setDocFunc("66645555");
// pp.setPis(78365438);
// pp.setOptante(true);
//
// daopro.cadastraFuncao(pp);
//
// pp = new PessoaProfissional();
// pp = daopro.listaEmpregosCodigo(p.getCodiPessoa()).get(0);
//
// System.out.println(
// pp.getCodiPess() + " " + pp.getNomeProf() + " " + pp.getPis());

// AtualizaSistemaBDIBovespa atuBov = new AtualizaSistemaBDIBovespa();
// atuBov.atualizaBancoManual();
// ManipulaArquivoTxt manTxt = new ManipulaArquivoTxt();
// manTxt.readCsvFile();
// AtualizaCotacaoAutHashSet atu = new AtualizaCotacaoAutHashSet();
// atu.run();
// AtuIntrDay atu = new AtuIntrDay();
// try {
// atu.atualizar();
// } catch (Exception e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }

//
// java.util.Date date = new java.util.Date(); // Right now
// java.sql.Timestamp timestamp = new
// java.sql.Timestamp(date.getTime());
// System.out.println("date: " + date);
// System.out.println("timestamp: " + timestamp);
// String data = timestamp.toString();
// String hora = ManipulaData.horaString(data);
// System.out.println("String: " + data);
// System.out.println("Hora: " + hora);
