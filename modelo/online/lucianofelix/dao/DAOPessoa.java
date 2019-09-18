package online.lucianofelix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.Pessoa;
import online.lucianofelix.beans.PessoaProfissional;
import online.lucianofelix.util.Conexao;
import online.lucianofelix.util.ConexaoSTM;

public abstract class DAOPessoa {
	private Conexao c;
	private ConexaoSTM c2;
	private ResultSet result;
	private PreparedStatement prepStm;
	private Pessoa p;
	private String sql;

	private List<Pessoa> listP;

	public DAOPessoa() {
		System.out.println("DAOUsuario.construtor");
		c = new Conexao(ConfigS.getBdPg(), "siacecf");
		c2 = new ConexaoSTM(ConfigS.getBdPg(), "siacecf");
	}

	public int consultaUltimo() {
		c2.conectStm();
		result = c2.query("SELECT max(seq_pessoa) FROM pessoas;");
		c2.disconect();
		try {
			result.next();
			return result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}

	public void reservaCodigo(String codiPessoa) throws SQLException {
		// TODO Reserva um código livre na tabela de pedidos para cadastrar
		setSql("insert into pessoas (codi_pessoa) values (?)");
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, codiPessoa);
		prepStm.executeUpdate();
		c.desconectar();
	}

	public boolean cadastra(Pessoa p) {
		setSql("insert into pessoas (codi_pessoa, nome_pessoa, email_pessoa, tipo_pessoa, codi_grupo) values (?,?,?,?,?)");
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, p.getCodiPessoa());
			prepStm.setString(2, p.getNome());
			prepStm.setString(3, p.getEmail());
			prepStm.setString(4, p.getTipoPessoa());
			prepStm.setString(5, p.getRelacao());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return false;
		}

	}
	public boolean altera(Pessoa p) {
		System.out.println("DAOUsuario.alterar");
		setSql("update pessoas set nome_pessoa=?, cnpj_cpf_pessoa=?, email_pessoa=?, codi_grupo=? where codi_pessoa =?;");
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, p.getNome());
			prepStm.setString(2, p.getCpf());
			prepStm.setString(3, p.getEmail());
			prepStm.setString(4, p.getRelacao());
			prepStm.setString(5, p.getCodiPessoa());
			prepStm.execute();
			c.desconectar();
			return true;
		} catch (Exception e) {
			c.desconectar();
			e.printStackTrace();
			return false;
		}
	}
	public boolean exclui(Pessoa u) {
		System.out.println("DAOUsuario.excluir");
		setSql("delete from pessoas where codi_pessoa=?;");
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, u.getCodiPessoa());
			prepStm.executeUpdate();
			c.desconectar();
			return true;
		} catch (Exception e) {
			c.desconectar();
			e.printStackTrace();
			return false;
		}
	}

	// Pesquisa por relação (clientes, fornecedores, etc.)
	public List<Pessoa> pesquisaRelacao(String str) {
		System.out.println("DAOUsuario.pesquisarRelacao");
		setSql("select * from pessoas where codi_grupo = ?;");
		listP = new ArrayList<Pessoa>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, str);
			result = prepStm.executeQuery();
			while (result.next()) {
				p = new Pessoa();
				p.setSeqUsuario((result.getInt("seq_pessoa")));
				p.setCodiPessoa(result.getString("codi_pessoa"));
				p.setNome(result.getString("nome_pessoa"));
				p.setCpf(result.getString("cnpj_cpf_pessoa"));
				p.setEmail(result.getString("email_pessoa"));
				p.setRelacao(result.getString("codi_grupo"));
				p.setTipoPessoa(result.getString("tipo_pessoa"));
				listP.add(p);
			}

			c.desconectar();
			return listP;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}
	public Pessoa pessoaCodigo(String codiPessoa) {
		setSql("select * from pessoas where codi_pessoa=?;");
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, codiPessoa);
			result = prepStm.executeQuery();
			if (result.next()) {
				p = new Pessoa();
				p.setSeqUsuario(result.getInt("seq_pessoa"));
				p.setCodiPessoa(result.getString("codi_pessoa"));
				p.setNome(result.getString("nome_pessoa"));
				p.setCpf(result.getString("cnpj_cpf_pessoa"));
				p.setEmail(result.getString("email_pessoa"));
				p.setRelacao(result.getString("codi_grupo"));
				p.setTipoPessoa(result.getString("tipo_pessoa"));
			}
			c.desconectar();
			return p;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}
	public List<Pessoa> listaPessoas() {
		setSql("select * from pessoas order by nome_pessoa;");
		listP = new ArrayList<Pessoa>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			result = prepStm.executeQuery();
			while (result.next()) {
				p = new Pessoa();
				p.setSeqUsuario(result.getInt("seq_pessoa"));
				p.setCodiPessoa(result.getString("codi_pessoa"));
				p.setNome(result.getString("nome_pessoa"));
				p.setCpf(result.getString("cnpj_cpf_pessoa"));
				p.setEmail(result.getString("email_pessoa"));
				p.setRelacao(result.getString("codi_grupo"));
				p.setTipoPessoa(result.getString("tipo_pessoa"));
				listP.add(p);
			}
			c.desconectar();
			return listP;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}

	public List<Pessoa> pesquisaString(String nome) {
		System.out.println("DAOUsuario.pesquisarNome");
		setSql("select * from pessoas where nome_pessoa ~* ? or cnpj_cpf_pessoa ~* ? or email_pessoa ~* ? or codi_pessoa = ? order by nome_pessoa;");
		listP = new ArrayList<Pessoa>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, nome);
			prepStm.setString(2, nome);
			prepStm.setString(3, nome);
			prepStm.setString(4, nome);
			result = prepStm.executeQuery();
			while (result.next()) {
				p = new Pessoa();
				p.setSeqUsuario((result.getInt("seq_pessoa")));
				p.setCodiPessoa(result.getString("codi_pessoa"));
				p.setNome(result.getString("nome_pessoa"));
				p.setCpf(result.getString("cnpj_cpf_pessoa"));
				p.setEmail(result.getString("email_pessoa"));
				p.setRelacao(result.getString("codi_grupo"));
				p.setTipoPessoa(result.getString("tipo_pessoa"));
				listP.add(p);
			}

			c.desconectar();
			return listP;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}

	public List<PessoaProfissional> carregaDetalhesProfissionais(Pessoa p) {

		return null;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}
