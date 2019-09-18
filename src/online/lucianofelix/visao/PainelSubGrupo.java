package online.lucianofelix.visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
import javax.swing.UIManager;

import online.lucianofelix.beans.GrupoSubgrupo;
import online.lucianofelix.controle.ControlaGrupoSubgrupo;
import online.lucianofelix.controle.ControlaListaGrupo;

public class PainelSubGrupo extends JPanel {
	private JPanel painelPrincipal;

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

	private static JTextField txtF02;
	private static JTextField txtF03;
	private static JTextField txtF04;
	private static JTextField txtF05;
	private static JTextField txtF06;
	private static JTextField txtF07;
	private static JTextField txtF08;
	private static JTextField txtF09;
	private static JTextField txtF10;

	private JSplitPane sppImagem;
	private static JScrollPane scrImagem;
	private JSplitPane sppPrincipal;
	private JSplitPane sppSuperior;
	private JPanel pnlInferior;
	private JPanel pnlGrid;
	private JTabbedPane tabVisualiza;

	private static JScrollPane scrP01;
	private static JScrollPane scrP02;
	private static JTable tbl01;
	private static JTable tbl02;

	private static JCheckBox chkIsroot;
	private static JComboBox<String> cmbGrupoRaiz;
	private static JComboBox<String> cmbSubGrupo;
	private static ControlaListaGrupo controledaLista;
	private static GrupoSubgrupo grupo;
	private static ControlaGrupoSubgrupo contGrupo;
	private static List<GrupoSubgrupo> listGrupo;

	public PainelSubGrupo(String str) {
		UIManager.put("TextField.font",
				new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Label.font", new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Button.font",
				new Font("Times New Roman", Font.BOLD, 12));
		setLayout(null);
		painelPrincipal = new JPanel();
		painelPrincipal.setBorder(BorderFactory.createEtchedBorder());
		painelPrincipal.setLayout(null);
		painelPrincipal.setSize(525, 510);

		contGrupo = new ControlaGrupoSubgrupo();

		lbl01 = new JLabel("Grupos");
		lbl01.setFont(new Font("Times New Roman", Font.BOLD, 28));

		lbl02 = new JLabel("Seq. :");
		txtF02 = new JTextField();

		lbl03 = new JLabel("Código :");
		txtF03 = new JTextField(0);

		lbl04 = new JLabel("Nome: ");
		txtF04 = new JTextField();

		lbl05 = new JLabel("É Raiz?");
		chkIsroot = new JCheckBox();

		lbl06 = new JLabel("Categoria Mãe ");
		cmbGrupoRaiz = contGrupo.carregarRaizes();

		lbl07 = new JLabel("Subgrupo");
		cmbSubGrupo = contGrupo.carregarSubGrupos();
		cmbSubGrupo.setSelectedItem("Sem Categoria");

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
		pnlGrid.add(lbl06);
		pnlGrid.add(cmbGrupoRaiz);
		pnlGrid.add(lbl07);
		pnlGrid.add(cmbSubGrupo);
		pnlGrid.add(lbl04);
		pnlGrid.add(txtF04);
		pnlGrid.add(lbl05);
		pnlGrid.add(chkIsroot);

		sppSuperior = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		sppSuperior.setDividerLocation(200);
		sppSuperior.setDividerSize(3);
		sppSuperior.setEnabled(false);
		sppSuperior.add(sppImagem);
		sppSuperior.add(pnlGrid);

		tbl01 = new JTable();
		scrP01 = new JScrollPane();
		scrP01.setViewportView(tbl01);

		tbl02 = new JTable();
		scrP02 = new JScrollPane();
		scrP02.setViewportView(tbl02);

		tabVisualiza = new JTabbedPane();
		tabVisualiza.addTab("Tabela 1", scrP01);
		tabVisualiza.add("Tabela 2", scrP02);

		pnlInferior = new JPanel();
		pnlInferior.setBorder(BorderFactory.createEtchedBorder());
		pnlInferior.setLayout(new GridLayout());
		pnlInferior.setBackground(Color.WHITE);
		pnlInferior.add(tabVisualiza);

		desHabilitaEdicao();
		listGrupo = contGrupo.pesqNomeArray(str);
		int tam = listGrupo.size();
		tam--;
		if (tam < 0) {
			FrameInicial.setPainelVisualiza(null);
			FrameInicial.getScrVisualiza()
					.setViewportView(FrameInicial.getPainelVisualiza());
		} else {
			controledaLista = new ControlaListaGrupo(listGrupo);
			grupo = controledaLista.first();
			carregarCampos(grupo);
		}
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
	public PainelSubGrupo(GrupoSubgrupo grupo) {
		UIManager.put("TextField.font",
				new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Label.font", new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Button.font",
				new Font("Times New Roman", Font.BOLD, 12));
		setLayout(null);
		painelPrincipal = new JPanel();
		painelPrincipal.setBorder(BorderFactory.createEtchedBorder());
		painelPrincipal.setLayout(null);
		painelPrincipal.setSize(525, 510);

		contGrupo = new ControlaGrupoSubgrupo();

		lbl01 = new JLabel("SubGrupos");
		lbl01.setFont(new Font("Times New Roman", Font.BOLD, 28));

		lbl02 = new JLabel("Seq. :");
		txtF02 = new JTextField();

		lbl03 = new JLabel("Código :");
		txtF03 = new JTextField(0);

		lbl04 = new JLabel("Nome: ");
		txtF04 = new JTextField();

		lbl05 = new JLabel("Categoria Mãe");
		lbl06 = new JLabel(AbaCadastros.getNomeNo());
		lbl07 = new JLabel("É Raíz?");
		chkIsroot = new JCheckBox();

		lbl08 = new JLabel("Mudar para o Grupo");
		cmbGrupoRaiz = contGrupo.carregarRaizes();

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
		pnlGrid.add(lbl06);
		pnlGrid.add(chkIsroot);
		pnlGrid.add(lbl07);
		pnlGrid.add(lbl08);
		pnlGrid.add(cmbGrupoRaiz);

		sppSuperior = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		sppSuperior.setDividerLocation(200);
		sppSuperior.setDividerSize(3);
		sppSuperior.setEnabled(false);
		sppSuperior.add(sppImagem);
		sppSuperior.add(pnlGrid);

		tbl01 = new JTable();
		scrP01 = new JScrollPane();
		scrP01.setViewportView(tbl01);

		tbl02 = new JTable();
		scrP02 = new JScrollPane();
		scrP02.setViewportView(tbl02);

		tabVisualiza = new JTabbedPane();
		tabVisualiza.addTab("Tabela 1", scrP01);
		tabVisualiza.add("Tabela 2", scrP02);

		pnlInferior = new JPanel();
		pnlInferior.setBorder(BorderFactory.createEtchedBorder());
		pnlInferior.setLayout(new GridLayout());
		pnlInferior.setBackground(Color.WHITE);
		pnlInferior.add(tabVisualiza);

		desHabilitaEdicao();
		carregarCampos(grupo);
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

	public static GrupoSubgrupo lerCampos() {
		System.out.println("PanelGrupoSubgrupo.lerCampos");
		grupo = new GrupoSubgrupo();
		if (!txtF02.equals("") & !txtF02.equals(null)) {
			grupo.setSeqGrupo(Integer.parseInt(txtF02.getText()));
		}
		if (!txtF03.getText().equals("") & !txtF03.getText().equals(null)) {
			grupo.setCodiGrupo(txtF03.getText());
		} else {
			grupo.setCodiGrupo(getContGrupo().criaCodigo());
		}
		if (!txtF04.getText().equals(null) & !txtF04.getText().equals("")) {
			grupo.setNomeGrupo(txtF04.getText());
		} else {
			JOptionPane.showMessageDialog(null,
					"Problemas: Verifique as informações preenchidas",
					"Erro ao Salvar. Campos com * são necessários",
					JOptionPane.ERROR_MESSAGE);
		}

		if (!chkIsroot.equals(null)) {
			grupo.setIsroot(chkIsroot.isSelected());
		}

		return grupo;
	}

	// TODO Carregar campos
	public static void carregarCampos(GrupoSubgrupo grupo) {
		if (!grupo.equals(null)) {
			txtF02.setText(String.valueOf(grupo.getSeqGrupo()));
			txtF03.setText(grupo.getCodiGrupo());
			txtF04.setText(grupo.getNomeGrupo());
			chkIsroot.setSelected(grupo.getIsroot());
			carregarImagem(grupo.getCodiGrupo());
		} else {
			limparCampos();
		}

	}
	public static void carregarImagem(String codiGrupo) {
		lblImagem = new JLabel(
				new ImageIcon("C:\\SIMPRO\\img\\order\\Grupos200x200\\"
						+ "grupo02" + ".jpg "));
		scrImagem.setViewportView(lblImagem);
	}

	public static void irParaPoicao(int posicao) {
		controledaLista.setCurrentPosition(posicao);
		grupo = controledaLista.getAt(posicao);
		carregarCampos(grupo);
	}

	// TODO Edição
	public static void habilitaEdicao() {
		txtF02.setEditable(false);
		txtF03.setEditable(false);
		txtF04.setEditable(true);
		chkIsroot.setEnabled(true);
		cmbGrupoRaiz.setEnabled(true);

	}

	public static void desHabilitaEdicao() {
		txtF02.setEditable(false);
		txtF03.setEditable(false);
		txtF04.setEditable(false);
		chkIsroot.setEnabled(false);
		cmbGrupoRaiz.setEnabled(false);

	}

	// TODO Habilita novo
	public static void habilitaNovo() {
		limparCampos();
		txtF02.setText("0");
		txtF03.setEditable(false);
		txtF03.setFocusable(false);
		txtF03.setText(contGrupo.criaCodigo());
		txtF04.setEditable(true);
		txtF04.grabFocus();
		chkIsroot.setEnabled(true);

	}

	// TODO Limpar campos
	public static void limparCampos() {
		txtF02.setText(null);
		txtF03.setText(null);
		txtF04.setText(null);
		chkIsroot.setSelected(false);
		// cmbGrupoRaiz.setSelectedIndex(0);
		// cmbSubGrupo.setSelectedIndex(0);

	}

	public static JTextField getTxtFCodigoGrupo() {
		return txtF03;
	}

	public static void setTxtFCodigoGrupo(JTextField txtFCodigoGrupo) {
		PainelSubGrupo.txtF03 = txtFCodigoGrupo;
	}

	public static JTextField getTxtFNomeGrupo() {
		return txtF04;
	}

	public static void setTxtFNomeGrupo(JTextField txtFNomeGrupo) {
		PainelSubGrupo.txtF04 = txtFNomeGrupo;
	}

	public static JTextField getTxtFNoAncora() {
		return txtF05;
	}

	public static void setTxtFNoAncora(JTextField txtFNoAncora) {
		PainelSubGrupo.txtF05 = txtFNoAncora;
	}

	public static JTextField getTxtFSeqGrupo() {
		return txtF02;
	}

	public static void setTxtFSeqGrupo(JTextField txtFSeqGrupo) {
		PainelSubGrupo.txtF02 = txtFSeqGrupo;
	}

	public static GrupoSubgrupo getGrupo() {
		return grupo;
	}

	public static void setGrupo(GrupoSubgrupo grupo) {
		PainelSubGrupo.grupo = grupo;
	}

	public static ControlaGrupoSubgrupo getContGrupo() {
		return contGrupo;
	}

	public static void setContGrupo(ControlaGrupoSubgrupo contGrupo) {
		PainelSubGrupo.contGrupo = contGrupo;
	}

}
