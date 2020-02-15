package online.lucianofelix.controle;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import online.lucianofelix.beans.GrupoSubgrupo;
import online.lucianofelix.beans.Pessoa;
import online.lucianofelix.beans.PessoaProfissional;
import online.lucianofelix.dao.DAOGrupoSubgrupo;
import online.lucianofelix.dao.DAOPedidoPrepSTM;
import online.lucianofelix.dao.DAOPessoa;
import online.lucianofelix.dao.DAOPessoaPG;
import online.lucianofelix.dao.DAOPessoaProfissional;
import online.lucianofelix.dao.TableModelPedidos;
import online.lucianofelix.tableModels.TableModelProfissao;
import online.lucianofelix.tableModels.commom.TableModelPessoa;
import online.lucianofelix.visao.AbaCadastros;
import online.lucianofelix.visao.FrameInicial;
import online.lucianofelix.visao.FrameInicial.ControlaBotoes;
import online.lucianofelix.visao.PainelLancamento;
import online.lucianofelix.visao.PainelPedidos;
import online.lucianofelix.visao.PainelPessoa;

public class ControlaPessoa {

	private List<Pessoa> arrayPessoa;
	private List<GrupoSubgrupo> listGrupo;
	static DAOPessoa daoP;
	static DAOGrupoSubgrupo daoG;
	static DAOPessoaProfissional daoPP;
	static DAOPedidoPrepSTM daoPed;
	static JTable tbl01;
	static JTable tbl02;
	static JTable tbl03;
	static TableModelPessoa tblMdPessoa;
	static TableModelProfissao tblMdProf;
	static TableModelPedidos tblMdUltP;
	private JComboBox<String> cmbGrupos;
	static Pessoa p;

	public ControlaPessoa() {
		daoP = new DAOPessoaPG();
		daoG = new DAOGrupoSubgrupo();
		daoPP = new DAOPessoaProfissional();
		daoPed = new DAOPedidoPrepSTM();
		listGrupo = daoG.pesquisarString("");

	}
	// TODO Tabela Principal Atual
	public JTable tblPessoas(String str) {
		tblMdPessoa = new TableModelPessoa(daoP.pesquisaString(str));
		tbl01 = new JTable(tblMdPessoa);
		tbl01.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getExtendedKeyCode() == 40
						|| e.getExtendedKeyCode() == 38) {

					p = tblMdPessoa.getPessoa(tbl01.getSelectedRow());
					carregaDetalhes(p);
					tbl01.grabFocus();

				} else if (e.getExtendedKeyCode() == 27) {// esc
					funcaoCancelar();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 40 || e.getKeyCode() == 38) {
					p = tblMdPessoa.getPessoa(tbl01.getSelectedRow());
					carregaDetalhes(p);
					tbl01.grabFocus();
				}

			}
		});
		tbl01.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				p = tblMdPessoa.getPessoa(tbl01.getSelectedRow());
				carregaDetalhes(p);
				tbl01.grabFocus();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				p = tblMdPessoa.getPessoa(tbl01.getSelectedRow());
				carregaDetalhes(p);
				tbl01.grabFocus();
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				p = tblMdPessoa.getPessoa(tbl01.getSelectedRow());
				carregaDetalhes(p);
			}
		});

		tbl01.setShowGrid(true);
		return tbl01;
	}

	// TODO Tabela Ligada ao painel de Contatos/ Pessoas
	public static JTable pesqNomeTabela(String nome) {
		tbl01 = new JTable();
		ArrayList<String> colunas = new ArrayList<>();
		DefaultTableModel modelotabela = new DefaultTableModel();
		modelotabela = (DefaultTableModel) tbl01.getModel();
		tbl01.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					int posicao = tbl01.getSelectedRow();
					// PainelPessoa.irParaPoicao(posicao);
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				}
			}

			@Override
			public void keyPressed(KeyEvent tecla) {
				int posicao = tbl01.getSelectedRow();
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {

				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 10) {
					// PainelPessoa.irParaPoicao(posicao);
					FrameInicial.getTabela().changeSelection(--posicao, 0,
							false, false);
					PainelPessoa.getTxtfNome().grabFocus();
				}
			}
		});
		tbl01.addMouseListener(new MouseListener() {
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
				int posicao = tbl01.getSelectedRow();
				// PainelPessoa.irParaPoicao(posicao);
				System.out.println(tbl01.getMouseListeners());
			}
		});
		colunas.add("Nome");
		colunas.add("CPF");
		colunas.add("Email");
		List<Pessoa> dados = new ArrayList<>();
		dados = daoP.pesquisaString(nome);
		modelotabela.setColumnIdentifiers(colunas.toArray());
		for (int i = 0; i < dados.size(); i++) {
			Object linha[] = {dados.get(i).getNome(),
					String.valueOf(dados.get(i).getCpf()),
					dados.get(i).getEmail()};
			modelotabela.addRow(linha);
		}
		tbl01.setShowGrid(true);
		tbl01.setModel(modelotabela);
		return tbl01;
	}

	public void iniciar() {
		System.out.println("FrameInicial.controlePessoasIniciar");
		configuraBotoes();
		configuraTxtPesquisa();
		FrameInicial.setTabela(tblPessoas(""));
		FrameInicial.getTabela().setRowSelectionInterval(0, 0);
		p = tblMdPessoa.getPessoa(FrameInicial.getTabela().getSelectedRow());
		FrameInicial.setPainelVisualiza(new PainelPessoa(p));
		carregaDetalhes(p);
	}
	public void iniciar(String grupo) {
		System.out.println("FrameInicial.controlePessoasIniciar");
		configuraBotoes();
		configuraTxtPesquisa();
		FrameInicial.setTabela(tblPessoas(grupo));
		FrameInicial.getTabela().setRowSelectionInterval(0, 0);
		p = tblMdPessoa.getPessoa(FrameInicial.getTabela().getSelectedRow());
		FrameInicial.setPainelVisualiza(new PainelPessoa(p));
		carregaDetalhes(p);
	}

	/**
	 * 
	 * 
	 * @param codiPessoa
	 * @return nomePessoa
	 * 
	 */

	public String pesqCodigoNome(String nomePessoa) {
		Pessoa pessoa = daoP.pessoaCodigo(nomePessoa);
		return pessoa.getCodiPessoa();
	}
	/**
	 * Retorna o Codigo da pessoa atravÈs do nome
	 * 
	 * @param codiPessoa
	 * @return nomePessoa
	 * 
	 */
	public String pesqNomeCodigo(String nomePessoa) {
		Pessoa pessoa = daoP.pessoaCodigo(nomePessoa);
		return pessoa.getNome();
	}

	// TODO Tabela que adiciona usu·rios ao pedido
	public JTable pesqNomeTabelaAdicionaUsuarioAopedido(String str) {
		ArrayList<String> colunas = new ArrayList<>();
		tbl01 = new JTable();
		DefaultTableModel modelotabela = new DefaultTableModel();
		modelotabela = (DefaultTableModel) tbl01.getModel();
		tbl01.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Ao escrever
			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				// TODO Ao Soltar a tecla
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					int posicao = tbl01.getSelectedRow();
				}
			}

			@Override
			public void keyPressed(KeyEvent tecla) {
				// TODO Ao Pressionar Tecla
				int posicao = tbl01.getSelectedRow();
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 10) {
					PainelPedidos.adicionaUsuario(arrayPessoa.get(posicao));
				} else if (tecla.getKeyCode() == 9) {
					// PainelPedidos.getTxtfCondPag().grabFocus();
				}
			}
		});
		tbl01.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Ao soltar o bot√£o do mouse
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Ao Pressionar o bot√£o do mouse
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
				int posicao = tbl01.getSelectedRow();
				PainelPedidos.adicionaUsuario(arrayPessoa.get(posicao));
			}
		});
		colunas.add("Nome");
		colunas.add("CPF");
		colunas.add("Email");
		arrayPessoa = new ArrayList<>();
		arrayPessoa = daoP.pesquisaString(str);
		modelotabela.setColumnIdentifiers(colunas.toArray());
		for (int i = 0; i < arrayPessoa.size(); i++) {
			Object linha[] = {arrayPessoa.get(i).getNome(),
					arrayPessoa.get(i).getCpf(), arrayPessoa.get(i).getEmail()};
			modelotabela.addRow(linha);
		}
		tbl01.setShowGrid(true);
		tbl01.setModel(modelotabela);
		return tbl01;
	}

	public JTable pesqNomeTblAddUsuariLanc(String str) {
		tbl01 = new JTable();
		TableModelPessoa mdlTblPess = new TableModelPessoa(
				daoP.pesquisaString(str));
		tbl01.setModel(mdlTblPess);
		tbl01.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Ao escrever
			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				// TODO Ao Soltar a tecla
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					Pessoa p = mdlTblPess.getPessoa(tbl01.getSelectedRow());
					PainelLancamento.adicionaUsuario(p);
				}
			}

			@Override
			public void keyPressed(KeyEvent tecla) {
				// TODO Ao Pressionar Tecla
				Pessoa p = mdlTblPess.getPessoa(tbl01.getSelectedRow());
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 10) {
					PainelLancamento.adicionaUsuario(p);
				} else if (tecla.getKeyCode() == 9) {
					// PainelPedidos.getTxtfCondPag().grabFocus();
				}
			}
		});
		tbl01.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Ao Clicar
				Pessoa p = mdlTblPess.getPessoa(tbl01.getSelectedRow());
				PainelLancamento.adicionaUsuario(p);
			}
		});

		tbl01.setShowGrid(true);
		return tbl01;
	}

	public List<Pessoa> pesquisar() {
		arrayPessoa = new ArrayList<Pessoa>();
		arrayPessoa = daoP.listaPessoas();
		for (int i = 0; i < arrayPessoa.size(); i++) {
			System.out.println(arrayPessoa.get(i).getCpf() + "Nome"
					+ arrayPessoa.get(i).getNome());
		}
		return arrayPessoa;

	}

	public String carregarNomeGrupoCodigo(String codiGrupo) {
		return daoG.pesquisarNomeCodigo(codiGrupo);
	}

	public String carregarCodigoGrupoNome(String nomeGrupo) {
		return daoG.pesquisarCodigoNome(nomeGrupo);
	}

	public JComboBox<String> carregarGrupos() {
		cmbGrupos = new JComboBox<String>();
		cmbGrupos.addItem("Grupos");
		for (int i = 0; i < listGrupo.size(); i++) {
			cmbGrupos.addItem(listGrupo.get(i).getNomeGrupo());
		}
		return cmbGrupos;

	}

	public boolean logar(Pessoa p) {
		// TODO Auto-generated method stub
		return false;
	}

	public void cadastrar(Pessoa u2, String confirmaSenha,
			String confirmaEmail) {
		// TODO Auto-generated method stub
	}

	public String criaCodiUsuario() {
		Calendar c = Calendar.getInstance();
		String codi = String.valueOf(daoP.consultaUltimo())
				+ String.valueOf(c.get(Calendar.YEAR))
				+ String.valueOf(c.get(Calendar.MONTH))
				+ String.valueOf(c.get(Calendar.DAY_OF_MONTH));

		return codi;
	}

	// TODO FunÁ„o sobrescrever
	public void funcaoSobrescrever() {
		System.out.println("ControlaUsuario.funcaoSobrescrever");
		ControlaBotoes.limparBtnSalvar();
		PainelPessoa.habilitaEdicao();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p = PainelPessoa.lerCampos();
				if (!p.equals(null) & daoP.altera(p)) {
					FrameInicial.setTabela(tblPessoas((p.getCodiPessoa())));
					FrameInicial.setPainelVisualiza(new PainelPessoa(p));
					FrameInicial.atualizaTela();
					JOptionPane.showMessageDialog(null, "Feito!");
					FrameInicial.getContPess()
							.iniciar(AbaCadastros.getNomeNo());
				} else {
					JOptionPane.showMessageDialog(null,
							"Favor verificar os campos informados. ",
							"N„o foi possivel alterar!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	// TODO FunÁ„o Salvar
	public static void funcaoSalvar() {
		System.out.println("ControlaUsuario.funcaoSalvar");
		ControlaBotoes.limparBtnSalvar();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p = PainelPessoa.lerCampos();
				if (!p.equals(null) & daoP.cadastra(p)) {
					PainelPessoa.limparCampos();
					FrameInicial.setTabela(pesqNomeTabela(p.getCodiPessoa()));
					FrameInicial.setPainelVisualiza(new PainelPessoa(p));
					FrameInicial.atualizaTela();
					JOptionPane.showMessageDialog(null, "Feito");
					FrameInicial.getContPess()
							.iniciar(AbaCadastros.getNomeNo());
				} else {
					JOptionPane.showMessageDialog(null,
							"Problemas: Erro de acesso ao banco",
							"Erro ao Salvar", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public static void funcaoCancelar() {
		System.out.println("ControlaUsuario.cancelar");
		FrameInicial.getContPess().iniciar("");
	}

	public List<PessoaProfissional> listProf(Pessoa p) {
		List<PessoaProfissional> listEmpregos = daoPP
				.leFuncaoCodigo(p.getCodiPessoa());

		return listEmpregos;

	}

	// TODO Funcao excluir
	public static boolean funcaoExcluir() {
		System.out.println("ControlaProduto.excluir");
		p = PainelPessoa.lerCampos();
		if (daoP.exclui(p)) {
			FrameInicial.limpaTela();
			funcaoCancelar();
			return true;
		} else {
			funcaoCancelar();
			return false;
		}
	}
	public JTable carregaProfissoes(Pessoa p) {
		tblMdProf = new TableModelProfissao(
				daoPP.leFuncaoCodigo(p.getCodiPessoa()));
		tbl02 = new JTable(tblMdProf);

		return tbl02;
	}
	public JTable carregaUltPed(Pessoa p) {
		tblMdUltP = new TableModelPedidos(
				daoPed.carregaPedCliente(p.getCodiPessoa()));
		tbl03 = new JTable(tblMdUltP);
		return tbl03;
	}
	void configuraBotoes() {
		ControlaBotoes.limpaTodosBotoes();
		ControlaBotoes.desHabilitaEdicaoBotoes();
		FrameInicial.getBtnEditar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlaBotoes.habilitaEdicaoBotoes();
				funcaoSobrescrever();
			}
		});
		FrameInicial.getBtnNovo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameInicial.setTabela(new JTable());
				ControlaBotoes.clicaNovoBotoes();
				PainelPessoa.habilitaNovo();
				FrameInicial.atualizaTela();
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
	void configuraTxtPesquisa() {
		FrameInicial.limparTxtfPesquisa();
		FrameInicial.getTxtfPesquisa().grabFocus();
		FrameInicial.getTxtfPesquisa().addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent tecla) {
				if (tecla.getExtendedKeyCode() == 40) {
					if (FrameInicial.getTabela().getRowCount() > 0) {
						FrameInicial.getTabela().grabFocus();
						FrameInicial.getScrFluxo().setViewportView(null);
					}
				} else if (tecla.getExtendedKeyCode() == 27) {
					funcaoCancelar();
				} else {
					String str = FrameInicial.getTxtfPesquisa().getText();
					FrameInicial.setTabela(tblPessoas(str));
					if (tbl01.getRowCount() > 0) {
						FrameInicial.getTabela().setRowSelectionInterval(0, 0);
						p = tblMdPessoa.getPessoa(tbl01.getSelectedRow());
						carregaDetalhes(p);
						FrameInicial.getScrFluxo().setViewportView(null);
					} else {
						FrameInicial.setTabela(new JTable());
						p = new Pessoa();
						carregaDetalhes(p);
						JLabel label = new JLabel("Nada encontrado!!");
						label.setFont(
								new Font("Times New Roman", Font.BOLD, 48));
						label.setForeground(Color.RED);
						FrameInicial.getScrFluxo().setViewportView(label);
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				if (tecla.getExtendedKeyCode() == 40) {
					if (tbl01.getRowCount() > 0) {
						FrameInicial.getTabela().setRowSelectionInterval(0, 0);
						p = tblMdPessoa.getPessoa(tbl01.getSelectedRow());
						carregaDetalhes(p);
						FrameInicial.getScrFluxo().setViewportView(null);
					}

				} else if (tecla.getExtendedKeyCode() == 27) {
					funcaoCancelar();
				} else {
					String str = FrameInicial.getTxtfPesquisa().getText();
					FrameInicial.setTabela(tblPessoas(str));
					if (FrameInicial.getTabela().getRowCount() > 0) {
						FrameInicial.getTabela().setRowSelectionInterval(0, 0);
						p = tblMdPessoa.getPessoa(tbl01.getSelectedRow());
						carregaDetalhes(p);
						FrameInicial.getScrFluxo().setViewportView(null);
					} else {
						FrameInicial.setTabela(new JTable());
						p = new Pessoa();
						carregaDetalhes(p);
						JLabel label = new JLabel("Nada encontrado!!");
						label.setFont(
								new Font("Times New Roman", Font.BOLD, 48));
						label.setForeground(Color.RED);
						FrameInicial.getScrFluxo().setViewportView(label);
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

	}
	public void carregaDetalhes(Pessoa p) {
		PainelPessoa.carregarCampos(p);
		FrameInicial.atualizaTela();
	}

	public JTable adicionaOcup(Pessoa p) {
		p.setListOcup(daoPP.leFuncaoCodigo(p.getCodiPessoa()));
		if (p.getListOcup().isEmpty()) {
			tblMdProf = new TableModelProfissao();
			tbl02 = new JTable(tblMdProf);
			tbl02.addRowSelectionInterval(0, 0);
			tblMdProf.adicionaOcup(p, tbl02.getSelectedRow());
			tbl02 = new JTable(tblMdProf);
			carregaDetalhes(p);
		} else {
			tblMdProf = new TableModelProfissao(p.getListOcup());
			tbl02 = new JTable(tblMdProf);
			carregaDetalhes(p);
		}

		return tbl02;

	}

	public void apagaOcup() {
		if (!tbl02.equals(null) & tbl02.getSelectedRow() != -1) {
			PessoaProfissional pp = tblMdProf
					.getPessProf(tbl02.getSelectedRow());
			daoPP.apagar(pp);
			carregaDetalhes(p);
		} else {

		}
	}
}
