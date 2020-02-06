package online.lucianofelix.visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdatepicker.JDatePicker;
import org.joda.time.DateTime;

import online.lucianofelix.beans.CondPagamento;
import online.lucianofelix.beans.Conta;
import online.lucianofelix.beans.Lancamento;
import online.lucianofelix.beans.Pessoa;
import online.lucianofelix.controle.ControlaCentroCusto;
import online.lucianofelix.controle.ControlaCondPagamento;
import online.lucianofelix.controle.ControlaConta;
import online.lucianofelix.controle.ControlaPessoa;

public class PainelLancamento extends JPanel {

	private JLabel lblImagem;
	private JLabel lbl01;
	private JLabel lblSequencia;
	private JLabel lblCodigo;
	private JLabel lblTitular;
	private JLabel lblTitulo;
	private JLabel lblCondPag;
	private JLabel lblDTVenc;
	private JLabel lbl08;
	private JLabel lbl09;
	private JLabel lbl10;
	private JLabel lbl11;

	private static JTextField txtFSequencia;
	private static JTextField txtFCodigo;
	private static JTextField txtFTitular;
	private static JTextField txtFTitulo;
	private static JTextField txtFCondPag;

	private static JDatePicker dtvenc;
	private static JTextField txtF08;

	private JScrollPane scrImagem;
	private static JScrollPane scrP01;
	private static JScrollPane scrP02;
	private static JTable tbl01;
	private static JTable tbl02;
	static List<Conta> listContas;
	static ControlaConta contConta;
	static ControlaCentroCusto contCCusto;
	static ControlaPessoa contPess;
	static ControlaCondPagamento contCdPag;
	private JSplitPane sppImagem;
	private JSplitPane sppPrincipal;
	private JSplitPane sppSuperior;
	private JPanel pnlInferior;
	private JPanel pnlGrid;
	private JTabbedPane tabVisualiza;

	private static JComboBox<String> cmbCCusto;;
	private static JComboBox<String> cmbContas;
	private static Lancamento lanc;

	public PainelLancamento(Lancamento l) {
		contConta = new ControlaConta();
		contCCusto = new ControlaCentroCusto();
		contPess = new ControlaPessoa();
		contCdPag = new ControlaCondPagamento();
		iniciaComponentes();
		carregarCampos(l);
		desHabilitaEdicao();
		add(sppPrincipal);

	}
	public PainelLancamento() {
		contConta = new ControlaConta();
		contCCusto = new ControlaCentroCusto();
		contPess = new ControlaPessoa();
		contCdPag = new ControlaCondPagamento();
		iniciaComponentes();
		desHabilitaEdicao();
		add(sppPrincipal);

	}
	void iniciaComponentes() {
		lbl01 = new JLabel("Lançamento");
		lbl01.setFont(new Font("Times New Roman", Font.BOLD, 28));

		setLblSequencia(new JLabel("Sequência:"));
		txtFSequencia = new JTextField();
		lblCodigo = new JLabel("Código:");
		txtFCodigo = new JTextField();
		lblTitular = new JLabel("Titular:");
		txtFTitular = new JTextField();
		txtFTitular.setEditable(false);
		txtFTitular.setFocusable(false);
		txtFTitular.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				FrameInicial.pesqUsuarioAddPLanc();
			}
		});
		lblTitulo = new JLabel("Título: ");
		txtFTitulo = new JTextField();
		lblCondPag = new JLabel("Condição Pagamento:");
		txtFCondPag = new JTextField();
		txtFCondPag.setEditable(false);
		txtFCondPag.setFocusable(false);
		txtFCondPag.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				FrameInicial.pesqCondPagAddPLanc();
			}
		});

		lblDTVenc = new JLabel("Data criação:");
		lblDTVenc.setFont(new Font("Times New Roman", Font.BOLD, 18));
		dtvenc = new JDatePicker(Calendar.getInstance());

		lbl08 = new JLabel("Valor:");
		lbl08.setFont(new Font("Times New Roman", Font.BOLD, 22));
		txtF08 = new JTextField();
		txtF08.setHorizontalAlignment(JTextField.RIGHT);
		txtF08.setFont(new Font("Times New Roman", Font.BOLD, 18));

		tbl01 = new JTable();
		scrP01 = new JScrollPane();
		scrP01.setViewportView(tbl01);

		tbl02 = new JTable();
		scrP02 = new JScrollPane();
		scrP02.setViewportView(tbl02);

		tabVisualiza = new JTabbedPane();
		tabVisualiza.addTab("Entradas", scrP01);
		tabVisualiza.addTab("Saídas", scrP02);
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
		pnlGrid.setLayout(new GridLayout(6, 2));
		pnlGrid.setBackground(Color.WHITE);

		cmbCCusto = contCCusto.cmbCentrosCusto();
		cmbContas = new JComboBox<>();
		cmbCCusto.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				cmbCCustoSelecionaItem(e);
			}
			private void cmbCCustoSelecionaItem(ItemEvent e) {
				recarregaContas((String) e.getItem());
			}
			private void recarregaContas(String item) {
				contConta.carregarContas(cmbContas, item);
			}
		});

		pnlGrid.add(cmbCCusto);
		pnlGrid.add(cmbContas);
		// pnlGrid.add(lbl02);
		// pnlGrid.add(txtF02);
		// pnlGrid.add(lbl03);
		// pnlGrid.add(txtF03);
		pnlGrid.add(lblTitular);
		pnlGrid.add(txtFTitular);
		pnlGrid.add(lblTitulo);
		pnlGrid.add(txtFTitulo);
		pnlGrid.add(lblCondPag);
		pnlGrid.add(txtFCondPag);
		pnlGrid.add(lblDTVenc);
		pnlGrid.add(dtvenc);
		pnlGrid.add(lbl08);
		pnlGrid.add(txtF08);

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
		desHabilitaEdicao();

	}
	public static void adicionaUsuario(Pessoa usua) {
		txtFTitular.setText(usua.getNome());

	}
	public static void adicionaCondPag(CondPagamento condPag) {
		txtFCondPag.setText(condPag.getNomeCondicao());

	}

	public static void desHabilitaEdicao() {

		txtFSequencia.setEditable(false);
		txtFCodigo.setEditable(false);
		txtFTitular.setFocusable(false);
		txtFTitulo.setEditable(false);
		txtFCondPag.setFocusable(false);
		dtvenc.setEnabled(false);
		cmbCCusto.setEnabled(false);
		cmbContas.setEnabled(false);

	}

	public static Lancamento lerCampos() {
		lanc = new Lancamento();
		lanc.setCodiPessoa(txtFTitular.getText());
		lanc.setCodiCondPag(txtFCondPag.getText());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>CMB Conta "
				+ cmbContas.getSelectedItem());
		if (!cmbContas.getSelectedItem().equals(null)) {
			lanc.setCodiConta(cmbContas.getSelectedItem().toString());
		}
		lanc.setValor(new BigDecimal(txtF08.getText()));

		return lanc;
	}

	public void carregarCampos(Lancamento l) {
		if (l.getCodiPessoa() != null & l.getCodiPessoa() != "") {
			txtFTitular.setText(contPess.pesqNomeCodigo(l.getCodiPessoa()));
		}
		txtFTitulo.setText(l.getCodiPedido());
		txtFCondPag.setText(contCdPag.buscaNomeCodigo(l.getCodiCondPag()));
		DateTime dt = new DateTime(l.getDtHrLanc());
		dtvenc.getModel().setDate(dt.getDayOfMonth(), dt.getMonthOfYear(),
				dt.getYear());

		System.out.println(
				">>>>>>>>>>>>>>>>>>>>  " + "dia " + dt.getDayOfMonth() + " mes "
						+ dt.getMonthOfYear() + " ano " + dt.getYear());
		txtF08.setText(String.valueOf(l.getValor()));
		cmbCCusto.setSelectedItem(contConta.nomeCentCustCodi(l.getCodiConta()));
		cmbContas.setSelectedItem(contConta.nomeContaCodigo(l.getCodiConta()));

	}
	public static void habilitaEdicao() {

		txtFTitular.setFocusable(true);
		txtFTitulo.setEditable(true);
		txtFCondPag.setFocusable(true);
		dtvenc.setEnabled(true);
		cmbContas.setEnabled(true);
		cmbCCusto.setEnabled(true);
	}
	public static void habilitaNovo() {
		limparCampos();

		txtFSequencia.setText("0");
		txtFTitular.setFocusable(true);
		txtFTitulo.setEditable(true);
		txtFCondPag.setFocusable(true);
		txtFCondPag.setEditable(true);
		dtvenc.setEnabled(true);
		txtF08.setEditable(true);
		cmbCCusto.setEnabled(true);
		cmbCCusto.grabFocus();
		cmbContas.setEnabled(true);

	}
	// TODO Limpar campos
	public static void limparCampos() {

		txtFSequencia.setText(null);
		txtFCodigo.setText(null);
		txtFTitular.setText(null);
		txtFTitulo.setText(null);
		txtFCondPag.setText(null);
		txtFCondPag.setText(null);
		dtvenc.getModel().setDate(0, 0, 0);
		txtF08.setText(null);
		cmbCCusto.setSelectedItem(0);
		cmbContas.setSelectedItem(0);

	}
	/**
	 * @return the cmbContas
	 */
	public static JComboBox<String> getCmbContas() {
		return cmbContas;
	}
	/**
	 * @param cmbContas
	 *            the cmbContas to set
	 */
	public static void setCmbContas(JComboBox<String> cmbContas) {
		PainelLancamento.cmbContas = cmbContas;
	}
	/**
	 * @return the lblSequencia
	 */
	public JLabel getLblSequencia() {
		return lblSequencia;
	}
	/**
	 * @param lblSequencia
	 *            the lblSequencia to set
	 */
	public void setLblSequencia(JLabel lblSequencia) {
		this.lblSequencia = lblSequencia;
	}

}
