package online.lucianofelix.zebra;

/**
 * @author Equipe Magica
 * @category Estudos
 * @see Classe para a presentação do trabalho sobre Padrao de Projeto Proxy
 */
public class PadraoProxy {
	/// **
	// * Há um recurso muito utilizado em uma determinada aplicação que deve
	// * ser agora protegido. Problema: Como implementar uma segurança sem
	// * modificar a classe a qual o recurso pertence, evitando assim
	// grandes
	// * modificações de código? Solução: PROXY (Procuración).
	// */
	// // TODO Demonstracao de PROXY
	// // Classe com o recurso desprotegido.
	// System.out.println("Conexão usando a classe desprotegida!!");
	// pausa();
	//
	// Conexao2 con2 = new Conexao2("PostgreSql");
	// con2.conectar();
	//
	// pausa();
	// System.out.println("Desconectando...");
	// pausa();
	//
	// con2.desconectar();
	//
	// /**
	// * Agora uma classe com proxy implementado extendendo a classe a qual
	// se
	// * quer proteger o recurso, porém com a senha errada. A
	// * "assinatura da procuração está inválida" por assim dizer.
	// */
	// pausa();
	// System.out.println("Tentanco conexão com senha errada...");
	// pausa();
	//
	// ConexaoProxy conProxy1 = new ConexaoProxy("PostgreSql", "postgres",
	// "senha");
	// conProxy1.conectar();
	//
	// /**
	// * E por Fim a classe que implementa com a senha correta.
	// */
	// pausa();
	// System.out.println("Tentando conexão com a senha correta");
	// pausa();
	//
	// ConexaoProxy conProxy2 = new ConexaoProxy("PostgreSql", "postgres",
	// "Lu123!@#");
	// conProxy2.conectar();
	// pausa();
	// System.out.println(
	// "Um usuario usando a classe sem validação desconectando...");
	// pausa();
	// // Observando que estamos usando a conexão desprotegida
	// con2.desconectar();
	// pausa();
	// System.out.println("Fim da rotina!!");

	static void pausa() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Alguma mensagem aqui de erro de execução!!
			e.printStackTrace();
		}
	}

}
