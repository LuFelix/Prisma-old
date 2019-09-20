package online.lucianofelix.visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import online.lucianofelix.beans.CondPagamento;
import online.lucianofelix.beans.Lancamento;
import online.lucianofelix.beans.Pedido;
import online.lucianofelix.beans.Pessoa;
import online.lucianofelix.beans.Produto;
import online.lucianofelix.beans.TabelaPreco;
import online.lucianofelix.controle.ControlaLancamento;
import online.lucianofelix.controle.ControlaListaPedidos;
import online.lucianofelix.controle.ControlaPedido;
import online.lucianofelix.controle.ControlaProduto;
import online.lucianofelix.dao.DAOCondPagamento;
import online.lucianofelix.dao.DAOPedidoPrepSTM;
import online.lucianofelix.dao.DAOProdutosEstoque;
import online.lucianofelix.dao.DAOTabelaPreco;

public class PainelPedidos extends JPanel {

	private JLabel lblImagem;
	private JLabel lblTituloTela;
	private JLabel lblLogoEmpresa;
	private JLabel lblCodigopedi;
	private JLabel lblUsuario;
	private JLabel lblCliente;
	private JLabel lblCodiCliente;
	private JLabel lblQuantItens;
	private JLabel lblData;
	private JLabel lblPermiteEditar;

	private static JLabel lblTipoPedido;
	private static JTextField txtFCodigoPedi;
	private static JTextField txtFCodiCliente;
	private static JTextField txtFNomeCliente;
	private static JTextField txtfUsuario;
	private static JTextField txtFQuantItens;
	private static JTextField txtFPrecopedi;

	private static JScrollPane scrObsPedido;
	private static JComboBox<String> cmbTipoPedido;
	private static JComboBox<String> cmbTabPreco;

	private static JScrollPane scrImagem;
	private static JScrollPane scrP01;
	private static JScrollPane scrP02;
	private static JScrollPane scrP03;
	private JSplitPane sppImagem;
	private JSplitPane sppPrincipal;
	private JSplitPane sppSuperior;
	private JPanel pnlInferior;
	private JPanel pnlGrid;
	private static JTabbedPane tabVisualiza;
	private static JTable tbl01;
	private static JTable tbl02;

	private static JTable tbl03;

	private JRadioButton jrbEditarSim;
	private JRadioButton jrbEditarNao;
	private ButtonGroup grpRadio;

	// TODO objetos de controle

	private static ControlaListaPedidos controledaLista;
	private static ControlaPedido contPedi;
	private ControlaProduto contProd;
	private static Pedido pedi;
	private static DAOPedidoPrepSTM daoPedi;
	private static DAOTabelaPreco daoTabPreco;
	private static DAOCondPagamento daoCondPagamento;

	private List<Pedido> listPedi;
	int tam;

	private JLabel lblPrecoPedido;
	private JButton btnCapturaQRCupom;
	private static float totalPedido;
	private static int quantProdutos;
	private static int numTab;

	// Painel de Visualiza do pedido;
	private static List<TabelaPreco> listTabPreco;
	private static JTextArea txtAreaObsPedido;
	private static DefaultTableModel modeloTabela;
	private static DefaultTableModel modeloTabelaPagamentos;
	private static Lancamento lanc;
	private static ControlaLancamento contLanc;
	private static DAOProdutosEstoque daoProdEstoque;

	public PainelPedidos(String nome) {
		UIManager.put("TextField.font",
				new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Label.font",
				new Font("Times New Roman", Font.PLAIN, 12));
		UIManager.put("Button.font",
				new Font("Times New Roman", Font.BOLD, 12));
		// Controle
		contPedi = new ControlaPedido();
		controledaLista = new ControlaListaPedidos(listPedi);
		daoTabPreco = new DAOTabelaPreco();
		daoCondPagamento = new DAOCondPagamento();
		listTabPreco = new ArrayList<TabelaPreco>(
				daoTabPreco.pesquisaString(""));
		contLanc = new ControlaLancamento();
		daoProdEstoque = new DAOProdutosEstoque();

		// Painel Superior

		lblTituloTela = new JLabel("Pedido");
		lblTituloTela.setFont(new Font("Times New Roman", Font.BOLD, 36));
		setLblTipoPedido(new JLabel());
		getLblTipoPedido()
				.setFont(new Font("Times New Roman", Font.ITALIC, 28));
		lblData = new JLabel(String.valueOf(Calendar.getInstance()
				.get(Calendar.DATE)
				+ " - "
				+ String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1
						+ " - " + String.valueOf(
								Calendar.getInstance().get(Calendar.YEAR)))));
		lblData.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		lblCodigopedi = new JLabel("Código do Pedido:");
		txtFCodigoPedi = new JTextField();
		lblCodiCliente = new JLabel("Código Cliente: ");
		txtFCodiCliente = new JTextField();
		txtFCodiCliente.addFocusListener(new FocusListener() {
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
		lblCliente = new JLabel("Nome Cliente: ");
		txtFNomeCliente = new JTextField();
		lblQuantItens = new JLabel("Quantidade de ítens: ");
		txtFQuantItens = new JTextField();
		txtFQuantItens.setHorizontalAlignment(JTextField.RIGHT);
		txtFQuantItens.setFont(new Font("TimesNew Roman", Font.BOLD, 16));
		txtFQuantItens.setEditable(false);
		lblPrecoPedido = new JLabel("TOTAL: ");
		lblPrecoPedido.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtFPrecopedi = new JTextField();
		txtFPrecopedi.setFont(new Font("Times New Roman", Font.BOLD, 28));
		txtFPrecopedi.setForeground(Color.RED);
		txtFPrecopedi.setHorizontalAlignment(JTextField.RIGHT);
		txtFPrecopedi.setEditable(false);

		cmbTabPreco = new JComboBox<String>();
		cmbTabPreco.addItem("Tabela de Preços");
		for (int i = 0; i < listTabPreco.size(); i++) {
			cmbTabPreco.addItem(listTabPreco.get(i).getNomeTabela());
		}
		cmbTabPreco.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// JOptionPane.showMessageDialog(null, "Alterando para " +
				// cmbTabPreco.getSelectedItem());
			}
		});
		btnCapturaQRCupom = new JButton("Captura QR");

		// TODO Configuração do Painel Superior
		lblLogoEmpresa = new JLabel(
				new ImageIcon("C:\\SIMPRO\\img\\logo\\perfilsti180X180.jpg"));

		lblLogoEmpresa.setSize(new Dimension(200, 200));
		scrImagem = new JScrollPane(lblLogoEmpresa);
		scrImagem.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrImagem.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		sppImagem = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		sppImagem.add(lblTituloTela);

		sppImagem.add(scrImagem);
		sppImagem.setDividerLocation(50);
		sppImagem.setEnabled(false);
		sppImagem.setBackground(Color.WHITE);
		sppImagem.setForeground(Color.WHITE);
		sppImagem.setDividerSize(3);

		pnlGrid = new JPanel();
		pnlGrid.setBorder(BorderFactory.createEtchedBorder());
		pnlGrid.setLayout(new GridLayout(7, 2));
		pnlGrid.setBackground(Color.WHITE);
		pnlGrid.add(lblTipoPedido);
		pnlGrid.add(lblData);
		pnlGrid.add(lblCodigopedi);
		pnlGrid.add(txtFCodigoPedi);
		pnlGrid.add(lblCodiCliente);
		pnlGrid.add(txtFCodiCliente);
		pnlGrid.add(lblCliente);
		pnlGrid.add(txtFNomeCliente);
		pnlGrid.add(lblQuantItens);
		pnlGrid.add(txtFQuantItens);
		pnlGrid.add(lblPrecoPedido);
		pnlGrid.add(txtFPrecopedi);

		pnlGrid.add(cmbTabPreco);
		pnlGrid.add(btnCapturaQRCupom);

		sppSuperior = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		sppSuperior.setDividerLocation(200);
		sppSuperior.setDividerSize(3);
		sppSuperior.setEnabled(false);
		sppSuperior.add(sppImagem);
		sppSuperior.add(pnlGrid);

		// Painel Inferior
		tbl01 = new JTable();
		scrP01 = new JScrollPane();
		scrP01.setViewportView(tbl01);

		tbl02 = new JTable();
		scrP02 = new JScrollPane();
		scrP02.setViewportView(tbl02);

		tbl03 = new JTable();
		txtAreaObsPedido = new JTextArea();
		scrP03 = new JScrollPane();
		scrP03.setViewportView(txtAreaObsPedido);

		tabVisualiza = new JTabbedPane();
		tabVisualiza.addTab("Produtos", scrP01);
		tabVisualiza.addTab("Pagamentos", scrP02);
		tabVisualiza.addTab("Observações", scrP03);
		tabVisualiza.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// habilitaTabelaMovimentos();
			}
		});

		btnCapturaQRCupom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contPedi.capturaQR();

			}
		});

		pnlInferior = new JPanel();
		pnlInferior.setBorder(BorderFactory.createEtchedBorder());
		pnlInferior.setLayout(new GridLayout());
		pnlInferior.setBackground(Color.WHITE);
		pnlInferior.add(tabVisualiza);

		desHabilitaEdicao();
		listPedi = contPedi.listaPedidosTipo(AbaNegocios.getNomeNo());
		tam = listPedi.size();
		tam--;
		if (tam < 0) {
			FrameInicial.setPainelVisualiza(null);
			FrameInicial.setTabela(null);
			FrameInicial.atualizaTela();
		} else {
			controledaLista = new ControlaListaPedidos(listPedi);
			pedi = controledaLista.first();
			contPedi.carregarProdutosPedido(pedi);
			contPedi.carregarPagamentosPedido(pedi);
			carregarCampos(pedi);
			desbilitaTabela();
		}
		sppPrincipal = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		sppPrincipal.setDividerSize(3);
		sppPrincipal.setDividerLocation(250);
		sppPrincipal.setEnabled(false);
		sppPrincipal.setBackground(Color.WHITE);
		sppPrincipal.add(sppSuperior);
		sppPrincipal.add(pnlInferior);
		setLayout(new GridLayout());
		setBackground(Color.WHITE);
		add(sppPrincipal);
	}

	public static void desbilitaTabela() {
		getTabelaItensPedido().setEnabled(false);

	}

	// TODO Vai para uma posição específica
	public static void irParaPoicao(int posicao) {
		controledaLista.setCurrentPosition(posicao);
		pedi = controledaLista.getAt(posicao);
		contPedi.carregarProdutosPedido(pedi);
		contPedi.carregarPagamentosPedido(pedi);
		carregarCampos(pedi);
		desbilitaTabela();
	}

	private boolean listavazia() {
		if (tam == -1) {
			return true;
		}
		return false;
	}

	// TODO Editar lista de ítens do pedido
	public static void adicionaItem(Produto prod) {
		// Tratar se o usuário cancelar ou digitar letras ou 0
		System.out.println("PainelPedido.adicionarItem");
		prod.setQuantMovimento(Integer
				.parseInt(JOptionPane.showInputDialog(null, "QUANTIDADE")));
		System.out.println("Trabalhando no pedido: " + pedi.getCodiPedi());
		for (Produto produto : pedi.getItensProduto()) {
			System.out.println("Produto na lista: " + produto.getNome_prod()
					+ " :::: " + produto.getQuantMovimento());
			if (produto.equals(prod)) {
				System.out.println("Produto igual a " + produto.getNome_prod());
			}
			System.out.println(
					"Produto não encontrado ::::: " + prod.getNome_prod());

		}
		if (!pedi.getItensProduto().contains(prod)) {
			pedi.getItensProduto().add(prod);
			contPedi.adicionaItemProduto(pedi, prod);
			daoProdEstoque.novoMovProdEstoque("Padrao", null,
					prod.getCodi_prod_1(), pedi.getQuantItens(),
					pedi.getCodiPedi(), "Sai");
		} else {
			contPedi.alterarQuantProd(pedi, prod);
		}
		atualizaTabelaProdutos(pedi);
	}

	public static void adicionaPagamento(CondPagamento condPag) {
		float valor = Float.parseFloat(JOptionPane.showInputDialog("Valor:"));
		pedi = leCampos();
		lanc = new Lancamento();
		lanc.setCodiCondPag(condPag.getCodiCondPag());
		lanc.setValor(valor);
		if (valor <= 0) {
			contPedi.removerPagamento(pedi, lanc);
		} else {
			contLanc.adicionaLancamentoPedido(pedi, lanc);
		}
		atualizaTabelaPagamentos(pedi);
	}

	// TODO Atualizar a tabela de itens do pedido
	public static JTable atualizaTabelaProdutos(final Pedido pedi) {
		System.out.println("PainelPedidos.atualizaTabelaProdutos");
		quantProdutos = 0;
		totalPedido = 0;
		modeloTabela = new DefaultTableModel();
		tbl01 = new JTable(modeloTabela);
		Object colunas[] = {"Código", "Nome", "Quantidade", "Preço Unit.",
				"Total ítem"};
		modeloTabela.setColumnIdentifiers(colunas);
		getTabelaItensPedido().setRowSelectionAllowed(false);
		getTabelaItensPedido().setCellSelectionEnabled(false);
		getTabelaItensPedido().setColumnSelectionAllowed(false);
		tbl01.addMouseListener(new MouseListener() {

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
				int posicao = tbl01.getSelectedRow();
				adicionaItem(pedi.getItensProduto().get(posicao));
				carregarCampos(pedi);

			}
		});
		contPedi.carregarProdutosPedido(pedi);
		for (int i = 0; i < pedi.getItensProduto().size(); i++) {
			Object linha[] = {pedi.getItensProduto().get(i).getCodi_prod_1(),
					pedi.getItensProduto().get(i).getNome_prod(),
					pedi.getItensProduto().get(i).getQuantMovimento(),
					pedi.getItensProduto().get(i).getPrec_prod_1(),
					pedi.getItensProduto().get(i).getPrec_prod_1() * pedi
							.getItensProduto().get(i).getQuantMovimento()};
			modeloTabela.addRow(linha);
			quantProdutos = quantProdutos
					+ pedi.getItensProduto().get(i).getQuantMovimento();
			totalPedido = totalPedido
					+ (pedi.getItensProduto().get(i).getPrec_prod_1() * pedi
							.getItensProduto().get(i).getQuantMovimento());
		}
		tbl01.setShowGrid(true);
		scrP01.setViewportView(tbl01);
		txtFQuantItens.setText(String.valueOf(quantProdutos));
		txtFPrecopedi.setText(String.valueOf(totalPedido));
		return tbl01;
	}

	// TODO Atualiza tabela de pagamentos
	public static JTable atualizaTabelaPagamentos(final Pedido pedi) {
		System.out.println("PainelPedidos.atualizaTabelaPagamentos");
		modeloTabelaPagamentos = new DefaultTableModel();
		setTbl02(new JTable(modeloTabelaPagamentos));
		Object colunas[] = {"Cod. Pedido", "Cond. Pagamento", "Valor",
				"Data do Lançamento"};
		modeloTabelaPagamentos.setColumnIdentifiers(colunas);
		getTbl02().setRowSelectionAllowed(false);
		getTbl02().setCellSelectionEnabled(false);
		getTbl02().setColumnSelectionAllowed(false);
		getTbl02().addMouseListener(new MouseListener() {

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
				int posicao = getTbl02().getSelectedRow();
				adicionaPagamento(daoCondPagamento.pesquisaCondPagCodigo(
						pedi.getLancPedido().get(posicao).getCodiCondPag()));
				carregarCampos(pedi);
			}
		});
		contPedi.carregarPagamentosPedido(pedi);
		for (int i = 0; i < pedi.getLancPedido().size(); i++) {
			Object linha[] = {pedi.getCodiPedi(),
					daoCondPagamento.pesquisaNomeCodigo(
							pedi.getLancPedido().get(i).getCodiCondPag()),
					pedi.getLancPedido().get(i).getValor(),
					pedi.getLancPedido().get(i).getDataHoraLancamento()};
			modeloTabelaPagamentos.addRow(linha);
		}
		getTbl02().setShowGrid(true);
		scrP02.setViewportView(getTbl02());
		return getTbl02();
	}

	// TODO Ler os campos e retornar um pedido
	public static Pedido leCampos() {
		System.out.println("PainelPedidos.lerCampos");
		pedi = new Pedido();
		pedi.setTipoPedido(AbaNegocios.getNomeNo());
		pedi.setCodiPedi(txtFCodigoPedi.getText());
		if (!txtFCodiCliente.getText().equals("")
				& !txtFCodiCliente.equals(null)) {
			pedi.setCodiPessoaCliente(txtFCodiCliente.getText());
		}
		if (!txtFNomeCliente.getText().equals("")
				& !txtFNomeCliente.equals(null)) {
			pedi.setxNome(txtFNomeCliente.getText());
		}
		if (!txtFQuantItens.getText().equals(null)
				& !txtFQuantItens.getText().equals("")) {
			pedi.setQuantItens(Integer.parseInt(txtFQuantItens.getText()));
		}
		if (!txtFPrecopedi.getText().equals(null)
				& !txtFPrecopedi.getText().equals("")) {
			pedi.setTotalPedi(Float.parseFloat(txtFPrecopedi.getText()));
		}
		pedi.setCodiTabPreco(daoTabPreco
				.pesquisaCodigoNome(cmbTabPreco.getSelectedItem().toString()));
		pedi.setObsPedi1(txtAreaObsPedido.getText());
		return pedi;
	}

	// TODO Habilita novo
	public static void habilitaNovo() {
		pedi = new Pedido();
		limparCampos();
		atualizaTabelaProdutos(pedi);
		atualizaTabelaPagamentos(pedi);
		pedi.setCodiPedi(criaCodiPedi());
		pedi.setTipoPedido(AbaNegocios.getNomeNo());
		lblTipoPedido.setText(pedi.getTipoPedido());
		try {
			daoPedi.reservaCodigo(pedi.getCodiPedi());
		} catch (SQLException e) {
			// TODO Erro ao conectar banco.
			JOptionPane.showMessageDialog(null,
					"Erro ao reservar código para o pedido/n" + e.getMessage());
			e.printStackTrace();
		}
		cmbTabPreco.setEnabled(true);
		txtFCodigoPedi.setText(pedi.getCodiPedi());
		txtFCodigoPedi.setEditable(false);
		txtFCodiCliente.setFocusable(true);
		txtFCodiCliente.setEditable(false);
		txtFNomeCliente.setEditable(false);
		// txtfUsuario.setEditable(true);
		txtAreaObsPedido.setEditable(true);
		txtAreaObsPedido.setFocusable(true);
		getTabelaItensPedido().setEnabled(true);
		tabVisualiza.setFocusable(true);
		tabVisualiza.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				numTab = tabVisualiza.getSelectedIndex();
				if (numTab == 0) {
					FrameInicial.pesquisaProdutoAdicaoItem();
				} else if (numTab == 1) {
					FrameInicial.pesquisaCondPagamentoRealizaPedido();
				} else if (numTab == 2) {

				}
			}
		});
		tabVisualiza.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

			}

			@Override
			public void focusGained(FocusEvent e) {
				numTab = tabVisualiza.getSelectedIndex();
				if (numTab == 0) {
					FrameInicial.pesquisaProdutoAdicaoItem();
				} else if (numTab == 1) {
					FrameInicial.pesquisaCondPagamentoRealizaPedido();
				} else if (numTab == 2) {

				}
			}
		});
	}

	// TODO Habilita edição
	public static void habilitaEdicao() {
		cmbTabPreco.setEnabled(true);
		txtFCodigoPedi.setEditable(false);
		txtFCodiCliente.setFocusable(true);
		txtFCodiCliente.setEditable(true);
		txtFPrecopedi.setEditable(true);
		txtAreaObsPedido.setEditable(true);
		txtAreaObsPedido.setFocusable(true);
		getTabelaItensPedido().setEnabled(true);
		tabVisualiza.setFocusable(true);
		tabVisualiza.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				numTab = tabVisualiza.getSelectedIndex();
				if (numTab == 0) {
					FrameInicial.pesquisaProdutoAdicaoItem();
				} else if (numTab == 1) {
					FrameInicial.pesquisaCondPagamentoRealizaPedido();
				} else if (numTab == 2) {

				}

			}
		});
	}

	public int abaSelecionnada() {
		numTab = tabVisualiza.getSelectedIndex();
		return numTab;
	}

	// TODO desabilita edição
	public static void desHabilitaEdicao() {
		cmbTabPreco.setEnabled(false);
		txtFCodigoPedi.setEditable(false);
		txtFCodiCliente.setFocusable(false);
		txtFNomeCliente.setEditable(false);
		txtFQuantItens.setEditable(false);
		txtFCodiCliente.setEditable(false);
		txtFCodiCliente.setFocusable(false);
		txtFPrecopedi.setEditable(false);
		txtFPrecopedi.setText(null);
		txtAreaObsPedido.setEditable(false);
		txtAreaObsPedido.setFocusable(false);
		tabVisualiza.setFocusable(false);
		for (ChangeListener cl : tabVisualiza.getChangeListeners()) {
			tabVisualiza.removeChangeListener(cl);
		}
	}

	// TODO Limpa Campos
	public static void limparCampos() {
		pedi = new Pedido();
		cmbTabPreco.setSelectedIndex(0);
		txtFCodigoPedi.setText(null);
		txtFNomeCliente.setText(null);
		// txtfUsuario.setText(null);
		txtFQuantItens.setText(null);
		txtFCodiCliente.setText(null);
		txtFPrecopedi.setText(null);
		txtFQuantItens.setText(null);
		txtAreaObsPedido.setText(null);
		totalPedido = 0;
		quantProdutos = 0;
		limparTabelas();
	}

	static void limparTabelas() {
		setTabelaItensPedido(null);
		setTbl02(null);
	}

	// TODO Carregar a tela com um pedido
	public static void carregarCampos(Pedido pedi) {
		if (!pedi.equals(null)) {
			System.out.println("PainelPedidos.carregarCampos: ");
			txtFCodigoPedi.setText(pedi.getCodiPedi());
			txtFCodiCliente.setText(pedi.getCodiPessoaCliente());
			txtFNomeCliente.setText(pedi.getxNome());
			txtFQuantItens.setText(String.valueOf(pedi.getQuantItens()));
			txtFPrecopedi.setText(String.valueOf(pedi.getTotalPedi()));
			lblTipoPedido.setText(pedi.getTipoPedido());
			if (!(pedi.getCodiTabPreco() == null)) {
				cmbTabPreco.setSelectedItem(
						daoTabPreco.pesquisaNomeCodigo(pedi.getCodiTabPreco()));
			} else {
				cmbTabPreco.setSelectedIndex(0);
			}
			txtAreaObsPedido.setText(pedi.getObsPedi1());
			System.out.println("Conteudo de pedido tabela de preço:  "
					+ pedi.getCodiTabPreco());
			atualizaTabelaProdutos(pedi);
			atualizaTabelaPagamentos(pedi);
		}
	}

	// TODO Adicionar um cliente
	public static void adicionaUsuario(Pessoa usua) {
		txtFCodiCliente.setText(usua.getCodiPessoa());
		txtFNomeCliente.setText(usua.getNome());
	}

	// TODO Fechamento do pedido
	public static void fechamentoPedido() {
		System.out.println("Fechando Pedido");
		// somar os Ã­tens da tabela e aplicar o desconto
		// chamar a condiÃ§Ã£o de pagamento cartÃ£o ou mudar o status do pedido
		// para
		// pendente de pagamento e passar para o caixa caso vÃ¡ emitir cupom ou
		// outro comprovante.
		// o caixa emite o comprovante, muda o status para pendente de entrega
		// e o cliente dirige-se ao estoque para aretirada do produto
	}

	// TODO Setar o codigo do pedido
	public static String criaCodiPedi() {
		daoPedi = new DAOPedidoPrepSTM();
		Calendar c = Calendar.getInstance();
		daoPedi.consultaUltimo();
		String codiPedi = String.valueOf(daoPedi.consultaUltimo())
				+ String.valueOf(c.get(Calendar.YEAR))
				+ String.valueOf(c.get(Calendar.MONTH))
				+ String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		return codiPedi;
	}

	public void leQRcode() {

	}

	public static JScrollPane getScrPedido() {
		return scrP01;
	}

	public static void setScrPedido(JScrollPane scrPedido) {
		PainelPedidos.scrP01 = scrPedido;
	}

	public static JTable getTabelaItensPedido() {
		return tbl01;
	}

	public static void setTabelaItensPedido(JTable tabelaItensPedido) {
		PainelPedidos.tbl01 = tabelaItensPedido;
	}

	public JLabel getLblQuantItens() {
		return lblQuantItens;
	}

	public void setLblQuantItens(JLabel lblQuantItens) {
		this.lblQuantItens = lblQuantItens;
	}

	public static JTextField getTxtfCliente() {
		return txtFCodiCliente;
	}

	public static void setTxtfCliente(JTextField txtfCliente) {
		PainelPedidos.txtFCodiCliente = txtfCliente;
	}

	public static JTabbedPane getPnlTabAnexos() {
		return tabVisualiza;
	}

	public static void setPnlTabAnexos(JTabbedPane pnlTabAnexos) {
		PainelPedidos.tabVisualiza = pnlTabAnexos;
	}

	public static JTable getTbl02() {
		return tbl02;
	}

	public static void setTbl02(JTable tbl02) {
		PainelPedidos.tbl02 = tbl02;
	}

	public JLabel getLblTipoPedido() {
		return lblTipoPedido;
	}

	public void setLblTipoPedido(JLabel lblTipoPedido) {
		this.lblTipoPedido = lblTipoPedido;
	}
}
