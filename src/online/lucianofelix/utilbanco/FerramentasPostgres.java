package online.lucianofelix.utilbanco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.dao.ConfigS;
import online.lucianofelix.util.Conexao;

/**
 * @author Luciano Felix
 * @since 05 2016 classe que se destina a testar bancos de dados
 * 
 */

public class FerramentasPostgres {
	String comando;
	ResultSet res;
	private Conexao c;
	private PreparedStatement prepStm;
	private List<Banco> listConecBanco;
	Banco banco;

	// Criar um método para executar esse comando: Comando para eliminar todas
	// as conexões ativas SELECT pg_terminate_backend(pid)
	// FROM pg_stat_activity
	// WHERE pid <> pg_backend_pid();
	public List<Banco> listaUsuariosConectados() {
		c = new Conexao(ConfigS.getBdPg(), "postgres");
		comando = "select datname,  pid, usename, application_name, client_addr, client_hostname, backend_start  from pg_stat_activity;";
		listConecBanco = new ArrayList<Banco>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(comando);
			res = prepStm.executeQuery();
			banco = new Banco();
			while (res.next()) {
				banco.setDatname(res.getString("datname"));
				banco.setPid(res.getInt("pid"));
				banco.setUsename(res.getString("usename"));
				banco.setApplication_name(res.getString("application_name"));
				banco.setClient_addr(res.getString("client_addr"));
				banco.setClient_hostname(res.getString("client_hostname"));
				banco.setBackend_start(res.getTimestamp("backend_start"));
				listConecBanco.add(banco);
				c.desconectar();

			}
			for (int i = 0; i < listConecBanco.size(); i++) {
				System.out.println("\n\nDATNAME: "
						+ listConecBanco.get(i).getDatname() + "\nPID: "
						+ listConecBanco.get(i).getPid() + "\nUSENAME: "
						+ listConecBanco.get(i).getUsename() + "\nAPPNAME: "
						+ listConecBanco.get(i).getApplication_name()
						+ "\nBACKEND START: "
						+ listConecBanco.get(i).getBackend_start());
			}

			return listConecBanco;

		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}

	private class Banco {
		public String getDatname() {
			return datname;
		}

		public void setDatname(String datname) {
			this.datname = datname;
		}

		public int getPid() {
			return pid;
		}

		public void setPid(int pid) {
			this.pid = pid;
		}

		public String getUsename() {
			return usename;
		}

		public void setUsename(String usename) {
			this.usename = usename;
		}

		public String getApplication_name() {
			return application_name;
		}

		public void setApplication_name(String application_name) {
			this.application_name = application_name;
		}

		public String getClient_addr() {
			return client_addr;
		}

		public void setClient_addr(String client_addr) {
			this.client_addr = client_addr;
		}

		public String getClient_hostname() {
			return client_hostname;
		}

		public void setClient_hostname(String client_hostname) {
			this.client_hostname = client_hostname;
		}

		public Timestamp getBackend_start() {
			return backend_start;
		}

		public void setBackend_start(Timestamp backend_start) {
			this.backend_start = backend_start;
		}

		// TODO Variáveis.
		int pid;
		String datname;
		String usename;
		String application_name;
		String client_hostname;
		String client_addr;
		Timestamp backend_start;
	}

}
