package online.lucianofelix.visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
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
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import online.lucianofelix.beans.CondPagamento;
import online.lucianofelix.beans.Pedido;
import online.lucianofelix.beans.Pessoa;
import online.lucianofelix.beans.Servico;
import online.lucianofelix.controle.ControlaListaPedidos;
import online.lucianofelix.controle.ControlaPedido;
import online.lucianofelix.dao.DAOPedidoPrepSTM;

public class PainelOrdServicos extends JPanel {

	// JFrame telaPedido;
	JPanel painelPrincipal;
	private JLabel lblTituloTela;
	// Labels e text fields
	private JLabel lblCodigopedi;
	private JLabel lblCondPag;
	private JLabel lblUsuario;
	private JLabel lblCliente;
	private JLabel lblQuantItens;
	private JLabel lblData;
	private JLabel lblPermiteEditar;
	private static JTextField txtFCodigoPedi;
	private static JTextField txtfCondPag;
	private static JTextField txtfUsuario;
	private static JTextField txtfCliente;
	private static JTextField txtFQuantItens;
	private static JScrollPane scrProdutos;
	private JTabbedPane tabVisualiza;

	private static JTextField txtFPrecopedi;
	private JButton btnProximo;
	private JButton btnAnterior;
	private static JButton btnNovo;
	private static JButton btnEditar;
	private static JButton btnSalvar;
	private JButton btnExcluir;
	private static JButton btnCancelar;

	private JRadioButton jrbEditarSim;
	private JRadioButton jrbEditarNao;
	private ButtonGroup grpRadio;
	private ActionListener acaoEditar;
	private ActionListener sobrescrever;

	// TODO objetos de controle

	private static ControlaListaPedidos controledaLista;
	private ControlaPedido contPedi;
	private static Pedido pedi;
	private DAOPedidoPrepSTM daoPedi;
	private List<Pedido> listpedi;
	int tam;
	private JLabel lblPrecoPedido;
	private JScrollPane scrObsPedido;
	private static JTextArea txtAreaObsPedido;
	private static JTable tabelaItensPedido;
	private static DefaultTableModel modeloTabela;
	private static float total;
	private static int quant;
	private static int quantTotItens;

	public static JButton getBtnSalvar() {
		return btnSalvar;
	}

	public static void setBtnSalvar(JButton btnSalvar) {
		PainelOrdServicos.btnSalvar = btnSalvar;
	}

	public PainelOrdServicos()
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {

		// TODO Construtor da Tela de Pedido
		UIManager.put("TextField.font",
				new Font("Times New Roman", Font.BOLD, 16));
		UIManager.put("Label.font", new Font("Times New Roman", Font.BOLD, 10));
		UIManager.put("Button.font",
				new Font("Times New Roman", Font.BOLD, 12));
		UIManager.setLookAndFeel(
				"com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		contPedi = new ControlaPedido();
		daoPedi = new DAOPedidoPrepSTM();
		controledaLista = new ControlaListaPedidos(listpedi);
		pedi = new Pedido();

		// telaPedido = new JFrame("SIAC ECF - Cadastro de Pedido");
		// telaPedido.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// telaPedido.setBounds(350, 100, 610, 710);
		setLayout(null);
		painelPrincipal = new JPanel();
		painelPrincipal.setBorder(new EmptyBorder(0, 0, 0, 0));
		painelPrincipal.setLayout(null);
		painelPrincipal.setSize(610, 800);
		painelPrincipal.setBackground(Color.white);

		tabVisualiza = new JTabbedPane();
		tabVisualiza.setBounds(10, 250, 620, 350);
		scrProdutos = new JScrollPane();
		scrProdutos.setSize(600, 200);
		tabVisualiza.add("Pedido", scrProdutos);
		// telaPedido.setContentPane(painelPrincipal);

		// TODO Configuração dos Labels e text fields

		lblTituloTela = new JLabel("Pedido");
		lblTituloTela.setBounds(50, 0, 100, 80);
		lblTituloTela.setFont(new Font("Times New Roman", Font.BOLD, 28));

		lblCodigopedi = new JLabel("Código :");
		lblCodigopedi.setBounds(5, 50, 90, 20);
		txtFCodigoPedi = new JTextField(0);
		txtFCodigoPedi.setBounds(85, 60, 170, 20);
		lblData = new JLabel(String.valueOf(Calendar.getInstance().getTime()));
		lblData.setBounds(5, 210, 180, 20);

		lblCondPag = new JLabel("Cond. Pag: ");
		lblCondPag.setBounds(5, 90, 70, 25);
		txtfCondPag = new JTextField();
		txtfCondPag.setBounds(100, 90, 170, 25);

		lblUsuario = new JLabel("UsuÃ¡rio: ");
		lblUsuario.setBounds(10, 120, 90, 25);
		txtfUsuario = new JTextField();
		txtfUsuario.setBounds(100, 120, 170, 25);

		lblCliente = new JLabel("Cliente: ");
		lblCliente.setBounds(10, 150, 90, 25);
		txtfCliente = new JTextField();
		txtfCliente.setBounds(100, 150, 170, 25);

		txtfCliente.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Ao receber foco
				FrameInicial.pesquisaUsuarioAdicionarAOPedido();

			}
		});

		lblQuantItens = new JLabel("Qtd. �?tens: ");
		lblQuantItens.setBounds(10, 180, 90, 25);
		txtFQuantItens = new JTextField();
		txtFQuantItens.setBounds(100, 180, 170, 25);

		txtFPrecopedi = new JTextField();
		txtFPrecopedi.setBounds(100, 210, 170, 25);

		lblPermiteEditar = new JLabel("Permite Editar ");
		lblPermiteEditar.setBounds(105, 590, 100, 25);

		// TODO Posicionamento dos e ações Botões

		// JRadio Buttons+++++++++++++++++
		jrbEditarSim = new JRadioButton("Sim");
		jrbEditarSim.setBounds(95, 560, 50, 35);
		jrbEditarNao = new JRadioButton("Nï¿½o");
		jrbEditarNao.setBounds(155, 560, 50, 35);
		grpRadio = new ButtonGroup();
		grpRadio.add(jrbEditarSim);
		grpRadio.add(jrbEditarNao);

		// Ações Radio Buttons++++++++++++++++
		jrbEditarNao.setSelected(true);

		// Jbuttons+++++++++++++++++++++++

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(105, 570, 100, 30);
		// Botao Editar Visivel
		// btnCancelar = new JButton("Cancelar");
		// btnCancelar.setBounds(105, 590, 100, 30);
		// btnProximo = new JButton("PrÃ³ximo");
		// btnProximo.setBounds(305, 560, 100, 30);
		// btnNovo = new JButton("Novo");
		// btnNovo.setBounds(205, 590, 200, 30);
		// btnSalvar = new JButton("Salvar");
		// btnSalvar.setBounds(405, 560, 100, 30);
		// btnExcluir = new JButton("Excluir");
		// btnExcluir.setBounds(405, 590, 100, 30);
		//
		// BotÃ£o Editar Invisivel
		btnEditar.setVisible(false);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(25, 420, 100, 60);
		btnProximo = new JButton("PrÃ³ximo");
		btnProximo.setBounds(225, 420, 100, 30);
		btnNovo = new JButton("Novo");
		btnNovo.setBounds(125, 450, 200, 30);
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(325, 420, 100, 30);
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(325, 450, 100, 30);
		btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(125, 420, 100, 30);
		// TODO Actions
		acaoEditar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				habilitaEdicao();
				funcaoSobrescrever();
			}
		};
		btnEditar.addActionListener(acaoEditar);

		btnNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				habilitaNovo();
				funcaoSalvar();
			}
		});
		btnAnterior.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!listavazia()) {
					if (controledaLista.getCurrentPosition() == 0) {
						controledaLista.setCurrentPosition(tam);
						pedi = controledaLista.getAt(tam);
					} else {
						pedi = controledaLista.previous();
					}
					System.out.println("Foi para: "
							+ controledaLista.getCurrentPosition());
					carregarCampos(pedi);
				}
			}
		});
		btnProximo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!listavazia()) {
					if (controledaLista.getCurrentPosition() < tam) {
						pedi = controledaLista.next();
					} else {
						controledaLista.setCurrentPosition(0);
						pedi = controledaLista.getAt(0);
					}
					carregarCampos(pedi);
					// FrameECF.atualizaTela(pedi);
				}
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrameInicial.setTabela(contPedi.tblPedidosNomeTipo("", ""));
				FrameInicial.scrLista.setViewportView(FrameInicial.getTabela());
				desHabilitaEdicao();
				carregarCampos(pedi);
				limparBtnSalvar();
			}
		});
		// Ações de foco dos Text Fields
		txtFCodigoPedi.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				pedi = new Pedido();
				carregarCampos(pedi);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				pedi = new Pedido();
				carregarCampos(pedi);
			}
		});

		// Configurações do Painel principal
		desHabilitaEdicao();
		painelPrincipal.setLayout(null);
		painelPrincipal.add(lblTituloTela);
		painelPrincipal.add(lblCodigopedi);
		painelPrincipal.add(txtFCodigoPedi);
		painelPrincipal.add(lblCondPag);
		painelPrincipal.add(txtfCondPag);
		painelPrincipal.add(lblUsuario);
		painelPrincipal.add(txtfUsuario);
		painelPrincipal.add(lblCliente);
		painelPrincipal.add(txtfCliente);
		painelPrincipal.add(lblQuantItens);
		painelPrincipal.add(txtFQuantItens);
		painelPrincipal.add(lblData);
		painelPrincipal.add(txtFPrecopedi);
		// painelPrincipal.add(lblPermiteEditar);
		painelPrincipal.add(btnAnterior);
		painelPrincipal.add(btnNovo);
		painelPrincipal.add(btnProximo);
		painelPrincipal.add(btnEditar);
		painelPrincipal.add(btnSalvar);
		painelPrincipal.add(btnExcluir);
		painelPrincipal.add(btnCancelar);
		painelPrincipal.add(tabVisualiza);
		// painelPrincipal.add(jrbEditarSim);z\
		// painelPrincipal.add(jrbEditarNao);

		listpedi = daoPedi.pesquisarNome("");
		tam = listpedi.size();
		tam--;
		if (listavazia()) {
			FrameInicial.setPainelVisualiza(null);
			FrameInicial.getScrVisualiza()
					.setViewportView(FrameInicial.getPainelVisualiza());
			JOptionPane.showMessageDialog(null, "Lista Vazia");
		} else {
			pedi = controledaLista.first();
			carregarCampos(pedi);
		}
		add(painelPrincipal);
	} // TODO
		// Fim
		// do
		// construtor
		// 1
		// Inicio
		// da
		// sobrecarga

	public PainelOrdServicos(String nome) {
		UIManager.put("TextField.font",
				new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Label.font",
				new Font("Times New Roman", Font.PLAIN, 12));
		UIManager.put("Button.font",
				new Font("Times New Roman", Font.BOLD, 12));

		contPedi = new ControlaPedido();
		daoPedi = new DAOPedidoPrepSTM();
		controledaLista = new ControlaListaPedidos(listpedi);
		// telaPedido = new JFrame("SIAC ECF - Cadastro de Pedido");
		// telaPedido.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// telaPedido.setBounds(350, 100, 610, 710);
		setLayout(null);

		painelPrincipal = new JPanel();
		painelPrincipal.setBorder(new EmptyBorder(0, 0, 0, 0));
		painelPrincipal.setLayout(null);
		painelPrincipal.setSize(610, 800);
		// telaPedido.setContentPane(painelPrincipal);

		txtAreaObsPedido = new JTextArea();
		scrObsPedido = new JScrollPane();
		scrObsPedido.setSize(420, 320);
		scrObsPedido.setViewportView(txtAreaObsPedido);
		scrProdutos = new JScrollPane();
		scrProdutos.setSize(420, 320);

		tabVisualiza = new JTabbedPane();
		tabVisualiza.setBounds(10, 180, 440, 230);
		tabVisualiza.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabVisualiza.add("Produtos", scrProdutos);
		tabVisualiza.addTab("Observações", scrObsPedido);

		// TODO Labels e Text fields

		lblTituloTela = new JLabel("Pedido");
		lblTituloTela.setBounds(50, 0, 150, 40);
		lblTituloTela.setFont(new Font("Times New Roman", Font.BOLD, 28));

		lblCodigopedi = new JLabel("Código:");
		lblCodigopedi.setBounds(5, 60, 90, 25);
		txtFCodigoPedi = new JTextField();
		txtFCodigoPedi.setBounds(75, 60, 120, 25);
		// txtFCodigopedi.setColumns(6);
		lblData = new JLabel(String.valueOf(Calendar.getInstance().getTime()));
		lblData.setBounds(240, 60, 165, 25);

		lblCondPag = new JLabel("Cond Pag: ");
		lblCondPag.setBounds(5, 90, 120, 25);
		txtfCondPag = new JTextField();
		txtfCondPag.setBounds(75, 90, 120, 25);
		txtfCondPag.addFocusListener(new FocusListener() {

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

		lblUsuario = new JLabel("Usuário:");
		lblUsuario.setBounds(210, 90, 120, 25);
		txtfUsuario = new JTextField();
		txtfUsuario.setBounds(270, 90, 175, 25);

		lblCliente = new JLabel("Cliente: ");
		lblCliente.setBounds(5, 120, 120, 25);
		txtfCliente = new JTextField();
		txtfCliente.setBounds(75, 120, 370, 25);
		txtfCliente.addFocusListener(new FocusListener() {
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
		lblQuantItens = new JLabel("Qtd. ítens: ");
		lblQuantItens.setBounds(5, 150, 120, 25);
		txtFQuantItens = new JTextField();
		txtFQuantItens.setBounds(75, 150, 80, 25);
		txtFQuantItens.setHorizontalAlignment(JTextField.RIGHT);
		txtFQuantItens.setFont(new Font("TimesNew Roman", Font.BOLD, 16));
		txtFQuantItens.setEditable(false);

		lblPrecoPedido = new JLabel("TOTAL: ");
		lblPrecoPedido.setBounds(160, 150, 80, 25);
		lblPrecoPedido.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtFPrecopedi = new JTextField();
		txtFPrecopedi.setBounds(240, 150, 200, 40);
		txtFPrecopedi.setFont(new Font("Times New Roman", Font.BOLD, 28));
		// txtFPrecopedi.setBackground(Color.LIGHT_GRAY);
		txtFPrecopedi.setForeground(Color.RED);
		txtFPrecopedi.setHorizontalAlignment(JTextField.RIGHT);
		txtFPrecopedi.setEditable(false);

		lblPermiteEditar = new JLabel("Permite Editar ");
		lblPermiteEditar.setBounds(105, 590, 100, 25);

		// TODO Posicionamento Botões

		// JRadio Buttons+++++++++++++++++
		jrbEditarSim = new JRadioButton("Sim");
		jrbEditarSim.setBounds(95, 560, 50, 35);
		jrbEditarNao = new JRadioButton("Não");
		jrbEditarNao.setBounds(155, 560, 50, 35);
		grpRadio = new ButtonGroup();
		grpRadio.add(jrbEditarSim);
		grpRadio.add(jrbEditarNao);

		// AÃ§Ã£o Radio Buttons++++++++++++++++
		jrbEditarNao.setSelected(true);

		// JButtons+++++++++++++++++++++++

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(105, 560, 100, 30);
		// BotÃ£o Editar Visivel
		// btnCancelar = new JButton("Cancelar");
		// btnCancelar.setBounds(105, 590, 100, 30);
		// btnProximo = new JButton("Prï¿½ximo");
		// btnProximo.setBounds(305, 560, 100, 30);
		// btnNovo = new JButton("Novo");
		// btnNovo.setBounds(205, 590, 200, 30);
		// btnSalvar = new JButton("Salvar");
		// btnSalvar.setBounds(405, 560, 100, 30);
		// btnExcluir = new JButton("Excluir");
		// btnExcluir.setBounds(405, 590, 100, 30);
		//
		// TODO Botão Editar Invisivel
		btnEditar.setVisible(false);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(25, 420, 100, 60);
		btnProximo = new JButton("Próximo");
		btnProximo.setBounds(225, 420, 100, 30);
		btnNovo = new JButton("Novo");
		btnNovo.setBounds(125, 450, 200, 30);
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(325, 420, 100, 30);
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(325, 450, 100, 30);
		btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(125, 420, 100, 30);
		// TODO Actions
		acaoEditar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				habilitaEdicao();
				funcaoSobrescrever();
				funcaoCancelar();
			}
		};
		btnEditar.addActionListener(acaoEditar);

		btnNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				habilitaNovo();
				FrameInicial.pesquisaProdutoAdicaoItem();
				funcaoSalvar();
			}
		});
		btnAnterior.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!listavazia()) {
					if (controledaLista.getCurrentPosition() == 0) {
						controledaLista.setCurrentPosition(tam);
						pedi = controledaLista.getAt(tam);
						contPedi.posicionarTabela(
								controledaLista.getCurrentPosition());
					} else {
						pedi = controledaLista.previous();
						contPedi.posicionarTabela(
								controledaLista.getCurrentPosition());
					}
					carregarCampos(pedi);
					// FrameECF.atualizaTela(pedi);
				}
			}
		});
		btnProximo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!listavazia()) {
					if (controledaLista.getCurrentPosition() < tam) {
						pedi = controledaLista.next();
						contPedi.posicionarTabela(
								controledaLista.getCurrentPosition());
					} else {
						controledaLista.setCurrentPosition(0);
						pedi = controledaLista.getAt(0);
						contPedi.posicionarTabela(
								controledaLista.getCurrentPosition());
					}
					carregarCampos(pedi);
				}
			}

		});
		btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Exclui o pedido carregado na tela
				leCampos();
				apagarPedido(pedi);
				JOptionPane.showMessageDialog(null, "Pedido excluido!!");
				btnCancelar.doClick();
			}
		});
		// TODO Configuração do Painel principal
		funcaoCancelar();
		painelPrincipal.setBackground(Color.WHITE);
		painelPrincipal.setBorder(BorderFactory.createEtchedBorder());
		painelPrincipal.setLayout(null);
		painelPrincipal.setSize(451, 511);
		painelPrincipal.setLayout(null);
		painelPrincipal.add(lblTituloTela);
		painelPrincipal.add(lblCodigopedi);
		painelPrincipal.add(txtFCodigoPedi);
		painelPrincipal.add(lblCondPag);
		painelPrincipal.add(txtfCondPag);
		painelPrincipal.add(lblUsuario);
		painelPrincipal.add(txtfUsuario);
		painelPrincipal.add(lblCliente);
		painelPrincipal.add(txtfCliente);
		painelPrincipal.add(lblQuantItens);
		painelPrincipal.add(txtFQuantItens);
		painelPrincipal.add(lblData);
		painelPrincipal.add(lblPrecoPedido);
		painelPrincipal.add(txtFPrecopedi);
		painelPrincipal.add(btnAnterior);
		painelPrincipal.add(btnNovo);
		painelPrincipal.add(btnProximo);
		painelPrincipal.add(btnEditar);
		painelPrincipal.add(btnSalvar);
		painelPrincipal.add(btnExcluir);
		painelPrincipal.add(btnCancelar);
		painelPrincipal.add(tabVisualiza);
		desHabilitaEdicao();
		listpedi = contPedi.listaPedidosTipo(nome);
		tam = listpedi.size();
		tam--;
		if (tam < 0) {
			FrameInicial.setPainelVisualiza(null);
			FrameInicial.setTabela(null);
			FrameInicial.atualizaTela();
		} else {
			controledaLista = new ControlaListaPedidos(listpedi);
			pedi = controledaLista.first();
			carregarCampos(pedi);
		}
		add(painelPrincipal);
	}

	// TODO Fim do 2 construtor / Salvar
	void funcaoSalvar() {
		limparBtnSalvar();
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtfCondPag.getText().equals("")
						& !txtfUsuario.equals("")) {
					pedi = new Pedido();
					try {
						pedi = leCampos();
						daoPedi.alterar(pedi);
						limparCampos();
						FrameInicial.setTabela(contPedi
								.tblPedidosNomeTipo(pedi.getCodiPedi(), ""));
						FrameInicial.setPainelVisualiza(
								new PainelPedidos(pedi.getCodiPedi()));
						FrameInicial.atualizaTela();
						desHabilitaEdicao();
						JOptionPane.showMessageDialog(null, "Feito!");
						FrameInicial.getContPedi()
								.iniciar(pedi.getTipoPedido());
						btnNovo.grabFocus();
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"Problemas: Codigo Nulo ou Duplicado",
								"Erro ao Salvar", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Favor verificar os campos informados. ",
							"Não foi possivel gravar!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	// TODO Sobrescrever
	void funcaoSobrescrever() {
		limparBtnSalvar();
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtfCondPag.getText().equals("")
						& !txtfUsuario.equals("")) {
					pedi = leCampos();
					try {
						daoPedi.alterar(pedi);
						limparCampos();
						desHabilitaEdicao();
						JOptionPane.showMessageDialog(null, "Pedido Alterado!");
						FrameInicial.setPainelVisualiza(
								new PainelPedidos(pedi.getxNome()));
						FrameInicial.setTabela(contPedi.tblPedidosNomeTipo(
								pedi.getCodiPedi(), pedi.getTipoPedido()));
						FrameInicial.atualizaTela();
						pedi = new Pedido();
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"Erro ao gravar\n" + e2.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Favor verificar os campos informados. ",
							"Não foi possivel alterar o Pedido!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void apagarPedido(Pedido pedi) {
		daoPedi.remover(pedi);
	}

	// TODO Limpar o botão salvar
	private void limparBtnSalvar() {
		for (ActionListener act : btnSalvar.getActionListeners()) {
			btnSalvar.removeActionListener(act);
		}
	}

	// TODO Limpar Botão Cancelar
	private void limparBtnCancelar() {
		for (ActionListener act : btnCancelar.getActionListeners()) {
			btnCancelar.removeActionListener(act);
		}
	}

	// TODO Vai para uma posição específica
	public static void irParaPoicao(int posicao) {
		controledaLista.setCurrentPosition(posicao);
		pedi = controledaLista.getAt(posicao);
		carregarCampos(pedi);
	}

	void funcaoCancelarNovo() {
		limparBtnCancelar();
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				desHabilitaEdicao();
				leCampos();
				apagarPedido(pedi); // Apaga o pedido reservado
				FrameInicial.getContPedi().iniciar(pedi.getTipoPedido());
				atualizaTabela();
				scrProdutos.setViewportView(tabelaItensPedido);
				carregarCampos(pedi);
				funcaoCancelar();

			}
		});
	}

	void funcaoCancelar() {
		limparBtnCancelar();
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Cancelar
				desHabilitaEdicao();
				FrameInicial.getContPedi().iniciar(pedi.getTipoPedido());
				atualizaTabela();
				scrProdutos.setViewportView(tabelaItensPedido);
				carregarCampos(pedi);
			}
		});
	}

	private boolean listavazia() {
		if (tam == -1) {
			return true;
		}
		return false;
	}

	// TODO Remover um ítem do produto

	// TODO Adicionar ítem ao pedido
	public static List<Servico> adicionaItem(Servico servico) {
		return null;

	}

	// TODO Atualizar a tabela de itens do pedido
	public static JTable atualizaTabela() {
		tabelaItensPedido = new JTable();
		modeloTabela = new DefaultTableModel();
		modeloTabela = (DefaultTableModel) tabelaItensPedido.getModel();
		Object colunas[] = {"Código", "Nome", "Quantidade", "Preço Unit.",
				"Total ítem"};
		modeloTabela.setColumnIdentifiers(colunas);
		tabelaItensPedido = new JTable(modeloTabela);
		// for (int i = 0; i < arrayItemProd.size(); i++) {
		// Object linha[] = { arrayItemProd.get(i).getCodi_prod_1(),
		// arrayItemProd.get(i).getNome_prod(), quant,
		// arrayItemProd.get(i).getPrec_prod_1(),
		// arrayItemProd.get(i).getPrec_prod_1() * quant };
		// modeloTabela.addRow(linha);
		// }
		scrProdutos.setViewportView(tabelaItensPedido);
		return tabelaItensPedido;
	}

	// TODO Habilita novo
	public void habilitaNovo() {
		limparCampos();
		atualizaTabela();
		String codipedido = criaCodiPedi();
		try {
			daoPedi.reservaCodigo(codipedido);
		} catch (SQLException e) {
			// TODO Erro ao conectar banco.
			JOptionPane.showMessageDialog(null,
					"Erro ao reservar código para o pedido/n" + e.getMessage());
			e.printStackTrace();
		}

		txtFCodigoPedi.setText(codipedido);
		txtFCodigoPedi.setEditable(false);
		txtfCondPag.setEditable(true);
		txtfCondPag.setFocusable(true);
		txtfUsuario.setEditable(true);
		txtFQuantItens.setEditable(true);
		txtfCliente.setEditable(true);
		txtfCliente.setFocusable(true);
		txtAreaObsPedido.setEditable(true);
		txtAreaObsPedido.setEnabled(true);

		btnNovo.setEnabled(false);
		btnProximo.setEnabled(false);
		btnAnterior.setEnabled(false);
		btnEditar.setEnabled(false);
		btnSalvar.setEnabled(true);
		btnExcluir.setEnabled(false);
		funcaoCancelarNovo();
	}

	// TODO Habilita edição
	public void habilitaEdicao() {
		txtFCodigoPedi.setEditable(false);
		txtfCondPag.setEditable(true);
		txtfCondPag.setFocusable(true);
		txtfUsuario.setEditable(true);
		txtFQuantItens.setEditable(true);
		txtfCliente.setEditable(true);
		txtfCliente.setFocusable(true);
		txtFPrecopedi.setEditable(true);
		txtAreaObsPedido.setEditable(true);
		txtAreaObsPedido.setEnabled(true);

		btnNovo.setEnabled(false);
		btnProximo.setEnabled(false);
		btnAnterior.setEnabled(false);
		btnEditar.setEnabled(false);
		btnSalvar.setEnabled(true);
		btnExcluir.setEnabled(true);

	}

	// TODO desabilita edição
	public void desHabilitaEdicao() {
		txtFCodigoPedi.setEditable(false);
		txtfCondPag.setEditable(false);
		txtfCondPag.setFocusable(false);
		txtfUsuario.setEditable(false);
		txtFQuantItens.setEditable(false);
		txtfCliente.setEditable(false);
		txtfCliente.setFocusable(false);
		txtFPrecopedi.setEditable(false);
		txtFPrecopedi.setText(null);
		txtAreaObsPedido.setEditable(false);
		txtAreaObsPedido.setEnabled(false);

		btnNovo.setEnabled(true);
		btnProximo.setEnabled(true);
		btnAnterior.setEnabled(true);
		btnEditar.setEnabled(true);
		btnSalvar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}

	// TODO Limpa Campos
	public void limparCampos() {
		txtFCodigoPedi.setText(null);
		txtfCondPag.setText(null);
		txtfUsuario.setText(null);
		txtFQuantItens.setText(null);
		txtfCliente.setText(null);
		txtFPrecopedi.setText(null);
		txtFQuantItens.setText(null);
		txtAreaObsPedido.setText(null);
		total = 0;
		quant = 0;
		quantTotItens = 0;
		setTabelaItensPedido(null);
		// atualizaTabela();
	}

	// TODO Carregar a tela com um pedido
	public static void carregarCampos(Pedido pedi) {
		if (!pedi.equals(null)) {
			txtFCodigoPedi.setText(pedi.getCodiPedi());
			txtFQuantItens.setText(String.valueOf(pedi.getQuantItens()));
			txtFPrecopedi.setText(String.valueOf(pedi.getTotalPedi()));
			txtfCliente.setText(pedi.getxNome());
			txtfCondPag.setText(pedi.getCodiCondPag());
			atualizaTabela();
		}
	}

	// TODO Ler os campos e retornar um pedido
	public Pedido leCampos() {
		pedi = new Pedido();
		pedi.setCodiPedi(txtFCodigoPedi.getText());
		if (!txtfCondPag.getText().equals("") & !txtfCondPag.equals(null)) {
			pedi.setCodiCondPag(txtfCondPag.getText());
		}
		if (!txtfCliente.getText().equals("") & !txtfCliente.equals(null)) {
			pedi.setxNome(txtfCliente.getText());
		}
		if (!txtFQuantItens.getText().equals(null)
				& !txtFQuantItens.getText().equals("")) {
			pedi.setQuantItens(Integer.parseInt(txtFQuantItens.getText()));
		}
		if (!txtFPrecopedi.getText().equals(null)
				& !txtFPrecopedi.getText().equals("")) {
			pedi.setTotalPedi(new BigDecimal(txtFPrecopedi.getText()));
		}
		pedi.setCodiCondPag(txtfCondPag.getText());
		// ItensProduto[] arrteste = pedi.getItensProdutoArray(arrayItemProd);
		// System.out.println("Tamanho " + arrteste.length);
		// System.out.println(arrteste[0].getNome_prod());
		return pedi;
	}

	// TODO Adicionar um cliente
	public static void adicionaUsuario(Pessoa usua) {
		txtfCliente.setText(usua.getNome());
	}

	// TODO Adicionar uma condição de pagamento
	public static void adicionaCondPag(CondPagamento condPagamento) {
		txtfCondPag.setText(condPagamento.getCodiCondPag());
	}

	// TODO public static cancelar
	public static void cancelar() {
		btnCancelar.doClick();
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

	public static JButton getBtnNovo() {
		return btnNovo;
	}

	public static void setBtnNovo(JButton btnNovo) {
		PainelOrdServicos.btnNovo = btnNovo;
	}

	public static JButton getBtnEditar() {
		return btnEditar;
	}

	// TODO Setar o codigo do pedido
	public String criaCodiPedi() {
		Calendar c = Calendar.getInstance();
		daoPedi.consultaUltimo();
		String codiPedi = String.valueOf(daoPedi.consultaUltimo())
				+ String.valueOf(c.get(Calendar.YEAR))
				+ String.valueOf(c.get(Calendar.MONTH))
				+ String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		return codiPedi;
	}

	public static void setBtnEditar(JButton btnEditar) {
		PainelOrdServicos.btnEditar = btnEditar;
	}

	public static JTextField getTxtFNomepedi() {
		return txtfCondPag;
	}

	public static void setTxtFNomepedi(JTextField txtFNomepedi) {
		PainelOrdServicos.txtfCondPag = txtFNomepedi;
	}

	public static JScrollPane getScrPedido() {
		return scrProdutos;
	}

	public static void setScrPedido(JScrollPane scrPedido) {
		PainelOrdServicos.scrProdutos = scrPedido;
	}

	public static JTable getTabelaItensPedido() {
		return tabelaItensPedido;
	}

	public static void setTabelaItensPedido(JTable tabelaItensPedido) {
		PainelOrdServicos.tabelaItensPedido = tabelaItensPedido;
	}

	public JLabel getLblQuantItens() {
		return lblQuantItens;
	}

	public void setLblQuantItens(JLabel lblQuantItens) {
		this.lblQuantItens = lblQuantItens;
	}

	public static JTextField getTxtfCondPag() {
		return txtfCondPag;
	}

	public static void setTxtfCondPag(JTextField txtfCondPag) {
		PainelOrdServicos.txtfCondPag = txtfCondPag;
	}

	public static JTextField getTxtfCliente() {
		return txtfCliente;
	}

	public static void setTxtfCliente(JTextField txtfCliente) {
		PainelOrdServicos.txtfCliente = txtfCliente;
	}
}
