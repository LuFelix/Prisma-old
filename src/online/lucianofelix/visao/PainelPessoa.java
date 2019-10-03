package online.lucianofelix.visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import online.lucianofelix.beans.Pessoa;
import online.lucianofelix.controle.ControlaPessoa;

public class PainelPessoa extends JPanel {
	// Objetos de Controle
	private static Pessoa p;
	private static ControlaPessoa contP;

	// Labels
	private JLabel lblTituloTela;
	private JLabel lblCodiPessoa;
	private JLabel lblNome;
	private JLabel lblEmail;
	private JLabel lblCpf;
	private JLabel lblSeqPessoa;

	// Text Fields
	private static JTextField txtfNome;
	private static JTextField txtFSeqPessoa;
	private static JTextField txtFCodiPessoa;
	private static JTextField txtfCpf;
	private static JTextField txtfEmail;
	private static JComboBox<String> cmbTipoPessoa;
	private static JComboBox<String> cmbRelPessoa;

	// Paineis
	JPanel painelPrincipal;
	// Variaveis
	int result[];
	int result1[];
	private JSplitPane jspPrincipal;
	private JSplitPane sppImagem;
	private JSplitPane sppSuperior;
	private JPanel painelGrid;
	private JPanel painelMovimento;
	private static JLabel lblImagem;
	private static JTabbedPane tabVisualiza;
	private static JScrollPane scrImagem;
	private static JScrollPane scrP01;
	private static JScrollPane scrP02;
	private static JScrollPane scrP03;
	private static JScrollPane scrP04;
	private static JButton btnAdd;
	private static JButton btnDel;
	private static JTable tbl01;
	private static JTable tbl02;
	private static JTable tbl03;
	private static JTable tbl04;

	public PainelPessoa(Pessoa p) {
		UIManager.put("TextField.font",
				new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Label.font", new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Button.font",
				new Font("Times New Roman", Font.BOLD, 12));

		result = new int[9];
		result1 = new int[10];

		// JLabels e JTextFields
		lblTituloTela = new JLabel("Contato");
		lblTituloTela.setFont(new Font("Times New Roman", Font.BOLD, 28));

		lblSeqPessoa = new JLabel("Número:");
		txtFSeqPessoa = new JTextField();

		lblCodiPessoa = new JLabel("Código:");
		txtFCodiPessoa = new JTextField();
		contP = new ControlaPessoa();

		lblNome = new JLabel("Nome:");
		txtfNome = new JTextField();
		txtfNome.setCaretPosition(0);
		txtfNome.setHorizontalAlignment(JTextField.LEFT);

		lblCpf = new JLabel("CPF/CNPJ:");
		txtfCpf = new JTextField();
		txtfCpf.setColumns(15);

		lblEmail = new JLabel("E-mail:");
		txtfEmail = new JTextField();
		txtfEmail.setHorizontalAlignment(JTextField.LEFT);

		cmbTipoPessoa = new JComboBox<String>();
		cmbTipoPessoa.addItem("Tipo de Pessoa");
		cmbTipoPessoa.addItem("Jurídica");
		cmbTipoPessoa.addItem("Física");
		cmbRelPessoa = contP.carregarGrupos();
		cmbRelPessoa.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				FrameInicial.getBtnSalvar().grabFocus();

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});

		btnAdd = new JButton("Adiciona");
		btnDel = new JButton("Remove");

		lblImagem = new JLabel("Image not Found");
		scrImagem = new JScrollPane(lblImagem);
		scrImagem.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrImagem.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tbl01 = new JTable();
		scrP01 = new JScrollPane();

		tbl02 = new JTable();
		scrP02 = new JScrollPane();

		setTbl03(new JTable());
		scrP03 = new JScrollPane();

		setTbl04(new JTable());
		scrP04 = new JScrollPane();

		tabVisualiza = new JTabbedPane();
		tabVisualiza.add("Ocupações", scrP01);
		tabVisualiza.add("Contratos", scrP03);
		tabVisualiza.add("Últimos Pedidos", scrP02);
		tabVisualiza.add("Recebimentos / Pagamentos", scrP04);

		painelMovimento = new JPanel();
		painelMovimento.setBorder(BorderFactory.createEtchedBorder());
		painelMovimento.setLayout(new GridLayout());
		painelMovimento.setBackground(Color.WHITE);
		painelMovimento.add(tabVisualiza);

		painelGrid = new JPanel();
		painelGrid.setBorder(BorderFactory.createEtchedBorder());
		painelGrid.setLayout(new GridLayout(7, 2));
		painelGrid.setBackground(Color.WHITE);

		painelGrid.add(lblSeqPessoa);
		painelGrid.add(txtFSeqPessoa);
		painelGrid.add(lblCodiPessoa);
		painelGrid.add(txtFCodiPessoa);
		painelGrid.add(lblNome);
		painelGrid.add(txtfNome);
		painelGrid.add(lblCpf);
		painelGrid.add(txtfCpf);
		painelGrid.add(lblEmail);
		painelGrid.add(txtfEmail);
		painelGrid.add(cmbTipoPessoa);
		painelGrid.add(cmbRelPessoa);
		painelGrid.add(btnAdd);
		painelGrid.add(btnDel);

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
		carregarCampos(p);
		desHabilitaEdicao();

		FrameInicial.getTxtfPesquisa().grabFocus();
	}// TODO Fim do construtor
		// Controle de Campos

	public void desHabilitaEdicao() {
		cmbRelPessoa.setEnabled(false);
		cmbTipoPessoa.setEnabled(false);
		txtFSeqPessoa.setEditable(false);
		txtFCodiPessoa.setEditable(false);
		txtfNome.setEditable(false);
		txtfCpf.setEditable(false);
		txtfEmail.setEditable(false);
		btnAdd.setEnabled(false);
		btnDel.setEnabled(false);

	}

	// TODO Habilita Edição
	public static void habilitaEdicao() {
		cmbRelPessoa.setEnabled(true);
		cmbTipoPessoa.setEnabled(true);
		txtfNome.setEditable(true);
		txtfNome.grabFocus();
		txtfCpf.setEditable(true);
		txtfEmail.setEditable(true);
		btnAdd.setEnabled(true);
		btnDel.setEnabled(true);
		habilitaTbl(getTbl01());
	}
	static void habilitaTbl(JTable tabela) {
		// tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
		// tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		// tabela.getColumnModel().getColumn(2).setPreferredWidth(70);
		// tabela.getColumnModel().getColumn(3).setPreferredWidth(30);
		tabela.setShowGrid(true);
		tabela.setRowSelectionAllowed(true);
		tabela.setCellSelectionEnabled(true);
		tabela.setColumnSelectionAllowed(true);
		tabela.setEnabled(true);

	}
	static void desabilitaTbl(JTable tabela) {
		// tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
		// tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		// tabela.getColumnModel().getColumn(2).setPreferredWidth(70);
		// tabela.getColumnModel().getColumn(3).setPreferredWidth(30);
		tabela.setShowGrid(true);
		tabela.setRowSelectionAllowed(false);
		tabela.setCellSelectionEnabled(false);
		tabela.setColumnSelectionAllowed(false);
		tabela.setEnabled(false);
	}
	// TODO habilitar novo
	public static void habilitaNovo() {
		limparCampos();
		if (txtFCodiPessoa.getText().equals("")
				|| txtFCodiPessoa.getText().equals(null)) {
			txtFCodiPessoa.setText(contP.criaCodiUsuario());
		}
		txtfNome.setEditable(true);
		txtfNome.grabFocus();
		txtfCpf.setEditable(true);
		txtfEmail.setEditable(true);
		cmbRelPessoa.setEnabled(true);
		cmbTipoPessoa.setEnabled(true);
		btnAdd.setEnabled(true);
		btnDel.setEnabled(true);
	}

	// TODO le campos
	public static Pessoa lerCampos() {
		p = new Pessoa();
		if (txtFSeqPessoa.getText().equals("")) {
			p.setSeqUsuario(0);
		} else {
			p.setSeqUsuario(Integer.parseInt(txtFSeqPessoa.getText()));
		}
		p.setRelacao(contP.carregarCodigoGrupoNome(
				cmbRelPessoa.getSelectedItem().toString()));
		p.setTipoPessoa(cmbTipoPessoa.getSelectedItem().toString());
		p.setCodiPessoa(txtFCodiPessoa.getText());
		p.setNome(txtfNome.getText());
		p.setCpf(txtfCpf.getText());
		p.setEmail(txtfEmail.getText());
		return p;
	}

	public static void limparCampos() {
		cmbRelPessoa.setSelectedIndex(0);
		cmbTipoPessoa.setSelectedIndex(0);
		txtFCodiPessoa.setText(null);
		txtfNome.setText(null);
		txtfCpf.setText(null);
		txtfEmail.setText(null);
		setTbl01(null);
		setTbl02(null);
		setTbl03(null);
		scrP01.setViewportView(getTbl01());
		scrP02.setViewportView(getTbl02());
		scrP03.setViewportView(getTbl03());
	}

	public static void carregarCampos(Pessoa p) {
		if (!p.equals(null)) {
			cmbTipoPessoa.setSelectedItem(p.getTipoPessoa());
			if (p.getRelacao() != null) {
				cmbRelPessoa.setSelectedItem(
						contP.carregarNomeGrupoCodigo(p.getRelacao()));
			} else {
				cmbRelPessoa.setSelectedIndex(0);
			}
			txtFSeqPessoa.setText(String.valueOf(p.getSeqUsuario()));
			txtFCodiPessoa.setText(p.getCodiPessoa());
			txtfNome.setText(p.getNome());
			txtfCpf.setText(String.valueOf(p.getCpf()));
			txtfEmail.setText(p.getEmail());
			carregarImagem(p.getCodiPessoa());
			setTbl01(contP.carregaProfissoes(p));
			setTbl02(contP.carregaUltPed(p));
			desabilitaTbl(getTbl01());
			desabilitaTbl(getTbl02());
			scrP01.setViewportView(getTbl01());
			scrP02.setViewportView(getTbl02());

		} else {

		}
	}
	void limpaBtnDet() {
		for (ActionListener act : btnAdd.getActionListeners()) {
			btnAdd.removeActionListener(act);
		}
		for (ActionListener act : btnDel.getActionListeners()) {
			btnDel.removeActionListener(act);
		}

	}

	void funcaoOcupacao() {
		limpaBtnDet();
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contP.adicionaOcup(lerCampos());
				habilitaTbl(getTbl01());

			}
		});

		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contP.apagaOcup();

			}
		});

	}

	public static void carregarImagem(String codiPessoa) {
		lblImagem = new JLabel(new ImageIcon(
				"C:\\SIMPRO\\img\\common\\" + "javinha4" + ".jpg "));
		scrImagem.setViewportView(lblImagem);
	}

	public static JTextField getTxtfNome() {
		return txtfNome;
	}

	public static void setTxtfNome(JTextField txtfNome) {
		PainelPessoa.txtfNome = txtfNome;
	}

	// TODO Validar CPF
	// private boolean validarcpf(String cpf) {
	// int soma = 0;
	// int soma1 = 0;
	// int resto = 0;
	// int resto1 = 0;
	// int dv = 0;
	// int dv1 = 0;
	// int mult[] = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
	// for (int i = 0; i < 9; i++) {
	// result[i] = mult[i] * Integer.parseInt(cpf.substring(i, i + 1));
	// soma = soma + result[i];
	// System.out.println(result[i] + "soma:" + soma);
	//
	// }
	// // Primeiro digito verificador efetuado.
	// resto = soma % 11;
	//
	// if (resto < 2) {
	// dv = 0;
	// System.out.println(dv);
	// } else {
	// dv = 11 - resto;
	// System.out.println(dv);
	// }
	// // Segundo digito verificador
	// int mult1[] = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	// for (int i = 0; i < 10; i++) {
	// result1[i] = mult1[i] * Integer.parseInt(cpf.substring(i, i + 1));
	// soma1 = soma1 + result1[i];
	// System.out.println(result1[i] + "soma:" + soma1);
	//
	// }
	// resto1 = soma1 % 11;
	// if (resto1 < 2) {
	// dv1 = 0;
	// System.out.println(dv1);
	//
	// } else {
	// dv1 = 11 - resto1;
	// System.out.println(dv1);
	// }
	//
	// if (dv == Integer.parseInt(cpf.substring(9, 10)) & dv1 ==
	// Integer.parseInt(cpf.substring(10, 11))) {
	// return true;
	// } else {
	// JOptionPane.showMessageDialog(null, "Favor verificar CPF ", "CPF
	// Inválido!", JOptionPane.ERROR_MESSAGE);
	// return false;
	// }
	//
	// return true;
	// }
	public static JTable getTbl01() {
		return tbl01;
	}

	public static void setTbl01(JTable tbl01) {
		PainelPessoa.tbl01 = tbl01;
	}

	public static JTable getTbl02() {
		return tbl02;
	}

	public static void setTbl02(JTable tbl02) {
		PainelPessoa.tbl02 = tbl02;
	}

	public static JTable getTbl03() {
		return tbl03;
	}

	public static void setTbl03(JTable tbl03) {
		PainelPessoa.tbl03 = tbl03;
	}

	public static JScrollPane getScrP04() {
		return scrP04;
	}

	public static void setScrP04(JScrollPane scrP04) {
		PainelPessoa.scrP04 = scrP04;
	}

	public static JTable getTbl04() {
		return tbl04;
	}

	public static void setTbl04(JTable tbl04) {
		PainelPessoa.tbl04 = tbl04;
	}
}
