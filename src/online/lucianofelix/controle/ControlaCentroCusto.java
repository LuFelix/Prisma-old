package online.lucianofelix.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import online.lucianofelix.beans.CentroCusto;
import online.lucianofelix.dao.DAOCentroCusto;
import online.lucianofelix.tableModels.commom.TableModelCentroCusto;
import online.lucianofelix.visao.FrameInicial;
import online.lucianofelix.visao.FrameInicial.ControlaBotoes;
import online.lucianofelix.visao.PainelCentroCusto;

public class ControlaCentroCusto {

	private static JTable tabela;
	TableModelCentroCusto mdltabela;
	private static JComboBox<String> cmbCCusto;
	private static List<CentroCusto> listCentroCusto;
	private static DAOCentroCusto daoCentroCusto;
	private static CentroCusto centroCusto;
	private static ControlaConta contConta;
	private static String nome;

	public ControlaCentroCusto() {
		System.out.println("ControlaCentroCusto.construtor");
		daoCentroCusto = new DAOCentroCusto();
		contConta = new ControlaConta();
	}

	public JComboBox cmbCentrosCusto() {
		cmbCCusto = new JComboBox<String>();
		cmbCCusto.addItem("Centro de Custo");
		cmbCCusto.setToolTipText("Selecione o centro de custo para a conta.");

		listCentroCusto = new ArrayList<CentroCusto>(pesqNomeArray(""));
		if (listCentroCusto.size() > 0) {
			for (CentroCusto centroCusto : listCentroCusto) {
				cmbCCusto.addItem(centroCusto.getNomeCentroCusto());
			}
		}
		return cmbCCusto;

	}

	// TODO Carregar as contas do centro de custo
	public void carregaContasCCusto(CentroCusto c) {

		c.setListContas(contConta.lisContCCusto(c.getCodiCentroCusto()));

	}
	// TODO Retorna um JTable pesquisado em codigo nome
	private JTable pesqNomeTabela(String str) {
		tabela = new JTable();

		mdltabela = new TableModelCentroCusto(
				daoCentroCusto.pesquisarString(str));
		tabela.setModel(mdltabela);
		tabela.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					centroCusto = mdltabela
							.getCentroCusto(tabela.getSelectedRow());
					PainelCentroCusto.carregarCampos(centroCusto);
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				}
			}

			@Override
			public void keyPressed(KeyEvent tecla) {
				int posicao = tabela.getSelectedRow();
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					PainelCentroCusto.carregarCampos(centroCusto);
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 10) {
					PainelCentroCusto.carregarCampos(centroCusto);
					funcaoSobrescrever();
					FrameInicial.getTabela().changeSelection(--posicao, 0,
							false, false);
					PainelCentroCusto.getTxtFNome().grabFocus();
				}
			}
		});
		tabela.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				centroCusto = mdltabela.getCentroCusto(tabela.getSelectedRow());
				PainelCentroCusto.carregarCampos(centroCusto);

			}
		});

		// tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(60);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
		tabela.setShowGrid(true);

		return tabela;
	}

	public List<CentroCusto> pesqNomeArray(String str) {
		return daoCentroCusto.pesquisarString(str);
	}

	private static void ajusta_tamanho_coluna() {
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(60);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(270);
	}

	// TODO Função Salvar
	private void funcaoSalvar() {
		System.out.println("ControlaCentroCusto.funcaoSalvar");
		ControlaBotoes.limparBtnSalvar();
		ControlaBotoes.habilitaNovoBotoes();
		PainelCentroCusto.habilitaNovo();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				centroCusto = PainelCentroCusto.lerCampos();
				if (!centroCusto.equals(null)
						& daoCentroCusto.cadastrar(centroCusto)) {
					PainelCentroCusto.limparCampos();
					FrameInicial.setTabela(
							pesqNomeTabela(centroCusto.getCodiCentroCusto()));
					PainelCentroCusto.carregarCampos(centroCusto);
					FrameInicial.atualizaTela();
					JOptionPane.showMessageDialog(null, "Feito");
					iniciar();
				} else {
					JOptionPane.showMessageDialog(null,
							"Problemas: Erro de acesso ao banco",
							"Erro ao Salvar", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void funcaoCancelar() {
		System.out.println("ControlaCentroCusto.cancelar");
		iniciar();
	}

	// TODO Funcao excluir
	public boolean funcaoExcluir() {
		System.out.println("ControlaGCentroCusto.excluir");
		centroCusto = PainelCentroCusto.lerCampos();
		if (daoCentroCusto.excluir(centroCusto)) {
			FrameInicial.limpaTela();
			funcaoCancelar();
			return true;
		} else {
			funcaoCancelar();
			return false;
		}
	}

	// TODO Função sobrescrever
	private void funcaoSobrescrever() {
		System.out.println("ControlaCentroCusto.funcaoSobrescrever");
		ControlaBotoes.limparBtnSalvar();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				centroCusto = PainelCentroCusto.lerCampos();
				if (!centroCusto.equals(null)
						& daoCentroCusto.alterar(centroCusto)) {
					FrameInicial.setTabela(
							pesqNomeTabela(centroCusto.getCodiCentroCusto()));
					// FrameInicial.setPainelVisualiza(new PainelCentroCusto(
					// centroCusto.getCodiCentroCusto()));
					FrameInicial.atualizaTela();
					JOptionPane.showMessageDialog(null, "Feito!");
					iniciar();
				} else {
					JOptionPane.showMessageDialog(null,
							"Favor verificar os campos informados. ",
							"Não foi possivel alterar!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public CentroCusto buscaNome(String nome) {
		centroCusto = daoCentroCusto.buscaNome(nome);
		return centroCusto;
	}

	public CentroCusto buscaCodigo(String codigo) {
		centroCusto = daoCentroCusto.buscaCodigo(codigo);
		return centroCusto;
	}

	// TODO Criar código
	public String criaCodigo() {
		System.out.println("ControlaCentroCusto.criarCodigo");
		Calendar c = Calendar.getInstance();
		String codigo = String.valueOf(daoCentroCusto.consultaUltimo())
				+ String.valueOf(c.get(Calendar.YEAR))
				+ String.valueOf(c.get(Calendar.MONTH))
				+ String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		return codigo;
	}

	// criar a tablemodel do centro de custo

	// TODO Pesquisa Centro Custo
	public void iniciar() {
		System.out.println("ControlaCentroCusto.pesquisaGrupo");
		ControlaBotoes.limpaTodosBotoes();
		ControlaBotoes.desHabilitaEdicaoBotoes();
		FrameInicial.getTxtfPesquisa().grabFocus();
		FrameInicial.setTabela(pesqNomeTabela(""));
		FrameInicial.setPainelVisualiza(new PainelCentroCusto());
		FrameInicial.atualizaTela();
		configuraTXTPesquisa();
		configuraBotoes();

	}
	void configuraTXTPesquisa() {
		FrameInicial.limparTxtfPesquisa();
		FrameInicial.getTxtfPesquisa().addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent tecla) {
				if (tecla.getExtendedKeyCode() == 40) {// seta para baixo
					FrameInicial.getTabela().grabFocus();
					FrameInicial.getTabela().changeSelection(0, 0, false,
							false);
				} else if (tecla.getExtendedKeyCode() == 27) {
					funcaoCancelar();
				} else {
					// System.out.println(tecla.getExtendedKeyCode());
					nome = FrameInicial.getTxtfPesquisa().getText();
					FrameInicial.setTabela(pesqNomeTabela(nome));
					// FrameInicial
					// .setPainelVisualiza(new PainelCentroCusto(nome));
					FrameInicial.atualizaTela();
				}
			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				nome = FrameInicial.getTxtfPesquisa().getText();
				FrameInicial.setTabela(pesqNomeTabela(nome));
				// FrameInicial.setPainelVisualiza(new PainelCentroCusto(nome));
				FrameInicial.atualizaTela();
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
	}
	void configuraBotoes() {
		ActionListener acaoEditar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelCentroCusto.habilitaEdicao();
				ControlaBotoes.habilitaEdicaoBotoes();
				funcaoSobrescrever();
			}
		};
		FrameInicial.getBtnEditar().addActionListener(acaoEditar);

		FrameInicial.getBtnNovo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlaBotoes.habilitaNovoBotoes();
				PainelCentroCusto.habilitaNovo();
				funcaoSalvar();
			}
		});
		FrameInicial.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlaBotoes.desHabilitaEdicaoBotoes();
				funcaoCancelar();
			}
		});
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlaBotoes.desHabilitaEdicaoBotoes();
				funcaoSalvar();
			}
		});
		FrameInicial.getBtnExcluir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlaBotoes.desHabilitaEdicaoBotoes();
				funcaoExcluir();
			}
		});
	}

}
