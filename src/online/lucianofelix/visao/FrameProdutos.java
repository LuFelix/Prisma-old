package online.lucianofelix.visao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import online.lucianofelix.beans.Produto;
import online.lucianofelix.controle.ControlaListaProdutos;
import online.lucianofelix.controle.ControlaProduto;
import online.lucianofelix.dao.DAOProdutoPrepSTM;

public class FrameProdutos {

	JFrame telaProduto;
	JPanel painelPrincipal;
	private JLabel lblTituloTela;
	// Labels e text fields
	private JLabel lblCodigoProd;
	private JLabel lblNomeProd;
	private JLabel lblDescricaoProd;
	private JLabel lblAliquotaICMSProd;
	private JLabel lblQuantProdEstoque;
	private JLabel lblPrecoProd;
	private JLabel lblPermiteEditar;
	private JTextField txtFCodigoProd;
	private JTextField txtFNomeProd;
	private JTextField txtFDescricaoProd;
	private JTextField txtFQuantProdEstoque;
	private JTextField txtFAliquotaICMSProd;
	private JTextField txtFPrecoProd;
	private JButton btnProximo;
	private JButton btnAnterior;
	private JButton btnEditar;
	private JButton btnSalvar;
	private JButton btnExcluir;
	private JButton btnCancelar;

	private JRadioButton jrbEditarSim;
	private JRadioButton jrbEditarNao;
	private ButtonGroup grpRadio;
	private int i;

	// objetos de controle
	private DAOProdutoPrepSTM daoProd;
	private ControlaListaProdutos controledaLista;
	private ControlaProduto cProd;
	private Produto prod;
	private List<Produto> listProd;
	int tam;

	public FrameProdutos() {
		// TODO +++++Construtor da Tela de
		// Produtos+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		UIManager.put("TextField.font", new Font("Times New Roman", Font.BOLD, 16));
		UIManager.put("Label.font", new Font("Times New Roman", Font.BOLD, 14));
		UIManager.put("Button.font", new Font("Times New Roman", Font.BOLD, 14));

		cProd = new ControlaProduto();
		prod = new Produto();

		telaProduto = new JFrame("Simpro - Cadastro de Produtos");
		telaProduto.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		telaProduto.setBounds(100, 100, 610, 710);
		painelPrincipal = new JPanel();
		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		telaProduto.setContentPane(painelPrincipal);

		// TODO +++++Configuração dos Labels e text
		// fields++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		lblTituloTela = new JLabel("PRODUTOS");
		lblTituloTela.setBounds(275, 10, 100, 80);

		lblCodigoProd = new JLabel("Código :");
		lblCodigoProd.setBounds(10, 60, 90, 25);
		txtFCodigoProd = new JTextField(0);
		txtFCodigoProd.setBounds(100, 60, 170, 25);

		lblNomeProd = new JLabel("Produto: ");
		lblNomeProd.setBounds(10, 90, 70, 25);
		txtFNomeProd = new JTextField();
		txtFNomeProd.setBounds(100, 90, 170, 25);

		lblDescricaoProd = new JLabel("Descrição: ");
		lblDescricaoProd.setBounds(10, 120, 90, 25);
		txtFDescricaoProd = new JTextField();
		txtFDescricaoProd.setBounds(100, 120, 170, 25);

		lblAliquotaICMSProd = new JLabel("Aliquota: ");
		lblAliquotaICMSProd.setBounds(10, 150, 90, 25);
		txtFAliquotaICMSProd = new JTextField();
		txtFAliquotaICMSProd.setBounds(100, 150, 170, 25);

		lblQuantProdEstoque = new JLabel("Qtd. Estoque: ");
		lblQuantProdEstoque.setBounds(10, 180, 90, 25);
		txtFQuantProdEstoque = new JTextField();
		txtFQuantProdEstoque.setBounds(100, 180, 170, 25);

		lblPrecoProd = new JLabel("Preço: ");
		lblPrecoProd.setBounds(10, 210, 90, 25);
		txtFPrecoProd = new JTextField();
		txtFPrecoProd.setBounds(100, 210, 170, 25);

		lblPermiteEditar = new JLabel("Permite Editar ");
		lblPermiteEditar.setBounds(105, 590, 100, 25);

		// TODO +++++Posicionamento dos Botões
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		// TODO +++JRadio Buttons+++++++++++++++++
		jrbEditarSim = new JRadioButton("Sim");
		jrbEditarSim.setBounds(95, 560, 50, 35);
		jrbEditarNao = new JRadioButton("Não");
		jrbEditarNao.setBounds(155, 560, 50, 35);
		grpRadio = new ButtonGroup();
		grpRadio.add(jrbEditarSim);
		grpRadio.add(jrbEditarNao);

		// TODO Ação Radio Buttons++++++++++++++++

		jrbEditarNao.setSelected(true);

		// TODO +++ Jbuttons+++++++++++++++++++++++

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(105, 560, 100, 25);
		btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(205, 560, 100, 25);
		btnProximo = new JButton("Próximo");
		btnProximo.setBounds(305, 560, 100, 25);
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(405, 560, 100, 25);
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(405, 590, 100, 25);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(105, 590, 100, 25);
		// TODO+++++ Ações dos
		// Botões+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		// TODO+++++ Botão Editar ++++++++++++++++++++++++
		btnEditar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				habilitaEdicao();
			}
		});

		// TODO+++++ Botão Anterior ++++++++++++++++++++++++
		btnAnterior.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Estava em: " + controledaLista.getCurrentPosition());
				if (controledaLista.getCurrentPosition() == 0) {
					controledaLista.setCurrentPosition(tam);
					prod = controledaLista.getAt(tam);
				} else {
					prod = controledaLista.previous();
				}
				System.out.println("Foi para: " + controledaLista.getCurrentPosition());
				carregarCampos(prod);
				FramePedido001.atualizaTela(prod);

			}
		});

		// TODO+++++ Botão Próximo ++++++++++++++++++++++++
		btnProximo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Estava em: " + controledaLista.getCurrentPosition());
				if (controledaLista.getCurrentPosition() < tam) {
					prod = controledaLista.next();
				} else {
					controledaLista.setCurrentPosition(0);
					prod = controledaLista.getAt(0);
				}
				System.out.println("Foi para: " + controledaLista.getCurrentPosition());
				carregarCampos(prod);
				FramePedido001.atualizaTela(prod);

			}
		});

		// TODO+++++ Botao Salvar +++++++++++++++++++++++++

		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				prod = leCampos();
				cProd.cadastrar(prod);
				desHabilitaEdicao();
			}
		});
		// TODO+++++ Botao Cancelar +++++++++++++++++++++++++
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				desHabilitaEdicao();
				carregarCampos(prod);
			}
		});
		// TODO +++++Ações de foco dos Text
		// Fields++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		// TODO +++++Ação de foco txtFCodigo++++++++++++++++++
		txtFCodigoProd.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				prod = new Produto();
				carregarCampos(prod);
				FramePedido001.atualizaTela(prod);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				prod = new Produto();
				carregarCampos(prod);
				FramePedido001.atualizaTela(prod);

			}
		});

		// TODO +++++Configuração do Painel
		// principal++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		desHabilitaEdicao();
		painelPrincipal.setLayout(null);
		painelPrincipal.add(lblTituloTela);
		painelPrincipal.add(lblCodigoProd);
		painelPrincipal.add(txtFCodigoProd);
		painelPrincipal.add(lblNomeProd);
		painelPrincipal.add(txtFNomeProd);
		painelPrincipal.add(lblDescricaoProd);
		painelPrincipal.add(txtFDescricaoProd);
		painelPrincipal.add(lblAliquotaICMSProd);
		painelPrincipal.add(txtFAliquotaICMSProd);
		painelPrincipal.add(lblQuantProdEstoque);
		painelPrincipal.add(txtFQuantProdEstoque);
		painelPrincipal.add(lblPrecoProd);
		painelPrincipal.add(txtFPrecoProd);
		// painelPrincipal.add(lblPermiteEditar);
		painelPrincipal.add(btnAnterior);
		painelPrincipal.add(btnProximo);
		painelPrincipal.add(btnEditar);
		painelPrincipal.add(btnSalvar);
		painelPrincipal.add(btnExcluir);
		painelPrincipal.add(btnCancelar);
		// painelPrincipal.add(jrbEditarSim);z\
		// painelPrincipal.add(jrbEditarNao);
		listProd = cProd.procurarTodos();
		tam = listProd.size();
		tam--;
		controledaLista = new ControlaListaProdutos(listProd);
		prod = controledaLista.first();
		carregarCampos(prod);
		// FrameECF.atualizaTela(prod);
		telaProduto.setVisible(true);

	} // +++++++Fim do
		// construtor+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	// TODO +++++ Habilita/Desabilita/Carrega/Le/Limpa os Campos
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	public Produto leCampos() {
		prod = new Produto();

		if (!txtFCodigoProd.getText().equals("")) {
			prod.setCodi_prod_1(txtFCodigoProd.getText());
		}
		prod.setNome_prod(txtFNomeProd.getText());
		prod.setDesc_prod(txtFDescricaoProd.getText());
		prod.setAliq_prod(txtFAliquotaICMSProd.getText());

		if (txtFPrecoProd.getText().equals("")) {
			prod.setPrec_prod_1(0);
		} else {
			prod.setPrec_prod_1(Float.parseFloat(txtFPrecoProd.getText()));
		}

		return prod;
	}

	public void carregarCampos(Produto prod) {
		txtFCodigoProd.setText(prod.getCodi_prod_1());
		txtFNomeProd.setText(prod.getNome_prod());
		txtFDescricaoProd.setText(prod.getDesc_prod());
		txtFAliquotaICMSProd.setText(prod.getAliq_prod());
		txtFPrecoProd.setText(String.valueOf(prod.getPrec_prod_1()));
	}

	public void habilitaEdicao() {
		txtFCodigoProd.setEditable(false);
		txtFNomeProd.setEditable(true);
		txtFDescricaoProd.setEditable(true);
		txtFQuantProdEstoque.setEditable(true);
		txtFAliquotaICMSProd.setEditable(true);
		txtFPrecoProd.setEditable(true);

		btnProximo.setEnabled(false);
		btnAnterior.setEnabled(false);
		btnEditar.setEnabled(false);
		btnSalvar.setEnabled(true);
		btnExcluir.setEnabled(true);

	}

	public void desHabilitaEdicao() {

		txtFCodigoProd.setEditable(false);
		txtFNomeProd.setEditable(false);
		txtFDescricaoProd.setEditable(false);
		txtFQuantProdEstoque.setEditable(false);
		txtFAliquotaICMSProd.setEditable(false);
		txtFPrecoProd.setEditable(false);

		btnProximo.setEnabled(true);
		btnAnterior.setEnabled(true);
		btnEditar.setEnabled(true);
		btnSalvar.setEnabled(false);
		btnExcluir.setEnabled(false);

	}

	// TODO ++++++Limpa Campos +++++++++++++++++++++++++++

	public void limparCampos() {

		txtFCodigoProd.setText(null);
		txtFNomeProd.setText(null);
		txtFDescricaoProd.setText(null);
		txtFQuantProdEstoque.setText(null);
		txtFAliquotaICMSProd.setText(null);
		txtFPrecoProd.setText(null);

	}

	/*
	 * private JRadioButton jrbEditarSim; private JRadioButton jrbEditarNao;
	 * private ButtonGroup grpRadio;
	 */

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

}