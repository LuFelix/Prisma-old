package online.lucianofelix.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import online.lucianofelix.beans.TabelaPreco;
import online.lucianofelix.dao.DAOTabelaPreco;
import online.lucianofelix.util.ManipulaData;
import online.lucianofelix.visao.FrameInicial;
import online.lucianofelix.visao.FrameInicial.ControlaBotoes;
import online.lucianofelix.visao.PainelTabelaPreco;

public class ControlaTabelaPreco {

	String resposta;
	TabelaPreco op;
	DAOTabelaPreco daoTabPreco;
	ControlaListaTabelaPreco controledaLista;
	private JTable tabela;
	List<TabelaPreco> listTabPreco;
	ManipulaData manData;
	private float total;
	private TabelaPreco tabPreco;

	public ControlaTabelaPreco() {
		manData = new ManipulaData();
		daoTabPreco = new DAOTabelaPreco();

	}

	public void cadastrar(TabelaPreco op) {
		try {
			daoTabPreco.cadastrar(op);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void posicionarTabela(int linha) {
		System.out.println("Ir para a linha: " + linha);
		FrameInicial.getTabela().changeSelection(linha, 0, false, false);

	}

	public JTable tabela() {
		ArrayList<String> colunas = new ArrayList<>();
		tabela = new JTable();
		DefaultTableModel modelotabela = new DefaultTableModel();
		modelotabela = (DefaultTableModel) tabela.getModel();
		colunas.add("Ativo");
		colunas.add("Compra");
		colunas.add("Venda");
		List<TabelaPreco> dados = new ArrayList<TabelaPreco>();
		dados = daoTabPreco.pesquisaString("");
		modelotabela.setColumnIdentifiers(colunas.toArray());
		for (int i = 0; i < dados.size(); i++) {
			Object linha[] = {listTabPreco.get(i).getNomeTabela(),
					String.valueOf(listTabPreco.get(i).getTipoTabela()),
					listTabPreco.get(i).getCodiLoja()};
			modelotabela.addRow(linha);
		}
		tabela.setShowGrid(true);
		tabela.setModel(modelotabela);
		return tabela;
	}

	// TODO Tabela ligada ao painel de Tabelas de preços
	public JTable pesqNomeTabela(String nome) {
		ArrayList<String> colunas = new ArrayList<>();
		tabela = new JTable();
		DefaultTableModel modelotabela = new DefaultTableModel();
		modelotabela = (DefaultTableModel) tabela.getModel();
		tabela.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					int posicao = tabela.getSelectedRow();
					PainelTabelaPreco.irParaPoicao(posicao);
				}
			}

			@Override
			public void keyPressed(KeyEvent tecla) {
				int posicao = tabela.getSelectedRow();
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {

					PainelTabelaPreco.irParaPoicao(posicao);
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 10) {
					PainelTabelaPreco.irParaPoicao(posicao);
					FrameInicial.getTabela().changeSelection(--posicao, 0,
							false, false);

				}
			}
		});
		tabela.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				//

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				//

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				//

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				//

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int posicao = tabela.getSelectedRow();
				PainelTabelaPreco.irParaPoicao(posicao);

			}
		});
		colunas.add("Nome");
		colunas.add("Tipo");
		colunas.add("Classe");
		listTabPreco = new ArrayList<>();
		listTabPreco = daoTabPreco.pesquisaString(nome);
		modelotabela.setColumnIdentifiers(colunas.toArray());
		for (int i = 0; i < listTabPreco.size(); i++) {
			Object linha[] = {listTabPreco.get(i).getNomeTabela(),
					listTabPreco.get(i).getTipoTabela(),
					listTabPreco.get(i).getClasseTabela()};
			modelotabela.addRow(linha);
		}
		tabela.setShowGrid(true);
		tabela.setModel(modelotabela);
		return tabela;
	}

	// TODO Salvar
	public void funcaoSalvar() {
		ControlaBotoes.limparBtnSalvar();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!PainelTabelaPreco.getDataInicio().equals("")
						& !PainelTabelaPreco.getDataFim().equals("")) {
					try {
						tabPreco = PainelTabelaPreco.lerCampos();
						daoTabPreco.cadastrar(tabPreco);
						PainelTabelaPreco.limparCampos();
						FrameInicial.setTabela(
								pesqNomeTabela(tabPreco.getCodiTabela()));
						FrameInicial.setPainelVisualiza(new PainelTabelaPreco(
								tabPreco.getCodiTabela()));
						FrameInicial.atualizaTela();
						PainelTabelaPreco.desHabilitaEdicao();
						JOptionPane.showMessageDialog(null, "Feito!");
						PainelTabelaPreco.limparCampos();
						iniciar();
						FrameInicial.getBtnNovo().grabFocus();
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"Problemas: Codigo Nulo ou Duplicado",
								"Erro ao Cadastrar", JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Favor verificar os campos informados. ",
							"Não foi possivel concluir!",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});
	}

	// TODO sobrescrever
	public void funcaoSobrescrever() {
		ControlaBotoes.limparBtnSalvar();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!PainelTabelaPreco.getTxtFCodiTabela().getText()
						.equals("")) {
					tabPreco = PainelTabelaPreco.lerCampos();
					try {
						daoTabPreco.alterar(tabPreco);
						PainelTabelaPreco.limparCampos();
						PainelTabelaPreco.desHabilitaEdicao();
						FrameInicial.setTabela(
								pesqNomeTabela(tabPreco.getCodiTabela()));
						FrameInicial.setPainelVisualiza(new PainelTabelaPreco(
								tabPreco.getCodiTabela()));
						FrameInicial.atualizaTela();
						JOptionPane.showMessageDialog(null, "Feito!");
						iniciar();
					} catch (Exception e2) {
						PainelTabelaPreco.desHabilitaEdicao();
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"Erro ao gravar\n" + e2.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					PainelTabelaPreco.desHabilitaEdicao();
					JOptionPane.showMessageDialog(null,
							"Favor verificar os campos informados. ",
							"Não foi possivel alterar!",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});
	}

	public String criaCodigo() {
		Calendar c = Calendar.getInstance();
		String codiOperacao = String.valueOf(daoTabPreco.consultaUltimo())
				+ String.valueOf(c.get(Calendar.YEAR))
				+ String.valueOf(c.get(Calendar.MONTH))
				+ String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		return codiOperacao;
	}

	public void funcaoExcluir() {
		tabPreco = PainelTabelaPreco.lerCampos();
		try {
			daoTabPreco.remover(tabPreco);
			JOptionPane.showMessageDialog(null, "Feito!");
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"Problemas: Erro de acesso ao banco", "Erro ao Excluir",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}

	}

	// TODO Pesquisa Tabela de preços
	public void iniciar() {
		System.out.println("FrameInicial.pesuisaTabelaPreco");
		ControlaBotoes.limpaTodosBotoes();
		ControlaBotoes.desHabilitaEdicaoBotoes();
		FrameInicial.limparTxtfPesquisa();
		FrameInicial.getTxtfPesquisa().grabFocus();
		FrameInicial.setTabela(pesqNomeTabela(""));
		FrameInicial.setPainelVisualiza(new PainelTabelaPreco(""));
		FrameInicial.atualizaTela();
		FrameInicial.getBtnNovo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PainelTabelaPreco.habilitaNovo();
				ControlaBotoes.habilitaEdicaoBotoes();
				funcaoSalvar();
			}
		});
		FrameInicial.getBtnEditar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelTabelaPreco.habilitaEdicao();
				ControlaBotoes.habilitaEdicaoBotoes();
				funcaoSobrescrever();

			}
		});
		FrameInicial.getBtnExcluir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Excluir
				funcaoExcluir();
			}
		});
		FrameInicial.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				iniciar();
			}
		});
		// TODO Ações do txtfPesquisa
		FrameInicial.getTxtfPesquisa().addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent tecla) {
				String nome = FrameInicial.getTxtfPesquisa().getText();
				System.out.println(tecla.getExtendedKeyCode());
				if (tecla.getExtendedKeyCode() == 27) {// ESC
					// pergunta se quer mesmo encerrar a adição
				} else if (tecla.getExtendedKeyCode() == 10) {// enter
					FrameInicial.getTabela().grabFocus();
					FrameInicial.getTabela().changeSelection(0, 0, false,
							false);
				} else if (tecla.getExtendedKeyCode() == 40 // seta para baixo
						| tecla.getExtendedKeyCode() == 38) { // seta cima
					FrameInicial.getTabela().grabFocus();
					FrameInicial.getTabela().changeSelection(0, 0, false,
							false);
				} else {
					nome = FrameInicial.getTxtfPesquisa().getText();
					FrameInicial.setTabela(pesqNomeTabela(nome));
					FrameInicial
							.setPainelVisualiza(new PainelTabelaPreco(nome));
					FrameInicial.atualizaTela();
				}
			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				String nome = FrameInicial.getTxtfPesquisa().getText();
				FrameInicial.setTabela(pesqNomeTabela(nome));
				FrameInicial.setPainelVisualiza(new PainelTabelaPreco(nome));
				FrameInicial.atualizaTela();
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
	}

}
