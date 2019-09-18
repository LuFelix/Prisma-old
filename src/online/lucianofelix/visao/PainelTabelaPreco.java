package online.lucianofelix.visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import de.wannawork.jcalendar.JCalendarComboBox;
import online.lucianofelix.beans.Ativo;
import online.lucianofelix.beans.TabelaPreco;
import online.lucianofelix.controle.ControlaListaTabelaPreco;
import online.lucianofelix.controle.ControlaTabelaPreco;
import online.lucianofelix.dao.DAOTabelaPreco;
import online.lucianofelix.util.ManipulaData;

public class PainelTabelaPreco extends JPanel {

	private JLabel lblImagem;
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

	private static JTextField txtF02;
	private static JTextField txtF03;
	private static JTextField txtF04;
	private static JTextField txtF05;
	private static JTextField txtF06;
	private static JTextField txtF07;
	private static JTextField txtF08;
	private static JTextField txtF09;
	private static JTextField txtF10;

	private JPanel pnlGrid;
	private JSplitPane sppImagem;
	private JSplitPane sppPrincipal;
	private JSplitPane sppSuperior;
	private JScrollPane scrImagem;
	private JPanel pnlInferior;
	private JTabbedPane tabVisualiza;
	private static JTable tbl01;
	private static JTable tbl02;
	private static JScrollPane scrP01;
	private static JScrollPane scrP02;

	private JLabel lblDataInicio;
	private JLabel lblDataFim;

	private static JComboBox<String> cmbTipoTabela;
	private static JCalendarComboBox dataInicio;
	private static JCalendarComboBox dataFim;

	// objetos de controle

	private static ControlaListaTabelaPreco controledaLista;
	static ManipulaData manData;
	private DAOTabelaPreco daoTabPreco;
	private static TabelaPreco tabPreco;
	private List<TabelaPreco> listTabPreco;
	private static ControlaTabelaPreco contTab;
	int tam;

	public PainelTabelaPreco(String nome) {
		UIManager.put("TextField.font",
				new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Label.font", new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Button.font",
				new Font("Times New Roman", Font.BOLD, 12));

		contTab = new ControlaTabelaPreco();
		daoTabPreco = new DAOTabelaPreco();
		manData = new ManipulaData();
		setLayout(null);

		// TODO Configuração dos Labels e text fields
		lbl01 = new JLabel("Tabela de Preço");
		lbl01.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lbl02 = new JLabel("Sequência:");
		txtF02 = new JTextField();
		lbl03 = new JLabel("Código:");
		setTxtFCodiTabela(new JTextField());
		lbl04 = new JLabel("Data Criação:");
		txtF04 = new JTextField();
		lbl05 = new JLabel("Nome Tabela:");
		txtF05 = new JTextField();
		lbl06 = new JLabel("Descrição:");
		txtF06 = new JTextField();
		lbl07 = new JLabel("Fornecedor: ");
		txtF07 = new JTextField();
		lbl08 = new JLabel("Classe:");
		txtF08 = new JTextField();
		lbl09 = new JLabel("Loja/Revenda:");
		txtF09 = new JTextField();
		lbl10 = new JLabel("Tipo Tabela: ");

		cmbTipoTabela = new JComboBox<String>();
		cmbTipoTabela.addItem("Tipo Tabela");
		cmbTipoTabela.addItem("Venda");
		cmbTipoTabela.addItem("Compra");

		lblDataInicio = new JLabel("Data Início: ");
		setDataInicio(new JCalendarComboBox());
		getDataInicio().setBackground(Color.WHITE);
		lblDataFim = new JLabel("Data Fim: ");
		setDataFim(new JCalendarComboBox());
		getDataFim().setBackground(Color.WHITE);

		// TODO Configuração do Painel principal
		pnlGrid = new JPanel();
		pnlGrid.setBorder(BorderFactory.createEtchedBorder());
		pnlGrid.setLayout(new GridLayout(11, 2));
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
		pnlGrid.add(lbl08);
		pnlGrid.add(txtF08);
		pnlGrid.add(lbl09);
		pnlGrid.add(txtF09);
		pnlGrid.add(lblDataInicio);
		pnlGrid.add(getDataInicio());
		pnlGrid.add(lblDataFim);
		pnlGrid.add(getDataFim());
		pnlGrid.add(lbl10);
		pnlGrid.add(cmbTipoTabela);

		desHabilitaEdicao();
		listTabPreco = daoTabPreco.pesquisaString(nome);
		tam = listTabPreco.size();
		tam--;
		if (tam < 0) {
			FrameInicial.setPainelVisualiza(null);
			FrameInicial.getScrVisualiza()
					.setViewportView(FrameInicial.getPainelVisualiza());
		} else {
			controledaLista = new ControlaListaTabelaPreco(listTabPreco);
			tabPreco = controledaLista.first();
			carregarCampos(tabPreco);
		}

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

		tbl01 = new JTable();
		scrP01 = new JScrollPane();
		scrP01.setViewportView(tbl01);
		tbl02 = new JTable();
		scrP02 = new JScrollPane();
		scrP02.setViewportView(tbl02);

		tabVisualiza = new JTabbedPane();
		tabVisualiza.addTab("Tabela 1", scrP01);
		tabVisualiza.add("Tabela 2", scrP02);

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
		setLayout(new GridLayout());
		setBackground(Color.WHITE);
		add(sppPrincipal);

	}

	public static void irParaPoicao(int posicao) {
		controledaLista.setCurrentPosition(posicao);
		tabPreco = controledaLista.getAt(posicao);
		carregarCampos(tabPreco);
	}

	// TODO Ler Campos.
	public static TabelaPreco lerCampos() {
		tabPreco = new TabelaPreco();
		tabPreco.setCodiTabela(getTxtFCodiTabela().getText());
		tabPreco.setDataCriacao(Date.valueOf(txtF04.getText()));
		// tabPreco.setDataCriacao(Timestamp.valueOf(manData.dataAgora()));
		tabPreco.setDataInicio(manData.sqlDate(getDataInicio().getDate()));
		tabPreco.setDataFim(manData.sqlDate(getDataFim().getDate()));
		if (!txtF02.getText().equals("") && !txtF02.equals(null)) {
			tabPreco.setSeqTabelaPrecos(Integer.parseInt(txtF02.getText()));
		}
		tabPreco.setNomeTabela(txtF05.getText());
		tabPreco.setTipoTabela(cmbTipoTabela.getSelectedItem().toString());
		tabPreco.setDescTabela(txtF06.getText());
		tabPreco.setCodiFornecedor(txtF07.getText());
		tabPreco.setClasseTabela(txtF08.getText());
		return tabPreco;
	}

	// TODO Carregar Campos
	public static void carregarCampos(TabelaPreco tabPreco) {
		if (!tabPreco.equals(null) && !tabPreco.equals("")) {
			txtF02.setText(String.valueOf(tabPreco.getSeqTabelaPrecos()));
		}
		getTxtFCodiTabela().setText(tabPreco.getCodiTabela());
		txtF04.setText(String.valueOf(tabPreco.getDataCriacao()));
		getDataInicio().setDate(tabPreco.getDataInicio());
		getDataFim().setDate(tabPreco.getDataFim());
		// txtFDataInicio.setText(String.valueOf(tabPreco.getDataInicio()));
		// txtFDataFim.setText(String.valueOf(tabPreco.getDataFim()));
		// txtFTipoTabela.setText(tabPreco.getTipoTabela());
		cmbTipoTabela.setSelectedItem(tabPreco.getTipoTabela());
		txtF05.setText(tabPreco.getNomeTabela());
		txtF06.setText(tabPreco.getDescTabela());
		txtF07.setText(String.valueOf(tabPreco.getCodiFornecedor()));
		txtF08.setText(String.valueOf(tabPreco.getClasseTabela()));
	}

	public static void habilitaEdicao() {
		getTxtFCodiTabela().setEditable(false);
		txtF04.setEditable(false);
		getDataInicio().setEnabled(true);
		getDataFim().setEnabled(true);
		txtF06.setEditable(true);
		txtF07.setEditable(true);
		txtF08.setEditable(true);
		cmbTipoTabela.setEnabled(true);
	}

	// TODO Habilita novo
	public static void habilitaNovo() {
		limparCampos();
		getTxtFCodiTabela().setText(contTab.criaCodigo());
		getTxtFCodiTabela().setEditable(false);
		getTxtFCodiTabela().setEnabled(false);
		txtF04.setEditable(false);
		txtF04.setEnabled(false);
		txtF04.setText(String.valueOf(new Timestamp(System.currentTimeMillis()))
				.substring(0, 10));
		getDataInicio().setEnabled(true);
		getDataFim().setEnabled(true);
		txtF06.setEditable(true);
		txtF07.setEditable(true);
		txtF08.setEditable(true);
		cmbTipoTabela.setEnabled(true);
	}

	public void refresh() {
		repaint();
	}

	// DesabilitaEdição
	public static void desHabilitaEdicao() {
		getTxtFCodiTabela().setEditable(false);
		txtF02.setEditable(false);
		txtF04.setEditable(false);
		getDataInicio().setEnabled(false);
		getDataFim().setEnabled(false);
		txtF06.setEditable(false);
		txtF07.setEditable(false);
		txtF09.setEditable(false);
		txtF08.setEditable(false);
		cmbTipoTabela.setEnabled(false);

	}

	public static void limparCampos() {
		txtF04.setText(null);
		getDataInicio().setDate(Calendar.getInstance().getTime());
		getDataFim().setDate(null);
		txtF05.setText(null);
		txtF06.setText(null);
		txtF07.setText(null);
		txtF09.setText(null);
		txtF08.setText(null);
		cmbTipoTabela.setSelectedIndex(0);

	}

	public static void adicionaAtvOp(Ativo ativo) {
		txtF04.setText(ativo.getIdNeg());

	}

	/*
	 * private JRadioButton jrbEditarSim; private JRadioButton jrbEditarNao;
	 * private ButtonGroup grpRadio;
	 */

	public static JCalendarComboBox getDataInicio() {
		return dataInicio;
	}

	public static void setDataInicio(JCalendarComboBox dataInicio) {
		PainelTabelaPreco.dataInicio = dataInicio;
	}

	public static JCalendarComboBox getDataFim() {
		return dataFim;
	}

	public static void setDataFim(JCalendarComboBox dataFim) {
		PainelTabelaPreco.dataFim = dataFim;
	}

	public static JTextField getTxtFCodiTabela() {
		return txtF03;
	}

	public static void setTxtFCodiTabela(JTextField txtFCodiTabela) {
		PainelTabelaPreco.txtF03 = txtFCodiTabela;
	}
}
