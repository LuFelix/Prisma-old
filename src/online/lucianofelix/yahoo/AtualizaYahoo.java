package online.lucianofelix.yahoo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.opencsv.CSVReader;

import online.lucianofelix.beans.Ativo;
import online.lucianofelix.beans.AtivoYahoo;
import online.lucianofelix.beans.CotacaoYahoo;
import online.lucianofelix.dao.DAOAtivo;
import online.lucianofelix.dao.DAOAtvYahoo;
import online.lucianofelix.dao.DAOCotacaoYahoo;
import online.lucianofelix.util.BaixarArquivos;
import online.lucianofelix.util.ManipulaData;

public class AtualizaYahoo {

	BaixarArquivos baixar;
	List<String> listLines;
	String dataAux;
	String horaAux;
	String idYahoo;
	Date dataCotacao;
	Time hora;
	Timestamp dataHora;
	float preAbe, preUlt, preMax, preMin, volumeNeg, variacao;
	DAOCotacaoYahoo daoCotYahoo;
	DAOAtvYahoo daoAtvYahoo;
	DAOAtivo daoAtv;
	BufferedReader novaEntrada;
	BufferedReader ultimaEntrada;
	private String urlString;
	private String pathLocalAtual;
	private String pathLocalUlt;
	CotacaoYahoo cotYahoo;

	public AtualizaYahoo() {
		setPathLocalAtual("C:\\SIMPRO\\yahoo\\atual\\");
		setPathLocalUlt("C:\\SIMPRO\\yahoo\\ult\\");
		baixar = new BaixarArquivos();
		daoCotYahoo = new DAOCotacaoYahoo();
		daoAtvYahoo = new DAOAtvYahoo();

	}

	// http://download.finance.yahoo.com/d/quotes.csv?s= VALE5.SA
	// &f=sl1d1t1c1ohgv&e=.csv

	public String getPathLocalAtual() {
		return pathLocalAtual;
	}

	public void setPathLocalAtual(String pathLocalAtual) {
		this.pathLocalAtual = pathLocalAtual;
	}

	public String getPathLocalUlt() {
		return pathLocalUlt;
	}

	public void setPathLocalUlt(String pathLocalUlt) {
		this.pathLocalUlt = pathLocalUlt;
	}

	public boolean atualizaPasta(String idYahoo) throws Exception {
		urlString = "http://download.finance.yahoo.com/d/quotes.csv?s="
				+ idYahoo + "&f=sl1d1t1c1ohgv&e=.csv";
		baixar.gravaArquivoDeURL(urlString, pathLocalAtual, idYahoo);
		System.out.println("Atualizado para: ===>> " + idYahoo);
		return true;
	}
	public void atualizaJASON() throws IOException {

		String baseUrl = "http://query.yahooapis.com/v1/public/yql?q=";
		String query = "select * from upcoming.events where location='San Francisco' and search_text='dance'";
		String fullUrlStr = baseUrl + URLEncoder.encode(query, "UTF-8")
				+ "&format=json";

		URL fullUrl = new URL(fullUrlStr);
		InputStream is = fullUrl.openStream();

		JSONTokener tok = new JSONTokener(is);
		JSONObject result = new JSONObject(tok);
		is.close();
		System.out.println(result.get("name"));
	}

	public void atualizaCSV(String idYahoo) throws Exception {

		urlString = "http://download.finance.yahoo.com/d/quotes.csv?s="
				+ idYahoo + "&f=sl1d1t1c1ohgv&e=.csv";

		URL urlCompleta = new URL(urlString);
		System.out.println(urlCompleta);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(urlCompleta.openStream()));
		CSVReader reader = new CSVReader(in);
		String valores[];
		valores = reader.readNext();
		for (int i = 0; i < valores.length; i++) {
			String string = valores[i];
			System.out.println(string);
		}

	}

	// passa a referencia do nome do arquivo na pasta para interpretacão
	// retornando uma nova cotação
	public CotacaoYahoo interpretaCotacaoYahoo(String idYahoo)
			throws IOException {
		BufferedReader novaEntrada = new BufferedReader(
				new FileReader(pathLocalAtual + idYahoo));
		String linha;
		// Lendo as linhas do arquivo uma a uma
		while ((linha = novaEntrada.readLine()) != null) {
			// converte cada linha em um array separados pelo marcadorn
			// nesse
			// caso CSV ","
			String conteudo[] = linha.split(",");

			// Tratando conteudo[0] idYahoo
			idYahoo = conteudo[0].replace("\"", "").trim();
			// System.out.println("ID Yahoo => " + idYahoo);

			// Tratando conteudo[1] Último Preço
			if (!conteudo[1].equals("N/A")) {
				preUlt = Float.parseFloat(conteudo[1].trim());
				// System.out.println("variável " + preUlt);
				DecimalFormat df = new DecimalFormat("###.##");
				df.setRoundingMode(RoundingMode.UP);
				// System.out.println(df.format(preUlt)); //

			} else
				preUlt = 0;
			// System.out.println("Ultimo => " + preUlt);

			// Tratando conteudo[2] e conteudo[3] DataHora
			if (!conteudo[2].equals("N/A")) {
				dataAux = conteudo[2].replaceAll("\"", "").trim();
				horaAux = conteudo[3].replace("\"", "").trim();
				// System.out.println("Data => " + dataAux);
				ManipulaData manData = new ManipulaData();
				dataAux = manData.inverteData4(dataAux);
				horaAux = manData.converteAMPM(horaAux);
				// System.out.println("Data correta => " + dataAux);
				// System.out.println("Hora correta => " + horaAux);
				// System.out.println("DATA/HORA =>" + dataAux + " " +
				// horaAux);
				dataHora = Timestamp.valueOf(dataAux + " " + horaAux);
				// System.out.println(dataHora);
				// dataCotacao = java.sql.Date.valueOf(dataAux);
				// System.out.println("TIMESTAMP => "
				// + Timestamp.valueOf(dataAux + " " + horaAux));

			}
			// Tratando conteudo[4] variaÃ§Ã£o
			if (!conteudo[4].isEmpty() && !conteudo[4].equals("N/A")) {
				variacao = Float.parseFloat(conteudo[4].trim());

			}
			// System.out.println("Variacao => " + conteudo[4]);

			// Tratando conteudo[5] abertura
			if (!conteudo[5].equals("N/A")) {
				preAbe = Float.parseFloat(conteudo[1].trim());
			} else
				preAbe = 0;
			// System.out.println("Abertura => " + conteudo[5]);

			// Tratando conteudo[6] mÃ¡ximo
			if (!conteudo[6].equals("N/A")) {
				preMax = Float.parseFloat(conteudo[6].trim());
			} else
				preAbe = 0;
			// System.out.println("MÃ¡ximo => " + conteudo[6]);

			// Tratando conteudo[7] mÃ­nimo
			if (!conteudo[7].equals("N/A")) {
				preMax = Float.parseFloat(conteudo[7].trim());
			} else
				preMin = 0;
			// System.out.println("Mínimo => " + conteudo[7]);

			// Tratando conteudo[8] Volume
			if (!conteudo[8].equals("N/A")) {
				volumeNeg = Float.parseFloat(conteudo[8].trim());
			} else
				volumeNeg = 0;
			// System.out.println("Volume => " + conteudo[8]);
			// System.out.println();
			// System.out.println("Interpretado para: ===>> " + idYahoo);
			// System.out.println();
			// System.out
			// .println("++++++++++++++++++++++++++++++++++++++++++++++");
			// System.out.println();

		}
		cotYahoo = new CotacaoYahoo();
		cotYahoo.setIdYahoo(idYahoo);
		cotYahoo.setDataHoraCotacao(dataHora);
		cotYahoo.setPreAbe(preAbe);
		cotYahoo.setPreFec(preUlt);
		cotYahoo.setPreMax(preMax);
		cotYahoo.setPreMin(preMin);
		cotYahoo.setVolumeNeg(volumeNeg);
		cotYahoo.setVariacao(variacao);
		// IO.delete(pathLocalAtual + idYahoo);
		return cotYahoo;
	}

	public boolean verificaUltCotacao(AtivoYahoo atvYahoo) {

		return false;

	}

	public void atualizarListaAtivos() {
		List<Ativo> listAtv = daoAtv.procurarTodosOrdIdNeg();
		AtivoYahoo atvYahoo;
		for (int i = 0; i < listAtv.size(); i++) {
			atvYahoo = new AtivoYahoo();
			atvYahoo.setIdYahoo(listAtv.get(i).getIdNeg() + ".SA");
			daoAtvYahoo.inserir(atvYahoo);
		}

	}

}
