package online.lucianofelix.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import online.lucianofelix.beans.Ativo;
import online.lucianofelix.beans.CotacaoAtivo;

public class MontaGrid {
	JTable tbl;
	JPanel painelGrid;

	public JTable montaTabelaCotacao(ArrayList<Object> col, List<CotacaoAtivo> list) {
		tbl = new JTable();
		DefaultTableModel modeloTabela = new DefaultTableModel();
		modeloTabela = (DefaultTableModel) tbl.getModel();
		modeloTabela.setColumnIdentifiers(col.toArray());

		tbl.setSize(280, 600);

		for (int i = 0; i < list.size(); i++) {
			Object linha[] = { list.get(i).getPreAbe(), list.get(i).getPreFec(), list.get(i).getPreMax(),
					list.get(i).getPreMin(), list.get(i).getDataCotacao() };
			modeloTabela.addRow(linha);
		}
		tbl.setModel(modeloTabela);
		return tbl;
	}

	public JTable montaTabelaAtivos(ArrayList<Object> col, List<Ativo> list) {
		tbl = new JTable();
		DefaultTableModel modeloTabela = new DefaultTableModel();
		modeloTabela = (DefaultTableModel) tbl.getModel();
		modeloTabela.setColumnIdentifiers(col.toArray());
		for (int i = 0; i < list.size(); i++) {
			Object linha[] = { list.get(i).getIdNeg(), list.get(i).getNomeRes() };
			modeloTabela.addRow(linha);
		}
		tbl.setModel(modeloTabela);
		return tbl;
	}

	public JPanel montaPainelGrid(ArrayList<Object> col, List<CotacaoAtivo> list) {
		tbl = montaTabelaCotacao(col, list);

		painelGrid = new JPanel();
		painelGrid.add(tbl);
		return painelGrid;

	}
}