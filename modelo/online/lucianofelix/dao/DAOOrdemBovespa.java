package online.lucianofelix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.OrdemBovespa;
import online.lucianofelix.util.Conexao;
import online.lucianofelix.util.ConexaoSTM;

public class DAOOrdemBovespa {
	private Conexao c;
	private OrdemBovespa ord;
	private List<OrdemBovespa> listOrd;
	private PreparedStatement prepStm;
	private ResultSet result;
	private ConexaoSTM c2;

	/**
	 * Classe de acesso às Ordens Bovespa
	 * 
	 * @author Luciano de O. Felix
	 */

	public DAOOrdemBovespa() {
		System.out.println("DAOOrdemBovespa.construtor");
		c = new Conexao(ConfigS.getBdPg(), "simpro");
		c2 = new ConexaoSTM(ConfigS.getBdPg(), "simpro");
	}

	// TODO Cadastrar/ Inserir
	public void cadastrar(OrdemBovespa ord) throws SQLException {
		String sql = "insert into ordens_bovespa ( valor_ordem, codi_fuse, id_neg, validade, tipo_ordem, codi_ordem, executou) values (?,?,?,?,?,?,?);";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setFloat(1, ord.getValorOrdem());
		prepStm.setString(2, ord.getCodiFuse());
		prepStm.setString(3, ord.getCodiAtivo());
		prepStm.setInt(4, ord.getValidade());
		prepStm.setString(5, ord.getTipoOrdem());
		prepStm.setString(6, ord.getCodiOrdem());
		prepStm.setBoolean(7, ord.isExecutou());
		prepStm.executeUpdate();
		c.desconectar();
	}

	// TODO Consulta Último
	public int consultaUltimo() {
		c2.conectStm();
		result = c2.query("SELECT max(seq_ord_bovespa) FROM ordens_bovespa;");
		c2.disconect();
		try {
			result.next();
			return result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}

	// TODO Remover uma ordem
	public void remover(OrdemBovespa ord) throws SQLException {
		String sql = "delete from ordens_bovespa where seq_ord=?; ";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setInt(1, ord.getSeqOrdBovespa());
		prepStm.executeUpdate();
		c.desconectar();
	}

	// TODO Pesquisa String generico pesquisa em varios campos
	public List<OrdemBovespa> pesquisaString(String str) {
		String sql = "select * from ordens_bovespa where codi_ordem ~*? or id_neg~*? order by id_neg;";
		listOrd = new ArrayList<OrdemBovespa>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, str);
			prepStm.setString(2, str);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				ord = new OrdemBovespa();
				ord.setSeqOrdBovespa(res.getInt("seq_ord"));
				ord.setValorOrdem(res.getFloat("valor_ordem"));
				ord.setCodiFuse(res.getString("codi_fuse"));
				ord.setCodiAtivo(res.getString("id_neg"));
				ord.setValidade(res.getInt("validade"));
				ord.setTipoOrdem(res.getString("tipo_ordem"));
				ord.setCodiOrdem(res.getString("codi_ordem"));
				ord.setQtdPapeis(res.getInt("quant_papeis"));
				ord.setExecutou(res.getBoolean("executou"));
				listOrd.add(ord);
			}
			c.desconectar();
			return listOrd;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
	}
}
