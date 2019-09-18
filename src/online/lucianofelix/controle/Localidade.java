package online.lucianofelix.controle;

import java.util.ArrayList;

public class Localidade {

	ArrayList <String> loc;	

	public ArrayList <String> localidade(){
		loc = new ArrayList<>();
		loc.add("Selecione");
		loc.add("Acre");
		loc.add("Alagoas");
		loc.add("Paraíba");
		loc.add("Bahia");
		loc.add("Pernambuco");
		loc.add("Ceará");
		loc.add("Maranhão");
		return loc;
		
		
		
	}
}
