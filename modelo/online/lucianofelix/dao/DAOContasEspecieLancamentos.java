package online.lucianofelix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.EspecieLancamento;
import online.lucianofelix.util.Conexao;

public class DAOContasEspecieLancamentos {

	private Conexao c;
	private PreparedStatement prepStm;
	private ResultSet res;
	private List<EspecieLancamento> listEspLanc;
	private EspecieLancamento espLanc;

	public DAOContasEspecieLancamentos() {
		c = new Conexao(ConfigS.getBdPg(), "siacecf");
	}

	public void cadastrar(EspecieLancamento espLanc) throws SQLException {
		System.out.println("DaoTipoLancamento.cadastrar");
		String sql = "insert into contas_lancamentos ( codi_tipo_lancamento, nome_tipo_lancamento, desc_tipo_lancamento) "
				+ "values (?,?,?,?);";
		c.conectar();
		prepStm = c.getCon().prepareStatement(sql);
		prepStm.setString(1, espLanc.getCodigo());
		prepStm.setString(2, espLanc.getNome());
		prepStm.setString(3, espLanc.getDesccricao());
		c.desconectar();
	}

	public List<EspecieLancamento> listaEspecieLancamento(String codigo) {
		System.out.println(
				"DaoTipoLancamento.consultaMovimentoContaOrdAscendente");
		String sql = "select * from contas_especie_lancamentos where codi_especie_lancamento = '"
				+ codigo + "' order by seq_especie_lancamento asc;";
		listEspLanc = new ArrayList<EspecieLancamento>();
		try {
			c.conectar();
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = prepStm.executeQuery();
			if (res.first()) {
				do {
					espLanc = new EspecieLancamento();
					espLanc.setSequencia(res.getInt("seq_especie_lancamento"));
					espLanc.setCodigo(res.getString("codi_espcie_lancamento"));
					espLanc.setNome(res.getString("nome_especie_lancamento"));
					espLanc.setDesccricao(
							res.getString("desc_especie_lancamento"));

					listEspLanc.add(espLanc);
				} while (res.next());
			} else {
				espLanc = new EspecieLancamento();
				espLanc.setCodigo("Nulo");
				listEspLanc.add(espLanc);
			}
			c.desconectar();
			return listEspLanc;
		} catch (SQLException e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}
	}
}
