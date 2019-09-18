package online.lucianofelix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.TabelaPreco;
import online.lucianofelix.util.Conexao;
import online.lucianofelix.util.ConexaoSTM;

public class DAOTabelaPreco {

	private Conexao c;
	private TabelaPreco tabPreco;
	private PreparedStatement prepStm;
	private ResultSet result;
	private ConexaoSTM c2;
	private List<TabelaPreco> listTabPreco;

	public DAOTabelaPreco() {
		System.out.println("DAOTabelaPreco.construtor");
		c = new Conexao(ConfigS.getBdPg(), "siacecf");
		c2 = new ConexaoSTM(ConfigS.getBdPg(), "siacecf");
	}

	// TODO Cadastrar/ Inserir
	public void cadastrar(TabelaPreco tabPreco) throws SQLException {
		String sql = "insert into tabelas_precos ( nome_tabela, data_criacao, data_inicio, data_fim,  tipo_tabela, desc_tabela, codi_fornecedor, codi_loja, classe_tabela, codi_tabela) "
				+ "values (?,?,?,?,?,?,?,?,?,?);";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, tabPreco.getNomeTabela());
		prepStm.setDate(2, tabPreco.getDataCriacao());
		prepStm.setDate(3, tabPreco.getDataInicio());
		prepStm.setDate(4, tabPreco.getDataFim());
		prepStm.setString(5, tabPreco.getTipoTabela());
		prepStm.setString(6, tabPreco.getDescTabela());
		prepStm.setString(7, tabPreco.getCodiFornecedor());
		prepStm.setString(8, tabPreco.getCodiLoja());
		prepStm.setString(9, tabPreco.getClasseTabela());
		prepStm.setString(10, tabPreco.getCodiTabela());
		prepStm.executeUpdate();
		c.desconectar();
	}

	// TODO Alterar
	public void alterar(TabelaPreco tabPreco) throws SQLException {
		String sql = "update tabelas_precos set nome_tabela=?, data_inicio=?,"
				+ " data_fim=?, tipo_tabela=?,  desc_tabela=?, codi_fornecedor=?, "
				+ "codi_loja=?, classe_tabela=? where codi_tabela=?;";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, tabPreco.getNomeTabela());
		prepStm.setDate(2, tabPreco.getDataInicio());
		prepStm.setDate(3, tabPreco.getDataFim());
		prepStm.setString(4, tabPreco.getTipoTabela());
		prepStm.setString(5, tabPreco.getDescTabela());
		prepStm.setString(6, tabPreco.getCodiFornecedor());
		prepStm.setString(7, tabPreco.getCodiLoja());
		prepStm.setString(8, tabPreco.getClasseTabela());
		prepStm.setString(9, tabPreco.getCodiTabela());
		prepStm.executeUpdate();
		c.desconectar();
	}

	// TODO Pesquisa String generico pesquisa em varios campos
	public List<TabelaPreco> pesquisaString(String str) {
		String sql = "select * from tabelas_precos where codi_tabela ~* ?  or nome_tabela ~* ? order by nome_tabela;";
		listTabPreco = new ArrayList<TabelaPreco>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, str);
			prepStm.setString(2, str);
			result = prepStm.executeQuery();
			while (result.next()) {
				tabPreco = new TabelaPreco();
				tabPreco.setNomeTabela(result.getString("nome_tabela"));
				tabPreco.setDataInicio(result.getDate("data_inicio"));
				tabPreco.setDataFim(result.getDate("data_fim"));
				tabPreco.setDataCriacao(result.getDate("data_criacao"));
				tabPreco.setTipoTabela(result.getString("tipo_tabela"));
				tabPreco.setDescTabela(result.getString("desc_tabela"));
				tabPreco.setCodiFornecedor(result.getString("codi_fornecedor"));
				tabPreco.setCodiLoja(result.getString("codi_loja"));
				tabPreco.setCodiTabela(result.getString("codi_tabela"));
				tabPreco.setClasseTabela(result.getString("classe_tabela"));
				listTabPreco.add(tabPreco);
			}
			c.desconectar();
			return listTabPreco;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}

	// TODO Consultar o último
	public int consultaUltimo() {
		c2.conectStm();
		result = c2.query("SELECT max(seq_tab_precos) FROM tabelas_precos;");
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
		String sql = "insert into tabelas_precos (codi_tab_preco) values (?)";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, codigo);
		prepStm.executeUpdate();
		c.desconectar();
	}

	// Pesquisa nome por código
	public String pesquisaNomeCodigo(String str) {
		String sql = "select nome_tabela from tabelas_precos where codi_tabela =?;";
		String nome = null;
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, str);
			result = prepStm.executeQuery();
			if (result.next()) {
				nome = result.getString("nome_tabela");
			}
			c.desconectar();
			return nome;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}

	// TODO Pesquisa codigo por nome;
	public String pesquisaCodigoNome(String str) {
		String sql = "select codi_tabela from tabelas_precos where nome_tabela =?;";
		String codigo = null;
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, str);
			result = prepStm.executeQuery();
			if (result.next()) {
				codigo = result.getString("codi_tabela");
			}
			c.desconectar();
			return codigo;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}

	// TODO Remover
	public void remover(TabelaPreco tabPreco) throws SQLException {
		String sql = "delete from tabelas_precos where codi_tabela=?;";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, tabPreco.getCodiTabela());
		prepStm.executeUpdate();
	}
}
