package online.lucianofelix.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
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
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
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
import online.lucianofelix.controle.ControlaLancamento;
import online.lucianofelix.controle.ControlaPessoa;
import online.lucianofelix.tableModels.commom.TableModelBaixas;
import online.lucianofelix.util.JTextFieldNumeros;

public class PainelLancamento extends JPanel {

	private JLabel lblImagem;
	private JLabel lbl01;
	private JLabel lblSequencia;
	private JLabel lblCodigo;
	private JLabel lblTitular;
	private JLabel lblTipDoc;
	private JLabel lblTitulo;
	private JLabel lblCondPag;
	private JLabel lblDTVenc;
	private JLabel lblDTRegis;
	private JLabel lblDTRec;
	private JLabel lblTotTitulo;
	private JLabel lblTotBaixas;
	private JLabel lblResta;

	private JCheckBox chkBaixa;
	private static JButton btnBaixa;
	private static JButton btnGeraTit;

	private static JTextField txtFSequencia;
	private static JTextField txtFCodigo;
	private static JTextField txtFTipDoc;
	private static JTextField txtFTitular;
	private static JTextField txtFTitulo;
	private static JTextField txtFCondPag;
	private static JTextField txtFTotTitulo;
	private static JTextField txtFTotBaixas;
	private static JTextField txtFResta;

	private static JDatePicker dtPkteste;
	private static JDatePicker dtPkVenc;
	private static JDatePicker dtPkRec;
	private static JDatePicker dtPkRegis;

	private static JComboBox<String> cmbCCusto;
	private static JComboBox<String> cmbContas;
	private static JComboBox<String> cmbTipDoc;
	static List<Conta> listContas;
	static ControlaConta contConta;
	static ControlaCentroCusto contCCusto;
	static ControlaPessoa contPess;
	static ControlaCondPagamento contCdPag;
	static ControlaLancamento contLanc;

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
	private JPanel pnlTitulo;
	private JTabbedPane tabVisualiza;

	private static Lancamento lanc;

	public PainelLancamento(Lancamento l) {
		this.lanc = l;
		contConta = new ControlaConta();
		contCCusto = new ControlaCentroCusto();
		contPess = new ControlaPessoa();
		contCdPag = new ControlaCondPagamento();
		contLanc = new ControlaLancamento();
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

		lblTipDoc = new JLabel("Tipo de Documento:");
		lblTipDoc.setFont(new Font("Times New Roman", Font.BOLD, 18));
		cmbTipDoc = new JComboBox<String>();

		lblTitular = new JLabel("Titular:");
		lblTitular.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtFTitular = new JTextField();
		txtFTitular.setEditable(false);
		txtFTitular.setFocusable(false);
		txtFTitular.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				FrameInicial.pesqUsuarioAddPLanc();
			}
		});

		lblTitulo = new JLabel("Título:");
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 18));
		pnlTitulo = new JPanel(new BorderLayout());
		txtFTitulo = new JTextField();
		btnGeraTit = new JButton("Gerar");
		pnlTitulo.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		pnlTitulo.setBackground(Color.GREEN);
		pnlTitulo.add(txtFTitulo, BorderLayout.CENTER);
		pnlTitulo.add(btnGeraTit, BorderLayout.EAST);

		lblCondPag = new JLabel("Condição Pagamento:");
		lblCondPag.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtFCondPag = new JTextField();
		txtFCondPag.setEditable(false);
		txtFCondPag.setFocusable(false);
		txtFCondPag.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				FrameInicial.pesqCondPagAddPLanc();
			}
		});

		dtPkteste = new JDatePicker();
		dtPkteste.getModel().setValue(null);
		dtPkteste.getModel().setSelected(true);

		lblDTRegis = new JLabel("Data do Registro:");
		lblDTRegis.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblDTRegis.setForeground(Color.RED);
		dtPkRegis = new JDatePicker();
		dtPkRegis.setFont(new Font("Times New Roman", Font.BOLD, 20));
		dtPkRegis.setTextEditable(false);

		lblDTVenc = new JLabel("Data do Vencimento:");
		lblDTVenc.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblDTVenc.setForeground(Color.RED);
		dtPkVenc = new JDatePicker();
		dtPkVenc.setFont(new Font("Times New Roman", Font.BOLD, 20));
		dtPkVenc.setTextEditable(false);

		// lblDTRec = new JLabel("Data da Baixa");
		// lblDTRec.setFont(new Font("Times New Roman", Font.BOLD, 18));
		// lblDTRec.setForeground(Color.RED);
		// dtPkRec = new JDatePicker();

		lblTotTitulo = new JLabel("Total do Título:");
		lblTotTitulo.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtFTotTitulo = new JTextFieldNumeros();
		txtFTotTitulo.setHorizontalAlignment(JTextField.RIGHT);
		txtFTotTitulo.setForeground(Color.RED);
		txtFTotTitulo.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lblTotBaixas = new JLabel("Total de Baixas:");
		lblTotBaixas.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtFTotBaixas = new JTextFieldNumeros();
		txtFTotBaixas.setText("0");
		txtFTotBaixas.setHorizontalAlignment(JTextField.RIGHT);
		txtFTotBaixas.setForeground(Color.RED);
		txtFTotBaixas.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtFTotBaixas.setEditable(false);
		txtFTotBaixas.setFocusable(false);

		lblResta = new JLabel("Resta:");
		lblResta.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtFResta = new JTextFieldNumeros();
		txtFResta.setText("0");
		txtFResta.setHorizontalAlignment(JTextField.RIGHT);
		txtFResta.setForeground(Color.RED);
		txtFResta.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtFResta.setEditable(false);
		txtFResta.setFocusable(false);

		btnBaixa = new JButton("Aplicar Baixa");
		btnBaixa.setForeground(Color.RED);
		btnBaixa.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnBaixa.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				FrameInicial.getBtnSalvar().grabFocus();
			}

		});

		btnBaixa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contLanc.adicionaBaixaTitPedido(lanc);
				carregarCampos(lanc);

			}
		});

		tbl01 = new JTable();
		scrP01 = new JScrollPane();
		scrP01.setViewportView(tbl01);

		tbl02 = new JTable();
		scrP02 = new JScrollPane();
		scrP02.setViewportView(tbl02);

		tabVisualiza = new JTabbedPane();
		tabVisualiza.addTab("Baixas", scrP01);
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
		pnlGrid.setLayout(new GridLayout(11, 2));
		pnlGrid.setBackground(Color.WHITE);

		pnlGrid.add(cmbCCusto);
		pnlGrid.add(cmbContas);
		pnlGrid.add(lblTitular);
		pnlGrid.add(txtFTitular);
		pnlGrid.add(lblTipDoc);
		pnlGrid.add(cmbTipDoc);
		pnlGrid.add(lblTitulo);
		pnlGrid.add(pnlTitulo);
		pnlGrid.add(lblCondPag);
		pnlGrid.add(txtFCondPag);
		pnlGrid.add(lblDTRegis);
		pnlGrid.add(dtPkRegis);
		pnlGrid.add(lblDTVenc);
		pnlGrid.add(dtPkVenc);
		pnlGrid.add(lblTotTitulo);
		pnlGrid.add(txtFTotTitulo);
		pnlGrid.add(lblTotBaixas);
		pnlGrid.add(txtFTotBaixas);
		pnlGrid.add(lblResta);
		pnlGrid.add(txtFResta);

		pnlGrid.add(btnBaixa);

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
		sppPrincipal.setDividerLocation(350);
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
		txtFTotTitulo.setEditable(false);
		dtPkRegis.setEnabled(false);
		dtPkVenc.setEnabled(false);
		cmbCCusto.setEnabled(false);
		cmbContas.setEnabled(false);
		cmbTipDoc.setEditable(false);
		btnGeraTit.setEnabled(false);
		btnBaixa.setEnabled(true);

	}
	public void atualizaTblBaixas(Lancamento l) {
		System.out.println(
				">>>>>>>>>>>>>>>>  PainelLancamento Atualiza Tbl Baixas para o titulo "
						+ l.getCodiCtaReceber());
		contLanc.carregarBaixasCtaReceber(l);
		TableModelBaixas mdlTblBaixas = new TableModelBaixas(l.getListbaixas());
		setTbl01(new JTable(mdlTblBaixas));
		getTbl01().setRowSelectionAllowed(false);
		getTbl01().setCellSelectionEnabled(false);
		getTbl01().setColumnSelectionAllowed(false);
		getTbl01().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Lancamento baixa = mdlTblBaixas
						.getLancamento(getTbl01().getSelectedRow());
				editaBaixaTitReceber(baixa);
				atualizaTblBaixas(l);
			}
		});

		getTbl01().setShowGrid(true);
		scrP01.setViewportView(getTbl01());

	}
	/**
	 * Edita a baixa ao titulo
	 * 
	 * @param baixa
	 */
	public void editaBaixaTitReceber(Lancamento baixa) {
		String entrada = JOptionPane.showInputDialog("Valor:").replace(",",
				".");
		BigDecimal valor = BigDecimal.ZERO;
		if (entrada != null) {
			valor = new BigDecimal(entrada);
			baixa.setValor(valor);
			// JOptionPane.showMessageDialog(null,
			// "PainelLancamento editaBaixTitReceber(Lancamento baixa);"
			// + " baixa.getCodiCtaReceber: "
			// + baixa.getCodiCtaReceber());
			// baixa.setCodiPessoa(contPess.pesqNomeCodigo(txtFTitular.getText()));

			if (valor.compareTo(new BigDecimal(0)) <= 0 & valor != null) {
				int opcao = JOptionPane.showConfirmDialog(null,
						"Deseja mesmo remover a baixa para esse título?");
				switch (opcao) {
					case 0 :
						contLanc.removeBaixa(baixa);
						break;
					default :
						break;
				}

			} else {
				int opcao = JOptionPane.showConfirmDialog(null,
						"Deseja alterar o valor da baixa para: " + valor);
				if (opcao == 0) {
					contLanc.editaBaixaTitPedido(baixa);
				}
			}
		}
		carregarCampos(lanc);
	}

	public static Lancamento lerCampos() {
		lanc = new Lancamento();
		lanc.setCodiCtaReceber(txtFTitulo.getText());
		lanc.setCodiPessoa(contPess.pesqCodigoNome(txtFTitular.getText()));
		lanc.setCodiCondPag(contCdPag.buscaCodigoNome(txtFCondPag.getText()));
		lanc.setValor(
				new BigDecimal(txtFTotTitulo.getText().replace(",", ".")));
		if (cmbContas.getSelectedItem() != null) {
			lanc.setCodiConta(contConta
					.codigoContaNome(cmbContas.getSelectedItem().toString()));
		}
		if (dtPkVenc.getModel().getValue() != null) {
			Calendar data = (Calendar) dtPkVenc.getModel().getValue();
			lanc.setDtHrVenc(new Timestamp(data.getTimeInMillis()));
		}

		System.out.println(">>>>>>>>>>>>> Data de Vencimento no dt picker: "
				+ dtPkVenc.getModel().getValue());
		// System.out
		// .println(">>>>>>>>>>>>>>>>>>>>>>>>> Alterando Valores para:\n "
		// + " Código: " + lanc.getCodiCtaReceber() + "\n"
		// + "Pessoa: " + txtFTitular.getText() + " Código: "
		// + lanc.getCodiPessoa() + "\n" + "CMB Conta "
		// + cmbContas.getSelectedItem() + "\n" + "Codigo Conta: "
		// + lanc.getCodiConta() + "\n" + "Valor: "
		// + lanc.getValorString() + "\n" + "Data de Vencimento: "
		// + lanc.getDtHrVenc().toString() + "\n");
		return lanc;
	}

	public void carregarCampos(Lancamento l) {
		System.out.println(
				">>>>>>>>>>>>>>> PainelLancamento.carregarCampos: l.getCodiconta: ");
		if (l != null) {
			if (l.getCodiPessoa() != null & l.getCodiPessoa() != "") {
				txtFTitular.setText(contPess.pesqNomeCodigo(l.getCodiPessoa()));
			}
			txtFTitulo.setText(l.getCodiCtaReceber());
			txtFCondPag.setText(contCdPag.buscaNomeCodigo(l.getCodiCondPag()));
			txtFTotTitulo
					.setText(String.valueOf(l.getValor()).replace(".", ","));
			contLanc.carregarBaixasCtaReceber(l);
			if (!l.getListbaixas().isEmpty()) {
				txtFTotBaixas.setText(
						contLanc.totalBaixas(l).toString().replace(".", ","));
				txtFResta.setText(l.getValor().subtract(contLanc.totalBaixas(l))
						.toString().replace(".", ","));
			}
			cmbCCusto.setSelectedItem(
					contConta.nomeCentCustCodiConta(l.getCodiConta()));
			cmbContas.setSelectedItem(
					contConta.nomeContaCodigo(l.getCodiConta()));

			atualizaTblBaixas(l);
			carregarDatasLanc(l);
		}
	}
	public static void carregarDatasLanc(Lancamento l) {
		DateTime dt = new DateTime(l.getDtHrLanc());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>l.getDtHrLanc()  "
				+ l.getDtHrLanc());
		dtPkRegis.getModel().setSelected(true);
		dtPkRegis.getModel().setDate(dt.getYear(), dt.getMonthOfYear() - 1,
				dt.getDayOfMonth());

		if (l.getDtHrVenc() != null) {
			dt = new DateTime(l.getDtHrVenc());
			dtPkVenc.getModel().setSelected(true);
			dtPkVenc.getModel().setDate(dt.getYear(), dt.getMonthOfYear() - 1,
					dt.getDayOfMonth());
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>l.getDtHrVenc()  "
					+ l.getDtHrVenc());
		}
		// if (l.getDtHrReceb() != null) {
		// dt = new DateTime(l.getDtHrReceb());
		// dtPkRec.getModel().setSelected(true);
		// dtPkRec.getModel().setDate(dt.getYear(), dt.getMonthOfYear() - 1,
		// dt.getDayOfMonth());
		// dtPkRec.getModel().setSelected(true);
		// System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>l.getDtHrReceb "
		// + l.getDtHrReceb());
		//
		// }

	}
	public static void habilitaEdicao() {

		txtFTitular.setFocusable(true);
		txtFCondPag.setFocusable(true);
		dtPkVenc.setEnabled(true);
		cmbContas.setEnabled(true);
		cmbCCusto.setEnabled(true);
		cmbTipDoc.setEditable(true);
	}
	public static void habilitaNovo() {
		limparCampos();

		txtFSequencia.setText("0");
		txtFTitular.setFocusable(true);
		txtFTitulo.setEditable(true);
		txtFCondPag.setFocusable(true);
		txtFCondPag.setEditable(true);
		dtPkRegis.getModel().setDate(DateTime.now().getYear(),
				DateTime.now().getMonthOfYear() - 1,
				DateTime.now().getDayOfMonth());
		dtPkRegis.getModel().setSelected(true);
		dtPkVenc.setEnabled(true);
		txtFTotTitulo.setEditable(true);
		cmbCCusto.setEnabled(true);
		cmbCCusto.grabFocus();
		cmbContas.setEnabled(true);
		btnGeraTit.setEnabled(true);
		btnBaixa.setEnabled(false);

	}
	// TODO Limpar campos
	public static void limparCampos() {

		txtFSequencia.setText(null);
		txtFCodigo.setText(null);
		txtFTitular.setText(null);
		txtFTitulo.setText(null);
		txtFCondPag.setText(null);
		txtFCondPag.setText(null);
		dtPkVenc.getModel().setValue(null);
		txtFTotTitulo.setText("");
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
	/**
	 * @return the lblDTVenc
	 */
	public JLabel getLblDTVenc() {
		return lblDTVenc;
	}
	/**
	 * @param lblDTVenc
	 *            the lblDTVenc to set
	 */
	public void setLblDTVenc(JLabel lblDTVenc) {
		this.lblDTVenc = lblDTVenc;
	}

	/**
	 * @return the tbl01
	 */
	public static JTable getTbl01() {
		return tbl01;
	}

	/**
	 * @param tbl01
	 *            the tbl01 to set
	 */
	public static void setTbl01(JTable tbl01) {
		PainelLancamento.tbl01 = tbl01;
	}

	/**
	 * @return the tbl02
	 */
	public static JTable getTbl02() {
		return tbl02;
	}

	/**
	 * @param tbl02
	 *            the tbl02 to set
	 */
	public static void setTbl02(JTable tbl02) {
		PainelLancamento.tbl02 = tbl02;
	}

}
