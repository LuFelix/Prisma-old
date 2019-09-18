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
import javax.swing.table.DefaultTableModel;

import online.lucianofelix.beans.GrupoSubgrupo;
import online.lucianofelix.beans.TipoSistema;
import online.lucianofelix.dao.DAOGrupoSubgrupo;
import online.lucianofelix.dao.DAOTiposSistema;
import online.lucianofelix.tableModels.commom.TableModelGrupoCategoria;
import online.lucianofelix.visao.FrameInicial;
import online.lucianofelix.visao.FrameInicial.ControlaBotoes;
import online.lucianofelix.visao.PainelConta;
import online.lucianofelix.visao.PainelSubGrupo;

public class ControlaGrupoSubgrupo {
	private JTable tabela;
	private List<GrupoSubgrupo> listGrupo;
	private DAOGrupoSubgrupo daoGrupo;
	private GrupoSubgrupo grupo;
	private String nome;
	private JTable tbl01;
	private TableModelGrupoCategoria mdlTblGrupo;
	private JComboBox<String> cmbRaizes;
	private JComboBox<String> cmbSubGrupo;
	private DAOTiposSistema daoTipoS;
	private List<TipoSistema> listRaizes;

	public ControlaGrupoSubgrupo() {
		System.out.println("ControlaGrupoSubgrupo.construtor");
		daoGrupo = new DAOGrupoSubgrupo();
		daoTipoS = new DAOTiposSistema();
		listGrupo = daoGrupo.pesquisarString("");
		listRaizes = daoTipoS.pesquisaString("");
	}

	// TODO Função Salvar
	public void funcaoSalvar() {
		System.out.println("ControlaGrupo.funcaoSalvar");
		ControlaBotoes.limparBtnSalvar();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grupo = PainelSubGrupo.lerCampos();

				if (!grupo.equals(null) & daoGrupo.cadastrar(grupo)) {
					PainelSubGrupo.limparCampos();
					FrameInicial.setTabela(tblGrupo(grupo.getCodiGrupo()));
					FrameInicial.setPainelVisualiza(
							new PainelSubGrupo(grupo.getCodiGrupo()));
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

	// TODO Criar código
	public String criaCodigo() {
		System.out.println("ControlaGrupoSubgrupo.criarCodigo");
		Calendar c = Calendar.getInstance();
		String codigo = String.valueOf(daoGrupo.consultaUltimo())
				+ String.valueOf(c.get(Calendar.YEAR))
				+ String.valueOf(c.get(Calendar.MONTH))
				+ String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		return codigo;
	}

	public GrupoSubgrupo nomeAproximado() {
		int tam = daoGrupo.consultaUltimo();
		if (tam == 0) {
			FrameInicial.setPainelVisualiza(new PainelSubGrupo(""));
			FrameInicial.getScrVisualiza()
					.setViewportView(FrameInicial.getPainelVisualiza());
			return null;
		} else {
			return daoGrupo.pesquisarString("").get(0);
		}
	}

	public void funcaoCancelar() {
		System.out.println("ControlaGrupo.cancelar");
		iniciar();
	}

	public List<GrupoSubgrupo> pesqNomeArray(String str) {
		return daoGrupo.pesquisarString(str);
	}

	// tabela fora de utilização
	public JTable pesqNomeTabelaOld(String str) {
		tabela = new JTable();
		List<String> colunas = new ArrayList<String>();
		DefaultTableModel modelotabela = new DefaultTableModel();
		modelotabela = (DefaultTableModel) tabela.getModel();
		listGrupo = new ArrayList<GrupoSubgrupo>(daoGrupo.pesquisarString(str));
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
					grupo = listGrupo.get(posicao);
					PainelSubGrupo.carregarCampos(grupo);
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				}
			}

			@Override
			public void keyPressed(KeyEvent tecla) {
				int posicao = tabela.getSelectedRow();
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					PainelSubGrupo.carregarCampos(grupo);
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 10) {
					PainelSubGrupo.carregarCampos(grupo);
					funcaoSobrescrever();
					FrameInicial.getTabela().changeSelection(--posicao, 0,
							false, false);
					PainelSubGrupo.getTxtFNomeGrupo().grabFocus();
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
				int posicao = tabela.getSelectedRow();
				PainelSubGrupo.irParaPoicao(posicao);
				System.out.println(tabela.getMouseListeners());
			}
		});
		colunas.add("Número");
		colunas.add("Grupo");
		colunas.add("Subgrupo");
		modelotabela.setColumnIdentifiers(colunas.toArray());
		for (int i = 0; i < listGrupo.size(); i++) {
			Object linha[] = {listGrupo.get(i).getSeqGrupo(),
					listGrupo.get(i).getNoAncora(),
					listGrupo.get(i).getNomeGrupo()};
			modelotabela.addRow(linha);
		}
		tabela.setShowGrid(true);
		tabela.setModel(modelotabela);
		return tabela;
	}

	public JTable tblGrupo(String str) {
		tbl01 = new JTable();
		mdlTblGrupo = new TableModelGrupoCategoria(daoGrupo.listRaizes());
		tbl01.setModel(mdlTblGrupo);
		tbl01.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					grupo = mdlTblGrupo.getGrupo(tbl01.getSelectedRow());
					PainelSubGrupo.carregarCampos(grupo);
					FrameInicial.atualizaTela();
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				}
			}

			@Override
			public void keyPressed(KeyEvent tecla) {
				int posicao = tbl01.getSelectedRow();
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					grupo = mdlTblGrupo.getGrupo(tbl01.getSelectedRow());
					PainelSubGrupo.carregarCampos(grupo);
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 10) {
					grupo = mdlTblGrupo.getGrupo(tbl01.getSelectedRow());
					PainelSubGrupo.carregarCampos(grupo);
					funcaoSobrescrever();
					FrameInicial.getTabela().changeSelection(--posicao, 0,
							false, false);
					PainelConta.getTxtFNomeConta().grabFocus();
				}
			}
		});
		tbl01.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				grupo = mdlTblGrupo.getGrupo(tbl01.getSelectedRow());
				PainelSubGrupo.carregarCampos(grupo);
				FrameInicial.atualizaTela();
			}
		});

		tbl01.setShowHorizontalLines(true);
		return tbl01;
	}

	// TODO Função sobrescrever
	public void funcaoSobrescrever() {
		System.out.println("ControlaGrupoSubgrupo.funcaoSobrescrever");
		ControlaBotoes.limparBtnSalvar();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grupo = PainelSubGrupo.lerCampos();
				if (!grupo.equals(null) & daoGrupo.alterar(grupo)) {
					FrameInicial.setTabela(tblGrupo(grupo.getCodiGrupo()));
					FrameInicial.setPainelVisualiza(
							new PainelSubGrupo(grupo.getCodiGrupo()));
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

	// TODO Funcao excluir
	public boolean funcaoExcluir() {
		System.out.println("ControlaGrupoSubgrupo.excluir");
		grupo = PainelSubGrupo.lerCampos();
		if (daoGrupo.excluir(grupo)) {
			FrameInicial.limpaTela();
			funcaoCancelar();
			return true;
		} else {
			funcaoCancelar();
			return false;
		}
	}

	public JTable getTabela() {
		return tabela;
	}

	public void setTabela(JTable tabela) {
		this.tabela = tabela;
	}

	void configuraBotoes() {
		ControlaBotoes.limpaTodosBotoes();
		ControlaBotoes.desHabilitaEdicaoBotoes();
		ActionListener acaoEditar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelSubGrupo.habilitaEdicao();
				ControlaBotoes.habilitaEdicaoBotoes();
				funcaoSobrescrever();
			}
		};
		FrameInicial.getBtnEditar().addActionListener(acaoEditar);

		FrameInicial.getBtnNovo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlaBotoes.habilitaNovoBotoes();
				PainelSubGrupo.habilitaNovo();
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

	};
	void configuraTxtPesquisa() {
		FrameInicial.limparTxtfPesquisa();
		FrameInicial.getTxtfPesquisa().grabFocus();
		FrameInicial.getTxtfPesquisa().addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent tecla) {
				if (tecla.getExtendedKeyCode() == 40) {// seta para baixo
					getTabela().grabFocus();
					getTabela().changeSelection(0, 0, false, false);
				} else if (tecla.getExtendedKeyCode() == 27) {
					funcaoCancelar();
				} else {
					// System.out.println(tecla.getExtendedKeyCode());
					nome = FrameInicial.getTxtfPesquisa().getText();
					setTabela(tblGrupo(nome));
					FrameInicial.setPainelVisualiza(new PainelSubGrupo(nome));
					FrameInicial.atualizaTela();
				}
			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				nome = FrameInicial.getTxtfPesquisa().getText();
				setTabela(tblGrupo(nome));
				FrameInicial.setPainelVisualiza(new PainelSubGrupo(nome));
				FrameInicial.atualizaTela();
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

	}

	// TODO Pesquisa Grupo.
	public void iniciar() {
		System.out.println("FrameInicial.pesquisaGrupo");
		configuraBotoes();
		configuraTxtPesquisa();
		FrameInicial.setTabela(tblGrupo(""));
		FrameInicial.getTabela().setRowSelectionInterval(0, 0);
		grupo = mdlTblGrupo.getGrupo(FrameInicial.getTabela().getSelectedRow());
		FrameInicial.setPainelVisualiza(new PainelSubGrupo(grupo));
		FrameInicial.atualizaTela();
	}
	public String carregarNomeGrupoCodigo(String codiGrupo) {
		return daoGrupo.pesquisarNomeCodigo(codiGrupo);
	}

	public String carregarCodigoGrupoNome(String nomeGrupo) {
		return daoGrupo.pesquisarCodigoNome(nomeGrupo);
	}

	public JComboBox<String> carregarRaizes() {
		cmbRaizes = new JComboBox<String>();
		for (int i = 0; i < listRaizes.size(); i++) {
			cmbRaizes.addItem(listRaizes.get(i).getNomeTipoSistema());
		}
		return cmbRaizes;

	}
	public JComboBox<String> carregarSubGrupos() {
		cmbSubGrupo = new JComboBox<String>();
		for (int i = 0; i < listGrupo.size(); i++) {
			cmbSubGrupo.addItem(listGrupo.get(i).getNomeGrupo());
		}
		return cmbSubGrupo;

	}

	public List<GrupoSubgrupo> getListGrupo() {
		return listGrupo;
	}

	public void setListGrupo(List<GrupoSubgrupo> listGrupo) {
		this.listGrupo = listGrupo;
	}

	public DAOGrupoSubgrupo getDaoGrupo() {
		return daoGrupo;
	}

	public void setDaoGrupo(DAOGrupoSubgrupo daoGrupo) {
		this.daoGrupo = daoGrupo;
	}

	public GrupoSubgrupo getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoSubgrupo grupo) {
		this.grupo = grupo;
	}

}
