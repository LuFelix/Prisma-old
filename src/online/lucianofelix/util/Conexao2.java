package online.lucianofelix.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao2 {
	private String local;
	private String user;
	private String senha;
	private Connection con;
	private Statement statment;
	private String str_conexao;
	private String driverjdbc;

	public Conexao2(String bd) {
		if (bd.equals("PostgreSql")) {
			setStr_conexao("jdbc:postgresql://" + "localhost" + ":" + "5432"
					+ "/" + "simpro");
			setLocal(local);
			setSenha("Lu123!@#");
			setUser("postgres");
			setDriverjdbc("org.postgresql.Driver");

		}
	}
	public void configUser(String user, String senha) {
		// TODO Aqui vai o proxy
		setUser(user);
		setSenha(senha);
	}
	public void configLocal(String banco) {
		setLocal(banco);
	}

	public boolean conectar() {

		System.setProperty("jdbc.Drivers", getDriverjdbc());
		try {
			con = DriverManager.getConnection(getStr_conexao(), getUser(),
					getSenha());
			System.out.println("Conectado");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	public boolean desconectar() {
		try {
			getCon().close();
			System.out.println("Desconectado");
			return true;
		} catch (SQLException ex) {
			System.err.println(ex);
			ex.printStackTrace();
			return false;
		}

	}

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

	public Statement getStatment() {
		return statment;
	}

	public void setStatment(Statement statment) {
		this.statment = statment;
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

}
