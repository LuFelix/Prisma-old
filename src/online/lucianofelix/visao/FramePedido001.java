package online.lucianofelix.visao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import online.lucianofelix.beans.Produto;
import online.lucianofelix.controle.ControlaProduto;
import online.lucianofelix.controle.ControlaVendaACBR;
import online.lucianofelix.util.LeArquivoTxt;
import online.lucianofelix.util.ManipulaArquivoTxt;

public class FramePedido001 extends JPanel {

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ Cria��o das
	// Variaveis ++++++++++++++++++++++++++++++++++++++++++
	private JPanel painelPrincipal;
	private JTextArea textArea;

	// Objetos de Controle
	private ControlaProduto controlaProduto;
	private ControlaVendaACBR vendaACBR;

	// Botoes
	private JButton btnMostrar;
	private JButton btnAbrir;
	private JButton btnAbreCupom;
	private JButton btnLimpar;
	private JButton btnGravar;
	private JButton btnVendeItem;
	private JButton btnProdutos;
	private JButton btnCancelaItem;
	private JButton btnCancelaCupom;
	private JButton btnFechaCupom;
	private JScrollPane scrAreaTexto;

	// Labels e Text Fields
	private JLabel lblCodigo;
	private JLabel lblProduto;
	private JLabel lblQuantidade;
	private JLabel lblPreco;
	private JLabel lblDescProd;
	private JLabel lblDesc;

	private static JTextField txtFCodigoProd;
	private static JTextField txtFNomeProd;
	private static JTextField txtFDescProd;
	private static JTextField txtFPreco;
	private JTextField txtFDesc;
	private JTextField txtFQuantidadeVenda;

	// Beans
	private Produto prod;

	/**
	 * Cria��o do Frame
	 */
	// TODO +++++Construtor da
	// classe++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	public FramePedido001() {
		UIManager.put("TextField.font",
				new Font("Times New Roman", Font.BOLD, 14));
		UIManager.put("Label.font", new Font("Times New Roman", Font.BOLD, 14));
		UIManager.put("Button.font",
				new Font("Times New Roman", Font.BOLD, 14));

		// setTitle("SIAC ECF");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(300, 100, 610, 710);
		setLayout(null);
		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(null);
		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelPrincipal.setSize(610, 710);

		controlaProduto = new ControlaProduto();

		// TODO +++++ Configura��o das Posi��es dos bot�es

		btnProdutos = new JButton("Produtos"); // Mesma posi��o do botao Abrir
		btnProdutos.setBounds(10, 10, 130, 25);

		btnAbrir = new JButton("Abrir"); // Mesma posi��o com Botao Produtos
		btnAbrir.setBounds(10, 10, 130, 25);

		btnMostrar = new JButton("Mostrar");
		btnMostrar.setBounds(10, 40, 130, 25);// Mesma posi��o do bot�o Abrir
												// COO

		btnAbreCupom = new JButton("Abrir COO");
		btnAbreCupom.setBounds(10, 40, 130, 25);// Mesma posi��o do bot�o
												// Mostrar

		btnVendeItem = new JButton("Venda");
		btnVendeItem.setBounds(10, 70, 130, 25);

		btnCancelaCupom = new JButton("Cancelar COO");
		btnCancelaCupom.setBounds(10, 160, 130, 25);

		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(10, 100, 130, 25);

		btnFechaCupom = new JButton("Fecha COO");
		btnFechaCupom.setBounds(10, 100, 130, 25);

		btnCancelaItem = new JButton("Cancela item");
		btnCancelaItem.setBounds(10, 130, 130, 25);// Mesma posi��o Botao Gravar

		btnGravar = new JButton("Gravar");
		btnGravar.setBounds(10, 130, 130, 25); // Mesma posi��o do bot�o Cancela
												// Item

		// TODO +++++ Ações dos botões ++++++++++++++++++++++

		// +++++Produtos+++++++++++++++++++

		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FrameProdutos();

			}
		});

		// +++++ Açãoo Botão Abrir++++++++++++++++++++++++++++

		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LeArquivoTxt(textArea);

			}
		});

		// +++++ Ação Botão Mostrar++++++++++++++++++++++++

		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		// +++++ Ação Botão Abrir COO +++++++++++++++++++++++++++++++++
		btnAbreCupom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vendaACBR = new ControlaVendaACBR();
				try {
					vendaACBR.ecfAbreCupom();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// +++++ Ação Botão Vende Item+++++++++++++++++++++++++
		btnVendeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				leCampos();
				vendaACBR = new ControlaVendaACBR();
				try {
					vendaACBR.ecfVendeItem(prod);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// +++++ Ação Botão Cancelar COO++++++++++++++++++++++++++++++++++
		btnCancelaCupom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vendaACBR = new ControlaVendaACBR();
				try {
					vendaACBR.ecfCancelarCupom();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// +++++ Ação Botão Limpar+++++++++++++++++++
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);

			}
		});
		// +++++ Ação Botão Fechar Cupom+++++++++++++
		btnFechaCupom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vendaACBR = new ControlaVendaACBR();
				try {
					vendaACBR.ecfSubtotalizaCupom(null);
					vendaACBR.ecfEfetuaPagamento("01",
							Float.parseFloat("200.00"), "Observar", false);
					vendaACBR.ecfFecharCupom("Obrigado Por Usar LULU ECF");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// +++++ Ação Botão Cancelar Item++++++++++++++++
		btnCancelaItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vendaACBR = new ControlaVendaACBR();
				String numItem = prod.getCodi_prod_1();
				try {
					vendaACBR.ecfCancelaItemVendido(Integer.parseInt(numItem));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		// +++++ Ação Botão Gravar+++++++++++++++++++++++++++
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManipulaArquivoTxt manTxt = new ManipulaArquivoTxt();
				File arquivo = manTxt.montaArquivotxt(textArea);
				try {
					manTxt.gravaArquivo(arquivo);
				} catch (Exception e) {

					e.printStackTrace();
				}

			}
		});

		// TODO ++++++ Configuração dos Labels e Text Fields

		lblCodigo = new JLabel("C�digo:");
		lblCodigo.setBounds(180, 10, 70, 30);
		txtFCodigoProd = new JTextField();
		txtFCodigoProd.setBounds(230, 10, 70, 30);

		lblProduto = new JLabel("Produto:");
		lblProduto.setBounds(305, 10, 70, 25);
		txtFNomeProd = new JTextField();
		txtFNomeProd.setBounds(360, 10, 232, 30);

		lblDescProd = new JLabel("Descri��o: ");
		lblDescProd.setBounds(180, 50, 70, 30);
		txtFDescProd = new JTextField();
		txtFDescProd.setBounds(245, 50, 347, 30);

		lblQuantidade = new JLabel("Quant.:");
		lblQuantidade.setBounds(180, 90, 70, 30);
		txtFQuantidadeVenda = new JTextField();
		txtFQuantidadeVenda.setBounds(230, 90, 50, 30);

		lblPreco = new JLabel("Pre�o:");
		lblPreco.setBounds(285, 90, 70, 30);
		txtFPreco = new JTextField();
		txtFPreco.setBounds(325, 90, 100, 30);

		lblDesc = new JLabel("Desconto:");
		lblDesc.setBounds(435, 90, 70, 30);
		txtFDesc = new JTextField();
		txtFDesc.setBounds(505, 90, 87, 30);

		// Ações Labels e Text Fields

		txtFCodigoProd.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				carregarCampos(prod);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				carregarCampos(prod);
			}
		});

		// +++++ Configuração da área de Texto++++++++++++

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrAreaTexto = new JScrollPane(textArea);
		scrAreaTexto.setBounds(180, 130, 412, 540);
		scrAreaTexto.setAutoscrolls(true);

		// TODO ++++++ Configuraçãoo do Painel +++++++++++++

		// Labels

		painelPrincipal.add(lblCodigo);
		painelPrincipal.add(lblProduto);
		painelPrincipal.add(lblQuantidade);
		painelPrincipal.add(lblDescProd);
		painelPrincipal.add(lblPreco);

		// Text Fields

		painelPrincipal.add(txtFCodigoProd);
		painelPrincipal.add(txtFNomeProd);
		painelPrincipal.add(txtFQuantidadeVenda);
		painelPrincipal.add(txtFDescProd);
		painelPrincipal.add(txtFPreco);
		// Area de Texto

		painelPrincipal.add(scrAreaTexto);

		// Botoes
		painelPrincipal.add(btnProdutos);
		// painelPrincipal.add(btnAbrir);
		painelPrincipal.add(btnAbreCupom);
		// painelPrincipal.add(btnMostrar);
		// painelPrincipal.add(btnLimpar);
		painelPrincipal.add(btnFechaCupom);
		// painelPrincipal.add(btnGravar);
		painelPrincipal.add(lblDesc);
		painelPrincipal.add(txtFDesc);
		painelPrincipal.add(btnCancelaCupom);
		painelPrincipal.add(btnCancelaItem);
		painelPrincipal.add(btnVendeItem);
		add(painelPrincipal);
		// setVisible(true);

	}

	public static void atualizaTela(Produto prod) {
		carregarCampos(prod);

	}

	public void leCampos() {
		prod = new Produto();
		prod.setCodi_prod_1(txtFCodigoProd.getText());
		prod.setNome_prod(txtFNomeProd.getText());
		prod.setDesc_prod(txtFDescProd.getText());
		if (txtFQuantidadeVenda.getText().equals("")) {
			txtFQuantidadeVenda.setText("1");
		}
		// prod.setEstoque(Integer.parseInt(txtFQuantidadeVenda.getText()));
		if (txtFPreco.getText().equals("")) {
			txtFPreco.setText("0.1");
		}
		prod.setPrec_prod_1(Float.parseFloat(txtFPreco.getText()));
	}

	public static void carregarCampos(Produto prod) {
		txtFCodigoProd.setText(prod.getCodi_prod_1());
		txtFNomeProd.setText(prod.getNome_prod());
		txtFDescProd.setText(prod.getDesc_prod());
		txtFPreco.setText(String.valueOf(prod.getPrec_prod_1()));

	}

	public void habilitaEdicao() {

		txtFCodigoProd.setEnabled(true);
		txtFNomeProd.setEnabled(true);
		txtFDescProd.setEnabled(true);
		txtFQuantidadeVenda.setEnabled(true);
	}

	public void desHabilitaEdicao() {

		txtFCodigoProd.setEnabled(false);
		txtFNomeProd.setEnabled(false);
		txtFDescProd.setEnabled(false);
		txtFQuantidadeVenda.setEnabled(false);

	}

	// TODO ++++++Limpa Campos +++++++++++++++++++++++++++

	public void limparCampos() {

		txtFCodigoProd.setText(null);
		txtFNomeProd.setText(null);
		txtFDescProd.setText(null);
		txtFQuantidadeVenda.setText(null);

	}

}
