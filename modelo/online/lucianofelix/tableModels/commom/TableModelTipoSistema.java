package online.lucianofelix.tableModels.commom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import online.lucianofelix.beans.TipoSistema;
import online.lucianofelix.dao.DAOTiposSistema;
import online.lucianofelix.visao.AbaCadastros;

public class TableModelTipoSistema extends AbstractTableModel {

	/**
	 * SERIAL
	 */
	private static final long serialVersionUID = 6763304050149263808L;

	private List<TipoSistema> linhas;
	private String[] colunas = new String[]{"Número", "Nome"};
	private static final int Numero = 0;
	private static final int Nome = 1;
	DAOTiposSistema daoTipoS;

	public TableModelTipoSistema() {
		daoTipoS = new DAOTiposSistema();
		linhas = new ArrayList<TipoSistema>();
	}

	public TableModelTipoSistema(List<TipoSistema> listTipo) {
		daoTipoS = new DAOTiposSistema();
		linhas = new ArrayList<TipoSistema>(listTipo);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TipoSistema obj = linhas.get(rowIndex);
		switch (columnIndex) {
			case Numero :
				return obj.getSeqTipoSistema();
			case Nome :
				return obj.getNomeTipoSistema();
			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	public boolean contemNomeLista(TipoSistema tipoS) {
		for (TipoSistema tp : linhas) {
			if (tp.equals(tipoS)) {
				return true;
			}
		}
		return false;
	}

	public TipoSistema getTipoS(int linha) {
		return linhas.get(linha);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case Numero :
				return Integer.class;
			case Nome :
				return String.class;
			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		TipoSistema tp = linhas.get(rowIndex);
		tp.setNomeTipoSistema((String) aValue);
		fireTableCellUpdated(rowIndex, columnIndex);
		daoTipoS.alterar(tp);

		AbaCadastros.recarregaArvore();

		// PainelGrupoSubgrupo.carregarCampos(g);
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
