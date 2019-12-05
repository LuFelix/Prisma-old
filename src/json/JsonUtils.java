package json;
import java.io.BufferedReader;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtils {

	// TODO Auto-generated constructor stub

	JSONObject js = null;
	JSONArray ja = null;

	public JsonUtils() {
		try {

			// dependendo da biblioteca da pra otimizar essa parte
			// *************************
			java.io.FileReader fr = new java.io.FileReader("Competidores.json");
			java.io.BufferedReader br = new BufferedReader(fr);
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb = sb.append(line);
			}

			br.close();
			fr.close();
			// ********************************

			// dentro do seu json ele começa com uma Array entao você trata ela
			// primeiramente
			// **************************************
			ja = new JSONArray(sb.toString());

			for (int x = 0; x < ja.length(); x++) {
				JSONObject ob = ja.getJSONObject(x);
				String id = ob.getString("id");
				String nome = ob.getString("nome");
				String apelido = ob.getString("mostraApelido");
				String estado = ob.getString("estado");
				String cidade = ob.getString("cidade");

				System.out.println("id = " + id + "\n" + "nome = " + nome + "\n"
						+ "apelido = " + apelido + "\n" + "estado =  " + estado
						+ "\n" + "cidade = " + cidade + "\n");

				System.out.println("endereco");
				// outra array
				JSONArray aends = ob.getJSONArray("endereco");
				for (int z = 0; z < aends.length(); z++) {
					String s = aends.getString(z);
					System.out.println(" " + s);
				}

				System.out.println("contatos");
				// outra array
				JSONArray acontatos = ob.getJSONArray("contatos");
				for (int z = 0; z < acontatos.length(); z++) {
					String s = acontatos.getString(z);
					System.out.println(" " + s);
				}

				System.out.println(
						"*****************************************************\n");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		JsonUtils rj = new JsonUtils();

	}
}