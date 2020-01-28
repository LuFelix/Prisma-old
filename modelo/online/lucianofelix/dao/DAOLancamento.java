package online.lucianofelix.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import online.lucianofelix.beans.Conta;
import online.lucianofelix.beans.Lancamento;
import online.lucianofelix.beans.Pedido;
import online.lucianofelix.util.Conexao;

public class DAOLancamento {
	private Conexao c;
	private PreparedStatement prepStm;
	private ResultSet res;
	private List<Lancamento> listMov;
	private Lancamento lanc;

	public DAOLancamento() {
		System.out.println("DAOContaLancamento.construtor");
		c = new Conexao(ConfigS.getBdPg(), "siacecf");
	}

	/**
	 * Insere um lancamento na tabela de contas a receber na data programada
	 * 
	 * @param lanc
	 * @return
	 */
	public boolean inserirLancRec(Lancamento lanc) {

		Date dataHoraMovimento = new Date(
				Calendar.getInstance().getTimeInMillis());

		String sql = "insert into tbl_ctas_lanc_receber ( codi_cond_pag, codi_pedido, codi_pessoa, "
				+ "data_hora_registro, valor, obs_lanc, data_hora_vencimento, tipo_lanc, tipo_docu_vinculado, codi_conta) "
				+ "values (?,?,?,?,?,?,?,?,?,?);";
		// System.out.println(lanc.getValor() + " " + lanc.getCodiCondPag()
		// + " " + dataHoraMovimento + " "
		// + BigDecimal.valueOf(lanc.getValor()));

		c.conectar();

		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, lanc.getCodiCondPag());
			prepStm.setString(2, lanc.getCodiPedido());
			prepStm.setString(3, lanc.getCodiPessoa());
			prepStm.setDate(4, dataHoraMovimento);
			prepStm.setBigDecimal(5, lanc.getValor());
			prepStm.setString(6, lanc.getObsLancamento());
			prepStm.setDate(7, dataHoraMovimento);
			prepStm.setString(8, lanc.getTipoLancamento());
			prepStm.setString(9, lanc.getEspecieLancamento());
			prepStm.setString(10, lanc.getCodiConta());
			prepStm.execute();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}

	}
	/**
	 * Insere um lancamento no caixa (lancamentos recebidos a vista)
	 * 
	 * @param lanc
	 * @return
	 */
	public boolean inserirLancCaixa(Lancamento lanc) {

		Date dataHoraMovimento = new Date(
				Calendar.getInstance().getTimeInMillis());

		String sql = "insert into tbl_ctas_lanc_receber ( codi_conta, codi_cond_pag, codi_pedido, codi_pessoa, "
				+ "data_hora_lancamento, valor, obs_lancamento, data_hora_recebimento, tipo_lanc) values (?,?,?,?,?,?,?,?,?);";
		System.out.println(lanc.getValor() + "   " + lanc.getCodiCondPag()
				+ "   " + dataHoraMovimento + "  " + lanc.getValor());

		c.conectar();

		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, lanc.getCodiConta());
			prepStm.setString(2, lanc.getCodiCondPag());
			prepStm.setString(3, lanc.getCodiPedido());
			prepStm.setString(4, lanc.getCodiPessoa());
			prepStm.setDate(5, dataHoraMovimento);
			prepStm.setBigDecimal(6, lanc.getValor());
			prepStm.setString(7, lanc.getObsLancamento());
			prepStm.setDate(8, lanc.getDataHoraLancamento());
			prepStm.setString(9, lanc.getTipoLancamento());
			prepStm.execute();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}

	}
	public boolean alterarLanRec(Lancamento lanc) {
		System.out.println("DAOLancamento.alterar");
		String sql = "update tbl_ctas_lanc_receber set  codi_conta=?, codi_cond_pag=?, "
				+ "codi_pedido=?, codi_pessoa=?,valor=?,obs_lancamento=?,tipo_lanc=? where seq_conta_lancamento =?;";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, lanc.getCodiConta());
			prepStm.setString(2, lanc.getCodiCondPag());
			prepStm.setString(3, lanc.getCodiPedido());
			prepStm.setString(4, lanc.getCodiPessoa());
			prepStm.setBigDecimal(5, lanc.getValor());
			prepStm.setString(6, lanc.getObsLancamento());
			prepStm.setString(7, lanc.getTipoLancamento());
			prepStm.setInt(8, lanc.getSequencia());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (Exception e) {
			c.desconectar();
			e.printStackTrace();
			return false;
		}
	}
	public void novoLancRec(String codiConta, String codiCondPag,
			String codiPedido, String codiPessoa, Date dataHoraMovimento,
			BigDecimal valor, String obsLanc, Date dataHoraReceb,
			String tipoLanc, String codiContaReceber, String tipoDocVinculado)
			throws SQLException {
		dataHoraMovimento = new Date(Calendar.getInstance().getTimeInMillis());
		String sql = "insert into tbl_ctas_lanc_receber ( codi_conta, codi_cond_pag, codi_pedido, codi_pessoa, "
				+ "data_hora_registro,valor, obs_lanc, data_hora_vencimento, tipo_lanc, codi_cta_receber, tipo_doc_vinculado) values (?,?,?,?,?,?,?,?,?,?,?);";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, codiConta);
		prepStm.setString(2, codiCondPag);
		prepStm.setString(3, codiPedido);
		prepStm.setString(4, codiPessoa);
		prepStm.setDate(5, dataHoraMovimento);
		prepStm.setBigDecimal(6, valor);
		prepStm.setString(7, obsLanc);
		prepStm.setDate(8, dataHoraReceb);
		prepStm.setString(9, tipoLanc);
		prepStm.setString(10, codiContaReceber);
		prepStm.setString(11, tipoDocVinculado);

		prepStm.executeUpdate();
		c.desconectar();
	}

	public List<Lancamento> listCtasReceber(String codigo) {
		System.out.println(
				"DaoContaLancamento.consulataMovimentoContaOrdAscendente");
		String sql = "select * from tbl_ctas_lanc_receber where codi_cta_receber = '"
				+ codigo + "' order by seq_cta_receber asc;";
		listMov = new ArrayList<Lancamento>();
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = prepStm.executeQuery();
			if (res.first()) {
				do {
					lanc = new Lancamento();
					lanc.setSequencia(res.getInt("seq_cta_receber"));
					lanc.setCodiConta(res.getString("codi_cta_receber"));
					lanc.setCodiCondPag(res.getString("codi_cond_pag"));
					lanc.setCodiPedido(res.getString("codi_pedido"));
					lanc.setCodiPessoa(res.getString("codi_pessoa"));
					lanc.setDataHoraLancamento(
							res.getDate("data_hora_lancamento"));
					lanc.setValor(res.getBigDecimal("valor"));
					lanc.setTipoLancamento(
							res.getString("codi_tipo_lancamento"));
					listMov.add(lanc);
				} while (res.next());
			} else {
				lanc = new Lancamento();
				lanc.setCodiConta("Nulo");
				listMov.add(lanc);
			}
			c.desconectar();
			return listMov;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
	}

	// Consulta somente receber por conta / categoria
	public List<Lancamento> listCtasReceber(Conta conta) {
		System.out.println("DAOContaMovimento.listCtasReceber");
		String sql = "select * from tbl_ctas_lanc_receber where codi_conta = '"
				+ conta.getCodiConta() + "' order by seq_cta_receber desc;";
		listMov = new ArrayList<Lancamento>();
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			if (res.first()) {
				do {
					lanc = new Lancamento();
					lanc.setSequencia(res.getInt("seq_cta_receber"));
					lanc.setCodiConta(res.getString("codi_cta_receber"));
					lanc.setCodiCondPag(res.getString("codi_cond_pag"));
					lanc.setCodiPedido(res.getString("codi_pedido"));
					lanc.setCodiPessoa(res.getString("codi_pessoa"));
					lanc.setDataHoraLancamento(
							res.getDate("data_hora_registro"));
					lanc.setValor(res.getBigDecimal("valor"));
					listMov.add(lanc);
				} while (res.next());
			}
			c.desconectar();
			return listMov;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
	}

	// Consulta lancamentos do Pedido
	public List<Lancamento> consultLancPedido(Pedido pedi) {
		System.out.println("DAOContaMovimento.ConsultaEntradasouSaidas");
		String sql = "select * from tbl_ctas_lanc_receber where codi_pedido = '"
				+ pedi.getCodiPedi() + "' order by seq_cta_receber asc;";
		listMov = new ArrayList<Lancamento>();
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = prepStm.executeQuery();
			if (res.first()) {
				do {
					lanc = new Lancamento();
					lanc.setSequencia(res.getInt("seq_cta_receber"));
					lanc.setCodiConta(res.getString("codi_cta_receber"));
					lanc.setCodiCondPag(res.getString("codi_cond_pag"));
					lanc.setCodiPedido(res.getString("codi_pedido"));
					lanc.setCodiPessoa(res.getString("codi_pessoa"));
					lanc.setDataHoraLancamento(
							res.getDate("data_hora_registro"));
					lanc.setValor(res.getBigDecimal("valor"));
					listMov.add(lanc);
				} while (res.next());
			}
			c.desconectar();
			return listMov;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();

			return null;
		}
	}
	// Lista com os últimos lançamentos a receber
	public List<Lancamento> listUltLancRec() {
		String sql = "select * from tbl_ctas_lanc_receber ;";
		listMov = new ArrayList<Lancamento>();
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = prepStm.executeQuery();
			if (res.first()) {
				do {
					lanc = new Lancamento();
					lanc.setSequencia(res.getInt("seq_cta_receber"));
					lanc.setCodiConta(res.getString("codi_cta_receber"));
					lanc.setCodiCondPag(res.getString("codi_cond_pag"));
					lanc.setCodiPedido(res.getString("codi_pedido"));
					lanc.setCodiPessoa(res.getString("codi_pessoa"));
					lanc.setDataHoraLancamento(
							res.getDate("data_hora_registro"));
					lanc.setValor(res.getBigDecimal("valor"));
					lanc.setObsLancamento(res.getString("obs_lanc"));
					lanc.setTipoLancamento(res.getString("tipo_lanc"));
					listMov.add(lanc);
				} while (res.next());
			} else {
				lanc = new Lancamento();
				lanc.setCodiConta("Nulo");
				listMov.add(lanc);
			}
			c.desconectar();
			return listMov;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}
	public boolean excluirLancRec(Lancamento lanc) {
		c.conectar();
		String sql = "delete from tbl_ctas_lanc_receber where codi_pedido=? and codi_cond_pag=?;";
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, lanc.getCodiPedido());
			prepStm.setString(2, lanc.getCodiCondPag());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return false;
		}

	}
	/**
	 * Exclui todos os lancamentos do pedido
	 * 
	 * @param pedi
	 * @return
	 */
	public boolean excluirLancPedido(Pedido pedi) {
		c.conectar();
		String sql = "delete from tbl_ctas_lanc_receber where codi_pedido=? ;";
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, pedi.getCodiPedi());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return false;
		}

	}

	public boolean removerItemLancRec(Pedido pedi, Lancamento lanc) {
		c.conectar();
		String sql = "delete from tbl_ctas_lanc_receber where codi_pedido=? and codi_cond_pag=?;";
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, pedi.getCodiPedi());
			prepStm.setString(2, lanc.getCodiCondPag());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return false;
		}

	}

	public void alterarQuantItemRec(Pedido pedi, Lancamento lanc) {
		c.conectar();
		String sql = "update tbl_ctas_lanc_receber  set valor =? where codi_pedido=? and codi_cond_pag=?;";
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setBigDecimal(1, lanc.getValor());
			prepStm.setString(2, pedi.getCodiPedi());
			prepStm.setString(3, lanc.getCodiCondPag());
			prepStm.executeUpdate();
			c.desconectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			c.desconectar();
		}

	}
}