package online.lucianofelix.util;
import java.text.DecimalFormat;


public class FormatarNumeros {
	
	
	public float mostrar2decimaisFloat(float num){  
		    
			num = num/100;
		    return num;
		 }  
	public String mostrar2decimais(int numero){  
		  String numString = "";  
		  DecimalFormat formata = new DecimalFormat("0.00");  
		  try{  
			numero = numero/100;
		    numString = formata.format(numero);
		    return numString;
		  }catch(Exception ex){  
		    System.err.println("Erro ao formatar numero: " + ex);  
		    return null;
		  }
		  
	}
		
		  
	
}
