package online.lucianofelix.controle;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import online.lucianofelix.beans.Operacao;
import online.lucianofelix.dao.DAOOperacao;
import online.lucianofelix.visao.FrameInicial;
import online.lucianofelix.visao.PainelOperacao;
import online.lucianofelix.visao.FrameInicial.ControlaBotoes;

public class ControlaOperacao {
	String resposta;
	Operacao op;
	DAOOperacao daoOp;
	ControlaListaOperacoes controledaLista;
	private JTable tabela;
	List<Operacao> arrayOp;
	private float total;

	public ControlaOperacao() {
		daoOp = new DAOOperacao();
	}

	public boolean cadastrar(Operacao op) {
		if (daoOp.cadastrar(op)) {
			return true;
		} else {
			return false;
		}
	}

	public void posicionarTabela(int linha) {
		System.out.println("Ir para a linha: " + linha);
		FrameInicial.getTabela().changeSelection(linha, 0, false, false);

	}

	// TODO cancelar
	public void funcaoCancelar() {
		FrameInicial.pesquisaOperacao();
	}

	public String criaCodiOp() {
		Calendar c = Calendar.getInstance();
		String codiOperacao = String.valueOf(daoOp.consultaUltimo()) + String.valueOf(c.get(Calendar.YEAR))
				+ String.valueOf(c.get(Calendar.MONTH)) + String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		return codiOperacao;
	}

	// TODO Salvar
	public void funcaoSalvar() {
		System.out.println("ControlaOperacao.funcaoSalvar");
		ControlaBotoes.limparBtnSalvar();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				op = PainelOperacao.lerCampos();
				if (!op.equals(null) & daoOp.alterar(op)) {
					PainelOperacao.limparCampos();
					FrameInicial.setTabela(pesqNomeTabela(op.getCodiAtivo()));
					FrameInicial.setPainelVisualiza(new PainelOperacao(op.getCodiAtivo()));
					FrameInicial.atualizaTela();
					FrameInicial.pesquisaOperacao();
				} else {
					JOptionPane.showMessageDialog(null, "Problemas: Erro de acesso ao banco", "Erro ao Salvar",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	// TODO Sobrescrever
	public void funcaoSobrescrever() {
		System.out.println("ControlaOperacao.funcaoSobrescrever");
		ControlaBotoes.limparBtnSalvar();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				op = PainelOperacao.lerCampos();
				if (!op.equals(null) & daoOp.alterar(op)) {
					PainelOperacao.limparCampos();
					FrameInicial.setTabela(pesqNomeTabela(op.getCodiAtivo()));
					FrameInicial.setPainelVisualiza(new PainelOperacao(op.getCodiAtivo()));
					FrameInicial.atualizaTela();
					JOptionPane.showMessageDialog(null, "Feito");
					FrameInicial.pesquisaOperacao();
				} else {
					JOptionPane.showMessageDialog(null, "Problemas: Erro de acesso ao banco", "Erro ao Salvar",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void funcaoNovo() {
		PainelOperacao.habilitaNovo();
		funcaoSalvar();
		FrameInicial.pesquisaAtivoRealizaOp();

	}

	public boolean funcaoExcluir() {
		op = PainelOperacao.lerCampos();
		if (daoOp.excluir(op)) {
			PainelOperacao.limparCampos();
			FrameInicial.limpaTela();
			funcaoCancelar();
			return true;
		} else {
			funcaoCancelar();
			return false;
		}

	}

	public JTable tabela() {
		ArrayList<String> colunas = new ArrayList<>();
		tabela = new JTable();
		DefaultTableModel modelotabela = new DefaultTableModel();
		modelotabela = (DefaultTableModel) tabela.getModel();
		colunas.add("Ativo");
		colunas.add("Compra");
		colunas.add("Venda");
		List<Operacao> dados = new ArrayList<Operacao>();
		dados = daoOp.pesquisaString("");
		modelotabela.setColumnIdentifiers(colunas.toArray());
		for (int i = 0; i < dados.size(); i++) {
			Object linha[] = { dados.get(i).getCodiAtivo(), String.valueOf(dados.get(i).getValorPapel()),
					String.valueOf(dados.get(i).getQtdPapeis()) };
			modelotabela.addRow(linha);
		}
		tabela.setShowGrid(true);
		tabela.setModel(modelotabela);
		FrameInicial.getTabela().getParent().setBackground(Color.WHITE);
		return tabela;
	}

	// TODO Tabela ligada ao painel de operações
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
				if (tecla.getExtendedKeyCode() == 40 || tecla.getExtendedKeyCode() == 38) {
					int posicao = tabela.getSelectedRow();
					PainelOperacao.irParaPoicao(posicao);
				}
			}

			@Override
			public void keyPressed(KeyEvent tecla) {
				int posicao = tabela.getSelectedRow();
				if (tecla.getExtendedKeyCode() == 40 || tecla.getExtendedKeyCode() == 38) {
					PainelOperacao.irParaPoicao(posicao);
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 10) {
					PainelOperacao.irParaPoicao(posicao);
					PainelOperacao.getBtnEditar().doClick();
					FrameInicial.getTabela().changeSelection(--posicao, 0, false, false);
					PainelOperacao.getTxtFNomeProd().grabFocus();
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
				PainelOperacao.irParaPoicao(posicao);

			}
		});
		colunas.add("Ativo");
		colunas.add("Preço");
		colunas.add("Quant.");
		arrayOp = new ArrayList<>();
		arrayOp = daoOp.pesquisaString(nome);
		modelotabela.setColumnIdentifiers(colunas.toArray());
		for (int i = 0; i < arrayOp.size(); i++) {
			Object linha[] = { arrayOp.get(i).getCodiAtivo(), String.valueOf(arrayOp.get(i).getValorPapel()),
					arrayOp.get(i).getQtdPapeis(), };
			modelotabela.addRow(linha);

		}
		tabela.setShowGrid(true);
		tabela.setModel(modelotabela);
		return tabela;
	}

}
