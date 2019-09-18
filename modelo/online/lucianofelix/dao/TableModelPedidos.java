package online.lucianofelix.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import online.lucianofelix.beans.Pedido;

public class TableModelPedidos extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Pedido> linhas;
	private String[] colunas = new String[]{"Número", "Nome", "Ítens", "Total",
			"Data"};
	private static final int Numero = 0;
	private static final int Nome = 1;
	private static final int Itens = 2;
	private static final int Total = 3;
	private static final int Data = 4;
	private DAOPedidoPrepSTM daoPedido;

	public TableModelPedidos() {
		linhas = new ArrayList<Pedido>();
	}
	public TableModelPedidos(List<Pedido> listDados) {
		daoPedido = new DAOPedidoPrepSTM();
		linhas = new ArrayList<Pedido>(listDados);
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Pedido pedi = linhas.get(rowIndex);
		switch (columnIndex) {
			case Numero :
				break;
			case Nome :
				break;
			case Itens :
				break;
			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
		daoPedido.alterar(pedi);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Pedido pedi = linhas.get(rowIndex);
		switch (columnIndex) {
			case Numero :
				return pedi.getCodiPedi();
			case Nome :
				return pedi.getxNome();
			case Itens :
				return pedi.getQuantItens();
			case Total :
				return pedi.getTotalPedi();
			case Data :
				return pedi.getDataHoraPedi();

			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case Numero :
				return columnIndex == Numero;
			case Nome :
				return columnIndex == Nome;
			case Itens :
				return columnIndex == Itens;
			case Total :
				return columnIndex == Total;
			case Data :
				return columnIndex == Data;

			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}

	}

	public Pedido getItem(int linha) {
		return linhas.get(linha);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		switch (columnIndex) {
			case Numero :
				return String.class;
			case Nome :
				return String.class;
			case Itens :
				return Integer.class;
			case Total :
				return Float.class;
			case Data :
				return Timestamp.class;
			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	};

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}

}
