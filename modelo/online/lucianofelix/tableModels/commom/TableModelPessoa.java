package online.lucianofelix.tableModels.commom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import online.lucianofelix.beans.Pessoa;
import online.lucianofelix.dao.DAOPessoaPG;
import online.lucianofelix.visao.PainelPessoa;

public class TableModelPessoa extends AbstractTableModel {

	private List<Pessoa> linhas;
	private String[] colunas = new String[]{"Nome", "E-mail"};
	private static final int Nome = 0;
	private static final int email = 1;
	DAOPessoaPG daoPessoa;

	public TableModelPessoa() {
		daoPessoa = new DAOPessoaPG();
		linhas = new ArrayList<Pessoa>();
	}

	public TableModelPessoa(List<Pessoa> listPessoa) {
		daoPessoa = new DAOPessoaPG();
		linhas = new ArrayList<Pessoa>(listPessoa);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Pessoa p = linhas.get(rowIndex);
		switch (columnIndex) {
			case Nome :
				return p.getNome();
			case email :
				return p.getEmail();

			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	public boolean contemNomeLista(Pessoa pessoa) {
		for (Pessoa p : linhas) {
			if (p.equals(pessoa)) {
				return true;
			}
		}
		return false;
	}

	public Pessoa getPessoa(int linha) {
		return linhas.get(linha);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case Nome :
				return String.class;
			case email :
				return String.class;
			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Pessoa p = linhas.get(rowIndex);
		p.setNome((String) aValue);
		fireTableCellUpdated(rowIndex, columnIndex);
		PainelPessoa.carregarCampos(p);
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	};

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == Nome;
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}

}
