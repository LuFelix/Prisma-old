package online.lucianofelix.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import online.lucianofelix.beans.GrupoSubgrupo;
import online.lucianofelix.beans.Produto;
import online.lucianofelix.controle.ControlaGrupoSubgrupo;
import online.lucianofelix.controle.ControlaListaProdutos;
import online.lucianofelix.controle.ControlaProduto;
import online.lucianofelix.controle.ControlaTabelaPreco;
import online.lucianofelix.dao.DAOTabelaPreco;
import online.lucianofelix.tableModels.commom.TableModelProdutoGrupo;
import online.lucianofelix.tableModels.commom.TableModelProdutoImagens;
import online.lucianofelix.util.ManipulaImagensProduto;

public class PainelProdutos extends JPanel {

	// JFrame telaProduto;

	private JSplitPane jspPrincipal;
	private JSplitPane sppSuperior;
	private JSplitPane sppImagem;
	private JPanel painelGrid;
	private JPanel painelMovimento;
	private JPanel pnlBotoesImagensProds;
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
	private List<GrupoSubgrupo> listGrupo;

	private static JButton btnEditarPreco;
	private static JButton btnAddCategoria;
	private static JButton btnNovo;
	private static JButton btnEditar;
	private static JButton btnCancelar;
	private JButton btnAddImagem;
	private JButton btnRemImagem;
	// Tabela de preços do produto

	private JTabbedPane tabVisualiza;
	private static JTable tabelaCategorias;
	private static JTable tabelaImagens;
	private static JTable tabelaPrecos;
	private static JTable tabelaMovEstoque;
	private static DefaultTableModel modeloTabela;
	private static TableModelProdutoGrupo modeloTabelaGrupo;
	private static TableModelProdutoImagens mdlTableProdImg;
	private JScrollPane scrDescricao;
	private static JScrollPane scrCategorias;
	private static JScrollPane scrPrecos;
	private static JScrollPane scrEstoque;
	private static JScrollPane scrImagem;
	private static JScrollPane scrImagensProd;
	private static JScrollPane scrDetalhes;
	private static JTextArea txtADescricao;
	// objetos de controle

	private static ControlaListaProdutos controledaLista;
	private static ControlaProduto contProd;
	private ControlaTabelaPreco contTabPreco;
	private ControlaGrupoSubgrupo contGrupo;
	private static Produto prod;
	private DAOTabelaPreco daoTabPreco;
	private JPanel pnlImagensProd;
	private JPanel pnlDetalhes;
	private JList listGrupoView;

	// TODO Construtor
	public PainelProdutos(Produto p) {

		UIManager.put("TextField.font",
				new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Label.font", new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Button.font",
				new Font("Times New Roman", Font.BOLD, 12));
		// Controle
		this.prod = p;
		contProd = new ControlaProduto();
		contGrupo = new ControlaGrupoSubgrupo();
		contTabPreco = new ControlaTabelaPreco();
		// Dados
		daoTabPreco = new DAOTabelaPreco();

		// TODO Configuração dos Labels e text fields

		lblTituloTela = new JLabel("   Produto");
		lblTituloTela.setBounds(10, 0, 150, 40);
		lblTituloTela.setFont(new Font("Times New Roman", Font.BOLD, 32));

		lbl02 = new JLabel("Sequência:");
		txtF02 = new JTextField();
		lbl03 = new JLabel("Código Interno:");
		txtF03 = new JTextField(0);
		lbl04 = new JLabel("Código Fábrica/EAN:");
		txtF04 = new JTextField();
		lbl05 = new JLabel("Produto:");
		txtF05 = new JTextField();
		lbl06 = new JLabel("Alíquota ICMS:");
		txtF06 = new JTextField();
		lbl08 = new JLabel("Estoque:");
		txtF08 = new JTextField();
		lbl09 = new JLabel("Preço Atual:");
		txtF09 = new JTextField();

		btnAddCategoria = new JButton("Adicionar Categoria");
		btnAddCategoria.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nomeCategoria = cmbSubGrupo.getSelectedItem().toString();
				prod = lerCampos();
				contProd.adicionarCategoria(prod, nomeCategoria);
				carregarCategorias(prod);

			}
		});
		btnAddImagem = new JButton("Adicionar imagens");
		btnAddImagem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				prod = lerCampos();
				new ManipulaImagensProduto(prod);

			}
		});
		btnRemImagem = new JButton("Remover imagens");
		btnRemImagem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				prod = lerCampos();

			}
		});

		cmbSubGrupo = contGrupo.carregarSubGruposProdutos();
		// lbl10 = new JLabel("Adicionar ao Grupo/Categoria");

		JButton btn = new JButton("Obter itens marcados");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itens = "";
				// Loop faz a varredura para obter quais estao marcados
				for (int i = 0; i < listGrupoView.getModel().getSize(); i++) {
					JCheckBox checkbox = (JCheckBox) listGrupoView.getModel()
							.getElementAt(i);
					if (checkbox.isSelected())
						itens += "Item com índice " + i + " está marcado\n";
					else
						itens += "Item com índice " + i + " está desmarcado\n";
				}
				JOptionPane.showMessageDialog(null, itens);
			}
		});
		listGrupoView = new JList();
		listGrupoView.setCellRenderer(new CheckBoxCellRenderer());
		Object[] cbArray = new Object[4];
		cbArray[0] = new JCheckBox("Categoria 1");
		cbArray[1] = new JCheckBox("Categoria 2");
		cbArray[2] = new JCheckBox("Categoria 3");
		cbArray[3] = new JCheckBox("Categoria 4");
		listGrupoView.setListData(cbArray);
		listGrupoView.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					int index = listGrupoView.getSelectedIndex();
					if (index != -1) {
						JCheckBox checkbox = (JCheckBox) listGrupoView
								.getModel().getElementAt(index);
						checkbox.setSelected(!checkbox.isSelected());
						repaint();
					}
				}
			}
		});
		listGrupoView.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int index = listGrupoView.locationToIndex(e.getPoint());
				if (index != -1) {
					JCheckBox checkbox = (JCheckBox) listGrupoView.getModel()
							.getElementAt(index);
					checkbox.setSelected(!checkbox.isSelected());
					repaint();
				}
			}
		});

		btnEditarPreco = new JButton("Alterar Preço");
		btnEditarPreco.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contProd.alteraPreco(lerCampos());
			}
		});

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

		tabelaPrecos = new JTable();
		tabelaMovEstoque = new JTable();
		tabelaCategorias = new JTable();
		tabelaImagens = new JTable();

		scrPrecos = new JScrollPane();
		scrPrecos.setViewportView(tabelaPrecos);

		scrCategorias = new JScrollPane();
		scrCategorias.setViewportView(tabelaCategorias);

		lblImagem = new JLabel("Image not Found");

		scrImagem = new JScrollPane(lblImagem);
		scrImagem.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrImagem.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		pnlImagensProd = new JPanel(new BorderLayout());
		scrImagensProd = new JScrollPane(tabelaImagens);
		pnlBotoesImagensProds = new JPanel(new GridLayout(1, 1));
		pnlBotoesImagensProds.add(btnAddImagem);
		// pnlBotoesImagensProds.add(btnRemImagem);
		pnlImagensProd.add(pnlBotoesImagensProds, BorderLayout.PAGE_START);
		pnlImagensProd.add(scrImagensProd, BorderLayout.CENTER);

		setPnlDetalhes(new JPanel(new BorderLayout()));
		pnlDetalhes.add(listGrupoView, BorderLayout.CENTER);
		scrDetalhes = new JScrollPane(pnlDetalhes);

		txtADescricao = new JTextArea();
		txtADescricao.setLineWrap(true);
		txtADescricao.setWrapStyleWord(true);
		scrDescricao = new JScrollPane(txtADescricao);

		tabVisualiza = new JTabbedPane();
		tabVisualiza.addTab("Detalhes", scrCategorias);
		tabVisualiza.addTab("Descrição", scrDescricao);
		tabVisualiza.addTab("Imagens", pnlImagensProd);
		tabVisualiza.addTab("Histórico de Preços", scrPrecos);
		tabVisualiza.add("Estoque", scrEstoque);

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
		painelGrid.add(lbl06);
		painelGrid.add(txtF06);
		painelGrid.add(lbl08);
		painelGrid.add(txtF08);
		painelGrid.add(lbl09);
		painelGrid.add(txtF09);
		painelGrid.add(btnEditarPreco);
		painelGrid.add(cmbTabPreco);
		painelGrid.add(btnAddCategoria);
		painelGrid.add(cmbSubGrupo);
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

		limparCampos();
		desHabilitaEdicao();
		if (prod != null) {
			contProd.carregaDetalhes(prod);
		}

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
	public static void desabilitaTabelaCategorias() {
		setTabelaCategorias(null);
		getScrPrecos().setViewportView(tabelaCategorias);
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

		if (!txtF04.getText().equals(null) & !txtF04.getText().equals("")) {
			prod.setcEAN(txtF04.getText());
		} else {
			prod.setcEAN("Não Cadastrado");
		}

		if (!txtF05.getText().equals(null) & !txtF05.getText().equals("")) {
			prod.setNome_prod(txtF05.getText());
		} else {
			erro();
		}
		if (!txtF06.getText().equals(null) & !txtF06.getText().equals("")) {
			prod.setAliq_prod(txtF06.getText());
		} else {
			prod.setAliq_prod("17");
		}
		if (txtF09.getText().equals("")) {
			prod.setPrec_prod_1(0);
		} else {
			prod.setPrec_prod_1(Float.parseFloat(txtF09.getText()));
		}
		prod.setDesc_prod(txtADescricao.getText());

		return prod;
	}
	void lerCategorias(Produto prod) {
		// Produto possui uma lista de GruposSubGrupo
	}
	static void carregarCategorias(Produto prod) {

		tabelaCategorias = new JTable();
		contProd.carregarCategorias(prod);
		modeloTabelaGrupo = new TableModelProdutoGrupo(prod);
		tabelaCategorias.setShowGrid(true);
		tabelaCategorias.setModel(modeloTabelaGrupo);
		scrCategorias.setViewportView(tabelaCategorias);

	}

	public static void carregarImagensProd(Produto prod) {

		mdlTableProdImg = new TableModelProdutoImagens(prod);
		tabelaImagens = new JTable(mdlTableProdImg);
		// tabelaImagens.setModel(mdlTableProdImg);
		TableColumnModel ColumnModel = tabelaImagens.getColumnModel();
		JTableRenderer renderer = new JTableRenderer();
		ColumnModel.getColumn(2).setCellRenderer(renderer);
		((DefaultTableCellRenderer) tabelaImagens.getTableHeader()
				.getDefaultRenderer())
						.setHorizontalAlignment(SwingConstants.LEFT);
		tabelaImagens.setShowGrid(true);
		tabelaImagens.setRowHeight(50);
		scrImagensProd.setViewportView(tabelaImagens);

	}

	public static void carregarImagem(String codiProd) {
		lblImagem = new JLabel(new ImageIcon(
				"C:\\SIMPRO\\img\\listas\\prods\\" + codiProd + ".jpg "));
		scrImagem.setViewportView(lblImagem);
	}

	// TODO Carregar campos
	public static void carregarCampos(Produto prod) {
		if (prod != null) {

			txtF02.setText(String.valueOf(prod.getSeq_produto()));
			txtF03.setText(String.valueOf(prod.getCodi_prod_1()));
			txtF04.setText(prod.getcEAN());
			txtF05.setText(prod.getNome_prod());
			txtF06.setText(prod.getAliq_prod());
			txtF08.setText(String.valueOf(prod.getEstoqueAtual()));
			txtF09.setText(String
					.valueOf(prod.getListCotacaoProduto().get(0).getValor()));

			txtADescricao.setText(prod.getDesc_prod());

			habilitaTabelaPrecos(prod);
			carregarImagem(prod.getCodi_prod_1());
			carregarCategorias(prod);
			carregarImagensProd(prod);

		}

	}

	// TODO Habilitar Edição
	public static void habilitaEdicao() {
		txtF03.setEditable(false);
		txtF05.setEditable(true);
		txtF06.setEditable(true);
		txtF08.setEditable(true);
		txtF09.setEditable(false);
		txtADescricao.setEditable(true);
		cmbSubGrupo.setEnabled(true);
		btnEditarPreco.setEnabled(true);
		btnAddCategoria.setEnabled(true);
		cmbTabPreco.setEnabled(true);
		tabelaCategorias.setEnabled(true);
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
		txtADescricao.setEditable(true);
		cmbTabPreco.setEnabled(true);
		cmbSubGrupo.setEnabled(true);
		btnEditarPreco.setEnabled(false);
		btnAddCategoria.setEnabled(true);
		tabelaCategorias.setEnabled(true);
	}

	// TODO Desabilita edição
	public static void desHabilitaEdicao() {
		txtF02.setEditable(false);
		txtF03.setEditable(false);
		txtF05.setEditable(false);
		txtF06.setEditable(false);
		txtF08.setEditable(false);
		txtF09.setEditable(false);
		txtADescricao.setEditable(false);
		btnEditarPreco.setEnabled(false);
		cmbTabPreco.setEnabled(false);
		cmbSubGrupo.setEnabled(false);
		chkBListaPrecos.setSelected(false);
		btnAddCategoria.setEnabled(false);

	}

	// TODO Limpar campos
	public static void limparCampos() {
		txtF02.setText(null);
		txtF03.setText(null);
		txtF05.setText(null);
		txtF08.setText(null);
		txtF06.setText(null);
		txtF09.setText(null);
		carregarImagem(null);
		desabilitaTabelaPrecos();
		desabilitaTabelaCategorias();
		txtADescricao.setText(null);
		tabelaCategorias = null;
		scrCategorias.setViewportView(tabelaCategorias);

	}

	static void erro() {
		JOptionPane.showMessageDialog(null,
				"Problemas: Verifique as informações preenchidas",
				"Erro ao Salvar. Campos com * são necessários",
				JOptionPane.ERROR_MESSAGE);
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
	public static void setTabelaCategorias(JTable tabelaCategorias) {
		PainelProdutos.tabelaCategorias = tabelaCategorias;
	}

	public static JScrollPane getScrPrecos() {
		return scrPrecos;
	}

	public void setScrPrecos(JScrollPane scrPrecos) {
		this.scrPrecos = scrPrecos;
	}

	public JPanel getPnlDetalhes() {
		return pnlDetalhes;
	}

	public void setPnlDetalhes(JPanel pnlDetalhes) {
		this.pnlDetalhes = pnlDetalhes;
	}

}
class JTableRenderer extends DefaultTableCellRenderer {
	protected void setValue(Object value) {
		if (value instanceof ImageIcon) {
			if (value != null) {
				// ImageIcon d = (ImageIcon) value;
				ImageIcon pic = (ImageIcon) value;
				Image scaled = pic.getImage().getScaledInstance(40, 40,
						Image.SCALE_DEFAULT);
				ImageIcon icon = new ImageIcon(scaled);
				// setIcon(d);
				setIcon(icon);
			} else {
				setText("");
				setIcon(null);
			}
		} else {
			super.setValue(value);
		}
	}
}
class CheckBoxCellRenderer implements ListCellRenderer {
	Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		JCheckBox checkbox = (JCheckBox) value;
		checkbox.setBackground(isSelected
				? list.getSelectionBackground()
				: list.getBackground());
		checkbox.setForeground(isSelected
				? list.getSelectionForeground()
				: list.getForeground());
		checkbox.setEnabled(list.isEnabled());
		checkbox.setFont(list.getFont());
		checkbox.setFocusPainted(false);
		checkbox.setBorderPainted(true);
		checkbox.setBorder(isSelected
				? UIManager.getBorder("List.focusCellHighlightBorder")
				: noFocusBorder);
		return checkbox;
	}
}
