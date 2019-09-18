package online.lucianofelix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.ItensIndicadores;
import online.lucianofelix.util.Conexao;

/**
 * 
 * @author Luciano Felix indicadores utilizados em cada recomendação e quais
 *         foram as suas configurações
 * 
 */
public class DAOIndicadoresFuse {

	private Conexao c;
	private PreparedStatement prepStm;
	private List<ItensIndicadores> arrayItIndi;
	private ItensIndicadores itIndi;

	public DAOIndicadoresFuse() {
		System.out.println("DAOIndicadoresFuse.construtor");
		c = new Conexao(ConfigS.getBdPg(), "siacecf");
	}

	public void inserirArrayItens(String codiReco,
			ItensIndicadores[] itensIndicador) throws SQLException {
		// TODO Inserir os indicadores utilizados em cada recomendação
		c.conectar();
		for (int i = 0; i < itensIndicador.length; i++) {
			String sql = "insert into indi_fuse (codi_fuse, codi_indi, nome_indi, periodo) values (?,?,?,?)";

			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, codiReco);
			prepStm.setString(2, itensIndicador[i].getCodiFuse());
			prepStm.setString(3, itensIndicador[i].getNomeIndi());
			prepStm.setInt(4, itensIndicador[i].getPeriodo());
			prepStm.executeUpdate();

		}
		c.desconectar();
	}

	// Construir um método para inserir os indicadores um a um

	// public void inserirItem(String codiPedi, ItensProduto item) throws
	// SQLException {
	// // TODO Insere os ítens de cada Pedido um a um
	// c = new Conexao("PostgreSql", "localhost", "5432", "siacecf", "postgres",
	// "Lu123!@#");
	// c.conectar();
	// String sql = "insert into produtos_pedidos (codi_pedido, codi_prod_1,"
	// + " nome_prod, desc_prod, prec_prod, aliq_prod, quant_itens)" + " values
	// (?,?,?,?,?,?,?)";
	// prepStm = c.getCon().prepareStatement(sql);
	// prepStm.setString(1, codiPedi);
	// prepStm.setString(2, item.getCodi_prod_1());
	// prepStm.setString(3, item.getNome_prod());
	// prepStm.setString(4, item.getDesc_prod());
	// prepStm.setFloat(5, item.getPrec_prod_1());
	// prepStm.setString(6, item.getAliq_prod());
	// prepStm.setInt(7, item.getQuantidade());
	// prepStm.executeUpdate();
	//
	// c.desconectar();
	//
	// }
	//
	// construir um metodo para alterar os indicadores de cada recomendacao
	// public void alterarItensPedido(String codiPedi, ItensProduto[]
	// itensProduto) throws SQLException {
	// // TODO Inserir os ítens de cada Pedido
	// c = new Conexao("PostgreSql", "localhost", "5432", "siacecf", "postgres",
	// "Lu123!@#");
	// c.conectar();
	// for (int i = 0; i < itensProduto.length; i++) {
	// String sql = "update produtos_pedidos (codi_pedido, codi_prod_1,"
	// + " nome_prod, desc_prod, prec_prod, aliq_prod, quant_itens)" + " values
	// (?,?,?,?,?,?,?)";
	//
	// prepStm = c.getCon().prepareStatement(sql);
	// prepStm.setString(1, codiPedi);
	// prepStm.setString(2, itensProduto[i].getCodi_prod_1());
	// prepStm.setString(3, itensProduto[i].getNome_prod());
	// prepStm.setString(4, itensProduto[i].getDesc_prod());
	// prepStm.setFloat(5, itensProduto[i].getPrec_prod_1());
	// prepStm.setString(6, itensProduto[i].getAliq_prod());
	// prepStm.setInt(7, itensProduto[i].getQuantidade());
	// prepStm.executeUpdate();
	//
	// }
	// c.desconectar();
	// }

	public List<ItensIndicadores> pesquisaString(String nome) {
		// Busca os indicadores utilizados em cada fuse
		String sql = "select * from indi_fuse where codi_fuse = ?;";
		arrayItIndi = new ArrayList<ItensIndicadores>();
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepStm.setString(1, nome);
			ResultSet res = prepStm.executeQuery();
			while (res.next()) {
				itIndi = new ItensIndicadores();
				itIndi.setCodiFuse(res.getString("codi_fuse"));
				itIndi.setNomeIndi(res.getString("nome_indi"));
				itIndi.setPeriodo(res.getInt("periodo"));
				arrayItIndi.add(itIndi);
			}
			c.desconectar();
			return arrayItIndi;
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
			return null;
		}
	}
}
