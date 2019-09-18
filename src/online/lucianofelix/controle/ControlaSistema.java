package online.lucianofelix.controle;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import online.lucianofelix.dao.DAOBanco;
import online.lucianofelix.visao.FrameInicial;
import online.lucianofelix.visao.PainelFuncoesBanco;

public class ControlaSistema {
	JTable tabela;
	ArrayList<Object> colunas;
	List<String> listTabelas;
	DAOBanco daoBanco;
	DefaultTableModel model;

	public ControlaSistema() {
		daoBanco = new DAOBanco();
	}

	public void limpaTabela(String nomeTabela) {
		if (JOptionPane.showConfirmDialog(null, "Deseja realmente limpasr esta tabela?   " + nomeTabela) == 1) {
			daoBanco.limpaTabela(nomeTabela);

		}

	}

	public JTable listarTabelasSistema() {
		colunas = new ArrayList<Object>();
		model = new DefaultTableModel();
		tabela = new JTable();
		model = (DefaultTableModel) tabela.getModel();
		colunas.add("Tabelas");
		model.setColumnIdentifiers(colunas.toArray());
		listTabelas = daoBanco.listaTabelas();
		for (String nomeTabela : listTabelas) {
			Object linha[] = { nomeTabela };
			model.addRow(linha);
		}
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
				String nomeTabela = (String) model.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
				FrameInicial.getScrVisualiza().setViewportView(new PainelFuncoesBanco(nomeTabela));
			}
		});
		tabela.setShowGrid(true);
		tabela.setModel(model);
		return tabela;

	}
}
