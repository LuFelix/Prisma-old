package online.lucianofelix.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoProxy extends Conexao2 {
	private String local;
	private String user;
	private String senha;
	private Connection con;
	private Statement statment;
	private String str_conexao;
	private String driverjdbc;

	public ConexaoProxy(String bd, String user, String senha) {

		super(bd);

		if (bd.equals("PostgreSql")) {
			setStr_conexao("jdbc:postgresql://" + "localhost" + ":" + "5432"
					+ "/" + "simpro");
			setLocal(local);
			setSenha(senha);
			setUser(user);
			setDriverjdbc("org.postgresql.Driver");

		}
	}
	public boolean validaUsuario() {
		// TODO Aqui vai a validacao Proxy
		if (getUser().equals("postgres") & getSenha().equals("Lu123!@#")) {
			return true;
		}
		return false;
	}
	public boolean conectar() {
		if (validaUsuario()) {
			System.setProperty("jdbc.Drivers", getDriverjdbc());
			try {
				con = DriverManager.getConnection(getStr_conexao(), getUser(),
						getSenha());
				System.out.println("Conectado");
				return true;
			} catch (SQLException e) {
				// TODO Aqui Mensagem de erro de conexão
				e.printStackTrace();
				return false;
			}
		} else {
			// TODO Mensagem de erro de validação do usuário
			System.out.println("Usuário sem permissão");
			return false;
		}
	}

}
