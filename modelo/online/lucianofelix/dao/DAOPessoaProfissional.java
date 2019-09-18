package online.lucianofelix.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.PessoaProfissional;
import online.lucianofelix.util.Conexao;

public class DAOPessoaProfissional {
	PessoaProfissional pPro;
	Conexao c;
	CallableStatement cStm;
	List<PessoaProfissional> listPro;

	public DAOPessoaProfissional() {
		pPro = new PessoaProfissional();
		c = new Conexao(ConfigS.getBdMDB(), ConfigS.getBanco3());

	}

	public List<PessoaProfissional> leFuncaoCodigo(String codiPessoa) {
		c.conectar();
		String sql = "{call lista_funcoes_codi_pess(?)}";
		listPro = new ArrayList<PessoaProfissional>();
		try {
			cStm = c.getCon().prepareCall(sql);
			cStm.setString(1, codiPessoa);
			ResultSet rs = cStm.executeQuery();
			while (rs.next()) {
				pPro = new PessoaProfissional();
				pPro.setSeqPessProf(rs.getInt("seq_func"));
				pPro.setCodiPess(rs.getString("codi_pess"));
				pPro.setCodiProf(rs.getString("codi_prof"));
				pPro.setNomeProf(rs.getString("nome_prof"));
				pPro.setDocFunc(rs.getString("doc_func"));
				pPro.setPis(rs.getInt("pis"));
				pPro.setOptante(rs.getBoolean("optante"));
				listPro.add(pPro);
			}
			c.desconectar();
			return listPro;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}

	}
	public void criarFuncao(PessoaProfissional pp) {
		c.conectar();
		String sql = "{call cadastra_funcao(?,?,?,?,?,?)}";

		try {
			cStm = c.getCon().prepareCall(sql);
			cStm.setString(1, pp.getCodiPess());
			cStm.setString(2, pp.getCodiProf());
			cStm.setString(3, pp.getNomeProf());
			cStm.setString(4, pp.getDocFunc());
			cStm.setInt(5, pp.getPis());
			cStm.setBoolean(6, pp.isOptante());
			cStm.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public void atualizar(PessoaProfissional pp) {
		c.conectar();
		String sql = "{call atualiza_ocupacao(?,?,?,?,?)}";
		try {
			cStm = c.getCon().prepareCall(sql);
			cStm.setString(1, pp.getNomeProf());
			cStm.setString(2, pp.getDocFunc());
			cStm.setInt(3, pp.getPis());
			cStm.setBoolean(4, pp.isOptante());
			cStm.setString(5, pp.getCodiPess());
			cStm.executeQuery();
			c.desconectar();
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
		}

	}
	public void apagar(PessoaProfissional pp) {
		c.conectar();
		String sql = "{call apaga_ocupacao(?,?)}";
		try {
			cStm = c.getCon().prepareCall(sql);
			cStm.setString(1, pp.getCodiPess());
			cStm.setInt(2, pp.getSeqPessProf());
			cStm.execute();
			c.desconectar();
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
		}
	}
}
