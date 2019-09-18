package online.lucianofelix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.CotacaoAtivo;
import online.lucianofelix.util.Conexao;

public class DAOCotacaoAtivo {
	private Conexao c;
	private PreparedStatement prepStm;
	private List<CotacaoAtivo> listCot;
	private List<String> listDataDisp;
	private CotacaoAtivo cot;

	public DAOCotacaoAtivo() {
		System.out.println("DAOCotacaoAtivo.construtor");
		c = new Conexao(ConfigS.getBdPg(), "simpro");
	}

	/**
	 * TODO Inserir nova cotação
	 * 
	 * @param cotacao
	 * @return
	 */
	public boolean inserir(CotacaoAtivo cotacao) {
		String sql = "insert into cotacoes (id_neg, data_cotacao, pre_abe, pre_fec, pre_max, pre_min, volume_neg, quatot_tit, tot_neg) values(?,?,?,?,?,?,?,?,?)";
		java.sql.Date dataSQl = new java.sql.Date(
				cotacao.getDataCotacao().getTime());
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, cotacao.getIdAtivo());
			prepStm.setDate(2, dataSQl);
			prepStm.setFloat(3, cotacao.getPreAbe());
			prepStm.setFloat(4, cotacao.getPreFec());
			prepStm.setFloat(5, cotacao.getPreMax());
			prepStm.setFloat(6, cotacao.getPreMin());
			prepStm.setFloat(7, cotacao.getVolumeNeg());
			prepStm.setLong(8, cotacao.getQuaToTit());
			prepStm.setLong(9, cotacao.getTotNeg());
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
	 * TODO Consulta as cotações do ativo retornado um array ordenado por data
	 * 
	 * @param idAtivo
	 * @return
	 */
	public List<CotacaoAtivo> conCotAtvOrdDtAscend(String idAtivo) {
		String sql = "select * from cotacoes where id_neg = '" + idAtivo
				+ "' order by data_cotacao asc;";
		listCot = new ArrayList<CotacaoAtivo>();
		try {

			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();

			if (res.first()) {
				do {
					cot = new CotacaoAtivo(res.getString("id_neg"),
							res.getTimestamp("data_cotacao"),
							res.getFloat("pre_abe"), res.getFloat("pre_fec"),
							res.getFloat("pre_max"), res.getFloat("pre_min"),
							res.getFloat("volume_neg"),
							res.getLong("quatot_tit"), res.getLong("tot_neg"));
					listCot.add(cot);
				} while (res.next());
				c.desconectar();
			}
			return listCot;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * TODO Consulta as cotações do ativo intradiárias da tabela do yahoo
	 * 
	 * @param idYahoo
	 * @return
	 */
	public List<CotacaoAtivo> consultaTodasCotacoesAtivoTHreadDiario(
			String idYahoo) {
		String conteudo[] = idYahoo.split(".");
		System.out.println(conteudo.length);
		System.out.println("idyahoo = " + idYahoo);
		System.out.println("Depois do split " + conteudo[1]);
		String sql = "select * from cotacoes where id_neg = '" + conteudo[0]
				+ "' order by data_cotacao asc;";
		listCot = new ArrayList<CotacaoAtivo>();
		try {

			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			res.first();
			do {
				cot = new CotacaoAtivo(res.getString("id_neg"),
						res.getTimestamp("data_cotacao"),
						res.getFloat("pre_abe"), res.getFloat("pre_fec"),
						res.getFloat("pre_max"), res.getFloat("pre_min"),
						res.getFloat("volume_neg"), res.getLong("quatot_tit"),
						res.getLong("tot_neg"));
				listCot.add(cot);
			} while (res.next());
			c.desconectar();
			return listCot;

		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * TODO Todas as cotações ordem de data DESCENDENTE *
	 * 
	 * @param idAtivo
	 * 
	 * @return
	 */
	public List<CotacaoAtivo> conCotAtvOrdDtDescend(String idAtivo) {
		String sql = "select * from cotacoes where id_neg = '" + idAtivo
				+ "' order by data_cotacao desc;";
		listCot = new ArrayList<CotacaoAtivo>();
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			res.first();
			do {
				cot = new CotacaoAtivo(res.getString("id_neg"),
						res.getTimestamp("data_cotacao"),
						res.getFloat("pre_abe"), res.getFloat("pre_fec"),
						res.getFloat("pre_max"), res.getFloat("pre_min"),
						res.getFloat("volume_neg"), res.getLong("quatot_tit"),
						res.getLong("tot_neg"));
				listCot.add(cot);
			} while (res.next());
			c.desconectar();
			return listCot;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * TODO Retorna uma lista com as datas disponíveis no sistema
	 * 
	 * @return
	 */
	public List<String> datasDisponiveisDB() {
		String sql = "select distinct data_cotacao from cotacoes order by data_cotacao desc;";
		listDataDisp = new ArrayList<>();
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			if (res.first()) {
				do {
					listDataDisp
							.add(res.getString(String.valueOf("data_cotacao")));
				} while (res.next());
				c.desconectar();
				return listDataDisp;
			}
			return null;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
	}

}
