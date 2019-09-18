package online.lucianofelix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.Fuse;
import online.lucianofelix.util.Conexao;

public class DAOFuse {

	private Conexao c;
	private Fuse fuse;
	private List<Fuse> arrayFuse;
	private PreparedStatement prepStm;
	private ResultSet result;
	private DAOOperacao daoOpe;
	private DAOIndicadoresFuse daoItenIndi;
	List<String> ativosFuse;

	public DAOFuse() {
		System.out.println("DAOFuse.construtor");
		c = new Conexao(ConfigS.getBdPg(), "simpro");
	}

	public boolean cadastrar(Fuse fuse) {
		String sql = "insert into fuses ( codi_fuse, id_neg, obs_fuse,  data_inicio, data_fim, data_exec_stop, prec_start_venda,"
				+ " prec_ord_venda, prec_start_compra, prec_ord_compra,lucro_prejuizo, exec_entrada, exec_saida, tipo_fuse) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, fuse.getCodiFuse());
			prepStm.setString(2, fuse.getCodiAtivo());
			prepStm.setString(3, fuse.getObsFuse());
			prepStm.setTimestamp(4, fuse.getDataInicio());
			prepStm.setTimestamp(5, fuse.getDataFim());
			prepStm.setTimestamp(6, fuse.getDataExecStop());
			prepStm.setFloat(7, fuse.getPrecStartVenda());
			prepStm.setFloat(8, fuse.getPrecOrdVenda());
			prepStm.setFloat(9, fuse.getPrecStartCompra());
			prepStm.setFloat(10, fuse.getPrecOrdCompra());
			prepStm.setFloat(11, fuse.getLucroPrejuizo());
			prepStm.setBoolean(12, fuse.getExecEntrada());
			prepStm.setBoolean(13, fuse.getExecSaida());
			prepStm.setString(14, fuse.getTipoFuse());
			prepStm.setFloat(14, fuse.getTotal());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return false;
		}

	}

	public Fuse procurar(int codiFuse) {
		return null;
	}

	public Fuse procurarAnterior(int codiFuse) {
		return null;
	}

	public Fuse procurar(String nomeFuse) {
		return null;
	}

	// TODO Pesquisa Geral
	public List<Fuse> pesquisarNome(String nome) {
		daoOpe = new DAOOperacao();
		daoItenIndi = new DAOIndicadoresFuse();
		String sql = "select * from fuses where codi_fuse ~* ? or obs_fuse ~* ? or id_neg ~* ? order by id_neg;";
		arrayFuse = new ArrayList<Fuse>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, nome);
			prepStm.setString(2, nome);
			prepStm.setString(3, nome);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				fuse = new Fuse();
				fuse.setSeqFuse(res.getInt("seq_fuse"));
				fuse.setCodiFuse(res.getString("codi_fuse"));
				fuse.setCodiAtivo(res.getString("id_neg"));
				fuse.setPrecStartCompra(res.getFloat("prec_start_compra"));
				fuse.setPrecOrdCompra(res.getFloat("prec_ord_compra"));
				fuse.setPrecStartVenda(res.getFloat("prec_start_venda"));
				fuse.setPrecOrdVenda(res.getFloat("prec_ord_venda"));
				fuse.setQuant(res.getInt("quant_tot_papeis"));
				// fuse.setListOperVinc(daoOpe.pesquisaCodigo(fuse.getCodiFuse()));
				fuse.setDataInicio(res.getTimestamp("data_inicio"));
				fuse.setDataFim(res.getTimestamp("data_fim"));
				// fuse.setListIndiUtilizados(daoItenIndi.pesquisaString(fuse.getCodiFuse()));
				fuse.setLucroPrejuizo(res.getFloat("lucro_prejuizo"));
				fuse.setTipoFuse(res.getString("tipo_fuse"));
				fuse.setTotal(res.getFloat("total_fuse"));
				arrayFuse.add(fuse);
			}
			c.desconectar();
			return arrayFuse;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
	}

	public List<String> pesquisarAtivoComFuse() {
		String sql = "select distinct a.id_neg from ativo a, fuses f where  a.id_neg = f.id_neg;";
		ativosFuse = new ArrayList<String>();
		String id_neg = null;
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				id_neg = res.getString("id_neg");
				ativosFuse.add(id_neg);
			}
			c.desconectar();
			return ativosFuse;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}

	public List<Fuse> procurarTodos() {
		daoOpe = new DAOOperacao();
		daoItenIndi = new DAOIndicadoresFuse();
		String sql = "select * from fuses order by codi_fuse; ";
		arrayFuse = new ArrayList<Fuse>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				fuse = new Fuse();
				fuse.setSeqFuse(res.getInt("seq_fuse"));
				fuse.setCodiFuse(res.getString("codi_fuse"));
				fuse.setCodiAtivo(res.getString("id_neg"));
				fuse.setPrecStartCompra(res.getFloat("prec_start_compra"));
				fuse.setPrecOrdCompra(res.getFloat("prec_ord_compra"));
				fuse.setPrecStartVenda(res.getFloat("prec_start_venda"));
				fuse.setPrecOrdVenda(res.getFloat("prec_ord_venda"));
				fuse.setQuant(res.getInt("quant_tot_papeis"));
				fuse.setDataInicio(res.getTimestamp("data_inicio"));
				fuse.setDataFim(res.getTimestamp("data_fim"));
				fuse.setLucroPrejuizo(res.getFloat("lucro_prejuizo"));
				fuse.setTipoFuse(res.getString("tipo_fuse"));
				fuse.setTotal(res.getFloat("total_fuse"));
				arrayFuse.add(fuse);
			}
			c.desconectar();
			return arrayFuse;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
	}

	public boolean tabelaVazia() {
		// TODO Verificar se a tabela está vazia
		c.conectar();
		String sql = "Select COUNT(*) as total from fuses";
		try {
			ResultSet result = c.getCon().createStatement().executeQuery(sql);
			if (result.getRow() == 0) {
				c.desconectar();
				return true;
			} else {
				c.desconectar();
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		c.desconectar();
		return false;
	}

	// TODO Remove um fuse
	public boolean excluir(Fuse fuse) {
		String sql = "delete from fuses where codi_fuse=?; ";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, fuse.getCodiFuse());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (Exception e) {
			c.desconectar();
			e.printStackTrace();
			return false;
		}
	}

	public int consultaUltimo() {
		c.conectar();
		String sql = "Select MAX(seq_fuse) as ultimo from fuses";
		try {
			ResultSet result = c.getCon().createStatement().executeQuery(sql);
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

	public void reservaCodigo(String codiFuse) throws SQLException {
		String sql = "insert into fuses (codi_fuse) values (?)";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, codiFuse);
		prepStm.executeUpdate();
		c.desconectar();
	}

	public boolean alterar(Fuse fuse) {
		String sql = "update fuses set  id_neg=?, obs_fuse=?, data_inicio=?, data_fim=?,data_exec_stop=?,  prec_start_venda=?, prec_ord_venda=?,"
				+ " prec_start_compra=?, prec_ord_compra=?, lucro_prejuizo=?, quant_tot_papeis=?, tipo_fuse=?, total_fuse=?  where codi_fuse=?;";
		daoOpe = new DAOOperacao();
		daoItenIndi = new DAOIndicadoresFuse();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, fuse.getCodiAtivo());
			prepStm.setString(2, fuse.getObsFuse());
			prepStm.setTimestamp(3, fuse.getDataInicio());
			prepStm.setTimestamp(4, fuse.getDataFim());
			prepStm.setTimestamp(5, fuse.getDataExecStop());
			prepStm.setFloat(6, fuse.getPrecStartVenda());
			prepStm.setFloat(7, fuse.getPrecOrdVenda());
			prepStm.setFloat(8, fuse.getPrecStartCompra());
			prepStm.setFloat(9, fuse.getPrecOrdCompra());
			prepStm.setFloat(10, fuse.getLucroPrejuizo());
			prepStm.setInt(11, fuse.getQuant());
			prepStm.setString(12, fuse.getTipoFuse());
			prepStm.setFloat(13, fuse.getTotal());
			prepStm.setString(14, fuse.getCodiFuse());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return false;
		}

	}

	public void acionaEntrada() {
		// TODO Criar método para acionar hipoteticamente uma entrada em um
		// fuse.
	}

	public void acionaSaida() {
		// TODO Criar método para acionar hipoteticamente uma saida em um
		// fuse.
	}

	public ResultSet getResult() {
		return result;
	}

	public void setResult(ResultSet result) {
		this.result = result;
	}

}
