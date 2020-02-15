package online.lucianofelix.tableModels.commom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import online.lucianofelix.beans.CondPagamento;
import online.lucianofelix.dao.DAOPessoaPG;

public class TableModelCondPag extends AbstractTableModel {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 3268919291605990541L;

	private List<CondPagamento> linhas;
	private String[] colunas = new String[]{"Codigo", "Descrição", "Parcelas",
			"Valor"};
	private static final int Codigo = 0;
	private static final int Descricao = 1;
	private static final int Parcelas = 2;
	private static final int Valor = 3;
	DAOPessoaPG daoPess;

	public TableModelCondPag() {
		daoPess = new DAOPessoaPG();
		linhas = new ArrayList<CondPagamento>();
	}

	public TableModelCondPag(List<CondPagamento> listCondPag) {
		daoPess = new DAOPessoaPG();
		linhas = new ArrayList<CondPagamento>(listCondPag);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		CondPagamento cp = linhas.get(rowIndex);
		switch (columnIndex) {
			case Codigo :
				return cp.getCodiCondPag();
			case Descricao :
				return cp.getNomeCondicao();
			case Parcelas :
				return cp.getQuantParcelas();
			case Valor :
				return 0;

			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	public boolean contemNomeLista(CondPagamento condPag) {
		for (CondPagamento cp : linhas) {
			if (cp.equals(condPag)) {
				return true;
			}
		}
		return false;
	}

	public CondPagamento getCondPag(int linha) {
		return linhas.get(linha);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case Codigo :
				return String.class;
			case Descricao :
				return String.class;
			case Parcelas :
				return Integer.class;
			case Valor :
				return Integer.class;
			default :
				throw new IndexOutOfBoundsException(
						"columnIndex out of bounds");
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		CondPagamento cp = linhas.get(rowIndex);
		switch (columnIndex) {
			case Codigo :
				// l.setCodiPessoa((String) aValue);
				break;
			case Descricao :
				// l.setCodiPedido((String) aValue);
				break;
			case Parcelas :
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

			case Codigo :
				return columnIndex == Codigo;
			case Descricao :
				return columnIndex == Descricao;
			case Parcelas :
				return columnIndex == Parcelas;
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
