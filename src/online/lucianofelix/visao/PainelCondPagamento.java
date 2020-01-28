package online.lucianofelix.visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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

import online.lucianofelix.beans.CondPagamento;
import online.lucianofelix.controle.ControlaCondPagamento;
import online.lucianofelix.controle.ControlaListaCondPag;
import online.lucianofelix.dao.DAOCondPagamento;
import online.lucianofelix.visao.FrameInicial.ControlaBotoes;

public class PainelCondPagamento extends JPanel {

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

	private static JTextField txtF02;
	private static JTextField txtF03;
	private static JTextField txtF04;
	private static JTextField txtF05;
	private static JTextField txtF06;
	private static JTextField txtF07;
	private static JTextField txtF08;
	private static JTextField txtF09;
	private static JTextField txtF10;
	private static JScrollPane scrP01;
	private static JScrollPane scrP02;
	private static JComboBox<String> cmbContaLanc;

	private JPanel pnlGrid;
	private JSplitPane sppImagem;
	private JSplitPane sppPrincipal;
	private JSplitPane sppSuperior;
	private JScrollPane scrImagem;
	private JPanel pnlInferior;
	private JTabbedPane tabVisualiza;
	private static JTable tbl01;
	private static JTable tbl02;

	int tam;
	private static ControlaCondPagamento contCondPag;
	private static DAOCondPagamento daoCondPag;
	private List<CondPagamento> listCondPag;

	private static JButton btnNovo;
	private static JButton btnEditar;
	private static JButton btnCancelar;
	private static ControlaListaCondPag controledaLista;
	private static CondPagamento condPag;

	/*
	 * private JRadioButton jrbEditarSim; private JRadioButton jrbEditarNao;
	 * private ButtonGroup grpRadio;
	 */

	public PainelCondPagamento(String nome) {

		UIManager.put("TextField.font",
				new Font("Times New Roman", Font.BOLD, 14));
		UIManager.put("Label.font", new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Button.font",
				new Font("Times New Roman", Font.BOLD, 12));

		contCondPag = new ControlaCondPagamento();
		daoCondPag = new DAOCondPagamento();

		setLayout(null);

		// TODO Configuração dos Labels e text fields

		lbl01 = new JLabel("<html>Condição de<br>   Pagamento</html>");
		lbl01.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lbl02 = new JLabel("Seq.:");
		txtF02 = new JTextField();
		lbl03 = new JLabel("Código :");
		txtF03 = new JTextField(0);
		lbl04 = new JLabel("Parcelas: ");
		txtF04 = new JTextField();
		lbl05 = new JLabel("Descrição: ");
		txtF05 = new JTextField();
		lbl06 = new JLabel("Juros 1: ");
		txtF06 = new JTextField();
		lbl07 = new JLabel("Juros 2: ");
		txtF07 = new JTextField();
		lbl08 = new JLabel("Preço: ");
		txtF08 = new JTextField();

		tbl01 = new JTable();
		scrP01 = new JScrollPane();
		scrP01.setViewportView(tbl01);
		tbl02 = new JTable();
		scrP02 = new JScrollPane();
		scrP02.setViewportView(tbl02);
		cmbContaLanc = new JComboBox<String>();
		cmbContaLanc.addItem("Conta Lançamento");

		tabVisualiza = new JTabbedPane();
		tabVisualiza.addTab("Tabela 1", scrP01);
		tabVisualiza.add("Tabela 2", scrP02);

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
		pnlGrid.add(lbl08);
		pnlGrid.add(txtF08);
		pnlGrid.add(cmbContaLanc);

		listCondPag = contCondPag.pesqNomeArray(nome);
		tam = listCondPag.size();
		tam--;
		if (tam < 0) {
			FrameInicial.setPainelVisualiza(null);
			FrameInicial.getScrVisualiza()
					.setViewportView(FrameInicial.getPainelVisualiza());
		} else {
			controledaLista = new ControlaListaCondPag(listCondPag);
			condPag = controledaLista.first();
			carregarCampos(condPag);
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

	public static void cancelar() {
		btnCancelar.doClick();

	}

	public static void irParaPoicao(int posicao) {
		controledaLista.setCurrentPosition(posicao);
		condPag = controledaLista.getAt(posicao);
		carregarCampos(condPag);
	}

	public static void habilitaEdicao() {
		txtF03.setEditable(false);
		txtF04.setEditable(true);
		txtF05.setEditable(true);
		txtF07.setEditable(true);
		txtF06.setEditable(true);
		txtF08.setEditable(true);

	}

	// TODO Habilitar novo
	public static void habilitaNovo() {
		limparCampos();
		String codigo = contCondPag.criaCodigo();
		try {
			daoCondPag.reservaCodigo(codigo);
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null,
					"Erro ao reservar código /n" + e.getMessage());
			e.printStackTrace();
		}

		txtF03.setText(codigo);
		txtF03.setEditable(false);
		txtF04.setEditable(true);
		txtF04.grabFocus();
		txtF05.setEditable(true);
		txtF07.setEditable(true);
		txtF06.setEditable(true);
		txtF08.setEditable(true);
		contCondPag.funcaoCancelarNovo();

	}

	// TODO Ler Campos
	public static CondPagamento lerCampos() {
		condPag = new CondPagamento();
		if (!txtF03.getText().equals("") & !txtF03.getText().equals(null)) {
			condPag.setCodiCondPag(txtF03.getText());
		}
		if (!txtF02.getText().equals("") & !txtF02.equals(null)) {
			condPag.setSeq_condPagamento(Integer.parseInt(txtF02.getText()));
		}
		if (!txtF04.getText().equals("") & !txtF04.equals(null)) {
			condPag.setQuantParcelas(Integer.parseInt(txtF04.getText().trim()));
		}
		condPag.setNomeCondicao(txtF05.getText());
		return condPag;
	}

	public static void limparCampos() {
		txtF02.setText(null);
		txtF03.setText(null);
		txtF04.setText(null);
		txtF05.setText(null);
		txtF07.setText(null);
		txtF06.setText(null);
		txtF08.setText(null);

	}

	public static void carregarCampos(CondPagamento condPag) {
		ControlaBotoes.desHabilitaEdicaoBotoes();
		desHabilitaEdicao();
		txtF02.setText(String.valueOf(condPag.getSeq_condPagamento()));
		txtF03.setText(condPag.getCodiCondPag());
		txtF04.setText(String.valueOf(condPag.getQuantParcelas()));
		txtF05.setText(condPag.getNomeCondicao());

	}

	public static void desHabilitaEdicao() {
		txtF02.setEditable(false);
		txtF03.setEditable(false);
		txtF04.setEditable(false);
		txtF05.setEditable(false);
		txtF07.setEditable(false);
		txtF06.setEditable(false);
		txtF08.setEditable(false);

	}

	public static void setBtnCancelar(JButton btnCancelar) {
		PainelCondPagamento.btnCancelar = btnCancelar;
	}

	public static void setBtnEditar(JButton btnEditar) {
		PainelCondPagamento.btnEditar = btnEditar;
	}

	public static void setBtnNovo(JButton btnNovo) {
		PainelCondPagamento.btnNovo = btnNovo;
	}

	public static void setTxtFNomeProd(JTextField txtFNomeProd) {
		PainelCondPagamento.txtF04 = txtFNomeProd;
	}

	public static JButton getBtnCancelar() {
		return btnCancelar;
	}

	public static JButton getBtnEditar() {
		return btnEditar;
	}

	public static JButton getBtnNovo() {
		return btnNovo;
	}

	public static JTextField getTxtFNomeProd() {
		return txtF04;
	}

	public static JTextField getTxtFCodigoCondPag() {
		return txtF03;
	}

	public static void setTxtFCodigoCondPag(JTextField txtFCodigoCondPag) {
		PainelCondPagamento.txtF03 = txtFCodigoCondPag;
	}

	public static JTextField getTxtFDescricaoCondPag() {
		return txtF05;
	}

	public static void setTxtFDescricaoCondPag(
			JTextField txtFDescricaoCondPag) {
		PainelCondPagamento.txtF05 = txtFDescricaoCondPag;
	}

	public static JTextField getTxtFQuantParcelas() {
		return txtF04;
	}

	public static void setTxtFQuantParcelas(JTextField txtFQuantParcelas) {
		PainelCondPagamento.txtF04 = txtFQuantParcelas;
	}

}
