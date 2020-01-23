package online.lucianofelix.visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
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
import javax.swing.border.EtchedBorder;
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
import online.lucianofelix.controle.ControlaTabelaPreco;
import online.lucianofelix.dao.DAOCondPagamento;
import online.lucianofelix.dao.DAOPedidoPrepSTM;
import online.lucianofelix.dao.DAOProdutosEstoque;
import online.lucianofelix.dao.DAOTabelaPreco;
import online.lucianofelix.util.JTextFieldMaiusculas;

public class PainelPedidos extends JPanel {

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
	private static JLabel txtFPrecopedi;

	private static JScrollPane scrObsPedido;
	private static JComboBox<String> cmbTipoPedido;
	private static JLabel lblTabPreco;

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
	private static ControlaProduto contProd;
	private static ControlaTabelaPreco contTPreco;
	private static Pedido pedi;
	private static DAOPedidoPrepSTM daoPedi;
	private static DAOTabelaPreco daoTabPreco;
	private static DAOCondPagamento daoCondPagamento;

	private List<Pedido> listPedi;
	int tam;

	private JLabel lblPrecoPedido;
	private static JButton btnCapturaQRCupom;

	private static BigDecimal totalPedido;
	private static BigDecimal quant;
	private static int nItens;
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
		contProd = new ControlaProduto();
		controledaLista = new ControlaListaPedidos(listPedi);
		daoTabPreco = new DAOTabelaPreco();
		contTPreco = new ControlaTabelaPreco();
		daoCondPagamento = new DAOCondPagamento();
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
		lblTabPreco = new JLabel("Tabela de Preços:");
		lblTabPreco.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTabPreco.setForeground(Color.RED);
		lblCodigopedi = new JLabel("Código do Pedido:");
		txtFCodigoPedi = new JTextField();
		lblCodiCliente = new JLabel("Código Cliente: ");
		txtFCodiCliente = new JTextField();
		txtFCodiCliente.addFocusListener(new FocusAdapter() {

			public void focusGained(FocusEvent e) {
				// TODO txtfCliente ao receber foco
				FrameInicial.pesquisaUsuarioAdicionarAOPedido();
			}
		});
		lblCliente = new JLabel("Nome Cliente: ");
		txtFNomeCliente = new JTextFieldMaiusculas();
		lblQuantItens = new JLabel("Quantidade de ítens: ");
		txtFQuantItens = new JTextField();
		txtFQuantItens.setHorizontalAlignment(JTextField.RIGHT);
		txtFQuantItens.setFont(new Font("TimesNew Roman", Font.BOLD, 16));
		txtFQuantItens.setEditable(false);
		lblPrecoPedido = new JLabel("TOTAL: ");
		lblPrecoPedido.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtFPrecopedi = new JLabel();
		txtFPrecopedi.setFont(new Font("Times New Roman", Font.BOLD, 28));
		txtFPrecopedi.setForeground(Color.RED);
		txtFPrecopedi.setHorizontalAlignment(JTextField.RIGHT);
		txtFPrecopedi.setPreferredSize(getMinimumSize());

		btnCapturaQRCupom = new JButton("Captura QR");

		// TODO Configuração do Painel Superior
		lblLogoEmpresa = new JLabel(
				new ImageIcon("C:\\SIMPRO\\img\\logo\\perfilsti180X180.jpg"));
		lblLogoEmpresa.setSize(new Dimension(400, 400));
		lblLogoEmpresa.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		sppImagem = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		sppImagem.add(lblTituloTela);

		sppImagem.add(lblLogoEmpresa);
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
		pnlGrid.add(lblTabPreco);
		pnlGrid.add(btnCapturaQRCupom);
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
	// public static void adicionaItem(Produto prod) {
	// // Tratar se o usuário cancelar ou digitar letras ou 0
	// System.out.println(
	// ">>>>>>>>>>>>>>>>>>>>>>>> PainelPedido.adicionarItem");
	// if (contProd.consultaUltimoPreco(prod)) {
	// prod.setQuantMovimento(new BigDecimal(
	// JOptionPane.showInputDialog(null, "QUANTIDADE")));
	// System.out.println(">>>>>>>>>>>>>>>>>>>> quantidade inserida"
	// + prod.getQuantMovimento());
	// for (Produto produto : pedi.getItensProduto()) {
	// System.out.println("Produto na lista: " + produto.getNome_prod()
	// + " :::: " + produto.getQuantMovimento());
	// if (produto.equals(prod)) {
	// System.out.println(
	// "Produto igual a " + produto.getNome_prod());
	// }
	// System.out.println(
	// "Produto não encontrado ::::: " + prod.getNome_prod());
	//
	// }
	//
	// } else {
	// // contProd.alteraPreco();
	// }
	// if (!pedi.getItensProduto().contains(prod)) {
	// pedi.getItensProduto().add(prod);
	// contPedi.adicionaItemProduto(pedi, prod);
	// daoProdEstoque.novoMovProdEstoque("Padrao", null,
	// prod.getCodi_prod_1(), pedi.getQuantItens(),
	// pedi.getCodiPedi(), "Sai");
	// } else {
	// contPedi.alterarQuantProd(pedi, prod);
	// }
	// atualizaTabelaItensPedido(pedi);
	// }
	public static void adicionaItem(Produto prod, String nomeTabela) {
		// Tratar se o usuário cancelar ou digitar letras ou 0
		System.out.println(
				">>>>>>>>>>>>>>>>>>>>>>>>  PainelPedido.adicionarItem");

		if (nomeTabela.equals("Selecionar Tabela")) {
			nomeTabela = contTPreco.selecionaNomeTabelaPreco();
			lblTabPreco.setText(nomeTabela);
			contPedi.gravarTabPreco(leCampos());
		} else {
			boolean resp = contProd.consultaUltimoPreco(prod, nomeTabela);
			if ((resp != false)) {
				BigDecimal quant = new BigDecimal(
						JOptionPane.showInputDialog(null, "QUANTIDADE"));
				if (quant.compareTo(new BigDecimal(0)) < 0 | quant.equals(null)
						| quant.equals("")) {
					JOptionPane.showMessageDialog(null,
							"Verifique a quantidade inserida " + quant, "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					prod.setQuantMovimento(quant);
				}

				System.out.println(
						">>>>>>>>>>>>>>>>>>>>      quantidade inserida "
								+ prod.getQuantMovimento());
				for (Produto produto : pedi.getItensProduto()) {
					System.out.println(
							"Produto na lista: " + produto.getNome_prod()
									+ " :::: " + produto.getQuantMovimento());
					if (produto == prod) {
						System.out.println(
								"Produto igual a " + produto.getNome_prod());
					} else {
						System.out.println("Produto não encontrado ::::: "
								+ prod.getNome_prod());
					}

				}

			} else {
				// contProd.alteraPreco();
			}

		}
		if (!pedi.getItensProduto().contains(prod)) {
			pedi.getItensProduto().add(prod);
			contPedi.adicionaItemProduto(pedi, prod);
			daoProdEstoque.novoMovProdEstoque("Padrao", null,
					prod.getCodi_prod_1(), pedi.getQuantItens(),
					pedi.getCodiPedi(), "Sai");
			atualizaTabelaItensPedido(pedi);
		} else {
			contPedi.alterarQuantProd(pedi, prod);
			atualizaTabelaItensPedido(pedi);
		}

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
	public static JTable atualizaTabelaItensPedido(final Pedido pedi) {
		System.out.println("PainelPedidos.atualizaTabelaProdutos");
		quant = new BigDecimal(0);
		totalPedido = new BigDecimal(0);
		modeloTabela = new DefaultTableModel();
		tbl01 = new JTable(modeloTabela);
		Object colunas[] = {"Código", "Nome", "Quantidade", "Preço Unit.",
				"Total ítem"};
		modeloTabela.setColumnIdentifiers(colunas);
		getTabelaItensPedido().setRowSelectionAllowed(false);
		getTabelaItensPedido().setCellSelectionEnabled(false);
		getTabelaItensPedido().setColumnSelectionAllowed(false);
		tbl01.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tbl01.isEnabled()) {
					int posicao = tbl01.getSelectedRow();
					if (getLblTabPreco().getText() == "Selecionar Tabela"
							| getLblTabPreco() == null) {
						lblTabPreco
								.setText(contTPreco.selecionaNomeTabelaPreco());
					} else {
						adicionaItem(pedi.getItensProduto().get(posicao),
								PainelPedidos.getLblTabPreco().getText());
					}
				}
			}
		});
		contPedi.carregarProdutosPedido(pedi);

		for (int i = 0; i < pedi.getItensProduto().size(); i++) {
			if (pedi.getItensProduto().get(i).getQuantMovimento() != null) {
				Object linha[] = {
						pedi.getItensProduto().get(i).getCodi_prod_1(),
						pedi.getItensProduto().get(i).getNome_prod(),
						pedi.getItensProduto().get(i).getQuantMovimento()
								.setScale(3, BigDecimal.ROUND_HALF_UP),
						pedi.getItensProduto().get(i).getPrec_prod_1(),
						pedi.getItensProduto().get(i).getPrec_prod_1()
								.multiply(pedi.getItensProduto().get(i)
										.getQuantMovimento())
								.setScale(2, BigDecimal.ROUND_DOWN)};
				modeloTabela.addRow(linha);
				quant = quant
						.add(pedi.getItensProduto().get(i).getQuantMovimento());
				totalPedido = totalPedido
						.add(pedi.getItensProduto().get(i).getPrec_prod_1()
								.multiply(pedi.getItensProduto().get(i)
										.getQuantMovimento())
								.setScale(2, BigDecimal.ROUND_DOWN));
			}
		}
		nItens = quant.ROUND_UP;
		tbl01.setShowGrid(true);
		scrP01.setViewportView(tbl01);
		txtFQuantItens.setText(String.valueOf(nItens));
		txtFPrecopedi.setText(String.valueOf(totalPedido).replace(".", ","));
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
		getTbl02().addMouseListener(new MouseAdapter() {

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
			pedi.setQuantItens(
					new BigDecimal(txtFQuantItens.getText()).intValue());
		}
		if (!txtFPrecopedi.getText().equals(null)
				& !txtFPrecopedi.getText().equals("")) {
			pedi.setTotalPedi(
					new BigDecimal(txtFPrecopedi.getText().replace(",", ".")));
		}
		if (lblTabPreco.getText() != null & lblTabPreco.getText() != "") {
			pedi.setCodiTabPreco(
					daoTabPreco.pesquisaCodigoNome(lblTabPreco.getText()));

		}
		pedi.setObsPedi1(txtAreaObsPedido.getText());
		return pedi;
	}

	// TODO Habilita novo
	public static void habilitaNovo() {
		pedi = new Pedido();
		limparCampos();
		atualizaTabelaItensPedido(pedi);
		atualizaTabelaPagamentos(pedi);
		pedi.setCodiPedi(criaCodiPedi());
		pedi.setTipoPedido(AbaNegocios.getNomeNo());
		lblTipoPedido.setText(pedi.getTipoPedido());
		btnCapturaQRCupom.setEnabled(true);
		try {
			daoPedi.reservaCodigo(pedi.getCodiPedi());
		} catch (SQLException e) {
			// TODO Erro ao conectar banco.
			JOptionPane.showMessageDialog(null,
					"Erro ao reservar código para o pedido/n" + e.getMessage());
			e.printStackTrace();
		}
		lblTabPreco.setText(contTPreco.selecionaNomeTabelaPreco());
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
		tabVisualiza.addFocusListener(new FocusAdapter() {

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
		txtFCodigoPedi.setEditable(false);
		txtFCodiCliente.setFocusable(true);
		txtFCodiCliente.setEditable(true);
		txtAreaObsPedido.setEditable(true);
		txtAreaObsPedido.setFocusable(true);
		getTabelaItensPedido().setEnabled(true);
		tabVisualiza.setFocusable(true);
		btnCapturaQRCupom.setEnabled(true);
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
		txtFCodigoPedi.setEditable(false);
		txtFCodiCliente.setFocusable(false);
		txtFNomeCliente.setEditable(false);
		txtFQuantItens.setEditable(false);
		txtFCodiCliente.setEditable(false);
		txtFCodiCliente.setFocusable(false);
		txtFPrecopedi.setText(null);
		txtAreaObsPedido.setEditable(false);
		txtAreaObsPedido.setFocusable(false);
		tabVisualiza.setFocusable(false);
		btnCapturaQRCupom.setEnabled(false);
		for (ChangeListener cl : tabVisualiza.getChangeListeners()) {
			tabVisualiza.removeChangeListener(cl);
		}
	}

	// TODO Limpa Campos
	public static void limparCampos() {
		pedi = new Pedido();
		lblTabPreco.setText(null);
		txtFCodigoPedi.setText(null);
		txtFNomeCliente.setText(null);
		// txtfUsuario.setText(null);
		txtFQuantItens.setText(null);
		txtFCodiCliente.setText(null);
		txtFPrecopedi.setText(null);
		txtFQuantItens.setText(null);
		txtAreaObsPedido.setText(null);
		totalPedido = new BigDecimal(0);
		quant = new BigDecimal(0);
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
			if (!pedi.getCodiTabPreco().equals(null)
					& !pedi.getCodiTabPreco().equals("")) {
				lblTabPreco.setText(
						daoTabPreco.pesquisaNomeCodigo(pedi.getCodiTabPreco()));
			} else {
				lblTabPreco.setText("Selecionar Tabela");
			}
			txtAreaObsPedido.setText(pedi.getObsPedi1());
			System.out.println(
					">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Conteudo de pedido tabela de preço:  "
							+ pedi.getCodiTabPreco());
			atualizaTabelaItensPedido(pedi);
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

	/**
	 * @return the lblTabPreco
	 */
	public static JLabel getLblTabPreco() {
		return lblTabPreco;
	}

	/**
	 * @param lblTabPreco
	 *            the lblTabPreco to set
	 */
	public static void setLblTabPreco(JLabel lblTabPreco) {
		PainelPedidos.lblTabPreco = lblTabPreco;
	}

}
