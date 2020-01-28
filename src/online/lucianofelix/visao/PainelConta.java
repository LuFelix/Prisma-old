package online.lucianofelix.visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
import online.lucianofelix.controle.ControlaCentroCusto;
import online.lucianofelix.controle.ControlaConta;
import online.lucianofelix.controle.ControlaListaConta;
import online.lucianofelix.visao.FrameInicial.ControlaBotoes;

public class PainelConta extends JPanel {

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
	private JSplitPane sppImagem;
	private JSplitPane sppPrincipal;
	private JSplitPane sppSuperior;
	private JPanel pnlInferior;
	private JPanel pnlGrid;
	private JTabbedPane tabVisualiza;

	private static JComboBox<String> cmbTipoConta;
	private static JComboBox<String> cmbCentroCusto;

	private static JTable tbl01;
	private static JTable tbl02;
	private static DefaultTableModel modeloTabelaEntra;
	private static DefaultTableModel modeloTabelaSai;

	// objetos de controle

	private static ControlaListaConta controledaLista;
	private static ControlaConta contConta;
	private static ControlaCentroCusto contCentroCusto;
	private static Conta conta;
	private List<Conta> listConta;
	private static List<CentroCusto> listCentroCusto;
	private static CentroCusto centroCusto;
	// TODO Construtor

	public PainelConta() {
		UIManager.put("TextField.font",
				new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Label.font", new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Button.font",
				new Font("Times New Roman", Font.BOLD, 12));
		// Controle
		contConta = new ControlaConta();
		contCentroCusto = new ControlaCentroCusto();
		// Dados

		inciaComponentes();
		desHabilitaEdicao();

	}

	void inciaComponentes() {

		lbl01 = new JLabel("Conta");
		lbl01.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lbl02 = new JLabel("Seq.:");
		txtF02 = new JTextField();
		lbl03 = new JLabel("Código :");
		txtF03 = new JTextField(0);
		lbl04 = new JLabel("Nome da Conta: ");
		txtF04 = new JTextField();
		lbl05 = new JLabel("Descrição: ");
		txtF05 = new JTextField();
		lbl06 = new JLabel("Titular: ");
		txtF06 = new JTextField();
		lbl07 = new JLabel("Agência: ");
		txtF07 = new JTextField();
		lbl08 = new JLabel("Núm. Conta|Cartão:");
		txtF08 = new JTextField();
		lbl09 = new JLabel("Banco:");
		txtF09 = new JTextField();
		lbl10 = new JLabel("Saldo:");
		lbl10.setFont(new Font("Times New Roman", Font.BOLD, 22));
		txtF10 = new JTextField();
		txtF10.setHorizontalAlignment(JTextField.RIGHT);
		txtF10.setFont(new Font("Times New Roman", Font.BOLD, 18));

		cmbCentroCusto = contCentroCusto.cmbCentrosCusto();

		cmbTipoConta = new JComboBox<String>();
		cmbTipoConta.addItem("Plano de Contas");
		cmbTipoConta.addItem("Despesa");
		cmbTipoConta.addItem("Receita");
		cmbTipoConta.setSelectedIndex(0);
		cmbTipoConta.setToolTipText("Selecione o plano de contas para a conta");
		cmbTipoConta.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				FrameInicial.getBtnSalvar().grabFocus();

			}

			@Override
			public void focusGained(FocusEvent e) {

			}
		});

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
				habilitaTabelaMovimentos(lerCampos());
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
		pnlGrid.setLayout(new GridLayout(10, 2));
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
		pnlGrid.add(lbl10);
		pnlGrid.add(txtF10);

		pnlGrid.add(cmbCentroCusto);
		pnlGrid.add(cmbTipoConta);

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

	// TODO Ler Campos.

	public static Conta lerCampos() {
		conta = new Conta();
		if (!txtF02.getText().equals("") & !txtF02.getText().equals(null)) {
			conta.setSeqConta(Integer.parseInt(txtF02.getText()));
		}
		if (!txtF03.getText().equals("") & !txtF03.getText().equals(null)) {
			conta.setCodiConta(txtF03.getText());
		} else {
			conta.setCodiConta(contConta.criaCodigo());
		}
		if (!txtF04.getText().equals(null) & !txtF04.getText().equals("")) {
			conta.setNomeConta(txtF04.getText());
		} else {
			erroLeitura();
			return null;
		}
		conta.setDescConta(txtF05.getText());
		if (!txtF06.getText().equals(null) & !txtF06.getText().equals("")) {
			conta.setTiular(txtF06.getText());
		} else {
			System.out.println("Erro titular");
			erroLeitura();
			return null;
		}
		conta.setAgencia(txtF07.getText());
		conta.setConta(txtF08.getText());
		conta.setBanco(txtF09.getText());
		if (!cmbTipoConta.getSelectedItem().toString()
				.equals("Plano de Contas")) {
			conta.setTipoConta(cmbTipoConta.getSelectedItem().toString());
		} else {
			System.out.println("Erro tipo");
			erroLeitura();
			return null;
		}
		if (!cmbCentroCusto.getSelectedItem().toString()
				.equals("Centro de Custo")) {
			centroCusto = contCentroCusto
					.buscaNome(cmbCentroCusto.getSelectedItem().toString());
			conta.setCentroCusto(centroCusto.getCodiCentroCusto());
		} else {
			erroLeitura();
			return null;
		}
		return conta;
	}

	// TODO Carregar campos
	public static void carregarCampos(Conta c) {
		limparCampos();
		desHabilitaEdicao();
		ControlaBotoes.desHabilitaEdicaoBotoes();
		if (!c.equals(null)) {
			txtF02.setText(String.valueOf(c.getSeqConta()));
			txtF03.setText(c.getCodiConta());
			txtF04.setText(c.getNomeConta());
			txtF05.setText(c.getDescConta());
			txtF06.setText(c.getTiular());
			txtF07.setText(c.getAgencia());
			txtF08.setText(c.getConta());
			txtF09.setText(c.getBanco());
			txtF10.setText(String.valueOf(contConta.consultaSaldo(c)));
			cmbTipoConta.setSelectedItem(c.getTipoConta());
			cmbCentroCusto.setSelectedItem(
					contConta.nomeCentCustCodi(c.getCentroCusto()));
			habilitaTabelaMovimentos(c);

		}

	}

	private void desabilitaTabela() {
		setTabelaEntradas(null);
		setTabelaSaidas(null);
		scrP01.setViewportView(tbl01);
		scrP02.setViewportView(tbl02);

	}

	private static void erroLeitura() {
		JOptionPane.showMessageDialog(null,
				"Problemas: Verifique as informações preenchidas",
				"Erro ao Salvar. Campos com * são necessários",
				JOptionPane.ERROR_MESSAGE);
	}

	// TODO Habilitar tabela de movimentos
	public static void habilitaTabelaMovimentos(Conta c) {
		tbl01 = contConta.tblEntradas(c);
		tbl02 = new JTable();
		getScrEntradas().setViewportView(tbl01);
		getScrSaidas().setViewportView(tbl02);
	}

	// TODO Desabilita Edição
	public static void desHabilitaEdicao() {

		txtF02.setEditable(false);
		txtF03.setEditable(false);
		txtF04.setEditable(false);
		txtF05.setEditable(false);
		txtF06.setEditable(false);
		txtF06.setEditable(false);
		txtF07.setEditable(false);
		txtF08.setEditable(false);

		cmbTipoConta.setEnabled(false);
		cmbCentroCusto.setEnabled(false);

	}

	// TODO Habilitar Edição
	public static void habilitaEdicao() {

		txtF03.setEditable(false);
		txtF03.setFocusable(false);
		txtF04.setEditable(true);
		txtF04.grabFocus();
		txtF05.setEditable(true);
		txtF06.setEditable(true);
		txtF06.setEditable(true);
		txtF07.setEditable(true);
		txtF08.setEditable(true);

		cmbTipoConta.setEnabled(true);
		cmbCentroCusto.setEnabled(true);

	}

	// TODO Habilita novo
	public static void habilitaNovo() {
		limparCampos();

		txtF02.setText("0");
		txtF03.setEditable(false);
		txtF03.setFocusable(false);
		txtF03.setText(contConta.criaCodigo());
		txtF04.setEditable(true);
		txtF04.grabFocus();
		txtF05.setEditable(true);
		txtF06.setEditable(true);
		txtF06.setEditable(true);
		txtF07.setEditable(true);
		txtF08.setEditable(true);

		cmbTipoConta.setEnabled(true);
		cmbCentroCusto.setEnabled(true);

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
		cmbTipoConta.setSelectedIndex(0);
		cmbCentroCusto.setSelectedItem(0);
	}

	public static JTable getTabelaEntradas() {
		return tbl01;
	}

	public static void setTabelaEntradas(JTable tabelaEntradas) {
		PainelConta.tbl01 = tabelaEntradas;
	}

	public static JScrollPane getScrEntradas() {
		return scrP01;
	}

	public static void setScrEntradas(JScrollPane scrEntradas) {
		PainelConta.scrP01 = scrEntradas;
	}

	public static JScrollPane getScrSaidas() {
		return scrP02;
	}

	public static void setScrSaidas(JScrollPane scrSaidas) {
		PainelConta.scrP02 = scrSaidas;
	}

	public static JTable getTabelaSaidas() {
		return tbl02;
	}

	public static void setTabelaSaidas(JTable tabelaSaidas) {
		PainelConta.tbl02 = tabelaSaidas;
	}

	public static JTextField getTxtFNomeConta() {
		return txtF04;
	}

	public static void setTxtFNomeConta(JTextField txtFNomeConta) {
		PainelConta.txtF04 = txtFNomeConta;
	}

}
