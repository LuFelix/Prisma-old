package online.lucianofelix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.Ativo;
import online.lucianofelix.util.Conexao;;

public class DAOAtivo {
	Conexao c;
	PreparedStatement prepStm;
	Ativo atv;
	DAOCotacaoAtivo daoCot;
	List<Ativo> listAtv;

	public DAOAtivo() {
		System.out.println("DAOAtivo.construtor");
		c = new Conexao(ConfigS.getBdPg(), "simpro");
	}

	public boolean inserir(Ativo atv) {
		String sql = "insert into ativo (nome_res,id_neg,tp_merc) values(?,?,?)";
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, atv.getNomeRes());
			prepStm.setString(2, atv.getIdNeg());
			prepStm.setInt(3, atv.getTpMerc());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return false;
		}
	}

	// TODO Pesquisa todos do mercado a vista "10"
	public List<Ativo> todosMercadoVista() {
		listAtv = new ArrayList<Ativo>();
		String sql = "Select * From ativo where tp_merc = '10' order by nome_res asc;";

		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			res.first();
			while (res.next()) {
				atv = new Ativo();
				atv.setNomeRes(res.getString("nome_res"));
				atv.setTpMerc(res.getInt("tp_merc"));
				atv.setIdNeg(res.getString("id_neg"));
				listAtv.add(atv);
			}
			c.desconectar();
			return listAtv;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}

	// TODO Saber se a tabela está vazia
	public boolean tabelaVazia() {
		c.conectar();
		String sql = "Select COUNT(*) as total From ativo";
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
			c.desconectar();
			return false;
		}

	}

	// TODO Pesquisa os ativos sem cotações por ordem de nome ascendente
	public List<Ativo> procurarTodosOrdNomAsc() {
		listAtv = new ArrayList<Ativo>();
		String sql = "Select * From ativo order by nome_res asc;";
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			res.first();
			while (res.next()) {
				atv = new Ativo();
				atv.setNomeRes(res.getString("nome_res"));
				atv.setTpMerc(res.getInt("tp_merc"));
				atv.setIdNeg(res.getString("id_neg"));
				listAtv.add(atv);
			}
			c.desconectar();
			return listAtv;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}

	// metodo testado
	// retorna um array com os ativos sem cotações por ordem de id_neg
	// ascendente
	public List<Ativo> procurarTodosOrdIdNeg() {
		listAtv = new ArrayList<Ativo>();
		String sql = "Select * From ativo order by id_neg asc;";
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			res.first();
			while (res.next()) {
				atv = new Ativo();
				atv.setNomeRes(res.getString("nome_res"));
				atv.setTpMerc(res.getInt("tp_merc"));
				atv.setIdNeg(res.getString("id_neg"));
				listAtv.add(atv);
			}
			c.desconectar();
			return listAtv;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}

	// TODO MÃ©todos Que usam um de pesquisa generico em vÃ¡rios campos
	// MÃ©todo testado
	// retorna um array com campos id_neg ou nome_res com a string enviada
	public ArrayList<Ativo> pesquisaString(String str) {
		// essa Ã© a segunda coluna or codiprod ~*
		String sql = "select * from ativo where id_neg ~* ?  or nome_res ~* ? order by nome_res;";
		listAtv = new ArrayList<Ativo>();
		daoCot = new DAOCotacaoAtivo();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, str);
			prepStm.setString(2, str);
			// prepStm.setString(3, nome);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				atv = new Ativo();
				atv.setIdNeg(res.getString("id_neg"));
				atv.setNomeRes(res.getString("nome_res"));
				// atv.setListCot(daoCot.conCotAtvOrdDtDescend(atv.getIdNeg()));
				// prod.setAliquotaICMS(res.getString("aliqprod"));
				// prod.setprecoProd(res.getFloat("precprod"));
				// prod.setQuantidadeProdutos(res.getInt("quanprod"));
				listAtv.add(atv);
			}
			c.desconectar();
			return (ArrayList<Ativo>) listAtv;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}

	// retorna uma lista com todos os ativos com todas as cotações em ordem
	// descente de data
	// mÃ©todo lento
	public List<Ativo> procurarTodosLisCot() {
		listAtv = new ArrayList<Ativo>();
		daoCot = new DAOCotacaoAtivo();
		String sql = "Select * From ativo order by nome_res asc;";
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			res.first();
			while (res.next()) {
				atv = new Ativo();
				atv.setNomeRes(res.getString("nome_res"));
				atv.setTpMerc(res.getInt("tp_merc"));
				atv.setIdNeg(res.getString("id_neg"));
				System.out.println("Cotações para :: " + atv.getIdNeg());
				atv.setListCot(daoCot.conCotAtvOrdDtDescend(atv.getIdNeg()));
				listAtv.add(atv);
			}
			c.desconectar();
			return listAtv;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}

	// TEstado
	// retorna um ativo com sua respectiva lista de cotações
	public Ativo procurarIdneg(String idNeg) {
		daoCot = new DAOCotacaoAtivo();
		String sql = "select * from ativo where id_neg = ? order by id_neg;";
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, idNeg);
			ResultSet res = prepStm.executeQuery();
			atv = new Ativo();
			res.next();
			atv.setNomeRes(res.getString("nome_res"));
			atv.setTpMerc(res.getInt("tp_merc"));
			atv.setIdNeg(res.getString("id_neg"));
			atv.setListCot(daoCot.conCotAtvOrdDtDescend(idNeg));
			c.desconectar();
			return atv;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}
}
