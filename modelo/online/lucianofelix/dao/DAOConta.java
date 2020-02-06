package online.lucianofelix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.Conta;
import online.lucianofelix.util.Conexao;
import online.lucianofelix.util.ConexaoSTM;

public class DAOConta {
	private Conexao c;
	private ConexaoSTM c2;
	private ResultSet result;
	private PreparedStatement prepStm;
	private Conta conta;
	private List<Conta> listConta;
	private DAOCentroCusto daoCCusto;

	public DAOConta() {
		System.out.println("DAOConta.construtor");
		c = new Conexao(ConfigS.getBdPg(), "siacecf");
		c2 = new ConexaoSTM(ConfigS.getBdPg(), "siacecf");

	}

	/**
	 * Reserva um código livre na tabela de contas
	 * 
	 * @param codigo
	 * @throws SQLException
	 */
	public void reservaCodigo(String codigo) throws SQLException {
		String sql = "insert into tbl_conta (codi_conta) values (?)";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, codigo);
		prepStm.executeUpdate();
		c.desconectar();
	}
	/**
	 * Lista de contas por tipo
	 * 
	 * @param tipoConta
	 * @return
	 */
	public List<Conta> listContTipo(String tipoConta) {
		System.out.println("DAOConta.listContaTipo");
		String sql = "select * from tbl_contas where tipo_conta = ? order by nome_conta;";
		listConta = new ArrayList<Conta>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, tipoConta);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				conta = new Conta();
				conta.setSeqConta(res.getInt("seq_conta"));
				conta.setCodiConta(res.getString("codi_conta"));
				conta.setAgencia(res.getString("agencia"));
				conta.setConta(res.getString("conta"));
				conta.setBanco(res.getString("banco"));
				conta.setNomeConta(res.getString("nome_conta"));
				conta.setTiular(res.getString("titular"));
				conta.setNumCartao(res.getString("num_cartao"));
				conta.setSenha(res.getString("senha"));
				conta.setDescConta(res.getString("desc_conta"));
				conta.setObsConta(res.getString("obs_conta"));
				conta.setTipoConta(res.getString("tipo_conta"));
				conta.setCentroCusto(res.getString("codi_centro_custo"));
				listConta.add(conta);
			}
			c.desconectar();
			return listConta;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}
	/**
	 * Lista de contas por nome do centro de custo
	 * 
	 * @param nomeCentroCusto
	 * @return
	 */
	public List<Conta> listContCCusto(String nomeCentroCusto) {
		System.out.println("DAOConta.listContaCCusto");
		daoCCusto = new DAOCentroCusto();
		String codiCentro = daoCCusto.buscaCodigoNome(nomeCentroCusto);
		String sql = "select * from tbl_contas where codi_centro_custo = ? order by nome_conta;";
		listConta = new ArrayList<Conta>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, codiCentro);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				conta = new Conta();
				conta.setSeqConta(res.getInt("seq_conta"));
				conta.setCodiConta(res.getString("codi_conta"));
				conta.setAgencia(res.getString("agencia"));
				conta.setConta(res.getString("conta"));
				conta.setBanco(res.getString("banco"));
				conta.setNomeConta(res.getString("nome_conta"));
				conta.setTiular(res.getString("titular"));
				conta.setNumCartao(res.getString("num_cartao"));
				conta.setSenha(res.getString("senha"));
				conta.setDescConta(res.getString("desc_conta"));
				conta.setObsConta(res.getString("obs_conta"));
				conta.setTipoConta(res.getString("tipo_conta"));
				conta.setCentroCusto(res.getString("codi_centro_custo"));
				listConta.add(conta);
			}
			c.desconectar();
			return listConta;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}
	/**
	 * Cadastra uma nova conta sem reserva de codigo
	 * 
	 * @param conta
	 * @return
	 */
	public boolean cadastrar(Conta conta) {
		String sql = "insert into tbl_contas (codi_conta, agencia, conta, banco, nome_conta, "
				+ "titular, num_cartao, senha, desc_conta, obs_conta, tipo_conta, codi_centro_custo) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, conta.getCodiConta());
			prepStm.setString(2, conta.getAgencia());
			prepStm.setString(3, conta.getConta());
			prepStm.setString(4, conta.getBanco());
			prepStm.setString(5, conta.getNomeConta());
			prepStm.setString(6, conta.getTiular());
			prepStm.setString(7, conta.getNumCartao());
			prepStm.setString(8, conta.getSenha());
			prepStm.setString(9, conta.getDescConta());
			prepStm.setString(10, conta.getObsConta());
			prepStm.setString(11, conta.getTipoConta());
			prepStm.setString(12, conta.getCentroCusto());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Retorna o codigo do centro de custo da conta por codigo da conta
	 * 
	 * @param String
	 *            codigoConta
	 * @return String codiCentroCusto
	 * 
	 */
	public String cCustoCodiConta(String codigoConta) {
		String sql = "select codi_centro_custo from contas_centro_custo where codi_conta =?;";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, codigoConta);
			result = prepStm.executeQuery();
			if (result.next()) {
				String codiCCusto = result.getString("codigo_centro_custo");
				c.desconectar();
				return codiCCusto;
			} else {
				return null;
			}
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * Retorna o nome da conta por codigo
	 * 
	 * @param codigoConta
	 * @return
	 */
	public String nomeCtaCodigo(String codigoConta) {
		String sql = "select nome_conta from tbl_contas where codi_conta =?;";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, codigoConta);
			result = prepStm.executeQuery();
			if (result.next()) {
				String codiCCusto = result.getString("nome_conta");
				c.desconectar();
				return codiCCusto;
			} else {
				return null;
			}
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * Consulta o ultimo centro de custo cadastrado
	 * 
	 * @return
	 */
	public int consultaUltimo() {
		System.out.println("DAOConta.consultarUltimo");
		c2.conectStm();
		result = c2.query("SELECT max(seq_conta) FROM tbl_contas;");
		c2.disconect();
		try {
			if (result.next()) {
				System.out.println("Está retornando: " + result.getInt(1));
				return result.getInt(1);
			} else {
				return 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * Pesquisa geral em 3 campos
	 * 
	 * @param str
	 * @return
	 */
	public List<Conta> pesquisarString(String str) {
		System.out.println("DAOConta.pesquisarString");
		String sql = "select * from tbl_contas where codi_conta ~* ? or agencia ~* ? or conta ~* ? or banco = ? or nome_conta ~* ? order by nome_conta;";
		listConta = new ArrayList<Conta>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, str);
			prepStm.setString(2, str);
			prepStm.setString(3, str);
			prepStm.setString(4, str);
			prepStm.setString(5, str);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				conta = new Conta();
				conta.setSeqConta(res.getInt("seq_conta"));
				conta.setCodiConta(res.getString("codi_conta"));
				conta.setAgencia(res.getString("agencia"));
				conta.setConta(res.getString("conta"));
				conta.setBanco(res.getString("banco"));
				conta.setNomeConta(res.getString("nome_conta"));
				conta.setTiular(res.getString("titular"));
				conta.setNumCartao(res.getString("num_cartao"));
				conta.setSenha(res.getString("senha"));
				conta.setDescConta(res.getString("desc_conta"));
				conta.setObsConta(res.getString("obs_conta"));
				conta.setTipoConta(res.getString("tipo_conta"));
				conta.setCentroCusto(res.getString("codi_centro_custo"));
				listConta.add(conta);
			}
			c.desconectar();
			return listConta;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}
	/**
	 * Pesquisa contas do centro de custo
	 * 
	 * @param str
	 * @return
	 */
	public List<Conta> pesqContasCodiCCusto(String codiCentro) {
		System.out.println("DAOConta.pesquisarString");
		String sql = "select * from tbl_contas where codi_centro_custo = ? order by nome_conta;";
		listConta = new ArrayList<Conta>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, codiCentro);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				conta = new Conta();
				conta.setSeqConta(res.getInt("seq_conta"));
				conta.setCodiConta(res.getString("codi_conta"));
				conta.setAgencia(res.getString("agencia"));
				conta.setConta(res.getString("conta"));
				conta.setBanco(res.getString("banco"));
				conta.setNomeConta(res.getString("nome_conta"));
				conta.setTiular(res.getString("titular"));
				conta.setNumCartao(res.getString("num_cartao"));
				conta.setSenha(res.getString("senha"));
				conta.setDescConta(res.getString("desc_conta"));
				conta.setObsConta(res.getString("obs_conta"));
				conta.setTipoConta(res.getString("tipo_conta"));
				conta.setCentroCusto(res.getString("codi_centro_custo"));
				listConta.add(conta);
			}
			c.desconectar();
			return listConta;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}
	/**
	 * Busca todas as contas do centro de custo por nome
	 * 
	 * @param nomeCCusto
	 * @return
	 */
	public List<Conta> pesqContasNomeCCusto(String nomeCCentro) {
		System.out.println("DAOConta.pesquisarString");
		daoCCusto = new DAOCentroCusto();
		String codiCentro = daoCCusto.buscaCodigoNome(nomeCCentro);
		String sql = "select * from tbl_contas where codi_centro_custo = ? order by nome_conta;";
		listConta = new ArrayList<Conta>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, codiCentro);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				conta = new Conta();
				conta.setSeqConta(res.getInt("seq_conta"));
				conta.setCodiConta(res.getString("codi_conta"));
				conta.setAgencia(res.getString("agencia"));
				conta.setConta(res.getString("conta"));
				conta.setBanco(res.getString("banco"));
				conta.setNomeConta(res.getString("nome_conta"));
				conta.setTiular(res.getString("titular"));
				conta.setNumCartao(res.getString("num_cartao"));
				conta.setSenha(res.getString("senha"));
				conta.setDescConta(res.getString("desc_conta"));
				conta.setObsConta(res.getString("obs_conta"));
				conta.setTipoConta(res.getString("tipo_conta"));
				conta.setCentroCusto(res.getString("codi_centro_custo"));
				listConta.add(conta);
			}
			c.desconectar();
			return listConta;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}
	/**
	 * (não funcional) Retornar tudo da conta onde a pesquisa contar com o
	 * parametro de codigo de centro de custo
	 * 
	 * @param str
	 * @param codiCentro
	 * @return
	 */
	public List<Conta> pesquisarString(String str, String codiCentro) {
		System.out.println("DAOConta.pesquisarString");
		String sql = "select * from tbl_contas where codi_conta ~* ? or agencia ~* ? or conta ~* ? or banco = ? or nome_conta ~* ? and codi_centro_custo = ? order by nome_conta;";
		listConta = new ArrayList<Conta>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, str);
			prepStm.setString(2, str);
			prepStm.setString(3, str);
			prepStm.setString(4, str);
			prepStm.setString(5, str);
			prepStm.setString(6, codiCentro);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				conta = new Conta();
				conta.setSeqConta(res.getInt("seq_conta"));
				conta.setCodiConta(res.getString("codi_conta"));
				conta.setAgencia(res.getString("agencia"));
				conta.setConta(res.getString("conta"));
				conta.setBanco(res.getString("banco"));
				conta.setNomeConta(res.getString("nome_conta"));
				conta.setTiular(res.getString("titular"));
				conta.setNumCartao(res.getString("num_cartao"));
				conta.setSenha(res.getString("senha"));
				conta.setDescConta(res.getString("desc_conta"));
				conta.setObsConta(res.getString("obs_conta"));
				conta.setTipoConta(res.getString("tipo_conta"));
				conta.setCentroCusto(res.getString("codi_centro_custo"));

				listConta.add(conta);
			}
			c.desconectar();
			return listConta;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}
	// "select seq_conta, codi_conta, agencia, conta, banco,"
	// + "nome_conta, titular, num_cartao, senha, desc_conta, obs_conta,"
	// + "tipo_conta, codi_centro_custo from contas group by codi_centro_custo,
	// seq_conta;";

	public List<Conta> listContaGroupByCCusto() {
		System.out.println("DAOConta.pesquisarString");
		String sql = "select seq_conta, codi_conta, nome_conta, titular, codi_centro_custo, tipo_conta"
				+ " from tbl_contas order by codi_centro_custo, nome_conta;";
		listConta = new ArrayList<Conta>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				conta = new Conta();
				conta.setSeqConta(res.getInt("seq_conta"));
				conta.setCodiConta(res.getString("codi_conta"));
				conta.setNomeConta(res.getString("nome_conta"));
				conta.setTiular(res.getString("titular"));
				conta.setCentroCusto(res.getString("codi_centro_custo"));
				conta.setTipoConta(res.getString("tipo_conta"));
				listConta.add(conta);
			}
			c.desconectar();
			return listConta;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}
	/**
	 * Alterar a conta
	 * 
	 * @param conta
	 * @return
	 */
	public boolean alterar(Conta conta) {
		System.out.println("DAOConta.alterar");
		String sql = "update tbl_contas set  agencia=?, conta=?, banco=?, nome_conta=?, titular=?, "
				+ "num_cartao=?, senha=?, desc_conta=?, obs_conta=?,tipo_conta=?,codi_centro_custo=?"
				+ "  where codi_conta =?;";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, conta.getAgencia());
			prepStm.setString(2, conta.getConta());
			prepStm.setString(3, conta.getBanco());
			prepStm.setString(4, conta.getNomeConta());
			prepStm.setString(5, conta.getTiular());
			prepStm.setString(6, conta.getNumCartao());
			prepStm.setString(7, conta.getSenha());
			prepStm.setString(8, conta.getDescConta());
			prepStm.setString(9, conta.getObsConta());
			prepStm.setString(10, conta.getTipoConta());
			prepStm.setString(11, conta.getCentroCusto());
			prepStm.setString(12, conta.getCodiConta());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (Exception e) {
			c.desconectar();
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Exclui a conta
	 * 
	 * @param conta
	 * @return
	 */

	public boolean excluir(Conta conta) {
		System.out.println("DAOConta.excluir");
		String sql = "delete from tbl_contas where codi_conta=?;";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, conta.getCodiConta());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (Exception e) {
			c.desconectar();
			e.printStackTrace();
			return false;
		}
	}

}
