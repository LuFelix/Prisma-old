package online.lucianofelix.tableModels.commom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import online.lucianofelix.beans.Pedido;
import online.lucianofelix.beans.Produto;
import online.lucianofelix.dao.DAOProdutosPedidos;
import online.lucianofelix.visao.PainelProdutos;

public class TableModelProdutosPedidos extends AbstractTableModel {

	private List<Produto> linhas;
	private String[] colunas = new String[]{"Remover Imagem", "Nome", "Imagem"};
	private static final int RemoverImagem = 0;
	private static final int Codigo = 1;
	private static final int Nome = 2;

	private DAOProdutosPedidos daoProdPed;
	private Pedido pedido;

	public TableModelProdutosPedidos() {
		linhas = new ArrayList<Produto>();
	}

	public TableModelProdutosPedidos(Pedido pedi) {
		daoProdPed = new DAOProdutosPedidos();
		linhas = new ArrayList<Produto>(daoProdPed.pesquisaItensPedido(pedi));
		this.pedido = pedi;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Produto p = linhas.get(rowIndex);
		switch (columnIndex) {
			case RemoverImagem :
				return p.equals(p);
			case Codigo :
				return p.getCodi_prod_1();
			case Nome :
				return p.getNome_prod();
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

			case RemoverImagem :
				return Boolean.class;
			case Codigo :
				return String.class;
			case Nome :
				return String.class;
			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Produto p = linhas.get(rowIndex);
		switch (columnIndex) {

			case RemoverImagem :
				fireTableCellUpdated(rowIndex, columnIndex);
				daoProdPed.removerItem(pedido, p);
				System.out.println("AQUI E  " + p.getCodi_prod_1()
						+ " Sequencia " + p.getSeq_produto());
				PainelProdutos.carregarCampos(p);
				break;
		}

	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	};

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == RemoverImagem;
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
