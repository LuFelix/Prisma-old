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

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import online.lucianofelix.beans.Produto;
import online.lucianofelix.beans.Servico;
import online.lucianofelix.dao.DAOServico;
import online.lucianofelix.visao.FrameInicial;
import online.lucianofelix.visao.PainelServico;
import online.lucianofelix.visao.FrameInicial.ControlaBotoes;

public class ControlaServico {
	String resposta;
	Produto prod;
	DAOServico daoServ;
	ControlaListaServicos controledaLista;
	private JTable tabela;
	ArrayList<Servico> arrayServ;
	private float total;
	private Servico serv;

	public ControlaServico() {
		daoServ = new DAOServico();
	}

	public void cadastrar(Servico serv) {
		daoServ = new DAOServico();
		try {
			daoServ.cadastrar(serv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void posicionarTabela(int linha) {
		System.out.println("Ir para a linha: " + linha);
		FrameInicial.getTabela().changeSelection(linha, 0, false, false);

	}

	// TODO Tabela ligada ao painel de serviços
	public JTable pesqNomeTabela(String nome) {
		daoServ = new DAOServico();
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
					PainelServico.irParaPoicao(posicao);
				}
			}

			@Override
			public void keyPressed(KeyEvent tecla) {
				int posicao = tabela.getSelectedRow();
				if (tecla.getExtendedKeyCode() == 40 || tecla.getExtendedKeyCode() == 38) {
					PainelServico.irParaPoicao(posicao);
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 10) {
					PainelServico.irParaPoicao(posicao);
					FrameInicial.getBtnEditar().doClick();
					FrameInicial.getTabela().changeSelection(--posicao, 0, false, false);
					PainelServico.getTxtFNomeServico().grabFocus();
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
				PainelServico.irParaPoicao(posicao);

			}
		});
		colunas.add("Nome");
		colunas.add("Descrição");
		colunas.add("Preço Hora");
		arrayServ = new ArrayList<>();
		arrayServ = daoServ.pesquisaString(nome);
		modelotabela.setColumnIdentifiers(colunas.toArray());
		for (int i = 0; i < arrayServ.size(); i++) {
			total = total + arrayServ.get(i).getPrecoHora();
			Object linha[] = { arrayServ.get(i).getNomeServico(), String.valueOf(arrayServ.get(i).getDescServico()),
					arrayServ.get(i).getPrecoHora() };
			modelotabela.addRow(linha);

		}
		tabela.setShowGrid(true);
		tabela.setModel(modelotabela);
		return tabela;
	}

	// TODO Tabela ligada ao painel de Ordem de serviços
	// public JTable pesqNomeTabelaAdicionaItemAopedido(String nome) {
	// daoServ = new DaoServico();
	// ArrayList<String> colunas = new ArrayList<>();
	// tabela = new JTable();
	// DefaultTableModel modelotabela = new DefaultTableModel();
	// modelotabela = (DefaultTableModel) tabela.getModel();
	// tabela.addKeyListener(new KeyListener() {
	// @Override
	// public void keyTyped(KeyEvent arg0) {
	// // TODO Ao escrever
	// }
	//
	// @Override
	// public void keyReleased(KeyEvent tecla) {
	// // TODO Ao Soltar a tecla
	// if (tecla.getExtendedKeyCode() == 40 || tecla.getExtendedKeyCode() == 38)
	// {
	// int posicao = tabela.getSelectedRow();
	// }
	// }
	// @Override
	// public void keyPressed(KeyEvent tecla) {
	// // TODO Ao Pressionar Tecla
	// int posicao = tabela.getSelectedRow();
	// System.out.println(tecla.getExtendedKeyCode());
	// if (tecla.getExtendedKeyCode() == 40 || tecla.getExtendedKeyCode() == 38)
	// {
	// } else if (tecla.getExtendedKeyCode() == 27) {// esc
	// FrameInicial.getTxtfPesquisa().grabFocus();
	// } else if (tecla.getExtendedKeyCode() == 10) {
	// PainelPedidos.adicionaItem(arrayServ.get(posicao));
	// } else if (tecla.getExtendedKeyCode() == 9) {
	// PainelPedidos.getTxtfCliente().grabFocus();
	// }
	// }
	// });
	// tabela.addMouseListener(new MouseListener() {
	// @Override
	// public void mouseReleased(MouseEvent arg0) {
	// // TODO Ao soltar o botÃ£o do mouse
	//
	// }
	//
	// @Override
	// public void mousePressed(MouseEvent arg0) {
	// // TODO Ao Pressionar o botÃ£o do mouse
	// }
	// @Override
	// public void mouseExited(MouseEvent arg0) {
	// // TODO Ao sair o mouse
	// }
	// @Override
	// public void mouseEntered(MouseEvent arg0) {
	// // TODO Ao entrar o mouse
	// }
	// @Override
	// public void mouseClicked(MouseEvent arg0) {
	// // TODO Ao Clicar
	// int posicao = tabela.getSelectedRow();
	// PainelPedidos.adicionaItem(arrayServ.get(posicao));
	// }
	// });
	// colunas.add("Nome");
	// colunas.add("Descrição");
	// colunas.add("Preço");
	// arrayServ = daoServ.pesquisaString(nome);
	// modelotabela.setColumnIdentifiers(colunas.toArray());
	// for (int i = 0; i < arrayServ.size(); i++) {
	// total = total + arrayServ.get(i).getPrecoHora();
	// Object linha[] = { arrayServ.get(i).getNomeServico(),
	// String.valueOf(arrayServ.get(i).getDescServico()),
	// arrayServ.get(i).getPrecoHora(), };
	// modelotabela.addRow(linha);
	// }
	// ;
	// tabela.setModel(modelotabela);
	// return tabela;
	// }
	public String criaCodigo() {
		Calendar c = Calendar.getInstance();
		String codiServico = String.valueOf(daoServ.consultaUltimo()) + String.valueOf(c.get(Calendar.YEAR))
				+ String.valueOf(c.get(Calendar.MONTH)) + String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		return codiServico;
	}

	// TODO Função Salvar
	public void funcaoSalvar() {
		ControlaBotoes.limparBtnSalvar();
		funcaoCancelarNovo();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!PainelServico.getTxtFCodiServico().getText().equals("")
						& !PainelServico.getTxtFNomeServico().equals("")) {
					serv = PainelServico.lerCampos();
					try {
						daoServ.alterar(serv);
						PainelServico.limparCampos();
						FrameInicial.setTabela(pesqNomeTabela(serv.getCodiServico()));
						FrameInicial.setPainelVisualiza(new PainelServico(serv.getCodiServico()));
						FrameInicial.atualizaTela();
						PainelServico.desHabilitaEdicao();
						JOptionPane.showMessageDialog(null, "Feito!");
						iniciar();
						FrameInicial.getBtnNovo().grabFocus();
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Problemas: Código Nulo ou Duplicado", "Erro ao Cadastrar",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Favor verificar os campos informados. ",
							"Não foi possivel gravar!", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
	}

	// TODO Função sobrescrever
	public void funcaoSobrescrever() {
		ControlaBotoes.limparBtnSalvar();
		funcaoCancelar();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!PainelServico.getTxtFCodiServico().getText().equals("")
						& !PainelServico.getTxtFNomeServico().equals(null)) {
					serv = PainelServico.lerCampos();
					try {
						daoServ.alterar(serv);
						PainelServico.limparCampos();
						ControlaBotoes.desHabilitaEdicaoBotoes();
						FrameInicial.setTabela(pesqNomeTabela(serv.getCodiServico()));
						FrameInicial.setPainelVisualiza(new PainelServico(serv.getCodiServico()));
						FrameInicial.atualizaTela();
						JOptionPane.showMessageDialog(null, "Feito!");
						iniciar();
					} catch (Exception e2) {
						PainelServico.desHabilitaEdicao();
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Erro ao gravar\n" + e2.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					PainelServico.desHabilitaEdicao();
					JOptionPane.showMessageDialog(null, "Não foi possível concluir!",
							"Favor verificar os campos informados!", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
	}

	public void funcaoCancelarNovo() {
		ControlaBotoes.limparBtnCancelar();
		FrameInicial.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PainelServico.desHabilitaEdicao();
				serv = PainelServico.lerCampos();
				daoServ.remover(serv);
				iniciar();
			}
		});
	}

	public void funcaoCancelar() {
		ControlaBotoes.limparBtnCancelar();
		FrameInicial.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				iniciar();
			}
		});
	}

	public void funcaoExcluir() {
		serv = PainelServico.lerCampos();
		if (daoServ.remover(serv)) {
			JOptionPane.showMessageDialog(null, "Feito!");
		}
	}

	public void iniciar() {
		System.out.println("ControlaServico.iniciar");
		ControlaBotoes.limpaTodosBotoes();
		ControlaBotoes.desHabilitaEdicaoBotoes();
		FrameInicial.limparTxtfPesquisa();
		FrameInicial.getTxtfPesquisa().grabFocus();
		FrameInicial.setTabela(pesqNomeTabela(""));
		FrameInicial.setPainelVisualiza(new PainelServico(""));
		FrameInicial.atualizaTela();
		FrameInicial.getBtnNovo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PainelServico.limparCampos();
				PainelServico.habilitaNovo();
				funcaoSalvar();
			}
		});

		FrameInicial.getBtnEditar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlaBotoes.habilitaEdicaoBotoes();
				PainelServico.habilitaEdicao();
				funcaoSobrescrever();
			}
		});
		FrameInicial.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				iniciar();
			}
		});
		FrameInicial.getBtnExcluir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Excluir
				PainelServico.lerCampos();
				funcaoExcluir();
				iniciar();
			}
		});
		FrameInicial.getTxtfPesquisa().addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent tecla) {
				if (tecla.getExtendedKeyCode() == 40) {
					FrameInicial.getTabela().grabFocus();
					FrameInicial.getTabela().changeSelection(0, 0, false, false);
				} else {
					String nome = FrameInicial.getTxtfPesquisa().getText();
					FrameInicial.setTabela(pesqNomeTabela(nome));
					FrameInicial.setPainelVisualiza(new PainelServico(nome));
					FrameInicial.atualizaTela();
				}

			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				String nome = FrameInicial.getTxtfPesquisa().getText();
				FrameInicial.setTabela(pesqNomeTabela(nome));
				FrameInicial.setPainelVisualiza(new PainelServico(nome));
				FrameInicial.atualizaTela();
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
	}

}
