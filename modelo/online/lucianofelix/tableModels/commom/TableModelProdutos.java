package online.lucianofelix.tableModels.commom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import online.lucianofelix.beans.Produto;
import online.lucianofelix.dao.DAOProdutoPrepSTM;
import online.lucianofelix.visao.PainelProdutos;

public class TableModelProdutos extends AbstractTableModel {

	private List<Produto> linhas;
	private String[] colunas = new String[]{"Código", "Nome", "Preço Atual"};
	private static final int Codigo = 0;
	private static final int Nome = 1;
	private static final int Preco = 2;
	DAOProdutoPrepSTM daoProduto;

	public TableModelProdutos() {
		daoProduto = new DAOProdutoPrepSTM();
		linhas = new ArrayList<Produto>();
	}

	public TableModelProdutos(List<Produto> listProduto) {
		daoProduto = new DAOProdutoPrepSTM();
		linhas = new ArrayList<Produto>(listProduto);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Produto p = linhas.get(rowIndex);
		switch (columnIndex) {
			case Codigo :
				return p.getCodi_prod_1();
			case Nome :
				return p.getNome_prod();
			case Preco :
				return 0;

			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	public boolean contemNomeLista(Produto produto) {
		for (Produto p : linhas) {
			if (p.equals(produto)) {
				return true;
			}
		}
		return false;
	}

	public Produto getProduto(int linha) {
		return linhas.get(linha);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case Codigo :
				return String.class;
			case Nome :
				return String.class;
			case Preco :
				return Float.class;
			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Produto p = linhas.get(rowIndex);
		p.setNome_prod((String) aValue);
		fireTableCellUpdated(rowIndex, columnIndex);
		PainelProdutos.carregarCampos(p);
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
