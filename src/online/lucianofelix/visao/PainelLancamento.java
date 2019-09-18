package online.lucianofelix.visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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

import online.lucianofelix.beans.Conta;
import online.lucianofelix.beans.Lancamento;
import online.lucianofelix.controle.ControlaCentroCusto;
import online.lucianofelix.controle.ControlaConta;

public class PainelLancamento extends JPanel {

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
	private JLabel lbl11;

	private static JTextField txtF02;
	private static JTextField txtF03;
	private static JTextField txtF04;
	private static JTextField txtF05;
	private static JTextField txtF06;
	private static JTextField txtF07;
	private static JTextField txtF08;
	private static JTextField txtF09;
	private static JTextField txtF10;
	private static JTextField txtF11;
	private JScrollPane scrImagem;
	private static JScrollPane scrP01;
	private static JScrollPane scrP02;
	private static JTable tbl01;
	private static JTable tbl02;
	static List<Conta> listContas;
	static ControlaConta contConta;
	static ControlaCentroCusto contCCusto;
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
		iniciaComponentes();
		carregarCampos(l);
		desHabilitaEdicao();
		add(sppPrincipal);

	}
	public PainelLancamento() {
		contConta = new ControlaConta();
		contCCusto = new ControlaCentroCusto();
		iniciaComponentes();
		desHabilitaEdicao();
		add(sppPrincipal);

	}
	void iniciaComponentes() {
		lbl01 = new JLabel("Lançamento");
		lbl01.setFont(new Font("Times New Roman", Font.BOLD, 28));

		lbl02 = new JLabel("Sequência:");
		txtF02 = new JTextField();
		lbl03 = new JLabel("Código:");
		txtF03 = new JTextField();

		lbl04 = new JLabel("Titular:");
		txtF04 = new JTextField();

		lbl05 = new JLabel("Título: ");
		txtF05 = new JTextField();

		lbl06 = new JLabel("Condição Pagamento:");
		txtF06 = new JTextField();

		lbl07 = new JLabel("Data criação:");
		lbl07.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtF07 = new JTextField();
		txtF07.setHorizontalAlignment(JTextField.RIGHT);
		txtF07.setFont(new Font("Times New Roman", Font.BOLD, 16));

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
		cmbContas = contConta.cmbContas();

		pnlGrid.add(cmbCCusto);
		pnlGrid.add(cmbContas);
		// pnlGrid.add(lbl02);
		// pnlGrid.add(txtF02);
		// pnlGrid.add(lbl03);
		// pnlGrid.add(txtF03);
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

	public static void desHabilitaEdicao() {

		txtF02.setEditable(false);
		txtF03.setEditable(false);
		txtF04.setEditable(false);
		txtF05.setEditable(false);
		txtF06.setEditable(false);
		txtF07.setEditable(false);
		cmbCCusto.setEnabled(false);
		cmbContas.setEnabled(false);

	}

	public static Lancamento lerCampos() {
		lanc = new Lancamento();
		lanc.setCodiPessoa(txtF04.getText());
		lanc.setCodiCondPag(txtF06.getText());
		lanc.setCodiConta(cmbContas.getSelectedItem().toString());
		lanc.setValor(Float.parseFloat(txtF08.getText()));

		return lanc;
	}

	public void carregarCampos(Lancamento l) {
		txtF04.setText(l.getCodiPessoa());
		txtF05.setText(l.getCodiPedido());
		txtF06.setText(l.getCodiCondPag());
		txtF07.setText(String.valueOf(l.getDataHoraLancamento()));
		txtF08.setText(String.valueOf(l.getValor()));
		cmbCCusto.setSelectedItem(contConta.nomeCentCustCodi(l.getCodiConta()));
		cmbContas.setSelectedItem(contConta.nomeContaCodigo(l.getCodiConta()));

	}
	public static void habilitaEdicao() {

		txtF04.setEditable(true);
		txtF05.setEditable(true);
		txtF06.setEditable(true);
		cmbContas.setEnabled(true);
		cmbCCusto.setEnabled(true);
	}
	public static void habilitaNovo() {
		limparCampos();

		txtF02.setText("0");
		txtF04.setEditable(true);
		txtF04.grabFocus();
		txtF05.setEditable(true);
		txtF06.setEditable(true);
		txtF06.setEditable(true);
		txtF07.setEditable(false);
		txtF08.setEditable(true);
		cmbCCusto.setEnabled(true);
		cmbContas.setEnabled(true);

	}
	// TODO Limpar campos
	public static void limparCampos() {

		txtF02.setText(null);
		txtF03.setText(null);
		txtF04.setText(null);
		txtF05.setText(null);
		txtF06.setText(null);
		txtF06.setText(null);
		txtF07.setText(null);
		txtF08.setText(null);
		cmbCCusto.setSelectedItem(0);
		cmbContas.setSelectedItem(0);

	}

}
