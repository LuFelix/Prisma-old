package online.lucianofelix.zebra;
import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;

/**
  Esta classe cria um filtro para localizar os arquivos texto
  Utilizado em JFileChooser quando tenta selecionar um arquivo em ABRIR
*/
public class FiltrosDeArquivos extends FileFilter {


   private String descricao = "";
   // guarda a lista de extens�es suportadas
   private ArrayList<String> extensoesArquivos = new ArrayList<String>();


  /**
     adiciona uma extens�o no filtro
     @param extens�o do arquivo tipo ".txt" ou "txt"
  */
   public void adicionarExtensao(String e) {
     if (!e.startsWith(".")) { // acrescenta o ponto
        e = "." + e;
     }
     extensoesArquivos.add(e.toLowerCase());
   }


   /**
      Armazena a descri��o
      @param desc: uma descricao para o conjunto de filtros
   */
   public void setDescription(String desc) { descricao = desc; }


   /**
      Returna a descricao
      @return descricao
   */
   public String getDescription() { return descricao; }


   /**
      Indica que pode selecionar a entrada
   */
   public boolean accept(File f) {
     if (f.isDirectory()) return true;
     String name = f.getName().toLowerCase();


     // verifica se a extens�o passa no filtro
     for (String e : extensoesArquivos) {
       if (name.endsWith(e)) return true;
     }
     return false; // n�o achou
   }


}
