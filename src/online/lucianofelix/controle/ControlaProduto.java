package online.lucianofelix.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import online.lucianofelix.beans.Produto;
import online.lucianofelix.beans.ProdutoEstoque;
import online.lucianofelix.dao.DAOProdutoPrepSTM;
import online.lucianofelix.dao.DAOProdutosCotacao;
import online.lucianofelix.dao.DAOProdutosEstoque;
import online.lucianofelix.visao.FrameInicial;
import online.lucianofelix.visao.PainelPedidos;
import online.lucianofelix.visao.PainelProdutos;
import online.lucianofelix.visao.FrameInicial.ControlaBotoes;

public class ControlaProduto {
	String resposta;
	Produto prod;
	DAOProdutoPrepSTM daoProd;
	ControlaListaProdutos controledaLista;
	private DAOProdutosCotacao daoProdCota;
	private DAOProdutosEstoque daoProdEstoque;
	private JTable tabela;
	List<Produto> listProd;

	public ControlaProduto() {
		daoProd = new DAOProdutoPrepSTM();
		daoProdCota = new DAOProdutosCotacao();
		daoProdEstoque = new DAOProdutosEstoque();
		listProd = new ArrayList<Produto>(daoProd.pesquisaString(""));
	}

	// TODO Novo Movimento
	void novoMovimentoProduto(String codiEstoque, Date dataHoraMovimento,
			String codiProduto, int quantidade, String codiPedido,
			String tipoMovimento) {
		System.out.println("ControlaProduto.novoMovimentoProduto");
		daoProdEstoque.novoMovProdEstoque(codiEstoque, dataHoraMovimento,
				codiProduto, quantidade, codiPedido, tipoMovimento);
	}

	// TODO Salvar
	public void funcaoSalvar() {
		System.out.println("ControlaProduto.funcaoSalvar");
		funcaoCancelar();
		ControlaBotoes.limparBtnSalvar();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prod = PainelProdutos.lerCampos();
				if (!prod.equals(null) & daoProd.cadastrar(prod)) {
					PainelProdutos.limparCampos();
					FrameInicial
							.setTabela(pesqNomeTabela(prod.getCodi_prod_1()));
					FrameInicial.setPainelVisualiza(
							new PainelProdutos(prod.getCodi_prod_1()));
					FrameInicial.atualizaTela();
					JOptionPane.showMessageDialog(null, "Feito!");
					FrameInicial.pesquisaProduto();
				} else {
					JOptionPane.showMessageDialog(null,
							"Problemas: Erro de acesso ao banco",
							"Erro ao Salvar", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void funcaoCancelarNovo() {
		ControlaBotoes.limparBtnCancelar();
		FrameInicial.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PainelProdutos.desHabilitaEdicao();
				prod = PainelProdutos.lerCampos();
				if (daoProd.excluir(prod)) {
					FrameInicial.pesquisaProduto();
					funcaoCancelar();
				} else {
					JOptionPane.showMessageDialog(null,
							"Problemas: Erro ao apagar a reserva de código",
							"Erro ao Excluir", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	// TODO Criar código
	public String criaCodiProd() {
		System.out.println("ControlaProduto.criarCodigo");
		Calendar c = Calendar.getInstance();
		String codiProd = String.valueOf(daoProd.consultaUltimo())
				+ String.valueOf(c.get(Calendar.YEAR))
				+ String.valueOf(c.get(Calendar.MONTH))
				+ String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		return codiProd;
	}

	public void reservaCodigo(String codigo) {
		try {
			daoProd.reservaCodigo(codigo);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro de acesso ao banco",
					"Erro", JOptionPane.ERROR_MESSAGE);
			FrameInicial.pesquisaProduto();
			e.printStackTrace();
		}
	}

	// TODO Função sobrescrever
	public void funcaoSobrescrever() {
		System.out.println("ControlaProduto.funcaoSobrescrever");
		funcaoCancelar();
		ControlaBotoes.limparBtnSalvar();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prod = PainelProdutos.lerCampos();
				if (!prod.equals(null) & daoProd.alterar(prod)) {
					PainelProdutos.limparCampos();
					FrameInicial
							.setTabela(pesqNomeTabela(prod.getCodi_prod_1()));
					FrameInicial.setPainelVisualiza(
							new PainelProdutos(prod.getCodi_prod_1()));
					FrameInicial.atualizaTela();
					JOptionPane.showMessageDialog(null, "Feito!");
					FrameInicial.pesquisaProduto();
				} else {
					JOptionPane.showMessageDialog(null,
							"Favor verificar os campos informados. ",
							"Não foi possivel alterar o produto!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	// TODO Funcao excluir
	public boolean funcaoExcluir() {
		System.out.println("ControlaProduto.excluir");
		prod = PainelProdutos.lerCampos();
		if (daoProd.excluir(prod)) {
			FrameInicial.limpaTela();
			JOptionPane.showMessageDialog(null, "Feito!");
			FrameInicial.pesquisaProduto();
			return true;
		} else {
			JOptionPane.showMessageDialog(null,
					"Favor verificar os campos informados. ",
					"Não foi possivel excluir!", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	public void funcaoCancelar() {
		System.out.println("ControlaProduto.cancelar");
		ControlaBotoes.limparBtnCancelar();
		FrameInicial.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameInicial.pesquisaProduto();
			}
		});
	}

	// TODO Consultar Estoque

	public int consultaEstoque(Produto prod) {
		System.out.println("ControlaProduto.consultaEstoque");
		int totalEntra = 0;
		int totalSai = 0;
		int totalAtual = 0;
		List<ProdutoEstoque> listMov = new ArrayList<ProdutoEstoque>();
		DAOProdutosEstoque daoestoque = new DAOProdutosEstoque();
		listMov = daoestoque.conEntrSaiProdOrdSeqAscend(prod, "Sai");
		for (ProdutoEstoque produtoEstoque : listMov) {
			totalSai = totalSai + produtoEstoque.getQuantidade();
		}
		listMov = daoestoque.conEntrSaiProdOrdSeqAscend(prod, "Entra");
		for (ProdutoEstoque produtoEstoque : listMov) {
			totalEntra = totalEntra + produtoEstoque.getQuantidade();
		}
		totalAtual = totalEntra - totalSai;
		return totalAtual;
	}

	public void novoPreco(String codiTabela, java.sql.Date dataHoraMarcaca,
			String codiProduto, float valor) throws SQLException {

		// TODO Cadastra um novo preço para o produto na tabela selecionada;
		// java.sql.Date dataSql = manData.sqlDate(dataHoraMarcaca);
		System.out.println("ControlaProduto.novoPreço");
		daoProdCota.novoPrecoProduto(codiTabela, dataHoraMarcaca, codiProduto,
				valor);

	}

	public void cadastrar(Produto prod) {
		System.out.println("ControlaProduto.cadastrar");
		daoProd.cadastrar(prod);
	}

	public void posicionarTabela(int linha) {
		FrameInicial.getTabela().changeSelection(linha, 0, false, false);

	}

	public List<Produto> procurarTodos() {
		return daoProd.procurarTodos();
	}

	public JTable tabela() {
		ArrayList<String> colunas = new ArrayList<>();
		tabela = new JTable();
		DefaultTableModel modelotabela = new DefaultTableModel();
		modelotabela = (DefaultTableModel) tabela.getModel();
		colunas.add("Nome");
		colunas.add("Descrição");
		// colunas.add("Email");
		List<Produto> dados = new ArrayList<>();
		dados = daoProd.procurarTodos();
		modelotabela.setColumnIdentifiers(colunas.toArray());
		for (int i = 0; i < dados.size(); i++) {
			Object linha[] = {dados.get(i).getNome_prod(),
					String.valueOf(dados.get(i).getDesc_prod())};
			modelotabela.addRow(linha);
		}
		tabela.setShowGrid(true);
		tabela.setModel(modelotabela);
		return tabela;
	}

	// TODO Ajustar tamanho das colunas
	private void ajusta_tamanho_coluna() {
		// tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(260);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
		// tabela.getColumnModel().getColumn(3).setPreferredWidth(80);
		// tabela.getColumnModel().getColumn(4).setPreferredWidth(300);
		// tabela.getColumnModel().getColumn(5).setPreferredWidth(80);
		// tabela.getColumnModel().getColumn(6).setPreferredWidth(80);
	}

	// TODO Tabela ligada ao painel de produtos
	public JTable pesqNomeTabela(String nome) {
		ArrayList<String> colunas = new ArrayList<String>();
		tabela = new JTable();
		DefaultTableModel modelotabela = new DefaultTableModel();
		modelotabela = (DefaultTableModel) tabela.getModel();
		tabela.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					int posicao = tabela.getSelectedRow();
					PainelProdutos.irParaPoicao(posicao);
				}
			}

			@Override
			public void keyPressed(KeyEvent tecla) {
				int posicao = tabela.getSelectedRow();
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					PainelProdutos.irParaPoicao(posicao);
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 10) {
					PainelProdutos.irParaPoicao(posicao);
					PainelProdutos.getBtnEditar().doClick();
					FrameInicial.getTabela().changeSelection(--posicao, 0,
							false, false);
					PainelProdutos.getTxtFNomeProd().grabFocus();
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
				PainelProdutos.irParaPoicao(tabela.getSelectedRow());

			}
		});
		colunas.add("Código");
		colunas.add("Nome");
		colunas.add("Preço");
		listProd = new ArrayList<Produto>();
		listProd = daoProd.pesquisaString(nome);
		modelotabela.setColumnIdentifiers(colunas.toArray());
		for (int i = 0; i < listProd.size(); i++) {
			prod = listProd.get(i);
			Object linha[] = {prod.getCodi_prod_1(),
					String.valueOf(prod.getNome_prod()), prod.getPrec_prod_1()};
			modelotabela.addRow(linha);
		}
		ajusta_tamanho_coluna();
		tabela.setShowGrid(true);
		tabela.setModel(modelotabela);
		// FrameInicial.getTabela().getParent().setBackground(Color.WHITE);
		return tabela;
	}

	// TODO Tabela ligada ao painel de pedidos
	public JTable pesqNomeTabelaAdicionaItemAopedido(String nome) {
		ArrayList<String> colunas = new ArrayList<>();
		tabela = new JTable();
		DefaultTableModel modelotabela = new DefaultTableModel();
		modelotabela = (DefaultTableModel) tabela.getModel();
		tabela.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Ao escrever
			}

			@Override
			public void keyReleased(KeyEvent tecla) {

				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					int posicao = tabela.getSelectedRow();
				}
			}

			@Override
			public void keyPressed(KeyEvent tecla) {
				int posicao = tabela.getSelectedRow();
				System.out.println(tecla.getExtendedKeyCode());
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 10) {
					PainelPedidos.adicionaItem(listProd.get(posicao));
				} else if (tecla.getExtendedKeyCode() == 9) {
					PainelPedidos.getTxtfCliente().grabFocus();
				}
			}
		});
		tabela.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Ao soltar o botão do mouse

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
				int posicao = tabela.getSelectedRow();
				PainelPedidos.adicionaItem(listProd.get(posicao));
			}
		});
		colunas.add("Código");
		colunas.add("Nome");
		colunas.add("Preço");

		modelotabela.setColumnIdentifiers(colunas.toArray());
		for (int i = 0; i < listProd.size(); i++) {
			prod = listProd.get(i);
			Object linha[] = {prod.getCodi_prod_1(), prod.getNome_prod(),
					prod.getPrec_prod_1()};
			modelotabela.addRow(linha);
		}
		tabela.setShowGrid(true);
		tabela.setModel(modelotabela);
		return tabela;
	}

	public Produto carregarCotacoes(Produto prod) {
		prod.setListCotacaoProduto(daoProdCota.conCotProdOrdSeqDesc(prod));
		return prod;
	}

	public List<Produto> pesqNomeArray(String nome) {
		daoProd = new DAOProdutoPrepSTM();
		return daoProd.pesquisaString(nome);
	}

	public Produto procurar(String codiProduto) {
		return daoProd.procurar(codiProduto);
	}

	public Produto procurarProximo(int codiProd) {
		if (codiProd == 0) {
			codiProd++;
		}
		return daoProd.procurarProximo(codiProd);
	}

	public Produto procurarAnterior(int codiProd) {
		--codiProd;
		prod = daoProd.procurarAnterior(codiProd);
		return prod;
	}

	public int consultaUltimo() {
		return daoProd.consultaUltimo();
	}
}
