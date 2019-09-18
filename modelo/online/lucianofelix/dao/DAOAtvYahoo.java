/**
 * 
 */
package online.lucianofelix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.AtivoYahoo;
import online.lucianofelix.util.Conexao;

/**
 * @author luciano Classe criada para cadastrar a coluna com, os ids referentes
 *         as cotações do yahoo finance
 */
public class DAOAtvYahoo {
	Conexao c;
	PreparedStatement prepStm;
	AtivoYahoo atvYahoo;
	DAOCotacaoAtivo daoCot;
	List<AtivoYahoo> listAtvYahoo;

	public DAOAtvYahoo() {
		System.out.println("DAOAtivoYahoo.construtor");
		c = new Conexao(ConfigS.getBdPg(), "simpro");
		daoCot = new DAOCotacaoAtivo();
	}

	public List<AtivoYahoo> todosMercadoVistaCompl() {
		listAtvYahoo = new ArrayList<AtivoYahoo>();
		String sql = "Select * From ativo where tp_merc = '10' order by nome_res asc;";
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			res.first();
			while (res.next()) {
				atvYahoo = new AtivoYahoo();
				atvYahoo.setIdYahoo((res.getString("id_neg") + ".SA"));
				atvYahoo.setTpMerc(10);
				atvYahoo.setListCotComp(
						daoCot.consultaTodasCotacoesAtivoTHreadDiario(
								(atvYahoo.getIdYahoo())));
				listAtvYahoo.add(atvYahoo);
			}
			c.desconectar();
			return listAtvYahoo;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
		}
		return null;
	}

	public boolean inserir(AtivoYahoo atv) {
		String sql = "insert into ativo_yahoo (id_yahoo) values(?)";
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, atv.getIdYahoo());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return false;
		}
	}

	public List<AtivoYahoo> listarOrdIdNeg() {
		listAtvYahoo = new ArrayList<AtivoYahoo>();
		String sql = "select * from ativo_yahoo order by id_yahoo asc;";
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			res.first();
			while (res.next()) {
				atvYahoo = new AtivoYahoo();
				atvYahoo.setIdYahoo(res.getString("id_yahoo"));
				listAtvYahoo.add(atvYahoo);
			}
			c.desconectar();
			return listAtvYahoo;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}

}
