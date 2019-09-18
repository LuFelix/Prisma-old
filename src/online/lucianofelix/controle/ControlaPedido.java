package online.lucianofelix.controle;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.BasicConfigurator;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import online.lucianofelix.beans.Lancamento;
import online.lucianofelix.beans.Pedido;
import online.lucianofelix.beans.Produto;
import online.lucianofelix.dao.DAOLancamento;
import online.lucianofelix.dao.DAOPedidoPrepSTM;
import online.lucianofelix.dao.DAOProdutosEstoque;
import online.lucianofelix.dao.DAOProdutosPedidos;
import online.lucianofelix.visao.AbaNegocios;
import online.lucianofelix.visao.FrameInicial;
import online.lucianofelix.visao.FrameInicial.ControlaBotoes;
import online.lucianofelix.visao.PainelPedidos;

public class ControlaPedido {
	Pedido pedi;
	private String tipoMovEstoque;
	private String tipoMovConta;
	DAOPedidoPrepSTM daoPedi;
	ControlaListaPedidos clistPedi;
	private JTable tabela;
	List<Pedido> listPedi;
	private DAOProdutosPedidos daoProdPedi;
	private DAOProdutosEstoque daoProdEstoque;
	private DAOLancamento daoLancamento;

	public ControlaPedido() {
		pedi = new Pedido();
		daoPedi = new DAOPedidoPrepSTM();
		daoProdPedi = new DAOProdutosPedidos();
		daoProdEstoque = new DAOProdutosEstoque();
		daoLancamento = new DAOLancamento();
	}

	// TODO Sobrescrever
	public void funcaoSobrescrever() {
		ControlaBotoes.limparBtnSalvar();
		ControlaBotoes.habilitaEdicaoBotoes();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pedi = PainelPedidos.leCampos();
				if (pedi.getTipoPedido().equals("Compra")) {
					tipoMovEstoque = "Entra";
					tipoMovConta = "Sai";
				} else if (pedi.getTipoPedido().equals("Venda")) {
					tipoMovEstoque = "Sai";
					tipoMovConta = "Entra";
				}
				try {
					daoPedi.alterar(pedi);
					PainelPedidos.limparCampos();
					FrameInicial.setPainelVisualiza(
							new PainelPedidos(pedi.getCodiPedi()));
					FrameInicial.setTabela(tblPedidosNomeTipo(
							pedi.getCodiPedi(), pedi.getTipoPedido()));
					FrameInicial.atualizaTela();
					JOptionPane.showMessageDialog(null, "Feito!");
					iniciar(pedi.getTipoPedido());
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"Verifique os campos informados", "Erro ao gravar",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	// TODO Salvar
	public void funcaoSalvar() {
		System.out.println("PainelPedidos.funcaoSalvar");
		ControlaBotoes.limparBtnSalvar();
		funcaoCancelarNovo();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pedi = PainelPedidos.leCampos();
				try {
					daoPedi.alterar(pedi);
					FrameInicial.setTabela(tblPedidosNomeTipo(
							pedi.getCodiPedi(), pedi.getTipoPedido()));
					FrameInicial.setPainelVisualiza(
							new PainelPedidos(pedi.getCodiPedi()));
					FrameInicial.atualizaTela();
					PainelPedidos.desHabilitaEdicao();
					PainelPedidos.desbilitaTabela();
					JOptionPane.showMessageDialog(null, "Feito!");
					iniciar(pedi.getTipoPedido());
					FrameInicial.getBtnNovo().grabFocus();
				} catch (Exception e2) {
					e2.printStackTrace();

					JOptionPane.showMessageDialog(null,
							"Problemas: Verifique os campos informados",
							"Erro ao Salvar", JOptionPane.ERROR_MESSAGE);
					funcaoCancelarNovo();
				}
			}
		});

	}

	public boolean funcaoExcluir(Pedido pedi) {
		System.out.println("ControlaPedido.excluir");
		if (!pedi.equals(null)) {
			System.out.println("Codigo do pedido: " + pedi.getCodiPedi());
			daoPedi.remover(pedi);
			FrameInicial.limpaTela();
			JOptionPane.showMessageDialog(null, "Feito!");
			iniciar(pedi.getTipoPedido());
			return true;
		} else {
			iniciar(pedi.getTipoPedido());
			return false;
		}
	}

	public void funcaoCancelarNovo() {
		System.out.println("ControlaPedido.funcaoCancelarNovo");
		ControlaBotoes.limparBtnCancelar();
		FrameInicial.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pedi = PainelPedidos.leCampos();
				funcaoExcluir(pedi); // Apaga o pedido reservado
				iniciar(pedi.getTipoPedido());

			}
		});
	}

	// TODO Cancelar
	public void funcaoCancelar() {
		ControlaBotoes.limparBtnCancelar();
		FrameInicial.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				iniciar(AbaNegocios.getNomeNo());
			}
		});
	}

	// TODO Iniciar
	public void iniciar(final String tipoPedido) {
		ControlaBotoes.limpaTodosBotoes();
		ControlaBotoes.desHabilitaEdicaoBotoes();
		FrameInicial.limparTxtfPesquisa();
		FrameInicial.getTxtfPesquisa().grabFocus();
		System.out.println("Nova tela de pedidos");
		FrameInicial.setTabela(tblPedidosNomeTipo("", tipoPedido));
		configuraBotoes();
		FrameInicial.setPainelVisualiza(new PainelPedidos(""));
		FrameInicial.atualizaTela();
		funcaoCancelar();

		FrameInicial.getTxtfPesquisa().addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent tecla) {
				if (tecla.getExtendedKeyCode() == 40) {
					FrameInicial.getTabela().grabFocus();
					FrameInicial.getTabela().changeSelection(0, 0, false,
							false);
				} else {
					String nome = FrameInicial.getTxtfPesquisa().getText();
					FrameInicial
							.setTabela(tblPedidosNomeTipo(nome, tipoPedido));
					FrameInicial.setPainelVisualiza(new PainelPedidos(nome));
					FrameInicial.atualizaTela();
				}
			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				String nome = FrameInicial.getTxtfPesquisa().getText();
				FrameInicial.setTabela(tblPedidosNomeTipo(nome, tipoPedido));
				FrameInicial.setPainelVisualiza(new PainelPedidos(nome));
				FrameInicial.atualizaTela();
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

	}
	void configuraBotoes() {
		FrameInicial.getBtnEditar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlaBotoes.habilitaEdicaoBotoes();
				PainelPedidos.habilitaEdicao();
				FrameInicial.pesquisaProdutoAdicaoItem();
				funcaoSobrescrever();
			}
		});
		FrameInicial.getBtnNovo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ControlaBotoes.habilitaNovoBotoes();
				PainelPedidos.habilitaNovo();
				FrameInicial.pesquisaProdutoAdicaoItem();
				funcaoSalvar();
			}
		});
		FrameInicial.getBtnExcluir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pedi = PainelPedidos.leCampos();
				funcaoExcluir(pedi);
			}
		});
	}

	public void posicionarTabela(int linha) {
		System.out.println("Ir para a linha: " + linha);
		FrameInicial.getTabela().changeSelection(linha, 0, false, false);

	}

	// TODO Ajustar largura colunas
	private void ajusta_tamanho_coluna() {
		// tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(60);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(220);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(70);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
		// tabela.getColumnModel().getColumn(4).setPreferredWidth(300);
		// tabela.getColumnModel().getColumn(5).setPreferredWidth(80);
		// tabela.getColumnModel().getColumn(6).setPreferredWidth(80);
	}

	// TODO Tabela de pesquisa por nome filtra tipo
	public JTable tblPedidosNomeTipo(String nome, String tipo) {
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
					PainelPedidos.irParaPoicao(posicao);
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				}
			}

			@Override
			public void keyPressed(KeyEvent tecla) {
				int posicao = tabela.getSelectedRow();
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					PainelPedidos.irParaPoicao(posicao);
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 10) {
					PainelPedidos.irParaPoicao(posicao);
					FrameInicial.getTabela().changeSelection(--posicao, 0,
							false, false);
					PainelPedidos.getTxtfCliente().grabFocus();
				}
			}
		});
		tabela.addMouseListener(new MouseListener() {
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
				int posicao = tabela.getSelectedRow();
				PainelPedidos.irParaPoicao(posicao);
			}

		});

		colunas.add("Número");
		colunas.add("Nome");
		colunas.add("Ítens");
		colunas.add("Total");
		listPedi = daoPedi.pesquisarTipo(tipo);
		modelotabela.setColumnIdentifiers(colunas.toArray());
		ajusta_tamanho_coluna();
		for (int i = 0; i < listPedi.size(); i++) {
			Object linha[] = {listPedi.get(i).getSeqPedi(),
					listPedi.get(i).getxNome(), listPedi.get(i).getQuantItens(),
					listPedi.get(i).getTotalPedi()};
			modelotabela.addRow(linha);
		}
		tabela.setShowGrid(true);
		tabela.setModel(modelotabela);
		return tabela;
	}

	public void carregarProdutosPedido(Pedido pedi) {
		System.out.println("ControlaPedido.carregarProdutosPedido");
		pedi.setItensProduto(daoProdPedi.pesquisaItensPedido(pedi));
	}

	public void carregarPagamentosPedido(Pedido pedi) {
		System.out.println("ControlaPedido.carregarProdutosPedido");
		pedi.setLancPedido(daoLancamento.consultLancPedido(pedi));
	}

	public ArrayList<Pedido> listaPedidosTipo(String tipoPedido) {
		return daoPedi.pesquisarTipo(tipoPedido);
	}

	public Pedido procurar(int codiPedi) {
		return daoPedi.procurar(codiPedi);
	}

	public Pedido procurar(String nomePedi) {
		return null;
	}

	public void alterar(Pedido Pedi) {
	}

	public void remover(Pedido Pedi) {
	}

	public boolean listavazia() {
		if (daoPedi.tabelaVazia()) {
			return true;
		}
		return false;
	}

	// TODO Adicionar ítem
	public void adicionaItemProduto(Pedido pedi, Produto prod) {
		try {
			daoProdPedi.inserirItem(pedi, prod);
			carregarProdutosPedido(pedi);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// Adicionar condição de Pagamento

	// Remover conição de Pagamento

	// TODO Remover ítem.
	public void removerItem(Pedido pedi, Produto prod) {
		daoProdPedi.removerItem(pedi, prod);
	}

	// TODO Alterar a quantidade do ítem.
	public void alterarQuantProd(Pedido pedi, Produto prod) {
		if (prod.getQuantMovimento() <= 0) {
			daoProdPedi.removerItem(pedi, prod);
		} else {
			daoProdPedi.alterarQuantItem(pedi, prod);
		}
	}

	// TODO Capturar o QR code do CUPOM FISCAL
	public void capturaQR() {
		BasicConfigurator.configure();
		JButton btnFoto = new JButton("Foto");
		Webcam cam = Webcam.getDefault();
		Result result = null;
		BufferedImage image = null;
		WebcamPanel panel = new WebcamPanel(cam);
		panel.setLayout(new BorderLayout());
		panel.setFPSDisplayed(true);
		panel.setDisplayDebugInfo(true);
		panel.setImageSizeDisplayed(true);
		panel.setMirrored(true);
		panel.add(btnFoto, BorderLayout.NORTH);

		JFrame window = new JFrame("Captura");
		window.add(panel);
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.pack();
		window.setVisible(true);

		if (cam != null) {
			// System.out.println("Webcam Detectada: " + cam.getName());
			cam.open();

			do {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (cam.isOpen()) {
					if ((image = cam.getImage()) == null) {
						continue;
					}
					LuminanceSource source = new BufferedImageLuminanceSource(
							image);
					BinaryBitmap bitmap = new BinaryBitmap(
							new HybridBinarizer(source));
					try {
						result = new MultiFormatReader().decode(bitmap);

					} catch (NotFoundException e) {
						// fall thru, it means there is no QR code in image
					}
				}
				if (result != null) {
					System.out.println(result);
				}
			} while (true);

		} else {
			JOptionPane.showMessageDialog(null, "Sem detectou a câmera!",
					"Erro", JOptionPane.ERROR_MESSAGE);

		}

		btnFoto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ImageIO.write(cam.getImage(), "PNG",
							new File("C:\\SIMPRO\\foto.png"));
				} catch (IOException e1) {
					cam.close();
					e1.printStackTrace();
				}

			}
		});

	}

	// TODO Alterar o valor de um pagamento
	public void alterarValorPagamento(Pedido pedi, Lancamento lanc) {
		if (lanc.getValor() <= 0) {
			daoLancamento.removerItemLancRec(pedi, lanc);
		} else {
			daoLancamento.alterarQuantItemRec(pedi, lanc);
		}
	}

	public void removerPagamento(Pedido pedi, Lancamento lanc) {
		daoLancamento.removerItemLancRec(pedi, lanc);
	}
}
