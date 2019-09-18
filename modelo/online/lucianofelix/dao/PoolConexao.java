package online.lucianofelix.dao;
// package br.com.simprovendas.dao;
//
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.IOException;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.Properties;
//
// import org.apache.commons.dbcp2.BasicDataSource;
//
// public class PoolConexao {
//
// /**
// * Arquivo criado em: 23/09/2010
// *
// * @author Laudelino Martins Cardoso Neto
// */
// public class PoolConnectionDB {
//
// private Properties props;
//
// private BasicDataSource dataSource;
//
// public PoolConnectionDB() {
// // Carrega as configs do bd a partir de arquivos
// this.carregarConfiguracoesBd();
// this.configurarDataSourceRAC();// Configura o BasicDataSource
// }
//
// /**
// * Método que carrega os dados de acesso ao banco a partir de um arquivo
// * properties
// */
// private void carregarConfiguracoesBd() {
// try {
// String caminhoBanco = ArquivosUtil.getCaminhoAplicacao() +
// "/conexao.properties";
// File file = new File(caminhoBanco);
// FileInputStream fis = null;
// this.props = new Properties();
// fis = new FileInputStream(file);
// props.load(fis);
// } catch (IOException ex) {
// System.out.println(ex.getMessage());
// ex.printStackTrace();
// }
// }
//
// /**
// * Método que configura um BaseDataSource com uma conexão ativa (pode
// * ser aumentado)
// */
// private void configurarDataSourceRAC() {
// this.dataSource = new BasicDataSource();
// this.dataSource.setDriverClassName(props.getProperty("nome_driver"));
// this.dataSource.setUsername(props.getProperty("usuario"));
// this.dataSource.setPassword(props.getProperty("senha"));
// this.dataSource.setUrl(props.getProperty("url_conexao"));
// // Seta o pool para apenas uma conexão ativa
// this.dataSource.setInitialSize(1);
// // Obriga o pool a ter pelo menos uma conexão sempre ativa
// this.dataSource.setMinIdle(1);
// // Habilita o teste da conexão antes de retorná-la
// this.dataSource.setTestOnReturn(true);
// // Query que serve para atestar conectividade (para oracle)
// this.dataSource.setValidationQuery("SELECT sysdate FROM dual");
// }
//
// /**
// * Método que retorna uma conexão do BasicDataSource. Nesse caso ele
// * verifica se existe uma conexão pronta, senão existir verifica se o
// * número de conexões está abaixo do que foi estabelecido, se estiver
// * cria a conexão e devolve.
// *
// * @return Uma conexão do pool
// */
// public Connection getConexaoRAC() {
//
// try {
// return dataSource.getConnection();
// } catch (SQLException sqle) {
// sqle.printStackTrace();
// ArquivosUtil.gravarLog(DiversosUtil.getMensagemExcecao(sqle),
// ArquivosUtil.LOG_TIPO_ERRO);
// } catch (Exception e) {
// e.printStackTrace();
// ArquivosUtil.gravarLog(DiversosUtil.getMensagemExcecao(e),
// ArquivosUtil.LOG_TIPO_ERRO);
// }
//
// return null;
//
// }
//
// /**
// * Mostra informações do DataSource
// */
// public void showStatusDataSource() {
// System.out.println("Conexões ativas: " + dataSource.getNumActive());
// System.out.println("Conexões inativas: " + dataSource.getNumIdle());
// }
//
// /**
// * Fecha tudo o que foi usado pelos DAO's incluindo a conexão. Nesse
// * caso a conexão não é fechada de fato como no JDCB puro, e sim
// * devolvida ao pool de conexão (BasicDataSource)
// *
// * @param rs
// * ResultSet
// * @param ps
// * PreparedStatement
// * @param conn
// * Connection (oriunda do BasicDataSource, mas pode ser a do
// * JDBC também)
// */
// public static void liberarRecursosBD(ResultSet rs, PreparedStatement ps,
// Connection conn) {
// try {
// if (ps != null)
// ps.close();
// if (rs != null)
// rs.close();
// if (conn != null)
// conn.close();
// } catch (SQLException sqle) {
// sqle.printStackTrace();
// ArquivosUtil.gravarLog(DiversosUtil.getMensagemExcecao(sqle),
// ArquivosUtil.LOG_TIPO_ERRO);
// } catch (Exception e) {
// e.printStackTrace();
// ArquivosUtil.gravarLog(DiversosUtil.getMensagemExcecao(e),
// ArquivosUtil.LOG_TIPO_ERRO);
// }
//
// }
//
// }// Fim da classe PoolConnectionDB
//
// }
