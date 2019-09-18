package online.lucianofelix.tableModels.commom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import online.lucianofelix.beans.GrupoSubgrupo;
import online.lucianofelix.dao.DAOGrupoSubgrupo;
import online.lucianofelix.visao.PainelSubGrupo;

public class TableModelGrupoCategoria extends AbstractTableModel {

	private List<GrupoSubgrupo> linhas;
	private String[] colunas = new String[]{"Nome", "Número"};
	private static final int Nome = 0;
	private static final int Numero = 1;
	DAOGrupoSubgrupo daoGrupo;

	public TableModelGrupoCategoria() {
		daoGrupo = new DAOGrupoSubgrupo();
		linhas = new ArrayList<GrupoSubgrupo>();
	}

	public TableModelGrupoCategoria(List<GrupoSubgrupo> listGrupo) {
		daoGrupo = new DAOGrupoSubgrupo();
		linhas = new ArrayList<GrupoSubgrupo>(listGrupo);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		GrupoSubgrupo g = linhas.get(rowIndex);
		switch (columnIndex) {
			case Nome :
				return g.getNomeGrupo();
			case Numero :
				return g.getSeqGrupo();
			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	public boolean contemNomeLista(GrupoSubgrupo grupo) {
		for (GrupoSubgrupo g : linhas) {
			if (g.equals(grupo)) {
				return true;
			}
		}
		return false;
	}

	public GrupoSubgrupo getGrupo(int linha) {
		return linhas.get(linha);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case Nome :
				return String.class;
			case Numero :
				return String.class;
			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		GrupoSubgrupo g = linhas.get(rowIndex);
		g.setNomeGrupo((String) aValue);
		fireTableCellUpdated(rowIndex, columnIndex);
		daoGrupo.alterar(g);
		PainelSubGrupo.carregarCampos(g);
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
