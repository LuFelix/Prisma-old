package online.lucianofelix.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import online.lucianofelix.beans.CondPagamento;
import online.lucianofelix.beans.Lancamento;
import online.lucianofelix.beans.Pedido;
import online.lucianofelix.beans.Pessoa;
import online.lucianofelix.dao.DAOLancamento;
import online.lucianofelix.tableModels.commom.TableModelLancamento;
import online.lucianofelix.visao.FrameInicial;
import online.lucianofelix.visao.FrameInicial.ControlaBotoes;
import online.lucianofelix.visao.PainelLancamento;

public class ControlaLancamento {
	static DAOLancamento daoLancamento;
	Lancamento lanc;
	Lancamento baixa;
	TableModelLancamento tblMdLanc;
	TableModelLancamento tblMdBaixas;
	JTable tabelaTitulos;
	JTable tabelaBaixas;

	private String nome;

	public ControlaLancamento() {
		daoLancamento = new DAOLancamento();
	}
	/**
	 * 
	 * @param pedi
	 * @param lanc
	 */
	public void adicionaTitReceberPedido(Pedido pedi, Lancamento lanc) {
		if (pedi.getTipoPedido().equals("Compra")) {
			lanc.setTipoLancamento("Sai");
		} else if (pedi.getTipoPedido().equals("Venda")) {
			lanc.setTipoLancamento("Entra");
		}
		try {
			lanc.setCodiConta("162017514"); // sti Pedidos Criar forma de
											// escolher
			daoLancamento.inserirTituRec(lanc.getCodiCtaReceber(),
					lanc.getCodiCondPag(), pedi.getCodiPedi(),
					pedi.getCodiPessoaCliente(), lanc.getDtHrVenc(),
					lanc.getValor(), lanc.getObsLancamento(),
					lanc.getTipoLancamento(), "tipoDocVincu",
					lanc.getCodiConta());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * adiciona uma baixa para o titulo(conta a Receber) pelo pedido
	 * 
	 * @param baixa
	 */
	public void adicionaBaixaTitPedido(Lancamento baixa) {
		String entrada = JOptionPane.showInputDialog("Valor: ")
				.replace(",", ".").trim();
		try {
			daoLancamento.novoBaixaRec(baixa.getCodiPessoa(),
					baixa.getCodiCtaReceber(), new BigDecimal(entrada),
					baixa.getCodiCondPag());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Adiciona uma conta a receber
	 * 
	 * @param lancRec
	 */
	public void adicionaLancamento(Lancamento lancRec) {
		try {
			// conta para lançamento dos pedidos: "10201662"
			// daoLancamento.novoLancRec("10201662", lanc.getCodiCondPag(),
			// "codigoDocumento", "codigoPagador", "", lanc.getValor(),
			// "Obs", null, lanc.getTipoLancamento(), "", "");
			Timestamp dataHoraMovimento = new Timestamp(
					Calendar.getInstance().getTimeInMillis());
			daoLancamento.inserirTituRec(lanc.getCodiCtaReceber(),
					lanc.getCodiCondPag(), lanc.getCodiPedido(),
					lanc.getCodiPessoa(), lanc.getDtHrVenc(), lanc.getValor(),
					lanc.getObsLancamento(), "tipoLanc", "tipoDocVinculado",
					lanc.getCodiConta());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Adiciona baixa ao título com a criacao do codigo automatica
	 * 
	 * @param condPag
	 */
	public void adicionaBaixaTitReceber(CondPagamento condPag, Pessoa pess) {
		String entrada = JOptionPane.showInputDialog("Valor:").replace(",",
				".");
		BigDecimal valor = null;
		if (entrada != null) {
			valor = new BigDecimal(entrada);
			Lancamento baixa = PainelLancamento.lerCampos();
			baixa.setCodiCondPag(condPag.getCodiCondPag());
			baixa.setValor(valor);
			baixa.setCodiCtaReceber(criaCodiLancRec());
			baixa.setCodiPessoa(pess.getCodiPessoa());
			if (valor.compareTo(new BigDecimal(0)) <= 0 & valor != null) {
				daoLancamento.removerItemLancRec(baixa);
			} else {
				daoLancamento.alterarBaixaRec(baixa);
			}
		}
		// PainelLancamento.atualizaTabelaPagamentos(lanc);
	}
	public void adicionaBaixa(Lancamento baixa) {
		String entrada = JOptionPane.showInputDialog("Informe o Valor")
				.replace(",", ".");
		BigDecimal valor;
		// controlalançamento adicionar baixaes - verificar se foi
		// finalizado ou parcial - mostrar a difereça restante
		int opcao = JOptionPane.showConfirmDialog(null,
				"Confirma baixa para o título?");
		if (opcao == 0) {
			if (entrada != null) {
				valor = new BigDecimal(entrada);
				baixa.setValor(valor);
				adicionaBaixaTitPedido(baixa);
				JOptionPane.showMessageDialog(null,
						"Aplicando baixa para: " + baixa.getCodiCtaReceber()
								+ "\n Valor do Título: "
								+ baixa.getValorString());

			}
		}
	}
	/**
	 * Adiciona baixa ao título com a criacao do codigo automatica
	 * 
	 * @param condPag
	 */
	public void adicionaBaixaTitReceber(CondPagamento condPag,
			String codiPess) {
		String entrada = JOptionPane.showInputDialog("Valor:").replace(",",
				".");
		BigDecimal valor = null;
		if (entrada != null) {
			valor = new BigDecimal(entrada);
			Lancamento lanc = PainelLancamento.lerCampos();
			lanc.setCodiCondPag(condPag.getCodiCondPag());
			lanc.setValor(valor);
			lanc.setCodiCtaReceber(criaCodiLancRec());
			lanc.setCodiPessoa(codiPess);
			if (valor.compareTo(new BigDecimal(0)) <= 0 & valor != null) {
				daoLancamento.removerItemLancRec(lanc);
			} else {
				adicionaBaixaTitPedido(lanc);
			}
		}
		// atualizaTabelaPagamentos(lanc);
	}

	/**
	 * edita uma baixa para o titulo(conta a Receber) pelo pedido
	 * 
	 * @param baixa
	 */
	public void editaBaixaTitPedido(Lancamento baixa) {
		daoLancamento.alterarBaixaRec(baixa);
	}
	public void removeBaixa(Lancamento baixa) {
		daoLancamento.removerBaixa(baixa);

	}

	/**
	 * Cria um codigo pára o título(número do título, deveré ser usado como
	 * nossoNumero)
	 * 
	 * @return
	 */
	public static String criaCodiLancRec() {
		daoLancamento = new DAOLancamento();
		Calendar c = Calendar.getInstance();
		String codiCtaReceber = String
				.valueOf(daoLancamento.consultaUltLancReceber()
						+ String.valueOf(c.get(Calendar.YEAR))
						+ String.valueOf(c.get(Calendar.MONTH))
						+ String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
		return codiCtaReceber;
	}

	// TODO Tabela Atual
	public JTable pesqNomeTabela(String str) {
		tabelaTitulos = new JTable();
		tblMdLanc = new TableModelLancamento(daoLancamento.listUltLancRec());
		tabelaTitulos.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					lanc = tblMdLanc.getLancamento(
							FrameInicial.getTabela().getSelectedRow());
					FrameInicial.setPainelVisualiza(new PainelLancamento(lanc));
					FrameInicial.atualizaTela();
					FrameInicial.getTabela().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				}
			}

			@Override
			public void keyPressed(KeyEvent tecla) {
				int posicao = tabelaTitulos.getSelectedRow();
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					lanc = tblMdLanc.getLancamento(
							FrameInicial.getTabela().getSelectedRow());
					FrameInicial.setPainelVisualiza(new PainelLancamento(lanc));
					FrameInicial.atualizaTela();
					FrameInicial.getTabela().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();

				} else if (tecla.getExtendedKeyCode() == 10) {
					FrameInicial.getTabela().changeSelection(--posicao, 0,
							false, false);
					lanc = tblMdLanc.getLancamento(
							FrameInicial.getTabela().getSelectedRow());
					FrameInicial.setPainelVisualiza(new PainelLancamento(lanc));
					FrameInicial.atualizaTela();
					funcaoSobrescrever();
				}
			}
		});
		tabelaTitulos.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				lanc = tblMdLanc.getLancamento(
						FrameInicial.getTabela().getSelectedRow());
				System.out.println(
						">>>>>>>>>>>>>>>> Controlalancamento pesqNomeTabela lanc.getCodiCtaReceber: "
								+ lanc.getCodiCtaReceber());

				FrameInicial.setPainelVisualiza(new PainelLancamento(lanc));
				FrameInicial.atualizaTela();
				FrameInicial.getTabela().grabFocus();

			}
		});

		tabelaTitulos.setShowGrid(true);
		tabelaTitulos.setModel(tblMdLanc);
		return tabelaTitulos;
	}
	public void carregarBaixasCtaReceber(Lancamento lanc) {
		lanc.setListbaixas(daoLancamento
				.listBaixasNumCtaReceber(lanc.getCodiCtaReceber()));
	}
	/**
	 * TODO Iniciar
	 */
	public void iniciar() {
		System.out.println("ControlaLancamento.pesquisaLancamento");
		ControlaBotoes.limpaTodosBotoes();
		ControlaBotoes.desHabilitaEdicaoBotoes();
		configuraBotoes();
		FrameInicial.setTabela(pesqNomeTabela(""));
		// FrameInicial.getTabela().setRowSelectionInterval(0, 0);
		// lanc = tblMdLanc
		// .getLancamento(FrameInicial.getTabela().getSelectedRow());
		FrameInicial.setPainelVisualiza(new PainelLancamento(lanc));
		FrameInicial.atualizaTela();
		configuraTxtPesquisa();

	}
	public void iniciar(String lanc) {
		System.out.println("ControlaLancamento.pesquisaLancamento");
		ControlaBotoes.limpaTodosBotoes();
		ControlaBotoes.desHabilitaEdicaoBotoes();
		configuraBotoes();

		FrameInicial.setTabela(pesqNomeTabela(""));
		// FrameInicial.getTabela().setRowSelectionInterval(0, 0);
		// lanc = tblMdLanc
		// .getLancamento(FrameInicial.getTabela().getSelectedRow());
		FrameInicial.setPainelVisualiza(new PainelLancamento());
		FrameInicial.atualizaTela();
		configuraTxtPesquisa();

	}

	void configuraBotoes() {
		FrameInicial.getBtnEditar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelLancamento.habilitaEdicao();
				ControlaBotoes.habilitaEdicaoBotoes();
				funcaoSobrescrever();

			}
		});
		FrameInicial.getBtnNovo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlaBotoes.clicaNovoBotoes();
				PainelLancamento.habilitaNovo();
				funcaoSalvar();
			}
		});
		FrameInicial.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				iniciar();
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
		FrameInicial.getTxtfPesquisa().addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent tecla) {
				if (tecla.getExtendedKeyCode() == 40) {// seta para baixo
					FrameInicial.getTabela().grabFocus();
					FrameInicial.getTabela().setRowSelectionInterval(0, 0);
					lanc = tblMdLanc.getLancamento(
							FrameInicial.getTabela().getSelectedRow());
					FrameInicial.setPainelVisualiza(new PainelLancamento(lanc));
				} else if (tecla.getExtendedKeyCode() == 27) {
					funcaoCancelar();
				} else {
					nome = FrameInicial.getTxtfPesquisa().getText();
					FrameInicial.setTabela(pesqNomeTabela(nome));
					lanc = tblMdLanc.getLancamento(
							FrameInicial.getTabela().getSelectedRow());
					FrameInicial.setPainelVisualiza(new PainelLancamento(lanc));
					FrameInicial.atualizaTela();
				}
			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				nome = FrameInicial.getTxtfPesquisa().getText();
				FrameInicial.setTabela(pesqNomeTabela(nome));
				// lanc = tblMdLanc.getLancamento(
				// FrameInicial.getTabela().getSelectedRow());
				FrameInicial.setPainelVisualiza(new PainelLancamento(lanc));
				FrameInicial.atualizaTela();
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
	}
	public void funcaoCancelar() {
		System.out.println("ControlaConta.cancelar");
		iniciar();
	}
	// TODO Funcao excluir
	public boolean funcaoExcluir() {
		System.out.println("ControlaLancamento funcaoExcluir");
		if (daoLancamento.excluirLancRec(lanc)) {
			JOptionPane.showMessageDialog(null,
					"Excluido lancamento codigo: " + lanc.getCodiCtaReceber()
							+ "\n Data: " + lanc.getDtHrLanc());
			FrameInicial.setTabela(null);
			FrameInicial.setPainelVisualiza(new PainelLancamento());
			FrameInicial.atualizaTela();
			JOptionPane.showMessageDialog(null, "Feito!");
			iniciar();
			return true;
		} else {
			funcaoCancelar();
			return false;
		}
	}
	// TODO Função Salvar
	public void funcaoSalvar() {
		System.out.println(
				">>>>>>>>>>>>>>>>>>>>  ControlaLancamento.funcaoSalvar");
		ControlaBotoes.limparBtnSalvar();
		JOptionPane.showConfirmDialog(null,
				"O título tem documento vinculado?");
		// Mostrar o combobox de tipo de documento e listar os documentos que
		// não possui títulos vinculados de acorso com o tipo selecionado

		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				lanc = PainelLancamento.lerCampos();
				System.out.println(lanc.getValor() + "   "
						+ lanc.getCodiCondPag() + " " + lanc.getDtHrLanc());
				if (lanc != null) {
					daoLancamento.inserirTituRec(lanc);
					PainelLancamento.limparCampos();
					FrameInicial.setTabela(pesqNomeTabela(lanc.getCodiConta()));
					FrameInicial.setPainelVisualiza(new PainelLancamento(lanc));
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
	// TODO Função sobrescrever
	public void funcaoSobrescrever() {
		System.out.println(
				">>>>>>>>>>>>>>>>>>>>>>ControlaLancamento.funcaoSobrescrever");
		ControlaBotoes.limparBtnSalvar();
		FrameInicial.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lanc = PainelLancamento.lerCampos();
				if (lanc != null) {
					daoLancamento.alterarLanRec(lanc);
				}
				FrameInicial.setTabela(pesqNomeTabela(lanc.getCodiCondPag()));
				FrameInicial.setPainelVisualiza(new PainelLancamento(lanc));
				FrameInicial.atualizaTela();
				JOptionPane.showMessageDialog(null, "Feito!");
				iniciar();

			}
		});
	}
	/**
	 * Calcula o total ja baixado
	 * 
	 * @param l
	 * @return
	 */
	public BigDecimal totalBaixas(Lancamento l) {
		System.out.println(
				">>>>>>>>>>>>>>>>>>>>>> ControlaLancamento.totalBaixas() \n");
		BigDecimal totalBaixas = BigDecimal.ZERO;
		System.out
				.println(">>>>>>>>>>>>>>>>>>>>>> Tamanho da lista de Baixas: \n"
						+ l.getListbaixas().size());
		for (int i = 0; i < l.getListbaixas().size(); i++) {
			totalBaixas = totalBaixas.add(l.getListbaixas().get(i).getValor());
		}
		return totalBaixas;
	}

}
