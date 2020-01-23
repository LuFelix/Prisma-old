package online.lucianofelix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.Pedido;
import online.lucianofelix.beans.Produto;
import online.lucianofelix.util.Conexao;

public class DAOProdutosPedidos {
	private Conexao c;
	private PreparedStatement prepStm;
	private List<Produto> arrayItProd;
	private Produto prod;
	private DAOProdutosCotacao daoCotProd;
	private DAOProdutoPrepSTM daoProd;
	private ResultSet result;

	public DAOProdutosPedidos() {
		System.out.println("DAOProdutosPedidos.construtor");
		daoCotProd = new DAOProdutosCotacao();
		daoProd = new DAOProdutoPrepSTM();
		c = new Conexao(ConfigS.getBdPg(), "siacecf");
	}

	public void inserirArrayItens(String codiPedi, Produto[] itensProduto)
			throws SQLException {
		// TODO Inserir os ítens de cada Pedido
		c.conectar();
		for (int i = 0; i < itensProduto.length; i++) {
			String sql = "insert into produtos_pedidos (codi_pedido, codi_prod_1,  prec_prod, quant_itens)"
					+ " values (?,?,?,?)";
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, codiPedi);
			prepStm.setString(2, itensProduto[i].getCodi_prod_1());
			prepStm.setBigDecimal(3, itensProduto[i].getPrec_prod_1());
			prepStm.setBigDecimal(4, itensProduto[i].getQuantMovimento());
			prepStm.executeUpdate();
		}
		c.desconectar();
	}

	// TODO Insere os ítens de cada Pedido um a um
	public void inserirItem(Pedido pedi, Produto prod) throws SQLException {
		c.conectar();
		String sql = "insert into produtos_pedidos (codi_pedido, codi_prod_1,  prec_prod, quant_itens,nome_produto) values (?,?,?,?,?);";
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, pedi.getCodiPedi());
		prepStm.setString(2, prod.getCodi_prod_1());
		prepStm.setBigDecimal(3, prod.getPrec_prod_1());
		prepStm.setBigDecimal(4, prod.getQuantMovimento());
		prepStm.setString(5, prod.getNome_prod());
		prepStm.executeUpdate();
		c.desconectar();
	}

	public void alterarItensPedido(String codiPedi, Produto[] itensProduto)
			throws SQLException {
		// TODO Inserir os ítens de cada Pedido
		c.conectar();
		for (int i = 0; i < itensProduto.length; i++) {
			String sql = "update produtos_pedidos (codi_pedido, codi_prod_1,"
					+ " nome_prod, desc_prod, prec_prod, aliq_prod, quant_itens) values (?,?,?,?,?,?,?)";

			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, codiPedi);
			prepStm.setString(2, itensProduto[i].getCodi_prod_1());
			prepStm.setString(3, itensProduto[i].getNome_prod());
			prepStm.setString(4, itensProduto[i].getDesc_prod());
			prepStm.setBigDecimal(5, itensProduto[i].getPrec_prod_1());
			prepStm.setString(6, itensProduto[i].getAliq_prod());
			prepStm.setBigDecimal(7, itensProduto[i].getQuantMovimento());
			prepStm.executeUpdate();
		}
		c.desconectar();
	}

	public List<Produto> pesquisaItensPedido(Pedido pedi) {
		String sql = "select * from produtos_pedidos where codi_pedido = ?;";
		arrayItProd = new ArrayList<Produto>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, pedi.getCodiPedi());
			result = prepStm.executeQuery();
			if (!pedi.equals(null)) {
				while (result.next()) {
					prod = new Produto();
					prod.setCodi_prod_1(result.getString("codi_prod_1"));
					prod.setPrec_prod_1(result.getBigDecimal("prec_prod"));
					prod.setQuantMovimento(result.getBigDecimal("quant_itens"));
					prod.setNome_prod(result.getString("nome_produto"));
					arrayItProd.add(prod);
				}
			}

			c.desconectar();
			return arrayItProd;
		} catch (

		SQLException e)

		{
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}

	// TODO Remove um ítem do pedido
	public boolean removerItem(Pedido pedi, Produto prod) {
		c.conectar();
		String sql = "delete from produtos_pedidos where codi_pedido=? and codi_prod_1=?;";
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, pedi.getCodiPedi());
			prepStm.setString(2, prod.getCodi_prod_1());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return false;
		}

	}

	// Alterar a quantidade do ítem
	public void alterarQuantItem(Pedido pedi, Produto prod) {
		c.conectar();
		String sql = "update produtos_pedidos set quant_itens =? where codi_pedido=? and codi_prod_1=?;";
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setBigDecimal(1, prod.getQuantMovimento());
			prepStm.setString(2, pedi.getCodiPedi());
			prepStm.setString(3, prod.getCodi_prod_1());
			prepStm.executeUpdate();
			c.desconectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			c.desconectar();
		}

	}

}
