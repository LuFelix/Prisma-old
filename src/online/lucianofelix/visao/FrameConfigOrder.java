package online.lucianofelix.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class FrameConfigOrder extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8304440957190110234L;

	JPanel principal;
	JPanel grid;
	JPanel pnlFaturas;

	JRadioButton rdBtnPerguntaGeraTitulo;
	JRadioButton rdBtnGeraAutomaticamente;
	JRadioButton rdBtnNaoGeraTitulo;
	ButtonGroup grupoFatura;

	JCheckBox perguntaCriaTitulo;
	JCheckBox confirmaPreconoPedido;
	JCheckBox permitirAlterarPreconaTblItensPedido;
	JCheckBox perguntaGeraParcelas;

	public FrameConfigOrder() {
		// TODO Auto-generated constructor stub
		principal = new JPanel(new BorderLayout());
		grid = new JPanel(new GridLayout(3, 1));

		rdBtnPerguntaGeraTitulo = new JRadioButton(
				"Perguntar se gera Títulos.");
		rdBtnPerguntaGeraTitulo
				.setFont(new Font("Times New Roman", Font.BOLD, 16));

		rdBtnGeraAutomaticamente = new JRadioButton(
				"Gerar Títulos Automaticamente.");
		rdBtnGeraAutomaticamente
				.setFont(new Font("Times New Roman", Font.BOLD, 16));

		rdBtnNaoGeraTitulo = new JRadioButton(
				"Não gerar Títulos automaticamente.");
		rdBtnNaoGeraTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));

		grupoFatura = new ButtonGroup();
		grupoFatura.add(rdBtnPerguntaGeraTitulo);
		grupoFatura.add(rdBtnGeraAutomaticamente);
		grupoFatura.add(rdBtnNaoGeraTitulo);
		pnlFaturas = new JPanel(new GridLayout(1, 3));
		pnlFaturas.setBorder(BorderFactory.createTitledBorder(
				new LineBorder(Color.GRAY, 2), "Geração de Títulos",
				TitledBorder.LEFT, TitledBorder.BELOW_TOP));
		pnlFaturas.add(rdBtnPerguntaGeraTitulo);
		pnlFaturas.add(rdBtnGeraAutomaticamente);
		pnlFaturas.add(rdBtnNaoGeraTitulo);

		perguntaGeraParcelas = new JCheckBox(
				"Perguntar se gera as parcelas das Títulos?");
		perguntaGeraParcelas
				.setFont(new Font("Times New Roman", Font.BOLD, 16));

		confirmaPreconoPedido = new JCheckBox(
				"Confirmar o preço do produto na hora de adicionar ao pedido?");
		confirmaPreconoPedido
				.setFont(new Font("Times New Roman", Font.BOLD, 16));

		permitirAlterarPreconaTblItensPedido = new JCheckBox(
				"Permitir alterar o preço do produto na tabela de itens do pedido?");
		permitirAlterarPreconaTblItensPedido
				.setFont(new Font("Times New Roman", Font.BOLD, 16));

		grid.add(permitirAlterarPreconaTblItensPedido);
		grid.add(confirmaPreconoPedido);
		grid.add(pnlFaturas);
		// grid.add(perguntaGeraParcelas);
		grid.setBorder(
				BorderFactory.createTitledBorder(new LineBorder(Color.GRAY, 2),
						"Durante a realização do Pedido"));
		principal.add(grid, BorderLayout.CENTER);

		setContentPane(principal);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
