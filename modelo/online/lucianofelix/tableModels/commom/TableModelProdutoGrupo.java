package online.lucianofelix.tableModels.commom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import online.lucianofelix.beans.GrupoSubgrupo;
import online.lucianofelix.beans.Produto;
import online.lucianofelix.dao.DAOGrupoSubgrupo;
import online.lucianofelix.dao.DAOProdutoPrepSTM;
import online.lucianofelix.visao.FrameInicial;
import online.lucianofelix.visao.PainelProdutos;

public class TableModelProdutoGrupo extends AbstractTableModel {

	private List<GrupoSubgrupo> linhas;
	private String[] colunas = new String[]{"Nome", "Remover Categoria"};
	private static final int Nome = 0;
	private static final int RemoverCategoria = 1;
	DAOGrupoSubgrupo daoGrupo;
	DAOProdutoPrepSTM daoProd;
	private Produto prod;

	public TableModelProdutoGrupo() {
		daoGrupo = new DAOGrupoSubgrupo();

		linhas = new ArrayList<GrupoSubgrupo>();
	}

	public TableModelProdutoGrupo(Produto prod) {
		daoGrupo = new DAOGrupoSubgrupo();
		daoProd = new DAOProdutoPrepSTM();
		linhas = new ArrayList<GrupoSubgrupo>(prod.getListGrupo());
		this.prod = prod;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		GrupoSubgrupo g = linhas.get(rowIndex);
		switch (columnIndex) {
			case Nome :
				return g.getNomeGrupo();
			case RemoverCategoria :
				return g.getIsroot();
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
			case RemoverCategoria :
				return Boolean.class;
			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		GrupoSubgrupo g = linhas.get(rowIndex);
		switch (columnIndex) {

			case RemoverCategoria :
				fireTableCellUpdated(rowIndex, columnIndex);
				daoProd.removerCategoria(prod, g);
				FrameInicial.setPainelVisualiza(new PainelProdutos(prod));
				FrameInicial.atualizaTela();
				break;
		}
		// fireTableCellUpdated(rowIndex, columnIndex);
		// daoProd.removerCategoria(prod, g);
		// FrameInicial.setPainelVisualiza(new PainelProdutos(prod));
		// FrameInicial.atualizaTela();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	};

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == RemoverCategoria;
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
