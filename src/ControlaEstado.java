import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ControlaEstado {

	private ArrayList <Estado> arrayEstado;
	
	public ControlaEstado(){
		arrayEstado = new ArrayList<Estado>();
		
	} 
	
	public ArrayList<Estado> cadastrar(Estado e){
		arrayEstado.add(e);
		return arrayEstado;
	}
	public ArrayList<Estado> listar(){
		
		return arrayEstado;
		}
	}
