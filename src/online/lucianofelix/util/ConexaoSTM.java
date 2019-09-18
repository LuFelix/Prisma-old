package online.lucianofelix.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import online.lucianofelix.dao.ConfigS;

public class ConexaoSTM {

	private String local;
	private String user;
	private String senha;
	private Connection con;
	private String str_conexao;
	private String driverjdbc;
	private Statement stmt;
	private Statement statment;

	public ConexaoSTM(String SGBD, String nomeBanco) {

		if (SGBD.equals("PostgreSql")) {
			setLocal(ConfigS.getLocal());
			setSenha(ConfigS.getSenhaPgDB());
			setUser(ConfigS.getUserPgDB());
			setDriverjdbc("org.postgresql.Driver");
			setStr_conexao("jdbc:postgresql://" + local + ":"
					+ ConfigS.getPortaPgDB() + "/" + nomeBanco);

		} else {
			if (SGBD.equals("MySql")) {
				setLocal(ConfigS.getLocal());
				setSenha(ConfigS.getSenhaMDB());
				setUser(ConfigS.getUserMDB());
				setDriverjdbc("com.mysql.jdbc.Driver");
				setStr_conexao("jdbc:mysql://" + local + ":"
						+ ConfigS.getPortaMDB() + "/" + nomeBanco);
			}
		}
	}

	public void configUser(String user, String senha) {
		setUser(user);
		setSenha(senha);
	}

	public void configLocal(String banco) {
		setLocal(banco);
	}

	// Conexão com o Banco de Dados

	public void disconect() {
		try {
			getCon().close();
		} catch (SQLException ex) {
			System.err.println(ex);
			ex.printStackTrace();
		}
	}

	/**
	 * Statement (expressão) passa direto na conexão a instrução/expressão SQL
	 * que será executada através do set Statement onde o método setStatment
	 * executa o comando SQL passado diretamente no momento da conexão.
	 */
	public void conectStm() {
		try {
			Class.forName(getDriverjdbc());
			setCon(DriverManager.getConnection(getStr_conexao(), getUser(),
					getSenha()));
			setStatment(getCon().createStatement());
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	// ++++ Conecta com o banco com resultset rolavel
	public void conectSTMScroll() {
		try {
			Class.forName(getDriverjdbc());
			setCon(DriverManager.getConnection(getStr_conexao(), getUser(),
					getSenha()));
			setStmt(getCon().createStatement());
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	// TODO +++++Métodos que retornam um ResultSet onde foi passada uma conexão
	// que executa um Statement

	public ResultSet query(String query) {
		try {
			return getStatment().executeQuery(query);
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ResultSet queryScroll(String query) {
		try {
			return getStmt().executeQuery(query);
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	// GETs AND SETs

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public String getStr_conexao() {
		return str_conexao;
	}

	public void setStr_conexao(String str_conexao) {
		this.str_conexao = str_conexao;
	}

	public String getDriverjdbc() {
		return driverjdbc;
	}

	public void setDriverjdbc(String driverjdbc) {
		this.driverjdbc = driverjdbc;
	}

	// rs will be scrollable, will not show changes made by others,
	// and will be updatable

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		try {

			this.stmt = getCon().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Statement getStatment() {
		return statment;
	}

	public void setStatment(Statement statment) {
		this.statment = statment;
	}

}
