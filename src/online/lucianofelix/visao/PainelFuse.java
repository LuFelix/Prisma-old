package online.lucianofelix.visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import online.lucianofelix.beans.Fuse;
import online.lucianofelix.beans.Indicadores;
import online.lucianofelix.beans.ItensIndicadores;
import online.lucianofelix.beans.Operacao;
import online.lucianofelix.beans.OrdemBovespa;
import online.lucianofelix.beans.Pessoa;
import online.lucianofelix.controle.ControlaFuse;
import online.lucianofelix.controle.ControlaListaFuse;
import online.lucianofelix.dao.DAOFuse;

public class PainelFuse extends JPanel {

	/**
	 * @return the listOrd
	 */
	public static List<OrdemBovespa> getListOrd() {
		return listOrd;
	}

	/**
	 * @param listOrd
	 *            the listOrd to set
	 */
	public static void setListOrd(List<OrdemBovespa> listOrd) {
		PainelFuse.listOrd = listOrd;
	}

	public static JTable getTabelaOperacoes() {
		return tabelaOperacoes;
	}

	public static void setTabelaOperacoes(JTable tabelaOperacoes) {
		PainelFuse.tabelaOperacoes = tabelaOperacoes;
	}

	public static JTable getTabelaOrdens() {
		return tabelaOrdens;
	}

	public static void setTabelaOrdens(JTable tabelaOrdens) {
		PainelFuse.tabelaOrdens = tabelaOrdens;
	}

	// JFrame telaPedido;
	JPanel painelPrincipal;
	private JLabel lblTituloTela;
	// Labels e text fields
	private JLabel lblCodigoFuse;
	private JLabel lblCodiAtivo;
	private JLabel lblLucroPrejuizo;
	private JLabel lblStartCompra;
	private JLabel lblOrdemCompra;
	private JLabel lblStartVenda;
	private JLabel lblOrdemVenda;
	private JLabel lblQuant;
	private JLabel lblDataInicio;
	private JLabel lblPermiteEditar;
	private static JTextField txtFCodigoFuse;
	private static JTextField txtFCodiAtivo;
	private static JTextField txtFLucroPrejuizo;
	private static JTextField txtFStartCompra;
	private static JTextField txtFOrdemCompra;
	private static JTextField txtFStartVenda;
	private static JTextField txtFOrdemVenda;
	private static JTextField txtFQuantItens;
	private static JTextField txtFTotalFuse;
	private static JScrollPane scrOperacoes;
	private static JScrollPane scrIndicadores;
	private static JScrollPane scrOrdens;
	private JScrollPane scrObsPedido;
	private JTabbedPane tabVisualiza;

	private JRadioButton jrbEditarSim;
	private JRadioButton jrbEditarNao;
	private ButtonGroup grpRadio;
	private ActionListener acaoEditar;
	private ActionListener sobrescrever;

	// TODO objetos de controle

	private static ControlaListaFuse controledaLista;
	private static ControlaFuse contFuse;
	private static Fuse fuse;
	private static DAOFuse daoFuse;
	private List<Fuse> listfuse;
	int tam;
	private JLabel lblPrecoTotal;

	private static JTextArea txtAreaObsPedido;
	private static JTable tabelaItensIndicadores;
	private static JTable tabelaOperacoes;
	private static JTable tabelaOrdens;
	private static DefaultTableModel modeloTabela;
	private static List<ItensIndicadores> arrayItensIndi;
	private static List<Operacao> arrayOperacao;
	private static List<OrdemBovespa> listOrd;
	private static float total;
	private static int movimentos;
	private static int quantTotItens;
	private static ItensIndicadores itemIndi;

	public PainelFuse(String nome) {
		UIManager.put("TextField.font", new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Label.font", new Font("Times New Roman", Font.PLAIN, 12));
		UIManager.put("Button.font", new Font("Times New Roman", Font.BOLD, 12));

		contFuse = new ControlaFuse();
		daoFuse = new DAOFuse();
		controledaLista = new ControlaListaFuse(listfuse);
		arrayItensIndi = new ArrayList<ItensIndicadores>();

		setLayout(null);

		painelPrincipal = new JPanel();
		painelPrincipal.setBorder(BorderFactory.createEtchedBorder());
		painelPrincipal.setLayout(null);
		painelPrincipal.setSize(525, 510);
		painelPrincipal.setBackground(Color.WHITE);

		scrOrdens = new JScrollPane();
		scrOrdens.setSize(420, 320);

		scrOperacoes = new JScrollPane();
		scrOperacoes.setSize(420, 320);

		scrIndicadores = new JScrollPane();
		scrIndicadores.setSize(420, 320);

		txtAreaObsPedido = new JTextArea();
		scrObsPedido = new JScrollPane();
		scrObsPedido.setSize(420, 320);
		scrObsPedido.setViewportView(txtAreaObsPedido);

		tabVisualiza = new JTabbedPane();
		tabVisualiza.setBounds(0, 360, 525, 210);
		tabVisualiza.setBorder(new EmptyBorder(0, 0, 0, 0));

		tabVisualiza.add("Ordens", scrOrdens);
		tabVisualiza.add("Operações", scrOperacoes);
		tabVisualiza.add("Indicadores", scrIndicadores);
		tabVisualiza.addTab("Observações", scrObsPedido);

		// TODO Labels e Text fields

		lblTituloTela = new JLabel("FUSE");
		lblTituloTela.setBounds(10, 0, 150, 40);
		lblTituloTela.setFont(new Font("Times New Roman", Font.BOLD, 28));

		lblCodigoFuse = new JLabel("Código:");
		lblCodigoFuse.setBounds(5, 60, 90, 25);
		txtFCodigoFuse = new JTextField();
		txtFCodigoFuse.setBounds(85, 60, 80, 25);
		txtFCodigoFuse.setHorizontalAlignment(JTextField.CENTER);
		txtFCodigoFuse.setEditable(false);

		// txtFCodigopedi.setColumns(6);
		lblDataInicio = new JLabel(String.valueOf(Calendar.getInstance().getTime()));
		lblDataInicio.setBounds(180, 60, 185, 25);

		lblCodiAtivo = new JLabel("Ativo: ");
		lblCodiAtivo.setBounds(5, 90, 100, 25);
		txtFCodiAtivo = new JTextField();
		txtFCodiAtivo.setBounds(85, 90, 80, 25);
		txtFCodiAtivo.setHorizontalAlignment(JTextField.CENTER);
		txtFCodiAtivo.setEditable(false);
		txtFCodiAtivo.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Cond. pagamento ao perder foco

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Cond. pagamento ao ganhar foco
				FrameInicial.pesquisaCondPagamentoRealizaPedido();

			}
		});
		lblQuant = new JLabel("Quantidade: ");
		lblQuant.setBounds(170, 90, 120, 25);
		txtFQuantItens = new JTextField();
		txtFQuantItens.setBounds(260, 90, 80, 25);
		txtFQuantItens.setHorizontalAlignment(JTextField.RIGHT);
		txtFQuantItens.setFont(new Font("TimesNew Roman", Font.BOLD, 16));
		txtFQuantItens.setEditable(false);

		lblStartCompra = new JLabel("Start Compra: ");
		lblStartCompra.setBounds(5, 120, 80, 25);
		txtFStartCompra = new JTextField();
		txtFStartCompra.setBounds(85, 120, 80, 25);
		txtFStartCompra.setHorizontalAlignment(JTextField.RIGHT);
		txtFStartCompra.setFont(new Font("TimesNew Roman", Font.BOLD, 16));
		txtFStartCompra.setEditable(false);
		txtFStartCompra.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO txtfCliente ao perder foco
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO txtfCliente ao receber foco
				FrameInicial.pesquisaUsuarioAdicionarAOPedido();
			}
		});

		lblOrdemCompra = new JLabel("Ordem Compra: ");
		lblOrdemCompra.setBounds(170, 120, 100, 25);
		txtFOrdemCompra = new JTextField();
		txtFOrdemCompra.setBounds(260, 120, 80, 25);
		txtFOrdemCompra.setHorizontalAlignment(JTextField.RIGHT);
		txtFOrdemCompra.setFont(new Font("TimesNew Roman", Font.BOLD, 16));

		lblStartVenda = new JLabel("Start Venda: ");
		lblStartVenda.setBounds(5, 150, 80, 25);
		txtFStartVenda = new JTextField();
		txtFStartVenda.setBounds(85, 150, 80, 25);
		txtFStartVenda.setHorizontalAlignment(JTextField.RIGHT);
		txtFStartVenda.setFont(new Font("TimesNew Roman", Font.BOLD, 16));

		lblOrdemVenda = new JLabel("Ordem Venda: ");
		lblOrdemVenda.setBounds(170, 150, 100, 25);
		txtFOrdemVenda = new JTextField();
		txtFOrdemVenda.setBounds(260, 150, 80, 25);
		txtFOrdemVenda.setHorizontalAlignment(JTextField.RIGHT);
		txtFOrdemVenda.setFont(new Font("TimesNew Roman", Font.BOLD, 16));

		lblLucroPrejuizo = new JLabel("L|P/Ação:");
		lblLucroPrejuizo.setBounds(5, 180, 120, 25);
		txtFLucroPrejuizo = new JTextField();
		txtFLucroPrejuizo.setBounds(85, 180, 80, 25);
		txtFLucroPrejuizo.setHorizontalAlignment(JTextField.RIGHT);
		txtFLucroPrejuizo.setFont(new Font("TimesNew Roman", Font.BOLD, 16));
		txtFLucroPrejuizo.setEditable(false);

		lblPrecoTotal = new JLabel("TOTAL: ");
		lblPrecoTotal.setBounds(180, 180, 80, 25);
		lblPrecoTotal.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtFTotalFuse = new JTextField();
		txtFTotalFuse.setBounds(260, 180, 180, 40);
		txtFTotalFuse.setFont(new Font("Times New Roman", Font.BOLD, 28));
		// txtFTotalFuse.setBackground(Color.BLACK);
		txtFTotalFuse.setForeground(Color.RED);
		txtFTotalFuse.setHorizontalAlignment(JTextField.RIGHT);
		txtFTotalFuse.setEditable(false);

		lblPermiteEditar = new JLabel("Permite Editar ");
		lblPermiteEditar.setBounds(105, 590, 100, 25);

		painelPrincipal.add(lblTituloTela);
		painelPrincipal.add(lblCodigoFuse);
		painelPrincipal.add(txtFCodigoFuse);
		painelPrincipal.add(lblCodiAtivo);
		painelPrincipal.add(txtFCodiAtivo);
		painelPrincipal.add(lblLucroPrejuizo);
		painelPrincipal.add(txtFLucroPrejuizo);
		painelPrincipal.add(lblStartCompra);
		painelPrincipal.add(txtFStartCompra);
		painelPrincipal.add(lblOrdemCompra);
		painelPrincipal.add(txtFOrdemCompra);
		painelPrincipal.add(lblStartVenda);
		painelPrincipal.add(txtFStartVenda);
		painelPrincipal.add(lblOrdemVenda);
		painelPrincipal.add(txtFOrdemVenda);
		painelPrincipal.add(lblQuant);
		painelPrincipal.add(txtFQuantItens);
		painelPrincipal.add(lblDataInicio);
		painelPrincipal.add(lblPrecoTotal);
		painelPrincipal.add(txtFTotalFuse);
		painelPrincipal.add(tabVisualiza);
		desHabilitaEdicao();
		listfuse = contFuse.pesqNomeArray(nome);
		tam = listfuse.size();
		tam--;
		if (tam < 0) {
			FrameInicial.setPainelVisualiza(null);
			FrameInicial.setTabela(null);
			FrameInicial.atualizaTela();
		} else {
			controledaLista = new ControlaListaFuse(listfuse);
			fuse = controledaLista.first();
			carregarCampos(fuse);
		}

		add(painelPrincipal);
	}

	// TODO Vai para uma posição específica
	public static void irParaPoicao(int posicao) {
		controledaLista.setCurrentPosition(posicao);
		fuse = controledaLista.getAt(posicao);
		carregarCampos(fuse);
	}

	private boolean listavazia() {
		if (tam == -1) {
			return true;
		}
		return false;
	}

	// Colocar o indice correto para cada lista tam é o indice da lista de fuses
	List<ItensIndicadores> removeItemInd() {
		arrayItensIndi.remove(tam);
		atualizaTabelaIndi();
		return arrayItensIndi;
	}

	List<OrdemBovespa> removeOrdem() {
		listOrd.remove(tam);
		atualizaTabelaIndi();
		return listOrd;
	}

	// TODO Adicionar Indicador à Fuse
	public static List<ItensIndicadores> adicionaItem(Indicadores indi) {
		movimentos = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade de Movimentos"));
		itemIndi = new ItensIndicadores();
		itemIndi.setItemIndi(indi);
		itemIndi.setPeriodo(movimentos);
		arrayItensIndi.add(itemIndi);
		if (arrayItensIndi.contains(itemIndi)) {
		} else {
		}
		atualizaTabelaIndi();
		return arrayItensIndi;
	}

	public static List<OrdemBovespa> adicionaOrdem(Indicadores indi) {
		OrdemBovespa ord = new OrdemBovespa();
		// deve ler os campos referentes à ordem
		listOrd.add(ord);
		if (listOrd.contains(ord)) {
			// Deve perguntar se quer alterar essa ordem ou deixar duas ordens
			// identicas
		} else {
		}
		atualizaTabelaOrdens();
		return listOrd;
	}

	// TODO Atualizar as tabelas da Fuse
	public static JTable atualizaTabelaIndi() {
		tabelaItensIndicadores = new JTable();
		modeloTabela = new DefaultTableModel();
		modeloTabela = (DefaultTableModel) tabelaItensIndicadores.getModel();
		Object colunas[] = { "Nome", "Movimentos" };
		modeloTabela.setColumnIdentifiers(colunas);
		tabelaItensIndicadores = new JTable(modeloTabela);
		for (int i = 0; i < arrayItensIndi.size(); i++) {
			Object linha[] = { arrayItensIndi.get(i).getNomeIndi(), arrayItensIndi.get(i).getPeriodo() };
			modeloTabela.addRow(linha);
		}
		scrOperacoes.setViewportView(tabelaItensIndicadores);
		return tabelaItensIndicadores;
	}

	public static JTable atualizaTabelaOrdens() {
		tabelaOrdens = new JTable();
		modeloTabela = new DefaultTableModel();
		modeloTabela = (DefaultTableModel) tabelaOrdens.getModel();
		Object colunas[] = { "Ativo", "Valor", "Tipo" };
		modeloTabela.setColumnIdentifiers(colunas);
		tabelaItensIndicadores = new JTable(modeloTabela);
		// for (int i = 0; i < arrayItensIndi.size(); i++) {
		// Object linha[] = { listOrd.get(i).getCodiAtivo(),
		// listOrd.get(i).getValorOrdem(),
		// listOrd.get(i).getTipoOrdem() };
		// modeloTabela.addRow(linha);
		// }
		scrOrdens.setViewportView(tabelaOrdens);
		return tabelaOrdens;
	}

	public static JTable atualizaTabelaOpViculadas(final Fuse fuse) {
		System.out.println("PainelPedidos.atualizaTabela");
		modeloTabela = new DefaultTableModel();
		tabelaOperacoes = new JTable(modeloTabela);
		tabelaOperacoes.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int posicao = tabelaOperacoes.getSelectedRow();
				carregarCampos(fuse);

			}
		});
		Object colunas[] = { "Código", "Quantidade", "Valor Unit.", "Valor Op", "Tipo Op", "Data" };
		modeloTabela.setColumnIdentifiers(colunas);
		getTabelaOperacoes().setRowSelectionAllowed(false);
		getTabelaOperacoes().setCellSelectionEnabled(false);
		getTabelaOperacoes().setColumnSelectionAllowed(false);

		contFuse.carregarOperVinculadas(fuse);
		for (int i = 0; i < fuse.getListOperVinc().size(); i++) {
			Object linha[] = { fuse.getListOperVinc().get(i).getCodiAtivo(),
					fuse.getListOperVinc().get(i).getQtdPapeis(), fuse.getListOperVinc().get(i).getValorPapel(),
					fuse.getListOperVinc().get(i).getTotal(), fuse.getListOperVinc().get(i).getTipoOp(),
					fuse.getListOperVinc().get(i).getDataHoraExec() };
			modeloTabela.addRow(linha);
		}
		tabelaOperacoes.setShowGrid(true);
		scrOperacoes.setViewportView(tabelaOperacoes);
		return tabelaOperacoes;
	}

	// TODO Habilita novo
	public static void habilitaNovo() {
		limparCampos();
		arrayItensIndi = new ArrayList<ItensIndicadores>();
		fuse = new Fuse();
		atualizaTabelaIndi();
		atualizaTabelaOrdens();
		atualizaTabelaOpViculadas(fuse);
		String codigo = criaCodiPedi();
		try {
			daoFuse.reservaCodigo(codigo);
		} catch (SQLException e) {
			// TODO Erro ao conectar banco.
			JOptionPane.showMessageDialog(null, "Erro ao reservar código para o pedido/n" + e.getMessage());
			e.printStackTrace();
		}

		txtFCodigoFuse.setText(codigo);
		txtFCodigoFuse.setEditable(false);
		txtFCodiAtivo.setEditable(true);
		txtFCodiAtivo.setFocusable(true);
		txtFLucroPrejuizo.setEditable(true);
		txtFQuantItens.setEditable(true);
		txtFStartCompra.setEditable(true);
		txtFStartCompra.setFocusable(true);
		txtAreaObsPedido.setEditable(true);
		txtAreaObsPedido.setEnabled(true);

	}

	// TODO Habilita edição
	public static void habilitaEdicao() {
		txtFCodigoFuse.setEditable(false);
		txtFCodiAtivo.setEditable(true);
		txtFCodiAtivo.setFocusable(true);
		txtFLucroPrejuizo.setEditable(true);
		txtFQuantItens.setEditable(true);
		txtFStartCompra.setEditable(true);
		txtFStartCompra.setFocusable(true);
		txtFTotalFuse.setEditable(true);
		txtAreaObsPedido.setEditable(true);
		txtAreaObsPedido.setEnabled(true);

	}

	// TODO desabilita edição
	public void desHabilitaEdicao() {
		txtFCodigoFuse.setEditable(false);
		txtFCodiAtivo.setEditable(false);
		txtFCodiAtivo.setFocusable(false);
		txtFLucroPrejuizo.setEditable(false);
		txtFQuantItens.setEditable(false);
		txtFStartCompra.setEditable(false);
		txtFStartCompra.setFocusable(false);
		txtFTotalFuse.setEditable(false);
		txtFTotalFuse.setText(null);
		txtAreaObsPedido.setEditable(false);
		txtAreaObsPedido.setEnabled(false);
	}

	// TODO Limpa Campos
	public static void limparCampos() {
		txtFCodigoFuse.setText(null);
		txtFCodiAtivo.setText(null);
		txtFLucroPrejuizo.setText(null);
		txtFQuantItens.setText(null);
		txtFStartCompra.setText(null);
		txtFTotalFuse.setText(null);
		txtFQuantItens.setText(null);
		txtAreaObsPedido.setText(null);
		total = 0;
		movimentos = 0;
		quantTotItens = 0;
		arrayItensIndi = null;
		setTabelaItensIndicadores(null);
		// atualizaTabela();
	}

	// TODO Carregar a tela com um FUSE
	public static void carregarCampos(Fuse fuse) {
		if (!fuse.equals(null)) {
			txtFCodigoFuse.setText(fuse.getCodiFuse());
			txtFQuantItens.setText(String.valueOf(fuse.getQuant()));
			txtFTotalFuse.setText(String.valueOf(fuse.getTotal()));
			txtFCodiAtivo.setText(fuse.getCodiAtivo());
			if (fuse.getTipoFuse().equals("Vendida")) {
				txtFStartCompra.setText(String.valueOf(fuse.getPrecStartVenda()));
				txtFOrdemCompra.setText(String.valueOf(fuse.getPrecOrdVenda()));
				txtFStartVenda.setText(String.valueOf(fuse.getPrecStartCompra()));
				txtFOrdemVenda.setText(String.valueOf(fuse.getPrecOrdCompra()));
				txtFLucroPrejuizo.setText(String.valueOf(fuse.getLucroPrejuizo()));
				txtFTotalFuse.setText(String.valueOf(fuse.getTotal()));
			} else {
				txtFStartCompra.setText(String.valueOf(fuse.getPrecStartCompra()));
				txtFOrdemCompra.setText(String.valueOf(fuse.getPrecOrdCompra()));
				txtFStartVenda.setText(String.valueOf(fuse.getPrecStartVenda()));
				txtFOrdemVenda.setText(String.valueOf(fuse.getPrecOrdVenda()));
			}
			atualizaTabelaOpViculadas(fuse);
			setArrayItensIndi(fuse.getListIndiUtilizados());
			setListOrd(fuse.getListOrdGeradas());
			// atualizaTabelaIndi();
			atualizaTabelaOrdens();
		}
	}

	// TODO Ler os campos e retornar um pedido
	public static Fuse lerCampos() {
		fuse = new Fuse();
		fuse.setCodiFuse(txtFCodigoFuse.getText());
		if (!txtFCodiAtivo.getText().equals("") & !txtFCodiAtivo.equals(null)) {
			// fuse.setCodiCondPag(txtfCondPag.getText());
		}
		if (!txtFStartCompra.getText().equals("") & !txtFStartCompra.equals(null)) {
			// fuse.setxNome(txtfCliente.getText());
		}
		fuse.setListIndiUtilizados(arrayItensIndi);
		if (!txtFQuantItens.getText().equals(null) & !txtFQuantItens.getText().equals("")) {
			// fuse.setQuantItens(Integer.parseInt(txtFQuantItens.getText()));
		}
		if (!txtFTotalFuse.getText().equals(null) & !txtFTotalFuse.getText().equals("")) {
			// fuse.setTotalPedi(Float.parseFloat(txtFPrecopedi.getText()));
		}
		// fuse.setCodiCondPag(txtfCondPag.getText());
		// ItensProduto[] arrteste = pedi.getItensProdutoArray(arrayItemProd);
		// System.out.println("Tamanho " + arrteste.length);
		// System.out.println(arrteste[0].getNome_prod());
		return fuse;
	}

	// TODO Adicionar um cliente
	public static void adicionaUsuario(Pessoa usua) {
		txtFStartCompra.setText(usua.getNome());
	}

	// TODO Setar o codigo da FUSE
	public static String criaCodiPedi() {
		Calendar c = Calendar.getInstance();
		daoFuse.consultaUltimo();
		String codiPedi = String.valueOf(daoFuse.consultaUltimo()) + String.valueOf(c.get(Calendar.YEAR))
				+ String.valueOf(c.get(Calendar.MONTH)) + String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		return codiPedi;
	}

	public static JTextField getTxtFNomepedi() {
		return txtFCodiAtivo;
	}

	public static void setTxtFNomepedi(JTextField txtFNomepedi) {
		PainelFuse.txtFCodiAtivo = txtFNomepedi;
	}

	public static JScrollPane getScrOperacoes() {
		return scrOperacoes;
	}

	public static void setScrOpesracoes(JScrollPane scrOperacoes) {
		PainelFuse.scrOperacoes = scrOperacoes;
	}

	public static JTable getTabelaItensIndicadores() {
		return tabelaItensIndicadores;
	}

	public static void setTabelaItensIndicadores(JTable tabelaItensIndi) {
		PainelFuse.tabelaItensIndicadores = tabelaItensIndi;
	}

	public JLabel getLblQuantItens() {
		return lblQuant;
	}

	public void setLblQuantItens(JLabel lblQuantItens) {
		this.lblQuant = lblQuantItens;
	}

	public static List<ItensIndicadores> getArrayItensIndi() {
		return arrayItensIndi;
	}

	public static void setArrayItensIndi(List<ItensIndicadores> arrayItensIndi) {
		PainelFuse.arrayItensIndi = arrayItensIndi;
	}

	public static JTextField getTxtfCondPag() {
		return txtFCodiAtivo;
	}

	public static void setTxtfCondPag(JTextField txtfCondPag) {
		PainelFuse.txtFCodiAtivo = txtfCondPag;
	}

	public static JTextField getTxtfCliente() {
		return txtFStartCompra;
	}

	public static void setTxtfCliente(JTextField txtfCliente) {
		PainelFuse.txtFStartCompra = txtfCliente;
	}

	/**
	 * @return the arrayOperacao
	 */
	public static List<Operacao> getArrayOperacao() {
		return arrayOperacao;
	}

	/**
	 * @param arrayOperacao
	 *            the arrayOperacao to set
	 */
	public static void setArrayOperacao(List<Operacao> arrayOperacao) {
		PainelFuse.arrayOperacao = arrayOperacao;
	}

}
