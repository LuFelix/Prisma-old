package online.lucianofelix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import online.lucianofelix.beans.Servico;
import online.lucianofelix.util.Conexao;
import online.lucianofelix.util.ConexaoSTM;

public class DAOServico {
	private Conexao c;
	private Servico serv;
	private ArrayList<Servico> arrayServ;
	private PreparedStatement prepStm;
	private ResultSet result;
	private ConexaoSTM c2;

	/**
	 * Classe de acesso a dados do serviço.
	 * 
	 * @author Luciano de O. Felix
	 */
	// TODO Construtor
	public DAOServico() {
		System.out.println("DAOServico.construtor");
		c = new Conexao(ConfigS.getBdPg(), "siacecf");
	}

	// TODO Cadastrar/ Inserir
	public void cadastrar(Servico serv) throws SQLException {
		String sql = "insert into servicos (codi_servico, nome_servico, desc_servico, quant_horas, preco_hora, preco_adicional) values (?,?,?,?,?,?,?);";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, serv.getCodiServico());
		prepStm.setString(2, serv.getNomeServico());
		prepStm.setString(3, serv.getDescServico());
		prepStm.setFloat(3, serv.getTempoResposta());
		prepStm.setFloat(4, serv.getTempoExecucao());
		prepStm.setFloat(5, serv.getDuracao());
		prepStm.setFloat(6, serv.getPrecoHora());
		prepStm.setFloat(7, serv.getPrecoAdicional());
		prepStm.setString(8, serv.getCodiCategoria());
		prepStm.executeUpdate();
		c.desconectar();
	}

	// TODO Alterar
	public void alterar(Servico serv) throws SQLException {
		String sql = "update servicos set nome_servico=?, desc_servico=?, tempo_resposta=?, tempo_execucao=?, duracao=?, preco_hora=?, preco_adicional=?, codi_categoria=? where codi_servico=?;";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, serv.getNomeServico());
		prepStm.setString(2, serv.getDescServico());
		prepStm.setFloat(3, serv.getTempoResposta());
		prepStm.setFloat(4, serv.getTempoExecucao());
		prepStm.setFloat(5, serv.getDuracao());
		prepStm.setFloat(6, serv.getPrecoHora());
		prepStm.setFloat(7, serv.getPrecoAdicional());
		prepStm.setString(8, serv.getCodiCategoria());
		prepStm.setString(9, serv.getCodiServico());
		prepStm.execute();
		c.desconectar();
	}

	// TODO Consulta Último Cadastrado
	public int consultaUltimo() {
		c.conectar();
		String sql = "Select MAX(seq_servico) as ultimo from servicos";
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
	public boolean remover(Servico serv) {
		String sql = "delete from servicos where codi_servico=?; ";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, serv.getCodiServico());
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

	// metodo usa um Statement
	public void removerStmt(Servico serv) {
		c2.conectStm();
		result = c2.query("DELETE  FROM servicos WHERE codi_servico = "
				+ serv.getCodiServico());
		c2.disconect();
	}

	// TODO Reserva um código livre na tabela para cadastrar
	public void reservaCodigo(String codigo) throws SQLException {
		String sql = "insert into servicos (codi_servico) values (?)";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, codigo);
		prepStm.executeUpdate();
		c.desconectar();
	}

	// TODO Pesquisa String método genérico de pesquisa em vários campos
	public ArrayList<Servico> pesquisaString(String str) {
		String sql = "select * from servicos where codi_servico~*? or nome_servico ~* ?  or desc_servico ~* ?  order by nome_servico;";
		arrayServ = new ArrayList<Servico>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, str);
			prepStm.setString(2, str);
			prepStm.setString(3, str);
			result = prepStm.executeQuery();
			while (result.next()) {
				serv = new Servico();
				serv.setSeqServico(result.getInt("seq_servico"));
				serv.setCodiServico(result.getString("codi_servico"));
				serv.setNomeServico(result.getString("nome_servico"));
				serv.setDescServico(result.getString("desc_servico"));
				serv.setTempoResposta(result.getFloat("tempo_resposta"));
				serv.setTempoExecucao(result.getFloat("tempo_execucao"));
				serv.setDuracao(result.getFloat("duracao"));
				serv.setPrecoHora(result.getFloat("preco_hora"));
				serv.setPrecoAdicional(result.getFloat("preco_adicional"));
				serv.setCodiCategoria(result.getString("codi_categoria"));
				arrayServ.add(serv);
			}
			c.desconectar();
			return arrayServ;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}
}
