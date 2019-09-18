package online.lucianofelix.visao;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import online.lucianofelix.controle.ControlaSistema;

public class PainelFuncoesBanco extends JPanel {
	ControlaSistema contSis;
	JPanel pnlPrincipal;
	JPanel pnlFunctions;
	JScrollPane pnlResult;
	JButton btnLimpatabela;
	JButton btnExcluiTabela;
	JButton btnCriaTabela;
	private JLabel lblTituloTela;

	public PainelFuncoesBanco(String nomeTabela) {
		UIManager.put("TextField.font", new Font("Mukti Narrow Bold Italic", Font.BOLD, 12));
		lblTituloTela = new JLabel(nomeTabela);
		contSis = new ControlaSistema();
		btnCriaTabela = new JButton("Cria Tabela");
		btnExcluiTabela = new JButton("Exclui Tabela");

		btnLimpatabela = new JButton("Limpa Tabela");
		btnLimpatabela.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contSis.limpaTabela(lblTituloTela.getText());

			}
		});
		pnlFunctions = new JPanel(new FlowLayout());
		pnlFunctions.add(lblTituloTela);
		pnlFunctions.add(btnCriaTabela);
		pnlFunctions.add(btnExcluiTabela);
		pnlFunctions.add(btnLimpatabela);

		pnlResult = new JScrollPane();

		pnlPrincipal = new JPanel(new GridLayout(3, 3));
		pnlPrincipal.add(pnlFunctions);
		pnlPrincipal.add(pnlResult);
		add(pnlPrincipal);

	}

}
