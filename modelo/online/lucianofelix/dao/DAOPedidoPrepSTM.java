package online.lucianofelix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import online.lucianofelix.beans.Pedido;
import online.lucianofelix.util.Conexao;

public class DAOPedidoPrepSTM {
	private Conexao c;
	private Pedido pedi;
	private ArrayList<Pedido> arraypedi;
	private PreparedStatement prepStm;
	private ResultSet result;
	private DAOProdutosPedidos daoItPed;
	private DAOProdutosEstoque daoProdEstoque;
	private DAOLancamento daoContaLanc;
	private String tipoMovEstoque;
	private String tipoMovConta;

	public DAOPedidoPrepSTM() {
		System.out.println("DAOPedido.construtor");
		c = new Conexao(ConfigS.getBdPg(), "siacecf");
		daoItPed = new DAOProdutosPedidos();
		daoProdEstoque = new DAOProdutosEstoque();
		daoContaLanc = new DAOLancamento();
	}

	public Pedido procurar(int codiPedi) {
		return null;
	}

	public Pedido procurarAnterior(int codipedi) {
		return null;
	}

	public Pedido procurar(String nomepedi) {
		return null;
	}

	// TODO Pesquisa Geral
	public ArrayList<Pedido> pesquisarNome(String nome) {
		String sql = "select * from pedidos where codi_pedido ~* ? or obs_pedido_1 ~* ? or xnome_cliente ~* ? order by seq_pedido;";
		arraypedi = new ArrayList<Pedido>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, nome);
			prepStm.setString(2, nome);
			prepStm.setString(3, nome);
			result = prepStm.executeQuery();
			while (result.next()) {
				pedi = new Pedido();
				pedi.setSeqPedi(result.getInt("seq_pedido"));
				pedi.setCodiPedi(result.getString("codi_pedido"));
				pedi.setQuantItens(result.getInt("quant_itens"));
				pedi.setDataHoraPedi(result.getTimestamp("data_hora_pedido"));
				pedi.setxNome(result.getString("xnome_cliente"));
				pedi.setCodiCondPag(result.getString("codi_cond_pagamento"));
				pedi.setTotalPedi(result.getFloat("total_pedido"));
				pedi.setTipoPedido(result.getString("tipo_pedido"));
				pedi.setCodiPessoaCliente(
						result.getString("codi_pessoa_cliente"));
				pedi.setCodiTabPreco(result.getString("codi_tabela_preco"));
				pedi.setObsPedi1(result.getString("obs_pedido_1"));
				arraypedi.add(pedi);
			}
			c.desconectar();
			return arraypedi;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}

	public ArrayList<Pedido> pesquisarTipo(String tipoPedido) {
		String sql = "select * from pedidos where tipo_pedido = ? order by seq_pedido;";
		arraypedi = new ArrayList<Pedido>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, tipoPedido);
			result = prepStm.executeQuery();
			while (result.next()) {
				pedi = new Pedido();
				pedi.setSeqPedi(result.getInt("seq_pedido"));
				pedi.setCodiPedi(result.getString("codi_pedido"));
				pedi.setQuantItens(result.getInt("quant_itens"));
				pedi.setDataHoraPedi(result.getTimestamp("data_hora_pedido"));
				pedi.setxNome(result.getString("xnome_cliente"));
				pedi.setCodiCondPag(result.getString("codi_cond_pagamento"));
				pedi.setTotalPedi(result.getFloat("total_pedido"));
				pedi.setTipoPedido(result.getString("tipo_pedido"));
				pedi.setCodiPessoaCliente(
						result.getString("codi_pessoa_cliente"));
				pedi.setCodiTabPreco(result.getString("codi_tabela_preco"));
				pedi.setObsPedi1(result.getString("obs_pedido_1"));
				arraypedi.add(pedi);
			}
			c.desconectar();
			return arraypedi;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}
	public ArrayList<Pedido> carregaPedCliente(String codiCliente) {
		String sql = "select * from pedidos where codi_pessoa_cliente = ? order by seq_pedido;";
		arraypedi = new ArrayList<Pedido>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, codiCliente);
			result = prepStm.executeQuery();
			while (result.next()) {
				pedi = new Pedido();
				pedi.setSeqPedi(result.getInt("seq_pedido"));
				pedi.setCodiPedi(result.getString("codi_pedido"));
				pedi.setQuantItens(result.getInt("quant_itens"));
				pedi.setDataHoraPedi(result.getTimestamp("data_hora_pedido"));
				pedi.setxNome(result.getString("xnome_cliente"));
				pedi.setCodiCondPag(result.getString("codi_cond_pagamento"));
				pedi.setTotalPedi(result.getFloat("total_pedido"));
				pedi.setTipoPedido(result.getString("tipo_pedido"));
				pedi.setCodiPessoaCliente(
						result.getString("codi_pessoa_cliente"));
				pedi.setCodiTabPreco(result.getString("codi_tabela_preco"));
				pedi.setObsPedi1(result.getString("obs_pedido_1"));
				arraypedi.add(pedi);
			}
			c.desconectar();
			return arraypedi;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}

	public boolean alterar(Pedido pedi) {
		String sql = "update pedidos set quant_itens=?, total_pedido=?, obs_pedido_1=?, obs_pedido_2=?,"
				+ " codi_pessoa_cliente=?, num_nota=?, codi_cond_pagamento=?, xnome_cliente=?, "
				+ "data_hora_pedido=?, tipo_pedido=?, codi_pessoa_vendedor=?,nome_vendedor=?,"
				+ "nome_cond_pagamento=?, codi_tabela_preco=? where codi_pedido=?;";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setInt(1, pedi.getQuantItens());
			prepStm.setFloat(2, pedi.getTotalPedi());
			prepStm.setString(3, pedi.getObsPedi1());
			prepStm.setString(4, pedi.getObsPedi2());
			prepStm.setString(5, pedi.getCodiPessoaCliente());
			prepStm.setString(6, pedi.getNumNota());
			prepStm.setString(7, pedi.getCodiCondPag());
			prepStm.setString(8, pedi.getxNome());
			prepStm.setTimestamp(9, pedi.getDataHoraPedi());
			prepStm.setString(10, pedi.getTipoPedido());
			prepStm.setString(11, pedi.getCodiPessoaVendedor());
			prepStm.setString(12, pedi.getNomeVendedor());
			prepStm.setString(13, pedi.getNomeCondPagamento());
			prepStm.setString(14, pedi.getCodiTabPreco());
			prepStm.setString(15, pedi.getCodiPedi());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return false;
		}
	}

	public boolean tabelaVazia() {
		// TODO Verificar se a tabela está vazia
		c.conectar();
		String sql = "Select COUNT(*) as total from pedidos";
		try {
			ResultSet result = c.getCon().createStatement().executeQuery(sql);
			if (result.getRow() == 0) {
				c.desconectar();
				return true;
			} else {
				c.desconectar();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		c.desconectar();
		return false;
	}

	// TODO Remove um pedido
	public boolean remover(Pedido pedi) {
		String sql = "delete from pedidos where codi_pedido=?; ";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, pedi.getCodiPedi());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (Exception e) {
			c.desconectar();
			e.printStackTrace();
			return false;
		}
	}

	public int consultaUltimo() {
		c.conectar();
		String sql = "Select MAX(seq_pedido) as ultimo from pedidos";
		try {
			ResultSet result = c.getCon().createStatement().executeQuery(sql);
			result.next();
			int numUltimo = result.getInt("ultimo");
			c.desconectar();
			return numUltimo;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return 0;
		}
	}

	public void reservaCodigo(String codipedido) throws SQLException {
		String sql = "insert into pedidos (codi_pedido) values (?)";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, codipedido);
		prepStm.executeUpdate();
		c.desconectar();
	}

}
