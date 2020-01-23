package online.lucianofelix.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.Produto;
import online.lucianofelix.beans.ProdutoCotacao;
import online.lucianofelix.util.Conexao;
import online.lucianofelix.util.ConexaoSTM;

public class DAOProdutosCotacao {
	private Conexao c;
	private PreparedStatement prepStm;
	// private ResultSet result;
	private ConexaoSTM c2;
	private List<ProdutoCotacao> listCot;
	private ProdutoCotacao cot;
	private DAOTabelaPreco daoTabPreco;

	public DAOProdutosCotacao() {
		c = new Conexao(ConfigS.getBdPg(), "siacecf");
		c2 = new ConexaoSTM(ConfigS.getBdPg(), "siacecf");
		daoTabPreco = new DAOTabelaPreco();
	}
	public ProdutoCotacao consultaUltCotVend(Produto prod, String nomeTab) {
		c.conectar();

		String sql = "SELECT * FROM produtos_cotacoes where codi_produto = ? "
				+ "and data_hora_marcacao = (Select max(data_hora_marcacao) "
				+ "from produtos_cotacoes where codi_produto = ? and codi_tabela=?);";
		System.out.println("codi produto daoprodutocotacao >>>>>>>>>>>>>"
				+ prod.getCodi_prod_1());
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, prod.getCodi_prod_1());
			prepStm.setString(2, prod.getCodi_prod_1());
			prepStm.setString(3, daoTabPreco.pesquisaCodigoNome(nomeTab));
			ResultSet rs = prepStm.executeQuery();
			c.desconectar();
			if (rs.next()) {
				cot = new ProdutoCotacao();
				cot.setDataHoraMarcacao(rs.getTimestamp("data_hora_marcacao"));
				cot.setValor(rs.getFloat("valor"));
				System.out.println(
						"Valor do Produto >>>>>>>>>>>>>>>>>>>>>>>>>>      "
								+ cot.getValor());
				return cot;
			} else {
				cot = null;
				return cot;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ProdutoCotacao consultaUltCotVend(Produto prod) {
		c.conectar();
		System.out.println("Valor do Produto >>>>>>>>>>>>>>>>>>>>>>>>>>      "
				+ prod.getPrec_prod_1());
		String sql = "SELECT * FROM produtos_cotacoes where codi_produto = ? "
				+ "and data_hora_marcacao = (Select max(data_hora_marcacao) "
				+ "from produtos_cotacoes where codi_produto = ?);";
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, prod.getCodi_prod_1());
			prepStm.setString(2, prod.getCodi_prod_1());
			ResultSet rs = prepStm.executeQuery();
			c.desconectar();
			if (rs.next()) {
				cot = new ProdutoCotacao();
				cot.setDataHoraMarcacao(rs.getTimestamp("data_hora_marcacao"));
				cot.setValor(rs.getFloat("valor"));
				System.out.println(
						"Valor da última cotação de venda >>>>>>>>>>      "
								+ cot.getValor());
				return cot;
			} else {
				cot = null;
				return cot;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void novoPrecoProduto(String codiTabela, Date dataHoraMarcaca,
			String codiProduto, float valor) throws SQLException {
		String sql = "insert into produtos_cotacoes (codi_tabela, data_hora_marcacao, codi_produto, valor) "
				+ "values (?,?,?,?);";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, codiTabela);
		prepStm.setDate(2, dataHoraMarcaca);
		prepStm.setString(3, codiProduto);
		prepStm.setFloat(4, valor);
		prepStm.executeUpdate();
		c.desconectar();
	}
	public void novoPrecoProduto(String codiTabela, String dataHoraMarcaca,
			String codiProduto, float valor) throws SQLException {
		String sql = "insert into produtos_cotacoes (codi_tabela, data_hora_marcacao, codi_produto, valor) "
				+ "values (?,?,?,?);";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, codiTabela);
		prepStm.setString(2, dataHoraMarcaca);
		prepStm.setString(3, codiProduto);
		prepStm.setFloat(4, valor);
		prepStm.executeUpdate();
		c.desconectar();
	}
	public void novoPrecoProduto(String codiTabela, Timestamp dataHoraMarcaca,
			String codiProduto, BigDecimal valor) throws SQLException {
		String sql = "insert into produtos_cotacoes (codi_tabela, data_hora_marcacao, codi_produto, valor) "
				+ "values (?,?,?,?);";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, codiTabela);
		prepStm.setTimestamp(2, dataHoraMarcaca);
		prepStm.setString(3, codiProduto);
		prepStm.setBigDecimal(4, valor);
		prepStm.executeUpdate();
		c.desconectar();
	}

	// TODO Cotações do produto retornado um array ordenado por data ascendente
	public List<ProdutoCotacao> conCotProdOrdDtAscend(String codiProduto) {
		String sql = "select * from produtos_cotacoes where codi_produto = '"
				+ codiProduto + "' order by data_hora_marcacao asc;";
		listCot = new ArrayList<ProdutoCotacao>();
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			if (res.first()) {
				do {
					cot = new ProdutoCotacao();
					cot.setCodiProduto(res.getString("codi_produto"));
					cot.setSeqCotacaoProduto(
							res.getInt("seq_produtos_cotacoes"));
					cot.setCodiTabela(res.getString("codi_tabela"));
					cot.setDataHoraMarcacao(
							res.getTimestamp("data_hora_marcacao"));
					cot.setValor(res.getFloat("valor"));
					listCot.add(cot);
				} while (res.next());
			} else {
				cot = new ProdutoCotacao();
				cot.setCodiProduto(codiProduto);
				cot.setSeqCotacaoProduto(0);
				cot.setCodiTabela(null);
				cot.setDataHoraMarcacao(null);
				cot.setValor(0);
				listCot.add(cot);
			}
			c.desconectar();
			return listCot;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
	}

	public List<ProdutoCotacao> conCotProdOrdDtDesc(String codiProduto) {
		String sql = "select * from produtos_cotacoes where codi_produto = '"
				+ codiProduto + "' order by data_hora_marcacao desc;";
		listCot = new ArrayList<ProdutoCotacao>();
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			if (res.first()) {
				do {
					cot = new ProdutoCotacao();
					cot.setCodiProduto(res.getString("codi_produto"));
					cot.setSeqCotacaoProduto(
							res.getInt("seq_produtos_cotacoes"));
					cot.setCodiTabela(res.getString("codi_tabela"));
					cot.setDataHoraMarcacao(
							res.getTimestamp("data_hora_marcacao"));
					cot.setValor(res.getFloat("valor"));
					listCot.add(cot);
				} while (res.next());
			} else {
				cot = new ProdutoCotacao();
				cot.setCodiProduto(codiProduto);
				cot.setSeqCotacaoProduto(0);
				cot.setCodiTabela(null);
				cot.setDataHoraMarcacao(null);
				cot.setValor(0);
				listCot.add(cot);
			}
			c.desconectar();
			return listCot;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}

	public List<ProdutoCotacao> conCotProdOrdSeq(String codiProduto) {
		String sql = "select * from produtos_cotacoes where codi_produto = '"
				+ codiProduto + "' order by seq_cotacao_produto;";
		listCot = new ArrayList<ProdutoCotacao>();
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			if (res.first()) {
				do {
					cot = new ProdutoCotacao();
					cot.setCodiProduto(res.getString("codi_produto"));
					cot.setSeqCotacaoProduto(res.getInt("seq_cotacao_produto"));
					cot.setCodiTabela(res.getString("codi_tabela"));
					cot.setDataHoraMarcacao(
							res.getTimestamp("data_hora_marcacao"));
					cot.setValor(res.getFloat("valor"));
					listCot.add(cot);
				} while (res.next());
			} else {
				cot = new ProdutoCotacao();
				cot.setCodiProduto(codiProduto);
				cot.setSeqCotacaoProduto(0);
				cot.setCodiTabela(null);
				cot.setDataHoraMarcacao(null);
				cot.setValor(0);
				listCot.add(cot);
			}
			c.desconectar();
			return listCot;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
	}

	public List<ProdutoCotacao> conCotProdOrdSeqDesc(Produto prod) {
		String sql = "select * from produtos_cotacoes where codi_produto = '"
				+ prod.getCodi_prod_1()
				+ "' order by seq_produtos_cotacoes desc;";
		listCot = new ArrayList<ProdutoCotacao>();
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			if (res.first()) {
				do {
					cot = new ProdutoCotacao();
					cot.setCodiProduto(res.getString("codi_produto"));
					cot.setSeqCotacaoProduto(
							res.getInt("seq_produtos_cotacoes"));
					cot.setCodiTabela(res.getString("codi_tabela"));
					cot.setDataHoraMarcacao(
							res.getTimestamp("data_hora_marcacao"));
					cot.setValor(res.getFloat("valor"));
					listCot.add(cot);
				} while (res.next());
			} else {
				cot = new ProdutoCotacao();
				cot.setSeqCotacaoProduto(0);
				cot.setCodiTabela(null);
				cot.setDataHoraMarcacao(null);
				cot.setValor(0);
				listCot.add(cot);
			}
			c.desconectar();
			return listCot;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
	}
}
