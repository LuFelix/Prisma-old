package online.lucianofelix.tableModels.commom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import online.lucianofelix.beans.CentroCusto;
import online.lucianofelix.dao.DAOCentroCusto;

public class TableModelCentroCusto extends AbstractTableModel {

	private DAOCentroCusto dao;
	private ArrayList<CentroCusto> linhas;
	private String[] colunas = new String[]{"Nome", "Descrição"};
	private static final int Nome = 0;
	private static final int Descricao = 1;
	public TableModelCentroCusto() {
		dao = new DAOCentroCusto();
		linhas = new ArrayList<CentroCusto>();
	}

	public TableModelCentroCusto(List<CentroCusto> list) {
		dao = new DAOCentroCusto();
		linhas = new ArrayList<CentroCusto>(list);
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return linhas.size();
	}
	public CentroCusto getCentroCusto(int linha) {
		return linhas.get(linha);
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;
	}
	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	};

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		CentroCusto c = linhas.get(rowIndex);
		switch (columnIndex) {
			case Nome :
				return c.getNomeCentroCusto();
			case Descricao :
				return c.getDescCentroCusto();
			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}

	}

}
