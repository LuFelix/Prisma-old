package online.lucianofelix.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import online.lucianofelix.beans.CotacaoYahoo;
import online.lucianofelix.util.Conexao;

public class DAOCotacaoYahoo {
	private Conexao c;
	private PreparedStatement prepStm;
	private List<CotacaoYahoo> listCotYahoo;
	private CotacaoYahoo cotYahoo;
	private Statement stmt;
	private HashSet<CotacaoYahoo> hashSetCotYahoo;

	public DAOCotacaoYahoo() {
		System.out.println("DAOCotacaoYahoo.construtor");
		c = new Conexao(ConfigS.getBdPg(), "simpro");
	}

	// "ano-mês-dia horas:minutos:segundos"
	public boolean inserir(CotacaoYahoo cotYahoo) {
		String sql = "insert into meta_ativo_yahoo (id_yahoo, data_hora_cotacao, pre_abe, pre_ult, pre_max, pre_min, volume_neg, variacao)"
				+ " values(?,?,?,?,?,?,?,?)";
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, cotYahoo.getIdYahoo());
			prepStm.setTimestamp(2, cotYahoo.getDataHoraCotacao());
			prepStm.setFloat(3, cotYahoo.getPreAbe());
			prepStm.setFloat(4, cotYahoo.getPreFec());
			prepStm.setFloat(5, cotYahoo.getPreMax());
			prepStm.setFloat(6, cotYahoo.getPreMin());
			prepStm.setFloat(7, cotYahoo.getVolumeNeg());
			prepStm.setFloat(8, cotYahoo.getVariacao());
			prepStm.execute();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return false;
		}
	}

	// metodo retornando mais de um registro popr ativo corrigir o select
	// consultar a Lista ultima cotação retorna ArrayList
	public List<CotacaoYahoo> consultaUltimasCotacoes() {
		String sql = " SELECT * FROM view_ultimas_cotacoes_2;";
		listCotYahoo = new ArrayList<CotacaoYahoo>();
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				cotYahoo = new CotacaoYahoo();
				cotYahoo.setIdYahoo(res.getString("id_yahoo"));
				cotYahoo.setDataHoraCotacao(
						res.getTimestamp("data_hora_cotacao"));
				cotYahoo.setPreAbe(res.getFloat("pre_abe"));
				cotYahoo.setPreFec(res.getFloat("pre_ult"));
				cotYahoo.setPreMax(res.getFloat("pre_max"));
				cotYahoo.setPreMin(res.getFloat("pre_min"));
				cotYahoo.setVolumeNeg(res.getFloat("volume_neg"));
				cotYahoo.setVariacao(res.getFloat("variacao"));
				listCotYahoo.add(cotYahoo);
				// System.out.println("Id_yahoo " + cotYahoo.getIdYahoo() + "
				// Ultimo: valor " + cotYahoo.getPreFec()
				// + " Data hora " + cotYahoo.getDataHoraCotacao() + " Variacao:
				// " + cotYahoo.getVariacao());
			}
			c.desconectar();
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
		return listCotYahoo;

	}

	// TODO Método que chama um stored procedure
	public HashSet<CotacaoYahoo> consUltStoredProcedure() throws SQLException {
		// Desabilitar as transações.
		c.conectar();
		c.getCon().setAutoCommit(false);
		// Chamada do procedimento.
		CallableStatement proc = c.getCon()
				.prepareCall("{ ? = call tabela_ultimas (  ) }");
		proc.registerOutParameter(1, Types.OTHER);
		proc.execute();
		ResultSet results = (ResultSet) proc.getObject(1);
		while (results.next()) {
			System.out.println("E agora??");
		}
		results.close();
		proc.close();
		return hashSetCotYahoo;
	}

	public HashSet<CotacaoYahoo> consTudoStoredProcedure() throws SQLException {
		// Desabilitar as transações.
		c.conectar();
		c.getCon().setAutoCommit(false);
		// Chamada do procedimento.
		CallableStatement proc = c.getCon()
				.prepareCall("{ ? = call cons_tudo }");
		proc.registerOutParameter(1, Types.OTHER);
		proc.execute();
		ResultSet results = (ResultSet) proc.getObject(1);
		while (results.next()) {
			System.out.println("E agora??");
		}
		results.close();
		proc.close();
		return hashSetCotYahoo;
	}

	// nenhum registro retorna, mas funciona no pg admin
	// deve precisar fazer uma function e usar calledstatement
	// consultar a Lista ultima cotação retorna HashSet
	public Set<CotacaoYahoo> consUltCotHashSet() {
		String sql = "select * from view_ultimas_cotacoes_2;";
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			hashSetCotYahoo = new HashSet<CotacaoYahoo>();
			while (res.next()) {
				cotYahoo = new CotacaoYahoo();
				cotYahoo.setIdYahoo(res.getString("id_yahoo"));
				cotYahoo.setDataHoraCotacao(
						res.getTimestamp("data_hora_cotacao"));
				cotYahoo.setPreAbe(res.getFloat("pre_abe"));
				cotYahoo.setPreFec(res.getFloat("pre_ult"));
				cotYahoo.setPreMax(res.getFloat("pre_max"));
				cotYahoo.setPreMin(res.getFloat("pre_min"));
				cotYahoo.setVolumeNeg(res.getFloat("volume_neg"));
				cotYahoo.setVariacao(res.getFloat("variacao"));
				hashSetCotYahoo.add(cotYahoo);
				// System.out.println("Id_Yahoo: " + cotYahoo.getIdYahoo() +
				// " Ultimo: " + cotYahoo.getPreFec()
				// + " Data hora: " + cotYahoo.getDataHoraCotacao() + "
				// Variacao: " + cotYahoo.getVariacao());
			}
			res.close();
			c.desconectar();
			return hashSetCotYahoo;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}

	// TODO consultar a Última cotação por ativo
	public CotacaoYahoo consUltCotAtv(String idYahoo) {
		String sql = "Select * From meta_ativo_yahoo Where id_yahoo='" + idYahoo
				+ "' order by data_hora_cotacao;";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			ResultSet res = prepStm.executeQuery();
			res.next();
			cotYahoo = new CotacaoYahoo();
			cotYahoo.setIdYahoo(res.getString("id_yahoo"));
			cotYahoo.setDataHoraCotacao(res.getTimestamp("data_hora_cotacao"));
			cotYahoo.setPreAbe(res.getFloat("pre_abe"));
			cotYahoo.setPreFec(res.getFloat("pre_ult"));
			cotYahoo.setPreMax(res.getFloat("pre_max"));
			cotYahoo.setPreMin(res.getFloat("pre_min"));
			cotYahoo.setVolumeNeg(res.getFloat("volume_neg"));
			cotYahoo.setVariacao(res.getFloat("variacao"));
			res.close();
			c.desconectar();
			return cotYahoo;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
		}
		return null;
	}

	// TODO zerar tabela de cotações.
	public boolean limpaCotacoesYahoo() {
		String sql = "delete from meta_ativo_yahoo;";
		c.conectar();
		try {
			stmt = c.getCon().createStatement();
			stmt.execute(sql);
			stmt.close();
			c.desconectar();
			System.out.println("Apagado!!");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return false;
		}
	}

	// TODO Consulta as cotações do ativo retornado um array ordenado por data
	public List<CotacaoYahoo> conCotAtvOrdDtAscend(String idYahoo) {
		String sql = "select * from meta_ativo_yahoo where id_Yahoo = '"
				+ idYahoo + "' order by data_hora_cotacao asc;";
		listCotYahoo = new ArrayList<CotacaoYahoo>();
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			res.first();
			do {
				cotYahoo = new CotacaoYahoo();
				cotYahoo.setIdYahoo(res.getString("id_yahoo"));
				cotYahoo.setDataHoraCotacao(
						res.getTimestamp("data_hora_cotacao"));
				cotYahoo.setPreAbe(res.getFloat("pre_abe"));
				cotYahoo.setPreFec(res.getFloat("pre_ult"));
				cotYahoo.setPreMax(res.getFloat("pre_max"));
				cotYahoo.setPreMin(res.getFloat("pre_min"));
				cotYahoo.setVolumeNeg(res.getFloat("volume_neg"));
				cotYahoo.setVariacao(res.getFloat("variacao"));
				listCotYahoo.add(cotYahoo);
			} while (res.next());
			res.close();
			c.desconectar();
			return listCotYahoo;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}

	public List<CotacaoYahoo> conCotAtvOrdDtDescend(String idYahoo) {
		String sql = "select * from meta_ativo_yahoo where id_yahoo = '"
				+ idYahoo + "' order by data_cotacao desc;";
		listCotYahoo = new ArrayList<CotacaoYahoo>();
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			res.first();
			do {
				System.out.println(
						"Cotações para:::" + res.getString("id_yahoo"));
				cotYahoo = new CotacaoYahoo();
				cotYahoo.setIdYahoo(res.getString("id_yahoo"));
				cotYahoo.setDataHoraCotacao(
						res.getTimestamp("data_hora_cotacao"));
				cotYahoo.setPreAbe(res.getFloat("pre_abe"));
				cotYahoo.setPreFec(res.getFloat("pre_ult"));
				cotYahoo.setPreMax(res.getFloat("pre_max"));
				cotYahoo.setPreMin(res.getFloat("pre_min"));
				cotYahoo.setVolumeNeg(res.getFloat("volume_neg"));
				cotYahoo.setVariacao(res.getFloat("variacao"));
				listCotYahoo.add(cotYahoo);
			} while (res.next());
			c.desconectar();
			return listCotYahoo;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}
}
