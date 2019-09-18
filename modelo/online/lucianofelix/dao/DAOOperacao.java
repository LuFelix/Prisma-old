package online.lucianofelix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.Operacao;
import online.lucianofelix.util.Conexao;
import online.lucianofelix.util.ConexaoSTM;

public class DAOOperacao {
	private Conexao c;
	private Operacao op;
	private List<Operacao> listOp;
	private PreparedStatement prepStm;
	private ResultSet result;
	private ConexaoSTM c2;

	/**
	 * Classe de acesso às Operações
	 * 
	 * @author Luciano de O. Felix
	 */

	public DAOOperacao() {
		System.out.println("DAOOperacao.construtor");
		c = new Conexao(ConfigS.getBdPg(), "simpro");
		c2 = new ConexaoSTM(ConfigS.getBdPg(), "siacecf");
	}

	// TODO Alterar - Não há como alterar uma operação realizada.

	// TODO Cadastrar/ Inserir
	public boolean cadastrar(Operacao op) {
		String sql = "insert into operacoes ( id_neg, data_hora_executou,  tipo_op,  codi_operacao, codi_ordem, valor_papel, quant_papeis, corretagem, total) values (?,?,?,?,?,?,?,?,?);";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, op.getCodiAtivo());
			prepStm.setTimestamp(2, op.getDataHoraExec());
			prepStm.setString(3, op.getTipoOp());
			prepStm.setString(4, op.getCodiOperacao());
			prepStm.setString(5, op.getCodiOrdem());
			prepStm.setFloat(6, op.getValorPapel());
			prepStm.setInt(7, op.getQtdPapeis());
			prepStm.setFloat(8, op.getCorretagem());
			prepStm.setFloat(9, op.getTotal());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			c.desconectar();
			return false;
		}
	}

	// TODO Consulta Último
	public int consultaUltimo() {
		c2.conectStm();
		result = c2.query("SELECT max(seq_operacao) FROM operacoes;");
		c2.disconect();
		try {
			result.next();
			return result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}

	// TODO Remover uma operacao
	public boolean excluir(Operacao op) {
		String sql = "delete from operacoes where seq_operacao=?; ";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setInt(1, op.getSeqOperacao());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			c.desconectar();
			return false;
		}

	}

	// TODO Pesquisa String generico pesquisa em varios campos
	public List<Operacao> pesquisaString(String str) {
		String sql = "select * from operacoes where codi_operacao ~*? or id_neg~*? or codi_ordem~*? order by id_neg;";
		listOp = new ArrayList<Operacao>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, str);
			prepStm.setString(2, str);
			prepStm.setString(3, str);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				op = new Operacao();
				op.setSeqOperacao(res.getInt("seq_operacao"));
				op.setCodiAtivo(res.getString("id_neg"));
				op.setDataHoraExec(res.getTimestamp("data_hora_executou"));
				op.setTipoOp(res.getString("tipo_op"));
				op.setCodiOperacao(res.getString("codi_operacao"));
				op.setCodiOrdem(res.getString("codi_ordem"));
				op.setValorPapel(res.getFloat("valor_papel"));
				op.setQtdPapeis(res.getInt("quant_papeis"));
				op.setCorretagem(res.getFloat("corretagem"));
				op.setTotal(res.getFloat("total"));
				listOp.add(op);
			}
			c.desconectar();
			return listOp;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
	}

	// Seleciona todas as operações agrupadas por id_neg e ordenadas por data
	public List<Operacao> pesquisaCodigo(String codigoFuse) {
		String sql = "select seq_operacao, id_neg, data_hora_executou, tipo_op, codi_operacao, codi_ordem, valor_papel, quant_papeis, "
				+ "corretagem, total 	from operacoes where codi_ordem=? 	group by id_neg,seq_operacao,data_hora_executou,tipo_op,codi_operacao,codi_ordem,"
				+ "valor_papel,quant_papeis,corretagem, total order by data_hora_executou;";
		listOp = new ArrayList<Operacao>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, codigoFuse);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				op = new Operacao();
				op.setSeqOperacao(res.getInt("seq_operacao"));
				op.setCodiAtivo(res.getString("id_neg"));
				op.setDataHoraExec(res.getTimestamp("data_hora_executou"));
				op.setTipoOp(res.getString("tipo_op"));
				op.setCodiOperacao(res.getString("codi_operacao"));
				op.setCodiOrdem(res.getString("codi_ordem"));
				op.setValorPapel(res.getFloat("valor_papel"));
				op.setQtdPapeis(res.getInt("quant_papeis"));
				op.setCorretagem(res.getFloat("corretagem"));
				op.setTotal(res.getFloat("total"));
				listOp.add(op);
			}
			c.desconectar();
			return listOp;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
	}

	// TODO Pesquisa por codigo da fuse
	public List<Operacao> pesquisaOpCodiFuse(String codigo) {
		String sql = "select * from operacoes where codi_ordem = ? order by id_neg;";
		listOp = new ArrayList<Operacao>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, codigo);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				op = new Operacao();
				op.setSeqOperacao(res.getInt("seq_operacao"));
				op.setCodiAtivo(res.getString("id_neg"));
				op.setDataHoraExec(res.getTimestamp("data_hora_executou"));
				op.setTipoOp(res.getString("tipo_op"));
				op.setCodiOperacao(res.getString("codi_operacao"));
				op.setCodiOrdem(res.getString("codi_ordem"));
				op.setValorPapel(res.getFloat("valor_papel"));
				op.setQtdPapeis(res.getInt("quant_papeis"));
				op.setCorretagem(res.getFloat("corretagem"));
				op.setTotal(res.getFloat("total"));
				System.out.println("adicionou essa operação para a fuse: "
						+ op.getCodiOrdem());
				listOp.add(op);
			}
			c.desconectar();
			return listOp;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
	}

	// TODO Alterar
	public boolean alterar(Operacao op) {
		String sql = "update operacoes set id_neg=?, tipo_op=?, valor_papel=?, quant_papeis=?,"
				+ " corretagem=? where seq_operacao=?;";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, op.getCodiAtivo());
			prepStm.setString(2, op.getTipoOp());
			prepStm.setFloat(3, op.getValorPapel());
			prepStm.setInt(4, op.getQtdPapeis());
			prepStm.setFloat(5, op.getCorretagem());
			prepStm.setInt(6, op.getSeqOperacao());
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
}
