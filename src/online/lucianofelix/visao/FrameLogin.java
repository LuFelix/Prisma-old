package online.lucianofelix.visao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import de.wannawork.jcalendar.JCalendarComboBox;
import online.lucianofelix.beans.Pessoa;
import online.lucianofelix.controle.ControlaPessoa;

public class FrameLogin {
	// criação da tela
	private JFrame telaCadastroUsuario;
	private JPanel painel;
	// criação dos labels com a indicação do campo
	private JLabel lblDeVolta;
	private JLabel lblEmailEntrar;
	private JLabel lblSenhaEntrar;
	private JLabel lblEmailCadastrar;
	private JLabel lblConfirmaEmail;;
	private JLabel lblNome;
	private JLabel lblAbraUmaConta;
	private JLabel lblSenhaCadastrar;
	private JLabel lblConfirmaSenha;
	private JLabel lblSexo;
	private JLabel lbldataNasc;
	// Botões
	private JButton btnCriar;
	private JButton btnEntrar;
	// Objeto de data
	private JCalendarComboBox cmbDataNasc;
	// criação dos campos de texto para o usuario preencher
	private JTextField txtfNome;
	private JTextField txtfEmailEntrar;
	private JTextField txtfEmailCadastrar;
	private JTextField txtfConfirmaEmail;
	private JTextField txtfSenhaEntrar;
	private JTextField txtfSenhaCadastrar;
	private JTextField txtfConfirmaSenha;
	private String confirmaEmail;
	private String confirmaSenha;

	// criação dos botões de seleção de sexo
	private JRadioButton jrbMasculino;
	private JRadioButton jrbFeminino;
	private ButtonGroup grpSex;
	// Objetos de Controle
	private Pessoa u;
	private ControlaPessoa conU;

	public FrameLogin() {
		// try {
		// UIManager.setLookAndFeel(
		// new com.nilo.plaf.nimrod.NimRODLookAndFeel());
		// } catch (UnsupportedLookAndFeelException e1) {
		// e1.printStackTrace();
		// }

		UIManager.put("TextField.font",
				new Font("times new roman", Font.BOLD, 14));
		UIManager.put("Label.font", new Font("times new roman", Font.BOLD, 14));
		UIManager.put("Button.font",
				new Font("times new roman", Font.BOLD, 14));
		telaCadastroUsuario = new JFrame("Bem Vindo");
		telaCadastroUsuario.setBounds(500, 100, 315, 700);
		telaCadastroUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaCadastroUsuario.setLayout(null);

		// Entrar
		lblDeVolta = new JLabel("De volta?");
		lblDeVolta.setBounds(50, 10, 250, 30);
		lblDeVolta.setFont(new Font("times new roman", Font.BOLD, 24));
		lblEmailEntrar = new JLabel("E-mail:");
		lblEmailEntrar.setBounds(50, 40, 60, 30);
		txtfEmailEntrar = new JTextField();
		txtfEmailEntrar.setBounds(50, 65, 200, 30);
		lblSenhaEntrar = new JLabel("Senha:");
		lblSenhaEntrar.setBounds(50, 95, 60, 30);
		txtfSenhaEntrar = new JTextField();
		txtfSenhaEntrar.setBounds(50, 120, 200, 30);
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(90, 165, 100, 25);

		// Cadastrar
		lblAbraUmaConta = new JLabel("Inicie agora!");
		lblAbraUmaConta.setBounds(50, 200, 250, 30);
		lblAbraUmaConta.setFont(new Font("Times new roman", Font.BOLD, 24));
		lblNome = new JLabel("Nome completo:");
		lblNome.setBounds(50, 230, 140, 30);
		txtfNome = new JTextField();
		txtfNome.setBounds(50, 255, 200, 30);
		lblEmailCadastrar = new JLabel("E-mail:");
		lblEmailCadastrar.setBounds(50, 285, 60, 30);
		txtfEmailCadastrar = new JTextField();
		txtfEmailCadastrar.setBounds(50, 310, 200, 30);
		lblConfirmaEmail = new JLabel("Confirme seu e-mail:");
		lblConfirmaEmail.setBounds(50, 340, 170, 30);
		txtfConfirmaEmail = new JTextField();
		txtfConfirmaEmail.setBounds(50, 365, 200, 30);
		lblSenhaCadastrar = new JLabel("Senha:");
		lblSenhaCadastrar.setBounds(50, 400, 60, 30);
		txtfSenhaCadastrar = new JTextField();
		txtfSenhaCadastrar.setBounds(50, 425, 200, 30);
		lblConfirmaSenha = new JLabel("Confirme a senha:");
		lblConfirmaSenha.setBounds(50, 455, 160, 30);
		txtfConfirmaSenha = new JTextField();
		txtfConfirmaSenha.setBounds(50, 480, 200, 30);
		lbldataNasc = new JLabel("Nascimento:");
		lbldataNasc.setBounds(70, 515, 120, 30);
		cmbDataNasc = new JCalendarComboBox();
		cmbDataNasc.setBounds(70, 540, 150, 25);
		lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(50, 565, 200, 30);
		jrbMasculino = new JRadioButton("Masculino");
		jrbMasculino.setBounds(50, 585, 100, 30);
		jrbFeminino = new JRadioButton("Feminino");
		jrbFeminino.setBounds(150, 585, 100, 30);
		btnCriar = new JButton("Criar");
		btnCriar.setBounds(90, 615, 100, 25);
		grpSex = new ButtonGroup();
		grpSex.add(jrbMasculino);
		grpSex.add(jrbFeminino);

		// TODO Ações dos botões
		btnEntrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (leCamposLogar()) {
					telaCadastroUsuario.dispose();
					new FrameInicial();
				} else {
					JOptionPane.showMessageDialog(null,
							"Algo errado, favor conferir o preenchimento!",
							null, JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnCriar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (leCamposCadastrar()) {
					telaCadastroUsuario.dispose();
					new FrameInicial();
				} else {
					JOptionPane.showMessageDialog(null,
							"Algo errado, favor conferir o preenchimento!",
							null, JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		telaCadastroUsuario.add(lblDeVolta);
		telaCadastroUsuario.add(lblEmailEntrar);
		telaCadastroUsuario.add(txtfEmailEntrar);
		telaCadastroUsuario.add(lblSenhaEntrar);
		telaCadastroUsuario.add(txtfSenhaEntrar);
		telaCadastroUsuario.add(btnEntrar);
		telaCadastroUsuario.add(lblAbraUmaConta);
		telaCadastroUsuario.add(lblNome);
		telaCadastroUsuario.add(txtfNome);
		telaCadastroUsuario.add(lblEmailCadastrar);
		telaCadastroUsuario.add(txtfEmailCadastrar);
		telaCadastroUsuario.add(lblConfirmaEmail);
		telaCadastroUsuario.add(txtfConfirmaEmail);
		telaCadastroUsuario.add(lblSenhaCadastrar);
		telaCadastroUsuario.add(txtfSenhaCadastrar);
		telaCadastroUsuario.add(lblConfirmaSenha);
		telaCadastroUsuario.add(txtfConfirmaSenha);
		telaCadastroUsuario.add(lbldataNasc);
		telaCadastroUsuario.add(cmbDataNasc);
		telaCadastroUsuario.add(lblSexo);
		telaCadastroUsuario.add(jrbFeminino);
		telaCadastroUsuario.add(jrbMasculino);
		telaCadastroUsuario.add(btnCriar);
		telaCadastroUsuario.setVisible(true);

	}// TODO Fim do método construtor

	// TODO Limpa Campos +++++++++++

	public String getConfirmaEmail() {
		return confirmaEmail;
	}
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	public boolean leCamposCadastrar() {

		if (txtfNome.getText() != null) {
			u = new Pessoa();
			conU = new ControlaPessoa();
			u.setNome(txtfNome.getText());
			u.setEmail(txtfEmailCadastrar.getText());
			u.setSenha(txtfSenhaCadastrar.getText());
			u.setDataNasc(cmbDataNasc.getDate());
			u.setSexo(grpSex.getButtonCount());
			confirmaEmail = txtfConfirmaEmail.getText();
			confirmaSenha = txtfConfirmaSenha.getText();
			conU.cadastrar(u, getConfirmaSenha(), getConfirmaEmail());
			return true;
		} else {
			return false;
		}
	}

	// TODO Logar ++++++++++++++++++++++
	public boolean leCamposLogar() {
		u = new Pessoa();
		conU = new ControlaPessoa();
		u.setEmail(txtfEmailEntrar.getText());
		u.setSenha(txtfSenhaEntrar.getText());
		if (conU.logar(u)) {
			return true;
		} else {
			return false;
		}
	}

	// TODO Limpar campos++++++++
	public void limparCampos() {

	}

	public void setConfirmaEmail(String confirmaEmail) {
		this.confirmaEmail = confirmaEmail;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

}
