package online.lucianofelix.visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import online.lucianofelix.adapter.ConstrutorGrafico;
import online.lucianofelix.beans.Ativo;
import online.lucianofelix.beans.CotacaoAtivo;
import online.lucianofelix.controle.ControlaAtivo;
import online.lucianofelix.controle.ControlaListaAtivos;
import online.lucianofelix.dao.DAOAtivo;
import online.lucianofelix.dao.DAOCotacaoAtivo;

public class PainelAtivo extends JPanel {

	private static JTabbedPane pnlTabAnexos;
	private JPanel pnlGrid;
	private JSplitPane pnlSuperior;
	private JSplitPane painelPrincipal;

	private static JScrollPane scrpGrafico;
	private static JScrollPane scrpCotacoes;
	private static DefaultTableModel modeloTabelaCotacoes;
	private static JTable tblCotacoes;
	private static JTextField txtFIdAtivo;
	private static JTextField txtFNomeAtivo;
	private static JTextField txtFUltimoPreco;
	private static JTextField txtFPreAnterior;
	private static JTextField txtFPreMax;
	private static JTextField txtFPreMin;
	private static JButton btnNovo;
	private static JButton btnEditar;
	private static JButton btnCancelar;
	private static ControlaListaAtivos controledaLista;
	private static Ativo ativ;

	private JLabel lblTituloTela;
	// Labels e text fields
	private JLabel lblIdAtivo;

	// objetos de controle

	private JLabel lblNomeAtivo;
	private JLabel lblUltimoPreco;
	private JLabel lblPrecoAnterior;
	private JLabel lblPreMax;
	private JLabel lblPreMin;
	private JLabel lblPermiteEditar;

	private JButton btnProximo;
	private JButton btnAnterior;
	private JRadioButton jrbEditarSim;
	private JRadioButton jrbEditarNao;
	private ButtonGroup grpRadio;
	private static ControlaAtivo contAtivo;
	private static ConstrutorGrafico constGrafico;
	private static DAOCotacaoAtivo daoCota;
	private static List<CotacaoAtivo> listCotacao;
	private DAOAtivo daoAtv;
	private List<Ativo> listAtv;

	int tam;

	public PainelAtivo(String idNeg) {

		UIManager.put("TextField.font", new Font("Mukti Narrow Bold Italic", Font.BOLD, 12));
		UIManager.put("Label.font", new Font("Mukti Narrow Bold Italic", Font.BOLD, 12));
		UIManager.put("Button.font", new Font("Mukti Narrow Bold Italic", Font.BOLD, 12));

		contAtivo = new ControlaAtivo();
		daoAtv = new DAOAtivo();
		constGrafico = new ConstrutorGrafico();
		setLayout(new GridLayout());

		scrpGrafico = new JScrollPane();
		scrpGrafico.setBackground(Color.WHITE);

		scrpCotacoes = new JScrollPane();
		scrpCotacoes.setBackground(Color.WHITE);

		pnlTabAnexos = new JTabbedPane();
		pnlTabAnexos.addTab("Gráfico", scrpGrafico);
		pnlTabAnexos.addTab("Cotações", scrpCotacoes);

		// painelPrincipal.setBackground(Color.WHITE);

		// telaProduto.setContentPane(painelPrincipal);

		// TODO Configuração dos Labels e text fields

		lblTituloTela = new JLabel("   Ativo");
		lblTituloTela.setFont(new Font("Times New Roman", Font.BOLD, 28));

		lblIdAtivo = new JLabel("   Código :");
		txtFIdAtivo = new JTextField();

		lblPrecoAnterior = new JLabel("   Fec. Anterior ");
		txtFPreAnterior = new JTextField();

		lblUltimoPreco = new JLabel("   Último Neg.: ");
		txtFUltimoPreco = new JTextField();

		lblNomeAtivo = new JLabel("   Nome Res.: ");
		txtFNomeAtivo = new JTextField();

		lblPreMax = new JLabel("   Máximo: ");
		txtFPreMax = new JTextField();

		lblPreMin = new JLabel("   Mínimo: ");
		txtFPreMin = new JTextField();

		lblPermiteEditar = new JLabel("Permite Editar ");

		desHabilitaEdicao();

		painelPrincipal = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		painelPrincipal.setBorder(BorderFactory.createEtchedBorder());
		painelPrincipal.setBackground(Color.WHITE);
		painelPrincipal.setEnabled(false);
		pnlSuperior = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		pnlSuperior.setEnabled(false);
		pnlSuperior.setDividerLocation(125);
		pnlSuperior.setDividerSize(3);

		pnlGrid = new JPanel(new GridLayout(2, 5));
		pnlGrid.setBackground(Color.WHITE);
		pnlGrid.add(lblIdAtivo);
		pnlGrid.add(txtFIdAtivo);
		pnlGrid.add(lblNomeAtivo);
		pnlGrid.add(txtFNomeAtivo);
		pnlGrid.add(lblUltimoPreco);
		pnlGrid.add(txtFUltimoPreco);
		pnlGrid.add(lblPrecoAnterior);
		pnlGrid.add(txtFPreAnterior);
		pnlGrid.add(lblPreMax);
		pnlGrid.add(txtFPreMax);
		pnlGrid.add(lblPreMin);
		pnlGrid.add(txtFPreMin);

		pnlSuperior.add(lblTituloTela);
		pnlSuperior.add(pnlGrid);

		painelPrincipal.setDividerLocation(50);
		painelPrincipal.setDividerSize(3);
		painelPrincipal.add(pnlSuperior);
		painelPrincipal.add(pnlTabAnexos);
		listAtv = daoAtv.pesquisaString(idNeg);
		controledaLista = new ControlaListaAtivos(listAtv);
		tam = listAtv.size();
		tam--;
		if (tam < 0) {
			FrameInicial.setPainelVisualiza(null);
			FrameInicial.getScrVisualiza().setViewportView(FrameInicial.getPainelVisualiza());
		} else {
			controledaLista = new ControlaListaAtivos(listAtv);
			ativ = controledaLista.first();
			carregarCampos(ativ);

		}
		setBackground(Color.WHITE);
		add(painelPrincipal);
	}

	// TODO Fim do construtor, inicio Controle de tela.
	public static void carregarCampos(Ativo ativ) {
		txtFIdAtivo.setText(String.valueOf(ativ.getIdNeg()));
		txtFNomeAtivo.setText(ativ.getNomeRes());
		scrpGrafico.setViewportView(constGrafico.graficoLinhaPrecFech(ativ.getIdNeg()));
		atualizaTabelaCotacoes(ativ);
		// txtFUltimoPreco.setText(String.valueOf(atv.getListCot().get(0)
		// .getPreFec()));
		// txtFPreAnterior.setText(atv.getAliquotaICMS());
		// txtFPreMax.setText(String.valueOf(atv.getQuantidadeProdutos()));
		// txtFPreMin.setText(String.valueOf(atv.getprecoProd()));
	}

	// TODO Atualiza tabela de Cotações
	public static JTable atualizaTabelaCotacoes(final Ativo ativ) {
		System.out.println("PainelAtivo.atualizaTabelaCotacoes");
		daoCota = new DAOCotacaoAtivo();
		modeloTabelaCotacoes = new DefaultTableModel();
		setTblCotacoes(new JTable(modeloTabelaCotacoes));
		Object colunas[] = { "Data", "Fechamento", "Máximo", "Abertura", "Mínimo" };
		modeloTabelaCotacoes.setColumnIdentifiers(colunas);
		getTblCotacoes().setRowSelectionAllowed(false);
		getTblCotacoes().setCellSelectionEnabled(true);
		getTblCotacoes().setColumnSelectionAllowed(false);
		listCotacao = daoCota.conCotAtvOrdDtAscend(ativ.getIdNeg());
		for (int i = 0; i < listCotacao.size(); i++) {
			Object linha[] = { listCotacao.get(i).getDataCotacao(), listCotacao.get(i).getPreFec(),
					listCotacao.get(i).getPreMax(), listCotacao.get(i).getPreAbe(), listCotacao.get(i).getPreMin() };
			modeloTabelaCotacoes.addRow(linha);
		}
		getTblCotacoes().setShowGrid(true);
		getTblCotacoes().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				getTblCotacoes().setSelectionBackground(Color.WHITE);
				getTblCotacoes().setSelectionForeground(Color.BLACK);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				getTblCotacoes().setSelectionBackground(Color.BLACK);
				getTblCotacoes().setSelectionForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		scrpCotacoes.setViewportView(tblCotacoes);
		return getTblCotacoes();
	}

	public void desHabilitaEdicao() {

		txtFIdAtivo.setEditable(false);
		txtFNomeAtivo.setEditable(false);
		txtFUltimoPreco.setEditable(false);
		txtFPreMax.setEditable(false);
		txtFPreAnterior.setEditable(false);
		txtFPreMin.setEditable(false);

	}

	public static void irParaPoicao(int posicao) {
		controledaLista.setCurrentPosition(posicao);
		ativ = controledaLista.getAt(posicao);
		carregarCampos(ativ);
	}

	// TODO Limpa Campos
	public void limparCampos() {
		txtFIdAtivo.setText(null);
		txtFNomeAtivo.setText(null);
		txtFUltimoPreco.setText(null);
		txtFPreMax.setText(null);
		txtFPreAnterior.setText(null);
		txtFPreMin.setText(null);

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
		return txtFNomeAtivo;
	}

	public static void setBtnCancelar(JButton btnCancelar) {
		PainelAtivo.btnCancelar = btnCancelar;
	}

	public static void setBtnEditar(JButton btnEditar) {
		PainelAtivo.btnEditar = btnEditar;
	}

	public static void setBtnNovo(JButton btnNovo) {
		PainelAtivo.btnNovo = btnNovo;
	}

	public static void setTxtFNomeProd(JTextField txtFNomeProd) {
		PainelAtivo.txtFNomeAtivo = txtFNomeProd;
	}

	public static JTable getTblCotacoes() {
		return tblCotacoes;
	}

	public static void setTblCotacoes(JTable tblCotacoes) {
		PainelAtivo.tblCotacoes = tblCotacoes;
	}

}
