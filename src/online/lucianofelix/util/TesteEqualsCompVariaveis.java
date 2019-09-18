package online.lucianofelix.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import online.lucianofelix.beans.CotacaoYahoo;
import online.lucianofelix.dao.DAOCotacaoYahoo;
import online.lucianofelix.yahoo.AtualizaYahoo;

/**
 * 
 * @author luciano de Oliveira Felix
 *
 * @version Classe criada para sanar a dúvida sobre comparação de objetos
 *          usando o método .equals e o operador ==. O presente estudo levou a
 *          sobrescrever o metodo.equals da classe CotaçaoYahoo equals -->
 *          Comparações dentro do Heap da JVM as características do objeto ==
 *          --> Comparações fora do Heap da JVM se a referência do objeto
 *          aponta para o mesmo obeto comparado obs.: esse estudo vale para
 *          objetos e não para tipos primitivos; O maior resultado desse estudo
 *          é que deve-se usar o gerador de código automático do eclipse para
 *          gerar os métodos equals e hashcode sendo dessa forma mais eficiente
 *          e segura a comparação.
 */
public class TesteEqualsCompVariaveis {

	DAOCotacaoYahoo daoCotYahoo;
	AtualizaYahoo atuYahoo;
	CotacaoYahoo cot1;
	CotacaoYahoo cot2;
	CotacaoYahoo cot3;
	CotacaoYahoo cot4;
	CotacaoYahoo cot5;
	List<CotacaoYahoo> listUltCotacoes;

	public TesteEqualsCompVariaveis() {
		super();
		daoCotYahoo = new DAOCotacaoYahoo();
		atuYahoo = new AtualizaYahoo();
	}

	public void testaArrayList() {

		cot1 = daoCotYahoo.consUltCotAtv("ABCB10.SA");
		cot2 = daoCotYahoo.consUltCotAtv("ABCB4F.SA");
		cot3 = cot1;

		try {
			atuYahoo.atualizaPasta("ABCB10.SA");
			cot4 = atuYahoo.interpretaCotacaoYahoo("ABCB10.SA");
		} catch (Exception e) {
			e.printStackTrace();
		}

		cot5 = daoCotYahoo.consUltCotAtv("ABCB10.SA");

		System.out.println("Cot 1.equals cot1 " + cot1.equals(cot1));
		System.out.println("Cot 1.equals cot2 " + cot1.equals(cot2));
		System.out.println("Cot 3.equals cot1 " + cot3.equals(cot1));
		System.out.println("Cot 4.equals cot1 " + cot4.equals(cot1));
		System.out.println("Cot 5.equals cot1 " + cot5.equals(cot1));
		System.out.println("Cot 5.equals cot4 " + cot5.equals(cot4));

		listUltCotacoes = new ArrayList<CotacaoYahoo>();
		listUltCotacoes = daoCotYahoo.consultaUltimasCotacoes();
		System.out.println(
				"Tamanho da lista de últimos: " + listUltCotacoes.size());
		System.out.println(listUltCotacoes.contains(cot2));
		System.out.println(listUltCotacoes.contains(cot1));

		for (int i = 0; i < listUltCotacoes.size(); i++) {
			cot1 = listUltCotacoes.get(i);
			if (cot1.equals(cot2)) {
				System.out.println("Iguais .equals");
			}
			if (cot1 == cot2) {
				System.out.println("Iguais ==");
			} else {
				System.out.println("Diferentes");
			}
		}
	}

	// teste da interface hashset
	/**
	 * Para implementar o hashCode() é necessário prestar atenção nas regras
	 * de sobrescrição para que se escreva uma sintaxe válida. De acordo com
	 * a documentação do Java, esta é a sobrescrição correta do método:
	 * que deve estar na clase a qual se quer utilizar os algorítmos de
	 * comparaação:
	 * 
	 * public int hashCode() { // Agoritimo de criação de hashing aqui. } no
	 * nosso caso esse algorítmo foi sobrescrito na classe CotacaoYahoo.
	 *
	 */

	public void testaHashMap() {

		cot1 = daoCotYahoo.consUltCotAtv("ABCB10.SA");
		cot2 = daoCotYahoo.consUltCotAtv("ABCB4F.SA");

		HashMap<CotacaoYahoo, String> hashMapCotacao = new HashMap<>();

		// Inserção dos congressistas no HashMap
		hashMapCotacao.put(cot1,
				" Consigo acesso à que estão no hash Cotação 1");
		hashMapCotacao.put(cot2,
				" Consigo acesso à que estão no hash Cotação 2");

		/*
		 * Criaremos uma terceira cotação e iremos efetuar uma busca para ver
		 * se o algoritimo está, de fato, funcionando. Não será encontrado o
		 * objeto porque Primeiro: não o inserimos no HashMap Segundo: o
		 * tamanho do nome não bate com nenhum tamanho de nome inserido.
		 */

		// objeto recebe a mesma carga
		cot3 = daoCotYahoo.consUltCotAtv("ABCB10.SA");

		System.out.println("cot3 .equals cot1? " + cot3.equals(cot1));// True
		System.out.println();
		System.out.println("Contem cot3? hash.containsValue "
				+ hashMapCotacao.containsValue(cot3)); // False
		System.out.println();
		System.out.println("Contem cot3? hash.containsKey "
				+ hashMapCotacao.containsKey(cot3)); // False
		System.out.println();
		/*
		 * Aqui iremos procurar por uma cotacao que coincida com o valor (o
		 * valor de resultado do algoritmo de hash criado na classe
		 * CotacaoYahoo) de alguma que ja esteja no hash, mas - muito importante
		 * - não bate com o nome da cotação que está lá. Por isto é
		 * importante a implementação do método equals()
		 */

		cot4 = daoCotYahoo.consUltCotAtv("ABCP11.SA");
		System.out.println("Contem cot4? " + hashMapCotacao.containsKey(cot4)); // Falso!
		System.out.println();

		/*
		 * Podemos pesquisar por um objeto que já existe e exibir suas info.
		 */

		// String info = hash.get(cot1); // informação adicionada no Mapa
		// System.out.println(info);

		// info = hash.get(cot2);
		// System.out.println(info);

		/**
		 * Podemos pesquisar por um String específica que já existe e exibir
		 * recuperar o objeto. A ordem de parametros implica na mudança da
		 * busca nesse caso posso achar a cotação pela String de nome que foi
		 * adicionada para usar o codigo a seguir inverte- se a linha a seguir
		 * dessa maneira HashMap<CotacaoYahoo, String> hash = new HashMap<>();
		 * para HashMap<String,CotacaoYahoo> hash = new HashMap<>();
		 */
		// cot5 = hash.get(" Consigo acesso à que estão no hash Cotação
		// 1");//
		// String idYahoo = cot5.getIdYahoo();
		// System.out.println("Busca pela chave bem sucedida? " + idYahoo);

	}

	public void testaHashSet() {

		cot1 = daoCotYahoo.consUltCotAtv("ABCB10.SA");
		cot2 = daoCotYahoo.consUltCotAtv("ABCB4F.SA");

		HashSet<CotacaoYahoo> hashSetCotacao = new HashSet<CotacaoYahoo>();

		hashSetCotacao.add(cot1);
		hashSetCotacao.add(cot2);

		cot3 = daoCotYahoo.consUltCotAtv("ABCB10.SA");
		System.out.println("Comparator cot1 cot3 " + cot1.compareTo(cot3));
		System.out.println();
		System.out.println("cot3 .equals cot1? " + cot3.equals(cot1));// True
		System.out.println();
		System.out.println(
				"Contem cot3? hash.contains " + hashSetCotacao.contains(cot3)); // False
		System.out.println();
		System.out.println(
				"Contem cot1? hash.contains " + hashSetCotacao.contains(cot1)); // False
		System.out.println();

	}
}
