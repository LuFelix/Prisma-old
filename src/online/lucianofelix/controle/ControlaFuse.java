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

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import online.lucianofelix.beans.Fuse;
import online.lucianofelix.dao.DAOFuse;
import online.lucianofelix.dao.DAOOperacao;
import online.lucianofelix.util.ManipulaData;
import online.lucianofelix.visao.FrameInicial;
import online.lucianofelix.visao.FrameInicial.ControlaBotoes;
import online.lucianofelix.visao.PainelFuse;

public class ControlaFuse {

	String resposta;
	Fuse fuse;
	DAOFuse daoFuse;
	DAOOperacao daoOpe;
	ControlaListaProdutos controledaLista;
	private JTextArea txtARelat;
	private JTable tabela;
	List<Fuse> arrayFuse;
	List<String> listAtivo;
	private float total;

	public ControlaFuse() {
		daoFuse = new DAOFuse();
		daoOpe = new DAOOperacao();
		txtARelat = new JTextArea();
	}

	public void cadastrar(Fuse fuse) {

		if (daoFuse.cadastrar(fuse)) {

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
		colunas.add("Lucro/Prejuízo");
		colunas.add("Obs.");
		List<Fuse> dados = new ArrayList<Fuse>();
		dados = daoFuse.pesquisarNome("");
		modelotabela.setColumnIdentifiers(colunas.toArray());
		for (int i = 0; i < dados.size(); i++) {
			Object linha[] = {dados.get(i).getCodiAtivo(),
					String.valueOf(dados.get(i).getLucroPrejuizo()),
					String.valueOf(dados.get(i).getObsFuse())};
			modelotabela.addRow(linha);
		}
		tabela.setShowGrid(true);
		tabela.setModel(modelotabela);
		return tabela;
	}

	public void carregarOperVinculadas(Fuse fuse) {
		System.out.println("ControlaFuse.carregaOperacoesVinculadas");
		fuse.setListOperVinc(daoOpe.pesquisaOpCodiFuse(fuse.getCodiFuse()));
	}

	public void iniciar() {
		ControlaBotoes.limpaTodosBotoes();
		ControlaBotoes.desHabilitaEdicaoBotoes();
		FrameInicial.limparTxtfPesquisa();
		FrameInicial.getTxtfPesquisa().grabFocus();
		FrameInicial.setTabela(pesqNomeTabela(""));
		FrameInicial.setPainelVisualiza(new PainelFuse(""));
		FrameInicial.atualizaTela();
		funcaoCancelar();
		FrameInicial.getBtnEditar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlaBotoes.habilitaEdicaoBotoes();
				PainelFuse.habilitaEdicao();
				funcaoSobrescrever();
			}
		});
		FrameInicial.getBtnNovo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ControlaBotoes.clicaNovoBotoes();
				PainelFuse.habilitaNovo();
				funcaoSalvar();
			}
		});
		FrameInicial.getBtnExcluir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fuse = PainelFuse.lerCampos();
				funcaoExcluir();
			}
		});

		FrameInicial.getTxtfPesquisa().addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent tecla) {
				if (tecla.getExtendedKeyCode() == 40) {
					FrameInicial.getTabela().grabFocus();
					FrameInicial.getTabela().changeSelection(0, 0, false,
							false);
				} else {
					String nome = FrameInicial.getTxtfPesquisa().getText();
					FrameInicial.setTabela(pesqNomeTabela(nome));
					FrameInicial.setPainelVisualiza(new PainelFuse(nome));
					FrameInicial.atualizaTela();
				}
			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				String nome = FrameInicial.getTxtfPesquisa().getText();
				FrameInicial.setTabela(pesqNomeTabela(nome));
				FrameInicial.setPainelVisualiza(new PainelFuse(nome));
				FrameInicial.atualizaTela();
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

	}

	// TODO Cancelar
	public void funcaoCancelar() {
		ControlaBotoes.limparBtnCancelar();
		FrameInicial.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				iniciar();
			}
		});
	}

	// TODO Função sobrescrever
	public void funcaoSobrescrever() {
		System.out.println("ControlaFuse.funcaoSobrescrever");
		ControlaBotoes.limparBtnSalvar();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fuse = PainelFuse.lerCampos();
				if (!fuse.equals(null) & daoFuse.alterar(fuse)) {
					FrameInicial.setTabela(pesqNomeTabela(fuse.getCodiFuse()));
					FrameInicial.setPainelVisualiza(
							new PainelFuse(fuse.getCodiFuse()));
					FrameInicial.atualizaTela();
					JOptionPane.showMessageDialog(null, "Feito!");
					iniciar();
				}
			}
		});
	}

	// TODO Função Salvar
	public void funcaoSalvar() {
		System.out.println("ControlaFuse.funcaoSalvar");
		ControlaBotoes.limparBtnSalvar();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fuse = PainelFuse.lerCampos();
				if (!fuse.equals(null) & daoFuse.cadastrar(fuse)) {
					PainelFuse.limparCampos();
					FrameInicial.setTabela(pesqNomeTabela(fuse.getCodiFuse()));
					FrameInicial.setPainelVisualiza(
							new PainelFuse(fuse.getCodiFuse()));
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

	// TODO Funcao excluir
	public boolean funcaoExcluir() {
		System.out.println("ControlaFuse.excluir");
		fuse = PainelFuse.lerCampos();
		if (daoFuse.excluir(fuse)) {
			FrameInicial.setTabela(null);
			FrameInicial.setPainelVisualiza(null);
			FrameInicial.atualizaTela();
			JOptionPane.showMessageDialog(null, "Feito!");
			iniciar();
			return true;
		} else {
			funcaoCancelar();
			return false;
		}
	}

	public List<Fuse> pesqNomeArray(String nome) {

		return daoFuse.pesquisarNome(nome);
	}

	// TODO Tabela ligada ao painel de Fuses
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
					PainelFuse.irParaPoicao(posicao);
				}
			}

			@Override
			public void keyPressed(KeyEvent tecla) {
				int posicao = tabela.getSelectedRow();
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {

					PainelFuse.irParaPoicao(posicao);
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 10) {
					PainelFuse.irParaPoicao(posicao);
					// PainelFuse.getBtnEditar().doClick();
					FrameInicial.getTabela().changeSelection(--posicao, 0,
							false, false);
					// PainelFuse.getBtnNovo().grabFocus();
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
				PainelFuse.irParaPoicao(posicao);

			}
		});
		colunas.add("Ativo");
		colunas.add("Lucro/Prejuízo");
		colunas.add("Tipo");
		arrayFuse = new ArrayList<Fuse>();
		arrayFuse = daoFuse.pesquisarNome(nome);
		modelotabela.setColumnIdentifiers(colunas.toArray());
		for (int i = 0; i < arrayFuse.size(); i++) {
			Object linha[] = {arrayFuse.get(i).getCodiAtivo(),
					String.valueOf(arrayFuse.get(i).getLucroPrejuizo()),
					arrayFuse.get(i).getTipoFuse()};
			modelotabela.addRow(linha);
		}
		tabela.setShowGrid(true);
		tabela.setModel(modelotabela);
		return tabela;
	}

	// TODO Setar o codigo do Fuse
	public String criaCodigo() {
		Calendar c = Calendar.getInstance();
		daoFuse.consultaUltimo();
		String codiFuse = String.valueOf(daoFuse.consultaUltimo())
				+ String.valueOf(c.get(Calendar.YEAR))
				+ String.valueOf(c.get(Calendar.MONTH))
				+ String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		return codiFuse;
	}

	public void relLucAtivo() {
		FrameInicial.limpaTela();
		String codiAtivo = JOptionPane
				.showInputDialog("Informe o Código do ativo!");
		float lucroTotal = 0;
		txtARelat = new JTextArea();
		txtARelat.setBounds(0, 0, 525, 510);
		arrayFuse = new ArrayList<Fuse>();
		arrayFuse = daoFuse.pesquisarNome(codiAtivo);
		String linha = "Relatório para: " + codiAtivo;
		txtARelat.append(linha + "\n");
		linha = "Encontradas " + arrayFuse.size() + " fuse(s) para este ativo.";
		txtARelat.append(linha + "\n");
		for (Fuse fuse : arrayFuse) {
			linha = String.valueOf(fuse.getLucroPrejuizo() * fuse.getQuant());
			lucroTotal = lucroTotal
					+ (fuse.getLucroPrejuizo() * fuse.getQuant());
			txtARelat.append(linha + "\n");
		}
		linha = "O lucro desse ativo durante o período analisado foi de: "
				+ lucroTotal;
		txtARelat.append(linha + "\n");
		FrameInicial.getScrVisualiza().setViewportView(txtARelat);
	}

	/**
	 * Emite um TXTArea com os fuses e seus resultados por ativo;
	 * 
	 * @param codiAtivo
	 */
	public String relLucAtivo(String codiAtivo) {
		FrameInicial.limpaTela();
		ManipulaData manData = new ManipulaData();
		float lucroTotal = 0;
		txtARelat.setBounds(0, 0, 525, 510);
		arrayFuse = new ArrayList<Fuse>();
		arrayFuse = daoFuse.pesquisarNome(codiAtivo);
		String linha = "Relatório para: " + codiAtivo;
		String relatorio = "Relatório para: " + codiAtivo + "\n";
		txtARelat.append(linha + "\n");
		linha = "Encontradas " + arrayFuse.size() + " fuse(s) para este ativo.";
		txtARelat.append(linha + "\n");
		relatorio = relatorio + linha + "\n";
		for (Fuse fuse : arrayFuse) {
			linha = "Data início: "
					+ manData.inverteData6(
							fuse.getDataInicio().toString().substring(0, 10))
					+ "  |  Data fim: "
					+ manData.inverteData6(
							fuse.getDataFim().toString().substring(0, 10))
					+ "  |  Lucro ==>> "
					+ String.valueOf(fuse.getLucroPrejuizo() * fuse.getQuant());
			lucroTotal = lucroTotal
					+ (fuse.getLucroPrejuizo() * fuse.getQuant());
			txtARelat.append(linha + "\n");
			relatorio = relatorio + linha + "\n";
		}

		linha = "\n O lucro desse ativo durante o período analisado foi de: "
				+ lucroTotal;
		txtARelat.append(linha + "\n");
		relatorio = relatorio + linha + "\n";
		return relatorio;
	}

	public void ativomaislucrativo() {
		listAtivo = daoFuse.pesquisarAtivoComFuse();

	}

	public void relLucGeral() {
		FrameInicial.limpaTela();
		float lucroTotal = 0;
		txtARelat = new JTextArea();
		txtARelat.setBounds(0, 0, 525, 510);
		arrayFuse = new ArrayList<Fuse>();
		arrayFuse = daoFuse.procurarTodos();
		String linha = "	Relatório Geral ";
		txtARelat.append(linha + "\n");
		linha = "Encontradas " + arrayFuse.size() + " fuse(s).";
		txtARelat.append(linha + "\n");
		for (Fuse fuse : arrayFuse) {
			linha = "*** " + fuse.getCodiAtivo() + " ====>>  "
					+ fuse.getLucroPrejuizo() * fuse.getQuant();
			lucroTotal = lucroTotal
					+ (fuse.getLucroPrejuizo() * fuse.getQuant());
			txtARelat.append(linha + "\n");
		}
		linha = "O lucro dessas fuses durante o período analisado foi de: "
				+ lucroTotal;
		txtARelat.append(linha + "\n");
		FrameInicial.getScrVisualiza().setViewportView(txtARelat);
	}

	public void relLucGeralDetalh() {
		FrameInicial.limpaTela();
		JTextArea txtARelatDet = new JTextArea();
		txtARelat.setBounds(0, 0, 525, 510);
		listAtivo = daoFuse.pesquisarAtivoComFuse();

		for (String ativo : listAtivo) {
			txtARelatDet.append(relLucAtivo(ativo) + "\n"
					+ "++++++++++++++++++++++++++ \n");
		}
		FrameInicial.getScrVisualiza().setViewportView(txtARelatDet);
	}
}
