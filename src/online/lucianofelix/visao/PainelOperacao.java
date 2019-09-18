package online.lucianofelix.visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import online.lucianofelix.beans.Ativo;
import online.lucianofelix.beans.Operacao;
import online.lucianofelix.controle.ControlaListaOperacoes;
import online.lucianofelix.controle.ControlaOperacao;
import online.lucianofelix.dao.DAOOperacao;

public class PainelOperacao extends JPanel {

	JPanel painelPrincipal;
	private JLabel lblTituloTela;
	// Labels e text fields
	private JLabel lblSeqOperacao;
	private JLabel lblCodiOperacao;
	private JLabel lblCodiAtivo;
	private JLabel lblValorPapel;
	private JLabel lblCorretagem;
	private JLabel lblTipoOper;
	private JLabel lblCodiOrd;
	private JLabel lblQtdAcoes;
	private JLabel lblStopLoss;
	private JLabel lblDataHoraExec;
	private JLabel lblTotal;
	private JLabel lblTipoSetup;
	private JLabel lblFiltroIndicador;

	private static JTextField txtFCodiOperacao;
	private static JTextField txtFSeqOperacao;
	private static JTextField txtFCodiAtivo;
	private static JTextField txtFValorPapel;
	private static JTextField txtFCorretagem;
	private static JTextField txtFTipoOper;
	private static JTextField txtFCodiOrd;
	private static JTextField txtFQtdAcoes;
	private static JTextField txtFDataHoraExec;
	private static JTextField txtFTotal;

	private static JButton btnProximo;
	private static JButton btnAnterior;
	private static JButton btnNovo;
	private static JButton btnEditar;
	private JRadioButton jrbEditarSim;
	private JRadioButton jrbEditarNao;
	private ButtonGroup grpRadio;
	private ActionListener acaoEditar;
	private ActionListener sobrescrever;

	// objetos de controle
	private static ControlaListaOperacoes controledaLista;
	private static ControlaOperacao cOp;
	private static DAOOperacao daoOp;
	private static Operacao op;
	private List<Operacao> listOp;
	int tam;

	public PainelOperacao(String nome) {
		UIManager.put("TextField.font", new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Label.font", new Font("Times New Roman", Font.BOLD, 12));
		UIManager.put("Button.font", new Font("Times New Roman", Font.BOLD, 12));

		cOp = new ControlaOperacao();
		daoOp = new DAOOperacao();
		setLayout(null);
		painelPrincipal = new JPanel();
		painelPrincipal.setBackground(Color.WHITE);
		painelPrincipal.setBorder(BorderFactory.createEtchedBorder());
		painelPrincipal.setLayout(null);
		painelPrincipal.setSize(525, 510);

		// TODO Configuração dos Labels e text fields
		lblTituloTela = new JLabel("Operação");
		lblTituloTela.setBounds(10, 0, 170, 40);
		lblTituloTela.setFont(new Font("Times New Roman", Font.BOLD, 28));

		txtFSeqOperacao = new JTextField();
		txtFSeqOperacao.setBounds(365, 60, 40, 25);

		lblCodiOperacao = new JLabel("Cod. Operacão");
		lblCodiOperacao.setBounds(170, 60, 80, 25);
		txtFCodiOperacao = new JTextField();
		txtFCodiOperacao.setBounds(245, 60, 80, 25);

		lblCodiAtivo = new JLabel("Ativo:");
		lblCodiAtivo.setBounds(10, 60, 80, 25);
		txtFCodiAtivo = new JTextField(0);
		txtFCodiAtivo.setBounds(100, 60, 60, 25);

		lblValorPapel = new JLabel("Valor Papel: ");
		lblValorPapel.setBounds(10, 90, 120, 25);
		setTxtFValorPapel(new JTextField());
		getTxtFValorPapel().setBounds(100, 90, 180, 25);

		lblCorretagem = new JLabel("Corretagem: ");
		lblCorretagem.setBounds(10, 120, 120, 25);
		setTxtFCorretagem(new JTextField());
		getTxtFCorretagem().setBounds(100, 120, 180, 25);

		lblTipoOper = new JLabel("Tipo Operação ");
		lblTipoOper.setBounds(10, 150, 120, 25);
		txtFTipoOper = new JTextField();
		txtFTipoOper.setBounds(100, 150, 180, 25);

		lblCodiOrd = new JLabel("Cod. Ordem ");
		lblCodiOrd.setBounds(10, 180, 120, 25);
		txtFCodiOrd = new JTextField();
		txtFCodiOrd.setBounds(100, 180, 180, 25);

		lblQtdAcoes = new JLabel("Qtd Ações: ");
		lblQtdAcoes.setBounds(10, 210, 120, 25);
		txtFQtdAcoes = new JTextField();
		txtFQtdAcoes.setBounds(100, 210, 180, 25);

		lblDataHoraExec = new JLabel("Hora:");
		lblDataHoraExec.setBounds(10, 240, 90, 25);
		txtFDataHoraExec = new JTextField();
		txtFDataHoraExec.setBounds(100, 240, 180, 25);

		lblTotal = new JLabel("Total:");
		lblTotal.setBounds(10, 270, 90, 25);
		txtFTotal = new JTextField();
		txtFTotal.setBounds(100, 270, 180, 25);

		lblStopLoss = new JLabel("Stop Loss ");
		lblStopLoss.setBounds(105, 590, 100, 25);

		// Posicionamento e ações dos Botões

		// Jbuttons
		btnProximo = new JButton("Próximo");
		btnProximo.setBounds(225, 420, 100, 30);
		btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(125, 420, 100, 30);

		// Actions

		btnAnterior.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Estava em: " + controledaLista.getCurrentPosition());
				if (controledaLista.getCurrentPosition() == 0) {
					controledaLista.setCurrentPosition(tam);
					op = controledaLista.getAt(tam);
					cOp.posicionarTabela(controledaLista.getCurrentPosition());
				} else {
					op = controledaLista.previous();
					cOp.posicionarTabela(controledaLista.getCurrentPosition());
				}
				System.out.println("Foi para: " + controledaLista.getCurrentPosition());
				carregarCampos(op);
				// FrameECF.atualizaTela(op);

			}
		});
		btnProximo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Estava em: " + controledaLista.getCurrentPosition());
				if (controledaLista.getCurrentPosition() < tam) {
					op = controledaLista.next();
					cOp.posicionarTabela(controledaLista.getCurrentPosition());
				} else {
					controledaLista.setCurrentPosition(0);
					op = controledaLista.getAt(0);
					cOp.posicionarTabela(controledaLista.getCurrentPosition());
				}
				carregarCampos(op);
			}
		});
		// TODO Configuraçao do Painel principal

		painelPrincipal.add(txtFSeqOperacao);
		painelPrincipal.add(lblTituloTela);
		painelPrincipal.add(lblCodiAtivo);
		painelPrincipal.add(txtFCodiAtivo);
		painelPrincipal.add(lblValorPapel);
		painelPrincipal.add(getTxtFValorPapel());
		painelPrincipal.add(lblCorretagem);
		painelPrincipal.add(getTxtFCorretagem());
		painelPrincipal.add(lblCodiOrd);
		painelPrincipal.add(txtFCodiOrd);
		painelPrincipal.add(lblTipoOper);
		painelPrincipal.add(txtFTipoOper);
		painelPrincipal.add(lblQtdAcoes);
		painelPrincipal.add(txtFQtdAcoes);
		painelPrincipal.add(lblCodiOperacao);
		painelPrincipal.add(txtFCodiOperacao);
		painelPrincipal.add(lblDataHoraExec);
		painelPrincipal.add(txtFDataHoraExec);
		painelPrincipal.add(lblTotal);
		painelPrincipal.add(txtFTotal);
		painelPrincipal.add(btnAnterior);
		painelPrincipal.add(btnProximo);
		// painelPrincipal.add(btnNovo);
		// painelPrincipal.add(btnEditar);
		// painelPrincipal.add(btnSalvar);
		// painelPrincipal.add(btnExcluir);
		// painelPrincipal.add(btnCancelar);
		// painelPrincipal.add(jrbEditarSim);z\
		// painelPrincipal.add(jrbEditarNao);

		// e finalmente...

		desHabilitaEdicao();
		listOp = daoOp.pesquisaString(nome);
		tam = listOp.size();
		tam--;
		if (tam < 0) {
			FrameInicial.setPainelVisualiza(null);
			FrameInicial.getScrVisualiza().setViewportView(FrameInicial.getPainelVisualiza());
		} else {
			controledaLista = new ControlaListaOperacoes(listOp);
			System.out.println("PaineOperacao.fimConstrutor");
			op = controledaLista.first();
			System.out.println(op.getCodiAtivo());
			carregarCampos(op);
		}
		add(painelPrincipal);
	}
	// TODO Fim constr. inicio + Habilita/Desab./Carrega/Le/Limpa Campos

	public static void irParaPoicao(int posicao) {
		controledaLista.setCurrentPosition(posicao);
		op = controledaLista.getAt(posicao);
		carregarCampos(op);
	}

	// TODO Ler Campos.
	public static Operacao lerCampos() {
		op = new Operacao();
		op.setCodiOperacao(txtFCodiOperacao.getText());
		op.setCodiAtivo(txtFCodiAtivo.getText());
		op.setSeqOperacao(Integer.parseInt(txtFSeqOperacao.getText()));
		if (!getTxtFValorPapel().getText().equals(null) & !getTxtFValorPapel().getText().equals("")
				& !txtFQtdAcoes.getText().equals(null) & !txtFQtdAcoes.getText().equals("")) {
			op.setValorPapel(Float.parseFloat(getTxtFValorPapel().getText()));
			op.setQtdPapeis(Integer.parseInt(txtFQtdAcoes.getText()));
		} else {
			JOptionPane.showMessageDialog(null, "Problemas: Verifique as informações preenchidas",
					"Erro ao Salvar. Campos com * são necessários", JOptionPane.ERROR_MESSAGE);
		}
		op.setTipoOp(txtFTipoOper.getText());
		op.setCodiOrdem(txtFCodiOrd.getText());
		return op;
	}

	// TODO Carregar Campos
	public static void carregarCampos(Operacao op) {
		System.out.println("PainelOperação.carregarCampos");
		if (!op.equals(null)) {
			txtFSeqOperacao.setText(String.valueOf(op.getSeqOperacao()));
		}
		txtFCodiOperacao.setText(op.getCodiOperacao());
		txtFCodiAtivo.setText(op.getCodiAtivo());
		getTxtFValorPapel().setText(String.valueOf(op.getValorPapel()));
		getTxtFCorretagem().setText(String.valueOf(op.getCorretagem()));
		txtFTipoOper.setText(op.getTipoOp());
		txtFCodiOrd.setText(op.getCodiOrdem());
		txtFQtdAcoes.setText(String.valueOf(op.getQtdPapeis()));
		txtFTotal.setText(String.valueOf(op.getTotal()));
	}

	// TODO Habilita Edição
	public static void habilitaEdicao() {
		txtFCodiAtivo.setEditable(false);
		getTxtFValorPapel().setEditable(true);
		getTxtFCorretagem().setEditable(true);
		txtFTipoOper.setEditable(true);
		txtFCodiOrd.setEditable(true);
		txtFQtdAcoes.setEditable(true);

	}

	// TODO Habilita novo
	public static void habilitaNovo() {
		limparCampos();
		String codigo = cOp.criaCodiOp();
		txtFCodiOperacao.setText(codigo);
		txtFCodiAtivo.setEditable(false);
		txtFCodiAtivo.setEnabled(true);
		getTxtFValorPapel().setEditable(true);
		getTxtFCorretagem().setEditable(true);
		txtFTipoOper.setEditable(true);
		txtFCodiOrd.setEditable(true);
		txtFQtdAcoes.setEditable(true);

	}

	public void refresh() {
		repaint();
	}

	// DesabilitaEdição
	public static void desHabilitaEdicao() {
		txtFSeqOperacao.setEditable(false);
		txtFCodiAtivo.setEditable(false);
		getTxtFValorPapel().setEditable(false);
		getTxtFCorretagem().setEditable(false);
		txtFTipoOper.setEditable(false);
		txtFCodiOrd.setEditable(false);
		txtFQtdAcoes.setEditable(false);

	}

	public static void limparCampos() {
		txtFCodiAtivo.setText(null);
		getTxtFValorPapel().setText(null);
		getTxtFCorretagem().setText(null);
		txtFTipoOper.setText(null);
		txtFCodiOrd.setText(null);
		txtFQtdAcoes.setText(null);
		txtFTotal.setText(null);

	}

	public static void adicionaAtvOp(Ativo ativo) {
		txtFCodiAtivo.setText(ativo.getIdNeg());

	}

	/*
	 * private JRadioButton jrbEditarSim; private JRadioButton jrbEditarNao;
	 * private ButtonGroup grpRadio;
	 */

	public static JButton getBtnNovo() {
		return btnNovo;
	}

	public static void setBtnNovo(JButton btnNovo) {
		PainelOperacao.btnNovo = btnNovo;
	}

	public static JButton getBtnEditar() {
		return btnEditar;
	}

	public static void setBtnEditar(JButton btnEditar) {
		PainelOperacao.btnEditar = btnEditar;
	}

	public static JTextField getTxtFNomeProd() {
		return getTxtFValorPapel();
	}

	public static void setTxtFNomeProd(JTextField txtFNomeProd) {
		PainelOperacao.setTxtFValorPapel(txtFNomeProd);
	}

	public static JTextField getTxtFStartCompra() {
		return getTxtFValorPapel();
	}

	public static void setTxtFStartCompra(JTextField txtFStartCompra) {
		PainelOperacao.setTxtFValorPapel(txtFStartCompra);
	}

	public static JTextField getTxtFValorPapel() {
		return txtFValorPapel;
	}

	public static void setTxtFValorPapel(JTextField txtFValorPapel) {
		PainelOperacao.txtFValorPapel = txtFValorPapel;
	}

	public static JTextField getTxtFCorretagem() {
		return txtFCorretagem;
	}

	public static void setTxtFCorretagem(JTextField txtFCorretagem) {
		PainelOperacao.txtFCorretagem = txtFCorretagem;
	}
}
