package online.lucianofelix.tableModels.commom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.joda.time.DateTime;

import online.lucianofelix.beans.Lancamento;
import online.lucianofelix.dao.DAOPessoaPG;

public class TableModelBaixas extends AbstractTableModel {
	private List<Lancamento> linhas;
	private String[] colunas = new String[]{"Titular", "Documento", "Data",
			"Valor"};
	private static final int Titular = 0;
	private static final int Documento = 1;
	private static final int Data = 2;
	private static final int Valor = 3;
	DAOPessoaPG daoPess;

	public TableModelBaixas() {
		daoPess = new DAOPessoaPG();
		linhas = new ArrayList<Lancamento>();
	}

	public TableModelBaixas(List<Lancamento> list) {
		daoPess = new DAOPessoaPG();
		linhas = new ArrayList<Lancamento>(list);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Lancamento l = linhas.get(rowIndex);
		switch (columnIndex) {
			case Titular :
				return l.getCodiPessoa();
			case Documento :
				return l.getCodiPedido();
			case Data :
				return l.getDtHrVenc();
			case Valor :
				return l.getValor();

			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	public boolean contemNomeLista(Lancamento lanc) {
		for (Lancamento l : linhas) {
			if (l.equals(lanc)) {
				return true;
			}
		}
		return false;
	}

	public Lancamento getLancamento(int linha) {
		return linhas.get(linha);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case Titular :
				return String.class;
			case Documento :
				return String.class;
			case Data :
				return DateTime.class;
			case Valor :
				return Float.class;

			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Lancamento l = linhas.get(rowIndex);
		switch (columnIndex) {
			case Titular :
				// l.setCodiPessoa((String) aValue);
				break;
			case Documento :
				// l.setCodiPedido((String) aValue);
				break;
			case Data :
				// l.setDataHoraLancamento((Date) aValue);
				break;
			case Valor :
				// l.setValor((Float) aValue);
				break;

			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}

		fireTableCellUpdated(rowIndex, columnIndex);
		// daoPess.atualizar(l);

	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	};

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {

			case Titular :
				return columnIndex == Titular;
			case Documento :
				return columnIndex == Documento;
			case Data :
				return columnIndex == Data;
			case Valor :
				return columnIndex == Valor;
			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}

	}
	// public void adicionaOcup(Pessoa p, int rowIndex) {
	// if (linhas.isEmpty()) {
	// PessoaProfissional pp = new PessoaProfissional();
	// pp.setCodiPess(p.getCodiPessoa());
	// linhas.add(pp);
	// daoPess.criarFuncao(pp);
	// }
	//
	// }
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}

}
