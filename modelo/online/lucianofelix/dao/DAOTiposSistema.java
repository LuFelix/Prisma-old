package online.lucianofelix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.TipoSistema;
import online.lucianofelix.util.Conexao;

public class DAOTiposSistema {
	private TipoSistema tipoS;
	private PreparedStatement prepStm;
	Conexao c;
	List<TipoSistema> listTipoS;
	private ResultSet result;

	public DAOTiposSistema() {
		tipoS = new TipoSistema();
		c = new Conexao(ConfigS.getBdPg(), ConfigS.getBanco1());
	}
	public int buscaSeqNome(String nome) {
		String sql = "select seq_tipo_sistema from tipos_sistema where nome_tipo_sistema=? ;";
		System.out.println(nome + "    " + nome);
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, nome);
			result = prepStm.executeQuery();
			result.next();
			int seq = result.getInt("seq_tipo_sistema");
			c.desconectar();
			return seq;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return -1;
		}

	}
	public String buscaNomeSeq(int seq) {
		String sql = "select nome_tipo_sistema from tipos_sistema where seq_tipo_sistema=? ;";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setInt(1, seq);
			result = prepStm.executeQuery();
			String nome = result.getString("nome_tipo_sistema");
			c.desconectar();
			return nome;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}

	}

	public int cadastraTipo(TipoSistema tipoS) throws SQLException {
		String sql = "insert into tbl_tipos_sistema (codi_tipo_sistema, nome_tipo_sistema) values (?,?)";
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, tipoS.getCodiTipoSistema());
		prepStm.setString(1, tipoS.getNomeTipoSistema());
		prepStm.executeUpdate();
		c.desconectar();

		return 0;

	}
	public int consultaUltimo() {
		c.conectar();
		String sql = "Select MAX(seq_tipo_sistema) as ultimo from tbl_tipos_sistema";
		try {
			result = c.getCon().createStatement().executeQuery(sql);
			result.next();
			int numUltimo = result.getInt("ultimo");
			c.desconectar();
			return numUltimo;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return 0;
		}
	}

	// TODO Remover
	public boolean remover(TipoSistema obj) {
		String sql = "delete from tipos_sistema where codi_tipo_sistema=?; ";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, obj.getCodiTipoSistema());
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

	public void reservaCodigo(String codigo) throws SQLException {
		String sql = "insert into tipos_sistema (codi_tipo_sistema) values (?)";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, codigo);
		prepStm.executeUpdate();
		c.desconectar();
	}
	public boolean alterar(TipoSistema obj) {
		System.out.println("DAOGrupoSubgrupo.alterar");
		String sql = "update tipos_sistema set  nome_tipo_sistema=? where seq_tipo_sistema=?;";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, obj.getNomeTipoSistema());
			prepStm.setInt(2, obj.getSeqTipoSistema());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (Exception e) {
			c.desconectar();
			e.printStackTrace();
			return false;
		}
	}

	public List<TipoSistema> pesquisaString(String str) {
		String sql = "select * from tipos_sistema where codi_tipo_sistema~*? or nome_tipo_sistema~* ?   order by seq_tipo_sistema;";
		listTipoS = new ArrayList<TipoSistema>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, str);
			prepStm.setString(2, str);
			result = prepStm.executeQuery();
			while (result.next()) {
				tipoS = new TipoSistema();
				tipoS.setSeqTipoSistema(result.getInt("seq_tipo_sistema"));
				tipoS.setCodiTipoSistema(result.getString("codi_tipo_sistema"));
				tipoS.setNomeTipoSistema(result.getString("nome_tipo_sistema"));
				listTipoS.add(tipoS);
			}
			c.desconectar();
			return listTipoS;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}

}