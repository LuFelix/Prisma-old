package online.lucianofelix.util;
import java.awt.Desktop;  
import java.io.IOException;  
import java.net.URI;  
import java.net.URISyntaxException;  

import javax.swing.JOptionPane;


public class DisparaBrowserPadrao {
	
	
	  
	
	    public static void main(String[] args) {  
	        
	    	Desktop desktop = null;     
	        //Primeiro verificamos se é possível a integração com o desktop     
	        if (!Desktop.isDesktopSupported())     
	            throw new IllegalStateException("Desktop resources not supported!");     
	            
	        desktop = Desktop.getDesktop();     
	        //Agora vemos se é possível disparar o browser default.     
	        if (!desktop.isSupported(Desktop.Action.BROWSE))     
	            throw new IllegalStateException("Não há browser PAdrão!");     
	            
	        //Pega a URI de um componente de texto.
	        String urlBdi = JOptionPane.showInputDialog("Digite a URL");
	        URI uri = null;  
	        try {  
	            uri = new URI(urlBdi);  
	        } catch (URISyntaxException e1) {  
	            e1.printStackTrace();  
	        }     
	            
	        //Dispara o browser default, que pode ser o Explorer, Firefox ou outro.     
	        try {  
	            desktop.browse(uri);  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	}  


