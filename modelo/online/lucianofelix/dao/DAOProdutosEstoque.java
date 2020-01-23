package online.lucianofelix.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.Produto;
import online.lucianofelix.beans.ProdutoEstoque;
import online.lucianofelix.util.Conexao;

public class DAOProdutosEstoque {
	private Conexao c;
	private PreparedStatement prepStm;
	private ResultSet res;
	private List<ProdutoEstoque> listMov;
	private ProdutoEstoque mov;

	public DAOProdutosEstoque() {
		System.out.println("DAOProdutosEstoque.construtor");
		c = new Conexao(ConfigS.getBdPg(), "siacecf");
	}

	public void novoMovProdEstoque(String codiEstoque, Date dataHoraMovimento,
			String codiProduto, int quantidade, String codiPedido,
			String tipoMovimento) {
		String sql = "insert into produtos_estoque ( codi_estoque, data_hora_movimento, codi_produto, quantidade, codi_pedido, tipo_movimento) "
				+ "values (?,?,?,?,?,?);";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, codiEstoque);
			prepStm.setDate(2, dataHoraMovimento);
			prepStm.setString(3, codiProduto);
			prepStm.setFloat(4, quantidade);
			prepStm.setString(5, codiPedido);
			prepStm.setString(6, tipoMovimento);
			prepStm.executeUpdate();
			c.desconectar();
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
		}
	}

	public void movimentoPedido(String codiPedi, Produto[] itensProduto,
			String tipoMovimento) throws SQLException {
		// TODO Movimenta o estoque com os itens do pedido
		c.conectar();
		for (int i = 0; i < itensProduto.length; i++) {
			String sql = "insert into produtos_estoque (codi_estoque, data_hora_movimento, codi_produto, quantidade, codi_pedido, tipo_movimento) "
					+ "values (?,?,?,?,?,?);";
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, "Padrão");
			prepStm.setTimestamp(2, null);
			prepStm.setString(3, itensProduto[i].getCodi_prod_1());
			prepStm.setBigDecimal(4, itensProduto[i].getQuantMovimento());
			prepStm.setString(5, codiPedi);
			prepStm.setString(6, tipoMovimento);
			prepStm.executeUpdate();
		}
		c.desconectar();
	}

	// TODO Movimentos do produto, retorna um array ordenado por sequencia asc
	public List<ProdutoEstoque> conMovProdOrdSeqAscend(String codiProduto) {
		String sql = "select * from produtos_estoque where codi_produto = '"
				+ codiProduto + "' order by seq_movimento_estoque asc;";
		listMov = new ArrayList<ProdutoEstoque>();
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = prepStm.executeQuery();
			if (res.first()) {
				do {
					mov = new ProdutoEstoque();
					mov.setSeqMovimento(res.getInt("seq_movimento_estoque"));
					mov.setCodiPedido(res.getString("codi_estoque"));
					mov.setDataHoraMovimento(
							res.getDate("data_hora_movimento"));
					mov.setCodiProduto(res.getString("codi_produto"));
					mov.setQuantidade(res.getInt("quantidade"));
					mov.setCodiPedido(res.getString("codi_pedido"));
					mov.setTipoMovimento(res.getString("tipo_movimento"));
					listMov.add(mov);
				} while (res.next());
			} else {
				mov = new ProdutoEstoque();
				mov.setCodiProduto(codiProduto);
				listMov.add(mov);
			}
			c.desconectar();
			return listMov;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
	}

	// Consulta somente entradas ou somente saídas
	public List<ProdutoEstoque> conEntrSaiProdOrdSeqAscend(Produto prod,
			String tipoMovimento) {
		String sql = "select * from produtos_estoque where codi_produto = '"
				+ prod.getCodi_prod_1() + "' and tipo_movimento='"
				+ tipoMovimento + "' order by seq_movimento_estoque asc;";
		listMov = new ArrayList<ProdutoEstoque>();
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			if (res.first()) {
				do {
					mov = new ProdutoEstoque();
					mov.setSeqMovimento(res.getInt("seq_movimento_estoque"));
					mov.setCodiPedido(res.getString("codi_estoque"));
					mov.setDataHoraMovimento(
							res.getDate("data_hora_movimento"));
					mov.setCodiProduto(res.getString("codi_produto"));
					mov.setQuantidade(res.getInt("quantidade"));
					mov.setCodiPedido(res.getString("codi_pedido"));
					mov.setTipoMovimento(res.getString("tipo_movimento"));
					listMov.add(mov);
				} while (res.next());
			}
			c.desconectar();
			return listMov;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
	}

}
