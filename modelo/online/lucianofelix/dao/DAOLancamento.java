package online.lucianofelix.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	 * Insere um lancamento na tabela de baixas a receber
	 * 
	 * @param lanc
	 * @return
	 */
	public boolean inserirBaixaRec(Lancamento lanc) {

		Timestamp dataHoraMovimento = new Timestamp(
				Calendar.getInstance().getTimeInMillis());

		String sql = "insert into tbl_ctas_baixas_receber (num_titulo, codi_pessoa, valor, data_movimento,tip_doc_vinculado) values (?,?,?,?);";
		// System.out.println(lanc.getValor() + " " + lanc.getCodiCondPag()
		// + " " + dataHoraMovimento + " "
		// + BigDecimal.valueOf(lanc.getValor()));

		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, lanc.getCodiCtaReceber());
			prepStm.setString(2, lanc.getCodiPessoa());
			prepStm.setBigDecimal(3, lanc.getValor());
			prepStm.setTimestamp(4, dataHoraMovimento);
			prepStm.setString(5, lanc.getTipoDocvinculado());
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
	 * Insere uma baixa para o titulo / numeroContaReceber
	 * 
	 * @param codiPessoa
	 * @param numCtaReceber
	 * @param codiPedido
	 * @param valor
	 * @param codiCondiPag
	 * @throws SQLException
	 */
	public void novoBaixaRec(String codiPessoa, String numCtaReceber,
			BigDecimal valor, String codiCondiPag) throws SQLException {
		Timestamp dataHoraMovimento = new Timestamp(
				Calendar.getInstance().getTimeInMillis());
		String sql = "insert into tbl_ctas_baixas_receber (codi_pessoa, num_cta_receber, valor, data_movimento, codi_cond_pag) values (?,?,?,?,?);";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, codiPessoa);
		prepStm.setString(2, numCtaReceber);
		prepStm.setBigDecimal(3, valor);
		prepStm.setTimestamp(4, dataHoraMovimento);
		prepStm.setString(5, codiCondiPag);
		prepStm.executeUpdate();
		c.desconectar();
	}

	/**
	 * Insere um lancamento na tabela de contas a receber na data programada
	 * 
	 * @param lanc
	 * @return
	 */
	public boolean inserirTituRec(Lancamento lanc) {

		Date dataHoraMovimento = new Date(
				Calendar.getInstance().getTimeInMillis());

		String sql = "insert into tbl_ctas_lanc_receber (num_cta_receber, codi_cond_pag, codi_pedido, codi_pessoa, "
				+ "data_hora_registro, valor, obs_lanc, data_hora_vencimento, tipo_lanc, tipo_doc_vinculado) "
				+ "values (?,?,?,?,?,?,?,?,?,?);";
		// System.out.println(lanc.getValor() + " " + lanc.getCodiCondPag()
		// + " " + dataHoraMovimento + " "
		// + BigDecimal.valueOf(lanc.getValor()));

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
			prepStm.setTimestamp(8, lanc.getDtHrVenc());
			prepStm.setString(9, lanc.getTipoLancamento());
			prepStm.setString(10, lanc.getTipoDocvinculado());
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
	 * Insere um lancamento na tabela de contas a receber na data programada
	 * 
	 * @param lanc
	 * @return
	 * @throws SQLException
	 */
	public void inserirTituRec(String codiCtaReceber, String codiCondPag,
			String codiPedido, String codiPessoaCliente, Timestamp dataHoraVenc,
			BigDecimal Valor, String obs, String tipoLanc,
			String tipoDocVinculado, String codiConta) throws SQLException {

		Timestamp dataHoraMovimento = new Timestamp(
				Calendar.getInstance().getTimeInMillis());

		String sql = "insert into tbl_ctas_lanc_receber (codi_cta_Receber,codi_cond_pag, codi_pedido, codi_pessoa, "
				+ "data_hora_registro, valor, obs_lanc, data_hora_vencimento, tipo_lanc, tipo_docu_vinculado, codi_conta,num_titulo) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?);";
		// System.out.println(lanc.getValor() + " " + lanc.getCodiCondPag()
		// + " " + dataHoraMovimento + " "
		// + BigDecimal.valueOf(lanc.getValor()));

		c.conectar();

		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, codiConta);
		prepStm.setString(2, codiCondPag);
		prepStm.setString(3, codiPedido);
		prepStm.setString(4, codiPessoaCliente);
		prepStm.setTimestamp(5, dataHoraMovimento);
		prepStm.setBigDecimal(6, Valor);
		prepStm.setString(7, obs);
		prepStm.setTimestamp(8, dataHoraVenc);
		prepStm.setString(9, tipoLanc);
		prepStm.setString(10, tipoDocVinculado);

		prepStm.execute();
		c.desconectar();

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
	/**
	 * Lista com todos os titulos a receber (contas a receber) por numero da
	 * conta a receber(titulo)
	 * 
	 * @param codigo
	 * @return
	 */
	public List<Lancamento> listCtasReceber(String codiCtaReceber) {
		System.out.println(
				"DaoContaLancamento.consulataMovimentoContaOrdAscendente");
		String sql = "select * from tbl_ctas_lanc_receber where codi_cta_receber = '"
				+ codiCtaReceber + "' order by seq_cta_receber asc;";
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
					lanc.setDtHrLanc(res.getTimestamp("data_hora_lancamento"));
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

	/**
	 * Consulta os titulos (contas a receber) por conta / categoria
	 * 
	 * @param conta
	 * @return
	 */
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
					lanc.setCodiConta(res.getString("num_cta_receber"));
					lanc.setCodiCondPag(res.getString("codi_cond_pag"));
					lanc.setCodiPedido(res.getString("codi_pedido"));
					lanc.setCodiPessoa(res.getString("codi_pessoa"));
					lanc.setDtHrLanc(res.getTimestamp("data_hora_registro"));
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

	/**
	 * Consulta as baixas(pagamentos) por pedido lancamentos do Pedido
	 * 
	 * @param pedi
	 * @return
	 */
	public List<Lancamento> listBaixasPediCompra(Pedido pediCompra) {
		System.out.println("DAOContaMovimento.ConsultaEntradasouSaidas");
		String sql = "select * from tbl_ctas_baixas_receber where num_cta_receber = '"
				+ pediCompra.getCodiPedi() + "' order by seq_baixa asc;";
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
					lanc.setSequencia(res.getInt("seq_baixa"));
					lanc.setCodiPessoa(res.getString("codi_pessoa"));
					lanc.setCodiCtaReceber(res.getString("num_cta_receber"));
					lanc.setCodiPedido("num_cta_receber");
					lanc.setValor(res.getBigDecimal("valor"));
					lanc.setDtHrLanc(res.getTimestamp("data_movimento"));
					lanc.setCodiCondPag(res.getString("codi_cond_pag"));

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
					lanc.setCodiConta(res.getString("num_cta_receber"));
					lanc.setCodiCondPag(res.getString("codi_cond_pag"));
					lanc.setCodiPedido(res.getString("codi_pedido"));
					lanc.setCodiPessoa(res.getString("codi_pessoa"));
					lanc.setDtHrLanc(res.getTimestamp("data_hora_registro"));
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
	public void alterarQuantItemRec(Lancamento lanc) {
		c.conectar();
		String sql = "update tbl_ctas_lanc_receber  set valor =? where num_cta_receber=? and codi_cond_pag=?;";
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setBigDecimal(1, lanc.getValor());
			prepStm.setString(2, lanc.getCodiCtaReceber());
			prepStm.setString(3, lanc.getCodiCondPag());
			prepStm.executeUpdate();
			c.desconectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			c.desconectar();
		}

	}
	/**
	 * Consulta ultima posicao dos lancamentos a receber
	 * 
	 * @return
	 */
	public int consultaUltLancReceber() {
		c.conectar();
		String sql = "Select MAX(seq_cta_receber) as ultimo from tbl_ctas_lanc_receber";
		try {
			ResultSet result = c.getCon().createStatement().executeQuery(sql);
			result.next();
			int numUltimo = result.getInt("ultimo");
			c.desconectar();
			return numUltimo;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return 0;
		}
	}
}