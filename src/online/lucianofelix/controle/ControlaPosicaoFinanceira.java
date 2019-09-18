package online.lucianofelix.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import online.lucianofelix.adapter.ConstrutorGrafico;
import online.lucianofelix.visao.FrameInicial;
import online.lucianofelix.visao.PainelServico;
import online.lucianofelix.visao.PainelStatus;
import online.lucianofelix.visao.FrameInicial.ControlaBotoes;

public class ControlaPosicaoFinanceira {
	private ConstrutorGrafico constGrafico;

	public ControlaPosicaoFinanceira() {
		constGrafico = new ConstrutorGrafico();
	}

	public void iniciar() {
		System.out.println("ControlaPosicaoFinanceira.iniciar");
		ControlaBotoes.limpaTodosBotoes();
		ControlaBotoes.desHabilitaEdicaoBotoes();
		FrameInicial.limparTxtfPesquisa();
		FrameInicial.getTxtfPesquisa().grabFocus();
		FrameInicial.setPainelVisualiza(new PainelStatus(""));
		FrameInicial.atualizaTela();
		FrameInicial.getScrLista()
				.setViewportView(constGrafico.graficoPizzaRecDesp(3, 2));
		FrameInicial.getBtnNovo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PainelServico.limparCampos();
				PainelServico.habilitaNovo();
				// funcaoSalvar();
			}
		});

		FrameInicial.getBtnEditar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlaBotoes.habilitaEdicaoBotoes();
				PainelServico.habilitaEdicao();
				// funcaoSobrescrever();
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
				// funcaoExcluir();
				iniciar();
			}
		});
		FrameInicial.getTxtfPesquisa().addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent tecla) {
				if (tecla.getExtendedKeyCode() == 40) {
					// FrameInicial.getTabela().grabFocus();
					// FrameInicial.getTabela().changeSelection(0, 0, false,
					// false);
				} else {
					String nome = FrameInicial.getTxtfPesquisa().getText();
					// FrameInicial.setTabela(pesqNomeTabela(nome));
					// FrameInicial.setPainelVisualiza(new PainelServico(nome));
					// FrameInicial.atualizaTela();
				}

			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				String nome = FrameInicial.getTxtfPesquisa().getText();
				// FrameInicial.setTabela(pesqNomeTabela(nome));
				// FrameInicial.setPainelVisualiza(new PainelServico(nome));
				// FrameInicial.atualizaTela();
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
	}
}
