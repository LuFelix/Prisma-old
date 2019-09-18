package online.lucianofelix.util;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import online.lucianofelix.beans.Pedido;

public class ModeloTabelaPedidos extends AbstractTableModel {

	public static final int COL_ITEM = 0;
	public static final int COL_QTDE = 1;
	public static final int COL_CODIGOBARRAS = 2;
	public static final int COL_UNITARIO = 3;
	public static final int COL_DESCRICAO = 4;
	public static final int COL_ST = 5;
	public static final int COL_VALORITEM = 6;
	public ArrayList<Pedido> listarPedidos;

	public ModeloTabelaPedidos(ArrayList<Pedido> l) {
		listarPedidos = new ArrayList<Pedido>(l);
	}

	@Override
	public int getRowCount() {
		return listarPedidos.size();
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public Object getValueAt(int linhas, int colunas) {
		Pedido ped = listarPedidos.get(linhas);
		if (colunas == COL_ITEM)
			return ped.getCodiPedi();
		if (colunas == COL_QTDE)
			return ped.getxNome();
		if (colunas == COL_CODIGOBARRAS)
			return ped.getCNPJ();
		if (colunas == COL_UNITARIO)
			return ped.getQuantItens();
		if (colunas == COL_ST)
			return ped.getCodiCondPag();
		if (colunas == COL_DESCRICAO)
			return ped.getDataHoraPedi();
		if (colunas == COL_VALORITEM)
			return ped.getTotalPedi();
		return null;
	}

	@Override
	public String getColumnName(int colunas) {
		if (colunas == COL_ITEM)
			return "Item";
		if (colunas == COL_QTDE)
			return "Qtde. Unid.";
		if (colunas == COL_CODIGOBARRAS)
			return "Cód.Barras";
		if (colunas == COL_UNITARIO)
			return "Unit.";
		if (colunas == COL_ST)
			return "ST";
		if (colunas == COL_DESCRICAO)
			return "Descrição";
		if (colunas == COL_VALORITEM)
			return "Valor Item";
		return null;
	}
}
