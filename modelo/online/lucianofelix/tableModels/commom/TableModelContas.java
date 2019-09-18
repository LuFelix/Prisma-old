package online.lucianofelix.tableModels.commom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import online.lucianofelix.beans.Conta;

public class TableModelContas extends AbstractTableModel {

	// private DAOConta daoCont;
	private ArrayList<Conta> linhas;
	private String[] colunas = new String[]{"Nome", "Descrição"};
	private static final int Nome = 0;
	private static final int Descricao = 1;

	public TableModelContas() {
		linhas = new ArrayList<Conta>();
	}

	public TableModelContas(List<Conta> list) {
		linhas = new ArrayList<Conta>(list);
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}
	public Conta getConta(int linha) {
		return linhas.get(linha);
	}
	@Override
	public int getColumnCount() {
		return colunas.length;
	}
	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	};

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Conta c = linhas.get(rowIndex);
		switch (columnIndex) {
			case Nome :
				return c.getNomeConta();
			case Descricao :
				return c.getDescConta();
			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}

	}

}
