package online.lucianofelix.visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

import online.lucianofelix.beans.Produto;
import online.lucianofelix.controle.ControlaGrupoSubgrupo;
import online.lucianofelix.controle.ControlaListaProdutos;
import online.lucianofelix.controle.ControlaProduto;
import online.lucianofelix.controle.ControlaTabelaPreco;
import online.lucianofelix.dao.DAOProdutoPrepSTM;
import online.lucianofelix.dao.DAOTabelaPreco;

public class PainelProdutos extends JPanel {

	// JFrame telaProduto;

	private JSplitPane jspPrincipal;
	private JSplitPane sppSuperior;
	private JSplitPane sppImagem;
	private JPanel painelGrid;
	private JPanel painelMovimento;
	private JLabel lblTituloTela;
	// Labels e text fields

	private static JLabel lblImagem;
	private JLabel lbl01;
	private JLabel lbl02;
	private JLabel lbl03;
	private JLabel lbl04;
	private JLabel lbl05;
	private JLabel lbl06;
	private JLabel lbl07;
	private JLabel lbl08;
	private JLabel lbl09;
	private JLabel lbl10;

	private static JTextField txtF01;
	private static JTextField txtF02;
	private static JTextField txtF03;
	private static JTextField txtF04;
	private static JTextField txtF05;
	private static JTextField txtF06;
	private static JTextField txtF07;
	private static JTextField txtF08;
	private static JTextField txtF09;
	private static JTextField txtF10;

	private static JCheckBox chkBListaPrecos;

	private static JComboBox<String> cmbTabPreco;
	private static JComboBox<String> cmbGrupo;
	private static JComboBox<String> cmbSubGrupo;

	private JButton btnProximo;
	private JButton btnAnterior;
	private static JButton btnEditarPreco;
	private static JButton btnNovo;
	private static JButton btnEditar;
	private static JButton btnCancelar;

	// Tabela de preços do produto

	private JTabbedPane tabVisualiza;
	private static JTable tabelaPrecos;
	private static JTable tabelaMovEstoque;
	private static DefaultTableModel modeloTabela;
	private static JScrollPane scrPrecos;
	private static JScrollPane scrEstoque;
	private static JScrollPane scrImagem;
	private static JScrollPane scrImagensProd;
	private static JScrollPane scrDetalhes;
	private static JTextArea txtADetalhes;
	// objetos de controle

	private static ControlaListaProdutos controledaLista;
	private static ControlaProduto contProd;
	private ControlaTabelaPreco contTabPreco;
	private ControlaGrupoSubgrupo contGrupo;
	private static Produto prod;
	private DAOProdutoPrepSTM daoP;
	private List<Produto> listProd;
	int tam;
	private DAOTabelaPreco daoTabPreco;
	private JPanel pnlImagensProd;

	// TODO Construtor
	public PainelProdutos(Produto p) {

		UIManager.put("TextField.font",
				new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Label.font", new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Button.font",
				new Font("Times New Roman", Font.BOLD, 12));
		// Controle
		prod = p;
		contProd = new ControlaProduto();
		contGrupo = new ControlaGrupoSubgrupo();
		contTabPreco = new ControlaTabelaPreco();
		// Dados
		daoP = new DAOProdutoPrepSTM();
		daoTabPreco = new DAOTabelaPreco();
		// telaProduto.setContentPane(painelPrincipal);

		// TODO Configuração dos Labels e text fields

		lblTituloTela = new JLabel("   Produto");
		lblTituloTela.setBounds(10, 0, 150, 40);
		lblTituloTela.setFont(new Font("Times New Roman", Font.BOLD, 32));

		lbl02 = new JLabel("Sequencia:");
		txtF02 = new JTextField();
		lbl03 = new JLabel("Código Interno:");
		txtF03 = new JTextField(0);
		lbl04 = new JLabel("Código Fábrica/EAN:");
		txtF04 = new JTextField(0);
		lbl05 = new JLabel("Produto:");
		txtF05 = new JTextField();
		lbl06 = new JLabel("Alíquota ICMS:");
		txtF06 = new JTextField();
		lbl08 = new JLabel("Estoque:");
		txtF08 = new JTextField();
		lbl09 = new JLabel("Preço Atual:");
		txtF09 = new JTextField();

		btnEditarPreco = new JButton("Alterar Preço");

		cmbGrupo = contGrupo.carregarRaizes();
		cmbSubGrupo = contGrupo.carregarSubGrupos();

		cmbTabPreco = new JComboBox<String>();
		cmbTabPreco.addItem("Tabela de Preços");
		for (int i = 0; i < daoTabPreco.pesquisaString("").size(); i++) {
			cmbTabPreco.addItem(
					daoTabPreco.pesquisaString("").get(i).getDescTabela());
		}
		chkBListaPrecos = new JCheckBox("Exibir");
		chkBListaPrecos.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (chkBListaPrecos.isSelected()) {
					habilitaTabelaPrecos(prod);
				} else {
					desabilitaTabelaPrecos();
				}
			}
		});

		// Tabela de Visualiza

		tabelaPrecos = new JTable();
		tabelaMovEstoque = new JTable();

		scrPrecos = new JScrollPane();
		scrPrecos.setViewportView(tabelaPrecos);
		tabelaPrecos.getParent().setBackground(Color.WHITE);

		lblImagem = new JLabel("Image not Found");

		scrImagem = new JScrollPane(lblImagem);
		scrImagem.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrImagem.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnlImagensProd = new JPanel();
		scrImagensProd = new JScrollPane(pnlImagensProd);
		txtADetalhes = new JTextArea();
		txtADetalhes.setLineWrap(true);
		txtADetalhes.setWrapStyleWord(true);
		scrDetalhes = new JScrollPane(txtADetalhes);
		tabVisualiza = new JTabbedPane();

		tabVisualiza.addTab("Detalhes", scrDetalhes);
		tabVisualiza.addTab("Imagens", scrImagensProd);
		tabVisualiza.addTab("Histórico de Preços", scrPrecos);
		tabVisualiza.add("Estoque", scrEstoque);

		// Ações
		btnEditarPreco.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contProd.alteraPreco(lerCampos());
			}
		});

		// TODO Painel principal
		painelGrid = new JPanel();
		painelGrid.setBorder(BorderFactory.createEtchedBorder());
		painelGrid.setLayout(new GridLayout(9, 2));
		painelGrid.setBackground(Color.WHITE);
		painelGrid.add(lbl02);
		painelGrid.add(txtF02);
		painelGrid.add(lbl03);
		painelGrid.add(txtF03);
		painelGrid.add(lbl04);
		painelGrid.add(txtF04);
		painelGrid.add(lbl05);
		painelGrid.add(txtF05);
		painelGrid.add(cmbGrupo);
		painelGrid.add(cmbSubGrupo);
		painelGrid.add(lbl06);
		painelGrid.add(txtF06);
		painelGrid.add(lbl08);
		painelGrid.add(txtF08);
		painelGrid.add(lbl09);
		painelGrid.add(txtF09);
		painelGrid.add(cmbTabPreco);
		painelGrid.add(btnEditarPreco);

		sppImagem = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		sppImagem.add(lblTituloTela);
		sppImagem.add(scrImagem);
		sppImagem.setDividerLocation(50);
		sppImagem.setEnabled(false);
		sppImagem.setBackground(Color.WHITE);
		sppImagem.setForeground(Color.WHITE);
		sppImagem.setDividerSize(3);

		sppSuperior = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		sppSuperior.setDividerLocation(200);
		sppSuperior.setDividerSize(3);
		sppSuperior.setEnabled(false);
		sppSuperior.add(sppImagem);
		sppSuperior.add(painelGrid);

		painelMovimento = new JPanel();
		painelMovimento.setBorder(BorderFactory.createEtchedBorder());
		painelMovimento.setLayout(new GridLayout());
		painelMovimento.setBackground(Color.WHITE);
		painelMovimento.add(tabVisualiza);

		desHabilitaEdicao();

		contProd.carregaDetalhes(prod);
		contProd.carregarCotacoes(prod);
		jspPrincipal = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		jspPrincipal.setDividerSize(3);
		jspPrincipal.setDividerLocation(250);
		jspPrincipal.setEnabled(false);
		jspPrincipal.setBackground(Color.WHITE);
		jspPrincipal.add(sppSuperior);
		jspPrincipal.add(painelMovimento);
		setLayout(new GridLayout());
		setBackground(Color.WHITE);
		add(jspPrincipal);
	}

	// TODO Fim contrutor inicio Habilita/Desab./Carrega/Le/Limpa Campos

	public static void desabilitaTabelaPrecos() {
		setTabelaPrecos(null);
		getScrPrecos().setViewportView(tabelaPrecos);
	}

	// TODO Habilitar histórico de preços
	public static JTable habilitaTabelaPrecos(Produto prod) {
		tabelaPrecos = new JTable();
		modeloTabela = new DefaultTableModel();
		modeloTabela = (DefaultTableModel) tabelaPrecos.getModel();
		contProd.carregarCotacoes(prod);
		Object colunas[] = {"Nome", "Preço Unitário", "Data"};
		modeloTabela.setColumnIdentifiers(colunas);
		tabelaPrecos.setShowGrid(true);

		tabelaPrecos.setModel(modeloTabela);
		for (int i = 0; i < prod.getListCotacaoProduto().size(); i++) {
			Object linha[] = {prod.getNome_prod(),
					prod.getListCotacaoProduto().get(i).getValor(),
					prod.getListCotacaoProduto().get(i).getDataHoraMarcacao()};
			modeloTabela.addRow(linha);
		}

		scrPrecos.setViewportView(tabelaPrecos);
		return tabelaPrecos;
	}

	// TODO Ler Campos.

	public static Produto lerCampos() {
		prod = new Produto();
		if (txtF03.getText().equals("") || txtF03.getText().equals(null)) {
			prod.setCodi_prod_1(contProd.criaCodiProd());
		} else {
			prod.setCodi_prod_1(txtF03.getText());
		}
		if (!txtF05.getText().equals(null) & !txtF05.getText().equals("")) {
			prod.setNome_prod(txtF05.getText());
		} else {
			JOptionPane.showMessageDialog(null,
					"Problemas: Verifique as informações preenchidas",
					"Erro ao Salvar. Campos com * são necessários",
					JOptionPane.ERROR_MESSAGE);
		}
		if (!txtF06.getText().equals(null) & !txtF06.getText().equals("")) {
			prod.setAliq_prod(txtF06.getText());
		} else {
			JOptionPane.showMessageDialog(null,
					"Problemas: Verifique as informações preenchidas",
					"Erro ao Salvar. Campos com * são necessários",
					JOptionPane.ERROR_MESSAGE);
		}
		prod.setAliq_prod(txtF06.getText());
		if (txtF09.getText().equals("")) {
			prod.setPrec_prod_1(0);
		} else {
			prod.setPrec_prod_1(Float.parseFloat(txtF09.getText()));
		}
		return prod;
	}

	public static void carregarImagem(String codiProd) {
		lblImagem = new JLabel(new ImageIcon(
				"C:\\SIMPRO\\img\\listas\\prods\\" + codiProd + ".jpg "));
		scrImagem.setViewportView(lblImagem);
	}

	// TODO Carregar campos
	public static void carregarCampos(Produto prod) {
		if (!prod.equals(null)) {
			chkBListaPrecos.setSelected(false);
			txtF02.setText(String.valueOf(prod.getSeq_produto()));
			txtF03.setText(String.valueOf(prod.getCodi_prod_1()));
			txtF05.setText(prod.getNome_prod());
			txtF06.setText(prod.getAliq_prod());
			txtF08.setText(String.valueOf(prod.getEstoqueAtual()));
			txtF09.setText(String
					.valueOf(prod.getListCotacaoProduto().get(0).getValor()));

			txtADetalhes.setText(prod.getDesc_prod());
			habilitaTabelaPrecos(prod);
			carregarImagem(prod.getCodi_prod_1());

		}

	}

	// TODO Habilitar Edição
	public static void habilitaEdicao() {
		txtF03.setEditable(false);
		txtF05.setEditable(true);
		txtF06.setEditable(true);
		txtF08.setEditable(true);
		txtF09.setEditable(false);
		cmbGrupo.setEnabled(true);
		cmbSubGrupo.setEnabled(true);

		btnEditarPreco.setEnabled(true);
		cmbTabPreco.setEnabled(true);
	}

	// TODO Habilita novo
	public static void habilitaNovo() {
		limparCampos();
		txtF02.setEditable(false);
		txtF03.setEditable(false);
		txtF03.setText(contProd.criaCodiProd());
		txtF04.setEditable(true);
		txtF04.grabFocus();
		txtF05.setEditable(true);
		txtF06.setEditable(true);
		txtF08.setEditable(true);
		txtF09.setEditable(true);
		btnEditarPreco.setEnabled(false);
		cmbTabPreco.setEnabled(true);
		cmbGrupo.setEnabled(true);
		cmbSubGrupo.setEnabled(true);
	}

	// TODO Desabilita edição
	public static void desHabilitaEdicao() {
		txtF02.setEditable(false);
		txtF03.setEditable(false);
		txtF05.setEditable(false);
		txtF06.setEditable(false);
		txtF08.setEditable(false);
		txtF09.setEditable(false);

		btnEditarPreco.setEnabled(false);
		cmbTabPreco.setEnabled(false);
		chkBListaPrecos.setSelected(false);
		cmbGrupo.setEnabled(false);
		cmbSubGrupo.setEnabled(false);

	}

	// TODO Limpar campos
	public static void limparCampos() {
		txtF02.setText(null);
		txtF03.setText(null);
		txtF05.setText(null);
		txtF08.setText(null);
		txtF06.setText(null);
		txtF09.setText(null);
		chkBListaPrecos.setSelected(false);
		carregarImagem(null);
		desabilitaTabelaPrecos();
		txtADetalhes.setText(null);

	}

	public static JButton getBtnNovo() {
		return btnNovo;
	}

	public static void setBtnNovo(JButton btnNovo) {
		PainelProdutos.btnNovo = btnNovo;
	}

	public static JButton getBtnEditar() {
		return btnEditar;
	}

	public static void setBtnEditar(JButton btnEditar) {
		PainelProdutos.btnEditar = btnEditar;
	}

	public static JTextField getTxtFNomeProd() {
		return txtF05;
	}

	public static void setTxtFNomeProd(JTextField txtFNomeProd) {
		PainelProdutos.txtF05 = txtFNomeProd;
	}

	public static JButton getBtnCancelar() {
		return btnCancelar;
	}

	public static void setBtnCancelar(JButton btnCancelar) {
		PainelProdutos.btnCancelar = btnCancelar;
	}

	public static JComboBox<String> getCmbTabPreco() {
		return cmbTabPreco;
	}

	public static void setCmbTabPreco(JComboBox<String> cmbTabPreco) {
		PainelProdutos.cmbTabPreco = cmbTabPreco;
	}

	public JTable getTabelaPrecos() {
		return tabelaPrecos;
	}

	public static void setTabelaPrecos(JTable tabelaPrecos) {
		PainelProdutos.tabelaPrecos = tabelaPrecos;
	}

	public static JScrollPane getScrPrecos() {
		return scrPrecos;
	}

	public void setScrPrecos(JScrollPane scrPrecos) {
		this.scrPrecos = scrPrecos;
	}

}
