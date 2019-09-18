package online.lucianofelix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.Produto;
import online.lucianofelix.util.Conexao;
import online.lucianofelix.util.ConexaoSTM;
import online.lucianofelix.visao.FrameInicial;

public class DAOProdutoPrepSTM {
	private Conexao c;
	private ConexaoSTM c2;
	private Produto prod;
	private PreparedStatement prepStm;
	private ResultSet result;
	private List<Produto> listProd;
	private DAOProdutosCotacao daoCotProd;

	/**
	 * @category Classe de acessoa a dados do produto.
	 * @author Luciano de O. Felix
	 */

	// nome_prod text NOT NULL, -- Nome do produto.
	// desc_prod text, -- Descrição do ítem.
	// aliq_prod text NOT NULL, -- Aliquota de ICMS do produto.
	// prec_custo real, -- Preço de custo produto.
	// seq_produto integer NOT NULL DEFAULT sequencia de inserção
	// codi_prod_1 text NOT NULL, -- O principal código do produto.
	// prec_prod_1 real, -- Primeiro preço do produto.
	// prec_prod_2 real, -- Segundo preço de venda do produto.
	// codi_prod_2 text, -- Código extra 2 para o produto.

	public DAOProdutoPrepSTM() {
		System.out.println("DAOProduto.construtor");
		daoCotProd = new DAOProdutosCotacao();
		c = new Conexao(ConfigS.getBdPg(), "siacecf");
		c2 = new ConexaoSTM(ConfigS.getBdPg(), "siacecf");
	}

	// TODO Alterar
	public boolean alterar(Produto prod) {
		System.out.println("DaoProduto.alterar");
		String sql = "update produtos set nome_prod=?, desc_prod=?, aliq_prod=?, prec_custo=?,"
				+ " prec_prod_1=?, prec_prod_2=?, codi_prod_2=?  where codi_prod_1=?;";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, prod.getNome_prod());
			prepStm.setString(2, prod.getDesc_prod());
			prepStm.setString(3, prod.getAliq_prod());
			prepStm.setFloat(4, prod.getPrec_custo());
			prepStm.setFloat(5, prod.getPrec_prod_1());
			prepStm.setFloat(6, prod.getPrec_prod_2());
			prepStm.setString(7, prod.getCodi_prod_2());
			prepStm.setString(8, prod.getCodi_prod_1());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			c.desconectar();
			e.printStackTrace();
			FrameInicial.pesquisaProduto();
			return false;
		}
	}

	// TODO Cadastrar/ Inserir
	public boolean cadastrar(Produto prod) {
		String sql = "insert into produtos (nome_prod, desc_prod, aliq_prod, prec_custo, prec_prod_1,"
				+ " prec_prod_2, codi_prod_1, codi_prod_2) values (?,?,?,?,?,?,?,?)";

		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, prod.getNome_prod());
			prepStm.setString(2, prod.getDesc_prod());
			prepStm.setString(3, prod.getAliq_prod());
			prepStm.setFloat(4, prod.getPrec_custo());
			prepStm.setFloat(5, prod.getPrec_prod_1());
			prepStm.setFloat(6, prod.getPrec_prod_2());
			prepStm.setString(7, prod.getCodi_prod_1());
			prepStm.setString(8, prod.getCodi_prod_2());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			c.desconectar();
			e.printStackTrace();
			return false;
		}

	}

	// Consulta �ltimo produto inserido
	public int consultaUltimo() {
		c2.conectStm();
		result = c2.query("SELECT max(seq_produto) FROM produtos;");
		c2.disconect();
		try {
			result.next();
			return result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}

	public ResultSet getResult() {
		return result;
	}

	public List<Produto> pesquisaString(String str) {
		String sql = "select seq_produto, codi_prod_1, nome_prod, desc_prod, aliq_prod, prec_custo, prec_prod_1 from produtos where nome_prod ~* ?  or desc_prod ~* ? or codi_prod_1 ~* ? order by nome_prod;";
		listProd = new ArrayList<Produto>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, str);
			prepStm.setString(2, str);
			prepStm.setString(3, str);
			result = prepStm.executeQuery();
			while (result.next()) {
				prod = new Produto();
				prod.setSeq_produto(result.getInt("seq_produto"));
				prod.setCodi_prod_1(result.getString("codi_prod_1"));
				prod.setNome_prod(result.getString("nome_prod"));
				prod.setDesc_prod(result.getString("desc_prod"));
				prod.setAliq_prod(result.getString("aliq_prod"));
				prod.setPrec_custo(result.getFloat("prec_custo"));
				prod.setPrec_prod_1(result.getFloat("prec_prod_1"));
				listProd.add(prod);
			}
			c.desconectar();
			return listProd;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}

	public Produto procurar(String codiProduto) {
		prod = new Produto();
		c2.conectStm();
		result = c2.query("SELECT * FROM produtos WHERE codi_prod_1 ='"
				+ codiProduto + "';");
		c2.disconect();
		try {
			if (result.next()) {
				prod.setNome_prod(result.getString("nome_prod"));
				prod.setDesc_prod(result.getString("desc_prod"));
				prod.setAliq_prod(result.getString("aliq_prod"));
				prod.setPrec_custo(result.getFloat("prec_custo"));
				prod.setSeq_produto(result.getInt("seq_produto"));
				prod.setCodi_prod_1(result.getString("codi_prod_1"));
				prod.setPrec_prod_1(result.getFloat("prec_prod_1"));
				return prod;
			} else {
				return null;
			}
		} catch (SQLException e) {
			c2.disconect();
			e.printStackTrace();
			return null;
		}
	}

	public void reservaCodigo(String codiProduto) throws SQLException {
		String sql = "insert into produtos (codi_prod_1) values (?)";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, codiProduto);
		prepStm.executeUpdate();
		c.desconectar();
	}

	public Produto procurarAnterior(int codiProd) {
		return null;
	}

	public Produto procurarAnteriorStmt(int codiProd) {
		prod = new Produto();
		c2.conectStm();
		result = c2
				.query("SELECT codiprod, nomeprod,descprod,aliqprod,quanprod,precprod FROM produtos WHERE codiprod="
						+ codiProd);
		c2.disconect();
		try {
			result.next();
			prod.setAliq_prod(result.getString("aliq_prod"));
			// prod.setEstoque(result.getInt("quan_prod"));
			prod.setPrec_prod_1(result.getFloat("prec_prod"));
			prod.setCodi_prod_1(result.getString("codi_prod"));
			prod.setNome_prod(result.getString("nome_prod"));
			prod.setDesc_prod(result.getString("desc_prod"));
			prod.setSeq_produto(result.getInt("seq_produto"));
			return prod;

		} catch (SQLException e) {
			c2.disconect();
			e.printStackTrace();
			return null;
		}
	}

	public Produto procurarProximo(int codiProd) {
		return prod;
	}

	public Produto procurarProximoStmt(int codiProd) {
		prod = new Produto();
		c2.conectStm();
		result = c2.query("SELECT * FROM produtos WHERE codi_prod=" + codiProd);
		c2.disconect();
		try {
			result.next();
			prod.setCodi_prod_1(result.getString("codi_prod"));
			prod.setCodi_prod_2(result.getString("codi_prod_2"));
			prod.setNome_prod(result.getString("nome_prod"));
			prod.setDesc_prod(result.getString("desc_prod"));
			prod.setAliq_prod(result.getString("aliq_prod"));
			prod.setPrec_custo(result.getFloat("prec_custo real"));
			prod.setPrec_prod_1(result.getFloat("prec_prod_1"));
			prod.setPrec_prod_2(result.getFloat("prec_prod_2"));
			prod.setSeq_produto(result.getInt("seq_produto"));
			return prod;
		} catch (SQLException e) {
			e.printStackTrace();
			c2.disconect();
			return null;
		}
	}

	public Produto procurarStmt(int codiProd) {
		prod = new Produto();
		c2.conectStm();
		result = c2
				.query("SELECT codiprod,nomeprod,descprod,aliqprod,quanprod,precprod FROM produtos WHERE codiprod='"
						+ codiProd + "';");
		c2.disconect();
		try {
			result.next();
			prod.setCodi_prod_1(result.getString("codi_prod"));
			prod.setNome_prod(result.getString("nome_prod"));
			prod.setDesc_prod(result.getString("desc_prod"));
			prod.setAliq_prod(result.getString("aliq_prod"));
			prod.setPrec_prod_1(result.getFloat("prec_prod_1"));
			prod.setSeq_produto(result.getInt("seq_produto"));
			return prod;
		} catch (SQLException e) {
			c2.disconect();
			e.printStackTrace();
			return null;
		}
	}

	public List<Produto> procurarTodos() {
		String sql = "select * from produtos order by nomeprod; ";
		listProd = new ArrayList<Produto>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				prod = new Produto();
				prod.setCodi_prod_1(res.getString("codi_prod"));
				prod.setNome_prod(res.getString("nome_prod"));
				prod.setDesc_prod(res.getString("desc_prod"));
				prod.setAliq_prod(res.getString("aliq_prod"));
				prod.setPrec_prod_1(res.getFloat("prec_prod_1"));
				prod.setSeq_produto(res.getInt("seq_produto"));
				listProd.add(prod);
			}
			c.desconectar();
			return listProd;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
	}

	public List<Produto> procurarTodosStmt() {
		listProd = new ArrayList<Produto>();
		c2.conectSTMScroll();
		result = c2.queryScroll("SELECT * FROM produtos;");
		try {
			result.first();
			do {
				prod = new Produto();
				prod.setCodi_prod_1(result.getString("codi_prod_1"));
				prod.setNome_prod(result.getString("nome_prod"));
				prod.setDesc_prod(result.getString("desc_prod"));
				prod.setAliq_prod(result.getString("aliq_prod"));
				// prod.setEstoque(result.getInt("quan_prod")); // resolver
				// estoque
				prod.setPrec_prod_1(result.getFloat("prec_prod_1"));
				prod.setSeq_produto(result.getInt("seq_produto"));
				listProd.add(prod);
			} while (result.next());
			c2.disconect();
			return listProd;
		} catch (SQLException e) {
			c2.disconect();
			e.printStackTrace();
			return null;
		}
	}

	// Remove um produto
	public boolean excluir(Produto prod) {
		String sql = "delete from produtos where codi_prod_1=?; ";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, prod.getCodi_prod_1());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (Exception e) {
			c.desconectar();
			e.printStackTrace();
			return false;
		}
	}

	public void setResult(ResultSet result) {
		this.result = result;
	}
}
