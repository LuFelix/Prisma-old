package online.lucianofelix.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;


public class LeArquivoTxt {
	
	JFileChooser escolhe;
	File arquivo;
	
	
	public LeArquivoTxt(JTextArea areaTexto) {
		escolhe = new JFileChooser();
		escolhe.showOpenDialog(escolhe);
		arquivo = escolhe.getSelectedFile();
			
		try {
			BufferedReader leArquivo = new BufferedReader(new FileReader(arquivo));
			String lin = "";
			areaTexto.setText(null);
			while ((lin = leArquivo.readLine())!=null){
				areaTexto.append(lin+"\n");
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  	
			leArquivo.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	

}
