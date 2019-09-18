package online.lucianofelix.visao;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public abstract class PainelObjetos extends JPanel {
	JPanel painelPrincipal;

	private JLabel lblTituloTela;

	public JLabel getLblTituloTela() {
		return lblTituloTela;
	}

	public void setLblTituloTela(JLabel lblTituloTela) {
		this.lblTituloTela = lblTituloTela;
	}

	private JLabel lblSequencia;
	private JLabel lblCodigo;
	private JLabel lblNomeGrupo;
	private JLabel lblDescricao;

	private static JTextField txtFCodigo;
	private static JTextField txtFNome;
	private static JTextField txtFDescricao;
	private static JTextField txtFSequencia;
	private static Object objeto;

	public PainelObjetos(Object objeto) {
		UIManager.put("TextField.font", new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Label.font", new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Button.font", new Font("Times New Roman", Font.BOLD, 12));
		setLayout(null);
		painelPrincipal = new JPanel();
		painelPrincipal.setBorder(BorderFactory.createEtchedBorder());
		painelPrincipal.setLayout(null);
		painelPrincipal.setSize(525, 510);

		lblTituloTela = new JLabel();
		lblTituloTela.setBounds(10, 0, 150, 40);
		lblTituloTela.setFont(new Font("Times New Roman", Font.BOLD, 28));

		lblCodigo = new JLabel("Código :");
		lblCodigo.setBounds(10, 60, 120, 25);
		txtFCodigo = new JTextField(0);
		txtFCodigo.setBounds(110, 60, 180, 25);

		txtFSequencia = new JTextField();
		txtFSequencia.setBounds(295, 60, 40, 25);

		lblNomeGrupo = new JLabel("Nome: ");
		lblNomeGrupo.setBounds(10, 90, 120, 25);
		txtFNome = new JTextField();
		txtFNome.setBounds(110, 90, 180, 25);

		lblDescricao = new JLabel("Descricao : ");
		lblDescricao.setBounds(10, 120, 120, 25);
		txtFDescricao = new JTextField();
		txtFDescricao.setBounds(110, 120, 180, 25);

		painelPrincipal.add(lblTituloTela);
		painelPrincipal.add(lblCodigo);
		painelPrincipal.add(txtFCodigo);
		painelPrincipal.add(lblNomeGrupo);
		painelPrincipal.add(txtFNome);
		painelPrincipal.add(lblDescricao);
		painelPrincipal.add(txtFDescricao);
		painelPrincipal.add(txtFSequencia);
		painelPrincipal.setBackground(Color.WHITE);

		desHabilitaEdicao();
		if (!objeto.equals(null)) {
			carregarCampos(objeto);
		} else {
			FrameInicial.setPainelVisualiza(null);
			FrameInicial.getScrVisualiza().setViewportView(FrameInicial.getPainelVisualiza());
		}

		add(painelPrincipal);

	}

	public abstract Object lerCampos();

	public abstract void carregarCampos(Object objeto);

	// TODO Habilitar Edição
	public static void habilitaEdicao() {
		txtFSequencia.setEditable(false);
		txtFCodigo.setEditable(false);
		txtFNome.setEditable(true);
		txtFDescricao.setEditable(true);

	}

	public static void desHabilitaEdicao() {
		txtFSequencia.setEditable(false);
		txtFCodigo.setEditable(false);
		txtFNome.setEditable(false);
		txtFDescricao.setEditable(false);
	}

	// TODO Habilita novo
	public static void habilitaNovo(String codigo) {
		limparCampos();
		txtFSequencia.setText("0");
		txtFCodigo.setEditable(false);
		txtFCodigo.setFocusable(false);
		txtFCodigo.setText(codigo);
		txtFNome.setEditable(true);
		txtFNome.grabFocus();
		txtFDescricao.setEditable(true);

	}

	// TODO Limpar campos
	public static void limparCampos() {
		txtFSequencia.setText(null);
		txtFCodigo.setText(null);
		txtFNome.setText(null);
		txtFDescricao.setText(null);
	}

	public static JTextField getTxtFCodigo() {
		return txtFCodigo;
	}

	public static void setTxtFCodigo(JTextField txtFCodigo) {
		PainelObjetos.txtFCodigo = txtFCodigo;
	}

	public static JTextField getTxtFNome() {
		return txtFNome;
	}

	public static void setTxtFNome(JTextField txtFNome) {
		PainelObjetos.txtFNome = txtFNome;
	}

	public static JTextField getTxtFDescricao() {
		return txtFDescricao;
	}

	public static void setTxtFDescricao(JTextField txtFDescricao) {
		PainelObjetos.txtFDescricao = txtFDescricao;
	}

	public static JTextField getTxtFSequencia() {
		return txtFSequencia;
	}

	public static void setTxtFSequencia(JTextField txtFSequencia) {
		PainelObjetos.txtFSequencia = txtFSequencia;
	}

}
