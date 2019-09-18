package online.lucianofelix.zebra;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
  Esta classe � composta por uma �rea que apresenta os arquivos texto
  e permite a descompacta��o. O arquivo zip pode ser selecionado pelo menu.
*/
public class DescompactadordeBdi extends JFrame {
  // tamanho da tela
  public static final int DEFAULT_WIDTH = 600; 
  public static final int DEFAULT_HEIGHT = 400;


  private JComboBox<String> comboArquivosNoZip;
  private JTextArea areaArquivoTexto; // local para apresentar os arquivos texto
  private JButton btnExtrair; // 
  private String nomeArquivoZip;
  private ArrayList<String> arrayNomesArqNoZip;
  public DescompactadordeBdi() {
    setTitle("Exemplo de utilizacao de java.util.zip");
    setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    // menu
    JMenuBar menuBar = new JMenuBar();
    // 1. Arquivo
    JMenu menu = new JMenu("Arquivo"); 
    JMenuItem mnuAbrir = new JMenuItem("Abrir");
    menu.add(mnuAbrir);
    mnuAbrir.addActionListener(new ActionAbrirZip()); // classe definida abaixo
    JMenuItem mnuSair = new JMenuItem("Sair");
    menu.add(mnuSair);
    mnuSair.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) { System.exit(0); }
    });
    menuBar.add(menu);
    setJMenuBar(menuBar);


    // adiciona componentes no Frame
    areaArquivoTexto = new JTextArea();
    comboArquivosNoZip = new JComboBox<String>();
    comboArquivosNoZip.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
 String nomeArquivo = (String) comboArquivosNoZip.getSelectedItem();
 if (nomeArquivo.endsWith(".txt") || nomeArquivo.endsWith(".java")) {
         carregaArquivoNaTextArea(nomeArquivo);
 }
      }
    });
 
    add(comboArquivosNoZip, BorderLayout.NORTH);
    add(new JScrollPane(areaArquivoTexto), BorderLayout.CENTER);
    btnExtrair = new JButton("Extrair");
    btnExtrair.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
 // descompacta o conteudo
        descompactaArquivo((String) comboArquivosNoZip.getSelectedItem());
      }
    });
   add(btnExtrair, BorderLayout.SOUTH);
  } // Construtor ExemploZipInputFrame

  /**
   Classe que realiza a��o de "Abrir" do menu
  */
  private class ActionAbrirZip implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      // seleciona o arquivo Zip
      JFileChooser arquivoEscolhido = new JFileChooser();
      arquivoEscolhido.setCurrentDirectory(new File("."));
      FiltrosDeArquivos filtro = new FiltrosDeArquivos();
      filtro.adicionarExtensao(".zip");
      filtro.adicionarExtensao(".jar");
      filtro.setDescription("Arquivos ZIP");
      arquivoEscolhido.setFileFilter(filtro);
      int escolha = arquivoEscolhido.showOpenDialog(DescompactadordeBdi.this);
      if (escolha == JFileChooser.APPROVE_OPTION) {
        nomeArquivoZip = arquivoEscolhido.getSelectedFile().getPath();
        System.out.println(arquivoEscolhido.getSelectedFile().getPath());
        //dataArquivo=nomeArquivoZip.substring(28,32);
        ProcuraNoArquivoZip();
      }
    }
  } // ActionAbrirZip


  /**
    Lista entradas do arquivo ZIP e preenche o conteudo no combobox
  */
  public void ProcuraNoArquivoZip() {
    comboArquivosNoZip.removeAllItems();
     try {
       ZipInputStream arquivoZip =
        new ZipInputStream(new FileInputStream(nomeArquivoZip));
       ZipEntry entradaNoZip = null;
       while ((entradaNoZip = arquivoZip.getNextEntry()) != null) {
         comboArquivosNoZip.addItem(entradaNoZip.getName());
         arquivoZip.closeEntry();
       }
       arquivoZip.close();
     } catch (IOException e) {
       e.printStackTrace();
     }
  }
  /**
  Lista entradas do arquivo ZIP e preenche o conteudo no array e retorna o array de nomes
*/
  public ArrayList<String> ListaNoArquivoZip() {
	  comboArquivosNoZip.removeAllItems();
	   try {
	     ZipInputStream arquivoZip =
	      new ZipInputStream(new FileInputStream(nomeArquivoZip));
	     ZipEntry entradaNoZip = null;
	     while ((entradaNoZip = arquivoZip.getNextEntry()) != null) {
	       arrayNomesArqNoZip.add(entradaNoZip.getName());
	       arquivoZip.closeEntry();
	     }
	     arquivoZip.close();
	   } catch (IOException e) {
	     e.printStackTrace();
	     return null;
	   }
	   return ListaNoArquivoZip();
	}

  /**
     L� o conte�do do arquivo para a �rea
     @param nome do arquivo compactado (entrada do zip)
   */
   public void carregaArquivoNaTextArea(String nome) {
     BufferedReader arquivoLido = null;
  
     try {
       areaArquivoTexto.setText(""); // apaga o conteudo da �rea de texto
       ZipInputStream arquivoZip =
         new ZipInputStream(new FileInputStream(nomeArquivoZip));
       ZipEntry entradaNoZip = null;
       // varre as entradas
       // acha entrada = "nome"
       while ((entradaNoZip = arquivoZip.getNextEntry()) != null) {
  if (entradaNoZip.getName().equals(nome)) {
    // se achou ... le para �rea de texto
    arquivoLido = new BufferedReader(new InputStreamReader(arquivoZip));
    String linhaLida;
    while ((linhaLida = arquivoLido.readLine()) != null) {
      areaArquivoTexto.append(linhaLida);
      areaArquivoTexto.append("\n");
    }
  }
         arquivoZip.closeEntry();  
       }
       arquivoZip.close();
     } catch (IOException e) {
       e.printStackTrace();
     }
   }


   public void descompactaArquivo(String nome) {
     try {
       areaArquivoTexto.setText(""); // apaga o conteudo da �rea de texto
       ZipInputStream arquivoZip =
          new ZipInputStream(new FileInputStream(nomeArquivoZip));
       ZipEntry entradaNoZip = null;
       // acha entrada = "nome"
       while ((entradaNoZip = arquivoZip.getNextEntry()) != null) {
         if (entradaNoZip.getName().equals(nome)) {
           BufferedOutputStream arquivoDescompactado =
             new BufferedOutputStream(new FileOutputStream(nome));
           for(int i = arquivoZip.read(); i != -1; i = arquivoZip.read() ) {
      arquivoDescompactado.write(i);
    }
    arquivoDescompactado.close();
  }
  arquivoZip.closeEntry();
       }
       arquivoZip.close();
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
public String getNomeArquivoZip() {
	return nomeArquivoZip;
}
public void setNomeArquivoZip(String nomeArquivoZip) {
	this.nomeArquivoZip = nomeArquivoZip;
}


   
} // ExemploZipInputFrame