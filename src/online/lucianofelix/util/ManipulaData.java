package online.lucianofelix.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ManipulaData {
	SimpleDateFormat sdf;
	Calendar calendario;
	ManipulaData manData;

	public String dataAgora() {
		calendario = new GregorianCalendar();
		Date dataHoje = calendario.getTime();
		String strDataHoje = dataHoje.toString();
		return strDataHoje;
	}

	public ManipulaData() {

		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}

	/**
	 * Entra um timestamp completo e sai formatado para aaaa-mm-dd hh:mm:ss
	 * 
	 * @return
	 */
	public static Timestamp dataTimestamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return Timestamp.valueOf(dateFormat.format(date));
	}

	/**
	 * Entra data java.util e sai data java.sql
	 * 
	 * @param data
	 * @return
	 */
	public java.sql.Date sqlDate(java.util.Date data) {
		java.sql.Date dataSql = new java.sql.Date(data.getTime());
		return dataSql;
	}

	// TODO Dia da semana
	public String diaDaSemana() {
		GregorianCalendar gc = new GregorianCalendar();
		String diaSemana = "";
		int numDiaSemana = gc.get(gc.DAY_OF_WEEK);

		switch (numDiaSemana) {

		case 1:
			diaSemana = "Domingo";
			break;
		case 2:
			diaSemana = "Segunda-Feira";
			break;
		case 3:
			diaSemana = "Terça-Feira";
			break;
		case 4:
			diaSemana = "Quarta-Feira";
			break;
		case 5:
			diaSemana = "Quinta-Feira";
			break;
		case 6:
			diaSemana = "Sexta-Feira";
			break;
		case 7:
			diaSemana = "Sábado";

		}
		return diaSemana;

	}

	public boolean hjdiaUtil() {
		GregorianCalendar gc = new GregorianCalendar();
		int numDS = gc.get(gc.DAY_OF_WEEK);
		if ((numDS == 1) | (numDS == 7)) {
			return false;
		} else {
			return true;
		}

	}

	public boolean validaDiaUtil(Date data) {
		return false;
	}

	/**
	 * Retorna o dia do mês em um inteiro
	 * 
	 * @return int O dia do mes
	 */
	public int diaMes() {
		GregorianCalendar gc = new GregorianCalendar();
		int numDM = gc.get(gc.DAY_OF_MONTH);

		return numDM;

	}

	public int mesAno() {
		GregorianCalendar gc = new GregorianCalendar();
		int numM = gc.get(gc.MONTH) + 1;

		return numM;

	}

	/**
	 * Entra data (dd/mm/aaaa) sai (aaaa/mm/dd)
	 * 
	 * @param entraData
	 * @return String
	 */
	public String inverteData1(String entraData) {
		System.out.println("ManipulaData.inverteData");
		// entraData = JOptionPane.showInputDialog("Data");
		String vetorData[] = entraData.split("/");

		String dia = vetorData[0];
		String mes = vetorData[1];
		String ano = vetorData[2];

		// System.out.println("Ano: " + ano);
		// System.out.println("Máº½s: " + mes);
		// System.out.println("Dia: " + dia);

		// JOptionPane.showMessageDialog(null, ano + "/" + mes + "/" + dia);
		return ano + "/" + mes + "/" + dia;
	}

	/**
	 * entra data (dd-mm-aaaa) sai (aaaa-mm-dd)
	 * 
	 * @param entraData
	 * @return
	 */
	public String inverteData2(String entraData) {
		// entraData = JOptionPane.showInputDialog("Data");
		String vetorData[] = entraData.split("-");

		String dia = vetorData[0];
		String mes = vetorData[1];
		String ano = vetorData[2];

		// System.out.println("Ano: " + ano);
		// System.out.println("Máº½s: " + mes);
		// System.out.println("Dia: " + dia);

		// JOptionPane.showMessageDialog(null, ano + "-" + mes + "-" + dia);
		return ano + "-" + mes + "-" + dia;
	}

	/**
	 * TODO entra data (mm/dd/aaaa) sai (dd/mm/aaaa)
	 * 
	 * @param entraData
	 * @return String dd/mm/aaaa
	 */
	public String inverteData3(String entraData) {
		// entraData = JOptionPane.showInputDialog("Data");
		String vetorData[] = entraData.split("/");
		String mes = vetorData[0];
		String dia = vetorData[1];
		String ano = vetorData[2];
		// System.out.println("Ano: " + ano);
		// System.out.println("Máº½s: " + mes);
		// System.out.println("Dia: " + dia);
		// JOptionPane.showMessageDialog(null, dia + "/" + mes + "/" + ano);
		return dia + "/" + mes + "/" + ano;
	}

	/**
	 * TODO entra data (mm/dd/aaaa) sai (aaaa/mm/dd)
	 * 
	 * @param entraData
	 * @return
	 */
	public String inverteData4(String entraData) {
		// entraData = JOptionPane.showInputDialog("Data");
		String vetorData[] = entraData.split("/");
		String mes = vetorData[0];
		String dia = vetorData[1];
		String ano = vetorData[2];

		// System.out.println("Ano: " + ano);
		// System.out.println("Máº½s: " + mes);
		// System.out.println("Dia: " + dia);
		// JOptionPane.showMessageDialog(null, ano + "/" + mes + "/" + dia);
		// return ano + "/" + mes + "/" + dia;
		return ano + "-" + mes + "-" + dia;
		// return ano + mes + dia;
	}

	/**
	 * Entra data (aaaa-mm-dd) sai (dd-mm)
	 * 
	 * @param entraData
	 * @return
	 */
	public String inverteData5(String entraData) {
		// entraData = JOptionPane.showInputDialog("Data");
		String vetorData[] = entraData.split("-");

		String ano = vetorData[0];
		String dia = vetorData[2];
		String mes = vetorData[1];

		// System.out.println("Ano: " + ano);
		// System.out.println("Máº½s: " + mes);
		// System.out.println("Dia: " + dia);
		// JOptionPane.showMessageDialog(null, ano + "/" + mes + "/" + dia);
		return dia + "/" + mes;
	}

	/**
	 * Entra data (aaaa-mm-dd) sai (dd-mm-aa)
	 * 
	 * @param entraData
	 * @return String
	 */
	public String inverteData6(String entraData) {
		// entraData = JOptionPane.showInputDialog("Data");
		String vetorData[] = entraData.split("-");

		String ano = vetorData[0];
		String dia = vetorData[2];
		String mes = vetorData[1];

		// System.out.println("Ano: " + ano);
		// System.out.println("Máº½s: " + mes);
		// System.out.println("Dia: " + dia);
		// JOptionPane.showMessageDialog(null, ano + "/" + mes + "/" + dia);
		return dia + "-" + mes + "-" + ano;
	}

	/**
	 * Entra data (mm/dd) sai (ddmm)
	 * 
	 * @param entraData
	 * @return String
	 */
	public String inverteData8(String entraData) {
		// entraData = JOptionPane.showInputDialog("Data");
		String vetorData[] = entraData.split("/");
		String mes = vetorData[0];
		String dia = vetorData[1];

		// System.out.println("Ano: " + ano);
		// System.out.println("Máº½s: " + mes);
		// System.out.println("Dia: " + dia);

		// JOptionPane.showMessageDialog(null, ano + "/" + mes + "/" + dia);
		return dia + mes;
	}

	/**
	 * Entra data (dd/mm) sai (mmdd)
	 * 
	 * @param entraData
	 * @return String
	 */
	public String inverteData9(String entraData) {
		// entraData = JOptionPane.showInputDialog("Data");
		String vetorData[] = entraData.split("/");
		String dia = vetorData[0];
		String mes = vetorData[1];

		// System.out.println("Ano: " + ano);
		// System.out.println("Máº½s: " + mes);
		// System.out.println("Dia: " + dia);

		// JOptionPane.showMessageDialog(null, ano + "/" + mes + "/" + dia);
		return mes + dia;
	}

	public String inverteData7(String entraData) {
		// entraData = JOptionPane.showInputDialog("Data");
		String vetorData[] = entraData.split("/");

		String dia = vetorData[0];
		String mes = vetorData[1];
		String ano = vetorData[2];

		// System.out.println("Ano: " + ano);
		// System.out.println("Máº½s: " + mes);
		// System.out.println("Dia: " + dia);

		// JOptionPane.showMessageDialog(null, ano + "/" + mes + "/" + dia);
		return ano + "-" + mes + "-" + dia;
	}

	/**
	 * Separa data da hora retorna hora.
	 * 
	 * @param data
	 * @return
	 */
	public static String horaString(String data) {
		String hora = data.substring(11, 16);
		return hora;
	}

	/**
	 * Separa data da hora retorna dia - mês.
	 * 
	 * @param data
	 * @return String
	 */
	public static String diaMesString(String data) {
		String diaMes = data.substring(0, 10);
		return diaMes;
	}

	/**
	 * Mostra no console a instância de hora
	 */
	public void mostraDataAmPm() {
		Calendar dataHora = Calendar.getInstance();
		System.out.printf("%tc\n", dataHora);
		System.out.printf("%tF\n", dataHora);
		System.out.printf("%tD\n", dataHora);
		System.out.printf("%tr\n", dataHora);
		System.out.printf("%tT\n", dataHora);

	}

	/**
	 * Entra uma String (dd/mm/aaaa) sai um java.util.Date
	 * 
	 * @param entraData
	 * @return
	 * @throws Exception
	 */
	public java.util.Date converteDataDate(String entraData) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataDate = sdf.parse(entraData);
		return dataDate;

	}

	/**
	 * Incompleto Entra uma String (dd/mm/aaaa) sai um java.sql.Date
	 * 
	 * @param entraData
	 * @return
	 * @throws Exception
	 */
	public java.sql.Date converteDataDate2(String entraData) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date dataDateSql = null;
		return dataDateSql;
	}

	/**
	 * Entra uma String (dd/mm/aaaa) sai um Timestamp yyyy-mm-dd hh:mm:ss
	 * 
	 * @param entraData
	 * @return
	 * @throws Exception
	 * 
	 */
	public Timestamp converteDataTimestamp(String entraData) throws Exception {
		System.out.println("Data para converter para timestamp: " + entraData);
		manData = new ManipulaData();
		Timestamp dataTimestamp = Timestamp.valueOf(manData.inverteData7(entraData) + " 00:00:00");
		return dataTimestamp;
	}

	/**
	 * recebe uma string e configura os espaçosem branco preenchendo com zeros
	 * 
	 * @param String
	 *            data
	 * @return String data com todas as casas preenchidas
	 */
	public String setStrData(String data) {
		manData = new ManipulaData();
		if (data.isEmpty()) {
			int dia = manData.diaMes();
			if (dia < 10) {
				data = manData.mesAno() + "0" + String.valueOf(dia);
			} else {
				data = manData.mesAno() + "0" + String.valueOf(dia);
			}
		}
		if (!data.isEmpty()) {
			int dia = Integer.parseInt(data.substring(0, 2));
			int mes = Integer.parseInt(data.substring(2));

			if (dia < 10 & mes < 10) {
				data = "0" + String.valueOf(mes) + "0" + String.valueOf(dia);
			}
			if (dia >= 10 & mes <= 10) {
				data = "0" + String.valueOf(mes) + String.valueOf(dia);
			}
			if (dia < 10 & mes >= 10) {
				data = String.valueOf(mes) + "0" + String.valueOf(dia);
			}
			if (dia >= 10 & mes >= 10) {
				data = String.valueOf(mes) + String.valueOf(dia);

			}

		}
		return data;

	}

	/**
	 * Entra uma String com data e outra com hora
	 * 
	 * @param entraData
	 * @param entraHora
	 * @return Calendar com o valor de hora e data informados
	 */
	public Calendar converteDataCalendar(String entraData, String entraHora) {

		Calendar saiData = Calendar.getInstance();
		int year = 0;
		year = Integer.parseInt(entraData.substring(6));
		int month = 0;
		month = Integer.parseInt(entraData.substring(3, 5));
		int date = 0;
		date = Integer.parseInt(entraData.substring(0, 2));
		int hourOfDay = 0;
		hourOfDay = Integer.parseInt(entraHora.substring(0, 2));
		int minute = 0;
		minute = Integer.parseInt(entraHora.substring(3, 5));
		try {
			saiData.set(year, month, date, hourOfDay, minute);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saiData;
	}

	/**
	 * Coverte de padrão 12Hs para Padrão 24Hs
	 * 
	 * @param horaAux
	 * @return String separada por ":" com nhora minutos e segundos
	 */
	public String converteAMPM(String horaAux) {

		String conteudo[] = horaAux.split(":");
		String hora = conteudo[0];
		String minutos = conteudo[1];
		String ampm = minutos.substring(2, 4);
		minutos = minutos.substring(0, 2);
		if (ampm.equals("pm")) {
			if (hora.equals("1")) {
				hora = "13";
			}
			if (hora.equals("2")) {
				hora = "14";
			}
			if (hora.equals("3")) {
				hora = "15";
			}
			if (hora.equals("4")) {
				hora = "16";
			}
			if (hora.equals("5")) {
				hora = "17";
			}
			if (hora.equals("6")) {
				hora = "18";
			}
			if (hora.equals("7")) {
				hora = "19";
			}
			if (hora.equals("8")) {
				hora = "20";
			}
			if (hora.equals("9")) {
				hora = "21";
			}
			if (hora.equals("10")) {
				hora = "22";
			}
			if (hora.equals("11")) {
				hora = "23";
			}
			if (hora.equals("12")) {
				hora = "00";
			}
		}
		// System.out.println("Hora: " + hora + " Minutos: " + minutos
		// + " AM/PM: " + ampm);
		// return hora + minutos;
		// return hora + "-" + minutos;
		return hora + ":" + minutos + ":00";
	}
}
