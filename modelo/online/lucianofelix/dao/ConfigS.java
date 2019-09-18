package online.lucianofelix.dao;

public class ConfigS {

	private static String bdPg = "PostgreSql";

	public static String getBdPg() {
		return bdPg;
	}
	public static void setBdPg(String bdPg) {
		ConfigS.bdPg = bdPg;
	}
	public static String getBdMDB() {
		return bdMDB;
	}
	public static void setBdMDB(String bdMDB) {
		ConfigS.bdMDB = bdMDB;
	}
	public static String getLocal() {
		return local;
	}
	public static void setLocal(String local) {
		ConfigS.local = local;
	}
	public static String getPortaPgDB() {
		return portaPgDB;
	}
	public static void setPortaPgDB(String portaPgDB) {
		ConfigS.portaPgDB = portaPgDB;
	}
	public static String getPortaMDB() {
		return portaMDB;
	}
	public static void setPortaMDB(String portaMDB) {
		ConfigS.portaMDB = portaMDB;
	}
	public static String getBanco1() {
		return banco1;
	}
	public static void setBanco1(String banco1) {
		ConfigS.banco1 = banco1;
	}
	public static String getBanco2() {
		return banco2;
	}
	public static void setBanco2(String banco2) {
		ConfigS.banco2 = banco2;
	}
	public static String getBanco3() {
		return banco3;
	}
	public static void setBanco3(String banco3) {
		ConfigS.banco3 = banco3;
	}
	public static String getUserPgDB() {
		return userPgDB;
	}
	public static void setUserPgDB(String userPgDB) {
		ConfigS.userPgDB = userPgDB;
	}
	public static String getSenhaPgDB() {
		return senhaPgDB;
	}
	public static void setSenhaPgDB(String senhaPgDB) {
		ConfigS.senhaPgDB = senhaPgDB;
	}
	public static String getUserMDB() {
		return userMDB;
	}
	public static void setUserMDB(String userMDB) {
		ConfigS.userMDB = userMDB;
	}
	public static String getSenhaMDB() {
		return senhaMDB;
	}
	public static void setSenhaMDB(String senhaMDB) {
		ConfigS.senhaMDB = senhaMDB;
	}
	private static String bdMDB = "MariaDB";
	private static String local = "localhost";
	private static String portaPgDB = "5432";
	private static String portaMDB = "3306";
	private static String banco1 = "siacecf";
	private static String banco2 = "simpro";
	private static String banco3 = "contab";
	private static String userPgDB = "postgres";
	private static String senhaPgDB = "Lu123!@#";
	private static String userMDB = "root";
	private static String senhaMDB = "tec2005";

}
