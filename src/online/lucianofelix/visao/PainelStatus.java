package online.lucianofelix.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import online.lucianofelix.beans.CentroCusto;
import online.lucianofelix.beans.Conta;
import online.lucianofelix.beans.Produto;
import online.lucianofelix.controle.ControlaConta;
import online.lucianofelix.controle.ControlaListaProdutos;
import online.lucianofelix.controle.ControlaTabelaPreco;
import online.lucianofelix.dao.DAOCentroCusto;

public class PainelStatus extends JPanel {

	// JFrame telaProduto;
	private JTabbedPane tabPrincipal;
	JPanel painelGeral;
	JPanel painelDespesas;
	JPanel painelReceitas;
	private JLabel lblTituloTela;
	// Labels e text fields
	private JLabel lblImagem;
	private JLabel lbl01;
	private JLabel lbl02;
	private JLabel lbl03;
	private JLabel lbl04;
	private JLabel lbl05;
	private JLabel lbl06;
	private JLabel lbl07;
	private JLabel lbl08;

	private static JTextField txtF02;
	private static JTextField txtF03;
	private static JTextField txtF04;
	private static JTextField txtF05;
	private static JTextField txtF06;
	private static JTextField txtF07;
	private static JTextField txtF08;
	private JScrollPane scrImagem;
	private static JScrollPane scrP01;
	private static JScrollPane scrP02;
	private static JTable tbl01;
	private static JTable tbl02;

	private JSplitPane sppImagem;
	private JSplitPane sppPrincipal;
	private JSplitPane sppSuperior;
	private JPanel pnlInferior;
	private JPanel pnlGrid;
	private JTabbedPane tabVisualiza;

	private JLabel contas;
	private JLabel lblTotalGeral;
	private JLabel lblRealizado;
	private JLabel lblRealizar;
	private JLabel lblRealizadoPorc;
	private JLabel lblRealizarPorc;

	private static JCheckBox chkBListaPrecos;

	private static JTextField txtFTotalGeral;
	private static JTextField txtFRealizar;
	private static JTextField txtFRealizarPorc;
	private static JTextField txtFRealizado;
	private static JTextField txtFRealizadoPorc;

	private static JComboBox<String> cmbCentroCustoDesp;
	private static JComboBox<String> cmbCentroCustoRec;
	private static JComboBox<String> cmbAnoDesp;
	private static JComboBox<String> cmbAnoRec;

	private static JButton btnEditarPreco;
	private JRadioButton jrbEditarSim;
	private JRadioButton jrbEditarNao;
	private ButtonGroup grpRadio;
	private ActionListener acaoEditar;

	private JTabbedPane tabPnlDupPagar;
	private JTabbedPane tabPnlDupReceber;
	private static JTable tblContas;
	private static JTable tblCentroCusto;
	private static DefaultTableModel modeloTabela;
	private static JScrollPane scrContas;
	private static JScrollPane scrEstoque;

	// objetos de controle

	private static ControlaListaProdutos controledaLista;
	private static ControlaConta contConta;
	private ControlaTabelaPreco contTabPreco;
	private static Produto prod;
	private DAOCentroCusto daoCentCusto;

	private static JComboBox<String> cmbData;

	// TODO Construtor
	public PainelStatus(String nome) {

		UIManager.put("TextField.font",
				new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Label.font", new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Button.font",
				new Font("Times New Roman", Font.BOLD, 12));
		// Controle
		contConta = new ControlaConta();
		contTabPreco = new ControlaTabelaPreco();
		// Dados
		daoCentCusto = new DAOCentroCusto();
		iniciaComponentes();

	}
	void iniciaComponentes() {

		lbl01 = new JLabel("Resumos");
		lbl01.setFont(new Font("Times New Roman", Font.BOLD, 28));

		lbl02 = new JLabel("Valor Total Receitas:");
		txtF02 = new JTextField();

		lbl03 = new JLabel("Valor Realizado Receitas:");
		txtF03 = new JTextField();

		lbl04 = new JLabel("Percentual Realizado Receitas:");
		lbl04.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtF04 = new JTextField();
		txtF04.setHorizontalAlignment(JTextField.RIGHT);
		txtF04.setFont(new Font("Times New Roman", Font.BOLD, 16));

		lbl05 = new JLabel("Valor Total Despesas: ");
		txtF05 = new JTextField();

		lbl06 = new JLabel("Valor realizado Despesas:");
		txtF06 = new JTextField();

		lbl07 = new JLabel("Percentual Realizado Despesas:");
		lbl07.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtF07 = new JTextField();
		txtF07.setHorizontalAlignment(JTextField.RIGHT);
		txtF07.setFont(new Font("Times New Roman", Font.BOLD, 16));

		cmbData = new JComboBox<String>();
		cmbData.setBounds(10, 60, 90, 25);
		cmbData.addItem("..: Mês :..");
		cmbData.addItem("Janeiro");
		cmbData.addItem("Fevereiro");
		cmbData.addItem("Março");
		cmbData.addItem("Abril");
		cmbData.addItem("Maio");
		cmbData.addItem("Junho");
		cmbData.addItem("Julho");
		cmbData.addItem("Agosto");
		cmbData.addItem("Setembro");
		cmbData.addItem("Outubro");
		cmbData.addItem("Novembro");
		cmbData.addItem("Dezembro");

		cmbCentroCustoDesp = new JComboBox<String>();
		cmbCentroCustoDesp.setBounds(10, 115, 170, 25);
		cmbCentroCustoDesp.addItem("Centro de Custo");
		List<CentroCusto> listCentCustoDesp = daoCentCusto.pesquisarString("");
		for (int i = 0; i < listCentCustoDesp.size(); i++) {
			cmbCentroCustoDesp
					.addItem(listCentCustoDesp.get(i).getNomeCentroCusto());
		}

		cmbCentroCustoRec = new JComboBox<String>();
		cmbCentroCustoRec.setBounds(10, 115, 170, 25);
		cmbCentroCustoRec.addItem("Centro de Custo");
		List<CentroCusto> listCentCustoRec = daoCentCusto.pesquisarString("");
		for (int i = 0; i < listCentCustoRec.size(); i++) {
			cmbCentroCustoRec
					.addItem(listCentCustoRec.get(i).getNomeCentroCusto());
		}

		tbl01 = new JTable();
		scrP01 = new JScrollPane();

		tbl02 = new JTable();
		scrP02 = new JScrollPane();

		tabVisualiza = new JTabbedPane();
		tabVisualiza.addTab("Contas", scrP01);
		tabVisualiza.addTab("Tabela 02", scrP02);
		tabVisualiza.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// habilitaTabelaMovimentos();
			}
		});
		scrImagem = new JScrollPane(lblImagem);
		scrImagem.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrImagem.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		sppImagem = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		sppImagem.add(lbl01);
		sppImagem.add(scrImagem);
		sppImagem.setDividerLocation(50);
		sppImagem.setEnabled(false);
		sppImagem.setBackground(Color.WHITE);
		sppImagem.setForeground(Color.WHITE);
		sppImagem.setDividerSize(3);

		pnlGrid = new JPanel();
		pnlGrid.setBorder(BorderFactory.createEtchedBorder());
		pnlGrid.setLayout(new GridLayout(8, 2));
		pnlGrid.setBackground(Color.WHITE);

		pnlGrid.add(lbl02);
		pnlGrid.add(txtF02);
		pnlGrid.add(lbl03);
		pnlGrid.add(txtF03);
		pnlGrid.add(lbl04);
		pnlGrid.add(txtF04);
		pnlGrid.add(lbl05);
		pnlGrid.add(txtF05);
		pnlGrid.add(lbl06);
		pnlGrid.add(txtF06);
		pnlGrid.add(lbl07);
		pnlGrid.add(txtF07);

		sppSuperior = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		sppSuperior.setDividerLocation(200);
		sppSuperior.setDividerSize(3);
		sppSuperior.setEnabled(false);
		sppSuperior.add(sppImagem);
		sppSuperior.add(pnlGrid);

		pnlInferior = new JPanel();
		pnlInferior.setBorder(BorderFactory.createEtchedBorder());
		pnlInferior.setLayout(new GridLayout());
		pnlInferior.setBackground(Color.WHITE);
		pnlInferior.add(tabVisualiza);

		sppPrincipal = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		sppPrincipal.setDividerSize(3);
		sppPrincipal.setDividerLocation(250);
		sppPrincipal.setEnabled(false);
		sppPrincipal.setBackground(Color.WHITE);
		sppPrincipal.add(sppSuperior);
		sppPrincipal.add(pnlInferior);

		tabPnlDupPagar = new JTabbedPane();
		tabPnlDupPagar.addTab("Vencidas", null);
		tabPnlDupPagar.addTab("Hoje", null);
		tabPnlDupPagar.addTab("Prox. Semana", null);
		tabPnlDupPagar.addTab("Prox. 30 dias", null);
		tabPnlDupPagar.addTab("Prox. 60 dias", null);
		tabPnlDupPagar.addTab("Prox. 90 dias", null);

		tabPnlDupReceber = new JTabbedPane();
		tabPnlDupReceber.addTab("Vencidas", null);
		tabPnlDupReceber.addTab("Hoje", null);
		tabPnlDupReceber.addTab("Prox. Semana", null);
		tabPnlDupReceber.addTab("Prox. 30 dias", null);
		tabPnlDupReceber.addTab("Prox. 60 dias", null);
		tabPnlDupReceber.addTab("Prox. 90 dias", null);

		painelDespesas = new JPanel();
		painelDespesas.setBorder(BorderFactory.createEtchedBorder());
		painelDespesas.setLayout(new BorderLayout());
		painelDespesas.setBackground(Color.WHITE);
		painelDespesas.add(cmbCentroCustoDesp, BorderLayout.NORTH);
		painelDespesas.add(tabPnlDupPagar, BorderLayout.CENTER);

		painelReceitas = new JPanel();
		painelReceitas.setBorder(BorderFactory.createEtchedBorder());
		painelReceitas.setLayout(new BorderLayout());
		painelReceitas.setBackground(Color.WHITE);
		painelReceitas.add(cmbCentroCustoRec, BorderLayout.NORTH);
		painelReceitas.add(tabPnlDupReceber, BorderLayout.CENTER);

		tabPrincipal = new JTabbedPane(JTabbedPane.TOP);
		tabPrincipal.setBackground(Color.WHITE);

		tabPrincipal.addTab("Resumo", sppPrincipal);
		tabPrincipal.addTab("Despesas .:: C. Pagar", painelDespesas);
		tabPrincipal.addTab("Receitas .:: C. Receber", painelReceitas);

		setBackground(Color.WHITE);
		setLayout(new GridLayout());
		add(tabPrincipal);
		carregaTbl01();
		desHabilitaEdicao();

	}

	// TODO Fim contrutor inicio Habilita/Desab./Carrega/Le/Limpa Campos

	// TODO Ler Campos.
	public static Produto lerCampos() {
		prod = new Produto();
		if (txtFTotalGeral.equals("") || txtFTotalGeral.equals(null)) {
			prod.setSeq_produto(Integer.parseInt(txtFTotalGeral.getText()));
		}
		return prod;
	}

	// TODO Carregar campos
	public static void carregarCampos(Conta c) {

	}

	public void carregaTbl01() {
		tbl01 = contConta.pesqNomeTabela("");
		scrP01.setViewportView(tbl01);
	}

	public static void habilitaEdicao() {
		cmbCentroCustoDesp.setEnabled(true);
	}

	// TODO Habilita novo
	public static void habilitaNovo() {
		limparCampos();
		cmbCentroCustoDesp.setEnabled(true);
	}

	// TODO Desabilita edição
	public static void desHabilitaEdicao() {
		txtF02.setEditable(false);
		txtF03.setEditable(false);
		txtF04.setEditable(false);
		txtF05.setEditable(false);
		txtF06.setEditable(false);
		txtF07.setEditable(false);
	}

	// TODO Limpar campos
	public static void limparCampos() {
		txtFTotalGeral.setText(null);
		chkBListaPrecos.setSelected(false);
	}

	public JTable getTabelaPrecos() {
		return tblContas;
	}

	public void setTabelaPrecos(JTable tabelaPrecos) {
		this.tblContas = tabelaPrecos;
	}

	public JScrollPane getScrPrecos() {
		return scrContas;
	}

	public void setScrPrecos(JScrollPane scrPrecos) {
		this.scrContas = scrPrecos;
	}

}
