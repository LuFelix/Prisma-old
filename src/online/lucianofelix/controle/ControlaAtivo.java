package online.lucianofelix.controle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import online.lucianofelix.beans.Ativo;
import online.lucianofelix.beans.CotacaoYahoo;
import online.lucianofelix.dao.DAOAtivo;
import online.lucianofelix.dao.DAOCotacaoAtivo;
import online.lucianofelix.dao.DAOCotacaoYahoo;
import online.lucianofelix.util.ManipulaData;
import online.lucianofelix.visao.FrameInicial;
import online.lucianofelix.visao.PainelAtivo;
import online.lucianofelix.visao.PainelOperacao;

public class ControlaAtivo {
	String resposta;
	List<Ativo> listAtv;
	List<CotacaoYahoo> listUlt;
	Ativo atv;
	DAOAtivo daoAtv;
	ControlaListaAtivos controledalista;
	DAOCotacaoYahoo daoCotYahoo;
	ControlaAtivo cAtv;
	HashSet<CotacaoYahoo> hashUltimas;
	private JTable tabela1;
	private ArrayList<Object> colunas;
	private DefaultTableModel modelotabela;
	private DAOCotacaoAtivo daoCotaBov;
	private List<String> listData;
	JScrollBar vertBar;
	JScrollBar horiBar;
	int eixoY = 0;
	int eixoX = 0;

	public ControlaAtivo() {
		daoAtv = new DAOAtivo();
		daoCotaBov = new DAOCotacaoAtivo();
		daoCotYahoo = new DAOCotacaoYahoo();

	}

	/**
	 * Lista apenas o mercado a vista
	 * 
	 * @return
	 */
	public List<Ativo> procurarTodosOrdIdNeg() {
		listAtv = new ArrayList<Ativo>();
		List<Ativo> list = new ArrayList<>();
		list = daoAtv.procurarTodosOrdIdNeg();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTpMerc() == 10) {
				listAtv.add(list.get(i));
			}
		}
		return listAtv;

	}

	/**
	 * TODO Tabela com as últimas cotações.
	 * 
	 * @return
	 */
	public JTable pesqAtivoTabelaUltimas() {

		// vertBar = FrameInicial.getScrVisualiza().getVerticalScrollBar();

		colunas = new ArrayList<>();
		tabela1 = new JTable();

		DefaultTableModel modelotabela = new DefaultTableModel();

		// vertBar.addAdjustmentListener(new AdjustmentListener() {
		//
		// @Override
		// public void adjustmentValueChanged(AdjustmentEvent arg0) {
		// eixoY = vertBar.getValue();
		//
		// }
		// });

		modelotabela = (DefaultTableModel) tabela1.getModel();
		colunas.add("Código ");
		colunas.add("Max");
		colunas.add("Min");
		colunas.add("Último");
		colunas.add("Var");
		colunas.add("Hora último");
		listUlt = daoCotYahoo.consultaUltimasCotacoes();
		modelotabela.setColumnIdentifiers(colunas.toArray());
		float variacao;
		String dataHora;
		String hora;
		String diaMes;
		for (int i = 0; i < listUlt.size(); i++) {
			variacao = listUlt.get(i).getVariacao();
			dataHora = listUlt.get(i).getDataHoraCotacao().toString();
			diaMes = ManipulaData.diaMesString(dataHora);
			hora = ManipulaData.horaString(dataHora);
			if (variacao <= -0.3 | variacao >= 0.3) {
				Object linha[] = {listUlt.get(i).getIdYahoo(),
						listUlt.get(i).getPreMax(), listUlt.get(i).getPreMin(),
						listUlt.get(i).getPreFec(),
						listUlt.get(i).getVariacao(), hora + " " + diaMes};
				modelotabela.addRow(linha);
			}
		}

		tabela1.setShowHorizontalLines(true);
		tabela1.setModel(modelotabela);

		return tabela1;
	}

	void posicionaTabelaltimas() {
		FrameInicial.getScrVisualiza().getVerticalScrollBar().setValue(eixoY);
		// FrameInicial.getScrVisualiza().setVerticalScrollBar(vertBar);
		// FrameInicial.getScrVisualiza().setHorizontalScrollBar(horiBar);

	}

	// TODO Pesquisa ativo por código ou nome na tabela.
	public JTable pesqAtivoTabela(String str) {
		daoAtv = new DAOAtivo();
		colunas = new ArrayList<>();
		tabela1 = new JTable();
		modelotabela = new DefaultTableModel();
		modelotabela = (DefaultTableModel) tabela1.getModel();
		tabela1.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent tecla) {
				int posicao = tabela1.getSelectedRow();
				if ((tecla.getExtendedKeyCode() == 40)
						|| (tecla.getExtendedKeyCode() == 38)) {
					PainelAtivo.irParaPoicao(posicao);
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					// FrameInicial.getTxtfPesquisa().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 10) {
					PainelAtivo.irParaPoicao(posicao);
					PainelAtivo.getBtnEditar().doClick();
					FrameInicial.getTabela().changeSelection(--posicao, 0,
							false, false);
					PainelAtivo.getTxtFNomeProd().grabFocus();
				}
			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				if ((tecla.getExtendedKeyCode() == 40)
						|| (tecla.getExtendedKeyCode() == 38)) {
					int posicao = tabela1.getSelectedRow();
					PainelAtivo.irParaPoicao(posicao);
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		tabela1.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {

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
				int posicao = tabela1.getSelectedRow();
				PainelAtivo.irParaPoicao(posicao);

			}
		});

		colunas.add("Código ");
		colunas.add("Nome");
		colunas.add("Último");
		listAtv = daoAtv.pesquisaString(str);
		modelotabela.setColumnIdentifiers(colunas.toArray());
		for (int i = 0; i < listAtv.size(); i++) {
			Object linha[] = {listAtv.get(i).getIdNeg(),
					listAtv.get(i).getNomeRes(), listAtv.get(i).getCotacao()};
			modelotabela.addRow(linha);
		}
		tabela1.setShowGrid(true);
		tabela1.setModel(modelotabela);
		return tabela1;
	}

	/**
	 * TODO Tabela que adiciona ativo à operação.
	 * 
	 * @param nome
	 * @return
	 */
	public JTable pesqNomeTabelaAdicionaAtivoOp(String nome) {
		daoAtv = new DAOAtivo();
		ArrayList<String> colunas = new ArrayList<String>();
		tabela1 = new JTable();
		DefaultTableModel modelotabela = new DefaultTableModel();
		modelotabela = (DefaultTableModel) tabela1.getModel();
		tabela1.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Ao escrever
			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				// TODO Ao Soltar a tecla
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					int posicao = tabela1.getSelectedRow();
				}
			}

			@Override
			public void keyPressed(KeyEvent tecla) {
				// TODO Ao Pressionar Tecla
				int posicao = tabela1.getSelectedRow();
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 10) {
					PainelOperacao.adicionaAtvOp(listAtv.get(posicao));
				} else if (tecla.getExtendedKeyCode() == 9) {
					PainelOperacao.getTxtFStartCompra().grabFocus();

				}
			}
		});
		tabela1.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Ao soltar o botÃ£o do mouse
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Ao Pressionar o botÃ£o do mouse
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Ao sair o mouse
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Ao entrar o mouse
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Ao Clicar
				int posicao = tabela1.getSelectedRow();
				PainelOperacao.adicionaAtvOp(listAtv.get(posicao));
			}
		});
		colunas.add("Código");
		colunas.add("Nome");
		colunas.add("Último");
		listAtv = new ArrayList<Ativo>();
		listAtv = daoAtv.pesquisaString(nome);
		modelotabela.setColumnIdentifiers(colunas.toArray());
		System.out.println(listAtv.size());
		for (int i = 0; i < listAtv.size(); i++) {
			Object linha[] = {listAtv.get(i).getIdNeg(),
					listAtv.get(i).getNomeRes(), listAtv.get(i).getTpMerc()};
			modelotabela.addRow(linha);
		}
		tabela1.setShowGrid(true);
		tabela1.setModel(modelotabela);
		return tabela1;
	}

	public List<Ativo> procurarTodosOrdNomAscMercVista() {
		listAtv = new ArrayList<Ativo>();
		List<Ativo> list = new ArrayList<>();
		list = daoAtv.procurarTodosOrdNomAsc();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTpMerc() == 10) {
				listAtv.add(list.get(i));
			}
		}
		return listAtv;
	}

	public ArrayList<Ativo> procurarStr(String str) {
		listAtv = daoAtv.pesquisaString(str);
		return (ArrayList<Ativo>) listAtv;
	}

	public void posicionarTabela(int linha) {
		System.out.println("Ir para a linha: " + linha);
		FrameInicial.getTabela().changeSelection(linha, 0, false, false);
	}

	public int getEixoY() {
		return eixoY;
	}

	public void setEixoY(int eixoY) {
		this.eixoY = eixoY;
	}
}
