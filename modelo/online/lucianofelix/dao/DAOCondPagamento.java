package online.lucianofelix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.CondPagamento;
import online.lucianofelix.util.Conexao;
import online.lucianofelix.util.ConexaoSTM;

public class DAOCondPagamento {
	private Conexao c;
	private CondPagamento condPag;
	private ArrayList<CondPagamento> arrayCondPag;
	private PreparedStatement prepStm;
	private ResultSet result;
	ConexaoSTM c2;
	List<CondPagamento> listCondPag;

	public DAOCondPagamento() {
		System.out.println("DAOCondicaoPagamento.construtor");
		c = new Conexao(ConfigS.getBdPg(), "siacecf");
		c2 = new ConexaoSTM(ConfigS.getBdPg(), "siacecf");
	}

	// TODO Alterar
	public void alterar(CondPagamento condPag) throws SQLException {
		String sql = "update tbl_cond_pagamento set nome_cond_pag=?, indice_juros=?,"
				+ " desconto_acrescimo=?, gera_conta_receber=?,  quant_parcelas=?, tipo_numeracao =?, "
				+ "condicao_ecf=?, senha_cond_pag=?, data_cadastro=?, data_ultima_alteracao=? where codi_cond_pag=?;";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, condPag.getNomeCondicao());
		prepStm.setFloat(2, condPag.getIndiceJuros());
		prepStm.setFloat(3, condPag.getDescontoAcrescimo());
		prepStm.setString(4, condPag.getGeraContasReceber());
		prepStm.setFloat(5, condPag.getQuantParcelas());
		prepStm.setInt(6, condPag.getTipoNumeracao());
		prepStm.setInt(7, condPag.getCondicaoECF());
		prepStm.setString(8, condPag.getSenha());
		prepStm.setTimestamp(9, condPag.getData_cadastro());
		prepStm.setTimestamp(10, condPag.getData_ultima_alteracao());
		prepStm.setString(11, condPag.getCodiCondPag());
		prepStm.executeUpdate();
		c.desconectar();
	}

	// TESTAR
	public void cadastrar(CondPagamento condPag) throws SQLException {
		String sql = "insert into tbl_cond_pagamento (codi_cond_pag,  nome_cond_pagamento , indice_juros,"
				+ " desconto_acrescimo, gera_conta_receber,  quant_parcelas, tipo_numeracao, "
				+ "condicao_ecf, senha_cond_pag, data_cadastro, data_ultima_alteracao) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?)";

		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, condPag.getCodiCondPag());
		prepStm.setString(2, condPag.getNomeCondicao());
		prepStm.setFloat(3, condPag.getIndiceJuros());
		prepStm.setFloat(4, condPag.getDescontoAcrescimo());
		prepStm.setString(5, condPag.getGeraContasReceber());
		prepStm.setFloat(6, condPag.getQuantParcelas());
		prepStm.setInt(7, condPag.getTipoNumeracao());
		prepStm.setInt(8, condPag.getCondicaoECF());
		prepStm.setString(9, condPag.getSenha());
		prepStm.setTimestamp(10, condPag.getData_cadastro());
		prepStm.setTimestamp(11, condPag.getData_ultima_alteracao());
		prepStm.executeUpdate();
		c.desconectar();
	}

	// TODO Pesquisa String generico pesquisa em varios campos
	public List<CondPagamento> pesquisaString(String str) {
		String sql = "select * from tbl_cond_pagamento where codi_cond_pag ~* ?  or nome_cond_pag ~* ? order by nome_cond_pag;";
		arrayCondPag = new ArrayList<CondPagamento>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, str);
			prepStm.setString(2, str);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				condPag = new CondPagamento();
				condPag.setSeq_condPagamento(res.getInt("seq_cond"));
				condPag.setCodiCondPag(res.getString("codi_cond_pag"));
				condPag.setNomeCondicao(res.getString("nome_cond_pag"));
				condPag.setIndiceJuros(res.getFloat("indice_juros"));
				condPag.setDescontoAcrescimo(
						res.getFloat("desconto_acrescimo"));
				condPag.setGeraContasReceber(
						res.getString("gera_conta_receber"));
				condPag.setQuantParcelas(res.getInt("quant_parcelas"));
				condPag.setTipoNumeracao(res.getInt("tipo_numeracao"));
				condPag.setCondicaoECF(res.getInt("condicao_ecf"));
				condPag.setSenha(res.getString("senha_cond_pag"));
				condPag.setData_cadastro(res.getTimestamp("data_cadastro"));
				condPag.setData_ultima_alteracao(
						res.getTimestamp("data_ultima_alteracao"));
				arrayCondPag.add(condPag);
			}
			c.desconectar();
			return arrayCondPag;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}

	// TODO Pesquisa nome por código
	public String pesquisaNomeCodigo(String codigo) {
		String sql = "select nome_cond_pag from tbl_cond_pagamento where codi_cond_pag =?;";
		String nome = null;
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, codigo);
			result = prepStm.executeQuery();
			if (result.next()) {
				nome = result.getString("nome_cond_pag");
			}
			c.desconectar();
			return nome;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}

	// TODO Pesquisa codigo por nome
	public String pesquisaCodigoNome(String nome) {
		String sql = "select codi_cond_pag from tbl_cond_pagamento where nome_cond_pag =?;";
		String codigo = null;
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, nome);
			result = prepStm.executeQuery();
			if (result.next()) {
				codigo = result.getString("codi_cond_pag");
			}
			c.desconectar();
			return codigo;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}

	// TODO Pesquisa condição de Pagamento por nome
	public CondPagamento pesquisaCondPagNome(String nome) {
		String sql = "select * from tbl_cond_pagamento where nome_cond_pag =?;";
		String codigo = null;
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, nome);
			result = prepStm.executeQuery();
			if (result.next()) {
				condPag = new CondPagamento();
				condPag.setSeq_condPagamento(result.getInt("seq_cond"));
				condPag.setCodiCondPag(result.getString("codi_cond_pag"));
				condPag.setNomeCondicao(result.getString("nome_cond_pag"));
				condPag.setIndiceJuros(result.getFloat("indice_juros"));
				condPag.setDescontoAcrescimo(
						result.getFloat("desconto_acrescimo"));
				condPag.setGeraContasReceber(
						result.getString("gera_conta_receber"));
				condPag.setQuantParcelas(result.getInt("quant_parcelas"));
				condPag.setTipoNumeracao(result.getInt("tipo_numeracao"));
				condPag.setCondicaoECF(result.getInt("condicao_ecf"));
				condPag.setSenha(result.getString("senha_cond_pag"));
				condPag.setData_cadastro(result.getTimestamp("data_cadastro"));
				condPag.setData_ultima_alteracao(
						result.getTimestamp("data_ultima_alteracao"));
			}
			c.desconectar();
			return condPag;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}

	// TODO Pesquisa condição de Pagamento por código
	public CondPagamento pesquisaCondPagCodigo(String codigo) {
		String sql = "select * from tbl_cond_pagamento where codi_cond_pag =?;";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, codigo);
			result = prepStm.executeQuery();
			if (result.next()) {
				condPag = new CondPagamento();
				condPag.setSeq_condPagamento(result.getInt("seq_cond"));
				condPag.setCodiCondPag(result.getString("codi_cond_pag"));
				condPag.setNomeCondicao(result.getString("nome_cond_pag"));
				condPag.setIndiceJuros(result.getFloat("indice_juros"));
				condPag.setDescontoAcrescimo(
						result.getFloat("desconto_acrescimo"));
				condPag.setGeraContasReceber(
						result.getString("gera_conta_receber"));
				condPag.setQuantParcelas(result.getInt("quant_parcelas"));
				condPag.setTipoNumeracao(result.getInt("tipo_numeracao"));
				condPag.setCondicaoECF(result.getInt("condicao_ecf"));
				condPag.setSenha(result.getString("senha_cond_pag"));
				condPag.setData_cadastro(result.getTimestamp("data_cadastro"));
				condPag.setData_ultima_alteracao(
						result.getTimestamp("data_ultima_alteracao"));
			}
			c.desconectar();
			return condPag;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}

	// TODO Consultar o último
	public int consultaUltimo() {
		c2.conectStm();
		result = c2.query("SELECT max(seq_cond) FROM tbl_cond_pagamento;");
		c2.disconect();
		try {
			result.next();
			return result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}

	// TODO Reserva código
	public void reservaCodigo(String codigo) throws SQLException {
		String sql = "insert into tbl_cond_pagamento (codi_cond_pag) values (?)";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, codigo);
		prepStm.executeUpdate();
		c.desconectar();
	}

	// TODO Remover
	public boolean remover(CondPagamento condPag) {
		String sql = "delete from tbl_cond_pagamento where codi_cond_pag=?;";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, condPag.getCodiCondPag());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return false;
		}
	}

}
