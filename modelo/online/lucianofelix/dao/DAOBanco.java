package online.lucianofelix.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.util.Conexao;

public class DAOBanco {
	List<String> list;
	Conexao c;

	public DAOBanco() {
		c = new Conexao(ConfigS.getBdPg(), "siacecf");
	}

	public List<String> listaTabelas() {
		c.conectar();
		list = new ArrayList<String>();
		String sql = "{call lista_tabelas()}";
		try {

			CallableStatement cStm = c.getCon().prepareCall(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = cStm.executeQuery();

			while (rs.next()) {
				list.add(rs.getString(1));
			}
			c.desconectar();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}

	}
	public List<String> exibeTabela() {
		c.conectar();
		list = new ArrayList<String>();
		String sql = "{call lista_conteudo_tabela()}";
		try {

			CallableStatement cStm = c.getCon().prepareCall(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = cStm.executeQuery();

			while (rs.next()) {
				list.add(rs.getString(1));
			}
			c.desconectar();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}

	}

	public boolean limpaTabela(String nomeTabela) {
		c.conectar();
		String sql = "{call limpa_tabela(?)}";

		boolean executou = false;
		try {
			CallableStatement cStm = c.getCon().prepareCall(sql);
			cStm.setString(1, nomeTabela);
			executou = cStm.execute();
			c.desconectar();
			return executou;
		} catch (SQLException e) {

			e.printStackTrace();
			c.desconectar();
			return executou;
		}

	}

}
