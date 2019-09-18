package online.lucianofelix.zebra;

import java.io.*;
import java.sql.*;

// Embora n�o seja comum no c�digo do usu�rio, ser�
// utilizada a API LargeObject neste exemplo.
import org.postgresql.largeobject.*;

/*
 * Este exemplo testa a seguran�a de thread do driver.
 *
 * Isto � feito executando v�rias comandos, em threads diferentes.
 * Cada thread possui o seu pr�prio objeto Statement, o que � (na
 * minha compreens�o da especifica��o do jdbc) o requerimento m�nimo.
 *
 * Foi utilizada a imagem "/usr/share/backgrounds/images/dragonfly.jpg"
 * do Fedora Core 2 para fazer o teste. Deve ser substitu�da por outra
 * caso esta imagem n�o exista no sistema.
 *
 */

public class ThreadSafe
{
    Connection db;      // Conex�o com o servidor de banco de dados
    Statement st;       // Declara��o para executar os comandos
    String strImagem = "D:\\grafico.png";

    public void go() throws ClassNotFoundException, FileNotFoundException,
                            IOException, SQLException
    {
        // Banco de dados, usu�rio e senha igual a "teste"
        // CREATE USER teste WITH password 'teste';
        // CREATE DATABASE teste WITH OWNER teste ENCODING 'LATIN1';
    	
        String url = "jdbc:postgresql://localhost/simpro";
        String usr = "postgres";
        String pwd = "Lu123!@#";

        // Carregar o driver
        Class.forName("org.postgresql.Driver");

        // Conectar com o servidor de banco de dados
        System.out.println("Conectando ao banco de dados\nURL = " + url);
        db = DriverManager.getConnection(url, usr, pwd);

        System.out.println("Conectado...Criando a declara��o");
        st = db.createStatement();

        // Limpar o banco de dados (no caso de uma falha anterior) e inicializar
        cleanup();

        // Como s�o utilizados LargeObjects, devem ser utilizadas Transa��es
        db.setAutoCommit(false);

        // Executar os testes usando os m�todos do JDBC, e depois do LargeObjects
        doexample();

        // Limpar o banco de dados
        cleanup();

        // Fechar a conex�o
        System.out.println("Fechando a conex�o");
        st.close();
        db.close();
    }

    /*
     * Remover a tabela (caso exista). Nenhum erro � mostrado.
     */
    public void cleanup()
    {
        try
        {
            st.executeUpdate("drop table tbl_basica1");
        }
        catch (Exception ex)
        {
            // Ignorar todos os erros
        }

        try
        {
            st.executeUpdate("drop table tbl_basica2");
        }
        catch (Exception ex)
        {
            // Ignorar todos os erros
        }
    }

    /*
     * Executar o exemplo
     */
    public void doexample() throws SQLException
    {
        System.out.println("\n---------------------------------------" +
                           "\nEste teste executa tr�s Threads." +
                           "\nDuas simplesmente inserem dados na" +
                           "\ntabela e depois realizam uma consulta.\n" +
                           "\nEnquanto estas duas threads executam," +
                           "\numa terceira thread executa carregando" +
                           "\ne depois lendo dados de um LargeObject.\n" +
                           "\nSe tudo der certo ser� executado sem" +
                           "\nnenhum erro, e o driver � Thread Safe.\n" +
                           "\nPorque utilizar LargeObject?" +
                           "\nPorque ambos utilizam a conex�o de rede," +
                           "\ne se o bloqueio no fluxo n�o for feito" +
                           "\nde forma correta o servidor ficar�" +
                           "\nconfuso!" +
                           "\n---------------------------------------");

        thread3 thread3 = null;

        try
        {

            // Criar duas threads novas
            Thread thread0 = Thread.currentThread();
            Thread thread1 = new thread1(db);
            Thread thread2 = new thread2(db);
            thread3 = new thread3(db);

            // Executar e aguardar por elas
            thread1.start();
            thread2.start();
            thread3.start();

            // Fazer uma pausa tempor�ria no objeto thread em execu��o,
            // para permitir que as outras threads executem.
            System.out.println("Aguardando a execu��o das threads");
            while (thread1.isAlive() || thread2.isAlive() || thread3.isAlive())
                Thread.yield();
        }
        finally
        {
            // Limpar ap�s thread3 (o finally garante que � executado
            // mesmo ocorrendo uma excess�o dentro do bloco try { })
            if (thread3 != null)
                thread3.cleanup();
        }

        System.out.println("Nenhuma excess�o ocorreu. Isto � um bom sinal,\n" +
                           "porque significa que estamos t�o seguros para\n" +
                           "thread quanto podemos conseguir.");
    }

    // Esta � a primeira thread. � igual ao teste b�sico
    class thread1 extends Thread
    {
        Connection c;
        Statement st;

        public thread1(Connection c) throws SQLException
        {
            this.c = c;
            st = c.createStatement();
        }

        public void run()
        {
            try
            {
                System.out.println("Thread 1 executando...");

                // Criar a tabela que armazena os dados
                st.executeUpdate("create table tbl_basica1 (a int2, b int2)");

                // Inserir alguns dados utilizando Statement
                st.executeUpdate("insert into tbl_basica1 values (1,1)");
                st.executeUpdate("insert into tbl_basica1 values (2,1)");
                st.executeUpdate("insert into tbl_basica1 values (3,1)");

                // Utilizar PreparedStatement para fazer muitas inser��es
                PreparedStatement ps = 
                    db.prepareStatement("insert into tbl_basica1 values (?,?)");
                for (int i = 2; i < 2000; i++)
                {
                    ps.setInt(1, 4);    // "coluna a" = 4
                    ps.setInt(2, i);    // "coluna b" = i
                    ps.executeUpdate(); // porque insert n�o retorna dados
                    if ((i % 50) == 0)
                        DriverManager.println(
                    "Thread 1 executou " + i + " inser��es");
                }
                ps.close();             // Sempre fechar ao terminar

                // Consultar a tabela
                DriverManager.println("Thread 1 realizando a consulta");
                ResultSet rs = st.executeQuery("select a, b from tbl_basica1");
                int cnt = 0;
                if (rs != null)
                {
                    // Percorrer o conjunto de resultados mostrando os valores.
                    // Observe que � necess�rio chamar .next()
                    // antes de ler qualquer resultado.
                    while (rs.next())
                    {
                        int a = rs.getInt("a");   // Nome da coluna
                        int b = rs.getInt(2);     // N�mero da coluna
                        //System.out.println("  a="+a+" b="+b);
                        cnt++;
                    }
                    rs.close(); // � necess�rio fechar ao terminar
                }
                DriverManager.println("Thread 1 leu " + cnt + " linhas");

                // O m�todo cleanup() remove a tabela.
                System.out.println("Thread 1 terminou");
            }
            catch (SQLException se)
            {
                System.err.println("Thread 1: " + se.toString());
                se.printStackTrace();
                System.exit(1);
            }
        }
    }

    // Esta � a segunda thread. � semelhante ao teste b�sico e a thread1,
    // exceto por utilizar outra tabela.
    class thread2 extends Thread
    {
        Connection c;
        Statement st;

        public thread2(Connection c) throws SQLException
        {
            this.c = c;
            st = c.createStatement();
        }

        public void run()
        {
            try
            {
                System.out.println("Thread 2 executando...");

                // Criar a tabela que armazena os dados
                st.executeUpdate("create table tbl_basica2 (a int2, b int2)");

                // Utilizar PreparedStatement para fazer muitas inser��es
                PreparedStatement ps =
                    db.prepareStatement("insert into tbl_basica2 values (?,?)");
                for (int i = 2; i < 2000; i++)
                {
                    ps.setInt(1, 4);    // "coluna a" = 4
                    ps.setInt(2, i);    // "coluna b" = i
                    ps.executeUpdate(); // porque insert n�o retorna dados
                    //      c.commit();
                    if ((i % 50) == 0)
                        DriverManager.println(
                    "Thread 2 executou " + i + " inser��es");
                }
                ps.close();             // Sempre fechar ao terminar

                // Consultar a tabela
                DriverManager.println("Thread 2 realizando a consulta");
                ResultSet rs = st.executeQuery(
            "select * from tbl_basica2 where b>1");
                int cnt = 0;
                if (rs != null)
                {
                    // Descobrir os n�meros das colunas.
                    //
                    // � melhor fazer neste ponto, porque os m�todos chamados
                    // passando os nomes das colunas realizam internamente esta
                    // chamada toda toda vez que s�o chamados. Isto realmente
                    // melhora o desempenho em consultas grandes.
                    //
                    int col_a = rs.findColumn("a");
                    int col_b = rs.findColumn("b");

                    // Percorrer o conjunto de resultados mostrando os valores.
                    // Repetindo, � necess�rio chamar .next()
                    // antes de ler qualquer resultado.
                    while (rs.next())
                    {
                        int a = rs.getInt(col_a); // N�mero da coluna
                        int b = rs.getInt(col_b); // N�mero da coluna
                        //System.out.println("  a="+a+" b="+b);
                        cnt++;
                    }
                    rs.close(); // � necess�rio fechar o resultado ao terminar
                }
                DriverManager.println("Thread 2 leu " + cnt + " linhas");

                // O m�todo cleanup() remove a tabela.
                System.out.println("Thread 2 terminou");
            }
            catch (SQLException se)
            {
                System.err.println("Thread 2: " + se.toString());
                se.printStackTrace();
                System.exit(1);
            }
        }
    }

    // Esta � a terceira thread. Carrega e l� um LargeObject
    // utilizando a API LargeObject do PostgreSQL.
    //
    // A finalidade � testar se FastPath funciona junto com
    // consultas JDBC normais.
    class thread3 extends Thread
    {
        Connection c;
        Statement st;
        LargeObjectManager lom;
        LargeObject lo;
        int oid;

        public thread3(Connection c) throws SQLException
        {
            this.c = c;
            //st = c.createStatement();

            // Criar o BLOB
            lom = ((org.postgresql.PGConnection)c).getLargeObjectAPI();
            oid = lom.create();
            System.out.println("Thread 3 criou o BLOB com oid " + oid);
        }

        public void run()
        {
            try
            {
                System.out.println("Thread 3 executando...");

                DriverManager.println(
            "Thread 3: Carregando dados no BLOB " + oid);
                lo = lom.open(oid);
                FileInputStream fis = new FileInputStream(strImagem);
                // Utilizar um buffer pequeno para dar chance �s outras threads
                byte buf[] = new byte[128];
                int rc, bc = 1, bs = 0;
                while ((rc = fis.read(buf)) > 0)
                {
                    DriverManager.println("Thread 3 leu o bloco " + bc + 
                                           " " + bs + " bytes");
                    lo.write(buf, 0, rc);
                    bc++;
                    bs += rc;
                }
                lo.close();
                fis.close();

                DriverManager.println("Thread 3: Lendo o BLOB " + oid);
                lo = lom.open(oid);
                bc = 0;
                while (buf.length > 0)
                {
                    buf = lo.read(buf.length);
                    if (buf.length > 0)
                    {
                        String s = new String(buf);
                        bc++;
                        DriverManager.println("Thread 3 bloco " + bc);
                        DriverManager.println("Bloco " + bc + " obteve " + s);
                    }
                }
                lo.close();

                System.out.println("Thread 3 terminou");
            }
            catch (Exception se)
            {
                System.err.println("Thread 3: " + se.toString());
                se.printStackTrace();
                System.exit(1);
            }
        }

        public void cleanup() throws SQLException
        {
            if (lom != null && oid != 0)
            {
                System.out.println("Thread 3: Removendo BLOB com oid=" + oid);
                lom.delete(oid);
            }
        }
    }

    public static void main(String args[])
    {
        System.out.println(
        "PostgreSQL - Teste de Seguran�a de Thread v6.4 rev 1\n");
        try
        {
            ThreadSafe threadsafe = new ThreadSafe();
            threadsafe.go();
        }
        catch (Exception ex)
        {
            System.err.println("Exce��o capturada.\n" + ex);
            ex.printStackTrace();
        }
    }
}