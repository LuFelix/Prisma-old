package online.lucianofelix.tableModels.commom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import online.lucianofelix.beans.Produto;
import online.lucianofelix.beans.ProdutoImagem;
import online.lucianofelix.dao.DAOProdutoImagem;
import online.lucianofelix.visao.PainelProdutos;

public class TableModelProdutoImagens extends AbstractTableModel {

	private List<ProdutoImagem> linhas;
	private String[] colunas = new String[]{"Remover Imagem", "Nome", "Imagem"};
	private static final int RemoverImagem = 0;
	private static final int Nome = 1;
	private static final int Imagem = 2;

	private DAOProdutoImagem daoProdImg;
	private Produto prod;

	public TableModelProdutoImagens() {
		linhas = new ArrayList<ProdutoImagem>();
	}

	public TableModelProdutoImagens(Produto produto) {
		daoProdImg = new DAOProdutoImagem();
		linhas = new ArrayList<ProdutoImagem>(
				daoProdImg.carregarImagens(produto));
		this.prod = produto;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ProdutoImagem p = linhas.get(rowIndex);
		switch (columnIndex) {
			case RemoverImagem :
				return p.equals(p);
			case Nome :
				return p.getNomeImagem();
			case Imagem :
				return p.getImgIcon();
			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	public boolean contemNomeLista(ProdutoImagem produto) {
		for (ProdutoImagem p : linhas) {
			if (p.equals(produto)) {
				return true;
			}
		}
		return false;
	}

	public ProdutoImagem getProdutoImagem(int linha) {
		return linhas.get(linha);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {

			case RemoverImagem :
				return Boolean.class;
			case Nome :
				return String.class;
			case Imagem :
				return ImageIcon.class;
			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		ProdutoImagem p = linhas.get(rowIndex);
		switch (columnIndex) {

			case RemoverImagem :
				fireTableCellUpdated(rowIndex, columnIndex);
				daoProdImg.removerImagem(p);
				System.out.println("AQUI E  " + p.getCodiProduto()
						+ " Sequencia " + p.getSeqImagemProduto());
				PainelProdutos.carregarImagensProd(prod);
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
